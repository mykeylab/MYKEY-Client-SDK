package com.mykey.sdk.sample.controller;

import android.app.Activity;

import com.mykey.sdk.common.util.JsonUtil;
import com.mykey.sdk.common.util.MKUtil;
import com.mykey.sdk.entity.agreement.request.AuthorizeProtocolRequest;
import com.mykey.sdk.entity.agreement.request.ContractProtocolRequest;
import com.mykey.sdk.entity.agreement.request.SignProtocolRequest;
import com.mykey.sdk.entity.agreement.request.TransferProtocolRequest;
import com.mykey.sdk.entity.client.request.action.BaseAction;
import com.mykey.sdk.entity.client.request.action.ContractAction;
import com.mykey.sdk.entity.client.request.action.TransferAction;
import com.mykey.sdk.entity.client.request.action.data.TransferData;
import com.mykey.sdk.sample.Config;
import com.mykey.sdk.sample.entity.BuyRamDataEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero on 2019/6/5.
 */

public class SimpleWalletController {

    private Activity activity;

    public SimpleWalletController(Activity activity) {
        this.activity = activity;
    }

    public void onkAuthorize() {
        AuthorizeProtocolRequest authorizeRequest = new AuthorizeProtocolRequest();
        authorizeRequest.setProtocol("SimpleWallet");
        authorizeRequest.setVersion("1.0");
        authorizeRequest.setDappName("BiHu");
        authorizeRequest.setDappIcon("https://bihu.com/favicon.ico");
        authorizeRequest.setAction("login");
        authorizeRequest.setUuID("dhsajkhdasjkhdklsah");
        authorizeRequest.setLoginUrl(Config.DAPP_CALLBACK_URL);
        authorizeRequest.setLoginMemo("remark info");
        // DApp CallbackUrl
        // param：{"protocol": "", "version": "", "dapp_key": "", "uuID": "", "public_key": "", "sign": "", "ref": "", "timestamp": "", "account": ""}
        authorizeRequest.setCallback("simple://con.mykey.simplewallet?action=login");

        // uri eg:simplewallet://eos.io?param=%7b%22action%22%3a%22login%22%2c%22callback%22%3a%22%22%2c%22dappIcon%22%3a%22https%3a%2f%2fbihu.com%2ffavicon.ico%22%2c%22dappName%22%3a%22%e5%b8%81%e4%b9%8e%22%2c%22loginMemo%22%3a%22%e5%a4%87%e6%b3%a8%e4%bf%a1%e6%81%af%22%2c%22loginUrl%22%3a%22http%3a%2f%2f172.16.11.168%3a8086%2fapi%2flogin%2fcallback%22%2c%22protocol%22%3a%22SimpleWallet%22%2c%22uuID%22%3a%22dhsajkhdasjkhdklsah%22%2c%22version%22%3a%221.0%22%7d
        MKUtil.redirectToMYKEYForSimpleWallet(activity, JsonUtil.toJson(authorizeRequest));
    }

    public void onSimpleWalletContract() {
        ContractProtocolRequest contractRequest = new ContractProtocolRequest();
        contractRequest.setProtocol("SimpleWallet");
        contractRequest.setVersion("1.0");
        contractRequest.setAction("transaction");
        contractRequest.setDappName("BiHu");
        contractRequest.setDappIcon("https://bihu.com/favicon.ico");
        contractRequest.setDesc("Perform the REX mortgage operation");
        contractRequest.setCallback("simple://con.mykey.simplewallet?action=transaction");
        // DApp CallbackUrl
        // param：{"protocol": "", "version": "", "tx_id": "", "ref": "", "account": ""}
        // return: same as SimpleWallet transfer {"code": [0-2], "message": ""}
        contractRequest.setContractUrl(Config.DAPP_CALLBACK_URL);

        List<BaseAction> actionList = new ArrayList<>();
        ContractAction contractActionRequest = new ContractAction();
        contractActionRequest.setAccount("eosio")
                .setName("buyram")
                .setInfo("buy ram")
                .setData(new BuyRamDataEntity().setPayer("bobbobbobbob").setReceiver("alicealice11").setQuant("1.0000 EOS"));
        actionList.add(contractActionRequest);

        TransferAction transferActionRequest = new TransferAction();
        transferActionRequest.setAccount("eosio.token")
                .setName("transfer")
                .setInfo("transfer to alicealice11")
                .setData(new TransferData().setFrom("bobbobbobbob").setTo("alicealice11").setQuantity("0.0001 EOS").setMemo("memo"));
        actionList.add(transferActionRequest);
        contractRequest.setActions(actionList);

        // uri eg:simplewallet://eos.io?param=%7b%22action%22%3a%22transaction%22%2c%22actions%22%3a%5b%7b%22account%22%3a%22eosio%22%2c%22data%22%3a%7b%22payer%22%3a%22kddkkdskkkss%22%2c%22quant%22%3a%221.0000+EOS%22%2c%22receiver%22%3a%22kddkkdskkkss%22%7d%2c%22info%22%3a%22%e8%b4%ad%e4%b9%b0ram%22%2c%22name%22%3a%22buyram%22%7d%2c%7b%22account%22%3a%22eosio.token%22%2c%22data%22%3a%7b%22from%22%3a%22kddkkdskkkss%22%2c%22memo%22%3a%22%e6%b5%8b%e8%af%95%e6%b5%8b%e8%af%95%22%2c%22quantity%22%3a%220.0001+EOS%22%2c%22to%22%3a%22zerochlworld%22%7d%2c%22info%22%3a%22%e8%bd%ac%e8%b4%a6%e5%88%b0%e6%9f%90%e6%9f%90%e6%9f%90%22%2c%22name%22%3a%22transfer%22%7d%5d%2c%22callback%22%3a%22%22%2c%22dappIcon%22%3a%22https%3a%2f%2fbihu.com%2ffavicon.ico%22%2c%22dappName%22%3a%22%e5%b8%81%e4%b9%8e%22%2c%22desc%22%3a%22%e5%90%88%e7%ba%a6%e6%96%b9%e6%b3%95%e6%80%bb%e6%8f%8f%e8%bf%b0%22%2c%22protocol%22%3a%22SimpleWallet%22%2c%22version%22%3a%221.0%22%7d
        MKUtil.redirectToMYKEYForSimpleWallet(activity, JsonUtil.toJson(contractRequest));
    }

