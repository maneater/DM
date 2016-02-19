package com.maneater.app.sport.fragment;


import android.app.Fragment;
import android.view.View;
import com.maneater.app.sport.R;
import com.maneater.base.util.InjectUtil;

public class MineFragment extends BaseFragment {


    public MineFragment() {
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_mine;
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
