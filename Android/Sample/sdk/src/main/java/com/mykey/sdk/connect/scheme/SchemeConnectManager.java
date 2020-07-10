package com.mykey.sdk.connect.scheme;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.mykey.sdk.R;
import com.mykey.sdk.common.constants.ConfigCons;
import com.mykey.sdk.common.constants.ErrorCons;
import com.mykey.sdk.common.store.memory.MemoryManager;
import com.mykey.sdk.common.util.LogUtil;
import com.mykey.sdk.common.util.ToastUtil;
import com.mykey.sdk.connect.scheme.callback.MYKEYCallbackManager;
import com.mykey.sdk.connect.scheme.callback.MYKEYCallbackResponseFactory;
import com.mykey.sdk.connect.scheme.callback.MYKEYWalletCallback;
import com.mykey.sdk.entity.client.response.RootResponse;
import com.mykey.sdk.functionpage.guideinstall.GuideInstallActivity;
import com.mykey.sdk.jni.MYKEYWalletJni;

/**
 * Use scheme to send and receive
 * Created by zero on 2019/7/23.
 */

public class SchemeConnectManager {

    private static final String TAG = "SchemeConnectManager";

    private static final SchemeConnectManager schemeConnectManager = new SchemeConnectManager();

    private SchemeConnectManager() {
    }

    public static SchemeConnectManager getInstance() {
        return schemeConnectManager;
    }

    public void sendToMYKEY(Context context, String param, MYKEYWalletCallback mykeyWalletCallback) {
        LogUtil.i(TAG, "in sendToMYKEY.");
        Uri uri = getMyKeySchemeUri(param);
        if (null == uri) {
            LogUtil.e(TAG, "in sendToMYKEY uri is null.");
            return;
        }
        Intent intent = new Intent();
        intent.setData(uri);
        intent.setAction(Intent.ACTION_VIEW);
        // Make sure the newly launched APP has a separate stack
        // Remove this item if you want the new APP to use the same stack as the old APP
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // set MYKEY package name
        intent.setPackage(ConfigCons.MYKEY_PACKAGE_NAME);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.i(TAG, "in sendToMYKEY exception:" + e.getMessage());
            RootResponse rootResponse = MYKEYCallbackResponseFactory.getErrorResponse(ErrorCons.ERROR_CODE_MYKEY_NOT_INSTALL,
                    context.getResources().getString(R.string.error_txt_wake_up), mykeyWalletCallback);
            if (!MemoryManager.isDisableInstall()) {
                // if uninstall, jump to guide to install
                jumpToGuideInstall(context);
            }
            if (MemoryManager.isShowUpgradeTip()) {
                ToastUtil.toast(context, context.getResources().getString(R.string.error_txt_wake_up));
            }
            MYKEYCallbackManager.getInstance().dispatch(rootResponse);
        }
    }

    /**
     * Gets the URL for the MYKEY jump
     * @param param
     * @return
     */
    private static Uri getMyKeySchemeUri(String param) {
        LogUtil.i(TAG, "in getMyKeySchemeUri param:" + param);
        String encodeParam = MYKEYWalletJni.encodeParam(param);
        if (TextUtils.isEmpty(param)) {
            return null;
        }
        return Uri.parse(String.format(ConfigCons.MYKEY_DEEP_LINK_WALLET_FORMAT, encodeParam, MemoryManager.getAppKey(), MemoryManager.getUserId()));
    }

    private void jumpToGuideInstall(Context context) {
        LogUtil.i(TAG, "in jumpToGuideInstall.");
        Intent intent = new Intent(context, GuideInstallActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.i(TAG, "in jumpToGuideInstall exception:" + e.getMessage());
        }
    }
}
