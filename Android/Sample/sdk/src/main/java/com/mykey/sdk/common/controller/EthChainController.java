package com.mykey.sdk.common.controller;

import com.mykey.sdk.common.util.JsonUtil;
import com.mykey.sdk.common.util.LogUtil;
import com.mykey.sdk.entity.client.request.action.ContractAction;
import com.mykey.sdk.jni.MYKEYWalletJni;

public class EthChainController extends BaseChainController {
    @Override
    public String getBinaryForContract(ContractAction action) {
        LogUtil.e("HongLi", "in getBinaryForContract:" + JsonUtil.toJson(action.getData()));
        return MYKEYWalletJni.ethJsonToBinary(action.getAbi(), action.getName(), JsonUtil.toJson(action.getData()));
    }
}
