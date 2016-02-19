package com.maneater.app.sport.activity;

import android.os.Handler;
import android.view.View;
import com.maneater.app.sport.R;
import com.maneater.base.util.InjectUtil;
import rx.subjects.PublishSubject;

public class LogoActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_logo;
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
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                MainActivity.launch(LogoActivity.this);
//                finish();
//            }
//        }, 2000);
    }

    @Override
    protected void initListener() {

    }
}
