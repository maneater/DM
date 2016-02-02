package com.maneater.dm.app.model;

public class Hospital {
    private String name;
    private String desc;
    private String address;
    private String tel;
    private String picUrl;
    private double score;
    private String tags;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public static Hospital demo() {
        Hospital hospital = new Hospital();
        hospital.setName("A Hospital");
        hospital.picUrl = "http://s2.cn.bing.net/th?id=OJ.ZM00HjKhTav1vA&pid=MSNJVFeeds";
        hospital.setAddress("This is a simple hospital in China");
        return hospital;
    }
}
