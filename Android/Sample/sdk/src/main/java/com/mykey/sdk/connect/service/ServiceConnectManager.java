package com.mykey.sdk.connect.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.mykey.sdk.common.constants.ConfigCons;
import com.mykey.sdk.common.constants.TimeCons;
import com.mykey.sdk.common.manager.HandlerManager;
import com.mykey.sdk.common.store.memory.MemoryManager;
import com.mykey.sdk.common.util.LogUtil;
import com.mykey.sdk.connect.scheme.SchemeConnectManager;
import com.mykey.sdk.connect.scheme.callback.MYKEYCallbackManager;
import com.mykey.sdk.connect.scheme.callback.MYKEYWalletCallback;
import com.mykey.sdk.jni.MYKEYWalletJni;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zero on 2019/7/23.
 */

public class ServiceConnectManager {
    private static final String TAG = "Messenger";

    // client push messenger
    private Messenger mykeyPushMessenger;
    // client reply messenger
    private Messenger mykeyReplyMessenger = new Messenger(new ReplyMessengerHandler());
    private static final ServiceConnectManager serviceConnectManager = new ServiceConnectManager();

    private Context context;
    private static final int MESSAGE_TO_SERVER = 10001;
    private static final int MESSAGE_FROM_SERVICE = 20001;

    private ServiceConnectManager() {
    }

    public static ServiceConnectManager getInstance() {
        return serviceConnectManager;
    }

    public void init(Context context) {
        this.context = context;
        // bind service
        bindService();
    }

    public void sendToMYKEY(Context context, String param, MYKEYWalletCallback mykeyWalletCallback) {
        this.context = context;
        if (null == mykeyPushMessenger) {
            sendToMYKEYWhenUnbind(context, param, mykeyWalletCallback);
            return;
        }
        Message message = buildMessage(param);
        try {
            // record param before send
            MYKEYCallbackManager.getInstance().registerServiceParam(mykeyWalletCallback, param);
            mykeyPushMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
            LogUtil.e(TAG, "in sendToMYKEY send error, try bind");
            mykeyPushMessenger = null;
            sendToMYKEYWhenUnbind(context, param, mykeyWalletCallback);
        }
    }

    private void sendToMYKEYWhenUnbind(Context context, String param, MYKEYWalletCallback mykeyWalletCallback) {
        boolean bindResult = bindService();
        if (bindResult) {
            // bind return success, but need to delay connect success, so delay to send
            delaySend(context, param, mykeyWalletCallback);
        } else {
            // use scheme send, need clear free prompt, can clear all
            FreePromptManager.clearFreePrompt();
            // connect service error, try send by scheme
            SchemeConnectManager.getInstance().sendToMYKEY(context, param, mykeyWalletCallback);
        }
    }

    private void delaySend(final Context context, final String param, final MYKEYWalletCallback mykeyWalletCallback) {
        HandlerManager.getInstance().postMainDelay(new Runnable() {
            @Override
            public void run() {
                sendToMYKEY(context, param, mykeyWalletCallback);
            }
        }, TimeCons.SERVICE_DELAY_SEND);
    }

    private boolean bindService() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(ConfigCons.MYKEY_PACKAGE_NAME, ConfigCons.CONNECT_CLASS_PATH));
            context.startService(intent);
            boolean bindResult = context.bindService(intent, connection, Context.BIND_AUTO_CREATE);
            if (!bindResult) {
                LogUtil.e(TAG, "can not bind connect service");
            }
            return bindResult;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Message buildMessage(String param) {
        Message message = Message.obtain(null, MESSAGE_TO_SERVER);

        Bundle bundle = new Bundle();
        String encodeParam = MYKEYWalletJni.encodeParam(param);
        bundle.putString(ConfigCons.CONNECT_PARAM_KEY_PARAM, encodeParam);
        bundle.putString(ConfigCons.CONNECT_PARAM_KEY_APPKEY, MemoryManager.getAppKey());
        bundle.putString(ConfigCons.CONNECT_PARAM_KEY_USERID, MemoryManager.getUserId());

        message.setData(bundle);
        // set reply messenger to message
        message.replyTo = mykeyReplyMessenger;
        return message;
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.i(TAG, "ServiceConnection-->" + System.currentTimeMillis());
            // create push messenger when had connected to server
            mykeyPushMessenger = new Messenger(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.i(TAG, "onServiceDisconnected-->binder died");
            mykeyPushMessenger = null;
        }
    };
}
