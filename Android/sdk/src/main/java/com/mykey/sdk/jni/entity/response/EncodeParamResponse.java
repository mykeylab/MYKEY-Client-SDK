package com.mykey.sdk.jni.entity.response;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zero on 2019/5/30.
 */

public class EncodeParamResponse {
    @JSONField(name = "encode_data")
    private String encodeData;

    public String getEncodeData() {
        return encodeData;
    }

    public void setEncodeData(String encodeData) {
        this.encodeData = encodeData;
    }
}
