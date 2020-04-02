package com.mykey.sdk.sample.controller;

import android.app.Activity;
import android.widget.Toast;

import com.mykey.sdk.MYKEYSdk;
import com.mykey.sdk.connect.scheme.callback.MYKEYWalletCallback;
import com.mykey.sdk.common.util.LogUtil;
import com.mykey.sdk.entity.client.request.ContractRequest;
import com.mykey.sdk.entity.client.request.TransferRequest;
import com.mykey.sdk.entity.client.request.action.ContractAction;
import com.mykey.sdk.sample.entity.eos.StakeEntity;

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
        contractActionRequest.setAccount("hellomykey11")
                .setName("stake")
                .setInfo("Execute contract lock 1.0000 ADD")
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
                // The amount transferred from unstake should be the amount to be transferred + handling fee.
                // If the available currency in unstake is insufficient, an error will be reported
                // Currency available in unstake = unstake currency - currency in unlock
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
