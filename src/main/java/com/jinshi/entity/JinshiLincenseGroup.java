package com.jinshi.entity;

public class JinshiLincenseGroup {
    private Integer jsLgId;

    private String jsGroupName;

    private String jsNumber;

    private String jsCarCount;

    private String jsPhone;

    private String jsAddress;

    private Integer jsParkId;

    private Integer jsAgentId;

    private Integer jsAreaId;

    private String jsDataD;

    private String jsDataE;

    public Integer getJsLgId() {
        return jsLgId;
    }

    public void setJsLgId(Integer jsLgId) {
        this.jsLgId = jsLgId;
    }

    public String getJsGroupName() {
        return jsGroupName;
    }

    public void setJsGroupName(String jsGroupName) {
        this.jsGroupName = jsGroupName == null ? null : jsGroupName.trim();
    }

    public String getJsNumber() {
        return jsNumber;
    }

    public void setJsNumber(String jsNumber) {
        this.jsNumber = jsNumber == null ? null : jsNumber.trim();
    }

    public String getJsCarCount() {
        return jsCarCount;
    }

    public void setJsCarCount(String jsCarCount) {
        this.jsCarCount = jsCarCount == null ? null : jsCarCount.trim();
    }

    public String getJsPhone() {
        return jsPhone;
    }

    public void setJsPhone(String jsPhone) {
        this.jsPhone = jsPhone == null ? null : jsPhone.trim();
    }

    public String getJsAddress() {
        return jsAddress;
    }

    public void setJsAddress(String jsAddress) {
        this.jsAddress = jsAddress == null ? null : jsAddress.trim();
    }

    public Integer getJsParkId() {
        return jsParkId;
    }

    public void setJsParkId(Integer jsParkId) {
        this.jsParkId = jsParkId;
    }

    public Integer getJsAgentId() {
        return jsAgentId;
    }

    public void setJsAgentId(Integer jsAgentId) {
        this.jsAgentId = jsAgentId;
    }

    public Integer getJsAreaId() {
        return jsAreaId;
    }

    public void setJsAreaId(Integer jsAreaId) {
        this.jsAreaId = jsAreaId;
    }

    public String getJsDataD() {
        return jsDataD;
    }

    public void setJsDataD(String jsDataD) {
        this.jsDataD = jsDataD == null ? null : jsDataD.trim();
    }

    public String getJsDataE() {
        return jsDataE;
    }

    public void setJsDataE(String jsDataE) {
        this.jsDataE = jsDataE == null ? null : jsDataE.trim();
    }
}