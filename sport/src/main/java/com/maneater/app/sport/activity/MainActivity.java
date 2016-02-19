package com.maneater.app.sport.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;
import com.maneater.app.sport.R;
import com.maneater.base.util.InjectUtil;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup bottomGroup = null;

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
        findViewById(R.id.rbCompetition).performClick();
    }

    @Override
    protected void initListener() {
        bottomGroup.setOnCheckedChangeListener(this);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    private Fragment curFragment = null;

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group.findViewById(checkedId) != null) {
            String fragmentTag = (String) group.findViewById(checkedId).getTag();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (curFragment != null) {
                ft.hide(curFragment);
            }
            curFragment = fm.findFragmentByTag(fragmentTag);
            if (curFragment != null) {
                ft.show(curFragment);
            } else {
                curFragment = Fragment.instantiate(this, fragmentTag);
                ft.add(R.id.lytContent, curFragment, fragmentTag);
            }
            ft.commit();
        }
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
}
