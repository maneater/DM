package com.maneater.app.sport.account;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.GsonBuilder;
import com.maneater.app.sport.net.Result;
import com.maneater.app.sport.net.WebApiFactory;
import com.maneater.app.sport.net.vo.LoginVo;
import com.maneater.base.util.StringUtils;

public class XAccountManager {

    private Context mContext;
    private static XAccountManager instance = null;

    private SharedPreferences sharedPreferences = null;


    private XAccountManager(Context mContext) {
        this.mContext = mContext;
        this.sharedPreferences = this.mContext.getSharedPreferences("account", Context.MODE_PRIVATE);
    }

    public static XAccountManager getInstance(Context mContext) {
        if (instance == null) {
            instance = new XAccountManager(mContext);
        }
        return instance;
    }

    public static XAccountManager getInstance() {
        return instance;
    }


    public String peekSession() {
        return sharedPreferences.getString("key_sie", null);
    }

//    public String getSession() {
//        String sid = peekSession();
//        if (StringUtils.isBlank(sid)) {
//            XAccount xAccount = getCacheAccount();
//            if (xAccount != null) {
//                login(new LoginVo().setTelephone(xAccount.getTelephone()).setPasswd(xAccount.getPassword()))
//            }
//            sid = peekSession();
//        }
//        return sid;
//    }

    public XAccountManager deleteSession() {
        sharedPreferences.edit().putString("key_sid", null).apply();
        return this;
    }


    public synchronized Result<XAccount> login(LoginVo loginVo) {
        Result<XAccount> result = WebApiFactory.createApi().login(loginVo);
        deleteSession();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (result != null && result.isSuccess() && StringUtils.isNotBlank(WebApiFactory.Cache_Sid)) {
            XAccount xAccount = result.getData();
            if (xAccount != null) {
                editor.putString("key_account", new GsonBuilder().create().toJson(result.getData()));
            }
            editor.putString("key_sid", WebApiFactory.Cache_Sid);
        }
        editor.commit();
        return result;
    }

    public void logout() {
        deleteSession();
        deleteAccount();
    }

    private void deleteAccount() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key_account", null);
        editor.commit();
    }


    public boolean isLogin() {
        return StringUtils.isNotBlank(peekSession());
    }

    public XAccount getLoginAccount() {
        if (isLogin()) {
            return getCacheAccount();
        }
        return null;
    }

    public XAccount getCacheAccount() {

        String accountStr = sharedPreferences.getString("key_account", null);
        XAccount xAccount = new GsonBuilder().create().fromJson(accountStr, XAccount.class);
        if (xAccount == null) {
            deleteSession();
            deleteAccount();
        }
        return xAccount;
    }


}
