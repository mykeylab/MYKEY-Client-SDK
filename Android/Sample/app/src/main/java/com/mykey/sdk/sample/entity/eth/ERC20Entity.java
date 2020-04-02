package com.mykey.sdk.sample.entity.eth;

import com.alibaba.fastjson.annotation.JSONField;

public class ERC20Entity {
    @JSONField(name = "_to")
    private String address;
    @JSONField(name = "_value")
    private String amount;

    public String getAddress() {
        return address;
    }

    public ERC20Entity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAmount() {
        return amount;
    }

    public ERC20Entity setAmount(String amount) {
        this.amount = amount;
        return this;
    }
}
