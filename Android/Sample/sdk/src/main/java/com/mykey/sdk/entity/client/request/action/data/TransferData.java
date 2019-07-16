package com.mykey.sdk.entity.client.request.action.data;

/**
 * Created by zero on 2019/6/3.
 */

public class TransferData {
    private String from;
    private String to;
    private String quantity;
    private String memo;

    public String getFrom() {
        return from;
    }

    public TransferData setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public TransferData setTo(String to) {
        this.to = to;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public TransferData setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public TransferData setMemo(String memo) {
        this.memo = memo;
        return this;
    }
}
