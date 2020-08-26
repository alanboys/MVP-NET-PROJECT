package com.white.bird.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import com.white.bird.widget.BaseProgressDialog;


/**
 * author : ZYK
 * createTime   : 2020/8/8 09:20
 * function   :
 */
public class ProgressDialogUtil {


    public static ProgressDialog getProgressDialog (String title, Context context, Boolean isCancel) {
        BaseProgressDialog dialog = BaseProgressDialog.getDialogInstance(context);
        dialog.setCancelable(isCancel);
        dialog.setTips(title);
        return dialog;
    }

    public static ProgressDialog getProgressDialog (Context context) {
        return getProgressDialog(context, "");//加载中...
    }

    public static ProgressDialog getProgressDialog (Context context, String text) {
        return getProgressDialog(text, context, true);
    }

    public static void closeProgressDialog (Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static void showProgressDialog (Dialog dialog) {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }
}
