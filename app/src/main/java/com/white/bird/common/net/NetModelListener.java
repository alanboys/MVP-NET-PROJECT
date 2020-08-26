package com.white.bird.common.net;

/**
 * author : ZYK
 * createTime   : 2020/7/16 10:57
 * function   :
 */

public interface NetModelListener<T>{
    public void onSuccess(T result);

    public void onFail(String errorCode, String errorStr);
}
