package com.mykey.sdk.common.store.memory;

import com.mykey.sdk.common.constants.StoreKeyCons;
import com.mykey.sdk.common.util.StringUtil;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Memory Database
 * Created by zero on 2019/5/27.
 */

public class MemoryManager {
    private static final ConcurrentHashMap<String, Object> memoryMap = new ConcurrentHashMap<>();

    public static void set(String key, Object value) {
        memoryMap.put(key, value);
    }

    public static String getAppKey() {
        Object obj = memoryMap.get(StoreKeyCons.MEMORY_KEY_APP_KEY);
        if (null != obj) {
            return obj.toString();
        }
        return "";
    }

    public static String getUserId() {
        Object obj = memoryMap.get(StoreKeyCons.MEMORY_KEY_USER_ID);
        if (null != obj) {
            return obj.toString();
        }
        return "";
    }

    public static String getDAppName() {
        Object obj = memoryMap.get(StoreKeyCons.MEMORY_KEY_APP_NAME);
        if (null != obj) {
            return obj.toString();
        }
        return "";
    }

    public static String getDAppIcon() {
        Object obj = memoryMap.get(StoreKeyCons.MEMORY_KEY_APP_ICON);
        if (null != obj) {
            return obj.toString();
        }
        return "";
    }

    public static String getCallBackPage() {
        Object obj = memoryMap.get(StoreKeyCons.MEMORY_KEY_CALLBACK_PAGE);
        if (null != obj) {
            return obj.toString();
        }
        return "";
    }

    public static boolean isDisableInstall() {
        Object obj = memoryMap.get(StoreKeyCons.MEMORY_KEY_DISABLE_INSTALL);
        if (null != obj) {
            return StringUtil.toBoolean(obj.toString());
        }
        return false;
    }

    public static String getProtocol() {
        Object obj = memoryMap.get(StoreKeyCons.MEMORY_KEY_PROTOCOL);
        if (null != obj) {
            return obj.toString();
        }
        return "";
    }

    public static boolean isShowUpgradeTip() {
        Object obj = memoryMap.get(StoreKeyCons.MEMORY_KEY_SHOW_UPGRADE_TIP);
        if (null != obj) {
            return StringUtil.toBoolean(obj.toString());
        }
        return false;
    }

    public static boolean isContractPromptFree() {
        Object obj = memoryMap.get(StoreKeyCons.MEMORY_KEY_PROMPTFREE_CONTRACT);
        if (null != obj) {
            return StringUtil.toBoolean(obj.toString());
        }
        return false;
    }
}
