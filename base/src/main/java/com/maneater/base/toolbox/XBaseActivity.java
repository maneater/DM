package com.maneater.base.toolbox;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.maneater.base.util.InjectUtil;
import de.greenrobot.event.EventBus;

public abstract class XBaseActivity extends AppCompatActivity implements InjectUtil.InjectViewAble {

    public final static String KEY_1 = "key_1";

    private View.OnClickListener onClickListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareOnCreate(savedInstanceState);
    }

    protected abstract int getContentViewId();

    protected abstract InjectUtil.InjectViewAble getInjectViewTarget();

    protected abstract void onViewClick(int viewId, View view);

    /**
     * 重写时，需要调用父类接口
     *
     * @param savedInstanceState
     */
    protected void prepareOnCreate(Bundle savedInstanceState) {
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
        initView(savedInstanceState);
        initListener();
        initData();
    }


    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initListener();

    protected abstract void initData();

    protected EventBus getEventBus() {
        return EventBus.getDefault();
    }

    protected void registerEventBus() {
        getEventBus().register(this);
    }

    protected void unRegisterEventBus() {
        getEventBus().unregister(this);
    }

    private ProgressDialog mProgressDialog = null;

    protected ProgressDialog showProgress(String msg, boolean cancelAble, ProgressDialog.OnCancelListener onCancelListener) {
        dismissProgress();
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(cancelAble);
            if (onCancelListener != null) {
                mProgressDialog.setOnCancelListener(onCancelListener);
            }
        }
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
        return mProgressDialog;
    }


    protected ProgressDialog showProgress(String msg) {
        return showProgress(msg, false, null);
    }

    protected void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

}
