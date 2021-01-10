package com.jinshi.entity;

import java.util.Date;

public class JinshiCameras {
    private Integer jcId;

    private String jcName;

    private String jcCode;

    private String jcThandle;

    private String jcIpAddress;

    private String jcAccess;

    private String jcLicense;

    private String jcMac;

    private String jcSort;

    private Date jcCreatetime;

    private String jcRemarks;

    private String jcParking;

    private String jcAgent;

    private String jcSubnet;

    private String jcGateway;

    private String jcDns;

    private String cameraLicenseId;
    private Date cameraInboundTime;
    private Date cameraDepartureTime;
    private String cameraLincenseType;
    private Double cameraRent;
    private String cameraName;
    private String cameraState;
    private String cameraIpAddress;
    private String cameraMemberType;

    private String cameraAreaCode;


    private String access;
    private Integer cameraOften;
    private String platePayState;

    private String jcLedscreenType;//led屏型号
    private String jcBaudRate;//波特率
    private String jcDataBits;//数据位
    private String jcStopBits;//停止位
    private String jcValidation;//效验
    private String jcVolume;//音量
    private String jcCamerasId;//摄像机编号

    private String jcArea;

    private Integer voiceCode;  //声音标识

    private String picPath;  //图片地址

    private Date jcStartTime;
    private Date jcEndTime;
    private Integer jcIsCarTeam;
    private Integer jcIsType;

    public Integer getJcIsType() {
        return jcIsType;
    }

    public void setJcIsType(Integer jcIsType) {
        this.jcIsType = jcIsType;
    }

    public Integer getJcIsCarTeam() {
        return jcIsCarTeam;
    }

    public void setJcIsCarTeam(Integer jcIsCarTeam) {
        this.jcIsCarTeam = jcIsCarTeam;
    }

    public Date getJcStartTime() {
        return jcStartTime;
    }

    public void setJcStartTime(Date jcStartTime) {
        this.jcStartTime = jcStartTime;
    }

    public Date getJcEndTime() {
        return jcEndTime;
    }

