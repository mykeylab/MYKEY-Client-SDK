package com.mykey.sdk.jni.entity.response;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zero on 2019/7/31.
 */

public class KeyResponse {
    @JSONField(name = "pri_key")
    private String privateKey;
    @JSONField(name = "pub_key")
    private String publicKey;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
