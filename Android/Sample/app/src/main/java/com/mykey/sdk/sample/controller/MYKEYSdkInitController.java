package com.mykey.sdk.sample.controller;

import android.app.Activity;

import com.mykey.sdk.MYKEYSdk;
import com.mykey.sdk.entity.client.request.InitRequest;
import com.mykey.sdk.entity.client.request.InitSimpleRequest;
import com.mykey.sdk.sample.Config;

public class MYKEYSdkInitController {

    private Activity activity;

    public MYKEYSdkInitController(Activity activity) {
        this.activity = activity;
    }

    public void initSdk() {
        MYKEYSdk.getInstance().init(new InitRequest().setAppKey(Config.SAMPLE_DAPP_APP_KEY)
                .setDappName(Config.SAMPLE_DAPP_NAME)
                .setUuid(Config.SAMPLE_DAPP_USER_ID)
                .setDappIcon(Config.SAMPLE_DAPP_ICON)
                .setCallback(Config.SAMPLE_DAPP_CALLBACK)
                .setDisableInstall(false)
                .setMYKEYServer("https://dev-open.mykey.tech")
                .setContext(activity.getApplicationContext()));
    }

    public void initSdkSimple() {
        MYKEYSdk.getInstance().initSimple(new InitSimpleRequest().setDappName(Config.SAMPLE_DAPP_NAME)
                // app key is optional, if you set, will use, otherwise use default prefix and dapp name
                .setAppKey(Config.SAMPLE_DAPP_APP_KEY)
                .setDappIcon(Config.SAMPLE_DAPP_ICON)
                .setCallback(Config.SAMPLE_DAPP_CALLBACK)
                .setDisableInstall(false)
                .setContractPromptFree(true)
                .setContext(activity.getApplicationContext())
                // uuid is optional, if you set, will use.
                .setUuid(Config.SAMPLE_DAPP_USER_ID));
    }
}
