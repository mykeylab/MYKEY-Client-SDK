package com.mykey.sdk.entity.client.request;

import android.content.Context;

import com.mykey.sdk.common.constants.ConfigCons;

import java.util.UUID;

/**
 * Created by zero on 2019/5/27.
 */

public class InitRequest {
    private String protocol = ConfigCons.MYKEY_WALLET_PROTOCOL;
    private Context context;
    // From MYKEY
    private String appKey;
    private UUID uuid;
    private String dappName;
    private String dappIcon;
    // Whether to disable the default install page when MYKEY is not installed
    private boolean disableInstall = false;
    private boolean showUpgradeTip = false;
    // Deeplink MYKEY callback to dapp,defined in AndroidManifest.xml, e.g. customscheme://customhost/custompath
    private String callback;
    private String mykeyServer;

    public InitRequest() {
    }

    public InitRequest(InitSimpleRequest initSimpleRequest) {
        this.protocol = initSimpleRequest.getProtocol();
        this.context = initSimpleRequest.getContext();
        this.dappName = initSimpleRequest.getDappName();
        this.dappIcon = initSimpleRequest.getDappIcon();
        this.disableInstall = initSimpleRequest.isDisableInstall();
        this.callback = initSimpleRequest.getCallback();
        this.showUpgradeTip = initSimpleRequest.isShowUpgradeTip();
    }

    public String getMYKEYServer() {
        return mykeyServer;
    }

    public InitRequest setMYKEYServer(String mykeyServer) {
        this.mykeyServer = mykeyServer;
        return this;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getCallback() {
        return callback;
    }

    public InitRequest setCallback(String callBackPage) {
        this.callback = callBackPage;
        return this;
    }

    public boolean isDisableInstall() {
        return disableInstall;
    }

    public InitRequest setDisableInstall(boolean disableInstall) {
        this.disableInstall = disableInstall;
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public InitRequest setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public Context getContext() {
        return context;
    }

    public InitRequest setContext(Context context) {
        this.context = context;
        return this;
    }

    public String getAppKey() {
        return appKey;
    }

    public InitRequest setAppKey(String appKey) {
        this.appKey = appKey;
        return this;
    }

    public String getDappName() {
        return dappName;
    }

    public InitRequest setDappName(String dappName) {
        this.dappName = dappName;
        return this;
    }

    public String getDappIcon() {
        return dappIcon;
    }

    public InitRequest setDappIcon(String dappIcon) {
        this.dappIcon = dappIcon;
        return this;
    }

    public boolean isShowUpgradeTip() {
        return showUpgradeTip;
    }

    public InitRequest setShowUpgradeTip(boolean showUpgradeTip) {
        this.showUpgradeTip = showUpgradeTip;
        return this;
    }
}
