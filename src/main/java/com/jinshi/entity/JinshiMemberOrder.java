package com.jinshi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class JinshiMemberOrder {
    private Integer jmoId;

    private Integer jmoMemberTableId;

    private String jmoMemberId;

    private String jmoServiceType;

    private String jmoServiceNumber;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jmoJoinTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jmoExpirationTime;

    private Date jmoCreatTime;

    private BigDecimal jmoPayable;

    private BigDecimal jmoActualCost;

    private String jmoPhoneNumber;

    private String jmoAreaName;

    private Integer jmoParkId;

    private Integer jmoAgentId;

    private String jmoEnterUser;

    private String jmoOrderId;

    private String jmoDataB;

    private String jmoDataC;

    private String jmoDataD;

    private String jmoDataE;

    private String jmoLincensePlate;

    public String getJmoLincensePlate() {
        return jmoLincensePlate;
    }

    public void setJmoLincensePlate(String jmoLincensePlate) {
        this.jmoLincensePlate = jmoLincensePlate;
    }

    public Integer getJmoId() {
        return jmoId;
    }

    public void setJmoId(Integer jmoId) {
        this.jmoId = jmoId;
    }

    public Integer getJmoMemberTableId() {
        return jmoMemberTableId;
    }

    public void setJmoMemberTableId(Integer jmoMemberTableId) {
        this.jmoMemberTableId = jmoMemberTableId;
    }

    public String getJmoMemberId() {
        return jmoMemberId;
    }

    public void setJmoMemberId(String jmoMemberId) {
        this.jmoMemberId = jmoMemberId == null ? null : jmoMemberId.trim();
    }

    public String getJmoServiceType() {
        return jmoServiceType;
    }

    public void setJmoServiceType(String jmoServiceType) {
        this.jmoServiceType = jmoServiceType == null ? null : jmoServiceType.trim();
    }

    public String getJmoServiceNumber() {
        return jmoServiceNumber;
    }

    public void setJmoServiceNumber(String jmoServiceNumber) {
        this.jmoServiceNumber = jmoServiceNumber == null ? null : jmoServiceNumber.trim();
    }

    public Date getJmoJoinTime() {
        return jmoJoinTime;
    }

    public void setJmoJoinTime(Date jmoJoinTime) {
        this.jmoJoinTime = jmoJoinTime;
    }

    public Date getJmoExpirationTime() {
        return jmoExpirationTime;
    }

    public void setJmoExpirationTime(Date jmoExpirationTime) {
        this.jmoExpirationTime = jmoExpirationTime;
    }

    public Date getJmoCreatTime() {
        return jmoCreatTime;
    }

    public void setJmoCreatTime(Date jmoCreatTime) {
        this.jmoCreatTime = jmoCreatTime;
    }

    public BigDecimal getJmoPayable() {
        return jmoPayable;
    }

    public void setJmoPayable(BigDecimal jmoPayable) {
        this.jmoPayable = jmoPayable;
    }

    public BigDecimal getJmoActualCost() {
        return jmoActualCost;
    }

    public void setJmoActualCost(BigDecimal jmoActualCost) {
        this.jmoActualCost = jmoActualCost;
    }

    public String getJmoPhoneNumber() {
        return jmoPhoneNumber;
    }

    public void setJmoPhoneNumber(String jmoPhoneNumber) {
        this.jmoPhoneNumber = jmoPhoneNumber == null ? null : jmoPhoneNumber.trim();
    }

    public String getJmoAreaName() {
        return jmoAreaName;
    }

    public void setJmoAreaName(String jmoAreaName) {
        this.jmoAreaName = jmoAreaName == null ? null : jmoAreaName.trim();
    }

    public Integer getJmoParkId() {
        return jmoParkId;
    }

    public void setJmoParkId(Integer jmoParkId) {
        this.jmoParkId = jmoParkId;
    }

    public Integer getJmoAgentId() {
        return jmoAgentId;
    }

    public void setJmoAgentId(Integer jmoAgentId) {
        this.jmoAgentId = jmoAgentId;
    }

    public String getJmoEnterUser() {
        return jmoEnterUser;
    }

    public void setJmoEnterUser(String jmoEnterUser) {
        this.jmoEnterUser = jmoEnterUser == null ? null : jmoEnterUser.trim();
    }

    public String getJmoOrderId() {
        return jmoOrderId;
    }

    public void setJmoOrderId(String jmoOrderId) {
        this.jmoOrderId = jmoOrderId;
    }

    public String getJmoDataB() {
        return jmoDataB;
    }

    public void setJmoDataB(String jmoDataB) {
        this.jmoDataB = jmoDataB == null ? null : jmoDataB.trim();
    }

    public String getJmoDataC() {
        return jmoDataC;
    }

    public void setJmoDataC(String jmoDataC) {
        this.jmoDataC = jmoDataC == null ? null : jmoDataC.trim();
    }

    public String getJmoDataD() {
        return jmoDataD;
    }

    public void setJmoDataD(String jmoDataD) {
        this.jmoDataD = jmoDataD == null ? null : jmoDataD.trim();
    }

    public String getJmoDataE() {
        return jmoDataE;
    }

    public void setJmoDataE(String jmoDataE) {
        this.jmoDataE = jmoDataE == null ? null : jmoDataE.trim();
    }
}