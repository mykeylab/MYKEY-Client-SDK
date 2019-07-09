package com.mykey.sdk.plugin;

import com.mykey.sdk.MYKEYSdk;
import com.mykey.sdk.common.constants.ErrorCons;
import com.mykey.sdk.common.iface.ApiCallback;
import com.mykey.sdk.common.manager.HandlerManager;
import com.mykey.sdk.common.util.JsonUtil;
import com.mykey.sdk.entity.client.response.PayloadResponse;
import com.mykey.sdk.jni.MYKEYWalletJni;

import mykeycore.ApiRequestCallback;


/**
 * Created by zero on 2019/5/27.
 */

public class StakeTokenPlugin {
    private final static StakeTokenPlugin BIHU_PLUGIN = new StakeTokenPlugin();

    private StakeTokenPlugin() {
    }

    public static StakeTokenPlugin getInstance() {
        return BIHU_PLUGIN;
    }

    public void getBalance(final String chain, final String code, final String symbol, final ApiCallback apiCallBack) {
        if (!checkInit(apiCallBack)) {
            return;
        }
        HandlerManager.getInstance().postSlow(new Runnable() {
            @Override
            public void run() {
                MYKEYWalletJni.getBalance(chain, code, symbol, getTransitCallback(apiCallBack));
            }
        });
    }

    public void getBindInfo(final ApiCallback apiCallBack) {
        if (!checkInit(apiCallBack)) {
            return;
        }
        HandlerManager.getInstance().postSlow(new Runnable() {
            @Override
            public void run() {
                MYKEYWalletJni.getBindInfo(getTransitCallback(apiCallBack));
            }
        });
    }

    public void getUnlockList(final String chain, final String code, final String symbol, final ApiCallback apiCallBack) {
        if (!checkInit(apiCallBack)) {
            return;
        }
        HandlerManager.getInstance().postSlow(new Runnable() {
            @Override
            public void run() {
                MYKEYWalletJni.getUnlockList(chain, code, symbol, getTransitCallback(apiCallBack));
            }
        });
    }

    private boolean checkInit(final ApiCallback callback) {
        if (null == MYKEYSdk.getInstance().getContext()) {
            callback.onError(JsonUtil.toJson(new PayloadResponse(ErrorCons.ERROR_CODE_NO_INIT)));
            return false;
        }
        return true;
    }

    private ApiRequestCallback getTransitCallback(final ApiCallback clientCallback) {
        return new ApiRequestCallback() {
            @Override
            public void onError(final long errorCode, final String errorMsg) {
                HandlerManager.getInstance().postMain(new Runnable() {
                    @Override
                    public void run() {
                        clientCallback.onError(JsonUtil.toJson(new PayloadResponse(errorCode, errorMsg)));
                    }
                });
            }

            @Override
            public void onSuccess(final String data) {
                HandlerManager.getInstance().postMain(new Runnable() {
                    @Override
                    public void run() {
                        clientCallback.onSuccess(data);
                    }
                });
            }
        };
    }

}
