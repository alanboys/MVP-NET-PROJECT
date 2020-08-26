package com.white.bird.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.white.bird.R;

import java.io.ByteArrayOutputStream;

/**
 * author : ZYK
 * createTime   : 2020/8/10 09:19
 * function   :
 */
public class GlideUtils {


    public static void showImage(Context mContext, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
//                .fitCenter()
                .error(R.drawable.banner_winex)
                .placeholder(R.drawable.banner_default)
                .priority(Priority.HIGH)
//                .centerCrop()
                //.skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(mContext).load(url).apply(options)
                .into(imageView);
    }

    public static void showImage(Context mContext, String url, ImageView imageView, BitmapDrawable drawable) {

        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.ALL);//使用磁盘缓存
        options.placeholder(drawable);   //设置占位符
        Glide.with(mContext)
                .load(url)
                .apply(options)
                .into(imageView);

    }

    public static void showImage(Context mContext, String url, ImageView imageView, CircularProgressDrawable drawable) {

        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.ALL);//使用磁盘缓存
        options.placeholder(drawable);   //设置占位符

        Glide.with(mContext)
                .load(url)
                .apply(options)
                .into(imageView);

    }

    public static void showImage(Context mContext, Uri url, ImageView imageView, int id) {

        RequestOptions options = new RequestOptions();
        options.placeholder(id);   //设置占位符
        options.error(id);   //设置错误符
        Glide.with(mContext)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    public static void showBitmap(Context mContext, ImageView imageView, Bitmap bitmap) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();

        Glide.with(mContext).load(bytes).into(imageView);

    }


    public static void showNoCacheImage(Context mContext, String url, ImageView imageView, int id) {
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.ALL);//使用磁盘缓存
        options.skipMemoryCache(true);//跳过内存缓存
        options.placeholder(id);

        options.error(id);
        Glide.with(mContext)
                .load(url)
                .apply(options)
                .into(imageView);

    }


}
