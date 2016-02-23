package com.maneater.app.sport.activity;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.maneater.app.sport.R;
import com.maneater.base.util.InjectUtil;
import com.maneater.base.util.SystemUtil;

public class SettingActivity extends BaseActivity {
    public static void launch(Activity activity) {
        SystemUtil.launchActivity(activity, SettingActivity.class);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    protected InjectUtil.InjectViewAble getInjectViewTarget() {
        return this;
    }

    @Override
    protected void onViewClick(int i, View view) {

    }

    @Override
    protected void initView(Bundle bundle) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
