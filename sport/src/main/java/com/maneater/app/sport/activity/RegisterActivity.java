package com.maneater.app.sport.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.maneater.app.sport.R;
import com.maneater.app.sport.net.Result;
import com.maneater.app.sport.net.WebApiFactory;
import com.maneater.app.sport.net.vo.PhoneVerCodeVo;
import com.maneater.base.util.InjectUtil;
import com.maneater.base.util.SystemUtil;
import com.maneater.base.util.ToastUtil;
import com.maneater.base.util.ValidateUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class RegisterActivity extends BaseActivity {
    private View vBtnRegisterACK;
    private EditText vEtxPhoneNumber;

    private PublishSubject<String> getPhoneCodeCMD = PublishSubject.create();
    private PublishSubject<Result<Void>> postResultCMD = PublishSubject.create();

    public static void launch(Activity activity) {
        SystemUtil.launchActivity(activity, RegisterActivity.class);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected InjectUtil.InjectViewAble getInjectViewTarget() {
        return this;
    }

    @Override
    protected void onViewClick(int viewId, View view) {
        switch (viewId) {
            case R.id.vBtnRegisterACK:
                if (ValidateUtils.validatePhone(vEtxPhoneNumber)) {
                    getPhoneCodeCMD.onNext(vEtxPhoneNumber.getText().toString());
                }
                break;
        }
    }


    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initListener() {

        addSubscription(getPhoneCodeCMD.observeOn(AndroidSchedulers.mainThread()).doOnNext(new Action1<String>() {
            @Override
            public void call(String s) {
                showProgress("正在获取验证码");
            }
        }).observeOn(Schedulers.io()).subscribe(new Action1<String>() {
            @Override
            public void call(String phoneNumber) {
                postResultCMD.onNext(WebApiFactory.createApi().phoneVerCode(PhoneVerCodeVo.register(phoneNumber)));
            }
        }));

        addSubscription(postResultCMD.observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Result<Void>>() {
            @Override
            public void call(Result<Void> voidResult) {
                dismissProgress();
                //TODO
                voidResult.setResult(1);
                if (voidResult.isSuccess()) {
                    ToastUtil.showToast(getApplicationContext(), "已发送验证码,请注意查收");
                    RegisterPassActivity.launch(RegisterActivity.this, vEtxPhoneNumber.getText().toString());
                } else {
                    ToastUtil.showToast(getApplicationContext(), voidResult.getMessage());
                }
            }
        }));
    }

    @Override
    protected void initData() {
        vEtxPhoneNumber.setText(SystemUtil.getPhoneNumber(this));
    }
}
