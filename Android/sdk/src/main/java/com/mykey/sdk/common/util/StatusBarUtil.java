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
     * Android4.4以上的状态栏着色
     *
     * @param activity       Activity对象
     * @param statusBarColor 状态栏颜色
     */
    public static void tintStatusBar(Activity activity, int
            statusBarColor) {
        tintStatusBar(activity, statusBarColor, DEFAULT_ALPHA);
    }

    /**
     * Android4.4以上的状态栏着色
     *
     * @param activity       Activity对象
     * @param statusBarColor 状态栏颜色
     * @param alpha          透明栏透明度[0.0-1.0]
     */
    public static void tintStatusBar(Activity activity, int
            statusBarColor, float alpha) {
        tintStatusBar(activity.getWindow(), statusBarColor, alpha);
    }

    /**
     * Android4.4以上的状态栏着色
     *
     * @param window         一般都是用于Activity的window,也可以是其他的例如Dialog,
     *                       DialogFragment
     * @param statusBarColor 状态栏颜色
     */
    public static void tintStatusBar(Window window, int
            statusBarColor) {
        tintStatusBar(window, statusBarColor, DEFAULT_ALPHA);
    }

    /**
     * Android4.4以上的状态栏着色
     *
     * @param window         一般都是用于Activity的window,也可以是其他的例如Dialog,
     *                       DialogFragment
     * @param statusBarColor 状态栏颜色
     * @param alpha          透明栏透明度[0.0-1.0]
     */
    public static void tintStatusBar(Window window, int
            statusBarColor, float alpha) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {//系统版本号小于4
            // .4,不支持修改状态栏
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//系统版本大于5.0
            window.clearFlags(WindowManager.LayoutParams
                    .FLAG_TRANSLUCENT_STATUS);//去除半透明状态栏标志
            window.addFlags(WindowManager.LayoutParams
                    .FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);//添加绘制状态栏背景色标志
            window.setStatusBarColor(Color.TRANSPARENT);//设置状态栏颜色为透明
        } else {//系统版本在4.4与5.0间
            window.addFlags(WindowManager.LayoutParams
                    .FLAG_TRANSLUCENT_STATUS);//添加状态栏为透明标志
        }

        ViewGroup decorView = (ViewGroup) window.getDecorView();//获取窗口组件的装饰器控件
        ViewGroup contentView = (ViewGroup) window.getDecorView()
                .findViewById(Window.ID_ANDROID_CONTENT);//获取用户设置的视图控件
        View rootView = contentView.getChildAt(0);//获取视图控件的根控件
        if (rootView != null) {
            rootView.setFitsSystemWindows(true);//设置适应窗口
        }

        setStatusBar(decorView, statusBarColor, true);
        setTranslucentView(decorView, alpha);
    }

//    /**
//     * Android4.4以上的状态栏着色(针对于DrawerLayout) 注: 1.如果出现界面展示不正确,
//     * 删除布局中所有fitsSystemWindows属性,尤其是DrawerLayout的fitsSystemWindows属性
//     * 2.可以版本判断在5.0以上不调用该方法,使用系统自带
//     *
//     * @param activity       Activity对象
//     * @param drawerLayout   DrawerLayout对象
//     * @param statusBarColor 状态栏颜色
//     */
//    public static void tintStatusBarForDrawer(Activity activity, DrawerLayout drawerLayout, int statusBarColor) {
//        tintStatusBarForDrawer(activity, drawerLayout, statusBarColor,
//                DEFAULT_ALPHA);
//    }

