package com.white.bird.common.net;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;


import com.white.bird.BuildConfig;
import com.white.bird.common.Constants;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * author : ZYK
 * createTime   : 2020/7/16 10:57
 * function   :
 */

public class NetWorks {


    private static OkHttpClient.Builder okHttpClient;
    private static final int DEFAULT_TIMEOUT = 60;
    private static final int READ_TIMEOUT = 60;
    private static final int WRITE_TIMEOUT = 60;
    private static Retrofit mRetrofit;
    private static NetWorks mNetWorks;


    public static NetWorks getInstance() {
        if (mNetWorks == null) {
            synchronized (NetWorks.class) {
                if (mNetWorks == null) {
                    mNetWorks = new NetWorks();
                }
            }
        }
        return mNetWorks;
    }


    public <T> T createService(Class<T> serviceClazz) {
        return createService(null, serviceClazz);
    }

    public <T> T createService(@Nullable String baseUrl, Class<T> serviceClazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(getOkhttpClient())
                .baseUrl(TextUtils.isEmpty(baseUrl) ? Constants.BaseUrl : baseUrl)
//                .baseUrl(TextUtils.isEmpty(ServiceConfigUtil.getServiceApiUrl()) ? Constants.BaseUrl : ServiceConfigUtil.getServiceApiUrl())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClazz);

    }

    public OkHttpClient getOkhttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder();
            okHttpClient.retryOnConnectionFailure(true);
            okHttpClient.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            okHttpClient.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);//设置读取超时时间
            okHttpClient.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);//设置写的超时时间
//            okHttpClient.addNetworkInterceptor(Header);
            if (BuildConfig.DEBUG) {
                okHttpClient.addInterceptor(loggingIntercept);
            }
        }
        return okHttpClient.build();
    }


//    private final Interceptor Header = chain -> {
//        Request r1 = chain.request();
//        if (TextUtils.isEmpty(AppConfig.getToken())) {
//            return chain.proceed(r1);
//        }
//        Request request = r1.newBuilder().header("token", AppConfig.getToken()).build();
//        return chain.proceed(request);
//    };
    //查看log信息的拦截器
    private final Interceptor loggingIntercept = chain -> {
        Request request = chain.request();
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        Charset UTF8 = Charset.forName("UTF-8");

        String params = "request parames is :  ";
        Log.e("REQUEST_URL", request.toString());
        try {
            FormBody body = (FormBody) request.body();
            if (null == body) {
                params += "empty";
            } else {
                int size = body.size();
                for (int i = 0; i < size; i++) {
                    params += body.encodedName(i) + ":" + body.encodedValue(i) + " ";
                }
            }
        }catch (Exception e){

        }
        Log.e("REQUEST_PARAMS", params);
        Log.e("RESPONSE_JSON", buffer.clone().readString(UTF8));
        Log.e("-------", "----------------------------------------------------");

        return response;
    };
}
