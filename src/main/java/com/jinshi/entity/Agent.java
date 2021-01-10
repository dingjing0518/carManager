package com.jinshi.entity;

public class Agent {
    private Integer id;

    private String username;

    private String phoneNumber;

    private String site;

    private String rentalTerm;

    private String remainingTime;

    private Integer state;

    private String parkingNumbers;

    private String province;

    private String city;

    private String district;

    private String loginName;

    private String fixedPhone;

    private String weChat;

    private String qq;

    private String dividedRatio;

    private String loginPassword;

    private String remark;

    private String roleName;

    private String agentNumber;

    public String getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getFixedPhone() {
        return fixedPhone;
    }

    public void setFixedPhone(String fixedPhone) {
        this.fixedPhone = fixedPhone;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getDividedRatio() {
        return dividedRatio;
    }

    public void setDividedRatio(String dividedRatio) {
        this.dividedRatio = dividedRatio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    public String getRentalTerm() {
        return rentalTerm;
    }

    public void setRentalTerm(String rentalTerm) {
        this.rentalTerm = rentalTerm == null ? null : rentalTerm.trim();
    }

    public String getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime == null ? null : remainingTime.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getParkingNumbers() {
        return parkingNumbers;
    }

    public void setParkingNumbers(String parkingNumbers) {
        this.parkingNumbers = parkingNumbers == null ? null : parkingNumbers.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}