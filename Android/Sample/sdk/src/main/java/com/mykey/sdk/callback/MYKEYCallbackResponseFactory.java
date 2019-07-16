package com.mykey.sdk.callback;

import com.mykey.sdk.common.util.JsonUtil;
import com.mykey.sdk.entity.client.response.PayloadResponse;
import com.mykey.sdk.entity.client.response.RootResponse;

/**
 * Created by zero on 2019/5/30.
 */

public class MYKEYCallbackResponseFactory {
    public static RootResponse getErrorResponse(int errorCode, String errorMsg, MYKEYWalletCallback MYKEYWalletCallback) {
        RootResponse rootResponse = new RootResponse();
        rootResponse.setErrorCode(errorCode);
        rootResponse.setData(JsonUtil.toJson(new PayloadResponse(errorCode, errorMsg)));
        rootResponse.setCallBackId(MYKEYCallbackManager.getInstance().getCallBackId(MYKEYWalletCallback));
        return rootResponse;
    }

    public static RootResponse getResultResponse(String callBackId, int result, String payload) {
        RootResponse rootResponse = new RootResponse();
        rootResponse.setErrorCode(result);
        rootResponse.setCallBackId(callBackId);
        rootResponse.setData(payload);
        return rootResponse;
    }
}