//    /**
//     * Android4.4以上的状态栏着色(针对于DrawerLayout) 注: 1.如果出现界面展示不正确,
//     * 删除布局中所有fitsSystemWindows属性,尤其是DrawerLayout的fitsSystemWindows属性
//     * 2.可以版本判断在5.0以上不调用该方法,使用系统自带
//     *
//     * @param activity       Activity对象
//     * @param drawerLayout   DrawerLayout对象
//     * @param statusBarColor 状态栏颜色
//     * @param alpha          透明栏透明度[0.0-1.0]
//     */
//    public static void tintStatusBarForDrawer(Activity activity, DrawerLayout
//            drawerLayout, @ColorInt int statusBarColor,
//                                              @FloatRange(from = 0.0, to = 1.0) float alpha) {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {//版本低于4.4
//            return;
//        }
//
//        Window window = activity.getWindow();//获取活动窗口
//        ViewGroup decorView = (ViewGroup) window.getDecorView();//获取装饰器控件
//        ViewGroup drawContent = (ViewGroup) drawerLayout.getChildAt(0);
//        //获取抽屉控件的第一层控件
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//版本大于5.0
//            window.clearFlags(WindowManager.LayoutParams
//                    .FLAG_TRANSLUCENT_STATUS);//清除半透明标志
//            window.addFlags(WindowManager.LayoutParams
//                    .FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);//添加绘制状态栏背景标志
//            window.setStatusBarColor(Color.TRANSPARENT);//设置状态栏背景色为透明
//            drawerLayout.setStatusBarBackgroundColor(statusBarColor);
//            //设置抽屉控件的背景色
//
//            int systemUiVisibility = window.getDecorView()
//                    .getSystemUiVisibility();
//            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;//全屏
//            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;//
//            window.getDecorView().setSystemUiVisibility(systemUiVisibility);
//            //设置系统ui显示风格
//        } else {
//            window.addFlags(WindowManager.LayoutParams
//                    .FLAG_TRANSLUCENT_STATUS);//添加半透明标志
//        }
//
//        setStatusBar(decorView, statusBarColor, true, true);
//        setTranslucentView(decorView, alpha);//设置透明度
//
//        drawerLayout.setFitsSystemWindows(false);
//        drawContent.setFitsSystemWindows(true);
//        ViewGroup drawer = (ViewGroup) drawerLayout.getChildAt(1);
//        drawer.setFitsSystemWindows(false);
//    }

    /**
     * Android4.4以上的沉浸式全屏模式 注: 1.删除fitsSystemWindows属性:Android5
     * .0以上使用该方法如果出现界面展示不正确,删除布局中所有fitsSystemWindows属性
     * 或者调用forceFitsSystemWindows方法 2.不删除fitsSystemWindows属性:也可以区别处理,Android5
     * .0以上使用自己的方式实现,不调用该方法
     *
     * @param activity Activity对象
     */
    public static void immersiveStatusBar(Activity activity) {
        immersiveStatusBar(activity, DEFAULT_ALPHA);
    }

    /**
     * Android4.4以上的沉浸式全屏模式 注: 1.删除fitsSystemWindows属性:Android5
     * .0以上使用该方法如果出现界面展示不正确,删除布局中所有fitsSystemWindows属性
     * 或者调用forceFitsSystemWindows方法 2.不删除fitsSystemWindows属性:也可以区别处理,Android5
     * .0以上使用自己的方式实现,不调用该方法
     *
     * @param activity Activity对象
     * @param alpha    透明栏透明度[0.0-1.0]
     */
    public static void immersiveStatusBar(Activity activity, float alpha) {
        immersiveStatusBar(activity.getWindow(), alpha);
    }

    /**
     * Android4.4以上的沉浸式全屏模式 注: 1.删除fitsSystemWindows属性:Android5
     * .0以上使用该方法如果出现界面展示不正确,删除布局中所有fitsSystemWindows属性
     * 或者调用forceFitsSystemWindows方法 2.不删除fitsSystemWindows属性:也可以区别处理,Android5
     * .0以上使用自己的方式实现,不调用该方法
     *
     * @param window 一般都是用于Activity的window,也可以是其他的例如Dialog,DialogFragment
     */
    public static void immersiveStatusBar(Window window) {
        immersiveStatusBar(window, DEFAULT_ALPHA);
    }

    /**
     * Android4.4以上的沉浸式全屏模式 注: 1.删除fitsSystemWindows属性:Android5
     * .0以上使用该方法如果出现界面展示不正确,删除布局中所有fitsSystemWindows属性
     * 或者调用forceFitsSystemWindows方法 2.不删除fitsSystemWindows属性:也可以区别处理,Android5
     * .0以上使用自己的方式实现,不调用该方法
     *
     * @param window 一般都是用于Activity的window,也可以是其他的例如Dialog,DialogFragment
     * @param alpha  透明栏透明度[0.0-1.0]
     */
    private static void immersiveStatusBar(Window window, float alpha) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {//版本小于4.4
            return;
        }
        ViewGroup decorView = (ViewGroup) window.getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//大于5.0
            window.clearFlags(WindowManager.LayoutParams
                    .FLAG_TRANSLUCENT_STATUS);//清除半透明标志
            window.addFlags(WindowManager.LayoutParams
                    .FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);//添加绘制状态栏背景标志
            window.setStatusBarColor(Color.TRANSPARENT);//设置状态栏的颜色为透明

            int systemUiVisibility = decorView.getSystemUiVisibility();
            //获取装饰控件的ui显示属性
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;//与全屏标志或
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//设置字体颜色为黑色
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;//保持布局不被拉伸
            decorView.setSystemUiVisibility(systemUiVisibility);//设置系统ui显示风格
        } else {
            window.addFlags(WindowManager.LayoutParams
                    .FLAG_TRANSLUCENT_STATUS);//设置半透明
        }
        ViewGroup contentView = (ViewGroup) decorView.findViewById(Window
                .ID_ANDROID_CONTENT);//获取用户定义的布局控件
        View rootView = contentView.getChildAt(0);//获取最外层控件
        if (rootView != null) {//用户定义了布局
//            ViewCompat.setFitsSystemWindows(rootView, true);//设置适应窗口
            rootView.setFitsSystemWindows(true);
            int statusBarHeight = getStatusBarHeight(window.getContext());
            //获取状态栏高度
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams)
                    rootView.getLayoutParams();//获取布局参数对象
            lp.topMargin = -statusBarHeight;//向上扩展到状态栏
            rootView.setLayoutParams(lp);//设置布局
        }
        setTranslucentView(decorView, alpha);//设置透明度
    }

    /**
     * 设置状态栏darkMode,字体颜色及icon变黑(目前支持MIUI6以上,Flyme4以上,Android M以上)
     */
    public static void setStatusBarDarkMode(Activity activity) {
        setStatusBarDarkMode(activity.getWindow());
    }

    /**
     * 设置状态栏darkMode,字体颜色及icon变黑(目前支持MIUI6以上,Flyme4以上,Android M以上)
     */
    private static void setStatusBarDarkMode(Window window) {
        if (isFlyme4Later()) {
            setStatusBarDarkModeForFlyme4(window, true);
        } else if (isMIUI6Later()) {
            setStatusBarDarkModeForMIUI6(window, true);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setStatusBarDarkModeForM(window);
        }
    }

    //------------------------->

    /**
     * android 6.0设置字体颜色
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
     * 设置Flyme4+的darkMode,darkMode时候字体颜色及icon变黑 http://open-wiki.flyme
     * .cn/index.php?title=Flyme%E7%B3%BB%E7%BB%9FAPI
     */
    private static boolean setStatusBarDarkModeForFlyme4(Window window,
                                                         boolean dark) {
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
     * 设置MIUI6+的状态栏是否为darkMode,darkMode时候字体颜色及icon变黑 http://dev.xiaomi
     * .com/doc/p=4769/
     */
    private static void setStatusBarDarkModeForMIUI6(Window window, boolean
            darkmode) {
        Class<? extends Window> clazz = window.getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view" +
                    ".MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField
                    ("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int
                    .class, int.class);
            extraFlagField.invoke(window, darkmode ? darkModeFlag : 0,
                    darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建假的状态栏View
     */
    private static void setStatusBar(ViewGroup container, int
            statusBarColor, boolean visible, boolean addToFirst) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//大于4.4
            View statusBarView = container.findViewById(R.id.statusbar_view);
            //获取状态栏id对应的控件
            if (statusBarView == null) {//没有对应的控件
                statusBarView = new View(container.getContext());//创建一个
                statusBarView.setId(R.id.statusbar_view);//设置状态栏的id
                //设置尺寸，长为全屏，高为状态栏高度
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        getStatusBarHeight(container.getContext()));
                if (addToFirst) {//在第一个位置添加状态栏控件
                    container.addView(statusBarView, 0, lp);
                } else {
                    container.addView(statusBarView, lp);//添加在尾部
                }
            }

            statusBarView.setBackgroundColor(statusBarColor);//设置控件的背景色
            statusBarView.setVisibility(visible ? View.VISIBLE : View.GONE);
            //设置是否显示
        }
    }

    /**
     * 创建假的状态栏View
     */
    private static void setStatusBar(ViewGroup container, int
            statusBarColor, boolean visible) {
        setStatusBar(container, statusBarColor, visible, false);
    }

    /**
     * 创建假的透明栏
     */
    private static void setTranslucentView(ViewGroup container, float alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//大于4.4
            View translucentView = container.findViewById(R.id
                    .translucent_view);//获取
            if (translucentView == null) {
                translucentView = new View(container.getContext());
                translucentView.setId(R.id.translucent_view);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        getStatusBarHeight(container.getContext()));
                container.addView(translucentView, lp);
            }

            translucentView.setBackgroundColor(Color.argb((int) (alpha * 255)
                    , 0, 0, 0));
        }
    }


    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resId = context.getResources().getIdentifier("status_bar_height",
                "dimen", "android");//获取状态栏高度资源id
        if (resId > 0) {
            result = context.getResources().getDimensionPixelSize(resId);//获取高度
        }
        return result;
    }

    /**
     * 判断是否Flyme4以上
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
     * 判断是否为MIUI6以上
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

    /**
     * 增加View的高度以及paddingTop,增加的值为状态栏高度.一般是在沉浸式全屏给ToolBar用的
     */
    public static void setHeightAndPadding(Context context, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            ViewGroup.LayoutParams lp = view.getLayoutParams();
//            lp.height += getStatusBarHeight(context);//增高
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() +
                            getStatusBarHeight(context),
                    view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    /**
     * 增加View的paddingTop,增加的值为状态栏高度
     */
    public static void setPadding(Context context, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() +
                            getStatusBarHeight(context),
                    view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    /**
     * 强制rootView下面的子View的FitsSystemWindows为false
     */
    public static void forceFitsSystemWindows(Activity activity) {
        forceFitsSystemWindows(activity.getWindow());
    }

    /**
     * 强制rootView下面的子View的FitsSystemWindows为false
     */
    private static void forceFitsSystemWindows(Window window) {
        forceFitsSystemWindows((ViewGroup) window.getDecorView().findViewById
                (Window.ID_ANDROID_CONTENT));
    }

    /**
     * 强制rootView下面的子View的FitsSystemWindows为false
     */
    private static void forceFitsSystemWindows(ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//大于4.4
            int count = viewGroup.getChildCount();//获取用户视图的所有控件数
            for (int i = 0; i < count; i++) {//依次对每个控件进行操作
                View view = viewGroup.getChildAt(i);//获取当前控件
                if (view instanceof ViewGroup) {//当前控件为容器
                    forceFitsSystemWindows((ViewGroup) view);//迭代
                } else {
                    if (view.getFitsSystemWindows()) {
//                        ViewCompat.setFitsSystemWindows(view, false);//设置为不是配
                        view.setFitsSystemWindows(false);
                    }
                }
            }
        }
    }
}
