package io.github.zhaoqi99.snnu_android;


;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    TabLayout tablayout;
    private mViewPagerFragmentAdapter mAdapter;
    ViewPager mViewPager;
    private List<String> mTabTitleList;
    private List<Fragment> mFragmentList;

    public void setType(String type) {
        this.type = type;
    }

    private String type;
    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        tablayout = view.findViewById(R.id.tablayout);
        mViewPager = view.findViewById(R.id.mViewPager);

        InitTab();
        return view;
    }

    //初始化tab
    private void InitTab() {

        mFragmentList = new ArrayList<Fragment>();
        mTabTitleList = new ArrayList<String>();
        
        String keys[]={"学校主页","学生处","计算机科学学院","教务处"};
        for (String s:keys) {
            NewsTab t=new NewsTab();
            t.setDep(s);
            t.setType(type);
            mFragmentList.add(t);
            mTabTitleList.add(s);

        }

        mAdapter = new mViewPagerFragmentAdapter(getChildFragmentManager(), mFragmentList, mTabTitleList);
        mViewPager.setAdapter(mAdapter);
        tablayout.setupWithViewPager(mViewPager);
    }

}
