package com.mykey.sdk.handle;

import android.content.Context;

import com.mykey.sdk.common.constants.WalletActionCons;
import com.mykey.sdk.common.util.JsonUtil;
import com.mykey.sdk.entity.agreement.request.ContractProtocolRequest;
import com.mykey.sdk.entity.client.request.ContractRequest;
import com.mykey.sdk.callback.MYKEYCallbackManager;
import com.mykey.sdk.callback.MYKEYWalletCallback;

/**
 * Created by zero on 2019/5/27.
 */

public class ContractHandle extends BaseHandle {
    public void handle(Context context, ContractRequest contractRequest, MYKEYWalletCallback MYKEYWalletCallback) {
        this.MYKEYWalletCallback = MYKEYWalletCallback;
        wakeUpMyKey(context, getContractAgreementJson(contractRequest, MYKEYCallbackManager.getInstance().getCallBackId(MYKEYWalletCallback)));
    }

    private String getContractAgreementJson(ContractRequest contractRequest, String callBackId) {
        ContractProtocolRequest contractAgreementRequest = new ContractProtocolRequest();
        contractAgreementRequest.setAction(WalletActionCons.TRANSACTION);
        contractAgreementRequest.setDesc(contractRequest.getInfo());
        contractAgreementRequest.setOrderId(contractRequest.getOrderId());
        contractAgreementRequest.setActions(contractRequest.getActions());
        contractAgreementRequest.setContractUrl(contractRequest.getCallBackUrl());

        fillCommonData(contractAgreementRequest, callBackId);
        return JsonUtil.toJson(contractAgreementRequest);
    }
}
