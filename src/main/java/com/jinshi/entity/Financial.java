package com.jinshi.entity;

import java.util.Date;

public class Financial {
    private Integer id;

    private String memberIds;

    private Integer payMethod;

    private Date payTime;

    private String payMoney;

    private Integer serviceTypes;

    private String lincensePlateIds;

    private Date expirationTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(String memberIds) {
        this.memberIds = memberIds == null ? null : memberIds.trim();
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney == null ? null : payMoney.trim();
    }

    public Integer getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(Integer serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public String getLincensePlateIds() {
        return lincensePlateIds;
    }

    public void setLincensePlateIds(String lincensePlateIds) {
        this.lincensePlateIds = lincensePlateIds;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }
}