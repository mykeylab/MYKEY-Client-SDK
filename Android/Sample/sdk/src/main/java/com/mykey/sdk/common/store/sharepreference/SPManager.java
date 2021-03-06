package com.mykey.sdk.common.store.sharepreference;

import android.content.SharedPreferences;

import com.mykey.sdk.MYKEYSdk;
import com.mykey.sdk.common.constants.StoreKeyCons;

/**
 * Created by zero on 2019/5/27.
 */

public class SPManager {

    private static void put(String key, String value, SharedPreferences.Editor editor) {
        editor.putString(key, value);
        editor.apply();
    }

    private static void put(String key, int value, SharedPreferences.Editor editor) {
        editor.putInt(key, value);
        editor.apply();
    }

    private static void put(String key, boolean value, SharedPreferences.Editor editor) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    private static void put(String key, long value, SharedPreferences.Editor editor) {
        editor.putLong(key, value);
        editor.apply();
    }

    private static String get(SharedPreferences preferences, String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    private static int get(SharedPreferences preferences, String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    private static boolean get(SharedPreferences preferences, String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    private static long get(SharedPreferences preferences, String key, long defaultValue) {
        return preferences.getLong(key, defaultValue);
    }

    public static void setServicePrivate(String privateKey) {
        SharedPreferences userSharedPreference = SPFactory.getUserSP(MYKEYSdk.getInstance().getContext());
        SharedPreferences.Editor editor = userSharedPreference.edit();
        put(StoreKeyCons.SP_KEY_SERVICE_PRIVATE_KEY, privateKey, editor);
    }

    public static String getServicePrivate() {
        SharedPreferences userSharedPreference = SPFactory.getUserSP(MYKEYSdk.getInstance().getContext());
        return get(userSharedPreference, StoreKeyCons.SP_KEY_SERVICE_PRIVATE_KEY, "");
    }

    public static void setServicePublic(String publicKey) {
        SharedPreferences userSharedPreference = SPFactory.getUserSP(MYKEYSdk.getInstance().getContext());
        SharedPreferences.Editor editor = userSharedPreference.edit();
        put(StoreKeyCons.SP_KEY_SERVICE_PUBLIC_KEY, publicKey, editor);
    }

    public static String getServicePublic() {
        SharedPreferences userSharedPreference = SPFactory.getUserSP(MYKEYSdk.getInstance().getContext());
        return get(userSharedPreference, StoreKeyCons.SP_KEY_SERVICE_PUBLIC_KEY, "");
    }
}
