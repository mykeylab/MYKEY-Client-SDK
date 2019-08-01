package com.mykey.sdk.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mykey.sdk.common.constants.ConfigCons;
import com.mykey.sdk.common.util.MKUtil;
import com.mykey.sdk.sample.controller.MYKEYSdkInitController;
import com.mykey.sdk.sample.controller.MYKEYWalletController;
import com.mykey.sdk.sample.controller.SimpleWalletController;
import com.mykey.sdk.sample.controller.StakeTokenApiController;
import com.mykey.sdk.sample.controller.StakeTokenExampleController;

public class SampleActivity extends AppCompatActivity {

    private Button btnGoToMYKEY, btnGoToDApp;
    private Button btnMYKEYSdk, btnMYKEYSdkSimple;
    private Button btnMYKEYAuthorize, btnSimpleWalletAuthorize, btnMYKEYContract, btnSimpleWalletContract, btnMYKEYTransfer, btnSimpleWalletTransfer, btnMYKEYSign, btnSimpleWalletSign;
    private Button btnGetBalance, btnGetUnlockList, btnGetBindInfo;
    private Button btnStake, btnUnStake, btnTransfer, btnTransferAndStake, btnUnStakeAndTransfer;

    private MYKEYSdkInitController mykeySdkInitController;
    private SimpleWalletController simpleWalletController;
    private MYKEYWalletController mykeyWalletController;
    private StakeTokenApiController stakeTokenApiController;
    private StakeTokenExampleController stakeTokenExampleController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        btnGoToMYKEY = findViewById(R.id.deeplink_mykey);
        btnGoToDApp = findViewById(R.id.deeplink_dapp);

        btnMYKEYSdk = findViewById(R.id.mykey_sdk_init);
        btnMYKEYSdkSimple = findViewById(R.id.mykey_sdk_init_simple);

        btnSimpleWalletAuthorize = findViewById(R.id.simple_authorize_simple_wallet);
        btnSimpleWalletContract = findViewById(R.id.simple_contract_simple_wallet);
        btnSimpleWalletTransfer = findViewById(R.id.simple_transfer_simple_wallet);
        btnSimpleWalletSign = findViewById(R.id.simple_sign_simple_wallet);

        btnMYKEYAuthorize = findViewById(R.id.simple_authorize_mykey);
        btnMYKEYContract = findViewById(R.id.simple_contract_mykey);
        btnMYKEYTransfer = findViewById(R.id.simple_transfer_mykey);
        btnMYKEYSign = findViewById(R.id.simple_sign_mykey);

        btnGetBalance = findViewById(R.id.api_get_balance);
        btnGetUnlockList = findViewById(R.id.api_get_unlock_list);
        btnGetBindInfo = findViewById(R.id.api_get_bind_info);

        btnStake = findViewById(R.id.stake_token_contract_stake);
        btnUnStake = findViewById(R.id.stake_token_contract_unstake);
        btnTransfer = findViewById(R.id.stake_token_contract_transfer);
        btnTransferAndStake = findViewById(R.id.stake_token_contract_transfer_stake);
        btnUnStakeAndTransfer = findViewById(R.id.stake_token_contract_unstake_transfer);
    }

    private void initData() {
        mykeySdkInitController = new MYKEYSdkInitController(this);
        simpleWalletController = new SimpleWalletController(this);
        mykeyWalletController = new MYKEYWalletController(this);
        stakeTokenApiController = new StakeTokenApiController(this);
        stakeTokenExampleController = new StakeTokenExampleController(this);
    }

    private void initListener() {
        btnGoToMYKEY.setOnClickListener(onClickListener);
        btnGoToDApp.setOnClickListener(onClickListener);

        btnMYKEYSdk.setOnClickListener(onClickListener);
        btnMYKEYSdkSimple.setOnClickListener(onClickListener);

        btnSimpleWalletAuthorize.setOnClickListener(onClickListener);
        btnSimpleWalletContract.setOnClickListener(onClickListener);
        btnSimpleWalletTransfer.setOnClickListener(onClickListener);
        btnSimpleWalletSign.setOnClickListener(onClickListener);

        btnMYKEYAuthorize.setOnClickListener(onClickListener);
        btnMYKEYContract.setOnClickListener(onClickListener);
        btnMYKEYTransfer.setOnClickListener(onClickListener);
        btnMYKEYSign.setOnClickListener(onClickListener);

        btnGetBalance.setOnClickListener(onClickListener);
        btnGetUnlockList.setOnClickListener(onClickListener);
        btnGetBindInfo.setOnClickListener(onClickListener);

        btnStake.setOnClickListener(onClickListener);
        btnUnStake.setOnClickListener(onClickListener);
        btnTransfer.setOnClickListener(onClickListener);
        btnTransferAndStake.setOnClickListener(onClickListener);
        btnUnStakeAndTransfer.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // DeepLink
            if (view == btnGoToMYKEY) {
                // Jump to MYKEY home
                MKUtil.redirectToApp(SampleActivity.this, ConfigCons.MYKEY_DEEP_LINK_HOME);
            } else if (view == btnGoToDApp) {
                // Jump to dapp in MYKEY
                MKUtil.redirectToApp(SampleActivity.this, ConfigCons.MYKEY_DEEP_LINK_BIG_GAME);
            }
            // Following are MYKEY initialization method calls
            else if (view == btnMYKEYSdk) {
                mykeySdkInitController.initSdk();
            } else if (view == btnMYKEYSdkSimple) {
                mykeySdkInitController.initSdkSimple();
            }
            // Following are SimpleWallet method calls
            else if (view == btnSimpleWalletAuthorize) {
                simpleWalletController.onkAuthorize();
            } else if (view == btnSimpleWalletContract) {
                simpleWalletController.onSimpleWalletContract();
            } else if (view == btnSimpleWalletTransfer) {
                simpleWalletController.onSimpleWalletTransfer();
            } else if (view == btnSimpleWalletSign) {
                simpleWalletController.onSimpleWalletSign();
            }
            // Following are MYKEYSdk method calls
            else if (view == btnMYKEYAuthorize) {
                mykeyWalletController.onAuthorize();
            } else if (view == btnMYKEYContract) {
                mykeyWalletController.onMyKeyContract();
            } else if (view == btnMYKEYTransfer) {
                mykeyWalletController.onMyKeyTransfer();
            } else if (view == btnMYKEYSign) {
                mykeyWalletController.onMyKeySign();
            }
            // Following are 'stake token contract' api calls
            else if (view == btnGetBalance) {
                stakeTokenApiController.onGetBalance();
            } else if (view == btnGetUnlockList) {
                stakeTokenApiController.onGetUnlockList();
            } else if (view == btnGetBindInfo) {
                stakeTokenApiController.onBindInfo();
            }
            // Following are 'stake token contract' method calls
            else if (view == btnStake) {
                stakeTokenExampleController.onStake();
            } else if (view == btnUnStake) {
                stakeTokenExampleController.onUnStake();
            } else if (view == btnTransfer) {
                stakeTokenExampleController.onTransfer();
            } else if (view == btnTransferAndStake) {
                stakeTokenExampleController.onTransferAndStake();
            } else if (view == btnUnStakeAndTransfer) {
                stakeTokenExampleController.onUnStakeAndTransfer();
            }
        }
    };

}
