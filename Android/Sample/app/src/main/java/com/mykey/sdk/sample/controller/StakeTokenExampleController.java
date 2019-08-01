package com.mykey.sdk.sample.controller;

import android.app.Activity;
import android.widget.Toast;

import com.mykey.sdk.MYKEYSdk;
import com.mykey.sdk.connect.scheme.callback.MYKEYWalletCallback;
import com.mykey.sdk.common.util.LogUtil;
import com.mykey.sdk.entity.client.request.ContractRequest;
import com.mykey.sdk.entity.client.request.TransferRequest;
import com.mykey.sdk.entity.client.request.action.ContractAction;
import com.mykey.sdk.sample.entity.StakeEntity;

/**
 * Created by zero on 2019/6/5.
 */

public class StakeTokenExampleController {

    private static final String TAG = "MYKEYSdk";

    private Activity activity;

    public StakeTokenExampleController(Activity activity) {
        this.activity = activity;
    }

    public void onStake() {
        ContractRequest contractRequest = new ContractRequest()
                .setInfo("action memo");
        ContractAction contractActionRequest = new ContractAction();
        contractActionRequest.setAccount("mkstaketoken")
                .setName("stake")
                .setInfo("Execute contract lock 1.0000 SYS")
                // uiuiui111111
                .setData(new StakeEntity().setOwner("mykeyhulu525").setQuantity("1.0000 SYS"));
        contractRequest.addAction(contractActionRequest);

        MYKEYSdk.getInstance().contract(contractRequest, new MYKEYWalletCallback() {
            @Override
            public void onSuccess(String dataJson) {
                LogUtil.e(TAG, "onSuccess");
                Toast.makeText(activity, "success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String payloadJson) {
                LogUtil.e(TAG, "onError payloadJson:" + payloadJson);
                Toast.makeText(activity, "error, payloadJson:" + payloadJson, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                LogUtil.e(TAG, "cancel:");
                Toast.makeText(activity, "cancelled", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onUnStake() {
        ContractRequest contractRequest = new ContractRequest()
                .setInfo("action memo");
        ContractAction contractActionRequest = new ContractAction();
        contractActionRequest.setAccount("hellomykey11")
                .setName("unstake")
                .setInfo("Execute contract unlock 1.0000 ADD")
                .setData(new StakeEntity().setOwner("bobbobbobbob").setQuantity("1.0000 ADD"));
        contractRequest.addAction(contractActionRequest);

        MYKEYSdk.getInstance().contract(contractRequest, new MYKEYWalletCallback() {
            @Override
            public void onSuccess(String dataJson) {
                LogUtil.e(TAG, "onSuccess");
                Toast.makeText(activity, "success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String payloadJson) {
                LogUtil.e(TAG, "onError payloadJson:" + payloadJson);
                Toast.makeText(activity, "error, payloadJson:" + payloadJson, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                LogUtil.e(TAG, "cancel:");
                Toast.makeText(activity, "cancelled", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onTransfer() {
        TransferRequest transferRequest = new TransferRequest()
                .setFrom("bobbobbobbob")
                .setTo("alicealice11")
                .setAmount(1)
                .setMemo("")
                .setContractName("hellomykey11")
                .setSymbol("ADD")
                .setInfo("transfer to alicealice11")
                .setDecimal(4);
        MYKEYSdk.getInstance().transfer(transferRequest, new MYKEYWalletCallback() {
            @Override
            public void onSuccess(String dataJson) {
                LogUtil.e(TAG, "onSuccess data：" + dataJson);
                Toast.makeText(activity, "success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String payloadJson) {
                LogUtil.e(TAG, "onError payloadJson:" + payloadJson);
                Toast.makeText(activity, "error, payloadJson:" + payloadJson, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                LogUtil.e(TAG, "cancel:");
                Toast.makeText(activity, "cancelled", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onTransferAndStake() {
        TransferRequest transferRequest = new TransferRequest()
                .setFrom("bobbobbobbob")
                .setTo("alicealice11")
                .setAmount(1)
                .setMemo("Transfer:FromLiquidToStaked")
                .setContractName("hellomykey11")
                .setSymbol("ADD")
                .setInfo("FromLiquidToStaked")
                .setDecimal(4);
        MYKEYSdk.getInstance().transfer(transferRequest, new MYKEYWalletCallback() {
            @Override
            public void onSuccess(String dataJson) {
                LogUtil.e(TAG, "onSuccess data：" + dataJson);
                Toast.makeText(activity, "success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String payloadJson) {
                LogUtil.e(TAG, "onError payloadJson:" + payloadJson);
                Toast.makeText(activity, "error, payloadJson:" + payloadJson, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                LogUtil.e(TAG, "cancel:");
                Toast.makeText(activity, "cancelled", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onUnStakeAndTransfer() {
        TransferRequest transferRequest = new TransferRequest()
                .setFrom("bobbobbobbob")
                .setTo("alicealice11")
                .setAmount(1)
                .setMemo("Transfer:FromStakedToLiquid")
                .setContractName("hellomykey11")
                .setSymbol("ADD ")
                // 此处需要注意，从UnStake中转账的数额应该是需要转账的数额+手续费，如果UnStake中可用币不足会报错
                // UnStake中可用币=UnStake的币-解锁中的币
                .setInfo("FromStakedToLiquid")
                .setDecimal(4);
        MYKEYSdk.getInstance().transfer(transferRequest, new MYKEYWalletCallback() {
            @Override
            public void onSuccess(String dataJson) {
                LogUtil.e(TAG, "onSuccess data：" + dataJson);
                Toast.makeText(activity, "success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String payloadJson) {
                LogUtil.e(TAG, "onError payloadJson:" + payloadJson);
                Toast.makeText(activity, "error, payloadJson:" + payloadJson, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                LogUtil.e(TAG, "cancel:");
                Toast.makeText(activity, "cancelled", Toast.LENGTH_LONG).show();
            }
        });
    }
}
