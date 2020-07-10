package com.mykey.sdk.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mykey.sdk.common.constants.ConfigCons;
import com.mykey.sdk.common.util.MKUtil;
import com.mykey.sdk.sample.controller.AuthorizeAnyChainController;
import com.mykey.sdk.sample.controller.MYKEYSdkInitController;
import com.mykey.sdk.sample.controller.SimpleWalletController;
import com.mykey.sdk.sample.controller.StakeTokenApiController;
import com.mykey.sdk.sample.controller.StakeTokenExampleController;
import com.mykey.sdk.sample.controller.mykeywallet.MYKEYWalletEosController;
import com.mykey.sdk.sample.controller.mykeywallet.MYKEYWalletEthController;

public class SampleActivity extends Activity {

    private Button btnGoToMYKEY, btnGoToDApp;
    private Button btnMYKEYSdk, btnMYKEYSdkSimple;
    private Button btnMyKeyAuthorizeAny, btnSimpleWalletAuthorizeAny;
    private Button btnMYKEYAuthorize, btnSimpleWalletAuthorize, btnMYKEYContract, btnSimpleWalletContract, btnMYKEYTransfer, btnSimpleWalletTransfer, btnMYKEYSign, btnSimpleWalletSign;
    private Button btnGetBalance, btnGetUnlockList, btnGetBindInfo;
    private Button btnStake, btnUnStake, btnTransfer, btnTransferAndStake, btnUnStakeAndTransfer;
    private Button btnMYKEYEthAuthorize, btnMYKEYEthContract, btnMYKEYEthContractTransfer, btnMYKEYEthContractMulti, btnMYKEYEthTransferEth, btnMYKEYEthTransferErc20, btnMYKEYEthSign;

    private MYKEYSdkInitController mykeySdkInitController;
    private SimpleWalletController simpleWalletController;
    private MYKEYWalletEosController mykeyWalletEosController;
    private MYKEYWalletEthController mykeyWalletEthController;
    private StakeTokenApiController stakeTokenApiController;
    private StakeTokenExampleController stakeTokenExampleController;
    private AuthorizeAnyChainController authorizeAnyChainController;

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

        btnMyKeyAuthorizeAny = findViewById(R.id.sample_authorize_mykey_any);
        btnSimpleWalletAuthorizeAny = findViewById(R.id.sample_authorize_simple_any);

        btnSimpleWalletAuthorize = findViewById(R.id.sample_authorize_simple_wallet);
        btnSimpleWalletContract = findViewById(R.id.sample_contract_simple_wallet);
        btnSimpleWalletTransfer = findViewById(R.id.sample_transfer_simple_wallet);
        btnSimpleWalletSign = findViewById(R.id.sample_sign_simple_wallet);

        btnMYKEYAuthorize = findViewById(R.id.sample_authorize_mykey);
        btnMYKEYContract = findViewById(R.id.sample_contract_mykey);
        btnMYKEYTransfer = findViewById(R.id.sample_transfer_mykey);
        btnMYKEYSign = findViewById(R.id.sample_sign_mykey);

        btnMYKEYEthAuthorize = findViewById(R.id.sample_authorize_mykey_eth);
        btnMYKEYEthContract = findViewById(R.id.sample_contract_mykey_eth);
        btnMYKEYEthContractTransfer = findViewById(R.id.sample_contract_and_transfer_eth_mykey_eth);
        btnMYKEYEthContractMulti = findViewById(R.id.sample_contract_multi_mykey_eth);
        btnMYKEYEthTransferEth = findViewById(R.id.sample_transfer_mykey_eth);
        btnMYKEYEthTransferErc20 = findViewById(R.id.sample_transfer_mykey_erc20);
        btnMYKEYEthSign = findViewById(R.id.sample_sign_mykey_eth);

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
        mykeyWalletEosController = new MYKEYWalletEosController(this);
        mykeyWalletEthController = new MYKEYWalletEthController(this);
        stakeTokenApiController = new StakeTokenApiController(this);
        stakeTokenExampleController = new StakeTokenExampleController(this);
        authorizeAnyChainController = new AuthorizeAnyChainController(this);
    }

    private void initListener() {
        btnGoToMYKEY.setOnClickListener(onClickListener);
        btnGoToDApp.setOnClickListener(onClickListener);

        btnMYKEYSdk.setOnClickListener(onClickListener);
        btnMYKEYSdkSimple.setOnClickListener(onClickListener);

        btnMyKeyAuthorizeAny.setOnClickListener(onClickListener);
        btnSimpleWalletAuthorizeAny.setOnClickListener(onClickListener);

        btnSimpleWalletAuthorize.setOnClickListener(onClickListener);
        btnSimpleWalletContract.setOnClickListener(onClickListener);
        btnSimpleWalletTransfer.setOnClickListener(onClickListener);
        btnSimpleWalletSign.setOnClickListener(onClickListener);

        btnMYKEYEthAuthorize.setOnClickListener(onClickListener);
        btnMYKEYEthContract.setOnClickListener(onClickListener);
        btnMYKEYEthContractTransfer.setOnClickListener(onClickListener);
        btnMYKEYEthContractMulti.setOnClickListener(onClickListener);
        btnMYKEYEthTransferEth.setOnClickListener(onClickListener);
        btnMYKEYEthTransferErc20.setOnClickListener(onClickListener);
        btnMYKEYEthSign.setOnClickListener(onClickListener);

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
            // Authorize with set ANY chain
            else if (view == btnMyKeyAuthorizeAny) {
                authorizeAnyChainController.onMyKeyAuthorizeAnyChain();
            } else if (view == btnSimpleWalletAuthorizeAny) {
                authorizeAnyChainController.onSimpleWalletAuthorizeAnyChain();
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
            // Following are MYKEYSdk EOS method calls
            else if (view == btnMYKEYAuthorize) {
                mykeyWalletEosController.onAuthorize();
            } else if (view == btnMYKEYContract) {
                mykeyWalletEosController.onMyKeyContract();
            } else if (view == btnMYKEYTransfer) {
                mykeyWalletEosController.onMyKeyTransfer();
            } else if (view == btnMYKEYSign) {
                mykeyWalletEosController.onMyKeySign();
            }
            // Following are MYKEYSdk ETH method calls
            else if (view == btnMYKEYEthAuthorize) {
                mykeyWalletEthController.onAuthorize();
            } else if (view == btnMYKEYEthContract) {
                mykeyWalletEthController.onMyKeyContract();
            } else if (view == btnMYKEYEthContractTransfer) {
                mykeyWalletEthController.onMyKeyContractAndEthTransfer();
            } else if (view == btnMYKEYEthContractMulti) {
                mykeyWalletEthController.onMyKeyContractMulti();
            } else if (view == btnMYKEYEthTransferEth) {
                mykeyWalletEthController.onMyKeyTransferEth();
            } else if (view == btnMYKEYEthTransferErc20) {
                mykeyWalletEthController.onMyKeyTransferErc20();
            } else if (view == btnMYKEYEthSign) {
                mykeyWalletEthController.onMyKeySign();
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
