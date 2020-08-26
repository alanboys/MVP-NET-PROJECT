package com.white.bird.ui.presenter;

import android.content.Context;

import com.white.bird.common.base.BaseRequestView;
import com.white.bird.common.base.presenter.BasePresenter;
import com.white.bird.common.net.BaseSubscriber;
import com.white.bird.common.net.NetModelListener;
import com.white.bird.common.net.bean.NetBean;
import com.white.bird.common.service.ServiceFactory;
import com.white.bird.utils.Md5Utils;
import com.white.bird.utils.RequestBodyUtils;
import com.white.bird.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * author : ZYK
 * createTime   : 2020/8/10 09:45
 * function   :
 */
public class HomePresenter extends BasePresenter {

    private Context context;
    private BaseRequestView requestView;

    public HomePresenter(Context context, BaseRequestView view) {
        this.requestView = view;
        this.context = context;
    }


    public void doLogin(String mobile, String password) {
        try {
            JSONObject object = new JSONObject();
            object.put("mobile", mobile);//
            object.put("password", Md5Utils.MD5Encode(password));//
            addSubscription(ServiceFactory.getMineService().doLogin(RequestBodyUtils.getBody("mobile",mobile,"password",Md5Utils.MD5Encode(password))), new BaseSubscriber(context, new NetModelListener<NetBean>() {
                @Override
                public void onSuccess(NetBean result) {
                    requestView.onRequestSuccess(result);
                    requestView.onRequestFinish();
                }

                @Override
                public void onFail(String errorCode, String errorStr) {
                    requestView.onRequestError(errorStr);
                    ToastUtil.showToast(errorStr);
                    requestView.onRequestFinish();
                }
            }));
        } catch (JSONException e) {


        }
    }


}
