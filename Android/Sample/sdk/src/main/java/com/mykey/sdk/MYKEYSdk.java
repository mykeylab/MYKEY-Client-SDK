package com.mykey.sdk;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.mykey.sdk.callback.MYKEYCallbackManager;
import com.mykey.sdk.callback.MYKEYWalletCallback;
import com.mykey.sdk.common.constants.ConfigCons;
import com.mykey.sdk.common.constants.ErrorCons;
import com.mykey.sdk.common.iface.ApiCallback;
import com.mykey.sdk.common.store.memory.MemoryManager;
import com.mykey.sdk.common.util.JsonUtil;
import com.mykey.sdk.common.util.SystemUtil;
import com.mykey.sdk.entity.client.request.AuthorizeRequest;
import com.mykey.sdk.entity.client.request.ContractRequest;
import com.mykey.sdk.entity.client.request.InitRequest;
import com.mykey.sdk.entity.client.request.InitSimpleRequest;
import com.mykey.sdk.entity.client.request.SignRequest;
import com.mykey.sdk.entity.client.request.TransferRequest;
import com.mykey.sdk.entity.client.response.PayloadResponse;
import com.mykey.sdk.functionpage.guideinstall.GuideInstallActivity;
import com.mykey.sdk.handle.AuthorizeHandle;
import com.mykey.sdk.handle.ContractHandle;
import com.mykey.sdk.handle.InitHandle;
import com.mykey.sdk.handle.SignHandle;
import com.mykey.sdk.handle.TransferHandle;

/**
 * Responsible for providing interface for APP, this kind does not provide specific business implementation, only external, similar to adapter
 * Created by zero on 2019/5/27.
 */

public class MYKEYSdk {
    private final static MYKEYSdk mykeySdk = new MYKEYSdk();
    private Context context;
    private InitHandle initHandle;
    private AuthorizeHandle authorizeHandle;
    private TransferHandle transferHandle;
    private ContractHandle contractHandle;
    private SignHandle signHandle;

    private MYKEYSdk() {
        initHandle = new InitHandle();
        authorizeHandle = new AuthorizeHandle();
        transferHandle = new TransferHandle();
        contractHandle = new ContractHandle();
        signHandle = new SignHandle();
    }

    public static MYKEYSdk getInstance() {
        return mykeySdk;
    }

    public Context getContext() {
        return context;
    }

    /**
     * MYKEY SDK Initial
     * This initialization performs a binding operation with MYKEY
     * @param initRequest
     */
    public void init(InitRequest initRequest) {
        this.context = initRequest.getContext().getApplicationContext();
        initHandle.handle(initRequest);
    }

    /**
     * MYKEY SDK simple Initial
     * If you don't want to perform the binding operation with MYKEY, you can use this initialization method
     * @param initRequest
     */
    public void initSimple(InitSimpleRequest initRequest) {
        this.context = initRequest.getContext().getApplicationContext();
        initHandle.handle(initRequest);
    }

    /**
     * init: bind with MYKEY
     * initSimple: login through MYKEY
     * @param authorizeRequest
     * @param callback
     */
    public void authorize(AuthorizeRequest authorizeRequest, MYKEYWalletCallback callback) {
        if (!check(callback)) {
            return;
        }
        MYKEYCallbackManager.getInstance().registerListener(callback);
        authorizeHandle.handle(context, authorizeRequest, callback);
    }

    /**
     * transfer
     * @param transferRequest
     * @param callback
     */
    public void transfer(TransferRequest transferRequest, MYKEYWalletCallback callback) {
        if (!check(callback)) {
            return;
        }
        MYKEYCallbackManager.getInstance().registerListener(callback);
        transferHandle.handle(context, transferRequest, callback);
    }

    /**
     * contract
     * @param contractRequest
     * @param callback
     */
    public void contract(ContractRequest contractRequest, MYKEYWalletCallback callback) {
        if (!check(callback)) {
            return;
        }
        MYKEYCallbackManager.getInstance().registerListener(callback);
        contractHandle.handle(context, contractRequest, callback);
    }

    /**
     * sign
     * @param signRequest
     * @param callback
     */
    public void sign(SignRequest signRequest, MYKEYWalletCallback callback) {
        if (!check(callback)) {
            return;
        }
        MYKEYCallbackManager.getInstance().registerListener(callback);
        signHandle.handle(context, signRequest, callback);
    }

    private boolean check(MYKEYWalletCallback callback) {
        if (!checkInit(callback)) {
            return false;
        }
        if (!checkAppInstall(callback)) {
            return false;
        }
        return true;
    }

    /**
     * Detect whether MYKEY is installed
     * @param callback
     * @return
     */
    private boolean checkAppInstall(MYKEYWalletCallback callback) {
        if (SystemUtil.apkInstalled(context, ConfigCons.MYKEY_PACKAGE_NAME)) {
            return true;
        }
        callback.onError(JsonUtil.toJson(new PayloadResponse(ErrorCons.ERROR_CODE_MYKEY_NOT_INSTALL)));
        if (!MemoryManager.isDisableInstall()) {
            // 如果配置允许则跳转到引导安装页面
            jumpToGuideInstall();
        }
        return false;
    }

    /**
     * Checks whether it has been initialized
     * @param callback
     * @return
     */
    private boolean checkInit(MYKEYWalletCallback callback) {
        if (TextUtils.isEmpty(MemoryManager.getCallBackPage()) || null == context) {
            callback.onError(JsonUtil.toJson(new PayloadResponse(ErrorCons.ERROR_CODE_NO_INIT)));
            return false;
        }
        return true;
    }

    private void jumpToGuideInstall() {
        Intent intent = new Intent(context, GuideInstallActivity.class);
        context.startActivity(intent);
    }

}