package com.jinshi.entity;

import java.util.Date;

public class OutInRecord {
    private Integer id;

    private String lincensePlateId;

    private Date entryTime;

    private Date exitTime;

    private Integer userType;

    private Integer abnormal;

    private String money;

    private String site;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLincensePlateId() {
        return lincensePlateId;
    }

    public void setLincensePlateId(String lincensePlateId) {
        this.lincensePlateId = lincensePlateId == null ? null : lincensePlateId.trim();
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getAbnormal() {
        return abnormal;
    }

    public void setAbnormal(Integer abnormal) {
        this.abnormal = abnormal;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }
}