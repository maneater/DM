package com.maneater.app.sport.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.maneater.app.sport.R;
import com.maneater.app.sport.account.XAccount;
import com.maneater.app.sport.net.Result;
import com.maneater.app.sport.net.WebApiFactory;
import com.maneater.app.sport.net.vo.PhoneVerCodeVo;
import com.maneater.app.sport.net.vo.RegisterVo;
import com.maneater.base.util.InjectUtil;
import com.maneater.base.util.SystemUtil;
import com.maneater.base.util.ToastUtil;
import com.maneater.base.util.ValidateUtils;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;

public class RegisterPassActivity extends BaseActivity implements View.OnClickListener {


    public static void launch(Activity activity, String phoneNumber) {
        Intent intent = new Intent(activity, RegisterPassActivity.class);
        intent.putExtra(KEY_1, phoneNumber);
        SystemUtil.launchActivity(activity, intent);
    }

    private PublishSubject<String> getPhoneCodeCMD = PublishSubject.create();
    private PublishSubject<Result<Void>> postResultCMD = PublishSubject.create();

    private PublishSubject<Void> registerCMD = PublishSubject.create();
    private PublishSubject<Result<XAccount>> postRegisterResultCMD = PublishSubject.create();

    private View vBtnConfirmRegisterACK;
    private EditText vEtxPassword;
    private EditText vEtxPhoneVerCode;
    private TextView vEtxResendTip;
    private TextView vTxTip;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register_pass;
    }

    @Override
    protected InjectUtil.InjectViewAble getInjectViewTarget() {
        return this;
    }

    @Override
    protected void onViewClick(int viewId, View view) {
        switch (viewId) {
            case R.id.vEtxResendTip:
                getPhoneCodeCMD.onNext(getIntent().getStringExtra(KEY_1));
                break;
            case R.id.vBtnConfirmRegisterACK:
                if (ValidateUtils.validateBlank(vEtxPhoneVerCode, "验证码") && ValidateUtils.validateBlank(vEtxPassword, "密码")) {
                    registerCMD.onNext(null);
                }
                break;
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    @Override
    protected void initListener() {

        reStartCountDown();

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
                voidResult.setResult(1);
                if (voidResult.isSuccess()) {
                    reStartCountDown();
                    ToastUtil.showToast(getApplicationContext(), "已发送验证码,请注意查收");
                } else {
                    ToastUtil.showToast(getApplicationContext(), voidResult.getMessage());
                }
            }
        }));

        addSubscription(registerCMD.observeOn(AndroidSchedulers.mainThread()).doOnNext(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                showProgress("正在注册");
            }
        }).observeOn(Schedulers.io()).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                postRegisterResultCMD.onNext(WebApiFactory.createApi().register(new RegisterVo().setTelephone(getIntent().getStringExtra(KEY_1)).setPassword(vEtxPassword.getText().toString()).setTelephoneVerCode(vEtxPhoneVerCode.getText().toString())));
            }
        }));

        addSubscription(postRegisterResultCMD.observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Result<XAccount>>() {
            @Override
            public void call(Result<XAccount> accountResult) {
                dismissProgress();
                if (accountResult.isSuccess()) {
                    finish();
                    ToastUtil.showToast(getApplicationContext(), "注册成功");
                } else {
                    ToastUtil.showToast(getApplicationContext(), accountResult.getMessage());
                }
            }
        }));

    }

    private Subscription timeCountSubscription = null;

    private void reStartCountDown() {
        vEtxResendTip.setOnClickListener(null);
        timeCountSubscription = Observable.interval(0, 1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                if (aLong <= 120) {
                    vEtxResendTip.setText(getString(R.string.phone_code_resend_tip, aLong));
                } else {
                    vEtxResendTip.setText("点击发送验证码");
                    vEtxResendTip.setOnClickListener(RegisterPassActivity.this);
                    timeCountSubscription.unsubscribe();
                }
            }
        });
    }

    @Override
    protected void initData() {
        vTxTip.setText(getString(R.string.phone_send_tip, getIntent().getStringExtra(KEY_1)));
    }

    @Override
    public void onClick(View view) {
        onViewClick(view.getId(), view);
    }
}
