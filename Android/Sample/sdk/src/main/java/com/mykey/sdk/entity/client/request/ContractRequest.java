package com.mykey.sdk.entity.client.request;

import com.mykey.sdk.entity.client.request.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero on 2019/5/27.
 */

public class ContractRequest extends BaseRequest {
    // like scatter
    private List<BaseAction> actions;
    private String orderId;

    public ContractRequest() {
        actions = new ArrayList<>();
    }

    public String getInfo() {
        return info;
    }

    public ContractRequest setInfo(String info) {
        this.info = info;
        return this;
    }

    public List<BaseAction> getActions() {
        return actions;
    }

    public ContractRequest addAction(BaseAction action) {
        actions.add(action);
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public ContractRequest setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getCallBackUrl() {
        return callbackUrl;
    }

    public ContractRequest setCallBackUrl(String callBackUrl) {
        this.callbackUrl = callBackUrl;
        return this;
    }

    public String getChain() {
        return chain;
    }

    public ContractRequest setChain(String chain) {
        this.chain = chain;
        return this;
    }
}
