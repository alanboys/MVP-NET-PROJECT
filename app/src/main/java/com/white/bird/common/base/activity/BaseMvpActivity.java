package com.white.bird.common.base.activity;

import android.os.Bundle;

import com.white.bird.common.base.presenter.BasePresenter;
import com.white.bird.common.base.BaseTitleActivity;
import com.white.bird.utils.ToastUtil;


/**
 * Created by jjn on 2016/11/29.
 * <p>
 * 描述:
 * <p>
 * 邮箱:jjn1991013@163.com
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseTitleActivity {
    protected T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (createPresenter() != null) {
            mPresenter = createPresenter();
        }
        super.onCreate(savedInstanceState);
    }

    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.clear();
        }
    }

    public void showToast(String massage){
        ToastUtil.showToast(massage);
    }
}
