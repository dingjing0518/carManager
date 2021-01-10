package com.jinshi.entity;

public class JinshiUserRole {
    private Integer id;
    private String userName;
    private String passWord;
    private String realName;
    private String roleName;

    private String jsRolename;

    private String jsRolearray;

    private Integer jsUserid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getJsRolename() {
        return jsRolename;
    }

    public void setJsRolename(String jsRolename) {
        this.jsRolename = jsRolename;
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