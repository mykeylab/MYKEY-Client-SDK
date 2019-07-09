package com.mykey.sdk.common.util;

import android.text.TextUtils;
import android.util.Log;

import com.mykey.sdk.BuildConfig;

/**
 * Log utils
 * Created by zero on 2019/5/27.
 */

public class LogUtil {
    private static final String TAG = "LogUtil";

    /**
     * $: adb shell setprop log.tag.MyKey V
     * $: adb shell setprop log.tag.MyKey D
     */
    private static final boolean DEFAULT_LOG_STATUS = BuildConfig.DEBUG || isPropertyEnabled(TAG);
    private static boolean sLogStatus = DEFAULT_LOG_STATUS;

    public static boolean isPropertyEnabled(String propertyName) {
        return Log.isLoggable(propertyName, Log.VERBOSE);
    }

    public static void setDebug(boolean isDebug) {
        sLogStatus = isDebug;
    }

    public static boolean getDebugStatus() {
        return sLogStatus;
    }

    public static void initLogStatus() {
        sLogStatus = DEFAULT_LOG_STATUS;
    }

    /**
     * adb shell dumpsys activity com.mimikko.mimikkoui open/close
     *
     * @param command
     */

    public static void dumpLog(String[] command) {
        if (DEFAULT_LOG_STATUS || command == null || command.length == 0) {
            return;
        }
        if (command[0].contains("open")) {
            sLogStatus = true;
        } else if (command[0].contains("close")) {
            initLogStatus();
        }
    }


    public static void v(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (sLogStatus) {
            Log.v(TAG, msg);
        }
    }


    public static void v(String tag, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (sLogStatus) {
            Log.v(tag, msg);
        }
    }

    public static void i(String msg) {
        if (sLogStatus) {
            Log.i(TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Log.i(tag, msg);
    }

    public static void d(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (sLogStatus) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (sLogStatus) {
            Log.d(tag, msg);
        }
    }

    public static void w(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Log.w(TAG, msg);
    }

    public static void w(String tag, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Log.w(tag, msg);
    }

    public static void e(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Log.e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Log.e(tag, msg);
    }

    public static void e(String msg, Throwable e) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Log.e(TAG, msg, e);
    }

    public static Throwable getStackTrace() {
        final StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Throwable e = new Throwable();
        e.setStackTrace(stackTrace);
        return e;
    }

    /**
     * print long log
     * @param tag
     * @param str
     */
    public static void eLong(String tag, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.length() > 4000) {
            for (int i = 0, size = str.length(); i < size; i += 4000) {
                if (i + 4000 < size) {
                    Log.e(tag, str.substring(i, i + 4000));
                } else {
                    Log.e(tag, str.substring(i, size));
                }
            }
        }
    }

}