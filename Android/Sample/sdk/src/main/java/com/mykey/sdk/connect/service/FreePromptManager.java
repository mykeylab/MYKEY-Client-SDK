package com.mykey.sdk.connect.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zero on 2019/7/29.
 */

public class FreePromptManager {

    private static ConcurrentHashMap<String, Boolean> freePromptMap = new ConcurrentHashMap<>();

    public static void addFreePrompt(List<String> freePromptKeyList) {
        if (null == freePromptKeyList || freePromptKeyList.size() == 0) {
            return;
        }
        for (String freePromptKey : freePromptKeyList) {
            freePromptMap.put(freePromptKey, true);
        }
    }

    public static boolean isFreePrompt(String freePromptKey) {
        Boolean isFree = freePromptMap.get(freePromptKey);
        return null == isFree ? false : isFree;
    }

    public static void clearFreePrompt() {
        freePromptMap.clear();
    }
}
