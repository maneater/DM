package com.maneater.dm.app;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.maneater.base.toolbox.AbsBaseActivity;
import com.maneater.base.util.DumpSysUtil;
import com.maneater.base.util.InjectUtil;
import com.maneater.dm.app.net.WebApi;
import com.maneater.dm.app.net.WebApiFactory;

public class MainActivity extends AbsBaseActivity {

    private Button btnGetACK;
    private Toolbar toolbar;
    private TextView txContent;
    private WebApi webApi = WebApiFactory.createApi();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        txContent.setMovementMethod(ScrollingMovementMethod.getInstance());
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
//                webApi.content("https://www.baidu.com/").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Result<String>>() {
//                    @Override
//                    public void call(Result<String> content) {
//                        txContent.setText(content.getData());
//                    }
//                });
                txContent.setText(DumpSysUtil.dumpSysStatus(getApplicationContext()).toString());
                break;
        }
    }

}
