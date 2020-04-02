package com.mykey.sdk.entity.client.request;

import com.mykey.sdk.common.constants.ChainCons;

/**
 * Created by zero on 2019/6/3.
 */

public abstract class BaseRequest {
    protected String callbackUrl;
    protected String info;
    protected String chain = ChainCons.DEFAULT_CHAIN;
}
