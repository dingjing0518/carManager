package com.jinshi.entity;

public class JinshiCamerasSpare {
    private Integer jcsId;

    private Integer jcsSpareThandle;

    private String jcsCameraId;

    private Integer jcsParkId;

    private Integer jcsAgentId;

    private String jcsDataA;

    private String jcsDataB;

    private String jcsDataC;

    private String jcsDataD;

    private String jcsDataE;

    public Integer getJcsId() {
        return jcsId;
    }

    public void setJcsId(Integer jcsId) {
        this.jcsId = jcsId;
    }

    public Integer getJcsSpareThandle() {
        return jcsSpareThandle;
    }

    public void setJcsSpareThandle(Integer jcsSpareThandle) {
        this.jcsSpareThandle = jcsSpareThandle;
    }

    public String getJcsCameraId() {
        return jcsCameraId;
    }

    public void setJcsCameraId(String jcsCameraId) {
        this.jcsCameraId = jcsCameraId == null ? null : jcsCameraId.trim();
    }

    public Integer getJcsParkId() {
        return jcsParkId;
    }

    public void setJcsParkId(Integer jcsParkId) {
        this.jcsParkId = jcsParkId;
    }

    public Integer getJcsAgentId() {
        return jcsAgentId;
    }

    public void setJcsAgentId(Integer jcsAgentId) {
        this.jcsAgentId = jcsAgentId;
    }

    public String getJcsDataA() {
        return jcsDataA;
    }

    public void setJcsDataA(String jcsDataA) {
        this.jcsDataA = jcsDataA == null ? null : jcsDataA.trim();
    }

    public String getJcsDataB() {
        return jcsDataB;
    }

    public void setJcsDataB(String jcsDataB) {
        this.jcsDataB = jcsDataB == null ? null : jcsDataB.trim();
    }

    public String getJcsDataC() {
        return jcsDataC;
    }

    public void setJcsDataC(String jcsDataC) {
        this.jcsDataC = jcsDataC == null ? null : jcsDataC.trim();
    }

    public String getJcsDataD() {
        return jcsDataD;
    }

    public void setJcsDataD(String jcsDataD) {
        this.jcsDataD = jcsDataD == null ? null : jcsDataD.trim();
    }

    public String getJcsDataE() {
        return jcsDataE;
    }

    public void setJcsDataE(String jcsDataE) {
        this.jcsDataE = jcsDataE == null ? null : jcsDataE.trim();
    }
}