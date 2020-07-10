package com.mykey.sdk.entity.client.request.action;

import com.mykey.sdk.common.util.MKUtil;

/**
 * Created by zero on 2019/6/3.
 */

public class ContractAction extends BaseAction {
    private Object data;
    private String abi;
    private String binary;
    // Now just for ETH，call contract and transfer eth at the same time.
    private double amount;
    private String symbol;
    private String quantity;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSymbol() {
        return symbol;
    }

    public ContractAction setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public ContractAction setAmount(double amount) {
        this.amount = amount;
        return this;
    }

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