    public void onSimpleWalletTransfer() {
        TransferProtocolRequest transferRequest = new TransferProtocolRequest();
        transferRequest.setProtocol("SimpleWallet");
        transferRequest.setVersion("1.0");
        transferRequest.setAction("transfer");
        transferRequest.setDappName("BiHu");
        transferRequest.setDappIcon("https://bihu.com/favicon.ico");
        transferRequest.setFrom("bobbobbobbob");
        transferRequest.setTo("alicealice11");
        transferRequest.setAmount(1.0000);
        transferRequest.setContract("eosio.token");
        transferRequest.setSymbol("EOS");
        transferRequest.setPrecision(4);
        transferRequest.setDappData("memo");
        transferRequest.setDesc("transfer ro alicealice11");
        transferRequest.setCallback("simple://con.mykey.simplewallet?action=transfer");
        transferRequest.setTransferUrl(Config.DAPP_CALLBACK_URL);

        // uri eg:simplewallet://eos.io?param=%7b%22action%22%3a%22transfer%22%2c%22amount%22%3a1%2c%22callback%22%3a%22%22%2c%22contract%22%3a%22eosio.token%22%2c%22dappData%22%3a%22memo%22%2c%22dappIcon%22%3a%22https%3a%2f%2fbihu.com%2ffavicon.ico%22%2c%22dappName%22%3a%22%e5%b8%81%e4%b9%8e%22%2c%22desc%22%3a%22%e6%8f%8f%e8%bf%b0%e4%bf%a1%e6%81%af%22%2c%22from%22%3a%22kddkkdskkkss%22%2c%22precision%22%3a4%2c%22protocol%22%3a%22SimpleWallet%22%2c%22symbol%22%3a%22EOS%22%2c%22to%22%3a%22zerochlworld%22%2c%22version%22%3a%221.0%22%7d
        MKUtil.redirectToMYKEYForSimpleWallet(activity, JsonUtil.toJson(transferRequest));
    }

    public void onSimpleWalletSign() {
        SignProtocolRequest signRequest = new SignProtocolRequest();
        signRequest.setProtocol("SimpleWallet");
        signRequest.setVersion("1.0");
        signRequest.setAction("sign");
        signRequest.setDappName("BiHu");
        signRequest.setDappIcon("https://bihu.com/favicon.ico");
        signRequest.setCallback("simple://con.mykey.simplewallet?action=sign");
        signRequest.setMessage("Messages that need to be signed, [it could be random which come from dapp server]");
        // DApp CallbackUrl
        // param：{"protocol": "", "version": "", "message": "", "sign": "", "ref": "", "account": ""}
        // return: same as SimpleWallet transfer {"code": [0-2], "message": ""}
        signRequest.setSignUrl(Config.DAPP_CALLBACK_URL);

        // uri eg:simplewallet://eos.io?param=%7b%22action%22%3a%22sign%22%2c%22callback%22%3a%22%22%2c%22dappIcon%22%3a%22https%3a%2f%2fbihu.com%2ffavicon.ico%22%2c%22dappName%22%3a%22%e5%b8%81%e4%b9%8e%22%2c%22message%22%3a%22%e4%bd%a0%e5%a5%bd%e4%bd%a0%e5%a5%bd%ef%bc%8c%e8%bf%99%e9%87%8c%e9%9c%80%e8%a6%81%e7%ad%be%e5%90%8d%22%2c%22protocol%22%3a%22SimpleWallet%22%2c%22signUrl%22%3a%22http%3a%2f%2f172.16.11.168%3a8086%2fapi%2flogin%2fcallback%22%2c%22version%22%3a%221.0%22%7d
        MKUtil.redirectToMYKEYForSimpleWallet(activity, JsonUtil.toJson(signRequest));
    }

}
