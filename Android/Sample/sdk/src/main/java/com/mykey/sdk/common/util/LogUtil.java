package com.mykey.sdk.common.util;

import android.text.TextUtils;
import android.util.Log;

import com.mykey.sdk.BuildConfig;
import com.mykey.sdk.common.iface.LogCallback;

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
    private static LogCallback logCallback;

    public static void setLogCallback(LogCallback logCallback) {
        LogUtil.logCallback = logCallback;
    }

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

    public static void v(String tag, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (sLogStatus) {
            Log.v(tag, msg);
        }
        if (null != logCallback) {
            logCallback.log(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Log.i(tag, msg);
        if (null != logCallback) {
            logCallback.log(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (sLogStatus) {
            Log.d(tag, msg);
        }
        if (null != logCallback) {
            logCallback.log(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Log.w(tag, msg);
        if (null != logCallback) {
            logCallback.log(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Log.e(tag, msg);
        if (null != logCallback) {
            logCallback.log(tag, msg);
        }
    }

    public static Throwable getStackTrace() {
        final StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Throwable e = new Throwable();
        e.setStackTrace(stackTrace);
        return e;
    }

}