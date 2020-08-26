package com.white.bird.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * author : ZYK
 * createTime   : 2020/7/15 19:30
 * function   :
 */
public class AppConfig {
    public static final String phone = "4006315102";
    private static SharedPreferences preferences;

    private static final String SWITCH_SPEAKER = "switchSpeaker";


    static {
        preferences = BaseApplication.getContext().getSharedPreferences(
                "user", Context.MODE_PRIVATE);
//        mypasswords = BaseApplication.getInstance().getSharedPreferences("Mypassword",
//                Context.MODE_PRIVATE);

    }

    public static SharedPreferences getInstance() {
        return preferences;
    }



    //登录状态
    public static void setPermissions(Boolean isPermissions) {
        preferences.edit().putBoolean("isPermissions", isPermissions).apply();
    }

    public static boolean isPermissions() {
        return preferences.getBoolean("isPermissions", false);
    }

}
