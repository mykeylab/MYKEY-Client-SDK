package com.mykey.sdk.entity.agreement.request;

/**
 * Created by zero on 2019/5/30.
 */

public class AuthorizeProtocolRequest extends BaseProtocolRequest {

    // dapp server callback
    private String loginUrl;
    // optional
    private String loginMemo;

    private String dappUserName;
    // public key just for MYKEY bind
    private String requestPubKey;

    public String getRequestPubKey() {
        return requestPubKey;
    }

    public void setRequestPubKey(String requestPubKey) {
        this.requestPubKey = requestPubKey;
    }

    public String getDappUserName() {
        return dappUserName;
    }

    public void setDappUserName(String dappUserName) {
        this.dappUserName = dappUserName;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getLoginMemo() {
        return loginMemo;
    }

    public void setLoginMemo(String loginMemo) {
        this.loginMemo = loginMemo;
    }

}
