package com.mykey.sdk.entity.agreement.request;

/**
 * Created by zero on 2019/6/3.
 */

public class TransferProtocolRequest extends BaseProtocolRequest {
    // 付款人的EOS账号，transfer必须
    private String from;
    // 收款人的EOS账号，transfer必须
    private String to;
    // 转账数量，transfer必须
    private double amount;
    // 转账的token所属的contract账号名
    private String contract;
    // 转账的token名称，transfer必须
    private String symbol;
    // 转账的token的精度，小数点后面的位数，transfer必须
    private int precision;
    // 由dapp生成的业务参数信息，需要钱包在转账时附加在memo中发出去，格式为:k1=v1&k2=v2，可选;钱包转账时还可附加ref参数标明来源，如：k1=v1&k2=v2&ref=walletname
    private String dappData;
    // 交易的说明信息，钱包在付款UI展示给用户，最长不要超过128个字节，可选
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
