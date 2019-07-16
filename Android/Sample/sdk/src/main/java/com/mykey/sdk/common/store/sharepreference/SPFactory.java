package com.mykey.sdk.common.store.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.mykey.sdk.common.constants.StoreKeyCons;

import java.util.Map;

/**
 * shared preferences
 * Created by zero on 2019/5/27.
 */

public class SPFactory {
    private static String userId;

    public static void setUserId(String userId) {
        SPFactory.userId = userId;
    }

    public static SharedPreferences getUserSP(Context context) {
        SharedPreferences userSP = getSharedPreferences(context, StoreKeyCons.SP_DB_USER);
        Map<String, ?> dataMap = userSP.getAll();
        if (dataMap.size() == 0) {
            userSP = getSharedPreferences(context, userId + StoreKeyCons.SP_DB_USER);
        }
        return userSP;
    }

    public static SharedPreferences getConfigSP(Context context) {
        SharedPreferences configSP = getSharedPreferences(context, StoreKeyCons.SP_DB_CONFIG);
        Map<String, ?> dataMap = configSP.getAll();
        if (dataMap.size() == 0) {
            configSP = getSharedPreferences(context, userId + StoreKeyCons.SP_DB_CONFIG);
        }
        return configSP;
    }

    public static SharedPreferences getSharedPreferences(Context context, String dbName) {
        if (null == context || context.getApplicationContext() == null) {
            return null;
        }
        return context.getApplicationContext().getSharedPreferences(dbName, Context.MODE_PRIVATE);
    }

    public static void clearAll(Context context) {
        clear(getUserSP(context));
        clear(getConfigSP(context));
    }

    public static void clearCore(Context context) {
        clear(getUserSP(context));
    }

    private static void clear(SharedPreferences sp) {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }
}
