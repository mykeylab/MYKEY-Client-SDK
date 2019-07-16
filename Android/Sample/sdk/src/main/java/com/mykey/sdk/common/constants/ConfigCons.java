package com.mykey.sdk.common.constants;

/**
 * Created by zero on 2019/5/30.
 */

public interface ConfigCons {
    String SDK_VERSION = "1.0.0";
    String DEVICE = "android";
    // Package name of MYKEY
    String MYKEY_PACKAGE_NAME = "com.mykey.id";
    // deeplink of MYKEY home
    String MYKEY_DEEP_LINK_HOME = "mykey://mykey.org/";
    // deep link of dapp in MYKEY for sample
    String MYKEY_DEEP_LINK_BIG_GAME = "mykey://mykey.org/dapp?url=https%3a%2f%2fbig.game%2f";
    // deep link of MYKEY Sdk
    String MYKEY_DEEP_LINK_WALLET = "mykey://mykey.org/protocol";
    // the format deep link url for MYKEY Sdk, need fill in data
    String MYKEY_DEEP_LINK_WALLET_FORMAT = MYKEY_DEEP_LINK_WALLET + "?param=%s&appKey=%s&userId=%s";
    // the format deep link url for Simple Wallet, need fill in data
    String SIMPLE_WALLET_URL_FORMAT = "simplewallet://eos.io?param=%s";

    String MYKEY_BASE_URL = "https://dev-app.mykey.tech";
    // the product url of MYKEY
    String MYKEY_OFFICIAL_PRODUCT_URL = "https://mykey.org/product";

    // MYKEY Sdk Version
    String MYKEY_WALLET_VERSION = "1.0";
    String MYKEY_WALLET_PROTOCOL = "MYKEY";
    String MYKEY_SIMPLE_WALLET_PROTOCOL = "MYKEYSimple";
    // the format deep link url for callback, need fill in data
    String MYKEY_WALLET_CALLBACK_URL_FORMAT = "%s?action=%s&callBackId=%s";

    String MYKEY_SIMPLE_WALLET_APP_KEY_PREFIX = "MYKEY-simple-wallet-app-key-prefix-&#%";

}
