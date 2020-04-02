package com.mykey.sdk.entity.client.request.action;

/**
 * Created by zero on 2019/6/3.
 */

public class BaseAction {
    protected String info;
    protected String account;
    protected String name;
    protected String chain;

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }
}
