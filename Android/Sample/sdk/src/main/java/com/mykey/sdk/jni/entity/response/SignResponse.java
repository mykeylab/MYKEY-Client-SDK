package com.mykey.sdk.jni.entity.response;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zero on 2019/7/31.
 */

public class SignResponse {
    @JSONField(name = "signed_data")
    private String signedData;

    public String getSignedData() {
        return signedData;
    }

    public void setSignedData(String signedData) {
        this.signedData = signedData;
    }
}
