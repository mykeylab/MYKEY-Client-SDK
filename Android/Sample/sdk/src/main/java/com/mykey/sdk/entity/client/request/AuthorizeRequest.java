package com.mykey.sdk.entity.client.request;

/**
 * Created by zero on 2019/5/27.
 */

public class AuthorizeRequest extends BaseRequest {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public AuthorizeRequest setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public AuthorizeRequest setCallbackUrl(String callBackUrl) {
        this.callbackUrl = callBackUrl;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public AuthorizeRequest setInfo(String info) {
        this.info = info;
        return this;
    }

    public String getChain() {
        return chain;
    }

    public AuthorizeRequest setChain(String chain) {
        this.chain = chain;
        return this;
    }
}
