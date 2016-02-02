package com.maneater.dm.app;

import android.app.Application;
import com.maneater.base.util.XImageLoader;

public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        XImageLoader.getDefault(this).init();
    }
}
