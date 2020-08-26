package com.white.bird.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.white.bird.common.BaseApplication;

/**
 * author : ZYK
 * createTime   : 2020/8/25 10:32
 * function   :
 */
public class NetWorkUtils {

    /**
     * 获取网络信息
     * @return
     */
    public static boolean isNetworkAvailable() {

        ConnectivityManager cm = (ConnectivityManager) BaseApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info !=null){
            return info.isAvailable();
        }

        return false;
    }
    /**
     *
     * 获取网络类型
     *
     */
    public static int getNetType() {

        ConnectivityManager cm = (ConnectivityManager) BaseApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            return -1;
        } else {
            return info.getType();
        }
    }

}
