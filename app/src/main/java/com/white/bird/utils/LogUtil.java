package com.white.bird.utils;

import android.util.Log;

import com.white.bird.BuildConfig;


/**
 * author : ZYK
 * createTime   : 2020/8/8 09:20
 * function   :
 */
public class LogUtil {

    public static void e(String msg) {
//        if (BuildConfig.DEBUG)
            Log.e(getAutoJumpLogInfos(),  "TAG == " + msg);
    }

    public static void d(String msg) {
        if (BuildConfig.DEBUG)
            Log.d(getAutoJumpLogInfos(), msg);
    }

    public static void i(String msg) {
        if (BuildConfig.DEBUG)
            Log.i(getAutoJumpLogInfos(), msg);
    }

    public static void v(String msg) {
        if (BuildConfig.DEBUG)
            Log.v(getAutoJumpLogInfos(), msg);
    }

    /**
     * 获取打印信息所在方法名，行号等信息
     *
     * @return
     */
    private static String getAutoJumpLogInfos() {
        String[] infos = new String[]{"", "", ""};
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        infos[0] = "类：" + elements[4].getClassName().substring(
                elements[4].getClassName().lastIndexOf(".") + 1) + "-->方法：";
        infos[1] = elements[4].getMethodName() + "()-->";
        infos[2] = "（" + elements[4].getLineNumber() + ":行）";
        return infos[0] + infos[1] + infos[2];
    }
}
