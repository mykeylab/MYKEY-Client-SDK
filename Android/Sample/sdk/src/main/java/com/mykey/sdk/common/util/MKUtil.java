package com.mykey.sdk.common.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.alibaba.fastjson.JSONObject;
import com.mykey.sdk.common.constants.ConfigCons;

import java.net.URLEncoder;

/**
 * Created by zero on 2019/6/20.
 */

public class MKUtil {
    public static void redirectToMYKEYForSimpleWallet(Activity activity, String param) {
        try {
            param = URLEncoder.encode(param, "utf-8");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(ConfigCons.SIMPLE_WALLET_URL_FORMAT, param)));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void redirectToApp(Activity activity, String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object parseToJSONObject(Object obj) {
        if (null == obj) {
            return obj;
        }
        if (!(obj instanceof String)) {
            return obj;
        }
        JSONObject jsonObject = JsonUtil.toJSONObject(obj.toString());
        if (null != jsonObject) {
            return jsonObject;
        }
        return obj;
    }
}
