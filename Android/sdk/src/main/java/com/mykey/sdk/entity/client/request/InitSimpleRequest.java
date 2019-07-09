package com.mykey.sdk.entity.client.request;

import android.content.Context;

import com.mykey.sdk.common.constants.ConfigCons;

public class InitSimpleRequest {
    private String protocol = ConfigCons.MYKEY_SIMPLE_WALLET_PROTOCOL;
    private Context context;
    private String dappName;
    private String dappIcon;
    // Whether to disable the default install page when MYKEY is not installed
    private boolean disableInstall = false;
    // Deeplink MYKEY callback to dapp,defined in AndroidManifest.xml, e.g. customscheme://customhost/custompath
    private String callback;

    public String getProtocol() {
        return protocol;
    }

    public String getCallback() {
        return callback;
    }

    public InitSimpleRequest setCallback(String callBackPage) {
        this.callback = callBackPage;
        return this;
    }

    public boolean isDisableInstall() {
        return disableInstall;
    }

    public InitSimpleRequest setDisableInstall(boolean disableInstall) {
        this.disableInstall = disableInstall;
        return this;
    }

    public Context getContext() {
        return context;
    }

    public InitSimpleRequest setContext(Context context) {
        this.context = context;
        return this;
    }

    public String getDappName() {
        return dappName;
    }

    public InitSimpleRequest setDappName(String dappName) {
        this.dappName = dappName;
        return this;
    }

    public String getDappIcon() {
        return dappIcon;
    }

    public InitSimpleRequest setDappIcon(String dappIcon) {
        this.dappIcon = dappIcon;
        return this;
    }
}
