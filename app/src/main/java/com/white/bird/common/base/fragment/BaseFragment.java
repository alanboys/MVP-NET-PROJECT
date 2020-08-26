package com.white.bird.common.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.white.bird.R;
import com.white.bird.common.Header;
import com.white.bird.common.errorview.ErrorView;
import com.white.bird.common.errorview.RetryListener;
import com.white.bird.ui.activity.MainActivity;

/**
 * Created by wxc on 2016/12/1.
 */

public abstract class BaseFragment extends Fragment implements RetryListener {

    private final String TAG = this.getClass().getSimpleName();

    protected Header mHeader;

    protected View mContentView;

    ErrorView mErrorView;

//    private IssLoadingDialog issLoadingDialog;

    private boolean cancelAble = true;

    /**
     * 无数据显示view
     */
    public View mNoDataView;

    protected RelativeLayout headerContainer;

    public BaseFragment() {

    }

    public Context getContext() {
        return getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.base_title_layout, container, false);
        headerContainer = (RelativeLayout) rootView.findViewById(R.id.header_container);
        mErrorView = (ErrorView) rootView.findViewById(R.id.view_error);
        mErrorView.setOnRetryListener(this);
//        mNoDataView = rootView.findViewById(R.id.rl_base_empty);
        initHeader(headerContainer);
        mContentView = onCreateContentView();
        if (mContentView != null) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
            lp.weight = 1;
            ((LinearLayout) rootView).addView(mContentView, lp);
        }
        return rootView;
    }

    private void initHeader(RelativeLayout headerContainer) {
        Header.Builder builder = new Header.Builder(getActivity(), headerContainer);
        mHeader = onCreateHeader(builder);
        if (mHeader == null) {
            headerContainer.setVisibility(View.GONE);
            return;
        }
        if (null != mHeader.getBackButton() && !(getActivity() instanceof MainActivity)) {
            mHeader.getBackButton().setOnClickListener(v -> getActivity().finish());
        }
    }

    public void onShow() {

    }

    public void onUserLeaveHint() {

    }

    public void onNewIntent(Intent it) {


    }

    /**
     * 指定对象 活动，群组或者通讯录对应id消息数
     *
     * @param feedId
     */
    public void setMsgToFeedId(String feedId, String cardFeedId) {

    }

    /**
     * 自定义标头
     *
     * @param builder
     * @return
     */
    protected abstract Header onCreateHeader(Header.Builder builder);

    protected abstract View onCreateContentView();

    public void setHeaderVisibility(int visible) {
        mHeader.getView().setVisibility(visible);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (getActivity() instanceof BaseFragmentActivity) {
//
//            if (((BaseFragmentActivity) getActivity()).getCurrentFragment() == this) {
//                onShow();
//            }
//        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        mContentView = null;
        mHeader = null;
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 显示加载数据进度条
     */
//    public void showLoadingDialog(boolean cancelable) {
//        if (issLoadingDialog == null) {
//            cancelAble = cancelable;
//            issLoadingDialog = new IssLoadingDialog(getActivity());
//        } else {
//            if (issLoadingDialog.isShowing()) {
//                issLoadingDialog.dismiss();
//            }
//        }
//        issLoadingDialog.setCancelable(cancelAble);
//        issLoadingDialog.show();
//    }
    @Override
    public void onRetry() {

    }

    /**
     * 取消正在显示的dialog
     */
//    public void cancelLoadingDialog() {
//        cancelAble = true;
//        if (issLoadingDialog != null && issLoadingDialog.isShowing()) {
//            issLoadingDialog.dismiss();
//        }
//    }

    /**
     * 设置页面为空显示
     *
     * @param resId 图片资源id
     * @param code  提示语资源id
     */
    public void setNoDataView(int resId, String code, int outWidth, int outHeight) {
        if (resId != 0 && mNoDataView != null) {
//            ImageView imageView = (ImageView) mNoDataView.findViewById(R.id.iv_base_empty_bg);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.gravity = Gravity.CENTER_HORIZONTAL;
//            imageView.setLayoutParams(params);
//            ImageLoader.getInstance().displayImage("drawable://" + resId, imageView);
//            TextView textView = (TextView) mNoDataView.findViewById(R.id.tv_base_empty_bg);
//            textView.setText();
            if (mContentView != null) {
                mContentView.setVisibility(View.GONE);
            }
            if (mNoDataView != null) {
                mNoDataView.setVisibility(View.VISIBLE);
            }
        }
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

    /**
     * 切换显示页面
     */
    public void setDataView() {
        if (mNoDataView != null) {
            mNoDataView.setVisibility(View.GONE);
        }
        if (mContentView != null) {
            mContentView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * fragment切换的方法
     *
     * @param contentLayoutId
     * @param fragment
     */
    public void switchFragment(int contentLayoutId, BaseFragment fragment) {
        switchFragment(contentLayoutId, fragment, null, true);
    }

    public void switchFragment(int contentLayoutId, BaseFragment fragment,
                               Bundle bundle) {
        switchFragment(contentLayoutId, fragment, bundle, true);
    }

    public void switchFragment(int contentLayoutId, BaseFragment fragment,
                               boolean hasAnim) {
        switchFragment(contentLayoutId, fragment, null, hasAnim);
    }

    public void switchFragment(int contentLayoutId, BaseFragment fragment,
                               Bundle bundle, boolean hasAnim) {
        if (fragment == null) {
            return;
        }
        //  FragmentManager fragmentManager = getChildFragmentManager();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        if (!fragment.isAdded()) {
            fragment.setArguments(bundle);
        }
        fragmentTransaction.replace(contentLayoutId, fragment,
                fragment.getClass().getSimpleName()).commit();
    }

}