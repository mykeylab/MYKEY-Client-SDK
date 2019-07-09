package com.mykey.sdk.entity.client.response;

/**
 * Created by zero on 2019/6/25.
 */

public class PayloadResponse {
    private long errorCode;
    private String errorMsg;

    public PayloadResponse(long errorCode) {
        this.errorCode = errorCode;
    }

    public PayloadResponse(long errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
