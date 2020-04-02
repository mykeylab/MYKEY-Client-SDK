package com.mykey.sdk.handle;

import android.content.Context;
import android.text.TextUtils;

import com.mykey.sdk.common.constants.WalletActionCons;
import com.mykey.sdk.common.manager.MultiChainManager;
import com.mykey.sdk.common.store.memory.MemoryManager;
import com.mykey.sdk.common.store.sharepreference.SPManager;
import com.mykey.sdk.common.util.JsonUtil;
import com.mykey.sdk.connect.scheme.SchemeConnectManager;
import com.mykey.sdk.connect.scheme.callback.MYKEYCallbackManager;
import com.mykey.sdk.connect.scheme.callback.MYKEYWalletCallback;
import com.mykey.sdk.connect.service.FreePromptManager;
import com.mykey.sdk.connect.service.ServiceConnectManager;
import com.mykey.sdk.entity.agreement.request.ContractProtocolRequest;
import com.mykey.sdk.entity.client.request.ContractRequest;
import com.mykey.sdk.entity.client.request.action.BaseAction;
import com.mykey.sdk.entity.client.request.action.ContractAction;
import com.mykey.sdk.entity.client.request.action.TransferAction;
import com.mykey.sdk.jni.MYKEYWalletJni;

import java.util.List;

/**
 * Created by zero on 2019/5/27.
 */

public class ContractHandle extends BaseHandle {
    public void handle(Context context, ContractRequest contractRequest, MYKEYWalletCallback mykeyWalletCallback) {
        // check service private
        checkAndCreatePrivate(context);
        String freePromptKey = getFreePromptKey(contractRequest);
        String signedData = MemoryManager.isContractPromptFree() ? getSignData(contractRequest) : "";
        String protocolJson = getContractAgreementJson(contractRequest, MYKEYCallbackManager.getInstance().getCallBackId(mykeyWalletCallback), signedData);
        if (!TextUtils.isEmpty(signedData) && FreePromptManager.isFreePrompt(freePromptKey)) {
            ServiceConnectManager.getInstance().sendToMYKEY(context, protocolJson, mykeyWalletCallback);
        } else {
            SchemeConnectManager.getInstance().sendToMYKEY(context, protocolJson, mykeyWalletCallback);
        }
    }

    private String getContractAgreementJson(ContractRequest contractRequest, String callBackId, String signedData) {
        ContractProtocolRequest contractAgreementRequest = new ContractProtocolRequest();
        contractAgreementRequest.setAction(WalletActionCons.TRANSACTION);
        contractAgreementRequest.setDesc(contractRequest.getInfo());
        contractAgreementRequest.setOrderId(contractRequest.getOrderId());
        contractAgreementRequest.setActions(contractRequest.getActions());
        contractAgreementRequest.setContractUrl(contractRequest.getCallBackUrl());
        contractAgreementRequest.setSignedData(signedData);
        contractAgreementRequest.setServicePubKey(SPManager.getServicePublic());

        fillCommonData(contractAgreementRequest, callBackId, contractRequest.getChain());

        feedInfoToAction(contractAgreementRequest.getActions(), contractRequest.getChain());
        return JsonUtil.toJson(contractAgreementRequest);
    }

    private void feedInfoToAction(List<BaseAction> actions, String chain) {
        if (null == actions || actions.size() == 0) {
            return;
        }
        for (BaseAction action : actions) {
            // if action is ContractAction and binary is empty, set binary by chain
            if (action instanceof ContractAction && TextUtils.isEmpty(((ContractAction) action).getBinary())) {
                ((ContractAction) action).setBinary(MultiChainManager.getBinaryForContract(chain, (ContractAction) action));
            }
            // set chain
            action.setChain(chain);
        }
    }

    private void checkAndCreatePrivate(Context context) {
        String privateKey = SPManager.getServicePrivate();
        String publicKey = SPManager.getServicePublic();
        if (!TextUtils.isEmpty(privateKey) && !TextUtils.isEmpty(publicKey)) {
            return;
        }
        retrievePrivateKey(context);
    }

    private String getFreePromptKey(ContractRequest contractRequest) {
        if (null == contractRequest) {
            return "";
        }
        StringBuilder freePromptKey = new StringBuilder(MemoryManager.getProtocol() + WalletActionCons.TRANSACTION);
        for (BaseAction sdkActionRequest : contractRequest.getActions()) {
            if (sdkActionRequest instanceof ContractAction) {
                freePromptKey.append(((ContractAction) sdkActionRequest).getAccount());
                freePromptKey.append(((ContractAction) sdkActionRequest).getName());
            } else if (sdkActionRequest instanceof TransferAction) {
                // can not free prompt for transfer
                return "";
            }
        }
        return freePromptKey.toString();
    }

    private String getSignData(ContractRequest contractRequest) {
        String privateKey = SPManager.getServicePrivate();
        if (TextUtils.isEmpty(privateKey)) {
            return "";
        }
        String unsignedData = getUnSignedData(contractRequest);
        if (TextUtils.isEmpty(unsignedData)) {
            return "";
        }
        return MYKEYWalletJni.sign(privateKey, unsignedData);
    }

    private String getUnSignedData(ContractRequest contractRequest) {
        StringBuilder unsignedData = new StringBuilder();
        for (BaseAction action : contractRequest.getActions()) {
            if (action instanceof TransferAction) {
                unsignedData.append(((TransferAction) action).getAccount());
                unsignedData.append(((TransferAction) action).getName());
                unsignedData.append(((TransferAction) action).getInfo());
                unsignedData.append(JsonUtil.toJson(((TransferAction) action).getData()));
            } else if (action instanceof ContractAction) {
                unsignedData.append(((ContractAction) action).getAccount());
                unsignedData.append(((ContractAction) action).getName());
                unsignedData.append(((ContractAction) action).getInfo());
                unsignedData.append(JsonUtil.toJson(((ContractAction) action).getData()));
            }
        }
        return unsignedData.toString();
    }
}
