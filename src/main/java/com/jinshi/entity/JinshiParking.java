package com.jinshi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.Date;

public class JinshiParking {
    private Integer jpId;

    private String jpName;

    private String jpSite;

    private String jpMembers;

    private String jpTotalTurnover;

    private String jpNumber;

    private String jpPhoneNumber;

    private String jpPlaceNumber;

    private String jpCameraBrand;

    private String jpPicturePath;

    private Integer jpAgentId;

    private String jpAgentName;

    private String jpRemark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jpCreatetime;

    private Integer jpIsdelete;

    private String jpWxNumber;

    private String jpUsername;

    private String jpLoginname;

    private String jpPhoneNumberBackup;

    private String jpPhoneNumberBackupTwo;
    private String jpProvince;
    private String jpCity;
    private String jpDistrict;
    private String jpRoleName;

    private String jpParkNumber;

    private Integer parkId;

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public String getJpParkNumber() {
        return jpParkNumber;
    }

    public void setJpParkNumber(String jpParkNumber) {
        this.jpParkNumber = jpParkNumber;
    }

    public String getJpRoleName() {
        return jpRoleName;
    }

    public void setJpRoleName(String jpRoleName) {
        this.jpRoleName = jpRoleName;
    }

    public String getJpProvince() {
        return jpProvince;
    }

    public void setJpProvince(String jpProvince) {
        this.jpProvince = jpProvince;
    }

    public String getJpCity() {
        return jpCity;
    }

    public void setJpCity(String jpCity) {
        this.jpCity = jpCity;
    }

    public String getJpDistrict() {
        return jpDistrict;
    }

    public void setJpDistrict(String jpDistrict) {
        this.jpDistrict = jpDistrict;
    }

    public String getJpPhoneNumberBackupTwo() {
        return jpPhoneNumberBackupTwo;
    }

    public void setJpPhoneNumberBackupTwo(String jpPhoneNumberBackupTwo) {
        this.jpPhoneNumberBackupTwo = jpPhoneNumberBackupTwo;
    }

    public String getJpPhoneNumberBackup() {
        return jpPhoneNumberBackup;
    }

    public void setJpPhoneNumberBackup(String jpPhoneNumberBackup) {
        this.jpPhoneNumberBackup = jpPhoneNumberBackup;
    }

    public String getJpUsername() {
        return jpUsername;
    }

    public void setJpUsername(String jpUsername) {
        this.jpUsername = jpUsername;
    }

    public String getJpLoginname() {
        return jpLoginname;
    }

    public void setJpLoginname(String jpLoginname) {
        this.jpLoginname = jpLoginname;
    }

    public Integer getJpId() {
        return jpId;
    }

    public void setJpId(Integer jpId) {
        this.jpId = jpId;
    }

    public String getJpName() {
        return jpName;
    }

    public void setJpName(String jpName) {
        this.jpName = jpName == null ? null : jpName.trim();
    }

    public String getJpSite() {
        return jpSite;
    }

    public void setJpSite(String jpSite) {
        this.jpSite = jpSite == null ? null : jpSite.trim();
    }

    public String getJpMembers() {
        return jpMembers;
    }

    public void setJpMembers(String jpMembers) {
        this.jpMembers = jpMembers == null ? null : jpMembers.trim();
    }

    public String getJpTotalTurnover() {
        return jpTotalTurnover;
    }

    public void setJpTotalTurnover(String jpTotalTurnover) {
        this.jpTotalTurnover = jpTotalTurnover == null ? null : jpTotalTurnover.trim();
    }

    public String getJpNumber() {
        return jpNumber;
    }

    public void setJpNumber(String jpNumber) {
        this.jpNumber = jpNumber == null ? null : jpNumber.trim();
    }

    public String getJpPhoneNumber() {
        return jpPhoneNumber;
    }

    public void setJpPhoneNumber(String jpPhoneNumber) {
        this.jpPhoneNumber = jpPhoneNumber == null ? null : jpPhoneNumber.trim();
    }

    public String getJpPlaceNumber() {
        return jpPlaceNumber;
    }

    public void setJpPlaceNumber(String jpPlaceNumber) {
        this.jpPlaceNumber = jpPlaceNumber == null ? null : jpPlaceNumber.trim();
    }

    public String getJpCameraBrand() {
        return jpCameraBrand;
    }

    public void setJpCameraBrand(String jpCameraBrand) {
        this.jpCameraBrand = jpCameraBrand == null ? null : jpCameraBrand.trim();
    }

    public String getJpPicturePath() {
        return jpPicturePath;
    }

    public void setJpPicturePath(String jpPicturePath) {
        this.jpPicturePath = jpPicturePath == null ? null : jpPicturePath.trim();
    }

    public Integer getJpAgentId() {
        return jpAgentId;
    }

    public void setJpAgentId(Integer jpAgentId) {
        this.jpAgentId = jpAgentId;
    }

    public String getJpAgentName() {
        return jpAgentName;
    }

    public void setJpAgentName(String jpAgentName) {
        this.jpAgentName = jpAgentName;
    }

    public String getJpRemark() {
        return jpRemark;
    }

    public void setJpRemark(String jpRemark) {
        this.jpRemark = jpRemark == null ? null : jpRemark.trim();
    }

    public Date getJpCreatetime() {
        return jpCreatetime;
    }

    public void setJpCreatetime(Date jpCreatetime) {
        this.jpCreatetime = jpCreatetime;
    }

    public Integer getJpIsdelete() {
        return jpIsdelete;
    }

    public void setJpIsdelete(Integer jpIsdelete) {
        this.jpIsdelete = jpIsdelete;
    }

    public String getJpWxNumber() {
        return jpWxNumber;
    }

    public void setJpWxNumber(String jpWxNumber) {
        this.jpWxNumber = jpWxNumber;
    }
}