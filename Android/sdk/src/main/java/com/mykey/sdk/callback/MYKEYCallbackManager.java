package com.mykey.sdk.callback;

import com.mykey.sdk.common.constants.ErrorCons;
import com.mykey.sdk.common.manager.HandlerManager;
import com.mykey.sdk.common.util.JsonUtil;
import com.mykey.sdk.common.util.LogUtil;
import com.mykey.sdk.common.util.MD5Util;
import com.mykey.sdk.entity.client.response.ErrorResponse;
import com.mykey.sdk.entity.client.response.PayloadResponse;
import com.mykey.sdk.entity.client.response.RootResponse;

import java.util.HashMap;

/**
 * Created by zero on 2019/5/27.
 */

public class MYKEYCallbackManager {
    private final static String TAG = "MYKEYCallbackManager";
    private final static MYKEYCallbackManager MYKEY_LISTENER_MANAGER = new MYKEYCallbackManager();

    private HashMap<String, MYKEYWalletCallback> listenerHashMap = new HashMap<>();

    private MYKEYCallbackManager() {
    }

    public static MYKEYCallbackManager getInstance() {
        return MYKEY_LISTENER_MANAGER;
    }

    public void registerListener(MYKEYWalletCallback MYKEYWalletCallback) {
        listenerHashMap.put(getCallBackId(MYKEYWalletCallback), MYKEYWalletCallback);
    }

    public void dispatch(final RootResponse rootResponse) {
        final MYKEYWalletCallback callback = listenerHashMap.get(rootResponse.getCallBackId());
        if (null == callback) {
            LogUtil.e(TAG, "in dispatch can not find callback");
            return;
        }
        // find callback by callbackId
        HandlerManager.getInstance().postMain(new Runnable() {
            @Override
            public void run() {
                handleListener(callback, rootResponse);
            }
        });
        listenerHashMap.remove(rootResponse.getCallBackId());
    }

    public String getCallBackId(MYKEYWalletCallback MYKEYWalletCallback) {
        return MD5Util.getMD5String(MYKEYWalletCallback.toString());
    }

    private void handleListener(MYKEYWalletCallback MYKEYWalletCallback, RootResponse rootResponse) {
        if (ErrorCons.ERROR_CODE_OK == rootResponse.getErrorCode()) {
            // request normal
            MYKEYWalletCallback.onSuccess(rootResponse.getData());
            return;
        }
        if (ErrorCons.ERROR_CODE_CANCEL == rootResponse.getErrorCode()) {
            // request cancelled
            MYKEYWalletCallback.onCancel();
            return;
        }
        MYKEYWalletCallback.onError(getErrorResponse(rootResponse));
    }

    private String getErrorResponse(RootResponse rootResponse) {
        ErrorResponse errorResponse = JsonUtil.fastjsonParse(rootResponse.getData(), ErrorResponse.class);
        PayloadResponse payloadResponse = new PayloadResponse(rootResponse.getErrorCode());
        if (null != errorResponse) {
            payloadResponse.setErrorMsg(errorResponse.getErrorMsg());
        }
        return JsonUtil.toJson(payloadResponse);
    }

}
