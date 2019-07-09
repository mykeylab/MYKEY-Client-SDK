package com.mykey.sdk.sample;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.mykey.sdk.common.util.LogUtil;

/**
 * Callback page for SimpleWallet
 * Created by zero on 2019/6/5.
 */

public class SimpleWalletCallbackActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_wallet_callback);
        Uri uri = getIntent().getData();
        if (null == uri) {
            LogUtil.e("MYKEYSdk", "data is null.");
            return;
        }
        LogUtil.e("MYKEYSdk", "action:" + uri.getQueryParameter("action"));
        LogUtil.e("MYKEYSdk", "result:" + uri.getQueryParameter("result"));
        String action = uri.getQueryParameter("action");
        String result = uri.getQueryParameter("result");
        String sign = uri.getQueryParameter("sign");
        String txId = uri.getQueryParameter("txId");
        ((TextView) findViewById(R.id.result)).setText("action:" + action + ";\nresult:" + result + ";\nsign:" + sign + ";\ntxId:" + txId);
    }
}
