package com.mykey.sdk.entity.agreement.request;

/**
 * Created by zero on 2019/6/4.
 */

public class SignProtocolRequest extends BaseProtocolRequest {
    private String message;
    private String signUrl;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSignUrl() {
        return signUrl;
    }

    public void setSignUrl(String signUrl) {
        this.signUrl = signUrl;
    }
}
