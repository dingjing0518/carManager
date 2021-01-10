package com.jinshi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class JinshiCouponOrderBo {

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

    private String jcoAgentName;

    private String jcoParkingName;

    private String jcoAreaName;

    private String jcoCouponName;

    private String jcoCouponGenerateName;

    private Integer jcoCouponGenerateId;

    public Integer getJcoCouponGenerateId() {
        return jcoCouponGenerateId;
    }

    public void setJcoCouponGenerateId(Integer jcoCouponGenerateId) {
        this.jcoCouponGenerateId = jcoCouponGenerateId;
    }

    public String getJcoCouponGenerateName() {
        return jcoCouponGenerateName;
    }

    public void setJcoCouponGenerateName(String jcoCouponGenerateName) {
        this.jcoCouponGenerateName = jcoCouponGenerateName;
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
        this.jcoPlate = jcoPlate;
    }

    public String getJcoType() {
        return jcoType;
    }

    public void setJcoType(String jcoType) {
        this.jcoType = jcoType;
    }

    public String getJcoCarColor() {
        return jcoCarColor;
    }

    public void setJcoCarColor(String jcoCarColor) {
        this.jcoCarColor = jcoCarColor;
    }

    public String getJcoCarType() {
        return jcoCarType;
    }

    public void setJcoCarType(String jcoCarType) {
        this.jcoCarType = jcoCarType;
    }

    public String getJcoOrderId() {
        return jcoOrderId;
    }

    public void setJcoOrderId(String jcoOrderId) {
        this.jcoOrderId = jcoOrderId;
    }

    public String getJcoOften() {
        return jcoOften;
    }

    public void setJcoOften(String jcoOften) {
        this.jcoOften = jcoOften;
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

    public String getJcoAgentName() {
        return jcoAgentName;
    }

    public void setJcoAgentName(String jcoAgentName) {
        this.jcoAgentName = jcoAgentName;
    }

    public String getJcoParkingName() {
        return jcoParkingName;
    }

    public void setJcoParkingName(String jcoParkingName) {
        this.jcoParkingName = jcoParkingName;
    }

    public String getJcoAreaName() {
        return jcoAreaName;
    }

    public void setJcoAreaName(String jcoAreaName) {
        this.jcoAreaName = jcoAreaName;
    }

    public String getJcoCouponName() {
        return jcoCouponName;
    }

    public void setJcoCouponName(String jcoCouponName) {
        this.jcoCouponName = jcoCouponName;
    }
}
