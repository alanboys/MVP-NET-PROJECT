package com.white.bird.ui.fragment.homepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.white.bird.R;
import com.white.bird.common.base.fragment.BaseMvpFragment;
import com.white.bird.common.base.presenter.BasePresenter;

/**
 * author : ZYK
 * createTime   : 2020/8/8 10:41
 * function   :
 */
public class MineFragment extends BaseMvpFragment {

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_mine,null);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void refreshView() {

    }
}
