package com.mykey.sdk.entity.client.request;

/**
 * Created by zero on 2019/5/27.
 */

public class TransferRequest extends BaseRequest {
    private String from;
    private String to;
    private double amount;
    private String contractName;
    private String symbol;
    private String memo;
    private int decimal;
    private String orderId;

    public String getFrom() {
        return from;
    }

    public TransferRequest setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public TransferRequest setTo(String to) {
        this.to = to;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public TransferRequest setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public String getContractName() {
        return contractName;
    }

    public TransferRequest setContractName(String contractName) {
        this.contractName = contractName;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public TransferRequest setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public TransferRequest setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    public int getDecimal() {
        return decimal;
    }

    public TransferRequest setDecimal(int decimal) {
        this.decimal = decimal;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public TransferRequest setInfo(String info) {
        this.info = info;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public TransferRequest setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getCallBackUrl() {
        return callbackUrl;
    }

    public TransferRequest setCallBackUrl(String callBackUrl) {
        this.callbackUrl = callBackUrl;
        return this;
    }
}
