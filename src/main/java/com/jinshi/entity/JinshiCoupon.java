package com.jinshi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class JinshiCoupon {
    private Integer couponId;

    private String couponName;

    private String couponType;

    private Integer couponCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date couponStarttime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date couponEndtime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date couponCreatetime;

    private Integer couponBcId;

    private Integer couponAgentId;

    private Integer couponParkingId;

    private Integer couponAreaId;

    private String couponDataA;

    private String jcgReliefAlltime;

    private String jcgReliefRemaintime;

    private String jcgReliefAllmoney;

    private String jcgReliefRemainmoney;

    private String couponDataF;

    private String couponDataG;

    private String couponDataH;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType == null ? null : couponType.trim();
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public Date getCouponStarttime() {
        return couponStarttime;
    }

    public void setCouponStarttime(Date couponStarttime) {
        this.couponStarttime = couponStarttime;
    }

    public Date getCouponEndtime() {
        return couponEndtime;
    }

    public void setCouponEndtime(Date couponEndtime) {
        this.couponEndtime = couponEndtime;
    }

    public Date getCouponCreatetime() {
        return couponCreatetime;
    }

    public void setCouponCreatetime(Date couponCreatetime) {
        this.couponCreatetime = couponCreatetime;
    }

    public Integer getCouponBcId() {
        return couponBcId;
    }

    public void setCouponBcId(Integer couponBcId) {
        this.couponBcId = couponBcId;
    }

    public Integer getCouponAgentId() {
        return couponAgentId;
    }

    public void setCouponAgentId(Integer couponAgentId) {
        this.couponAgentId = couponAgentId;
    }

    public Integer getCouponParkingId() {
        return couponParkingId;
    }

    public void setCouponParkingId(Integer couponParkingId) {
        this.couponParkingId = couponParkingId;
    }

    public Integer getCouponAreaId() {
        return couponAreaId;
    }

    public void setCouponAreaId(Integer couponAreaId) {
        this.couponAreaId = couponAreaId;
    }

    public String getCouponDataA() {
        return couponDataA;
    }

    public void setCouponDataA(String couponDataA) {
        this.couponDataA = couponDataA == null ? null : couponDataA.trim();
    }

    public String getJcgReliefAlltime() {
        return jcgReliefAlltime;
    }

    public void setJcgReliefAlltime(String jcgReliefAlltime) {
        this.jcgReliefAlltime = jcgReliefAlltime;
    }

    public String getJcgReliefRemaintime() {
        return jcgReliefRemaintime;
    }

    public void setJcgReliefRemaintime(String jcgReliefRemaintime) {
        this.jcgReliefRemaintime = jcgReliefRemaintime;
    }

    public String getJcgReliefAllmoney() {
        return jcgReliefAllmoney;
    }

    public void setJcgReliefAllmoney(String jcgReliefAllmoney) {
        this.jcgReliefAllmoney = jcgReliefAllmoney;
    }

    public String getJcgReliefRemainmoney() {
        return jcgReliefRemainmoney;
    }

    public void setJcgReliefRemainmoney(String jcgReliefRemainmoney) {
        this.jcgReliefRemainmoney = jcgReliefRemainmoney;
    }

    public String getCouponDataF() {
        return couponDataF;
    }

    public void setCouponDataF(String couponDataF) {
        this.couponDataF = couponDataF == null ? null : couponDataF.trim();
    }

    public String getCouponDataG() {
        return couponDataG;
    }

    public void setCouponDataG(String couponDataG) {
        this.couponDataG = couponDataG == null ? null : couponDataG.trim();
    }

    public String getCouponDataH() {
        return couponDataH;
    }

    public void setCouponDataH(String couponDataH) {
        this.couponDataH = couponDataH == null ? null : couponDataH.trim();
    }
}