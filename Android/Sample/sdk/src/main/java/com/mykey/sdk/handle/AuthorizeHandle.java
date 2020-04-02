package com.mykey.sdk.handle;

import android.content.Context;

import com.mykey.sdk.common.constants.WalletActionCons;
import com.mykey.sdk.common.store.memory.MemoryManager;
import com.mykey.sdk.common.util.JsonUtil;
import com.mykey.sdk.connect.scheme.SchemeConnectManager;
import com.mykey.sdk.connect.scheme.callback.MYKEYCallbackManager;
import com.mykey.sdk.connect.scheme.callback.MYKEYWalletCallback;
import com.mykey.sdk.entity.agreement.request.AuthorizeProtocolRequest;
import com.mykey.sdk.entity.client.request.AuthorizeRequest;
import com.mykey.sdk.jni.MYKEYWalletJni;

/**
 * Created by zero on 2019/5/27.
 */

public class AuthorizeHandle extends BaseHandle {
    public void handle(Context context, AuthorizeRequest authorizeRequest, MYKEYWalletCallback mykeyWalletCallback) {
        // create private key
        String servicePublicKey = retrievePrivateKey(context);
        String protocolJson = getAuthorizeAgreementJson(authorizeRequest, MYKEYCallbackManager.getInstance().getCallBackId(mykeyWalletCallback), servicePublicKey);
        SchemeConnectManager.getInstance().sendToMYKEY(context, protocolJson, mykeyWalletCallback);
    }

    private String getAuthorizeAgreementJson(AuthorizeRequest authorizeRequest, String callBackId, String publicKey) {
        AuthorizeProtocolRequest authorizeAgreementRequest = new AuthorizeProtocolRequest();
        authorizeAgreementRequest.setAction(WalletActionCons.LOGIN);
        authorizeAgreementRequest.setUuID(MemoryManager.getUserId());
        authorizeAgreementRequest.setLoginUrl(authorizeRequest.getCallbackUrl());
        authorizeAgreementRequest.setLoginMemo(authorizeRequest.getInfo());

        authorizeAgreementRequest.setDappUserName(authorizeRequest.getUserName());
        authorizeAgreementRequest.setRequestPubKey(MYKEYWalletJni.getRequestPubKey());
        authorizeAgreementRequest.setServicePubKey(publicKey);

        fillCommonData(authorizeAgreementRequest, callBackId, authorizeRequest.getChain());
        return JsonUtil.toJson(authorizeAgreementRequest);
    }

}
