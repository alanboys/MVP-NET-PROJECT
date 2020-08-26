package com.white.bird.ui.fragment.homepage;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.white.bird.R;
import com.white.bird.bean.BannerListBean;
import com.white.bird.common.base.fragment.BaseMvpFragment;
import com.white.bird.common.base.presenter.BasePresenter;
import com.white.bird.utils.GlideUtils;
import com.white.bird.widget.ImageOrTextView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;


import java.util.List;

import butterknife.BindView;

/**
 * author : ZYK
 * createTime   : 2020/8/8 10:41
 * function   :
 */
public class HomeFragment extends BaseMvpFragment implements ImageLoaderInterface<ImageView> {

    @BindView(R.id.home_item_view_1)
    ImageOrTextView imageOrTextView1;
    @BindView(R.id.home_item_view_2)
    ImageOrTextView imageOrTextView2;
    @BindView(R.id.home_item_view_3)
    ImageOrTextView imageOrTextView3;
    @BindView(R.id.home_item_view_4)
    ImageOrTextView imageOrTextView4;
    @BindView(R.id.home_item_view_5)
    ImageOrTextView imageOrTextView5;
    @BindView(R.id.home_item_view_6)
    ImageOrTextView imageOrTextView6;
    @BindView(R.id.home_item_view_7)
    ImageOrTextView imageOrTextView7;
    @BindView(R.id.home_item_view_8)
    ImageOrTextView imageOrTextView8;

    @BindView(R.id.b_home_banner)
    Banner homeBanner;
    private List<BannerListBean.DataBean> bannerData;

    @Override
    protected void initView() {
        imageOrTextView1.setImageResource(R.mipmap.home_item_1);
        imageOrTextView2.setImageResource(R.mipmap.home_item_2);
        imageOrTextView3.setImageResource(R.mipmap.home_item_3);
        imageOrTextView4.setImageResource(R.mipmap.home_item_4);
        imageOrTextView5.setImageResource(R.mipmap.home_item_5);
        imageOrTextView6.setImageResource(R.mipmap.home_item_6);
        imageOrTextView7.setImageResource(R.mipmap.home_item_7);
        imageOrTextView8.setImageResource(R.mipmap.home_item_8);

        imageOrTextView1.setTextContent(getResources().getString(R.string.home_item_kc));
        imageOrTextView2.setTextContent(getResources().getString(R.string.home_item_sk));
        imageOrTextView3.setTextContent(getResources().getString(R.string.home_item_zz));
        imageOrTextView4.setTextContent(getResources().getString(R.string.home_item_zb));
        imageOrTextView5.setTextContent(getResources().getString(R.string.home_item_jj));
        imageOrTextView6.setTextContent(getResources().getString(R.string.home_item_sq));
        imageOrTextView7.setTextContent(getResources().getString(R.string.home_item_fx));
        imageOrTextView8.setTextContent(getResources().getString(R.string.home_item_zc));

        setBanners(null);
    }

    /**
     * 设置banner
     */
    public void setBanners(List<BannerListBean.DataBean> data) {
        if (data == null || data.size() == 0) return;
        this.bannerData = data;
        //设置banner样式
        homeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        homeBanner.setImageLoader(this);
        //设置图片集合
        homeBanner.setImages(data);
        //设置banner动画效果
//        bHomeBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        bHomeBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        homeBanner.isAutoPlay(true);
//        bHomeBanner.setBannerTitles(titles);


        //设置轮播时间
        homeBanner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        homeBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        homeBanner.start();
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

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home,null);
    }


    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        final BannerListBean.DataBean object = (BannerListBean.DataBean) path;

        GlideUtils.showImage(getActivity(),object.getUrl(),imageView);
        imageView.setOnClickListener(view -> {
            if (TextUtils.isEmpty(object.getLink())) return;

        });
    }

    @Override
    public ImageView createImageView(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int MAX_WIDTH = dm.widthPixels;

        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setAdjustViewBounds(true);

        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.width = MAX_WIDTH;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        imageView.setLayoutParams(lp);
        imageView.setMaxWidth(MAX_WIDTH);
        imageView.setMinimumHeight(MAX_WIDTH);
        imageView.setMaxHeight(MAX_WIDTH * 2);

        return imageView;
    }
}
