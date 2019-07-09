package com.mykey.sdk.jni.entity.response;


import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zero on 2019/5/30.
 */

public class BaseJniResponse<T> {
    @JSONField(name = "result_code")
    private int resultCode;
    @JSONField(name = "result_message")
    private String resultMessage;
    @JSONField(name = "result_data")
    private T resultData;

    public int getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }
}
