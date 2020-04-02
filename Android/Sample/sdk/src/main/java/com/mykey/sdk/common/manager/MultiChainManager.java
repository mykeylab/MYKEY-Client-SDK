package com.mykey.sdk.common.manager;

import android.text.TextUtils;

import com.mykey.sdk.common.constants.ChainCons;
import com.mykey.sdk.common.controller.BaseChainController;
import com.mykey.sdk.common.controller.EosChainController;
import com.mykey.sdk.common.controller.EthChainController;
import com.mykey.sdk.common.util.LogUtil;
import com.mykey.sdk.entity.client.request.action.ContractAction;

public class MultiChainManager {

    private static final String TAG = "MultiChainManager";

    private static EosChainController eosController = new EosChainController();
    private static EthChainController ethController = new EthChainController();

    public static String getBinaryForContract(String chain, ContractAction action) {
        if (null == action) {
            LogUtil.e(TAG, "in getBinaryForContract contractRequest is null.");
            return "";
        }
        return getChainController(chain).getBinaryForContract(action);
    }

    private static BaseChainController getChainController(String chain) {
        if (TextUtils.isEmpty(chain)) {
            LogUtil.e(TAG, "in getChainController chain is empty.");
            return eosController;
        }
        switch (chain) {
            case ChainCons.ETH:
                return ethController;
            case ChainCons.EOS:
            default:
                // default eos chain
                return eosController;
        }
    }
}
