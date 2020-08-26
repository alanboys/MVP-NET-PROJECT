package com.white.bird.common.net;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.white.bird.common.BaseApplication;
import com.white.bird.common.net.bean.NetBean;
import com.white.bird.utils.NetStateUtil;
import com.white.bird.utils.ProgressDialogUtil;

import rx.Subscriber;


/**
 * author : ZYK
 * createTime   : 2020/7/16 10:57
 * function   :
 */

public class BaseSubscriber<T> extends Subscriber<T> {
    private Context mContext;
    private Dialog mDialog;
    private NetModelListener netModelListener;

    /**
     * 不显示加载dialog
     *
     * @param modelListener
     */
    public BaseSubscriber(NetModelListener<T> modelListener) {
        this.netModelListener = modelListener;
    }

    /**
     * 显示加载dialog
     *
     * @param context
     * @param modelListener
     */
    public BaseSubscriber(Context context, NetModelListener<T> modelListener) {
        mContext = context;
        this.netModelListener = modelListener;
        mDialog = ProgressDialogUtil.getProgressDialog(context);
    }


    @Override
    public void onStart() {
        super.onStart();
        //本地检查网络状态
        if (!NetStateUtil.isConnected(BaseApplication.getContext())) {
            Toast.makeText(BaseApplication.getContext(), "网络请求失败，请检查网络设置", Toast.LENGTH_SHORT).show();
        }
        if (mContext != null) showDialog();
    }

    @Override
    public void onCompleted() {
        if (mContext != null) dissMissDialog();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (mContext != null) dissMissDialog();
        if (netModelListener != null) {
            netModelListener.onFail("", "网络请求失败,请稍候重试");
        }
    }

    @Override
    public void onNext(T t) {
        responseResult((NetBean) t);
    }


    private <T> void responseResult(NetBean<T> result) {
        if (result != null) {
            String message = result.getMessage();
            String code = result.getCode();
            if ("2001".equals(code)) {
                netModelListener.onFail(code, "未登录,请先登录");
                userInfoTimeOut();
            } else if ("2002".equals(code)) {
                netModelListener.onFail(code, "登录信息已过期,请重新登录");
                userInfoTimeOut();
            } else if ("00000".equals(code) || "".equals(code)) {
                netModelListener.onSuccess(result);
            } else {
                netModelListener.onFail(code, message);
            }
        }
    }

    //
    private void userInfoTimeOut() {//清除数据 跳到登录页
//        AppConfig.removeUserInfo();
//        Intent intentLogin = new Intent(BaseApplication.getmContext(), LoginActvity.class);
//        intentLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        BaseApplication.getmContext().startActivity(intentLogin);
    }

    public void showDialog() {
        ((Activity) mContext).runOnUiThread(() -> ProgressDialogUtil.showProgressDialog(mDialog));
    }

    public void dissMissDialog() {
        ((Activity) mContext).runOnUiThread(() -> ProgressDialogUtil.closeProgressDialog(mDialog));
    }
}
