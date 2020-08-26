package com.white.bird.ui.fragment.homepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.white.bird.R;
import com.white.bird.common.base.fragment.BaseMvpFragment;
import com.white.bird.common.base.presenter.BasePresenter;
import com.white.bird.utils.ToastUtil;

import butterknife.BindView;

/**
 * author : ZYK
 * createTime   : 2020/8/8 10:42
 * function   :
 */
public class GameFragment extends BaseMvpFragment {

    @BindView(R.id.rel_view)
    RelativeLayout relView;

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_game,null);
    }

    @Override
    protected void initView() {
        showEmptyView(relView,"暂时没有数据哦",0);
    }

    @Override
    protected void initData() {

    }

    public void getData(){
        ToastUtil.showToast("正在刷新");
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void refreshView() {
        getData();
    }

}
