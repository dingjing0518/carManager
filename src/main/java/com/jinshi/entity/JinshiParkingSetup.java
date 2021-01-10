package com.jinshi.entity;

import java.util.Date;

public class JinshiParkingSetup {
    private Integer id;

    private Integer jpsParkId;

    private Integer jpsAgentId;

    private Integer jpsOpenLocalInterface;

    private Integer jpsFreeMode;

    private Integer jpsOpenMode;

    private Integer jpsMemberCar;

    private Integer jpsInvalidRelease;

    private Integer jpsYellowLincenseAllow;

    private Integer jpsAllowNegativeCar;

    private String jpsUrlNewPc;

    private String jpsUrlNewShopPc;

    private Integer jpsHolidayFree;

    private Integer jpsHoliday;

    private Date jpsStartTime;

    private Date jpsEndTime;

    private Integer jpsTHandle;

    private Integer jpsInterval;
    private Integer jpsExportConfirm;
    private Integer jpsImportConfirm;
    private Integer jpsPayType;

    public Integer getJpsPayType() {
        return jpsPayType;
    }

    public void setJpsPayType(Integer jpsPayType) {
        this.jpsPayType = jpsPayType;
    }

    public Integer getJpsExportConfirm() {
        return jpsExportConfirm;
    }

    public void setJpsExportConfirm(Integer jpsExportConfirm) {
        this.jpsExportConfirm = jpsExportConfirm;
    }

    public Integer getJpsImportConfirm() {
        return jpsImportConfirm;
    }

    public void setJpsImportConfirm(Integer jpsImportConfirm) {
        this.jpsImportConfirm = jpsImportConfirm;
    }

    public Integer getJpsInterval() {
        return jpsInterval;
    }

    public void setJpsInterval(Integer jpsInterval) {
        this.jpsInterval = jpsInterval;
    }

    public Date getJpsStartTime() {
        return jpsStartTime;
    }

    public void setJpsStartTime(Date jpsStartTime) {
        this.jpsStartTime = jpsStartTime;
    }

    public Date getJpsEndTime() {
        return jpsEndTime;
    }

    public void setJpsEndTime(Date jpsEndTime) {
        this.jpsEndTime = jpsEndTime;
    }

    public Integer getJpsTHandle() {
        return jpsTHandle;
    }

    public void setJpsTHandle(Integer jpsTHandle) {
        this.jpsTHandle = jpsTHandle;
    }

    public Integer getJpsHolidayFree() {
        return jpsHolidayFree;
    }

    public void setJpsHolidayFree(Integer jpsHolidayFree) {
        this.jpsHolidayFree = jpsHolidayFree;
    }

    public Integer getJpsHoliday() {
        return jpsHoliday;
    }

    public void setJpsHoliday(Integer jpsHoliday) {
        this.jpsHoliday = jpsHoliday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJpsParkId() {
        return jpsParkId;
    }

    public void setJpsParkId(Integer jpsParkId) {
        this.jpsParkId = jpsParkId;
    }

    public Integer getJpsAgentId() {
        return jpsAgentId;
    }

    public void setJpsAgentId(Integer jpsAgentId) {
        this.jpsAgentId = jpsAgentId;
    }

    public Integer getJpsOpenLocalInterface() {
        return jpsOpenLocalInterface;
    }

    public void setJpsOpenLocalInterface(Integer jpsOpenLocalInterface) {
        this.jpsOpenLocalInterface = jpsOpenLocalInterface;
    }

    public Integer getJpsFreeMode() {
        return jpsFreeMode;
    }

    public void setJpsFreeMode(Integer jpsFreeMode) {
        this.jpsFreeMode = jpsFreeMode;
    }

    public Integer getJpsOpenMode() {
        return jpsOpenMode;
    }

    public void setJpsOpenMode(Integer jpsOpenMode) {
        this.jpsOpenMode = jpsOpenMode;
    }

    public Integer getJpsMemberCar() {
        return jpsMemberCar;
    }

    public void setJpsMemberCar(Integer jpsMemberCar) {
        this.jpsMemberCar = jpsMemberCar;
    }

    public Integer getJpsInvalidRelease() {
        return jpsInvalidRelease;
    }

    public void setJpsInvalidRelease(Integer jpsInvalidRelease) {
        this.jpsInvalidRelease = jpsInvalidRelease;
    }

    public Integer getJpsYellowLincenseAllow() {
        return jpsYellowLincenseAllow;
    }

    public void setJpsYellowLincenseAllow(Integer jpsYellowLincenseAllow) {
        this.jpsYellowLincenseAllow = jpsYellowLincenseAllow;
    }

    public Integer getJpsAllowNegativeCar() {
        return jpsAllowNegativeCar;
    }

    public void setJpsAllowNegativeCar(Integer jpsAllowNegativeCar) {
        this.jpsAllowNegativeCar = jpsAllowNegativeCar;
    }

    public String getJpsUrlNewPc() {
        return jpsUrlNewPc;
    }

    public void setJpsUrlNewPc(String jpsUrlNewPc) {
        this.jpsUrlNewPc = jpsUrlNewPc == null ? null : jpsUrlNewPc.trim();
    }

    public String getJpsUrlNewShopPc() {
        return jpsUrlNewShopPc;
    }

    public void setJpsUrlNewShopPc(String jpsUrlNewShopPc) {
        this.jpsUrlNewShopPc = jpsUrlNewShopPc == null ? null : jpsUrlNewShopPc.trim();
    }
}