package com.maneater.app.sport.model;

import com.google.gson.annotations.Expose;

/**
 * Created by liang on 16/2/21.
 */
public class ActivitySport {

//    activityId	活动ID	Y
//    activityName	活动名称	Y
//    activityDesc	活动说明	Y
//    startAddr	出发地	Y
//    endAddr	结束地	Y
//    level	级别	Y
//    distance	距离	Y
//    price	价格	Y
//    peopleNumMax	人数上限	N
//    peopleNumMin	人数下限	N
//    startTime	开始时间	Y
//    endTime	结束时间	Y
//    rendezvous	集合地	Y
//    linkmanName	联系人	Y
//    linkmanTelepone	联系人电话	Y
//    register	是否已报名	Y	Y:是
//    N：否


    @Expose
    private String activityId;
    @Expose
    private String activityName;
    @Expose
    private String activityDesc;
    @Expose
    private String startAddr;
    @Expose
    private String endAddr;
    @Expose
    private String level;
    @Expose
    private String linkmanTelepone;
    @Expose
    private String register;
    @Expose
    private Integer peopleNumMax;
    @Expose
    private Integer peopleNumMin;
    @Expose
    private Long startTime;
    @Expose
    private Long endTime;
    @Expose
    private Double distance;
    @Expose
    private String backgroundPic;

    public String getActivityId() {
        return activityId;
    }

    public ActivitySport setActivityId(String activityId) {
        this.activityId = activityId;
        return this;
    }

    public String getActivityName() {
        return activityName;
    }

    public ActivitySport setActivityName(String activityName) {
        this.activityName = activityName;
        return this;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public ActivitySport setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
        return this;
    }

    public String getStartAddr() {
        return startAddr;
    }

    public ActivitySport setStartAddr(String startAddr) {
        this.startAddr = startAddr;
        return this;
    }

    public String getEndAddr() {
        return endAddr;
    }

    public ActivitySport setEndAddr(String endAddr) {
        this.endAddr = endAddr;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public ActivitySport setLevel(String level) {
        this.level = level;
        return this;
    }

    public String getLinkmanTelepone() {
        return linkmanTelepone;
    }

    public ActivitySport setLinkmanTelepone(String linkmanTelepone) {
        this.linkmanTelepone = linkmanTelepone;
        return this;
    }

    public String getRegister() {
        return register;
    }

    public ActivitySport setRegister(String register) {
        this.register = register;
        return this;
    }

    public Integer getPeopleNumMax() {
        return peopleNumMax;
    }

    public ActivitySport setPeopleNumMax(Integer peopleNumMax) {
        this.peopleNumMax = peopleNumMax;
        return this;
    }

    public Integer getPeopleNumMin() {
        return peopleNumMin;
    }

    public ActivitySport setPeopleNumMin(Integer peopleNumMin) {
        this.peopleNumMin = peopleNumMin;
        return this;
    }

    public Long getStartTime() {
        return startTime;
    }

    public ActivitySport setStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public Long getEndTime() {
        return endTime;
    }

    public ActivitySport setEndTime(Long endTime) {
        this.endTime = endTime;
        return this;
    }

    public Double getDistance() {
        return distance;
    }

    public ActivitySport setDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    public String getBackgroundPic() {
        return backgroundPic;
    }

    public ActivitySport setBackgroundPic(String backgroundPic) {
        this.backgroundPic = backgroundPic;
        return this;
    }
}
