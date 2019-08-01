package com.mykey.sdk.connect.service;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.mykey.sdk.MYKEYSdk;
import com.mykey.sdk.common.constants.ErrorCons;
import com.mykey.sdk.common.constants.IntentKeyCons;
import com.mykey.sdk.common.util.LogUtil;
import com.mykey.sdk.connect.scheme.SchemeConnectManager;
import com.mykey.sdk.connect.scheme.callback.MYKEYCallbackManager;
import com.mykey.sdk.connect.scheme.callback.MYKEYCallbackResponseFactory;

/**
 * Created by zero on 2019/7/23.
 */

public class ReplyMessengerHandler extends Handler {
    private static final String TAG = "ReplyMessengerHandler";
    @Override
    public void handleMessage(Message message) {
        String callbackId = message.getData().getString(IntentKeyCons.INTENT_KEY_CALLBACK_ID);
        if (TextUtils.isEmpty(callbackId)) {
            LogUtil.e(TAG, "in ReplyMessengerHandler callbackId is empty.");
            MYKEYCallbackManager.getInstance().clearCache();
            return;
        }
        int result = message.getData().getInt(IntentKeyCons.INTENT_KEY_RESULT);
        String payload = message.getData().getString(IntentKeyCons.INTENT_KEY_PAYLOAD);
        LogUtil.i(TAG, "callbackId:" + callbackId + ";result:" + result + ";payload:" + payload);
        handleResult(result, callbackId, payload);
    }

    private void handleResult(int result, String callbackId, String payload) {
        if (result >= ErrorCons.SERVICE_FREE_PROMPT_INVALID) {
            // use scheme protocol
            SchemeConnectManager.getInstance().sendToMYKEY(MYKEYSdk.getInstance().getContext(),
                    MYKEYCallbackManager.getInstance().getParam(callbackId),
                    MYKEYCallbackManager.getInstance().getMYKEYCallback(callbackId));
            return;
        }
        MYKEYCallbackManager.getInstance().dispatch(MYKEYCallbackResponseFactory.getResultResponse(callbackId, result, payload));
    }

}
