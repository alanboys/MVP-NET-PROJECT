package com.white.bird.ui.activity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.white.bird.R;
import com.white.bird.common.AppConfig;
import com.white.bird.common.CheckStoragePermission;
import com.white.bird.common.base.activity.BaseActivity;
import com.white.bird.utils.StatusBarUtil;
import com.white.bird.utils.ToastUtil;


/**
 * author : ZYK
 * createTime   : 2020/7/15 9:37
 * function   :
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_splash);
        StatusBarUtil.immersive(this);
        if (AppConfig.isPermissions()) {
            toMainActivity();
        } else {
            CheckStoragePermission.verifyStoragePermissions(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != CheckStoragePermission.REQUEST_EXTERNAL_STORAGE) {
                    ToastUtil.showToast("权限申请成功");
                    AppConfig.setPermissions(true);
                    toMainActivity();
                } else {
                    ToastUtil.showToast("权限申请失败");
                    AppConfig.setPermissions(false);
                }
            }
        }
    }

    private void toMainActivity() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);//休眠3秒
                    MainActivity.toActivity(SplashActivity.this);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /**
                 * 要执行的操作
                 */
            }
        }.start();
    }
}
