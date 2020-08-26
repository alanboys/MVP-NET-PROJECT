package com.white.bird.common.base;


/**
 * Created by wxc on 2016/11/29.
 */

public interface BaseRequestView<T> extends BaseView {
    void onRequestError(String errorMsg);

    void onRequestSuccess(T obj);

    void onRequestFinish();
}
