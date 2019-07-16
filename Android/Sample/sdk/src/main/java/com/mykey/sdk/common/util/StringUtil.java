package com.mykey.sdk.common.util;

import android.text.TextUtils;

import com.mykey.sdk.common.util.urlencode.UrlEscapers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by zero on 2019/5/30.
 */

public class StringUtil {
    public static String urlEncodeStr(String str) {
        try {
            str = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
    public static int toInt(String value) {
        if (TextUtils.isEmpty(value)) return 0;
        int valueInt = 0;
        try {
            valueInt = Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return valueInt;
    }

    public static long toLong(String value) {
        if (TextUtils.isEmpty(value)) return 0L;
        long result = 0L;
        try {
            result = Long.parseLong(value);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static double toDouble(String value) {
        if (TextUtils.isEmpty(value)) return 0.0d;
        double result = 0.0d;
        try {
            result = Double.valueOf(value);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static boolean toBoolean(String value) {
        if (TextUtils.isEmpty(value)) return false;
        boolean result = false;
        try {
            result = Boolean.valueOf(value);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    /**
     * 获取UTF-8 encode
     *
     * @param str
     * @return
     */
    public static String encodeUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
//            return URLEncoder.encode(str, "UTF-8");
            return UrlEscapers.urlFragmentEscaper().escape(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
