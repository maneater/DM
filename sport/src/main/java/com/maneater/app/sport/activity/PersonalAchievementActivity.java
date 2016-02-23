package com.maneater.app.sport.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.maneater.app.sport.R;
import com.maneater.base.util.InjectUtil;
import com.maneater.base.util.SystemUtil;

public class PersonalAchievementActivity extends BaseActivity {
    public static void launch(Activity activity) {
        SystemUtil.launchActivity(activity, PersonalAchievementActivity.class);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_personal_achievement;
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

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
