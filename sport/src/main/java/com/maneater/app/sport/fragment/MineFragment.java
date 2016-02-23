package com.maneater.app.sport.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import com.maneater.app.sport.R;
import com.maneater.app.sport.account.XAccount;
import com.maneater.app.sport.account.XAccountManager;
import com.maneater.app.sport.activity.PersonalAchievementActivity;
import com.maneater.app.sport.activity.PersonalHistoryActivity;
import com.maneater.app.sport.net.Result;
import com.maneater.app.sport.view.CurveView;
import com.maneater.base.util.InjectUtil;
import com.maneater.base.util.XImageLoader;
import rx.subjects.PublishSubject;

import java.util.ArrayList;
import java.util.List;


public class MineFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    private PublishSubject<Result> loadUserDateCMD = PublishSubject.create();

    public MineFragment() {
    }

    private ImageView vImgHeaderViewACK = null;
    private CurveView vCurveView = null;
    private SwipeRefreshLayout lytSwipeRefresh = null;
    private HorizontalScrollView vLytHorizontal = null;
    private View vIvSettingACK = null;

    private TextView vTxTotalSortNum = null;
    private TextView vTxTotalTime = null;
    private TextView vTxTotalDistance = null;

    private ViewGroup vLytShowMyHistoryACK = null;
    private ViewGroup vLytShowMyScoreACK = null;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected InjectUtil.InjectViewAble getInjectViewTarget() {
        return this;
    }

    @Override
    protected void onViewClick(int viewId, View view) {
        switch (viewId) {
            case R.id.vIvSettingACK:
                break;
            case R.id.vImgHeaderViewACK:
                break;
            case R.id.vLytShowMyHistoryACK:
                PersonalHistoryActivity.launch(getActivity());
                break;
            case R.id.vLytShowMyScoreACK:
                PersonalAchievementActivity.launch(getActivity());
                break;
        }
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected void initData() {

        XAccount xAccount = XAccountManager.getInstance(getActivity()).getLoginAccount();
        //http://img4.duitang.com/uploads/item/201411/01/20141101172619_5sz2Y.jpeg
        XImageLoader.getDefault(getContext()).displayImage("http://img4.duitang.com/uploads/item/201411/01/20141101172619_5sz2Y.jpeg", vImgHeaderViewACK);
        vCurveView.render(randomData(), true);
    }

    private List<CurveView.CurveItem> randomData() {
        List<CurveView.CurveItem> dataList = new ArrayList<CurveView.CurveItem>();
        for (int i = 0; i < 20; i++) {
            dataList.add(new CurveView.CurveItem(i + "", ((int) (Math.random() * 100))));
        }
        return dataList;
    }

    @Override
    protected void initListener() {
        lytSwipeRefresh.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        vLytHorizontal.scrollTo(0, 0);
        initData();
        lytSwipeRefresh.setRefreshing(false);
    }
}
