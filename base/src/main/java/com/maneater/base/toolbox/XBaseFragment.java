package com.maneater.base.toolbox;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.maneater.base.util.InjectUtil;
import de.greenrobot.event.EventBus;

public abstract class XBaseFragment extends Fragment implements InjectUtil.InjectViewAble {


    private View.OnClickListener onClickListener = null;

    private View rootView = null;


    public View getRootView() {
        return rootView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        prepareOnCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public View findViewById(int viewId) {
        return rootView.findViewById(viewId);
    }

    protected abstract int getContentViewId();

    protected abstract InjectUtil.InjectViewAble getInjectViewTarget();

    protected abstract void onViewClick(int viewId, View view);

    protected void prepareOnCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentViewId() > 0) {
            rootView = inflater.inflate(getContentViewId(), container, false);
        }
        if (getInjectViewTarget() != null) {
            onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onViewClick(v.getId(), v);
                }
            };
            InjectUtil.injectViews(getInjectViewTarget(), getActivity(), onClickListener);
        }

        initView(savedInstanceState);
        initData();
        initListener();
    }

    protected abstract void initView(@Nullable Bundle savedInstanceState);

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
            mProgressDialog = new ProgressDialog(getActivity());
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
