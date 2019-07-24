package com.mykey.sdk.common.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import com.mykey.sdk.R;
import com.mykey.sdk.common.util.StatusBarUtil;

/**
 * Created by zero on 2019/6/12.
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar();
    }

    private void initStatusBar() {
        StatusBarUtil.immersiveStatusBar(this, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (StatusBarUtil.isMIUI6Later() || StatusBarUtil.isFlyme4Later() || Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                StatusBarUtil.setStatusBarDarkMode(this);
            } else {
                StatusBarUtil.tintStatusBar(this, getResources().getColor(R.color.mykey_page_top_bg), 1f);
            }
            StatusBarUtil.setHeightAndPadding(this, getWindow().getDecorView());
        }
    }
}
