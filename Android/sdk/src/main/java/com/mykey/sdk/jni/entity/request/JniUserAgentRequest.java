package com.mykey.sdk.jni.entity.request;

import com.mykey.sdk.common.constants.RequestHeaderCons;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mykeycore.InitEntity;

/**
 * Created by zero on 2019/6/4.
 */

public class JniUserAgentRequest {

    private HashMap<String, String> headerMap = new HashMap<>();

    public JniUserAgentRequest(InitEntity initEntity) {
        headerMap.put(RequestHeaderCons.HEADER_KEY_DEVICE, initEntity.getDevice());
        headerMap.put(RequestHeaderCons.HEADER_KEY_DEVICE_MODEL, initEntity.getDeviceMode());
        headerMap.put(RequestHeaderCons.HEADER_KEY_DEVICE_OS, initEntity.getDeviceOs());
        headerMap.put(RequestHeaderCons.HEADER_KEY_UUID, initEntity.getUuid());
        headerMap.put(RequestHeaderCons.HEADER_KEY_VERSION, initEntity.getSdkVersion());
    }

    public String toString() {
        if (headerMap.size() == 0) {
            return "";
        }
        Iterator<Map.Entry<String, String>> it = headerMap.entrySet().iterator();
        StringBuilder stringBuffer = new StringBuilder();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            stringBuffer.append(entry.getKey()).append(":").append(entry.getValue()).append(";");
        }
        return stringBuffer.toString();
    }
}
