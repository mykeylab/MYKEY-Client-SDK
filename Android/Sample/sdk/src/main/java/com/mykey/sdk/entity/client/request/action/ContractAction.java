package com.mykey.sdk.entity.client.request.action;

import com.mykey.sdk.common.util.MKUtil;

/**
 * Created by zero on 2019/6/3.
 */

public class ContractAction extends BaseAction {
    private Object data;
    private String abi;
    private String binary;

    public String getBinary() {
        return binary;
    }

    public ContractAction setBinary(String binary) {
        this.binary = binary;
        return this;
    }

    public String getAbi() {
        return abi;
    }

    public ContractAction setAbi(String abi) {
        this.abi = abi;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ContractAction setData(Object data) {
        this.data = MKUtil.parseToJSONObject(data);
        return this;
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
