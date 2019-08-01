package com.mykey.sdk.connect.scheme.callback;

import com.mykey.sdk.common.constants.ErrorCons;
import com.mykey.sdk.common.manager.HandlerManager;
import com.mykey.sdk.common.util.JsonUtil;
import com.mykey.sdk.common.util.LogUtil;
import com.mykey.sdk.common.util.MD5Util;
import com.mykey.sdk.connect.service.FreePromptManager;
import com.mykey.sdk.entity.client.response.ErrorResponse;
import com.mykey.sdk.entity.client.response.FreePromptResponse;
import com.mykey.sdk.entity.client.response.PayloadResponse;
import com.mykey.sdk.entity.client.response.RootResponse;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zero on 2019/5/27.
 */

public class MYKEYCallbackManager {
    private static final String TAG = "MYKEYCallbackManager";
    private static final MYKEYCallbackManager MYKEY_LISTENER_MANAGER = new MYKEYCallbackManager();

    private ConcurrentHashMap<String, MYKEYWalletCallback> listenerHashMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> paramMap = new ConcurrentHashMap<>();

    private MYKEYCallbackManager() {
    }

    public static MYKEYCallbackManager getInstance() {
        return MYKEY_LISTENER_MANAGER;
    }

    public void registerListener(MYKEYWalletCallback mykeyWalletCallback) {
        listenerHashMap.put(getCallBackId(mykeyWalletCallback), mykeyWalletCallback);
    }

    public void registerServiceParam(MYKEYWalletCallback mykeyWalletCallback, String param) {
        paramMap.put(getCallBackId(mykeyWalletCallback), param);
    }

    public void unregisterServiceParam(String callbackId) {
        paramMap.remove(callbackId);
    }

    public void dispatch(final RootResponse rootResponse) {
        final MYKEYWalletCallback callback = listenerHashMap.get(rootResponse.getCallBackId());
        if (null == callback) {
            LogUtil.e(TAG, "in dispatch can not find callback");
            clearCache();
            return;
        }
        // find callback by callbackId
        HandlerManager.getInstance().postMain(new Runnable() {
            @Override
            public void run() {
                handleListener(callback, rootResponse);
            }
        });
        clearCache();
    }

    public String getCallBackId(MYKEYWalletCallback mykeyWalletCallback) {
        return MD5Util.getMD5String(mykeyWalletCallback.toString());
    }

    public String getParam(String callbackId) {
        return paramMap.get(callbackId);
    }

    public MYKEYWalletCallback getMYKEYCallback(String callbackId) {
        return listenerHashMap.get(callbackId);
    }

    public void clearCache() {
        listenerHashMap.clear();
        paramMap.clear();
    }

    private void handleListener(MYKEYWalletCallback mykeyWalletCallback, RootResponse rootResponse) {
        if (ErrorCons.ERROR_CODE_OK == rootResponse.getErrorCode()) {
            // request normal
            mykeyWalletCallback.onSuccess(rootResponse.getData());
            // record free prompt
            recordFreePrompt(rootResponse.getData());
            return;
        }
        if (ErrorCons.ERROR_CODE_CANCEL == rootResponse.getErrorCode()) {
            // request cancelled
            mykeyWalletCallback.onCancel();
            return;
        }
        mykeyWalletCallback.onError(getErrorResponse(rootResponse));
    }

    private String getErrorResponse(RootResponse rootResponse) {
        ErrorResponse errorResponse = JsonUtil.fastjsonParse(rootResponse.getData(), ErrorResponse.class);
        PayloadResponse payloadResponse = new PayloadResponse(rootResponse.getErrorCode());
        if (null != errorResponse) {
            payloadResponse.setErrorMsg(errorResponse.getErrorMsg());
        }
        return JsonUtil.toJson(payloadResponse);
    }

    private void recordFreePrompt(String payLoadData) {
        FreePromptResponse freePromptResponse = JsonUtil.fastjsonParse(payLoadData, FreePromptResponse.class);
        if (null == freePromptResponse || null == freePromptResponse.getFreePromptKeyList() || freePromptResponse.getFreePromptKeyList().size() == 0) {
            return;
        }
        FreePromptManager.addFreePrompt(freePromptResponse.getFreePromptKeyList());
    }

}
