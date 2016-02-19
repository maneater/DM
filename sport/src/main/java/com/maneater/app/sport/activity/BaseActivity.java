package com.maneater.app.sport.activity;

import android.view.View;
import com.maneater.base.toolbox.XBaseActivity;

public abstract class BaseActivity extends XBaseActivity {
    public void onActionBackClick(View view){
        finish();
    }
}
