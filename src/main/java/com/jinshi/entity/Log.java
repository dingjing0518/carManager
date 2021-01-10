package com.jinshi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Log {

    //车场名称
    private String communityname;

    //进出时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addtime;

    //道闸的名称
    private String doorname;

    //车牌号码
    private String licenseplate;

    public String getCommunityname() {
        return communityname;
    }

    public void setCommunityname(String communityname) {
        this.communityname = communityname;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getDoorname() {
        return doorname;
    }

    public void setDoorname(String doorname) {
        this.doorname = doorname;
    }

    public String getLicenseplate() {
        return licenseplate;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    @Override
    public String toString() {
        return "Log{" +
                "communityname='" + communityname + '\'' +
                ", addtime=" + addtime +
                ", doorname='" + doorname + '\'' +
                ", licenseplate='" + licenseplate + '\'' +
                '}';
    }
}
