package com.maneater.base.toolbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.maneater.base.util.InjectUtil;

public abstract class XBaseFragment extends Fragment implements InjectUtil.InjectViewAble {


    private View.OnClickListener onClickListener = null;

    private View rootView = null;


    public View getRootView() {
        return rootView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initView(inflater, container, savedInstanceState);
        initData();
        initListener();
        return rootView;
    }

    @Override
    public View findViewById(int viewId) {
        return rootView.findViewById(viewId);
    }

    protected abstract int getContentViewId();

    protected abstract InjectUtil.InjectViewAble getInjectViewTarget();

    protected abstract void onViewClick(int viewId, View view);

    protected void initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
    }

    protected abstract void initData();

    protected abstract void initListener();


}
