package com.jinshi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Cartt {

    //小区名称
    private String communityName;

    //摄像头品牌
    private String cameraBrand;

    //摄像头型号
    private String cameraType;

    //摄像头序列号
    private String cameraNumber;

    //车牌号码
    private String licensePlate;

    //车牌颜色
    private String colorPlate;

    //抓拍时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date snapTime;

    //过车场景图参数
    private String snapPhotoMul;

    //过车通道
    private String crossChannel;

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getCameraBrand() {
        return cameraBrand;
    }

    public void setCameraBrand(String cameraBrand) {
        this.cameraBrand = cameraBrand;
    }

    public String getCameraType() {
        return cameraType;
    }

    public void setCameraType(String cameraType) {
        this.cameraType = cameraType;
    }

    public String getCameraNumber() {
        return cameraNumber;
    }

    public void setCameraNumber(String cameraNumber) {
        this.cameraNumber = cameraNumber;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getColorPlate() {
        return colorPlate;
    }

    public void setColorPlate(String colorPlate) {
        this.colorPlate = colorPlate;
    }

    public Date getSnapTime() {
        return snapTime;
    }

    public void setSnapTime(Date snapTime) {
        this.snapTime = snapTime;
    }

    public String getSnapPhotoMul() {
        return snapPhotoMul;
    }

    public void setSnapPhotoMul(String snapPhotoMul) {
        this.snapPhotoMul = snapPhotoMul;
    }

    public String getCrossChannel() {
        return crossChannel;
    }

    public void setCrossChannel(String crossChannel) {
        this.crossChannel = crossChannel;
    }

    @Override
    public String toString() {
        return "Cartt{" +
                "communityName='" + communityName + '\'' +
                ", cameraBrand='" + cameraBrand + '\'' +
                ", cameraType='" + cameraType + '\'' +
                ", cameraNumber='" + cameraNumber + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", colorPlate='" + colorPlate + '\'' +
                ", snapTime=" + snapTime +
                ", snapPhotoMul='" + snapPhotoMul + '\'' +
                ", crossChannel='" + crossChannel + '\'' +
                '}';
    }
}
