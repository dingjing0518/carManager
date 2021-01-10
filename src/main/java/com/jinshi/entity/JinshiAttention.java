package com.jinshi.entity;

public class JinshiAttention {
    private Integer id;

    private String jcLincensePlateId;

    private String jcPhone;

    private String jcWechat;

    private String jcAgent;

    private String jcParkid;

    private String jcTitle;
    private String jcRemark;

    public String getJcRemark() {
        return jcRemark;
    }

    public void setJcRemark(String jcRemark) {
        this.jcRemark = jcRemark;
    }

    public String getJcTitle() {
        return jcTitle;
    }

    public void setJcTitle(String jcTitle) {
        this.jcTitle = jcTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJcLincensePlateId() {
        return jcLincensePlateId;
    }

    public void setJcLincensePlateId(String jcLincensePlateId) {
        this.jcLincensePlateId = jcLincensePlateId == null ? null : jcLincensePlateId.trim();
    }

    public String getJcPhone() {
        return jcPhone;
    }

    public void setJcPhone(String jcPhone) {
        this.jcPhone = jcPhone == null ? null : jcPhone.trim();
    }

    public String getJcWechat() {
        return jcWechat;
    }

    public void setJcWechat(String jcWechat) {
        this.jcWechat = jcWechat == null ? null : jcWechat.trim();
    }

    public String getJcAgent() {
        return jcAgent;
    }

    public void setJcAgent(String jcAgent) {
        this.jcAgent = jcAgent == null ? null : jcAgent.trim();
    }

    public String getJcParkid() {
        return jcParkid;
    }

    public void setJcParkid(String jcParkid) {
        this.jcParkid = jcParkid == null ? null : jcParkid.trim();
    }
}