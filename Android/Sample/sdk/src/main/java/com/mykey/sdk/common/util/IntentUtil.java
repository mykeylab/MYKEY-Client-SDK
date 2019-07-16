package com.mykey.sdk.common.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by zero on 2019/6/12.
 */

public class IntentUtil {
    public static void jumpByUrl(Context context, String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
