package com.mykey.sdk.handle;

import android.content.Context;

import com.mykey.sdk.common.constants.WalletActionCons;
import com.mykey.sdk.common.store.memory.MemoryManager;
import com.mykey.sdk.common.util.JsonUtil;
import com.mykey.sdk.entity.agreement.request.AuthorizeProtocolRequest;
import com.mykey.sdk.entity.client.request.AuthorizeRequest;
import com.mykey.sdk.jni.MYKEYWalletJni;
import com.mykey.sdk.callback.MYKEYCallbackManager;
import com.mykey.sdk.callback.MYKEYWalletCallback;

/**
 * Created by zero on 2019/5/27.
 */

public class AuthorizeHandle extends BaseHandle {
    public void handle(Context context, AuthorizeRequest authorizeRequest, MYKEYWalletCallback MYKEYWalletCallback) {
        this.MYKEYWalletCallback = MYKEYWalletCallback;
        wakeUpMyKey(context, getAuthorizeAgreementJson(authorizeRequest, MYKEYCallbackManager.getInstance().getCallBackId(MYKEYWalletCallback)));
    }

    private String getAuthorizeAgreementJson(AuthorizeRequest authorizeRequest, String callBackId) {
        AuthorizeProtocolRequest authorizeAgreementRequest = new AuthorizeProtocolRequest();
        authorizeAgreementRequest.setAction(WalletActionCons.LOGIN);
        authorizeAgreementRequest.setUuID(MemoryManager.getUserId());
        authorizeAgreementRequest.setLoginUrl(authorizeRequest.getCallbackUrl());
        authorizeAgreementRequest.setLoginMemo(authorizeRequest.getInfo());

        authorizeAgreementRequest.setDappUserName(authorizeRequest.getUserName());
        authorizeAgreementRequest.setRequestPubKey(MYKEYWalletJni.getRequestPubKey());

        fillCommonData(authorizeAgreementRequest, callBackId);
        return JsonUtil.toJson(authorizeAgreementRequest);
    }

}
