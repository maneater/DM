package com.maneater.app.sport.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.maneater.app.sport.R;
import com.maneater.app.sport.account.XAccount;
import com.maneater.app.sport.account.XAccountManager;
import com.maneater.app.sport.net.Result;
import com.maneater.base.util.InjectUtil;
import com.maneater.base.util.XImageLoader;
import rx.subjects.PublishSubject;

public class MineFragment extends BaseFragment {


    private PublishSubject<Result> loadUserDateCMD = PublishSubject.create();

    public MineFragment() {
    }

    private ImageView vImgHeaderView = null;


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

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        XAccount xAccount = XAccountManager.getInstance(getActivity()).getLoginAccount();
        //http://img4.duitang.com/uploads/item/201411/01/20141101172619_5sz2Y.jpeg
        XImageLoader.getDefault(getContext()).displayImage("http://img4.duitang.com/uploads/item/201411/01/20141101172619_5sz2Y.jpeg", vImgHeaderView);
    }

    @Override
    protected void initListener() {

    }
}
