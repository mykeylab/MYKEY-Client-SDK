package com.mykey.sdk.connect.scheme.callback;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.mykey.sdk.common.constants.IntentKeyCons;
import com.mykey.sdk.common.util.LogUtil;
import com.mykey.sdk.common.util.StringUtil;

/**
 * Created by zero on 2019/5/27.
 */

public class MYKEYCallbackActivity extends Activity {

    private static final String TAG = "MYKEYCallbackActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseIntent();
        finish();
    }

    private void parseIntent() {
        Uri uri = getIntent().getData();
        if (null == uri) {
            LogUtil.e(TAG, "in MYKEYCallbackActivity uri is empty.");
            MYKEYCallbackManager.getInstance().clearCache();
            return;
        }
        String callbackId = uri.getQueryParameter(IntentKeyCons.INTENT_KEY_CALLBACK_ID);
        if (TextUtils.isEmpty(callbackId)) {
            LogUtil.e(TAG, "in MYKEYCallbackActivity callbackId is empty.");
            MYKEYCallbackManager.getInstance().clearCache();
            return;
        }
        int result = StringUtil.toInt(uri.getQueryParameter(IntentKeyCons.INTENT_KEY_RESULT));
//        String txId = uri.getQueryParameter(IntentKeyCons.INTENT_KEY_TX_ID);
        String payload = uri.getQueryParameter(IntentKeyCons.INTENT_KEY_PAYLOAD);
        MYKEYCallbackManager.getInstance().dispatch(MYKEYCallbackResponseFactory.getResultResponse(callbackId, result, payload));
    }
}
