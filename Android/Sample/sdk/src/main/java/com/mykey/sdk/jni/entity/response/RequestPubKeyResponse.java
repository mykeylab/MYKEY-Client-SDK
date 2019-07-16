package com.mykey.sdk.jni.entity.response;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zero on 2019/5/31.
 */

public class RequestPubKeyResponse {
    @JSONField(name = "pub_key")
    private String requestPubKey;

    public String getRequestPubKey() {
        return requestPubKey;
    }

    public void setRequestPubKey(String requestPubKey) {
        this.requestPubKey = requestPubKey;
    }
}
