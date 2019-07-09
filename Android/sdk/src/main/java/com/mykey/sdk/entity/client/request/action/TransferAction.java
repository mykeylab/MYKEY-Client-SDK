package com.mykey.sdk.entity.client.request.action;

import com.mykey.sdk.entity.client.request.action.data.TransferData;

/**
 * Created by zero on 2019/6/3.
 */

public class TransferAction extends BaseAction {
    private TransferData data;

    public TransferData getData() {
        return data;
    }

    public void setData(TransferData data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public TransferAction setInfo(String info) {
        this.info = info;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public TransferAction setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getName() {
        return name;
    }

    public TransferAction setName(String name) {
        this.name = name;
        return this;
    }
}

