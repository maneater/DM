package com.maneater.app.sport.net.vo;

import com.google.gson.annotations.Expose;

/**
 * Created by liang on 16/2/20.
 */
public class RegisterVo {
    @Expose
    private String telephone;
    @Expose
    private String password;
    @Expose
    private String telephoneVerCode;

    public String getTelephoneVerCode() {
        return telephoneVerCode;
    }

    public RegisterVo setTelephoneVerCode(String telephoneVerCode) {
        this.telephoneVerCode = telephoneVerCode;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public RegisterVo setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterVo setPassword(String password) {
        this.password = password;
        return this;
    }
}
