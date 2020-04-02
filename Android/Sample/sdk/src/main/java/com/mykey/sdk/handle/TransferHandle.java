package com.mykey.sdk.handle;

import android.content.Context;

import com.mykey.sdk.common.constants.WalletActionCons;
import com.mykey.sdk.common.util.JsonUtil;
import com.mykey.sdk.connect.scheme.SchemeConnectManager;
import com.mykey.sdk.connect.scheme.callback.MYKEYCallbackManager;
import com.mykey.sdk.connect.scheme.callback.MYKEYWalletCallback;
import com.mykey.sdk.entity.agreement.request.TransferProtocolRequest;
import com.mykey.sdk.entity.client.request.TransferRequest;

/**
 * Created by zero on 2019/5/27.
 */

public class TransferHandle extends BaseHandle {
    public void handle(Context context, TransferRequest transferRequest, MYKEYWalletCallback mykeyWalletCallback) {
        SchemeConnectManager.getInstance().sendToMYKEY(context, getTransferAgreementJson(transferRequest, MYKEYCallbackManager.getInstance().getCallBackId(mykeyWalletCallback)), mykeyWalletCallback);
    }

    private String getTransferAgreementJson(TransferRequest transferRequest, String callBackId) {
        TransferProtocolRequest transferAgreementRequest = new TransferProtocolRequest();
        transferAgreementRequest.setFrom(transferRequest.getFrom());
        transferAgreementRequest.setTo(transferRequest.getTo());
        transferAgreementRequest.setAmount(transferRequest.getAmount());
        transferAgreementRequest.setContract(transferRequest.getContractName());
        transferAgreementRequest.setSymbol(transferRequest.getSymbol());
        transferAgreementRequest.setPrecision(transferRequest.getDecimal());
        transferAgreementRequest.setDappData(transferRequest.getMemo());
        transferAgreementRequest.setDesc(transferRequest.getInfo());
        transferAgreementRequest.setOrderId(transferRequest.getOrderId());
        transferAgreementRequest.setAction(WalletActionCons.TRANSFER);
        transferAgreementRequest.setTransferUrl(transferRequest.getCallBackUrl());

        fillCommonData(transferAgreementRequest, callBackId, transferRequest.getChain());
        return JsonUtil.toJson(transferAgreementRequest);
    }
}
