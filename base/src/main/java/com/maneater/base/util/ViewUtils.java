package com.maneater.base.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Administrator on 2016/2/23 0023.
 */
public class ViewUtils {
    public int dp2px(Context mContext, float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, mContext.getResources().getDisplayMetrics());
    }

    public int sp2px(Context mContext, float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, mContext.getResources().getDisplayMetrics());
    }
}
