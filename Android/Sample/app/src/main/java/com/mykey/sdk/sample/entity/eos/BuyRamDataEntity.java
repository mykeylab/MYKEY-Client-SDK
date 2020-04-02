package com.mykey.sdk.sample.entity.eos;

/**
 * Created by zero on 2019/6/3.
 */

public class BuyRamDataEntity {
    private String payer;
    private String receiver;
    private String quant;

    public String getPayer() {
        return payer;
    }

    public BuyRamDataEntity setPayer(String payer) {
        this.payer = payer;
        return this;
    }

    public String getReceiver() {
        return receiver;
    }

    public BuyRamDataEntity setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public String getQuant() {
        return quant;
    }

    public BuyRamDataEntity setQuant(String quant) {
        this.quant = quant;
        return this;
    }
}
