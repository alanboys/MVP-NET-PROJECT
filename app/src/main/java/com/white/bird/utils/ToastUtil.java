package com.white.bird.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * CREATE BY ZYK ON 2020/8/8 09:20
 * param：toast 提示
 * ToastUtil.getInstance().Short(this,"").show();
 * 完全自定义显示方式
 * new ToastUtil(MainActivity.this,view,Toast.LENGTH_SHORT).show();
 */

public class ToastUtil {

    private static Context mContext;
    private static ToastUtil myToast;
    private static Toast toast;
    private static LinearLayout toastView;


    public static void getInstance(Context context) {
        mContext = context;
    }

    /**
     * 完全自定义布局Toast
     *
     * @param context
     */
    public ToastUtil(Context context) {
        toast = new Toast(context);
    }

    /**
     * 完全自定义布局Toast
     *
     * @param context
     * @param view
     */
    public ToastUtil(Context context, View view, int duration) {
        toast = new Toast(context);
        toast.setView(view);
        toast.setDuration(duration);
    }

    /**
     * 向Toast中添加自定义view
     *
     * @param view
     * @param postion
     * @return
     */
    public ToastUtil addView(View view, int postion) {
        toastView = (LinearLayout) toast.getView();
        toastView.addView(view, postion);

        return this;
    }

    /**
     * 设置Toast字体及背景颜色
     *
     * @param messageColor
     * @param backgroundColor
     * @return
     */
    public ToastUtil setToastColor(int messageColor, int backgroundColor) {
        View view = toast.getView();
        if (view != null) {
            TextView message = ((TextView) view.findViewById(android.R.id.message));
            message.setBackgroundColor(backgroundColor);
            message.setTextColor(messageColor);
        }
        return this;
    }

    /**
     * 设置Toast字体及背景
     *
     * @param messageColor
     * @param background
     * @return
     */
    public ToastUtil setToastBackground(int messageColor, int background) {
        View view = toast.getView();
        if (view != null) {
            TextView message = ((TextView) view.findViewById(android.R.id.message));
            message.setBackgroundResource(background);
            message.setTextColor(messageColor);
        }
        return this;
    }

    /**
     * 短时间显示Toast
     */
    public ToastUtil showShortToast(int message) {
        if (toast == null || (toastView != null && toastView.getChildCount() > 1)) {
            toast = Toast.makeText(mContext, message + "", Toast.LENGTH_SHORT);
            toastView = null;
        } else {
            toast.setText(message + "");
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        return this;
    }


    public static void showToast(CharSequence message) {
        if (toast == null) {
            toast = Toast.makeText(mContext, message, Toast.LENGTH_LONG);
        }
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
