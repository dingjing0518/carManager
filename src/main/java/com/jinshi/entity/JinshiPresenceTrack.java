package com.jinshi.entity;

import java.util.Date;

public class JinshiPresenceTrack {
    private Integer ptId;

    private Integer ptLpId;

    private Date ptCreatTime;

    private String ptLincensePlateId;

    private String ptThandle;

    private String ptStatus;

    private String ptParkid;

    private String ptDataC;

    private String ptDataD;

    private String ptDataE;

    private String jcName;

    public String getJcName() {
        return jcName;
    }

    public void setJcName(String jcName) {
        this.jcName = jcName;
    }

    public Integer getPtId() {
        return ptId;
    }

    public void setPtId(Integer ptId) {
        this.ptId = ptId;
    }

    public Integer getPtLpId() {
        return ptLpId;
    }

    public void setPtLpId(Integer ptLpId) {
        this.ptLpId = ptLpId;
    }

    public Date getPtCreatTime() {
        return ptCreatTime;
    }

    public void setPtCreatTime(Date ptCreatTime) {
        this.ptCreatTime = ptCreatTime;
    }

    public String getPtLincensePlateId() {
        return ptLincensePlateId;
    }

    public void setPtLincensePlateId(String ptLincensePlateId) {
        this.ptLincensePlateId = ptLincensePlateId == null ? null : ptLincensePlateId.trim();
    }

    public String getPtThandle() {
        return ptThandle;
    }

    public void setPtThandle(String ptThandle) {
        this.ptThandle = ptThandle == null ? null : ptThandle.trim();
    }

    public String getPtStatus() {
        return ptStatus;
    }

    public void setPtStatus(String ptStatus) {
        this.ptStatus = ptStatus;
    }

    public String getPtParkid() {
        return ptParkid;
    }

    public void setPtParkid(String ptParkid) {
        this.ptParkid = ptParkid;
    }

    public String getPtDataC() {
        return ptDataC;
    }

    public void setPtDataC(String ptDataC) {
        this.ptDataC = ptDataC == null ? null : ptDataC.trim();
    }

    public String getPtDataD() {
        return ptDataD;
    }

    public void setPtDataD(String ptDataD) {
        this.ptDataD = ptDataD == null ? null : ptDataD.trim();
    }

    public String getPtDataE() {
        return ptDataE;
    }

    public void setPtDataE(String ptDataE) {
        this.ptDataE = ptDataE == null ? null : ptDataE.trim();
    }
}