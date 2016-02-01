package com.maneater.base.util;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;

public final class StorageDir {

    private List<AppRootDir> dirList = new ArrayList<AppRootDir>();
    private Context context;

    public static StorageDir instance = null;

    public synchronized static StorageDir getInstance(Context context) {
        if (instance == null) {
            instance = new StorageDir(context);
        }
        return instance;
    }

    public AppRootDir getAppRootDir() {
        if (dirList != null && dirList.size() > 0) {
            return dirList.get(0);
        }
        return null;
    }

    public boolean hasDir() {
        return getAppRootDir() != null;
    }

    private StorageDir(Context context) {
        this.context = context;
        try {
            if (android.os.Build.VERSION.SDK_INT >= 14)
                useVolumeList(this.context);
            else
                useProList(this.context);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        File externalFileDir = this.context.getExternalFilesDir(null);
        if (externalFileDir != null) {
            dirList.add(new AppRootDir(this.context, externalFileDir.getAbsolutePath()));
        }

        for (int i = 0; i < dirList.size(); i++) {
            String appRootPath = dirList.get(i).getAppRootPath();
            File path = new File(appRootPath);
            if (!path.exists() || !path.isDirectory() || !path.canWrite())
                dirList.remove(i--);
        }
        if (CollectionUtils.isEmpty(dirList)) {
            dirList.add(new AppRootDir(this.context.getDir(context.getPackageName(), Context.MODE_WORLD_WRITEABLE)
                    .getAbsolutePath()));
        }
        if (CollectionUtils.isNotEmpty(dirList)) {
            for (AppRootDir path : dirList) {
                LogUtils.logMethod(path.getRootPath());
            }
        }
    }

    private void useProList(Context context) throws Exception {
        Scanner scanner = null;
        ArrayList<String> proclist = new ArrayList<String>();
        ArrayList<String> fstabList = new ArrayList<String>();
        File file = new File("/proc/mounts");
        if (file.exists()) {
            scanner = new Scanner(file);
            do {
                if (!scanner.hasNext())
                    break;
                String s = scanner.nextLine();
                if (s.startsWith("/dev/block/vold/")) {
                    s = s.replace('\t', ' ');
                    String as[] = s.split(" ");
                    if (as != null && as.length > 0)
                        proclist.add(as[1]);
                }
            } while (true);
            if (scanner != null)
                scanner.close();
        }
        File file1 = new File("/system/etc/vold.fstab");
        if (file1.exists()) {
            scanner = new Scanner(file1);
            do {
                if (!scanner.hasNext())
                    break;
                String s1 = scanner.nextLine();
                if (s1.startsWith("dev_mount")) {
                    s1 = s1.replace('\t', ' ');
                    String as1[] = s1.split(" ");
                    if (as1 != null && as1.length > 0) {
                        String s3 = as1[2];
                        if (s3.contains(":"))
                            s3 = s3.substring(0, s3.indexOf(":"));
                        fstabList.add(s3);
                    }
                }
            } while (true);
            if (scanner != null)
                scanner.close();
        }
        String normalPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        dirList.add(new AppRootDir(context, normalPath));

        Iterator<String> iterator = proclist.iterator();
        do {
            if (!iterator.hasNext())
                break;
            String s4 = (String) iterator.next();
            if (fstabList.contains(s4) && !s4.equals(normalPath)) {
                File file2 = new File(s4);
                if (file2.exists() && file2.isDirectory() && file2.canWrite())
                    dirList.add(new AppRootDir(context, s4));
            }
        } while (true);

    }

    @TargetApi(19)
    private void useVolumeList(Context context) {
        try {
            StorageManager storagemanager = (StorageManager) context.getSystemService("storage");
            Method method = storagemanager.getClass().getMethod("getVolumeList", new Class[0]);
            Method method1 = storagemanager.getClass().getMethod("getVolumeState",
                    new Class[]{String.class});
            Class<?> class1 = Class.forName("android.os.storage.StorageVolume");
            Method method2 = class1.getMethod("isRemovable", new Class[0]);
            Method method3 = class1.getMethod("getPath", new Class[0]);
            Object aobj[] = (Object[]) (Object[]) method.invoke(storagemanager, new Object[0]);
            if (aobj != null) {
                Object aobj1[] = aobj;
                int i = aobj1.length;
                for (int k = 0; k < i; k++) {
                    Object obj = aobj1[k];
                    String path = (String) method3.invoke(obj, new Object[0]);
                    if (path == null
                            || path.length() <= 0
                            || !"mounted".equals(method1.invoke(storagemanager,
                            new Object[]{path})))
                        continue;
                    boolean flag = !((Boolean) method2.invoke(obj, new Object[0])).booleanValue();
                    if (android.os.Build.VERSION.SDK_INT < 19 || flag) {
                        dirList.add(new AppRootDir(context, path));
                        continue;
                    }
                }

                if (android.os.Build.VERSION.SDK_INT >= 19) {
                    File afile[] = context.getExternalFilesDirs(null);
                    for (int j = 1; j < afile.length; j++) {
                        String s = afile[j].getAbsolutePath();
                        dirList.add(new AppRootDir(context, s));
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
