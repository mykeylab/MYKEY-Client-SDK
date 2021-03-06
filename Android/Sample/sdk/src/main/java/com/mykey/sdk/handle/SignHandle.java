package com.mykey.sdk.handle;

import android.content.Context;

import com.mykey.sdk.common.constants.WalletActionCons;
import com.mykey.sdk.common.util.JsonUtil;
import com.mykey.sdk.connect.scheme.SchemeConnectManager;
import com.mykey.sdk.connect.scheme.callback.MYKEYCallbackManager;
import com.mykey.sdk.connect.scheme.callback.MYKEYWalletCallback;
import com.mykey.sdk.entity.agreement.request.SignProtocolRequest;
import com.mykey.sdk.entity.client.request.SignRequest;

/**
 * Created by zero on 2019/5/27.
 */

public class SignHandle extends BaseHandle {
    public void handle(Context context, SignRequest signRequest, MYKEYWalletCallback mykeyWalletCallback) {
        SchemeConnectManager.getInstance().sendToMYKEY(context, getSignAgreementJson(signRequest, MYKEYCallbackManager.getInstance().getCallBackId(mykeyWalletCallback)), mykeyWalletCallback);
    }

    private String getSignAgreementJson(SignRequest signRequest, String callBackId) {
        SignProtocolRequest signAgreementRequest = new SignProtocolRequest();
        signAgreementRequest.setMessage(signRequest.getMessage());
        signAgreementRequest.setSignUrl(signRequest.getCallBackUrl());
        signAgreementRequest.setAction(WalletActionCons.SIGN);

        fillCommonData(signAgreementRequest, callBackId, signRequest.getChain());
        return JsonUtil.toJson(signAgreementRequest);
    }
}
