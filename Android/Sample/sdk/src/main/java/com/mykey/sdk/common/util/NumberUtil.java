package com.mykey.sdk.common.util;

import java.text.NumberFormat;

public class NumberUtil {
    public static String deleteScientificCounting(String num, int decimalCount) {
        return deleteScientificCounting(StringUtil.toDouble(num), decimalCount);
    }

    public static String deleteScientificCounting(double d, int decimalCount) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(decimalCount);
        nf.setGroupingUsed(false);
        return nf.format(d);
    }

    public static String deleteScientificCounting(double d) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(Integer.MAX_VALUE);
        nf.setGroupingUsed(false);
        return nf.format(d);
    }
}
