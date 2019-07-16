package com.mykey.sdk.sample;

import java.util.UUID;

/**
 * Created by zero on 2019/6/11.
 */

public interface Config {
    // CallbackUrl of DAppServer
    String DAPP_CALLBACK_URL = "http://115.159.101.119:8080/app/mock/17/sdk/action/callback";

    String SAMPLE_DAPP_APP_KEY = "9mDxSbyR";
    String SAMPLE_DAPP_NAME = "BiHu";
    String SAMPLE_DAPP_ICON = "https://bihu.com/favicon.ico";
    UUID SAMPLE_DAPP_USER_ID = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
    String SAMPLE_DAPP_CALLBACK = "simple://con.mykey.sdk";
}
