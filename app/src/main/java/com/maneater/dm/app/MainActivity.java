package com.maneater.dm.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.maneater.base.toolbox.XBaseActivity;
import com.maneater.base.util.InjectUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends XBaseActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private ViewPager homeViewPager = null;
    private RadioGroup topBar = null;
    private List<RadioButton> topBarItemList = new ArrayList<RadioButton>();
    private HomeFragmentPagerAdapter mHomeFragmentPagerAdapter = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected InjectUtil.InjectViewAble getInjectViewTarget() {
        return this;
    }

    @Override
    protected void onViewClick(int viewId, View view) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        int count = topBar.getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = topBar.getChildAt(i);
            if (childView instanceof RadioButton && childView.getTag() != null) {
                topBarItemList.add((RadioButton) childView);
            }
        }
    }


    @Override
    protected void initData() {
        mHomeFragmentPagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        homeViewPager.setAdapter(mHomeFragmentPagerAdapter);
    }

    @Override
    protected void initListener() {
        homeViewPager.addOnPageChangeListener(this);
        topBar.setOnCheckedChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        topBar.check(topBarItemList.get(position).getId());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        homeViewPager.setCurrentItem(topBarItemList.indexOf(radioGroup.findViewById(i)));
    }

    class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

        public HomeFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            View view = topBarItemList.get(position);
            if (view.getTag() instanceof Fragment) {
                return (Fragment) view.getTag();
            } else {
                Fragment instance = Fragment.instantiate(getApplicationContext(), (String) view.getTag());
                view.setTag(instance);
                return instance;
            }
        }

        @Override
        public int getCount() {
            return topBarItemList.size();
        }
    }

}
