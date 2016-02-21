package com.maneater.app.sport.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.maneater.app.sport.R;
import com.maneater.app.sport.view.adapter.ActivitySportAdapter;
import com.maneater.base.util.InjectUtil;
import com.maneater.base.util.LogUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView vActivityList = null;
    private ActivitySportAdapter activitySportAdapter = null;
    private SwipeRefreshLayout lytSwipeRefresh;


    public ExploreFragment() {
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_explore;
    }

    @Override
    protected InjectUtil.InjectViewAble getInjectViewTarget() {
        return this;
    }

    @Override
    protected void onViewClick(int viewId, View view) {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        activitySportAdapter = new ActivitySportAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        vActivityList.setItemAnimator(new DefaultItemAnimator());
        vActivityList.setLayoutManager(linearLayoutManager);
        vActivityList.setAdapter(activitySportAdapter);
        lytSwipeRefresh.setOnRefreshListener(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onRefresh() {
        LogUtils.logMethod("onRefresh");

    }
}
