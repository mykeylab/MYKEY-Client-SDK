package com.mykey.sdk.entity.client.request;

/**
 * Created by zero on 2019/5/27.
 */

public class SignRequest extends BaseRequest {
    private String message;

    public String getMessage() {
        return message;
    }

    public SignRequest setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getCallBackUrl() {
        return callbackUrl;
    }

    public SignRequest setCallBackUrl(String callBackUrl) {
        this.callbackUrl = callBackUrl;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public SignRequest setInfo(String info) {
        this.info = info;
        return this;
    }

    public String getChain() {
        return chain;
    }

    public SignRequest setChain(String chain) {
        this.chain = chain;
        return this;
    }
}
