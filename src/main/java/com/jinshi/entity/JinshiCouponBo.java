package com.jinshi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class JinshiCouponBo {

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

    private String couponBcName;

    private String couponAgentName;

    private String couponParkingName;

    private String couponAreaName;

    private Integer couponAllCount;

    private Integer couponDataA;

    private String couponDataF;

    private String jcgReliefAlltime;

    private String jcgReliefRemaintime;

    private String jcgReliefAllmoney;

    private String jcgReliefRemainmoney;

    private Integer jcgType;

    private Integer jcgCount;

    private Integer parkId;

    public String getCouponDataF() {
        return couponDataF;
    }

    public void setCouponDataF(String couponDataF) {
        this.couponDataF = couponDataF;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public Integer getJcgCount() {
        return jcgCount;
    }

    public void setJcgCount(Integer jcgCount) {
        this.jcgCount = jcgCount;
    }

    public Integer getJcgType() {
        return jcgType;
    }

    public void setJcgType(Integer jcgType) {
        this.jcgType = jcgType;
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

    public Integer getCouponDataA() {
        return couponDataA;
    }

    public void setCouponDataA(Integer couponDataA) {
        this.couponDataA = couponDataA;
    }

    public Integer getCouponAllCount() {
        return couponAllCount;
    }

    public void setCouponAllCount(Integer couponAllCount) {
        this.couponAllCount = couponAllCount;
    }

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
        this.couponName = couponName;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
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

    public String getCouponBcName() {
        return couponBcName;
    }

    public void setCouponBcName(String couponBcName) {
        this.couponBcName = couponBcName;
    }

    public String getCouponAgentName() {
        return couponAgentName;
    }

    public void setCouponAgentName(String couponAgentName) {
        this.couponAgentName = couponAgentName;
    }

    public String getCouponParkingName() {
        return couponParkingName;
    }

    public void setCouponParkingName(String couponParkingName) {
        this.couponParkingName = couponParkingName;
    }

    public String getCouponAreaName() {
        return couponAreaName;
    }

    public void setCouponAreaName(String couponAreaName) {
        this.couponAreaName = couponAreaName;
    }
}
