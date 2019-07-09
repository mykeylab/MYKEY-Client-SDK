package com.mykey.sdk.jni;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.mykey.sdk.common.constants.ErrorCons;
import com.mykey.sdk.common.util.LogUtil;
import com.mykey.sdk.jni.entity.response.BaseJniResponse;
import com.mykey.sdk.jni.entity.response.EmptyResponse;
import com.mykey.sdk.jni.entity.response.EncodeParamResponse;
import com.mykey.sdk.jni.entity.response.RequestPubKeyResponse;

import mykeycore.ApiRequestCallback;
import mykeycore.InitEntity;
import mykeycore.Mykeycore;

/**
 * Created by zero on 2019/5/24.
 */

public class MYKEYWalletJni {
    private static final String TAG = "MYKEYWalletJni";
//    private static final Gson gson = new Gson();

    public static void init(InitEntity initEntity) {
        EmptyResponse encodeEntity = getResultData(Mykeycore.init(initEntity), new TypeReference<BaseJniResponse<EmptyResponse>>() {
        });
        if (null == encodeEntity) {
            return;
        }
    }

    public static String encodeParam(String param) {
        EncodeParamResponse encodeEntity = getResultData(Mykeycore.encodeParam(param), new TypeReference<BaseJniResponse<EncodeParamResponse>>() {
        });
        if (null == encodeEntity) {
            return "";
        }
        return encodeEntity.getEncodeData();
    }

    public static String getRequestPubKey() {
        RequestPubKeyResponse pubKeyResponse = getResultData(Mykeycore.requestPubKey(), new TypeReference<BaseJniResponse<RequestPubKeyResponse>>() {
        });
        if (null == pubKeyResponse) {
            return "";
        }
        return pubKeyResponse.getRequestPubKey();
    }

    public static void getBalance(String chain, String code, String symbol, ApiRequestCallback apiRequest) {
        getResultData(Mykeycore.getBalance(chain, code, symbol, apiRequest), new TypeReference<BaseJniResponse<EmptyResponse>>() {
        });
    }

    public static void getBindInfo(ApiRequestCallback apiRequest) {
        getResultData(Mykeycore.getBindInfo(apiRequest), new TypeReference<BaseJniResponse<EmptyResponse>>() {
        });
    }

    public static void getUnlockList(String chain, String code, String symbol, ApiRequestCallback apiRequest) {
        getResultData(Mykeycore.getUnlockList(chain, code, symbol, apiRequest), new TypeReference<BaseJniResponse<EmptyResponse>>() {
        });
    }

//    private static <T> T getResultData(String responseJson, TypeToken typeToken) {
//        BaseJniResponse<T> responseEntity = gson.fromJson(responseJson, typeToken.getType());
//        // handle exception
//        handleErrorCode(responseEntity);
//        return responseEntity.getResultData();
//    }

    private static <T> T getResultData(String responseJson, TypeReference<BaseJniResponse<T>> typeReference) {
        BaseJniResponse<T> responseEntity = JSONObject.parseObject(responseJson, typeReference);
        // handle exception
        handleErrorCode(responseEntity);
        return responseEntity.getResultData();
    }

    private static void handleErrorCode(BaseJniResponse responseEntity) {
        switch (responseEntity.getResultCode()) {
            case ErrorCons.ERROR_CODE_OK:
                // do nothing
                break;
            default:
//                ToastTool.toast(responseEntity.getResultMessage());
//                ToastTool.toast(MyKeyApplication.getMainApplication().getResources().getString(R.string.error_pelease_repeat_after));
                LogUtil.e(TAG, responseEntity.getResultMessage());
                break;
        }
    }

}
