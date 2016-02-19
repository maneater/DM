package com.maneater.app.sport.utils;

import android.content.Context;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

public class LocationClientUtil {


    private static AMapLocation cacheAMapLocation = null;


    public static AMapLocationClient requestLocation(final Context mContext, long time, final AMapLocationListener listener) {
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setNeedAddress(false);
        mLocationOption.setOnceLocation(false);
        mLocationOption.setWifiActiveScan(true);
        mLocationOption.setMockEnable(false);
        mLocationOption.setInterval(time);
        AMapLocationClient aMapLocationClient = new AMapLocationClient(mContext.getApplicationContext());
        aMapLocationClient.setLocationOption(mLocationOption);
        aMapLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                    cacheAMapLocation = aMapLocation;
                }
                if (listener != null) {
                    listener.onLocationChanged(aMapLocation);
                }
            }
        });
        aMapLocationClient.startLocation();
        if (cacheAMapLocation != null && Math.abs(System.currentTimeMillis() - cacheAMapLocation.getTime()) < 60 * 1000) {
            if (listener != null) {
                listener.onLocationChanged(cacheAMapLocation);
            }
        }
        return aMapLocationClient;
    }

}
