package com.jinshi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class JinshiBusinessAccountBo {

    private Integer bcId;

    private String bcName;

    private String bcCode;

    private String bcPhone;

    private String bcTel;

    private String bcWechat;

    private String bcQq;

    private String bcOpenid;

    private String bcContactsName;

    private String bcUsername;

    private String bcPassword;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bcCreatetime;

    private String bcRemarks;

    private String bcAgentName;

    private String bcParkingName;

    private String bcAreaName;

    private Integer parkId;

    private Integer areaId;

    private Integer agentId;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public Integer getBcId() {
        return bcId;
    }

    public void setBcId(Integer bcId) {
        this.bcId = bcId;
    }

    public String getBcName() {
        return bcName;
    }

    public void setBcName(String bcName) {
        this.bcName = bcName;
    }

    public String getBcCode() {
        return bcCode;
    }

    public void setBcCode(String bcCode) {
        this.bcCode = bcCode;
    }

    public String getBcPhone() {
        return bcPhone;
    }

    public void setBcPhone(String bcPhone) {
        this.bcPhone = bcPhone;
    }

    public String getBcTel() {
        return bcTel;
    }

    public void setBcTel(String bcTel) {
        this.bcTel = bcTel;
    }

    public String getBcWechat() {
        return bcWechat;
    }

    public void setBcWechat(String bcWechat) {
        this.bcWechat = bcWechat;
    }

    public String getBcQq() {
        return bcQq;
    }

    public void setBcQq(String bcQq) {
        this.bcQq = bcQq;
    }

    public String getBcOpenid() {
        return bcOpenid;
    }

    public void setBcOpenid(String bcOpenid) {
        this.bcOpenid = bcOpenid;
    }

    public String getBcContactsName() {
        return bcContactsName;
    }

    public void setBcContactsName(String bcContactsName) {
        this.bcContactsName = bcContactsName;
    }

    public String getBcUsername() {
        return bcUsername;
    }

    public void setBcUsername(String bcUsername) {
        this.bcUsername = bcUsername;
    }

    public String getBcPassword() {
        return bcPassword;
    }

    public void setBcPassword(String bcPassword) {
        this.bcPassword = bcPassword;
    }

    public Date getBcCreatetime() {
        return bcCreatetime;
    }

    public void setBcCreatetime(Date bcCreatetime) {
        this.bcCreatetime = bcCreatetime;
    }

    public String getBcRemarks() {
        return bcRemarks;
    }

    public void setBcRemarks(String bcRemarks) {
        this.bcRemarks = bcRemarks;
    }

    public String getBcAgentName() {
        return bcAgentName;
    }

    public void setBcAgentName(String bcAgentName) {
        this.bcAgentName = bcAgentName;
    }

    public String getBcParkingName() {
        return bcParkingName;
    }

    public void setBcParkingName(String bcParkingName) {
        this.bcParkingName = bcParkingName;
    }

    public String getBcAreaName() {
        return bcAreaName;
    }

    public void setBcAreaName(String bcAreaName) {
        this.bcAreaName = bcAreaName;
    }
}
