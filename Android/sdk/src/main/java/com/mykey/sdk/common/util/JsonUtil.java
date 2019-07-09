package com.mykey.sdk.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by zero on 2019/5/27.
 */

public class JsonUtil {

    public static <T> T fastjsonParse(String json, Class<T> classObj) {
        try {
            return JSONObject.parseObject(json, classObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> fastjsonParseList(String json, Class<T> classObj) {
        try {
            return JSONArray.parseArray(json, classObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> String toJson(T object) {
        try {
            return JSONObject.toJSONString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static JSONObject toJSONObject(String json) {
        try {
            return JSONObject.parseObject(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
