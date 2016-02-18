package com.maneater.dm.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.maneater.base.util.InjectUtil;
import com.maneater.dm.app.R;
import com.maneater.dm.app.model.Hospital;
import com.maneater.dm.app.net.Result;
import com.maneater.dm.app.net.WebApiFactory;
import com.maneater.dm.app.view.adapter.HospitalAdapter;
import rx.functions.Action1;

import java.util.List;


public class SuggestionListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout lytSwipeRefresh = null;
    private RecyclerView recyclerView = null;
    private HospitalAdapter hospitalAdapter = null;
    private int mPageSize = 0;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_suggestion_list;
    }

    @Override
    protected InjectUtil.InjectViewAble getInjectViewTarget() {
        return this;
    }

    @Override
    protected void onViewClick(int viewId, View view) {

    }

    @Override
    protected void initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.initView(inflater, container, savedInstanceState);
        hospitalAdapter = new HospitalAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(hospitalAdapter);
        lytSwipeRefresh.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        lytSwipeRefresh.setRefreshing(true);
        loadPageData(0);
    }


    private void loadPageData(int targetPage) {
        WebApiFactory.createApi().listHospital("").subscribe(new Action1<Result<List<Hospital>>>() {
            @Override
            public void call(Result<List<Hospital>> listResult) {
                hospitalAdapter.setDataList(listResult.getData());
                lytSwipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void initListener() {

    }


    @Override
    public void onRefresh() {
        initData();
    }
}
