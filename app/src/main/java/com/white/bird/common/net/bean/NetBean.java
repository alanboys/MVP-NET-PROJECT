package com.white.bird.common.net.bean;

import com.google.gson.Gson;

/**
 * author : ZYK
 * createTime   : 2020/8/25 10:17
 * function   :
 */
public class NetBean<T> {


    String code = "";
    String message ;
    boolean success;
    T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
