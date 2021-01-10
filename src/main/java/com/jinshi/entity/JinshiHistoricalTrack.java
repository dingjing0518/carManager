package com.jinshi.entity;

import java.util.Date;

public class JinshiHistoricalTrack {
    private Integer htId;

    private Integer htLpId;

    private Date htCreatTime;

    private String htLincensePlateId;

    private String htThandle;

    private String htStatus;

    private String htParkid;

    private String htDataC;

    private String htDataD;

    private String htDataE;

    private String jaName;

    public String getJaName() {
        return jaName;
    }

    public void setJaName(String jaName) {
        this.jaName = jaName;
    }

    public Integer getHtId() {
        return htId;
    }

    public void setHtId(Integer htId) {
        this.htId = htId;
    }

    public Integer getHtLpId() {
        return htLpId;
    }

    public void setHtLpId(Integer htLpId) {
        this.htLpId = htLpId;
    }

    public Date getHtCreatTime() {
        return htCreatTime;
    }

    public void setHtCreatTime(Date htCreatTime) {
        this.htCreatTime = htCreatTime;
    }

    public String getHtLincensePlateId() {
        return htLincensePlateId;
    }

    public void setHtLincensePlateId(String htLincensePlateId) {
        this.htLincensePlateId = htLincensePlateId == null ? null : htLincensePlateId.trim();
    }

    public String getHtThandle() {
        return htThandle;
    }

    public void setHtThandle(String htThandle) {
        this.htThandle = htThandle == null ? null : htThandle.trim();
    }

    public String getHtStatus() {
        return htStatus;
    }

    public void setHtStatus(String htStatus) {
        this.htStatus = htStatus;
    }

    public String getHtParkid() {
        return htParkid;
    }

    public void setHtParkid(String htParkid) {
        this.htParkid = htParkid;
    }

    public String getHtDataC() {
        return htDataC;
    }

    public void setHtDataC(String htDataC) {
        this.htDataC = htDataC == null ? null : htDataC.trim();
    }

    public String getHtDataD() {
        return htDataD;
    }

    public void setHtDataD(String htDataD) {
        this.htDataD = htDataD == null ? null : htDataD.trim();
    }

    public String getHtDataE() {
        return htDataE;
    }

    public void setHtDataE(String htDataE) {
        this.htDataE = htDataE == null ? null : htDataE.trim();
    }
}