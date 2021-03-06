package com.mykey.sdk.handle;

import android.content.Context;
import android.text.TextUtils;

import com.mykey.sdk.common.constants.ConfigCons;
import com.mykey.sdk.common.constants.StoreKeyCons;
import com.mykey.sdk.common.store.memory.MemoryManager;
import com.mykey.sdk.common.util.LogUtil;
import com.mykey.sdk.common.util.MD5Util;
import com.mykey.sdk.common.util.SystemUtil;
import com.mykey.sdk.connect.service.ServiceConnectManager;
import com.mykey.sdk.entity.client.request.InitRequest;
import com.mykey.sdk.entity.client.request.InitSimpleRequest;
import com.mykey.sdk.jni.MYKEYWalletJni;
import com.mykey.sdk.jni.entity.request.JniUserAgentRequest;

import java.util.UUID;

import mykeycore.InitEntity;

/**
 * SDK initialization correlation
 * Created by zero on 2019/5/27.
 */

public class InitHandle {
    public void handle(Context context, InitRequest initRequest) {
        // Store related information
        MemoryManager.set(StoreKeyCons.MEMORY_KEY_APP_KEY, initRequest.getAppKey());
        MemoryManager.set(StoreKeyCons.MEMORY_KEY_USER_ID, initRequest.getUuid().toString());
        MemoryManager.set(StoreKeyCons.MEMORY_KEY_APP_NAME, initRequest.getDappName());
        MemoryManager.set(StoreKeyCons.MEMORY_KEY_APP_ICON, initRequest.getDappIcon());
        MemoryManager.set(StoreKeyCons.MEMORY_KEY_CALLBACK_PAGE, initRequest.getCallback());
        MemoryManager.set(StoreKeyCons.MEMORY_KEY_SHOW_UPGRADE_TIP, initRequest.isShowUpgradeTip());
        MemoryManager.set(StoreKeyCons.MEMORY_KEY_DISABLE_INSTALL, initRequest.isDisableInstall());
        MemoryManager.set(StoreKeyCons.MEMORY_KEY_PROTOCOL, initRequest.getProtocol());
        MemoryManager.set(StoreKeyCons.MEMORY_KEY_PROMPTFREE_CONTRACT, initRequest.isContractPromptFree());

        LogUtil.setLogCallback(initRequest.getLogCallback());

        // Prepare the underlying library to initialize the data
        InitEntity initEntity = new InitEntity();
        initEntity.setAppKey(initRequest.getAppKey());
        initEntity.setUserId(initRequest.getUuid().toString());
        initEntity.setBaseUrl(TextUtils.isEmpty(initRequest.getMYKEYServer()) ? ConfigCons.MYKEY_BASE_URL : initRequest.getMYKEYServer());
        initEntity.setDevice(ConfigCons.DEVICE);
        initEntity.setSdkVersion(ConfigCons.SDK_VERSION);
        initEntity.setUuid(SystemUtil.getUUID());
        initEntity.setDeviceMode(SystemUtil.getModel());
        initEntity.setDeviceOs(SystemUtil.getSystemVersion());

        JniUserAgentRequest userAgentRequest = new JniUserAgentRequest(initEntity);
        initEntity.setUserAgent(userAgentRequest.toString());

        MYKEYWalletJni.init(initEntity);
        ServiceConnectManager.getInstance().init(context);
    }

    public void handle(Context context, InitSimpleRequest initSimpleRequest) {
        String simpleAppKey = null;
        if (TextUtils.isEmpty(initSimpleRequest.getAppKey())) {
            // Generate simple's appkey and userid
            simpleAppKey = MD5Util.getMD5String(ConfigCons.MYKEY_SIMPLE_WALLET_APP_KEY_PREFIX + initSimpleRequest.getDappName());
        } else {
            simpleAppKey = initSimpleRequest.getAppKey();
        }
        String simpleUserId = SystemUtil.getUUID();
        InitRequest initRequest = new InitRequest(initSimpleRequest);
        initRequest.setAppKey(simpleAppKey);
        // if uuid had been set by init simple, then direct set.
        if (null == initSimpleRequest.getUuid()) {
            initRequest.setUuid(new UUID(simpleUserId.hashCode(), simpleUserId.hashCode()));
        } else {
            initRequest.setUuid(initSimpleRequest.getUuid());
        }

        handle(context, initRequest);
    }
}
