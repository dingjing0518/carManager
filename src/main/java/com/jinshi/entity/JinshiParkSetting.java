package com.jinshi.entity;

import java.util.Date;

public class JinshiParkSetting {
    private Integer jpsId;

    private Integer jpsParkId;

    private Integer jpsAgentId;

    private Integer jpsFreeTime;

    private Integer jpsFirstTime;

    private Double jpsFirstCharge;

    private Integer jpsFollowTime;

    private Double jpsFollowCharge;

    private Double jpsAlldayLimit;

    private Integer jpsAdvanceChargeOuttime;

    private String jpsRemark;

    private Date jpsCreatetime;

    private Integer jpsIsdelete;

    private Integer JpsCarType;

    public Integer getJpsCarType() {
        return JpsCarType;
    }

    public void setJpsCarType(Integer jpsCarType) {
        JpsCarType = jpsCarType;
    }

    public Integer getJpsId() {
        return jpsId;
    }

    public void setJpsId(Integer jpsId) {
        this.jpsId = jpsId;
    }

    public Integer getJpsParkId() {
        return jpsParkId;
    }

    public void setJpsParkId(Integer jpsParkId) {
        this.jpsParkId = jpsParkId;
    }

    public Integer getJpsAgentId() {
        return jpsAgentId;
    }

    public void setJpsAgentId(Integer jpsAgentId) {
        this.jpsAgentId = jpsAgentId;
    }

    public Integer getJpsFreeTime() {
        return jpsFreeTime;
    }

    public void setJpsFreeTime(Integer jpsFreeTime) {
        this.jpsFreeTime = jpsFreeTime;
    }

    public Integer getJpsFirstTime() {
        return jpsFirstTime;
    }

    public void setJpsFirstTime(Integer jpsFirstTime) {
        this.jpsFirstTime = jpsFirstTime;
    }

    public Double getJpsFirstCharge() {
        return jpsFirstCharge;
    }

    public void setJpsFirstCharge(Double jpsFirstCharge) {
        this.jpsFirstCharge = jpsFirstCharge;
    }

    public Integer getJpsFollowTime() {
        return jpsFollowTime;
    }

    public void setJpsFollowTime(Integer jpsFollowTime) {
        this.jpsFollowTime = jpsFollowTime;
    }

    public Double getJpsFollowCharge() {
        return jpsFollowCharge;
    }

    public void setJpsFollowCharge(Double jpsFollowCharge) {
        this.jpsFollowCharge = jpsFollowCharge;
    }

    public Double getJpsAlldayLimit() {
        return jpsAlldayLimit;
    }

    public void setJpsAlldayLimit(Double jpsAlldayLimit) {
        this.jpsAlldayLimit = jpsAlldayLimit;
    }

    public Integer getJpsAdvanceChargeOuttime() {
        return jpsAdvanceChargeOuttime;
    }

    public void setJpsAdvanceChargeOuttime(Integer jpsAdvanceChargeOuttime) {
        this.jpsAdvanceChargeOuttime = jpsAdvanceChargeOuttime;
    }

    public String getJpsRemark() {
        return jpsRemark;
    }

    public void setJpsRemark(String jpsRemark) {
        this.jpsRemark = jpsRemark == null ? null : jpsRemark.trim();
    }

    public Date getJpsCreatetime() {
        return jpsCreatetime;
    }

    public void setJpsCreatetime(Date jpsCreatetime) {
        this.jpsCreatetime = jpsCreatetime;
    }

    public Integer getJpsIsdelete() {
        return jpsIsdelete;
    }

    public void setJpsIsdelete(Integer jpsIsdelete) {
        this.jpsIsdelete = jpsIsdelete;
    }
}