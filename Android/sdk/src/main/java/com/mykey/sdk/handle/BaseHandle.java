package com.mykey.sdk.handle;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.mykey.sdk.R;
import com.mykey.sdk.common.constants.ConfigCons;
import com.mykey.sdk.common.constants.ErrorCons;
import com.mykey.sdk.common.constants.WalletActionCons;
import com.mykey.sdk.common.store.memory.MemoryManager;
import com.mykey.sdk.entity.agreement.request.BaseProtocolRequest;
import com.mykey.sdk.entity.client.response.RootResponse;
import com.mykey.sdk.jni.MYKEYWalletJni;
import com.mykey.sdk.callback.MYKEYCallbackResponseFactory;
import com.mykey.sdk.callback.MYKEYCallbackManager;
import com.mykey.sdk.callback.MYKEYWalletCallback;

/**
 * Created by zero on 2019/5/30.
 */

public class BaseHandle {

    protected MYKEYWalletCallback MYKEYWalletCallback;

    protected void fillCommonData(BaseProtocolRequest authorizeAgreementRequest, String callBackId) {
        authorizeAgreementRequest.setProtocol(MemoryManager.getProtocol());
        authorizeAgreementRequest.setVersion(ConfigCons.MYKEY_WALLET_VERSION);
        authorizeAgreementRequest.setAppKey(MemoryManager.getAppKey());
        authorizeAgreementRequest.setDappName(MemoryManager.getDAppName());
        authorizeAgreementRequest.setDappIcon(MemoryManager.getDAppIcon());
        authorizeAgreementRequest.setCallback(getDAppCallBackUrl(MemoryManager.getCallBackPage(), authorizeAgreementRequest.getAction(), callBackId));
    }

    /**
     * Wake up MYKEY
     * @param context
     * @param param
     */
    protected void wakeUpMyKey(Context context, String param) {
        Intent intent = new Intent();
        intent.setData(getMyKeyJumpUri(param));
        intent.setAction(Intent.ACTION_VIEW);
        // Make sure the newly launched APP has a separate stack
        // Remove this item if you want the new APP to use the same stack as the old APP
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            RootResponse rootResponse = MYKEYCallbackResponseFactory.getErrorResponse(ErrorCons.ERROR_CODE_CAN_NOT_WAKE_UP,
                    context.getResources().getString(R.string.error_txt_wake_up), MYKEYWalletCallback);
            MYKEYCallbackManager.getInstance().dispatch(rootResponse);
        }
    }

    /**
     * Gets the URL for the MYKEY jump
     * @param param
     * @return
     */
    private Uri getMyKeyJumpUri(String param) {
        String encodeParam = MYKEYWalletJni.encodeParam(param);
        return Uri.parse(String.format(ConfigCons.MYKEY_DEEP_LINK_WALLET_FORMAT, encodeParam, MemoryManager.getAppKey(), MemoryManager.getUserId()));
    }

    /**
     * Get the page URL of the DApp for the MYKEY callback
     * @param callBackPage
     * @param action
     * @param callBackId
     * @return
     */
    protected String getDAppCallBackUrl(String callBackPage, String action, String callBackId) {
        return String.format(ConfigCons.MYKEY_WALLET_CALLBACK_URL_FORMAT, callBackPage, action, callBackId);
    }
}
