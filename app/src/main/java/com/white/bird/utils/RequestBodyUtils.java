package com.white.bird.utils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author : ZYK
 * createTime   : 2020/8/26 12:29
 * function   :
 */
public class RequestBodyUtils {
    public static RequestBody getBody(String jsonString) {
        LogUtil.e(jsonString.toString());
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
    }

    public static RequestBody getBody(String... jsonString) {
        JSONObject object = new JSONObject();
        try {
            for (int i = 0; i < jsonString.length / 2; i++) {
                object.put(jsonString[2 * i], jsonString[2 * i + 1]);//
            }
            LogUtil.e(object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), object.toString());
    }
}
