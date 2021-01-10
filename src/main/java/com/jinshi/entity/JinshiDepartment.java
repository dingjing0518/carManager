package com.jinshi.entity;

public class JinshiDepartment {
    private Integer id;

    private String jdName;

    private String jdSum;

    private String jdDuty;

    private String jdUsername;

    private String jdPhone;

    private Integer jdCompanyid;

    private String jsCompanyName;

    private Integer parkId;

    private Integer agentId;

    public String getJsCompanyName() {
        return jsCompanyName;
    }

    public void setJsCompanyName(String jsCompanyName) {
        this.jsCompanyName = jsCompanyName;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJdName() {
        return jdName;
    }

    public void setJdName(String jdName) {
        this.jdName = jdName == null ? null : jdName.trim();
    }

    public String getJdSum() {
        return jdSum;
    }

    public void setJdSum(String jdSum) {
        this.jdSum = jdSum == null ? null : jdSum.trim();
    }

    public String getJdDuty() {
        return jdDuty;
    }

    public void setJdDuty(String jdDuty) {
        this.jdDuty = jdDuty == null ? null : jdDuty.trim();
    }

    public String getJdUsername() {
        return jdUsername;
    }

    public void setJdUsername(String jdUsername) {
        this.jdUsername = jdUsername == null ? null : jdUsername.trim();
    }

    public String getJdPhone() {
        return jdPhone;
    }

    public void setJdPhone(String jdPhone) {
        this.jdPhone = jdPhone == null ? null : jdPhone.trim();
    }

    public Integer getJdCompanyid() {
        return jdCompanyid;
    }

    public void setJdCompanyid(Integer jdCompanyid) {
        this.jdCompanyid = jdCompanyid;
    }
}