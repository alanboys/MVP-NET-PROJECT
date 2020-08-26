package com.white.bird.common.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.white.bird.R;
import com.white.bird.common.Header;
import com.white.bird.common.base.activity.BaseActivity;
import com.white.bird.common.errorview.ErrorView;
import com.white.bird.common.errorview.RetryListener;
import com.white.bird.utils.ProgressDialogUtil;
import com.white.bird.utils.StatusBarUtil;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrInterface;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * author : ZYK
 * createTime   : 2020/8/8 9:05
 * function   :
 */
public abstract class BaseTitleActivity extends BaseActivity implements RetryListener {
    private Context mContext;
    protected Header mHeader;
    protected View mDivideLine;
    protected View mContentView;
    private Bundle mSavedInstanceState;
    private LinearLayout container;
    private BitmapFactory.Options options = new BitmapFactory.Options();
    public SlidrConfig.Builder mBuilder;
    private SlidrConfig mSlidrConfig;
    private SlidrInterface mSlidrInterface;
    private RelativeLayout headerContainer;
    ErrorView mErrorView;
    Dialog mDialog;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_title_layout);
        mContext = this;
        StatusBarUtil.immersive(this);
        setStatusBar();
        mDialog = ProgressDialogUtil.getProgressDialog(this);
        this.mSavedInstanceState = savedInstanceState;
        options.inJustDecodeBounds = true;
        mErrorView = (ErrorView) findViewById(R.id.view_error);
        mErrorView.setOnRetryListener(this);
        headerContainer = (RelativeLayout) findViewById(R.id.header_container);
        headerContainer.setBackgroundColor(getResources().getColor(R.color.common_title_bg));
        mDivideLine = findViewById(R.id.main_layout_divider);
        initHeader(headerContainer);
        addView();
    }

    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().setStatusBarColor(getResources().getColor(R.color.common_title_bg));//设置状态栏颜色
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |               View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
        }
    }
    private void initHeader(RelativeLayout headerContainer) {
        Header.Builder builder = new Header.Builder(this, headerContainer);
        mHeader = onCreateHeader(builder);
        if (mHeader == null) {
            headerContainer.setVisibility(View.GONE);
            return;
        }
        if (null != mHeader.getBackButton()) {
            mHeader.getBackButton().setOnClickListener(v -> finish());
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            if (mContentView != null) {
                container.removeView(mContentView);
            }
//            addView();
        }
    }

    private void addView() {
        mContentView = onCreateContentView();
        unbinder = ButterKnife.bind(this, mContentView);
        if (mContentView != null) {
            container = (LinearLayout) findViewById(R.id.main_layout_container);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
            lp.weight = 1;
            container.addView(mContentView, lp);
        }
        initView();
        initData();
        initEvents();
    }

    public abstract Header onCreateHeader(Header.Builder builder);

    public abstract View onCreateContentView();

    /**
     * 设置视图对应监听事件
     */
    public void initEvents() {

    }

    public abstract void initView();

    /**
     * 加载当前Activity所需数据
     */
    public abstract void initData();

    /**
     * @param visible
     */
    public void setHeaderLineVisibility(int visible) {
        if (mHeader != null && mHeader.getView() != null) {
            mHeader.getView().setVisibility(visible);
        }
        mDivideLine.setVisibility(visible);
    }

    public Bundle getSavedInstanceState() {
        return this.mSavedInstanceState;
    }


    /**
     * 设置页面为空显示
     *
     * @param resId 图片资源id
     * @param code  提示语资源id
     */
    public void setEmptyView(int resId, String code) {                //, int Width, int Height
        if (resId != 0) {
            mContentView.setVisibility(View.GONE);
            mErrorView.setVisibility(View.VISIBLE);
            mErrorView.setErrorImageResource(resId);
        }
        mErrorView.setErrorTitle(code);
    }

    public void setEmptyView(String code) {
        mContentView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
        mErrorView.setErrorTitle(code);
    }


    public void setHideEmptyView() {
        mContentView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
    }


    public void hideDivideLine() {
        if (mDivideLine != null && mDivideLine.getVisibility() != View.GONE)
            mDivideLine.setVisibility(View.GONE);
    }

    public void showDialog() {
        runOnUiThread(() -> ProgressDialogUtil.showProgressDialog(mDialog));
    }

    public void disMissDialog() {
        runOnUiThread(() -> ProgressDialogUtil.closeProgressDialog(mDialog));
    }


    /**
     * 获取内容区域父view
     *
     * @return
     */
    public LinearLayout getContentView() {
        return container;
    }

    public String getStringResources(int resources) {
        return this.getResources().getString(resources);
    }

    public int getColorResources(int resources) {
        return this.getResources().getColor(resources);
    }

    /**
     *  获取布局文件
     * @param layout
     * @return
     */
    public View getLayoutView( int layout){
        return LayoutInflater.from(mContext).inflate(layout,null);
    }

    /**
     * 错误页面点击回调
     */
    @Override
    public void onRetry() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}


