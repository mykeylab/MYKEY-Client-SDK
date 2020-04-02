package com.mykey.sdk.entity.agreement.request;

/**
 * Created by zero on 2019/6/3.
 */

public class TransferProtocolRequest extends BaseProtocolRequest {
    private String from;
    private String to;
    private double amount;
    // contract name of account
    private String contract;
    // name of token
    private String symbol;
    // decimal of token
    private int precision;
    // memo
    private String dappData;
    // description for transaction, will show on wallet before user enter password.
    private String desc;

    private String orderId;
    private String transferUrl;

    public String getTransferUrl() {
        return transferUrl;
    }

    public void setTransferUrl(String transferUrl) {
        this.transferUrl = transferUrl;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public String getDappData() {
        return dappData;
    }

    public void setDappData(String dappData) {
        this.dappData = dappData;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
