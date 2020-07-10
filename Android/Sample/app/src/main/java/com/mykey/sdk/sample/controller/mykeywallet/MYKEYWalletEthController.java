package com.mykey.sdk.sample.controller.mykeywallet;

import android.app.Activity;
import android.widget.Toast;

import com.mykey.sdk.MYKEYSdk;
import com.mykey.sdk.common.constants.ChainCons;
import com.mykey.sdk.common.util.LogUtil;
import com.mykey.sdk.connect.scheme.callback.MYKEYWalletCallback;
import com.mykey.sdk.entity.client.request.AuthorizeRequest;
import com.mykey.sdk.entity.client.request.ContractRequest;
import com.mykey.sdk.entity.client.request.SignRequest;
import com.mykey.sdk.entity.client.request.TransferRequest;
import com.mykey.sdk.entity.client.request.action.ContractAction;
import com.mykey.sdk.entity.client.request.action.TransferAction;
import com.mykey.sdk.entity.client.request.action.data.TransferData;
import com.mykey.sdk.sample.Config;
import com.mykey.sdk.sample.entity.eth.ContractTestEntity;
import com.mykey.sdk.sample.entity.eth.ERC20Entity;

public class MYKEYWalletEthController {
    private static final String TAG = "MYKEYSdk";

    private Activity activity;

    public MYKEYWalletEthController(Activity activity) {
        this.activity = activity;
    }

