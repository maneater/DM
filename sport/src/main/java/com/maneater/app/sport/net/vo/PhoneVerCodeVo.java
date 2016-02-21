package com.maneater.app.sport.net.vo;

import com.google.gson.annotations.Expose;

/**
 * Created by liang on 16/2/20.
 */
public class PhoneVerCodeVo {
    @Expose
    private String telephone;
    @Expose
    private String type = "register";

    public String getTelephone() {
        return telephone;
    }

    public PhoneVerCodeVo setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getType() {
        return type;
    }

    public PhoneVerCodeVo setType(String type) {
        this.type = type;
        return this;
    }

    public static PhoneVerCodeVo register(String phoneNumber) {
        return new PhoneVerCodeVo().setTelephone(phoneNumber).setType("register");
    }

    public static PhoneVerCodeVo resetPass(String phoneNumber) {
        return new PhoneVerCodeVo().setTelephone(phoneNumber).setType("fpwd");
    }
}
