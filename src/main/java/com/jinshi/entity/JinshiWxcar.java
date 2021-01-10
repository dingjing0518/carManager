package com.jinshi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class JinshiWxcar {
    private Long id;

    private String jwOpenid;

    private String jwName;

    private String jwCarType;

    private String jwCarNumber;

    private String jwPhone;

    private String jwServiceName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jwCreateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jwExpirationTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJwOpenid() {
        return jwOpenid;
    }

    public void setJwOpenid(String jwOpenid) {
        this.jwOpenid = jwOpenid == null ? null : jwOpenid.trim();
    }

    public String getJwName() {
        return jwName;
    }

    public void setJwName(String jwName) {
        this.jwName = jwName == null ? null : jwName.trim();
    }

    public String getJwCarType() {
        return jwCarType;
    }

    public void setJwCarType(String jwCarType) {
        this.jwCarType = jwCarType == null ? null : jwCarType.trim();
    }

    public String getJwCarNumber() {
        return jwCarNumber;
    }

    public void setJwCarNumber(String jwCarNumber) {
        this.jwCarNumber = jwCarNumber == null ? null : jwCarNumber.trim();
    }

    public String getJwPhone() {
        return jwPhone;
    }

    public void setJwPhone(String jwPhone) {
        this.jwPhone = jwPhone == null ? null : jwPhone.trim();
    }

    public String getJwServiceName() {
        return jwServiceName;
    }

    public void setJwServiceName(String jwServiceName) {
        this.jwServiceName = jwServiceName == null ? null : jwServiceName.trim();
    }

    public Date getJwCreateTime() {
        return jwCreateTime;
    }

    public void setJwCreateTime(Date jwCreateTime) {
        this.jwCreateTime = jwCreateTime;
    }

    public Date getJwExpirationTime() {
        return jwExpirationTime;
    }

    public void setJwExpirationTime(Date jwExpirationTime) {
        this.jwExpirationTime = jwExpirationTime;
    }
}