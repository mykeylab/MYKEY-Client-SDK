package com.mykey.sdk.entity.client.request.action;

import com.mykey.sdk.common.util.MKUtil;

/**
 * Created by zero on 2019/6/3.
 */

public class ContractAction extends BaseAction {
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = MKUtil.parseToJSONObject(data);
    }

    public String getInfo() {
        return info;
    }

    public ContractAction setInfo(String info) {
        this.info = info;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public ContractAction setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getName() {
        return name;
    }

    public ContractAction setName(String name) {
        this.name = name;
        return this;
    }
}
