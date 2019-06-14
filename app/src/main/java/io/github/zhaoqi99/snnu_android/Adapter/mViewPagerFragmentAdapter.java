package io.github.zhaoqi99.snnu_android.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;;

import java.util.List;


public class mViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private List<String> mTabTitleList; //装有tablayout Title的list
    private List<Fragment> mFragmentList;

    public mViewPagerFragmentAdapter(FragmentManager fm, List<Fragment> mFragmentList, List<String> tabTitleList) {
        super(fm);
        this.mTabTitleList = tabTitleList;
        this.mFragmentList=mFragmentList;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }
}
