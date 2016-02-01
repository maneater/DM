package com.maneater.base.util;

import java.io.File;

import android.content.Context;

public class AppRootDir {
    private String rootPath;
    private String appRootPath;

    public AppRootDir(Context context, String sdPath) {
        rootPath = sdPath;
        appRootPath = rootPath + File.separator + context.getPackageName();
        new File(appRootPath).mkdir();
    }

    public AppRootDir(String appPath) {
        rootPath = appPath;
        appRootPath = appPath;
        new File(appRootPath).mkdir();
    }

    public String getRootPath() {
        return rootPath;
    }

    public String getAppRootPath() {
        return appRootPath;
    }
}
