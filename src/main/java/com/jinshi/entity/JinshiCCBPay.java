package com.jinshi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class JinshiCCBPay {
    private Integer ccbId;

    private String ccbCarNumber;

    private String ccbMoney;

    private String ccbRealMoney;

    private String ccbOften;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ccbCreatTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ccbInTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ccbOutTime;

    private Integer ccbParkId;

    private Integer ccbAgentId;

    private String ccbOrderId;

    private String ccbInThandle;

    private String ccbOutThandle;

    private String ccbPayType;

    private String ccbLgType;

    private String ccbDataA;

    private String ccbDataB;

    private String ccbDataC;

    private String ccbDataD;

    private String ccbDataE;

    public Integer getCcbId() {
        return ccbId;
    }

    public void setCcbId(Integer ccbId) {
        this.ccbId = ccbId;
    }

    public String getCcbCarNumber() {
        return ccbCarNumber;
    }

    public void setCcbCarNumber(String ccbCarNumber) {
        this.ccbCarNumber = ccbCarNumber == null ? null : ccbCarNumber.trim();
    }

    public String getCcbMoney() {
        return ccbMoney;
    }

    public void setCcbMoney(String ccbMoney) {
        this.ccbMoney = ccbMoney == null ? null : ccbMoney.trim();
    }

    public String getCcbRealMoney() {
        return ccbRealMoney;
    }

    public void setCcbRealMoney(String ccbRealMoney) {
        this.ccbRealMoney = ccbRealMoney == null ? null : ccbRealMoney.trim();
    }

    public String getCcbOften() {
        return ccbOften;
    }

    public void setCcbOften(String ccbOften) {
        this.ccbOften = ccbOften == null ? null : ccbOften.trim();
    }

    public Date getCcbCreatTime() {
        return ccbCreatTime;
    }

    public void setCcbCreatTime(Date ccbCreatTime) {
        this.ccbCreatTime = ccbCreatTime;
    }

    public Date getCcbInTime() {
        return ccbInTime;
    }

    public void setCcbInTime(Date ccbInTime) {
        this.ccbInTime = ccbInTime;
    }

    public Date getCcbOutTime() {
        return ccbOutTime;
    }

    public void setCcbOutTime(Date ccbOutTime) {
        this.ccbOutTime = ccbOutTime;
    }

    public Integer getCcbParkId() {
        return ccbParkId;
    }

    public void setCcbParkId(Integer ccbParkId) {
        this.ccbParkId = ccbParkId;
    }

    public Integer getCcbAgentId() {
        return ccbAgentId;
    }

    public void setCcbAgentId(Integer ccbAgentId) {
        this.ccbAgentId = ccbAgentId;
    }

    public String getCcbOrderId() {
        return ccbOrderId;
    }

    public void setCcbOrderId(String ccbOrderId) {
        this.ccbOrderId = ccbOrderId == null ? null : ccbOrderId.trim();
    }

    public String getCcbInThandle() {
        return ccbInThandle;
    }

    public void setCcbInThandle(String ccbInThandle) {
        this.ccbInThandle = ccbInThandle == null ? null : ccbInThandle.trim();
    }

    public String getCcbOutThandle() {
        return ccbOutThandle;
    }

    public void setCcbOutThandle(String ccbOutThandle) {
        this.ccbOutThandle = ccbOutThandle == null ? null : ccbOutThandle.trim();
    }

    public String getCcbPayType() {
        return ccbPayType;
    }

    public void setCcbPayType(String ccbPayType) {
        this.ccbPayType = ccbPayType == null ? null : ccbPayType.trim();
    }

    public String getCcbLgType() {
        return ccbLgType;
    }

    public void setCcbLgType(String ccbLgType) {
        this.ccbLgType = ccbLgType == null ? null : ccbLgType.trim();
    }

    public String getCcbDataA() {
        return ccbDataA;
    }

    public void setCcbDataA(String ccbDataA) {
        this.ccbDataA = ccbDataA == null ? null : ccbDataA.trim();
    }

    public String getCcbDataB() {
        return ccbDataB;
    }

    public void setCcbDataB(String ccbDataB) {
        this.ccbDataB = ccbDataB == null ? null : ccbDataB.trim();
    }

    public String getCcbDataC() {
        return ccbDataC;
    }

    public void setCcbDataC(String ccbDataC) {
        this.ccbDataC = ccbDataC == null ? null : ccbDataC.trim();
    }

    public String getCcbDataD() {
        return ccbDataD;
    }

    public void setCcbDataD(String ccbDataD) {
        this.ccbDataD = ccbDataD == null ? null : ccbDataD.trim();
    }

    public String getCcbDataE() {
        return ccbDataE;
    }

    public void setCcbDataE(String ccbDataE) {
        this.ccbDataE = ccbDataE == null ? null : ccbDataE.trim();
    }
}