package com.white.bird.common;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * author : ZYK
 * createTime   : 2020/7/15 15:20
 * function   :
 */
public class CheckStoragePermission {
    public static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };


    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,PERMISSIONS_STORAGE[1]);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {}
    }

    private static void initPermissions(Context context){
        /**
         //		 * 6.0系统 获取权限
         //		 */
        List<String> list = new ArrayList<>();
        if (PERMISSION_GRANTED != ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)){
            list.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (PERMISSION_GRANTED != ContextCompat.checkSelfPermission(context,Manifest.permission.CAMERA)){
            list.add(Manifest.permission.CAMERA);
        }
        if (PERMISSION_GRANTED != ContextCompat.checkSelfPermission(context,Manifest.permission.CALL_PHONE)){
            list.add(Manifest.permission.CALL_PHONE);
        }
        if (PERMISSION_GRANTED != ContextCompat.checkSelfPermission(context,Manifest.permission.READ_LOGS)){
            list.add(Manifest.permission.READ_LOGS);
        }
        if (PERMISSION_GRANTED != ContextCompat.checkSelfPermission(context,Manifest.permission.READ_PHONE_STATE)){
            list.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (PERMISSION_GRANTED != ContextCompat.checkSelfPermission(context,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            list.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (PERMISSION_GRANTED != ContextCompat.checkSelfPermission(context,Manifest.permission.SET_DEBUG_APP)){
            list.add(Manifest.permission.SET_DEBUG_APP);
        }
        if (PERMISSION_GRANTED != ContextCompat.checkSelfPermission(context,Manifest.permission.SYSTEM_ALERT_WINDOW)){
            list.add(Manifest.permission.SYSTEM_ALERT_WINDOW);
        }
        if (PERMISSION_GRANTED != ContextCompat.checkSelfPermission(context,Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS)){
            list.add(Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS);
        }
        if (list.size()>0) {
            String[] mPermissionList = list.toArray(new String[]{});
            ActivityCompat.requestPermissions((Activity) context, mPermissionList, 100);
        }
    }


}
