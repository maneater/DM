package com.maneater.dm.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.maneater.base.toolbox.XBaseActivity;
import com.maneater.base.util.DumpSysUtil;
import com.maneater.base.util.InjectUtil;
import com.maneater.dm.app.net.WebApi;
import com.maneater.dm.app.net.WebApiFactory;

public class MainActivity extends XBaseActivity {

    private ViewPager homeViewPager = null;
    private RadioGroup topBar = null;

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

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

}
