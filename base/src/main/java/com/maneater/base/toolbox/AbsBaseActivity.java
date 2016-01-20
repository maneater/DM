package com.maneater.base.toolbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.maneater.base.util.InjectUtil;

public abstract class AbsBaseActivity extends AppCompatActivity implements InjectUtil.InjectViewAble {


    private View.OnClickListener onClickListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContentViewId() > 0) {
            setContentView(getContentViewId());
        }

        if (getInjectViewTarget() != null) {
            onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onViewClick(v.getId(), v);
                }
            };
            InjectUtil.injectViews(getInjectViewTarget(), this, onClickListener);
        }
    }

    protected abstract int getContentViewId();

    protected abstract InjectUtil.InjectViewAble getInjectViewTarget();

    protected abstract void onViewClick(int viewId, View view);

}
