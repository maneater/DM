package com.maneater.app.sport.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.maneater.app.sport.R;
import com.maneater.app.sport.utils.LocationClientUtil;
import com.maneater.base.util.InjectUtil;
import com.maneater.base.widget.XActionBar;

public class CompetitionFragment extends BaseFragment implements AMapLocationListener {

    private MapView mapView;
    private AMap mAMap = null;
    private AMapLocationClient aMapLocationClient = null;
    private XActionBar vActionBar = null;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_competition;
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
        mapView.onCreate(savedInstanceState);
        mAMap = mapView.getMap();
        vActionBar.setBackActionVisibility(View.INVISIBLE);
    }

    @Override
    protected void initListener() {
        aMapLocationClient = LocationClientUtil.requestLocation(getActivity(), 1000, this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        aMapLocationClient.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        aMapLocationClient.startLocation();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        aMapLocationClient.stopLocation();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
            mAMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
        }
    }
}
