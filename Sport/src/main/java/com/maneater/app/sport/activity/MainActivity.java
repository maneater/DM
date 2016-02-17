package com.maneater.app.sport.activity;

import android.view.View;
import com.maneater.app.sport.R;
import com.maneater.base.util.InjectUtil;

public class MainActivity extends BaseActivity {
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
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
