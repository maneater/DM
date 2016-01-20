package com.maneater.dm.app;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.maneater.base.toolbox.AbsBaseActivity;
import com.maneater.base.util.InjectUtil;
import com.maneater.dm.app.net.WebApi;
import com.maneater.dm.app.net.WebApiFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AbsBaseActivity {

    private Button btnGetACK;
    private Toolbar toolbar;
    private TextView txContent;
    private WebApi webApi = WebApiFactory.createApi();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected InjectUtil.InjectViewAble getInjectViewTarget() {
        return this;
    }

    @Override
    protected void onViewClick(int viewId, View view) {
        switch (viewId) {
            case R.id.btnGetACK:
                webApi.content("https://www.baidu.com/").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
                    @Override
                    public void call(String content) {
                        txContent.setText(content);
                    }
                });
                break;
        }
    }

}
