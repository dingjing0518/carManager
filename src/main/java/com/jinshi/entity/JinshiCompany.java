package com.jinshi.entity;

public class JinshiCompany {
    private Integer id;

    private String jsName;

    private String jsSite;

    private String jsType;

    private String jsDepartment;

    private String jsUsername;

    private String jsPhone;

    private Integer parkId;

    private Integer agentId;

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJsName() {
        return jsName;
    }

    public void setJsName(String jsName) {
        this.jsName = jsName == null ? null : jsName.trim();
    }

    public String getJsSite() {
        return jsSite;
    }

    public void setJsSite(String jsSite) {
        this.jsSite = jsSite == null ? null : jsSite.trim();
    }

    public String getJsType() {
        return jsType;
    }

    public void setJsType(String jsType) {
        this.jsType = jsType == null ? null : jsType.trim();
    }

    public String getJsDepartment() {
        return jsDepartment;
    }

    public void setJsDepartment(String jsDepartment) {
        this.jsDepartment = jsDepartment == null ? null : jsDepartment.trim();
    }

    public String getJsUsername() {
        return jsUsername;
    }

    public void setJsUsername(String jsUsername) {
        this.jsUsername = jsUsername == null ? null : jsUsername.trim();
    }

    public String getJsPhone() {
        return jsPhone;
    }

    public void setJsPhone(String jsPhone) {
        this.jsPhone = jsPhone == null ? null : jsPhone.trim();
    }
}