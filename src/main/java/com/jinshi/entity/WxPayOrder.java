package com.jinshi.entity;

import java.util.Date;

public class WxPayOrder {
    private Long id;

    private String outTradeNo;

    private String totalFee;

    private String openId;

    private Date creatTime;

    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "WxPayOrder{" +
                "id=" + id +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", totalFee='" + totalFee + '\'' +
                ", openId='" + openId + '\'' +
                ", creatTime='" + creatTime + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}