package com.jinshi.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "park")
public class ParkConfig {
    private Integer parkId;
    private String parkNumber;
    private String picPathIs;
    private String picPathHttp;
    private Integer csCode;
    private Integer yellowLincenseAllow;
    private String urlNew;
    private String urlNewShop;
    private Integer openGateNewRealMoney;
    private Integer delayTime;
    private Integer interval;
    private Integer jcIsCarTeam;
    private Integer typeTime;
    private Integer payType;
    private String netPathKS;
    private String netPathWJ;

    private boolean freeMode;
    private boolean openGateAllMode;
    private boolean closeGateAllMode;
    private boolean noEnterMode;
    private boolean noOutMode;
    private boolean invalidRelease;

    public String getNetPathKS() {
        return netPathKS;
    }

    public void setNetPathKS(String netPathKS) {
        this.netPathKS = netPathKS;
    }

    public String getNetPathWJ() {
        return netPathWJ;
    }

    public void setNetPathWJ(String netPathWJ) {
        this.netPathWJ = netPathWJ;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getTypeTime() {
        return typeTime;
    }

    public void setTypeTime(Integer typeTime) {
        this.typeTime = typeTime;
    }

    public Integer getJcIsCarTeam() {
        return jcIsCarTeam;
    }

    public void setJcIsCarTeam(Integer jcIsCarTeam) {
        this.jcIsCarTeam = jcIsCarTeam;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Integer delayTime) {
        this.delayTime = delayTime;
    }

    public Integer getOpenGateNewRealMoney() {
        return openGateNewRealMoney;
    }

    public void setOpenGateNewRealMoney(Integer openGateNewRealMoney) {
        this.openGateNewRealMoney = openGateNewRealMoney;
    }

    public String getUrlNew() {
        return urlNew;
    }

    public void setUrlNew(String urlNew) {
        this.urlNew = urlNew;
    }

    public String getUrlNewShop() {
        return urlNewShop;
    }

    public void setUrlNewShop(String urlNewShop) {
        this.urlNewShop = urlNewShop;
    }

    public Integer getYellowLincenseAllow() {
        return yellowLincenseAllow;
    }

    public void setYellowLincenseAllow(Integer yellowLincenseAllow) {
        this.yellowLincenseAllow = yellowLincenseAllow;
    }

    public Integer getCsCode() {
        return csCode;
    }

    public void setCsCode(Integer csCode) {
        this.csCode = csCode;
    }

    public String getPicPathIs() {
        return picPathIs;
    }

    public void setPicPathIs(String picPathIs) {
        this.picPathIs = picPathIs;
    }

    public String getPicPathHttp() {
        return picPathHttp;
    }

    public void setPicPathHttp(String picPathHttp) {
        this.picPathHttp = picPathHttp;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public String getParkNumber() {
        return parkNumber;
    }

    public void setParkNumber(String parkNumber) {
        this.parkNumber = parkNumber;
    }

    public boolean isFreeMode() {
        return freeMode;
    }

    public void setFreeMode(boolean freeMode) {
        this.freeMode = freeMode;
    }

    public boolean isOpenGateAllMode() {
        return openGateAllMode;
    }

    public void setOpenGateAllMode(boolean openGateAllMode) {
        this.openGateAllMode = openGateAllMode;
    }

    public boolean isCloseGateAllMode() {
        return closeGateAllMode;
    }

    public void setCloseGateAllMode(boolean closeGateAllMode) {
        this.closeGateAllMode = closeGateAllMode;
    }

    public boolean isNoEnterMode() {
        return noEnterMode;
    }

    public void setNoEnterMode(boolean noEnterMode) {
        this.noEnterMode = noEnterMode;
    }

    public boolean isNoOutMode() {
        return noOutMode;
    }

    public void setNoOutMode(boolean noOutMode) {
        this.noOutMode = noOutMode;
    }

    public boolean isInvalidRelease() {
        return invalidRelease;
    }

    public void setInvalidRelease(boolean invalidRelease) {
        this.invalidRelease = invalidRelease;
    }
}
