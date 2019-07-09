package com.mykey.sdk.sample.controller;

import android.app.Activity;
import android.widget.Toast;

import com.mykey.sdk.common.iface.ApiCallback;
import com.mykey.sdk.common.util.LogUtil;
import com.mykey.sdk.plugin.StakeTokenPlugin;

/**
 * Created by zero on 2019/6/5.
 */

public class StakeTokenApiController {
    private static final String TAG = "MYKEYSdk";

    private Activity activity;

    public StakeTokenApiController(Activity activity) {
        this.activity = activity;
    }

    public void onGetBalance() {
        StakeTokenPlugin.getInstance().getBalance("EOS", "hellomykey11", "ADD", new ApiCallback() {
            @Override
            public void onError(String payloadJson) {
                Toast.makeText(activity, "error, payloadJson:" + payloadJson, Toast.LENGTH_LONG).show();
                LogUtil.e(TAG, "in error:" + payloadJson);
            }

            @Override
            public void onSuccess(String dataJson) {
                Toast.makeText(activity, "success", Toast.LENGTH_LONG).show();
                LogUtil.e(TAG, "in success:" + dataJson);
            }
        });
    }

    public void onGetUnlockList() {
        StakeTokenPlugin.getInstance().getUnlockList("EOS", "hellomykey11", "ADD", new ApiCallback() {
            @Override
            public void onError(String payloadJson) {
                Toast.makeText(activity, "error, payloadJson:" + payloadJson, Toast.LENGTH_LONG).show();
                LogUtil.e(TAG, "in error:" + payloadJson);
            }

            @Override
            public void onSuccess(String dataJson) {
                Toast.makeText(activity, "success", Toast.LENGTH_LONG).show();
                LogUtil.e(TAG, "in success:" + dataJson);
            }
        });
    }

    public void onBindInfo() {
        StakeTokenPlugin.getInstance().getBindInfo(new ApiCallback() {
            @Override
            public void onError(String payloadJson) {
                Toast.makeText(activity, "error, payloadJson:" + payloadJson, Toast.LENGTH_LONG).show();
                LogUtil.e(TAG, "in error:" + payloadJson);
            }

            @Override
            public void onSuccess(String dataJson) {
                Toast.makeText(activity, "success", Toast.LENGTH_LONG).show();
                LogUtil.e(TAG, "in success:" + dataJson);
            }
        });
    }
}
