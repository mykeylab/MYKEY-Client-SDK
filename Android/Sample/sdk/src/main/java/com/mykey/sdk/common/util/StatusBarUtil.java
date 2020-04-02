package com.mykey.sdk.common.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.mykey.sdk.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * Created by zero on 2018/9/1.
 */

public class StatusBarUtil {
    private static float DEFAULT_ALPHA = Build.VERSION.SDK_INT >= Build
            .VERSION_CODES.LOLLIPOP ? 0.2f : 0.3f;

    /**
     * tint for status bar above OS Version 4.4 on Android
     *
     * @param activity
     * @param statusBarColor
     * @param alpha          [0.0-1.0]
     */
    public static void tintStatusBar(Activity activity, int
            statusBarColor, float alpha) {
        tintStatusBar(activity.getWindow(), statusBarColor, alpha);
    }

    /**
     * tint for status bar above OS Version 4.4 on Android
     *
     * @param window
     * @param statusBarColor
     * @param alpha          [0.0-1.0]
     */
    public static void tintStatusBar(Window window, int statusBarColor, float alpha) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            // if OS Version below 4, not support
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // OS Version above 5.0
            View decorView = window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            // OS Version below 5.0
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * Immersive full screen mode above Android 4.4
     *
     * @param activity
     * @param alpha    [0.0-1.0]
     */
    public static void immersiveStatusBar(Activity activity, float alpha) {
        immersiveStatusBar(activity.getWindow(), alpha);
    }

    /**
     * Immersive full screen mode above Android 4.4
     *
     * @param window
     * @param alpha  [0.0-1.0]
     */
    private static void immersiveStatusBar(Window window, float alpha) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            // if OS Version below 4.4, not support
            return;
        }
        ViewGroup decorView = (ViewGroup) window.getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams
                    .FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams
                    .FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

            int systemUiVisibility = decorView.getSystemUiVisibility();
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(systemUiVisibility);
        } else {
            window.addFlags(WindowManager.LayoutParams
                    .FLAG_TRANSLUCENT_STATUS);
        }
        ViewGroup contentView = (ViewGroup) decorView.findViewById(Window
                .ID_ANDROID_CONTENT);
        View rootView = contentView.getChildAt(0);
        if (rootView != null) {
            rootView.setFitsSystemWindows(true);
            int statusBarHeight = getStatusBarHeight(window.getContext());
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams)
                    rootView.getLayoutParams();
            lp.topMargin = -statusBarHeight;
            rootView.setLayoutParams(lp);
        }
        setTranslucentView(decorView, alpha);
    }

    public static void setStatusBarDarkMode(Activity activity) {
        setStatusBarDarkMode(activity.getWindow());
    }

    private static void setStatusBarDarkMode(Window window) {
        if (isFlyme4Later()) {
            setStatusBarDarkModeForFlyme4(window, true);
        } else if (isMIUI6Later()) {
            setStatusBarDarkModeForMIUI6(window, true);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setStatusBarDarkModeForM(window);
        }
    }

    /**
     * set font color above android 6.0
     */
    @TargetApi(Build.VERSION_CODES.M)
    private static void setStatusBarDarkModeForM(Window window) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams
                .FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);

        int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
        systemUiVisibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        window.getDecorView().setSystemUiVisibility(systemUiVisibility);
    }

    /**
     * set Flyme4+ darkMode, when set darkMode, font color and icon to be black http://open-wiki.flyme.cn/index.php?title=Flyme%E7%B3%BB%E7%BB%9FAPI
     */
    private static boolean setStatusBarDarkModeForFlyme4(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams e = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(e);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }

                meizuFlags.setInt(e, value);
                window.setAttributes(e);
                result = true;
            } catch (Exception var8) {
                Log.e("StatusBar", "setStatusBarDarkIcon: failed");
            }
        }

        return result;
    }

    /**
     * set MIUI6+ darkMode, when set darkMode, font color and icon to be black http://dev.xiaomi.com/doc/p=4769/
     */
    private static void setStatusBarDarkModeForMIUI6(Window window, boolean darkmode) {
        Class<? extends Window> clazz = window.getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(window, darkmode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setStatusBar(ViewGroup container, int statusBarColor, boolean visible, boolean addToFirst) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View statusBarView = container.findViewById(R.id.statusbar_view);
            if (statusBarView == null) {
                statusBarView = new View(container.getContext());
                statusBarView.setId(R.id.statusbar_view);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(container.getContext()));
                if (addToFirst) {
                    container.addView(statusBarView, 0, lp);
                } else {
                    container.addView(statusBarView, lp);
                }
            }
            statusBarView.setBackgroundColor(statusBarColor);
            statusBarView.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    private static void setStatusBar(ViewGroup container, int statusBarColor, boolean visible) {
        setStatusBar(container, statusBarColor, visible, false);
    }

    private static void setTranslucentView(ViewGroup container, float alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View translucentView = container.findViewById(R.id.translucent_view);
            if (translucentView == null) {
                translucentView = new View(container.getContext());
                translucentView.setId(R.id.translucent_view);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(container.getContext()));
                container.addView(translucentView, lp);
            }
            translucentView.setBackgroundColor(Color.argb((int) (alpha * 255), 0, 0, 0));
        }
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelSize(resId);
        }
        return result;
    }

    /**
     * is above Flyme4
     */
    public static boolean isFlyme4Later() {
        return Build.FINGERPRINT.contains("Flyme_OS_4")
                || Build.VERSION.INCREMENTAL.contains("Flyme_OS_4")
                || Pattern.compile("Flyme OS [4|5]", Pattern
                .CASE_INSENSITIVE).matcher(Build.DISPLAY).find()
                || Pattern.compile("Flyme [4|5|6]", Pattern.CASE_INSENSITIVE)
                .matcher(Build.DISPLAY).find();
    }

    /**
     * is above MIUI6
     */
    public static boolean isMIUI6Later() {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method mtd = clz.getMethod("get", String.class);
            String val = (String) mtd.invoke(null, "ro.miui.ui.version.name");
            val = val.replaceAll("[vV]", "");
            int version = Integer.parseInt(val);
            return version >= 6;
        } catch (Exception e) {
            return false;
        }
    }

    public static void setHeightAndPadding(Context context, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + getStatusBarHeight(context),
                    view.getPaddingRight(), view.getPaddingBottom());
        }
    }

}
