package com.jinshi.entity;

import java.util.Date;

public class BlackList {
    private Integer id;

    private String name;

    private String lincensePlateNumber;

    private String describtion;

    private String listType;

    private Date createTime;

    private String isFlag;

    private Integer parkId;

    private Integer agentId;

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLincensePlateNumber() {
        return lincensePlateNumber;
    }

    public void setLincensePlateNumber(String lincensePlateNumber) {
        this.lincensePlateNumber = lincensePlateNumber == null ? null : lincensePlateNumber.trim();
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion == null ? null : describtion.trim();
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(String isFlag) {
        this.isFlag = isFlag;
    }
}