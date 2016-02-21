package com.maneater.app.sport.activity;

import android.view.View;
import com.maneater.base.toolbox.XBaseActivity;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity extends XBaseActivity {
    public void onActionBackClick(View view) {
        finish();
    }

    private final CompositeSubscription mSubscriptions = new CompositeSubscription();


    protected void addSubscription(Subscription subscription) {
        mSubscriptions.add(subscription);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }
}
