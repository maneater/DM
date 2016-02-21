package com.maneater.app.sport.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.maneater.app.sport.R;
import com.maneater.app.sport.account.XAccount;
import com.maneater.app.sport.account.XAccountManager;
import com.maneater.app.sport.net.Result;
import com.maneater.app.sport.net.vo.LoginVo;
import com.maneater.base.util.InjectUtil;
import com.maneater.base.util.ToastUtil;
import com.maneater.base.util.ValidateUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class LogoActivity extends BaseActivity {

    ////登陆布局
    private ViewGroup vLytLogin;
    private View vBtnLoginACK;
    private View vTxToResetPasswordACK;
    private View vTxToRegisterACK;
    private EditText vEtxLogin;
    private EditText vEtxPassword;

    private PublishSubject<Void> loginCMD = PublishSubject.create();
    private PublishSubject<Result<XAccount>> postLoginResultCMD = PublishSubject.create();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_logo;
    }

    @Override
    protected InjectUtil.InjectViewAble getInjectViewTarget() {
        return this;
    }

    @Override
    protected void onViewClick(int viewId, View view) {
        switch (viewId) {
            case R.id.vBtnLoginACK:
                if (ValidateUtils.validatePhone(vEtxLogin) && ValidateUtils.validateBlank(vEtxPassword, "密码")) {
                    loginCMD.onNext(null);
                }
                break;
            case R.id.vTxToResetPasswordACK:
                break;
            case R.id.vTxToRegisterACK:
                RegisterActivity.launch(LogoActivity.this);
                break;
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {
        addSubscription(loginCMD.observeOn(AndroidSchedulers.mainThread()).doOnNext(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                showProgress("正在登陆");
            }
        }).observeOn(Schedulers.io()).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                postLoginResultCMD.onNext(XAccountManager.getInstance(getApplicationContext()).login(new LoginVo().setTelephone(vEtxLogin.getText().toString()).setPasswd(vEtxPassword.getText().toString())));
            }
        }));
        addSubscription(postLoginResultCMD.observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Result<XAccount>>() {
            @Override
            public void call(Result<XAccount> accountResult) {
                dismissProgress();
                if (accountResult.isSuccess()) {
                    ToastUtil.showToast(getApplicationContext(), "登陆成功");
                    MainActivity.launch(LogoActivity.this);
                    finish();
                } else {
                    ToastUtil.showToast(getApplicationContext(), accountResult.getMessage());
                }
            }
        }));
    }
}
