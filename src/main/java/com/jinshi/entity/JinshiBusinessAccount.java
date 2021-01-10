package com.jinshi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class JinshiBusinessAccount {
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

    private Integer bcAgentId;

    private Integer bcParkingId;

    private Integer bcAreaId;

    private String bcDataA;

    private String bcDataB;

    private String bcDataC;

    private String bcDataD;

    private String bcDataE;

    private String bcDataF;

    private String bcDataG;

    private String bcDataH;

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
        this.bcName = bcName == null ? null : bcName.trim();
    }

    public String getBcCode() {
        return bcCode;
    }

    public void setBcCode(String bcCode) {
        this.bcCode = bcCode == null ? null : bcCode.trim();
    }

    public String getBcPhone() {
        return bcPhone;
    }

    public void setBcPhone(String bcPhone) {
        this.bcPhone = bcPhone == null ? null : bcPhone.trim();
    }

    public String getBcTel() {
        return bcTel;
    }

    public void setBcTel(String bcTel) {
        this.bcTel = bcTel == null ? null : bcTel.trim();
    }

    public String getBcWechat() {
        return bcWechat;
    }

    public void setBcWechat(String bcWechat) {
        this.bcWechat = bcWechat == null ? null : bcWechat.trim();
    }

    public String getBcQq() {
        return bcQq;
    }

    public void setBcQq(String bcQq) {
        this.bcQq = bcQq == null ? null : bcQq.trim();
    }

    public String getBcOpenid() {
        return bcOpenid;
    }

    public void setBcOpenid(String bcOpenid) {
        this.bcOpenid = bcOpenid == null ? null : bcOpenid.trim();
    }

    public String getBcContactsName() {
        return bcContactsName;
    }

    public void setBcContactsName(String bcContactsName) {
        this.bcContactsName = bcContactsName == null ? null : bcContactsName.trim();
    }

    public String getBcUsername() {
        return bcUsername;
    }

    public void setBcUsername(String bcUsername) {
        this.bcUsername = bcUsername == null ? null : bcUsername.trim();
    }

    public String getBcPassword() {
        return bcPassword;
    }

    public void setBcPassword(String bcPassword) {
        this.bcPassword = bcPassword == null ? null : bcPassword.trim();
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
        this.bcRemarks = bcRemarks == null ? null : bcRemarks.trim();
    }

    public Integer getBcAgentId() {
        return bcAgentId;
    }

    public void setBcAgentId(Integer bcAgentId) {
        this.bcAgentId = bcAgentId;
    }

    public Integer getBcParkingId() {
        return bcParkingId;
    }

    public void setBcParkingId(Integer bcParkingId) {
        this.bcParkingId = bcParkingId;
    }

    public Integer getBcAreaId() {
        return bcAreaId;
    }

    public void setBcAreaId(Integer bcAreaId) {
        this.bcAreaId = bcAreaId;
    }

    public String getBcDataA() {
        return bcDataA;
    }

    public void setBcDataA(String bcDataA) {
        this.bcDataA = bcDataA == null ? null : bcDataA.trim();
    }

    public String getBcDataB() {
        return bcDataB;
    }

    public void setBcDataB(String bcDataB) {
        this.bcDataB = bcDataB == null ? null : bcDataB.trim();
    }

    public String getBcDataC() {
        return bcDataC;
    }

    public void setBcDataC(String bcDataC) {
        this.bcDataC = bcDataC == null ? null : bcDataC.trim();
    }

    public String getBcDataD() {
        return bcDataD;
    }

    public void setBcDataD(String bcDataD) {
        this.bcDataD = bcDataD == null ? null : bcDataD.trim();
    }

    public String getBcDataE() {
        return bcDataE;
    }

    public void setBcDataE(String bcDataE) {
        this.bcDataE = bcDataE == null ? null : bcDataE.trim();
    }

    public String getBcDataF() {
        return bcDataF;
    }

    public void setBcDataF(String bcDataF) {
        this.bcDataF = bcDataF == null ? null : bcDataF.trim();
    }

    public String getBcDataG() {
        return bcDataG;
    }

    public void setBcDataG(String bcDataG) {
        this.bcDataG = bcDataG == null ? null : bcDataG.trim();
    }

    public String getBcDataH() {
        return bcDataH;
    }

    public void setBcDataH(String bcDataH) {
        this.bcDataH = bcDataH == null ? null : bcDataH.trim();
    }
}