package com.mykey.sdk.callback;

/**
 * Created by zero on 2019/5/27.
 */

public interface MYKEYWalletCallback {
    void onSuccess(String dataJson);

    void onError(String payloadJson);

    void onCancel();
}
