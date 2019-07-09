package com.mykey.sdk.sample.controller;

import android.app.Activity;
import android.widget.Toast;

import com.mykey.sdk.MYKEYSdk;
import com.mykey.sdk.common.util.LogUtil;
import com.mykey.sdk.entity.client.request.AuthorizeRequest;
import com.mykey.sdk.entity.client.request.ContractRequest;
import com.mykey.sdk.entity.client.request.SignRequest;
import com.mykey.sdk.entity.client.request.TransferRequest;
import com.mykey.sdk.entity.client.request.action.ContractAction;
import com.mykey.sdk.entity.client.request.action.TransferAction;
import com.mykey.sdk.entity.client.request.action.data.TransferData;
import com.mykey.sdk.callback.MYKEYWalletCallback;
import com.mykey.sdk.sample.Config;
import com.mykey.sdk.sample.entity.BuyRamDataEntity;
import com.mykey.sdk.sample.entity.StakeEntity;

/**
 * Created by zero on 2019/6/5.
 */

public class MYKEYWalletController {

    private static final String TAG = "MYKEYSdk";

    private Activity activity;

    public MYKEYWalletController(Activity activity) {
        this.activity = activity;
    }

    public void onAuthorize() {
        AuthorizeRequest authorizeRequest = new AuthorizeRequest()
                .setUserName("bobbobbobbob")
                // DApp CallbackUrl
                // param：{"protocol": "", "version": "", "dapp_key": "", "uuID": "", "public_key": "", "sign": "", "ref": "", "timestamp": "", "account": ""}
                // return: same as SimpleWallet {"code": [0-2], "message": ""}
                .setCallbackUrl(Config.DAPP_CALLBACK_URL)
                .setInfo("Perform the binding of dapp and MYKEY");
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

    public void onMyKeyContract() {
        ContractRequest contractRequest = new ContractRequest()
                .setInfo("Perform the mortgage REX operation")
                // order ID which come from dapp server
                .setOrderId("BH1000001")
                // DApp CallbackUrl
                // param：{"protocol": "", "version": "", "tx_id": "", "ref": "", "account": ""}
                // return: same as SimpleWallet {"code": [0-2], "message": ""}
                .setCallBackUrl(Config.DAPP_CALLBACK_URL);

        ContractAction contractActionRequest = new ContractAction();
        contractActionRequest.setAccount("hellomykey11")
                .setName("stake")
                .setInfo("Execute contract lock 1.0000 ADD")
                // JSON string support, eg:setData("{\"player\":\"bobbobbobbob\",\"receiver\":\"alicealice11\",\"quant\":\"1.0000 EOS\"}")
                .setData(new StakeEntity().setOwner("alicealice11").setQuantity("1.0000 ADD"));
        contractRequest.addAction(contractActionRequest);

        TransferAction transferActionRequest = new TransferAction();
        transferActionRequest.setAccount("eosio.token")
                .setName("transfer")
                .setInfo("transfer to alicealice11")
                .setData(new TransferData().setFrom("bobbobbobbob").setTo("alicealice11").setQuantity("0.0001 EOS").setMemo("memo"));
        contractRequest.addAction(transferActionRequest);

        MYKEYSdk.getInstance().contract(contractRequest, new MYKEYWalletCallback() {
            @Override
            public void onSuccess(String dataJson) {
                // dataJson：{"txId":""}
                LogUtil.e(TAG, "onSuccess dataJson:" + dataJson);
                Toast.makeText(activity, "success dataJson:" + dataJson, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String payloadJson) {
                // payloadJson: {"errorCode":,"errorMsg":""}
                LogUtil.e(TAG, "onError payloadJson:" + payloadJson);
                Toast.makeText(activity, "error, payloadJson:" + payloadJson, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                LogUtil.e(TAG, "cancel");
                Toast.makeText(activity, "cancelled", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onMyKeyTransfer() {
        TransferRequest transferRequest = new TransferRequest()
                .setFrom("bobbobbobbob")
                .setTo("alicealice11")
                .setAmount(0.0001)
                .setMemo("memo")
                .setContractName("eosio.token")
                .setSymbol("EOS")
                .setInfo("transfer to alicealice11")
                .setDecimal(4)
                // order ID which come from dapp server
                .setOrderId("BH1000001")
                // DApp CallbackUrl
                // param：{"protocol": "", "version": "", "tx_id": "", "ref": "", "account": ""}
                // return: same as SimpleWallet {"code": [0-2], "message": ""}
                .setCallBackUrl(Config.DAPP_CALLBACK_URL);
        MYKEYSdk.getInstance().transfer(transferRequest, new MYKEYWalletCallback() {
            @Override
            public void onSuccess(String dataJson) {
                // dataJson：{"txId":""}
                LogUtil.e(TAG, "onSuccess dataJson：" + dataJson);
                Toast.makeText(activity, "success dataJson:" + dataJson, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String payloadJson) {
                // payloadJson: {"errorCode":,"errorMsg":""}
                LogUtil.e(TAG, "onError payloadJson:" + payloadJson);
                Toast.makeText(activity, "error, payloadJson:" + payloadJson, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                LogUtil.e(TAG, "cancel");
                Toast.makeText(activity, "cancelled", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onMyKeySign() {
        SignRequest signRequest = new SignRequest().setMessage("Messages that need to be signed, [it could be random which come from dapp server]")
                // DApp CallbackUrl
                // param：{"protocol": "", "version": "", "message": "", "sign": "", "ref": "", "account": ""}
                // return: same as SimpleWallet {"code": [0-2], "message": ""}
                .setCallBackUrl(Config.DAPP_CALLBACK_URL);
        MYKEYSdk.getInstance().sign(signRequest, new MYKEYWalletCallback() {
            @Override
            public void onSuccess(String dataJson) {
                // dataJson：{"sign":""}
                LogUtil.e(TAG, "onSuccess data：" + dataJson);
                Toast.makeText(activity, "success dataJson:" + dataJson, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String payloadJson) {
                // payloadJson: {"errorCode":,"errorMsg":""}
                LogUtil.e(TAG, "onError payloadJson:" + payloadJson);
                Toast.makeText(activity, "error, payloadJson:" + payloadJson, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                LogUtil.e(TAG, "cancel");
                Toast.makeText(activity, "cancelled", Toast.LENGTH_LONG).show();
            }
        });
    }
}
