package com.maneater.base.util;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

public class DumpSysUtil {
    private DumpSysUtil() {
    }

    public static StringBuilder dumpSysStatus(Context mContext) {

        StringBuilder sysDump = new StringBuilder();
        sysDump.append(new Date());
        nextLine(sysDump);

        dumpNetStatus(mContext, sysDump);
        nextLine(sysDump);

        dumpLocationStatus(mContext, sysDump);
        nextLine(sysDump);

        return sysDump;
    }

    public static StringBuilder dumpNetStatus(Context mContext, StringBuilder dumpTarget) {
        if (dumpTarget == null) {
            dumpTarget = new StringBuilder();
        }
        dumpTarget.append("dumpNetStatus:");
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
//        for (NetworkInfo networkInfo : networkInfos) {
//            tabLine(dumpTarget).append(networkInfo.toString());
//        }

        NetworkInfo wifiNetWorkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        tabLine(dumpTarget).append("wifiNetWorkInfo:");
        tab2Line(dumpTarget).append(wifiNetWorkInfo);

        NetworkInfo mobileNetWorkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        tabLine(dumpTarget).append("mobileNetWorkInfo:");
        tab2Line(dumpTarget).append(mobileNetWorkInfo);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        tabLine(dumpTarget).append("getActiveNetworkInfo:");
        tab2Line(dumpTarget).append(activeNetworkInfo);

        tabLine(dumpTarget).append("isExistNetwork:");
        try {
            tab2Line(dumpTarget).append(invokeNoneArgs(connectivityManager, "getMobileDataEnabled", Boolean.class));
        } catch (Exception e) {
            e.printStackTrace();
            tab2Line(dumpTarget).append("exception crash");
        }

        return dumpTarget;
    }

    private static StringBuilder nextLine(StringBuilder stringBuilder) {
        return stringBuilder.append("\n");
    }


    private static StringBuilder tabLine(StringBuilder stringBuilder) {
        return nextLine(stringBuilder).append("    |----");
    }

    private static StringBuilder tab2Line(StringBuilder stringBuilder) {
        return nextLine(stringBuilder).append("        |----");
    }

    public static StringBuilder dumpLocationStatus(Context mContext, StringBuilder dumpTarget) {
        if (dumpTarget == null) {
            dumpTarget = new StringBuilder();
        }
        dumpTarget.append("dumpLocationStatus:");
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        List<String> allProviders = locationManager.getAllProviders();
        for (String providerName : allProviders) {
            dumpTarget.append("\r\nProvider[").append(providerName).append("]:[").append(locationManager.isProviderEnabled(providerName)).append("]");
        }
        return dumpTarget;
    }


    @SuppressWarnings("unchecked")
    public static <T> T invokeNoneArgs(Object target, String methodName, Class<T> result) throws Exception {
        Method method = target.getClass().getMethod(methodName);
        return (T) method.invoke(target);
    }


}
