package com.jinshi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class JinshiCouponOrder {
    private Integer jcoId;

    private String jcoPlate;

    private String jcoType;

    private String jcoCarColor;

    private String jcoCarType;

    private String jcoOrderId;

    private String jcoOften;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jcoInboundTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jcoDepartureTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jcoCreatetime;

    private Integer jcoAgentId;

    private Integer jcoParkingId;

    private Integer jcoAreaId;

    private Integer jcoCouponId;

    private Integer jcoCouponGenerateId;

    private String jcoDataA;

    private String jcoDataB;

    private String jcoDataC;

    private String jcoDataD;

    private String jcoDataE;

    private String jcoDataF;

    private String jcoDataG;

    private String jcoDataH;

    public Integer getJcoCouponGenerateId() {
        return jcoCouponGenerateId;
    }

    public void setJcoCouponGenerateId(Integer jcoCouponGenerateId) {
        this.jcoCouponGenerateId = jcoCouponGenerateId;
    }

    public Integer getJcoId() {
        return jcoId;
    }

    public void setJcoId(Integer jcoId) {
        this.jcoId = jcoId;
    }

    public String getJcoPlate() {
        return jcoPlate;
    }

    public void setJcoPlate(String jcoPlate) {
        this.jcoPlate = jcoPlate == null ? null : jcoPlate.trim();
    }

    public String getJcoType() {
        return jcoType;
    }

    public void setJcoType(String jcoType) {
        this.jcoType = jcoType == null ? null : jcoType.trim();
    }

    public String getJcoCarColor() {
        return jcoCarColor;
    }

    public void setJcoCarColor(String jcoCarColor) {
        this.jcoCarColor = jcoCarColor == null ? null : jcoCarColor.trim();
    }

    public String getJcoCarType() {
        return jcoCarType;
    }

    public void setJcoCarType(String jcoCarType) {
        this.jcoCarType = jcoCarType == null ? null : jcoCarType.trim();
    }

    public String getJcoOrderId() {
        return jcoOrderId;
    }

    public void setJcoOrderId(String jcoOrderId) {
        this.jcoOrderId = jcoOrderId == null ? null : jcoOrderId.trim();
    }

    public String getJcoOften() {
        return jcoOften;
    }

    public void setJcoOften(String jcoOften) {
        this.jcoOften = jcoOften == null ? null : jcoOften.trim();
    }

    public Date getJcoInboundTime() {
        return jcoInboundTime;
    }

    public void setJcoInboundTime(Date jcoInboundTime) {
        this.jcoInboundTime = jcoInboundTime;
    }

    public Date getJcoDepartureTime() {
        return jcoDepartureTime;
    }

    public void setJcoDepartureTime(Date jcoDepartureTime) {
        this.jcoDepartureTime = jcoDepartureTime;
    }

    public Date getJcoCreatetime() {
        return jcoCreatetime;
    }

    public void setJcoCreatetime(Date jcoCreatetime) {
        this.jcoCreatetime = jcoCreatetime;
    }

    public Integer getJcoAgentId() {
        return jcoAgentId;
    }

    public void setJcoAgentId(Integer jcoAgentId) {
        this.jcoAgentId = jcoAgentId;
    }

    public Integer getJcoParkingId() {
        return jcoParkingId;
    }

    public void setJcoParkingId(Integer jcoParkingId) {
        this.jcoParkingId = jcoParkingId;
    }

    public Integer getJcoAreaId() {
        return jcoAreaId;
    }

    public void setJcoAreaId(Integer jcoAreaId) {
        this.jcoAreaId = jcoAreaId;
    }

    public Integer getJcoCouponId() {
        return jcoCouponId;
    }

    public void setJcoCouponId(Integer jcoCouponId) {
        this.jcoCouponId = jcoCouponId;
    }

    public String getJcoDataA() {
        return jcoDataA;
    }

    public void setJcoDataA(String jcoDataA) {
        this.jcoDataA = jcoDataA == null ? null : jcoDataA.trim();
    }

    public String getJcoDataB() {
        return jcoDataB;
    }

    public void setJcoDataB(String jcoDataB) {
        this.jcoDataB = jcoDataB == null ? null : jcoDataB.trim();
    }

    public String getJcoDataC() {
        return jcoDataC;
    }

    public void setJcoDataC(String jcoDataC) {
        this.jcoDataC = jcoDataC == null ? null : jcoDataC.trim();
    }

    public String getJcoDataD() {
        return jcoDataD;
    }

    public void setJcoDataD(String jcoDataD) {
        this.jcoDataD = jcoDataD == null ? null : jcoDataD.trim();
    }

    public String getJcoDataE() {
        return jcoDataE;
    }

    public void setJcoDataE(String jcoDataE) {
        this.jcoDataE = jcoDataE == null ? null : jcoDataE.trim();
    }

    public String getJcoDataF() {
        return jcoDataF;
    }

    public void setJcoDataF(String jcoDataF) {
        this.jcoDataF = jcoDataF == null ? null : jcoDataF.trim();
    }

    public String getJcoDataG() {
        return jcoDataG;
    }

    public void setJcoDataG(String jcoDataG) {
        this.jcoDataG = jcoDataG == null ? null : jcoDataG.trim();
    }

    public String getJcoDataH() {
        return jcoDataH;
    }

    public void setJcoDataH(String jcoDataH) {
        this.jcoDataH = jcoDataH == null ? null : jcoDataH.trim();
    }
}