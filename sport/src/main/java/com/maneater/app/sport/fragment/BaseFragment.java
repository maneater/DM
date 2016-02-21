package com.maneater.app.sport.fragment;

import com.maneater.base.toolbox.XBaseFragment;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by liang on 16/2/18.
 */
public abstract class BaseFragment extends XBaseFragment {
    private final CompositeSubscription mSubscriptions = new CompositeSubscription();


    protected void addSubscription(Subscription subscription) {
        mSubscriptions.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }

}
