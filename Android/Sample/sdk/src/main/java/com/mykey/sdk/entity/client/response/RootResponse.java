package com.mykey.sdk.entity.client.response;

/**
 * Created by zero on 2019/5/27.
 */

public class RootResponse {
    private String callBackId;
    private int errorCode;
    private String data;

    public String getCallBackId() {
        return callBackId;
    }

    public void setCallBackId(String callBackId) {
        this.callBackId = callBackId;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
