package com.maneater.base.util;

import android.content.Context;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class XImageLoader {

    private ImageLoader innerImageLoader = null;
    private Context mContext = null;

    private static XImageLoader instance = null;

    private int loadResId;
    private int errorResId;

    public synchronized static XImageLoader getDefault() {
        return instance;
    }

    public synchronized static XImageLoader getDefault(Context mContext) {
        if (instance == null) {
            instance = new XImageLoader();
            instance.mContext = mContext.getApplicationContext();
            instance.innerImageLoader = ImageLoader.getInstance();
        }
        return instance;
    }


    public XImageLoader init() {
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this.mContext);
        innerImageLoader.init(configuration);
        return this;
    }

    public void displayImage(String url, ImageView imageView) {
        innerImageLoader.displayImage(url, imageView);
    }

}
