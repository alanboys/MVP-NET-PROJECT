package com.white.bird.common.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.white.bird.R;
import com.white.bird.common.base.presenter.BasePresenter;
import com.white.bird.common.errorview.ErrorView;
import com.white.bird.common.errorview.RetryListener;

import butterknife.ButterKnife;

/**
 * author : ZYK
 * createTime   : 2020/8/8 9:05
 * function   :
 */

public abstract class BaseMvpFragment<T extends BasePresenter> extends Fragment {
    protected T mPresenter;

    protected View mRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (createPresenter() != null) {
            mPresenter = createPresenter();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initRootView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, mRootView);
        initView();
        initData();
        return mRootView;
    }

    protected abstract void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract void initData();

    protected abstract T createPresenter();

    protected abstract void refreshView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.clear();
        }
    }

    ErrorView emptyView;

    /**
     * 点击会自动刷新
     *
     * @param v
     * @param content
     */
    protected void initEmptyView(SmartRefreshLayout v, String content) {
        emptyView = new ErrorView(getContext());
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        emptyView.setErrorTitle(content);
        emptyView.setErrorImageResource(R.mipmap.item_empty_img);
        emptyView.setOnRetryListener(new RetryListener() {
            @Override
            public void onRetry() {
                emptyView.setVisibility(View.GONE);
                v.finishRefresh(3000);//延迟3000毫秒后结束刷新
            }
        });
    }

    /**
     * 点击会刷新页面
     * @param content
     * @param src
     */
    protected void showEmptyView(RelativeLayout relativeLayout,String content, int src) {

        emptyView = new ErrorView(getContext());
        emptyView.setErrorTitle(content);
        if (src != 0) {
            emptyView.setErrorImageResource(src);
        } else {
            emptyView.setErrorImageResource(R.mipmap.item_empty_img);
        }
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);//与父容器的左侧对齐
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);//与父容器的上侧对齐
        emptyView.setLayoutParams(lp);
        relativeLayout.addView(emptyView);
        showEmptyView(true);
        emptyView.setOnRetryListener(new RetryListener() {
            @Override
            public void onRetry() {
                showEmptyView(false);
                refreshView();
            }
        });
    }

    /**
     * 点击会刷新页面
     * @param content
     * @param src
     */
    protected void showEmptyView(LinearLayout linearLayout, String content, int src) {

        emptyView = new ErrorView(getContext());
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        emptyView.setErrorTitle(content);
        if (src != 0) {
            emptyView.setErrorImageResource(src);
        } else {
            emptyView.setErrorImageResource(R.mipmap.item_empty_img);
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        emptyView.setLayoutParams(lp);
        linearLayout.addView(emptyView);
        emptyView.setOnRetryListener(new RetryListener() {
            @Override
            public void onRetry() {
                showEmptyView(false);
                refreshView();
            }
        });
    }

    protected void showEmptyView(boolean visible) {
        if (emptyView != null) {
            if(visible){
                emptyView.setVisibility(View.VISIBLE);
            }else {
                emptyView.setVisibility(View.GONE);
            }

        }
    }
}
