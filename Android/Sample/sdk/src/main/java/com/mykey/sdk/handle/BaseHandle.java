package com.mykey.sdk.handle;

import android.content.Context;
import android.text.TextUtils;

import com.mykey.sdk.common.constants.ConfigCons;
import com.mykey.sdk.common.store.memory.MemoryManager;
import com.mykey.sdk.common.store.sharepreference.SPManager;
import com.mykey.sdk.entity.agreement.request.BaseProtocolRequest;
import com.mykey.sdk.jni.MYKEYWalletJni;
import com.mykey.sdk.jni.entity.response.KeyResponse;

/**
 * Created by zero on 2019/5/30.
 */

public class BaseHandle {

    protected void fillCommonData(BaseProtocolRequest authorizeAgreementRequest, String callBackId) {
        authorizeAgreementRequest.setProtocol(MemoryManager.getProtocol());
        authorizeAgreementRequest.setVersion(ConfigCons.MYKEY_WALLET_VERSION);
        authorizeAgreementRequest.setAppKey(MemoryManager.getAppKey());
        authorizeAgreementRequest.setUuID(MemoryManager.getUserId());
        authorizeAgreementRequest.setDappName(MemoryManager.getDAppName());
        authorizeAgreementRequest.setDappIcon(MemoryManager.getDAppIcon());
        authorizeAgreementRequest.setCallback(getDAppCallBackUrl(MemoryManager.getCallBackPage(), authorizeAgreementRequest.getAction(), callBackId));
        authorizeAgreementRequest.setCallbackId(callBackId);
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

    protected String retrievePrivateKey(Context context) {
        KeyResponse keyResponse = MYKEYWalletJni.createPrivateKey(context);
        if (null == keyResponse || TextUtils.isEmpty(keyResponse.getPrivateKey())) {
            return "";
        }
        // 存储私钥
        SPManager.setServicePrivate(keyResponse.getPrivateKey());
        SPManager.setServicePublic(keyResponse.getPublicKey());
        return keyResponse.getPublicKey();
    }

}
