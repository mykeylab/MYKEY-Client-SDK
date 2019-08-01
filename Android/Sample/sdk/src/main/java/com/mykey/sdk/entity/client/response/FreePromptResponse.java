package com.mykey.sdk.entity.client.response;

import java.util.List;

/**
 * Created by zero on 2019/7/29.
 */

public class FreePromptResponse {
    private List<String> freePromptKeyList;

    public List<String> getFreePromptKeyList() {
        return freePromptKeyList;
    }

    public void setFreePromptKeyList(List<String> freePromptKeyList) {
        this.freePromptKeyList = freePromptKeyList;
    }
}
