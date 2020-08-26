package com.white.bird.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.white.bird.R;


/**
 * author : ZYK
 * createTime   : 2020/8/8 09:20
 * function   :
 */
public class BaseDialogView extends Dialog {

    private static String ed_setr;
    private View CenterView;
    private View.OnClickListener okOnClickListener = null;
    private View.OnClickListener cancelOnClickListener = null;
    private String title = "提示";
    private String content;
    private SpannableStringBuilder builder;

    public SpannableStringBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(SpannableStringBuilder builder) {
        this.builder = builder;
    }

    public String getContent() {
        return content;
    }

    public BaseDialogView setContent(String content) {
        this.content = content;
        return this;
    }

    private String okName = "确定";
    private String canceName = "取消";
    private boolean showTitle = true;
    private boolean showCancel = true;

    private static Button btn_firm;
    private static TextView nexrTime;
    private static LinearLayout closeLayout;
    private static LinearLayout goneLayout;
    boolean time;


    public BaseDialogView(Context context) {
        super(context, R.style.MyDialog);
    }

//    public BaseDialogView(Context context, boolean time) {
//        super(context, R.style.MyDialog);
//        this.time = time;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_basedialogview);
        goneLayout = (LinearLayout) findViewById(R.id.relayout_goneview);
//        if (time) {
//            closeSet();
//        }
        // 实例化控件
        Button btn_confir = (Button) findViewById(R.id.btn_confir);
        Button btn_cancel = (Button) findViewById(R.id.btn_cancel);
//        View btn_space = (View) findViewById(R.id.btn_pace);
        TextView textv_title = (TextView) findViewById(R.id.textv_title);
        TextView textv_content = (TextView) findViewById(R.id.textv_content);
        ImageView iv_blueview = (ImageView) findViewById(R.id.iv_blueview);
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.relayout_addview);
        if (getCenterView() != null) {
            textv_content.setVisibility(View.GONE);
            mainLayout.addView(getCenterView());

        } else {
            textv_content.setVisibility(View.VISIBLE);

            if (getBuilder() != null) {
                textv_content.setText(getBuilder());
            } else {
                textv_content.setText(getContent());
            }
            //   textv_content.setText(getBuilder());
        }
        // 设置标题
        textv_title.setText(getTitle());
        btn_confir.setText(getOkName());
        btn_cancel.setText(getCanceName());
        showTitle(textv_title, iv_blueview);
        if (!showCancel) {
            btn_cancel.setVisibility(View.GONE);
//            btn_space.setVisibility(View.GONE);
        }
        // 添加要引用的布局
        setClickListener(btn_confir, btn_cancel);
    }

    /**
     * 给确认取消添加点击事件
     *
     * @param ok
     * @param cancel
     */
    private void setClickListener(View ok, View cancel) {
        if (getOkOnClickListener() != null) {
            ok.setOnClickListener(getOkOnClickListener());
            this.dismiss();
        } else {
            ok.setOnClickListener(arg0 -> BaseDialogView.this.dismiss());
        }
        if (getCancelOnClickListener() != null) {
            cancel.setOnClickListener(getCancelOnClickListener());
            this.dismiss();
        } else {
            cancel.setOnClickListener(arg0 -> BaseDialogView.this.dismiss());
        }
    }

    private void showTitle(View view1, View view2) {
        if (isShowTitle()) {
            if (view1.getVisibility() != View.VISIBLE) {
                view1.setVisibility(View.VISIBLE);
            }
            if (view2.getVisibility() != View.VISIBLE) {
                view2.setVisibility(View.VISIBLE);
            }
        } else {
            if (view1.getVisibility() != View.GONE) {
                view1.setVisibility(View.GONE);
            }
            if (view2.getVisibility() != View.GONE) {
                view2.setVisibility(View.GONE);
            }
        }
        view2.setVisibility(View.GONE);
    }

    public boolean isShowTitle() {
        return showTitle;
    }

    public BaseDialogView setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BaseDialogView setTitle(String title) {
        this.title = title;
        return this;
    }

    public View getCenterView() {
        return CenterView;
    }

    public BaseDialogView setCenterView(View centerView) {
        CenterView = centerView;
        return this;
    }

    public View.OnClickListener getOkOnClickListener() {
        return okOnClickListener;
    }

    public BaseDialogView setOkOnClickListener(View.OnClickListener okOnClickListener) {
        this.okOnClickListener = okOnClickListener;
        return this;
    }

    public View.OnClickListener getCancelOnClickListener() {
        return cancelOnClickListener;
    }

    public BaseDialogView setCancelOnClickListener(View.OnClickListener cancelOnClickListener) {
        this.cancelOnClickListener = cancelOnClickListener;
        return this;
    }

//    public void closeSet() {
//        closeLayout.setVisibility(View.VISIBLE);
//        goneLayout.setVisibility(View.GONE);
//        nexrTime.setText(AppConfig.getStartTime());
//        btn_firm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BaseDialogView.this.dismiss();
//            }
//        });
//
//    }

    public String getOkName() {
        return okName;
    }

    public BaseDialogView setOkName(String okName) {
        this.okName = okName;
        return this;
    }

    public String getCanceName() {
        return canceName;
    }

    public BaseDialogView setCanceName(String canceName) {
        this.canceName = canceName;
        return this;
    }


    public boolean isShowCancel() {
        return showCancel;
    }

    public BaseDialogView setShowCancel(boolean showCancel) {
        this.showCancel = showCancel;
        return this;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        dailog = null;
    }

    private static BaseDialogView dailog;

    public static BaseDialogView getDailogInstance(Context context, View v) {
        dailog = new BaseDialogView(context);
        v.setPadding(10, 30, 10, 30);
        dailog.setCenterView(v);
        return dailog;
    }

//    public static BaseDialogView getDailogInstance(Context context, String content) {
//        dailog = new BaseDialogView(context);
//        View tv_view = LayoutInflater.from(context).inflate(R.layout.dialog_textview, null);
//        dailog.setCenterView(tv_view);
//        TextView tv = (TextView) tv_view.findViewById(R.id.text_view);
//        tv.setText(content);
//        return dailog;
//    }
//
//
//    public static BaseDialogView getDailogInstance(Context context, String content,boolean isCenter) {
//        dailog = new BaseDialogView(context);
//        View tv_view = LayoutInflater.from(context).inflate(R.layout.dialog_textview, null);
//        dailog.setCenterView(tv_view);
//        TextView tv = (TextView) tv_view.findViewById(R.id.text_view);
//        if(isCenter) {
//            tv.setGravity(Gravity.CENTER);
//        }
//        tv.setText(content);
//        return dailog;
//    }


    public static BaseDialogView getDailogInstance(Context context) {
        dailog = new BaseDialogView(context);
        return dailog;
    }

//    public static BaseDialogView getDailogInstance(Context context, boolean time) {
//        dailog = new BaseDialogView(context, time);
//        return dailog;
//    }
}