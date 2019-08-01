package com.mykey.sdk.connect.scheme;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.mykey.sdk.R;
import com.mykey.sdk.common.constants.ConfigCons;
import com.mykey.sdk.common.constants.ErrorCons;
import com.mykey.sdk.common.store.memory.MemoryManager;
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
    private static final SchemeConnectManager schemeConnectManager = new SchemeConnectManager();

    private SchemeConnectManager() {
    }

    public static SchemeConnectManager getInstance() {
        return schemeConnectManager;
    }

    public void sendToMYKEY(Context context, String param, MYKEYWalletCallback mykeyWalletCallback) {
        Intent intent = new Intent();
        intent.setData(getMyKeySchemeUri(param));
        intent.setAction(Intent.ACTION_VIEW);
        // Make sure the newly launched APP has a separate stack
        // Remove this item if you want the new APP to use the same stack as the old APP
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            RootResponse rootResponse = MYKEYCallbackResponseFactory.getErrorResponse(ErrorCons.ERROR_CODE_MYKEY_NOT_INSTALL,
                    context.getResources().getString(R.string.error_txt_wake_up), mykeyWalletCallback);
            if (!MemoryManager.isDisableInstall()) {
                // 如果配置允许则跳转到引导安装页面
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
        String encodeParam = MYKEYWalletJni.encodeParam(param);
        return Uri.parse(String.format(ConfigCons.MYKEY_DEEP_LINK_WALLET_FORMAT, encodeParam, MemoryManager.getAppKey(), MemoryManager.getUserId()));
    }

    private void jumpToGuideInstall(Context context) {
        Intent intent = new Intent(context, GuideInstallActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
