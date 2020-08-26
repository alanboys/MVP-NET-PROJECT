package com.white.bird.ui.adapter.home;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.white.bird.common.base.fragment.BaseMvpFragment;

import java.util.List;

/**
 * Created by Demon on 2018/5/18.
 */
public class HomeTabPageAdapter extends FragmentPagerAdapter {

    private List<BaseMvpFragment> fragments;

    public HomeTabPageAdapter(FragmentManager fm, List<BaseMvpFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
