package com.maneater.base.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

/**
 * Created by liang on 16/2/20.
 */
public class SystemUtil {


    public static void launchActivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
    }

    public static void launchActivity(Activity activity, Class clz) {
        Intent intent = new Intent(activity, clz);
        activity.startActivity(intent);
    }

    public static String getPhoneNumber(Context mContext) {
        TelephonyManager telephonyManager = (TelephonyManager) mContext
                .getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNumber = telephonyManager.getLine1Number();
        if (phoneNumber != null) {
            phoneNumber = phoneNumber.replace("+86", "").trim();
        }
        return phoneNumber;
    }
}
