package com.white.bird.common.base.presenter;


import android.content.Context;

import com.white.bird.common.net.BaseSubscriber;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by wxc on 2016/11/28.
 */

public class BasePresenter<V>  {
    private CompositeSubscription mCompositeDisposable;
    //Observable  被订阅者  Subscriber  订阅者
    public void addSubscription(Observable observable, BaseSubscriber subscriber) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeSubscription();
        }
        mCompositeDisposable.add(subscriber);
        observable.subscribeOn(Schedulers.io()) //使用调度器把请求数据切换到子线程
                //请求完数据指向主线程
                .observeOn(AndroidSchedulers.mainThread())
                //订阅
                .subscribe(subscriber);
    }

    //RXjava取消注册，以避免内存泄露
    public void clear() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}