package com.maneater.app.sport.net.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by liang on 16/2/20.
 */
public class LoginVo {
    @Expose
    private String passwd;
    @Expose
    @SerializedName("loginName")
    private String telephone;

    public String getPasswd() {
        return passwd;
    }

    public LoginVo setPasswd(String passwd) {
        this.passwd = passwd;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public LoginVo setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }
}
