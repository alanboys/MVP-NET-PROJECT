package com.white.bird.common;

import android.app.Application;
import android.content.Context;

import com.white.bird.utils.ToastUtil;


/**
 * author : ZYK
 * createTime   : 2020/7/15 19:06
 * function   :
 */
public class BaseApplication extends Application {
    private static Context mContext;//全局变量

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        ToastUtil.getInstance(mContext);
    }

    public static Context getContext() {
        return mContext;
    }

}
