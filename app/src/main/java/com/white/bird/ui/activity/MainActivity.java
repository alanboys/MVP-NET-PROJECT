package com.white.bird.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.white.bird.R;
import com.white.bird.common.Constants;
import com.white.bird.common.Header;
import com.white.bird.common.base.BaseRequestView;
import com.white.bird.common.base.BaseTitleActivity;
import com.white.bird.common.base.activity.BaseMvpActivity;
import com.white.bird.common.base.fragment.BaseMvpFragment;
import com.white.bird.ui.adapter.home.HomeTabPageAdapter;
import com.white.bird.ui.fragment.homepage.GameFragment;
import com.white.bird.ui.fragment.homepage.HomeFragment;
import com.white.bird.ui.fragment.homepage.MineFragment;
import com.white.bird.ui.fragment.homepage.TranFragment;
import com.white.bird.ui.presenter.HomePresenter;
import com.white.bird.utils.ToastUtil;
import com.white.bird.widget.viewpager.NoScrollViewPager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<HomePresenter> implements BottomNavigationBar.OnTabSelectedListener, BaseRequestView {

    public static void toActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.bnb_home_bottom_bar)
    BottomNavigationBar bnbHomeBottomBar;

    @BindView(R.id.nsvp_home_page)
    NoScrollViewPager tabHomePage;

    @Override
    public Header onCreateHeader(Header.Builder builder) {
//        return builder.backViewIsVisibility(false).setTitle(getStringResources(R.string.app_name), R.color.default_text_color).build();
        return null;
    }

    @Override
    public View onCreateContentView() {
        return getLayoutView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        bnbHomeBottomBar.setMode(BottomNavigationBar.MODE_FIXED);
        bnbHomeBottomBar.setActiveColor(R.color.color_tab_sel)
                .setInActiveColor(R.color.color_tab)
                .setBarBackgroundColor(R.color.white);

        bnbHomeBottomBar
                .addItem(new BottomNavigationItem(R.mipmap.tab_home_icon_sel,
                        getStringResources(R.string.home_page))
                        .setInactiveIconResource(R.mipmap.tab_home_icon))
                .addItem(new BottomNavigationItem(R.mipmap.tab_trad_icon_sel,
                        getStringResources(R.string.transaction_page))
                        .setInactiveIconResource(R.mipmap.tab_trad_icon))
                .addItem(new BottomNavigationItem(R.mipmap.tab_asset_icon_sel,
                        getStringResources(R.string.game_page))
                        .setInactiveIconResource(R.mipmap.tab_asset_icon))
                .addItem(new BottomNavigationItem(R.mipmap.tab_mine_icon_sel,
                        getStringResources(R.string.mine_page))
                        .setInactiveIconResource(R.mipmap.tab_mine_icon))
                .initialise();

        setBottomNavigationItem(bnbHomeBottomBar, 6, 42, 10);

        bnbHomeBottomBar.setTabSelectedListener(this);
        initFragment();
    }

    private void initFragment() {
        List<BaseMvpFragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TranFragment());
        fragments.add(new GameFragment());
        fragments.add(new MineFragment());

        tabHomePage.setOffscreenPageLimit(4);
        tabHomePage.setAdapter(new HomeTabPageAdapter(getSupportFragmentManager(), fragments));

        int intExtra = getIntent().getIntExtra(Constants.DARK_LIGHT_SWITCH, 0);
        if (intExtra == 1) {
            bnbHomeBottomBar.selectTab(0);
        }
    }

    @Override
    public void initData() {
        mPresenter.doLogin("19903877915","zyk123456");
    }

    @Override
    public void onTabSelected(int position) {
        System.out.println(position);
        //未选中->选中
        tabHomePage.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {
        //选中->未选中
    }

    @Override
    public void onTabReselected(int position) {

    }


    private void setBottomNavigationItem(BottomNavigationBar bottomNavigationBar, int space, int imgLen, int textSize) {
        Class barClass = bottomNavigationBar.getClass();
        Field[] fields = barClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            if (field.getName().equals("mTabContainer")) {
                try {
                    //反射得到 mTabContainer
                    LinearLayout mTabContainer = (LinearLayout) field.get(bottomNavigationBar);
                    for (int j = 0; j < mTabContainer.getChildCount(); j++) {
                        //获取到容器内的各个Tab
                        View view = mTabContainer.getChildAt(j);
                        //获取到Tab内的各个显示控件
                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(56));
                        FrameLayout container = (FrameLayout) view.findViewById(R.id.fixed_bottom_navigation_container);
                        container.setLayoutParams(params);
                        container.setPadding(dip2px(12), dip2px(0), dip2px(12), dip2px(0));

                        //获取到Tab内的文字控件
                        TextView labelView = (TextView) view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_title);
                        //计算文字的高度DP值并设置，setTextSize为设置文字正方形的对角线长度，所以：文字高度（总内容高度减去间距和图片高度）*根号2即为对角线长度，此处用DP值，设置该值即可。
                        labelView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
                        labelView.setIncludeFontPadding(false);
                        labelView.setPadding(0, 0, 0, dip2px(20 - textSize - space / 2));

                        //获取到Tab内的图像控件
                        ImageView iconView = (ImageView) view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_icon);
                        //设置图片参数，其中，MethodUtils.dip2px()：换算dp值
                        params = new FrameLayout.LayoutParams(dip2px(imgLen), dip2px(imgLen));
                        params.setMargins(0, 0, 0, space / 6);
                        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
                        iconView.setLayoutParams(params);
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int dip2px(float dpValue) {
        final float scale = getApplication().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private long mExitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (event.getAction() == KeyEvent.ACTION_DOWN) {

                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    ToastUtil.showToast("再按一次退出程序");
                    mExitTime = System.currentTimeMillis();
                } else {
                    finish();
                    System.exit(0);
                }

                return true; //返回true表示执行结束不需继续执行父类按键响应
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this,this);
    }

    @Override
    public void onRequestError(String errorMsg) {

    }

    @Override
    public void onRequestSuccess(Object obj) {

    }

    @Override
    public void onRequestFinish() {

    }
}