    public void setJcEndTime(Date jcEndTime) {
        this.jcEndTime = jcEndTime;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public Integer getVoiceCode() {
        return voiceCode;
    }

    public void setVoiceCode(Integer voiceCode) {
        this.voiceCode = voiceCode;
    }

    public String getCameraAreaCode() {
        return cameraAreaCode;
    }

    public void setCameraAreaCode(String cameraAreaCode) {
        this.cameraAreaCode = cameraAreaCode;
    }

    public String getPlatePayState() {
        return platePayState;
    }

    public void setPlatePayState(String platePayState) {
        this.platePayState = platePayState;
    }
    public JinshiCameras() {
    }

    public String getJcArea() {
        return jcArea;
    }

    public void setJcArea(String jcArea) {
        this.jcArea = jcArea;
    }

    public String getJcCamerasId() {
        return jcCamerasId;
    }

    public void setJcCamerasId(String jcCamerasId) {
        this.jcCamerasId = jcCamerasId;
    }

    public Integer getCameraOften() {
        return cameraOften;
    }

    public void setCameraOften(Integer cameraOften) {
        this.cameraOften = cameraOften;
    }

    public String getCameraMemberType() {
        return cameraMemberType;
    }

    public void setCameraMemberType(String cameraMemberType) {
        this.cameraMemberType = cameraMemberType;
    }

    public String getCameraLincenseType() {
        return cameraLincenseType;
    }

    public void setCameraLincenseType(String cameraLincenseType) {
        this.cameraLincenseType = cameraLincenseType;
    }

    public String getCameraLicenseId() {
        return cameraLicenseId;
    }

    public void setCameraLicenseId(String cameraLicenseId) {
        this.cameraLicenseId = cameraLicenseId;
    }

    public Date getCameraInboundTime() {
        return cameraInboundTime;
    }

    public void setCameraInboundTime(Date cameraInboundTime) {
        this.cameraInboundTime = cameraInboundTime;
    }

    public Date getCameraDepartureTime() {
        return cameraDepartureTime;
    }

    public void setCameraDepartureTime(Date cameraDepartureTime) {
        this.cameraDepartureTime = cameraDepartureTime;
    }

    public Double getCameraRent() {
        return cameraRent;
    }

    public void setCameraRent(Double cameraRent) {
        this.cameraRent = cameraRent;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getCameraState() {
        return cameraState;
    }

    public void setCameraState(String cameraState) {
        this.cameraState = cameraState;
    }

    public String getCameraIpAddress() {
        return cameraIpAddress;
    }

    public void setCameraIpAddress(String cameraIpAddress) {
        this.cameraIpAddress = cameraIpAddress;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getThandle() {
        return thandle;
    }

    public void setThandle(String thandle) {
        this.thandle = thandle;
    }

    private String thandle;


    public String getJcSubnet() {
        return jcSubnet;
    }

    public void setJcSubnet(String jcSubnet) {
        this.jcSubnet = jcSubnet;
    }

    public String getJcGateway() {
        return jcGateway;
    }

    public void setJcGateway(String jcGateway) {
        this.jcGateway = jcGateway;
    }

    public String getJcDns() {
        return jcDns;
    }

    public void setJcDns(String jcDns) {
        this.jcDns = jcDns;
    }

    public String getJcParking() {
        return jcParking;
    }

    public void setJcParking(String jcParking) {
        this.jcParking = jcParking;
    }

    public String getJcAgent() {
        return jcAgent;
    }

    public void setJcAgent(String jcAgent) {
        this.jcAgent = jcAgent;
    }

    public Integer getJcId() {
        return jcId;
    }

    public void setJcId(Integer jcId) {
        this.jcId = jcId;
    }

    public String getJcName() {
        return jcName;
    }

    public void setJcName(String jcName) {
        this.jcName = jcName == null ? null : jcName.trim();
    }

    public String getJcCode() {
        return jcCode;
    }

    public void setJcCode(String jcCode) {
        this.jcCode = jcCode == null ? null : jcCode.trim();
    }

    public String getJcThandle() {
        return jcThandle;
    }

    public void setJcThandle(String jcThandle) {
        this.jcThandle = jcThandle == null ? null : jcThandle.trim();
    }

    public String getJcIpAddress() {
        return jcIpAddress;
    }

    public void setJcIpAddress(String jcIpAddress) {
        this.jcIpAddress = jcIpAddress == null ? null : jcIpAddress.trim();
    }

    public String getJcAccess() {
        return jcAccess;
    }

    public void setJcAccess(String jcAccess) {
        this.jcAccess = jcAccess == null ? null : jcAccess.trim();
    }

    public String getJcLicense() {
        return jcLicense;
    }

    public void setJcLicense(String jcLicense) {
        this.jcLicense = jcLicense == null ? null : jcLicense.trim();
    }

    public String getJcMac() {
        return jcMac;
    }

    public void setJcMac(String jcMac) {
        this.jcMac = jcMac == null ? null : jcMac.trim();
    }

    public String getJcSort() {
        return jcSort;
    }

    public void setJcSort(String jcSort) {
        this.jcSort = jcSort == null ? null : jcSort.trim();
    }

    public Date getJcCreatetime() {
        return jcCreatetime;
    }

    public void setJcCreatetime(Date jcCreatetime) {
        this.jcCreatetime = jcCreatetime;
    }

    public String getJcRemarks() {
        return jcRemarks;
    }

    public void setJcRemarks(String jcRemarks) {
        this.jcRemarks = jcRemarks == null ? null : jcRemarks.trim();
    }


    public String getJcLedscreenType() {
        return jcLedscreenType;
    }

    public void setJcLedscreenType(String jcLedscreenType) {
        this.jcLedscreenType = jcLedscreenType;
    }

    public String getJcBaudRate() {
        return jcBaudRate;
    }

    public void setJcBaudRate(String jcBaudRate) {
        this.jcBaudRate = jcBaudRate;
    }

    public String getJcDataBits() {
        return jcDataBits;
    }

    public void setJcDataBits(String jcDataBits) {
        this.jcDataBits = jcDataBits;
    }

    public String getJcStopBits() {
        return jcStopBits;
    }

    public void setJcStopBits(String jcStopBits) {
        this.jcStopBits = jcStopBits;
    }

    public String getJcValidation() {
        return jcValidation;
    }

    public void setJcValidation(String jcValidation) {
        this.jcValidation = jcValidation;
    }

    public String getJcVolume() {
        return jcVolume;
    }

    public void setJcVolume(String jcVolume) {
        this.jcVolume = jcVolume;
    }
}