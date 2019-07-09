package com.mykey.sdk.common.iface;

/**
 * Created by zero on 2019/6/21.
 */

public interface ApiCallback {
    void onError(String payloadJson);

    void onSuccess(String dataJson);
}
