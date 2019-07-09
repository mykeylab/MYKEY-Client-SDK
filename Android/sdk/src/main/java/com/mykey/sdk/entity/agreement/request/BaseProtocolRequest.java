package com.mykey.sdk.entity.agreement.request;

/**
 * Created by zero on 2019/6/3.
 */

public abstract class BaseProtocolRequest {
    // Come from MYKEY
    private String appKey;
    // SimpleWallet | MYKEY | MYKEYSimple
    private String protocol;
    // protocol version
    private String version;
    private String dappName;
    private String dappIcon;
    // login|transfer|transaction|sign
    private String action;
    private String uuID;
    // appABC://abc.com?action=login&result=1
    // result：0:cancelled,1:success,2:failure
    private String callback;

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDappName() {
        return dappName;
    }

    public void setDappName(String dappName) {
        this.dappName = dappName;
    }

    public String getDappIcon() {
        return dappIcon;
    }

    public void setDappIcon(String dappIcon) {
        this.dappIcon = dappIcon;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUuID() {
        return uuID;
    }

    public void setUuID(String uuID) {
        this.uuID = uuID;
    }
}
