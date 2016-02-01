package com.maneater.base.toolbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.maneater.base.util.InjectUtil;
import de.greenrobot.event.EventBus;

public abstract class XBaseActivity extends AppCompatActivity implements InjectUtil.InjectViewAble {


    private View.OnClickListener onClickListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView(savedInstanceState);
        initData();
        initListener();
    }

    protected abstract int getContentViewId();

    protected abstract InjectUtil.InjectViewAble getInjectViewTarget();

    protected abstract void onViewClick(int viewId, View view);


    /**
     * 重写时，需要调用父类接口
     *
     * @param savedInstanceState
     */
    protected void initView(Bundle savedInstanceState) {
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

    protected abstract void initData();

    protected abstract void initListener();

    protected EventBus getEventBus() {
        return EventBus.getDefault();
    }

    protected void registerEventBus() {
        getEventBus().register(this);
    }

    protected void unRegisterEventBus() {
        getEventBus().unregister(this);
    }

}