    public void onAuthorize() {
        AuthorizeRequest authorizeRequest = new AuthorizeRequest()
                .setChain(ChainCons.ETH)
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
                .setChain(ChainCons.ETH)
                .setInfo("Perform the mortgage REX operation")
                // order ID which come from dapp server
                .setOrderId("BH1000001")
                // DApp CallbackUrl
                // param：{"protocol": "", "version": "", "tx_id": "", "ref": "", "account": ""}
                // return: same as SimpleWallet {"code": [0-2], "message": ""}
                .setCallBackUrl(Config.DAPP_CALLBACK_URL);

        ContractAction contractAction = new ContractAction();
        contractAction.setAccount("0x176C715e51F81066C299E162762525c0F42ff178")
                .setName("set")
                .setAbi("[{\"name\":\"set\",\"type\":\"function\",\"inputs\":[{\"type\":\"uint256\",\"name\":\"v\"}]}]")
                .setInfo("info:Execute contract set")
                // JSON string support, eg:setData("{\"v\":\"1234\"}")
                // you can just set binary instead of setData
                // .setBinary("0x60fe47b100000000000000000000000000000000000000000000000000000000000004d2");
                .setData(new ContractTestEntity().setV("1234"));
        contractRequest.addAction(contractAction);

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

    public void onMyKeyContractAndEthTransfer() {
        ContractRequest contractRequest = new ContractRequest()
                .setChain(ChainCons.ETH)
                .setInfo("Perform the mortgage REX operation")
                // order ID which come from dapp server
                .setOrderId("BH1000001")
                // DApp CallbackUrl
                // param：{"protocol": "", "version": "", "tx_id": "", "ref": "", "account": ""}
                // return: same as SimpleWallet {"code": [0-2], "message": ""}
                .setCallBackUrl(Config.DAPP_CALLBACK_URL);

        ContractAction contractActionRequest = new ContractAction();
        contractActionRequest.setAccount("0x176C715e51F81066C299E162762525c0F42ff178")
                .setName("set")
                .setAbi("[{\"name\":\"set\",\"type\":\"function\",\"inputs\":[{\"type\":\"uint256\",\"name\":\"v\"}]}]")
                .setInfo("info:Execute contract and transfer 0.0001 ETH")
                // JSON string support, eg:setData("{\"v\":\"1234\"}")
                // you can just set binary instead of setData
                // .setBinary("0x60fe47b100000000000000000000000000000000000000000000000000000000000004d2");
                .setData(new ContractTestEntity().setV("1234"))
                // if you want to transfer ETH at the same time, you must make sure the contract support.
                .setAmount(0.0001)
                .setSymbol("ETH");
        contractRequest.addAction(contractActionRequest);

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

    public void onMyKeyContractMulti() {
        ContractRequest contractRequest = new ContractRequest()
                .setChain(ChainCons.ETH)
                .setInfo("Perform the mortgage REX operation")
                // order ID which come from dapp server
                .setOrderId("BH1000001")
                // DApp CallbackUrl
                // param：{"protocol": "", "version": "", "tx_id": "", "ref": "", "account": ""}
                // return: same as SimpleWallet {"code": [0-2], "message": ""}
                .setCallBackUrl(Config.DAPP_CALLBACK_URL);

        ContractAction contractAction = new ContractAction();
        contractAction.setAccount("0x084d2D13d6AA6Dfb7F47Dc3c2376839025E022Ee")
                .setName("transfer")
                .setAbi("[{\"constant\":true,\"inputs\":[],\"name\":\"name\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_spender\",\"type\":\"address\"},{\"name\":\"_value\",\"type\":\"uint256\"}],\"name\":\"approve\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"totalSupply\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_value\",\"type\":\"uint256\"}],\"name\":\"transferFrom\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"decimals\",\"outputs\":[{\"name\":\"\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_owner\",\"type\":\"address\"}],\"name\":\"balanceOf\",\"outputs\":[{\"name\":\"balance\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"symbol\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_value\",\"type\":\"uint256\"}],\"name\":\"transfer\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_owner\",\"type\":\"address\"},{\"name\":\"_spender\",\"type\":\"address\"}],\"name\":\"allowance\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"payable\":true,\"stateMutability\":\"payable\",\"type\":\"fallback\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"owner\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"spender\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"Approval\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"from\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"Transfer\",\"type\":\"event\"}]")
                .setInfo("info:Execute contract transfer 1.0000 MTK")
                // JSON string support, eg:setData("{\"_to\":\"0x3BBf0A387a73022A9ebC2491DC4fA8A465C8aAbb\",\"_value\":\"1000000000000000000\"}")
                .setData(new ERC20Entity().setAddress("0x3BBf0A387a73022A9ebC2491DC4fA8A465C8aAbb").setAmount("1000000000000000000"));
        // you can just set binary instead of setData
        // .setBinary("0xa9059cbb0000000000000000000000003bbf0a387a73022a9ebc2491dc4fa8a465c8aabb0000000000000000000000000000000000000000000000000de0b6b3a7640000");
        contractRequest.addAction(contractAction);

        // ETH support multi action, all action will be packed into one transaction
        contractAction = new ContractAction();
        contractAction.setAccount("0x084d2D13d6AA6Dfb7F47Dc3c2376839025E022Ee")
                .setName("transfer")
                .setAbi("[{\"constant\":true,\"inputs\":[],\"name\":\"name\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_spender\",\"type\":\"address\"},{\"name\":\"_value\",\"type\":\"uint256\"}],\"name\":\"approve\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"totalSupply\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_value\",\"type\":\"uint256\"}],\"name\":\"transferFrom\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"decimals\",\"outputs\":[{\"name\":\"\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_owner\",\"type\":\"address\"}],\"name\":\"balanceOf\",\"outputs\":[{\"name\":\"balance\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"symbol\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_value\",\"type\":\"uint256\"}],\"name\":\"transfer\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_owner\",\"type\":\"address\"},{\"name\":\"_spender\",\"type\":\"address\"}],\"name\":\"allowance\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"payable\":true,\"stateMutability\":\"payable\",\"type\":\"fallback\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"owner\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"spender\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"Approval\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"from\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"Transfer\",\"type\":\"event\"}]")
                .setInfo("info:Execute contract transfer 2.0000 MTK")
                // JSON string support, eg:setData("{\"_to\":\"0xc0b09b78c00EBCeD69eD1b397F5FB6AD94938441\",\"_value\":\"2000000000000000000\"}")
                .setData(new ERC20Entity().setAddress("0xc0b09b78c00EBCeD69eD1b397F5FB6AD94938441").setAmount("2000000000000000000"));
        // you can just set binary instead of setData
        // .setBinary("0xa9059cbb0000000000000000000000003bbf0a387a73022a9ebc2491dc4fa8a465c8aabb0000000000000000000000000000000000000000000000000de0b6b3a7640000");
        contractRequest.addAction(contractAction);

        contractAction = new ContractAction();
        contractAction.setAccount("0x176C715e51F81066C299E162762525c0F42ff178")
                .setName("set")
                .setAbi("[{\"name\":\"set\",\"type\":\"function\",\"inputs\":[{\"type\":\"uint256\",\"name\":\"v\"}]}]")
                .setInfo("info:Execute contract and transfer 0.0001 ETH")
                // JSON string support, eg:setData("{\"v\":\"1234\"}")
                // you can just set binary instead of setData
                // .setBinary("0x60fe47b100000000000000000000000000000000000000000000000000000000000004d2");
                .setData(new ContractTestEntity().setV("1234"))
                // if you want to transfer ETH at the same time, you must make sure the contract support.
                .setAmount(0.0001)
                .setSymbol("ETH");
        contractRequest.addAction(contractAction);

        TransferAction transferAction = new TransferAction();
        transferAction.setAccount("ETH")
                .setName("transfer")
                .setInfo("info:transfer to 0x3BBf0A387a73022A9ebC2491DC4fA8A465C8aAbb")
                .setData(new TransferData().setFrom("0x46B075a13D2f280A01F87CB6ecAF8a6C12286f12").setTo("0x3BBf0A387a73022A9ebC2491DC4fA8A465C8aAbb").setQuantity("0.0001 ETH").setMemo(""));
        contractRequest.addAction(transferAction);

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

    public void onMyKeyTransferEth() {
        TransferRequest transferRequest = new TransferRequest()
                .setChain(ChainCons.ETH)
                .setFrom("0x46B075a13D2f280A01F87CB6ecAF8a6C12286f12")
                .setTo("0x3BBf0A387a73022A9ebC2491DC4fA8A465C8aAbb")
                .setAmount(0.0001)
                .setMemo("0x12")
                .setContractName("ETH")
                .setSymbol("ETH")
                .setInfo("info:transfer to 0x3BBf0A387a73022A9ebC2491DC4fA8A465C8aAbb")
                .setDecimal(18)
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

    public void onMyKeyTransferErc20() {
        TransferRequest transferRequest = new TransferRequest()
                .setChain(ChainCons.ETH)
                .setFrom("0x46B075a13D2f280A01F87CB6ecAF8a6C12286f12")
                .setTo("0x3BBf0A387a73022A9ebC2491DC4fA8A465C8aAbb")
                .setAmount(0.0001)
                .setContractName("0x084d2D13d6AA6Dfb7F47Dc3c2376839025E022Ee")
                .setSymbol("MTK")
                .setInfo("info:transfer to 0x3BBf0A387a73022A9ebC2491DC4fA8A465C8aAbb")
                .setDecimal(18)
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
        SignRequest signRequest = new SignRequest().setMessage("0x12345678")
                .setChain(ChainCons.ETH)
                // DApp CallbackUr
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
