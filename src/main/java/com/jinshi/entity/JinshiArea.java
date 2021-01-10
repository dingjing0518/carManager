package com.jinshi.entity;

public class JinshiArea {

    private Integer id;

    private String areaName;

    private Integer agentId;

    private Integer parkId;

    private String areaNumber;

    private Integer parkingCount;

    private Integer temporaryParkingCount;

    private Integer payMonth;

    private Integer payQuarter;

    private Integer payYear;

    private Integer allowNegativeCar;

    public Integer getAllowNegativeCar() {
        return allowNegativeCar;
    }

    public void setAllowNegativeCar(Integer allowNegativeCar) {
        this.allowNegativeCar = allowNegativeCar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

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

    public Integer getParkingCount() {
        return parkingCount;
    }

    public void setParkingCount(Integer parkingCount) {
        this.parkingCount = parkingCount;
    }

    public Integer getTemporaryParkingCount() {
        return temporaryParkingCount;
    }

    public void setTemporaryParkingCount(Integer temporaryParkingCount) {
        this.temporaryParkingCount = temporaryParkingCount;
    }

    public String getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(String areaNumber) {
        this.areaNumber = areaNumber;
    }

    public Integer getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(Integer payMonth) {
        this.payMonth = payMonth;
    }

    public Integer getPayQuarter() {
        return payQuarter;
    }

    public void setPayQuarter(Integer payQuarter) {
        this.payQuarter = payQuarter;
    }

    public Integer getPayYear() {
        return payYear;
    }

    public void setPayYear(Integer payYear) {
        this.payYear = payYear;
    }
}