package com.jinshi.entity;

public class JinshiRole {
    private Integer id;

    private String jsRolename;

    private String jsRolearray;
    private String jsRolearrayForWeb;

    private Integer jsUserid;

    private Integer parkid;

    private Integer agentid;

    public String getJsRolearrayForWeb() {
        return jsRolearrayForWeb;
    }

    public void setJsRolearrayForWeb(String jsRolearrayForWeb) {
        this.jsRolearrayForWeb = jsRolearrayForWeb;
    }

    public Integer getParkid() {
        return parkid;
    }

    public void setParkid(Integer parkid) {
        this.parkid = parkid;
    }

    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJsRolename() {
        return jsRolename;
    }

    public void setJsRolename(String jsRolename) {
        this.jsRolename = jsRolename == null ? null : jsRolename.trim();
    }

    public String getJsRolearray() {
        return jsRolearray;
    }

    public void setJsRolearray(String jsRolearray) {
        this.jsRolearray = jsRolearray;
    }

    public Integer getJsUserid() {
        return jsUserid;
    }

    public void setJsUserid(Integer jsUserid) {
        this.jsUserid = jsUserid;
    }
}