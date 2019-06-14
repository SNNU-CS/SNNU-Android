package io.github.zhaoqi99.snnu_android;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.github.zhaoqi99.snnu_android.Adapter.mViewPagerFragmentAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class JwcFragment extends Fragment {
    TabLayout tablayout;
    private mViewPagerFragmentAdapter mAdapter;
    ViewPager mViewPager;
    private List<String> mTabTitleList;
    private List<Fragment> mFragmentList;


    public JwcFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jwc, container, false);
        tablayout = view.findViewById(R.id.tablayout2);
        mViewPager = view.findViewById(R.id.mViewPager2);

        InitTab();
        return view;
    }
    private void InitTab() {

        mFragmentList = new ArrayList<Fragment>();
        mTabTitleList = new ArrayList<String>();

        String keys[]={"课程表","考试成绩"};
        for (String s:keys) {
            mTabTitleList.add(s);
        }

        mFragmentList.add(new CourseTab());
        mFragmentList.add(new GradeTab());


        mAdapter = new mViewPagerFragmentAdapter(getChildFragmentManager(), mFragmentList, mTabTitleList);
        mViewPager.setAdapter(mAdapter);
        tablayout.setupWithViewPager(mViewPager);
    }
}
