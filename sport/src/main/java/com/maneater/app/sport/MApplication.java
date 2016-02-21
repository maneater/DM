package com.maneater.app.sport;

import android.app.Application;
import com.maneater.app.sport.account.XAccountManager;
import com.maneater.base.util.XImageLoader;

public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        XAccountManager.getInstance(this);
        XImageLoader.getDefault(this).init();
    }
}
