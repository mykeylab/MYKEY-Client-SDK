package com.mykey.sdk.entity.agreement.request;

import com.mykey.sdk.entity.client.request.action.BaseAction;

import java.util.List;

/**
 * Created by zero on 2019/6/3.
 */

public class ContractProtocolRequest extends BaseProtocolRequest {
    private List<BaseAction> actions;
    // 交易的说明信息，钱包在付款UI展示给用户,可选
    private String desc;
    private String contractUrl;

    private String orderId;
    private String signedData;
    // public key just for MYKEY service
    private String servicePubKey;

    public String getServicePubKey() {
        return servicePubKey;
    }

    public void setServicePubKey(String servicePubKey) {
        this.servicePubKey = servicePubKey;
    }

    public String getSignedData() {
        return signedData;
    }

    public void setSignedData(String signedData) {
        this.signedData = signedData;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<BaseAction> getActions() {
        return actions;
    }

    public void setActions(List<BaseAction> actions) {
        this.actions = actions;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
