package com.mykey.sdk.common.controller;

import com.mykey.sdk.entity.client.request.action.ContractAction;

public abstract class BaseChainController {
    public abstract String getBinaryForContract(ContractAction action);
}
