package com.maneater.dm.app.fragment;

import android.view.View;
import com.maneater.base.util.InjectUtil;
import com.maneater.dm.app.R;

public class MyHomeFragment extends BaseFragment {


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_category_list;
    }

    @Override
    protected InjectUtil.InjectViewAble getInjectViewTarget() {
        return null;
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
