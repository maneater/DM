package com.maneater.app.sport.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maneater.app.sport.R;
import com.maneater.base.util.InjectUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends BaseFragment {


    public ExploreFragment() {
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_explore;
    }

    @Override
    protected InjectUtil.InjectViewAble getInjectViewTarget() {
        return this;
    }

    @Override
    protected void onViewClick(int viewId, View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
