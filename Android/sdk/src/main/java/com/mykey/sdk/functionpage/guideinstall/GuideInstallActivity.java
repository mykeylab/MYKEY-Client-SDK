package com.mykey.sdk.functionpage.guideinstall;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mykey.sdk.R;
import com.mykey.sdk.common.base.BaseActivity;
import com.mykey.sdk.common.constants.ConfigCons;
import com.mykey.sdk.common.util.IntentUtil;

/**
 * Created by zero on 2019/6/12.
 */

public class GuideInstallActivity extends BaseActivity {

    private ImageView btnBack;
    private TextView btnGoDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        initView();
        initData();
        initListener();
    }

    private void initView() {
        setContentView(R.layout.activity_guide_install);
        btnBack = findViewById(R.id.back);
        btnGoDownload = findViewById(R.id.sdk_guide_install_download);
    }

    private void initData() {

    }

    private void initListener() {
        btnBack.setOnClickListener(onClickListener);
        btnGoDownload.setOnClickListener(onClickListener);
    }

    private void onClickBack() {
        finish();
    }

    private void onClickDownload() {
        IntentUtil.jumpByUrl(this, ConfigCons.MYKEY_OFFICIAL_PRODUCT_URL);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btnBack) {
                onClickBack();
            } else if (view == btnGoDownload) {
                onClickDownload();
            }
        }
    };

}
