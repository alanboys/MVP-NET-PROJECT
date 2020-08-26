package com.white.bird.common.service;

import com.white.bird.common.net.bean.NetBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * author : ZYK
 * createTime   : 2020/8/25 10:05
 * function   :
 */
public interface MineService {

    @POST("eop/member/login")
    Observable<NetBean> doLogin(@Body RequestBody requestBody);
}
