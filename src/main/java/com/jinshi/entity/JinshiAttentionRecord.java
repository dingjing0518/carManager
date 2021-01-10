package com.jinshi.entity;

import java.util.Date;

public class JinshiAttentionRecord {
    private Integer id;

    private String jaPlate;

    private Date jaInboundTime;

    private Date jaDepartureTime;

    private String jaInboundCname;

    private String jaDepartureCname;

    private String jaOrderId;

    private String jaRealCost;

    private String jaRemark;

    private Integer jaParkId;

    private String jaDataB;

    private String jaDataC;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJaPlate() {
        return jaPlate;
    }

    public void setJaPlate(String jaPlate) {
        this.jaPlate = jaPlate == null ? null : jaPlate.trim();
    }

    public Date getJaInboundTime() {
        return jaInboundTime;
    }

    public void setJaInboundTime(Date jaInboundTime) {
        this.jaInboundTime = jaInboundTime;
    }

    public Date getJaDepartureTime() {
        return jaDepartureTime;
    }

    public void setJaDepartureTime(Date jaDepartureTime) {
        this.jaDepartureTime = jaDepartureTime;
    }

    public String getJaInboundCname() {
        return jaInboundCname;
    }

    public void setJaInboundCname(String jaInboundCname) {
        this.jaInboundCname = jaInboundCname == null ? null : jaInboundCname.trim();
    }

    public String getJaDepartureCname() {
        return jaDepartureCname;
    }

    public void setJaDepartureCname(String jaDepartureCname) {
        this.jaDepartureCname = jaDepartureCname == null ? null : jaDepartureCname.trim();
    }

    public String getJaOrderId() {
        return jaOrderId;
    }

    public void setJaOrderId(String jaOrderId) {
        this.jaOrderId = jaOrderId == null ? null : jaOrderId.trim();
    }

    public String getJaRealCost() {
        return jaRealCost;
    }

    public void setJaRealCost(String jaRealCost) {
        this.jaRealCost = jaRealCost == null ? null : jaRealCost.trim();
    }

    public String getJaRemark() {
        return jaRemark;
    }

    public void setJaRemark(String jaRemark) {
        this.jaRemark = jaRemark == null ? null : jaRemark.trim();
    }

    public Integer getJaParkId() {
        return jaParkId;
    }

    public void setJaParkId(Integer jaParkId) {
        this.jaParkId = jaParkId;
    }

    public String getJaDataB() {
        return jaDataB;
    }

    public void setJaDataB(String jaDataB) {
        this.jaDataB = jaDataB == null ? null : jaDataB.trim();
    }

    public String getJaDataC() {
        return jaDataC;
    }

    public void setJaDataC(String jaDataC) {
        this.jaDataC = jaDataC == null ? null : jaDataC.trim();
    }
}