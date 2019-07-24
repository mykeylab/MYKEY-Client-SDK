package com.mykey.sdk.common.util;

import android.content.Context;
import android.widget.Toast;

import com.mykey.sdk.common.manager.HandlerManager;

/**
 * Created by zero on 2019/7/12.
 */

public class ToastUtil {
    public static void toast(final Context context, final String msg) {
        HandlerManager.getInstance().postMain(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
