package com.maneater.dm.app.model;

import com.google.gson.annotations.Expose;

/**
 * Created by Administrator on 2016/1/20 0020.
 */
public class Weather {
    /**
     * city : 北京
     * cityId : 101010100
     * temp1 : 15℃
     * temp2 : 5℃
     * weather : 多云
     * img1 : d1.gif
     * img2 : n1.gif
     * pTime : 08:00
     */
    @Expose
    private String city;
    @Expose
    private String cityId;
    @Expose
    private String temp1;
    @Expose
    private String temp2;
    @Expose
    private String weather;
    @Expose
    private String img1;
    @Expose
    private String img2;
    @Expose
    private String pTime;

    public void setCity(String city) {
        this.city = city;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public void setpTime(String pTime) {
        this.pTime = pTime;
    }

    public String getCity() {
        return city;
    }

    public String getCityId() {
        return cityId;
    }

    public String getTemp1() {
        return temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public String getWeather() {
        return weather;
    }

    public String getImg1() {
        return img1;
    }

    public String getImg2() {
        return img2;
    }

    public String getpTime() {
        return pTime;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", cityId='" + cityId + '\'' +
                ", temp1='" + temp1 + '\'' +
                ", temp2='" + temp2 + '\'' +
                ", weather='" + weather + '\'' +
                ", img1='" + img1 + '\'' +
                ", img2='" + img2 + '\'' +
                ", pTime='" + pTime + '\'' +
                '}';
    }
}
