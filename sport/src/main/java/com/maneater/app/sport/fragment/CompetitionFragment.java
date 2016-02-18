package com.maneater.app.sport.fragment;

import android.view.View;
import com.maneater.app.sport.R;
import com.maneater.base.util.InjectUtil;

public class CompetitionFragment extends BaseFragment {


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_competition;
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
