package com.white.bird.common.base.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

/**
 * author : ZYK
 * createTime   : 2020/7/15 17:37
 * function   :
 */
public class BaseActivity extends AppCompatActivity {

    private InnerReceiver mReceiver;// 接收者

    private IntentFilter mFilter;// 过滤器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegister();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        //友盟统计写在此处
    }

    /**
     * create by moe
     * time 2017/03/15
     * description:注册广播
     */
    protected void register(String... actions) {
        if (null == mReceiver)
            mReceiver = new InnerReceiver();

        if (null == mFilter)
            mFilter = new IntentFilter();

        for (String action : actions) {
            if (!TextUtils.isEmpty(action)) {
                mFilter.addAction(action);
            }
        }

        registerReceiver(mReceiver, mFilter);

    }

    protected void unRegister() {
        if (null != mReceiver) {
            unregisterReceiver(mReceiver);
        }
    }


    /**
     * description:广播接收类
     */
    final class InnerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            handleReceiver(context, intent);
        }
    }

    protected void handleReceiver(Context context, Intent intent) {
    }


}

