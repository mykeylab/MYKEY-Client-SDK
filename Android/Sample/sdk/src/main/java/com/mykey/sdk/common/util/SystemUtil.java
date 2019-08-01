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
     * 获取DApp名称
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
     * 检查MyKey是否已安装
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
     * Base64加密,指定utf-8编码
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
     * Base64解密，指定utf-8编码
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
        //获得独一无二的Psuedo ID
        String serial = null;

        String deviceIdShort = "35" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +
                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +
                Build.USER.length() % 10; //13 位

        try {
            serial = android.os.Build.class.getField("SERIAL").get(null).toString();
        } catch (Exception exception) {
            //serial需要一个初始化
            serial = "serial"; // 随便一个初始化
            exception.printStackTrace();
        }
        //使用硬件信息拼凑出来的15位号码
        return new UUID(deviceIdShort.hashCode(), serial.hashCode()).toString();
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getModel() {
        return StringUtil.encodeUrl(android.os.Build.MODEL);
    }

    /**
     * 获取android系统版本号
     *
     * @return
     */
    public static String getSystemVersion() {
        return StringUtil.encodeUrl(android.os.Build.VERSION.RELEASE);
    }

    /**
     * 判断apk是否安装
     *
     * @param context
     * @param packageName
     * @return true:已安装；false：未安装
     */
    public static boolean apkInstalled(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
        //用于存储所有已安装程序的包名
        List<String> pName = new ArrayList<>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (null == pinfo) {
            return false;
        }
        for (int i = 0; i < pinfo.size(); i++) {
            String pn = pinfo.get(i).packageName;
            pName.add(pn);
        }
        //判断pName中是否有目标程序的包名，有TRUE，没有FALSE
        return pName.contains(packageName);
    }

    /**
     * 判断是否安装
     *
     * @param context
     * @param packageName
     * @return true:已安装；false：未安装
     */
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
