package com.mykey.sdk.sample.entity.eos;

/**
 * Created by zero on 2019/6/5.
 */

public class StakeEntity {
    private String owner;
    private String quantity;

    public String getOwner() {
        return owner;
    }

    public StakeEntity setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public StakeEntity setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }
}
