package com.mykey.sdk.sample.controller;

import android.app.Activity;
import android.widget.Toast;

import com.mykey.sdk.MYKEYSdk;
import com.mykey.sdk.common.constants.ChainCons;
import com.mykey.sdk.common.util.LogUtil;
import com.mykey.sdk.connect.scheme.callback.MYKEYWalletCallback;
import com.mykey.sdk.entity.client.request.AuthorizeRequest;
import com.mykey.sdk.sample.Config;

public class AuthorizeAnyChainController {

    private static final String TAG = "AuthorizeAnyChainController";

    private Activity activity;

    public AuthorizeAnyChainController(Activity activity) {
        this.activity = activity;
    }

    public void onMyKeyAuthorizeAnyChain() {
        AuthorizeRequest authorizeRequest = new AuthorizeRequest()
                .setChain(ChainCons.ANY)
                .setUserName("bobbobbobbob")
                // DApp CallbackUrl
                // param：{"protocol": "", "version": "", "dapp_key": "", "uuID": "", "public_key": "", "sign": "", "ref": "", "timestamp": "", "account": ""}
                // return: same as SimpleWallet {"code": [0-2], "message": ""}
                .setCallbackUrl(Config.DAPP_CALLBACK_URL)
                .setInfo("info:Perform the binding of dapp and MYKEY");
        MYKEYSdk.getInstance().authorize(authorizeRequest, new MYKEYWalletCallback() {
            @Override
            public void onSuccess(String dataJson) {
                // dataJson：{"protocol": "", "version": "", "dapp_key": "", "uuID": "", "public_key": "", "sign": "", "ref": "", "timestamp": "", "account": ""}
                LogUtil.e(TAG, "onSuccess dataJson：" + dataJson);
                Toast.makeText(activity, "success dataJson：" + dataJson, Toast.LENGTH_LONG).show();
                // At this point, DApp needs to go to DApp server to check whether the login is successful
            }

            @Override
            public void onError(String payloadJson) {
                // payloadJson: {"errorCode":,"errorMsg":""}
                LogUtil.e(TAG, "onError payloadJson:" + payloadJson);
                Toast.makeText(activity, "error，payloadJson:" + payloadJson, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                LogUtil.e(TAG, "cancel");
                Toast.makeText(activity, "cancelled", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onSimpleWalletAuthorizeAnyChain() {
        AuthorizeRequest authorizeRequest = new AuthorizeRequest()
                .setChain(ChainCons.ANY)
                .setUserName("bobbobbobbob")
                // DApp CallbackUrl
                // param：{"protocol": "", "version": "", "dapp_key": "", "uuID": "", "public_key": "", "sign": "", "ref": "", "timestamp": "", "account": ""}
                // return: same as SimpleWallet {"code": [0-2], "message": ""}
                .setCallbackUrl(Config.DAPP_CALLBACK_URL)
                .setInfo("info:Perform the binding of dapp and MYKEY");
        MYKEYSdk.getInstance().authorize(authorizeRequest, new MYKEYWalletCallback() {
            @Override
            public void onSuccess(String dataJson) {
                // dataJson：{"protocol": "", "version": "", "dapp_key": "", "uuID": "", "public_key": "", "sign": "", "ref": "", "timestamp": "", "account": ""}
                LogUtil.e(TAG, "onSuccess dataJson：" + dataJson);
                Toast.makeText(activity, "success dataJson：" + dataJson, Toast.LENGTH_LONG).show();
                // At this point, DApp needs to go to DApp server to check whether the login is successful
            }

            @Override
            public void onError(String payloadJson) {
                // payloadJson: {"errorCode":,"errorMsg":""}
                LogUtil.e(TAG, "onError payloadJson:" + payloadJson);
                Toast.makeText(activity, "error，payloadJson:" + payloadJson, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                LogUtil.e(TAG, "cancel");
                Toast.makeText(activity, "cancelled", Toast.LENGTH_LONG).show();
            }
        });
    }
}
