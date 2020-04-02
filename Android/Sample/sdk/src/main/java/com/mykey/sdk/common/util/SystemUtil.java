package com.mykey.sdk.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Base64;

import com.mykey.sdk.common.constants.ConfigCons;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by zero on 2019/5/27.
 */

public class SystemUtil {

    private final static String TAG = "SystemUtil";

    public static final int DEFAULT_USER_ID = -1;

    public static String getUserId(Context context) {
        PackageManager pm = context.getPackageManager();
        ApplicationInfo activityInfo = null;
        try {
            activityInfo = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            LogUtil.d(TAG, "User Id" + activityInfo.uid);
            return activityInfo.uid + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return DEFAULT_USER_ID + "";
    }

    /**
     * get DApp name
     *
     * @param context
     * @return
     */
    public static String getDAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * check MyKey install
     *
     * @param context
     * @return
     */
    public boolean isMyKeyInstall(Context context) {
        try {
            context.getPackageManager().getApplicationInfo(ConfigCons.MYKEY_PACKAGE_NAME, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Base64 encode
     *
     * @param content
     * @return
     */
    public static String base64Encode(String content) {
        try {
            return Base64.encodeToString(content.getBytes("utf-8"), Base64.NO_WRAP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Base64 decode
     *
     * @param encodedString
     * @return
     */
    public static String base64Decode(String encodedString) {
        try {
            return new String(Base64.decode(encodedString, Base64.NO_WRAP), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getUUID() {
        String serial = null;

        String deviceIdShort = "35" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +
                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +
                Build.USER.length() % 10;

        try {
            serial = android.os.Build.class.getField("SERIAL").get(null).toString();
        } catch (Exception exception) {
            serial = "serial";
            exception.printStackTrace();
        }
        return new UUID(deviceIdShort.hashCode(), serial.hashCode()).toString();
    }

    /**
     * get phone model
     *
     * @return
     */
    public static String getModel() {
        return StringUtil.encodeUrl(android.os.Build.MODEL);
    }

    /**
     * get os version
     *
     * @return
     */
    public static String getSystemVersion() {
        return StringUtil.encodeUrl(android.os.Build.VERSION.RELEASE);
    }

    public static boolean apkInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
        List<String> pName = new ArrayList<>();
        if (null == pinfo) {
            return false;
        }
        for (int i = 0; i < pinfo.size(); i++) {
            String pn = pinfo.get(i).packageName;
            pName.add(pn);
        }
        return pName.contains(packageName);
    }

    public static boolean apkInstalledMethodTwo(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        boolean hasInstallWx;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_GIDS);
            hasInstallWx = packageInfo != null;
        } catch (PackageManager.NameNotFoundException e) {
            hasInstallWx = false;
            e.printStackTrace();
        }
        return hasInstallWx;
    }
}
