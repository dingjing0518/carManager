package com.jinshi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class LincensePlateHistory {
    private Integer lpId;

    private String lpMemberIdCar;

    private String lpLincensePlateIdCar;

    private String lpServiceTypeCar;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lpInboundTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lpDepartureTime;

    private String lpRentTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lpCreateTime;

    private String lpOrderId;

    private String lpCarType;

    private String lpCarColor;

    private String lpLincenseType;

    private String lpParkingOften;

    private String lpParkingCost;

    private String lpParkingRealCost;

    private String lpInboundCname;

    private String lpDepartureCname;

    private String lpParkingName;

    private String lpAgentName;

    private String lpOrderState;

    private String lpPaymentType;

    private Integer lpLgId;

    private Integer lpLgType;

    private String lpLgName;

    private String lpLgTypeName;

    public String getLpLgName() {
        return lpLgName;
    }

    public void setLpLgName(String lpLgName) {
        this.lpLgName = lpLgName;
    }

    public String getLpLgTypeName() {
        return lpLgTypeName;
    }

    public void setLpLgTypeName(String lpLgTypeName) {
        this.lpLgTypeName = lpLgTypeName;
    }

    public Integer getLpLgId() {
        return lpLgId;
    }

    public void setLpLgId(Integer lpLgId) {
        this.lpLgId = lpLgId;
    }

    public Integer getLpLgType() {
        return lpLgType;
    }

    public void setLpLgType(Integer lpLgType) {
        this.lpLgType = lpLgType;
    }

    public Integer getLpId() {
        return lpId;
    }

    public void setLpId(Integer lpId) {
        this.lpId = lpId;
    }

    public String getLpMemberIdCar() {
        return lpMemberIdCar;
    }

    public void setLpMemberIdCar(String lpMemberIdCar) {
        this.lpMemberIdCar = lpMemberIdCar == null ? null : lpMemberIdCar.trim();
    }

    public String getLpLincensePlateIdCar() {
        return lpLincensePlateIdCar;
    }

    public void setLpLincensePlateIdCar(String lpLincensePlateIdCar) {
        this.lpLincensePlateIdCar = lpLincensePlateIdCar == null ? null : lpLincensePlateIdCar.trim();
    }

    public String getLpServiceTypeCar() {
        return lpServiceTypeCar;
    }

    public void setLpServiceTypeCar(String lpServiceTypeCar) {
        this.lpServiceTypeCar = lpServiceTypeCar;
    }

    public Date getLpInboundTime() {
        return lpInboundTime;
    }

    public void setLpInboundTime(Date lpInboundTime) {
        this.lpInboundTime = lpInboundTime;
    }

    public Date getLpDepartureTime() {
        return lpDepartureTime;
    }

    public void setLpDepartureTime(Date lpDepartureTime) {
        this.lpDepartureTime = lpDepartureTime;
    }

    public String getLpRentTime() {
        return lpRentTime;
    }

    public void setLpRentTime(String lpRentTime) {
        this.lpRentTime = lpRentTime == null ? null : lpRentTime.trim();
    }

    public Date getLpCreateTime() {
        return lpCreateTime;
    }

    public void setLpCreateTime(Date lpCreateTime) {
        this.lpCreateTime = lpCreateTime;
    }

    public String getLpOrderId() {
        return lpOrderId;
    }

    public void setLpOrderId(String lpOrderId) {
        this.lpOrderId = lpOrderId == null ? null : lpOrderId.trim();
    }

    public String getLpCarType() {
        return lpCarType;
    }

    public void setLpCarType(String lpCarType) {
        this.lpCarType = lpCarType == null ? null : lpCarType.trim();
    }

    public String getLpCarColor() {
        return lpCarColor;
    }

    public void setLpCarColor(String lpCarColor) {
        this.lpCarColor = lpCarColor == null ? null : lpCarColor.trim();
    }

    public String getLpLincenseType() {
        return lpLincenseType;
    }

    public void setLpLincenseType(String lpLincenseType) {
        this.lpLincenseType = lpLincenseType == null ? null : lpLincenseType.trim();
    }

    public String getLpParkingOften() {
        return lpParkingOften;
    }

    public void setLpParkingOften(String lpParkingOften) {
        this.lpParkingOften = lpParkingOften == null ? null : lpParkingOften.trim();
    }

    public String getLpParkingCost() {
        return lpParkingCost;
    }

    public void setLpParkingCost(String lpParkingCost) {
        this.lpParkingCost = lpParkingCost == null ? null : lpParkingCost.trim();
    }

    public String getLpInboundCname() {
        return lpInboundCname;
    }

    public void setLpInboundCname(String lpInboundCname) {
        this.lpInboundCname = lpInboundCname == null ? null : lpInboundCname.trim();
    }

    public String getLpDepartureCname() {
        return lpDepartureCname;
    }

    public void setLpDepartureCname(String lpDepartureCname) {
        this.lpDepartureCname = lpDepartureCname == null ? null : lpDepartureCname.trim();
    }

    public String getLpParkingName() {
        return lpParkingName;
    }

    public void setLpParkingName(String lpParkingName) {
        this.lpParkingName = lpParkingName == null ? null : lpParkingName.trim();
    }

    public String getLpAgentName() {
        return lpAgentName;
    }

    public void setLpAgentName(String lpAgentName) {
        this.lpAgentName = lpAgentName == null ? null : lpAgentName.trim();
    }

    public String getLpOrderState() {
        return lpOrderState;
    }

    public void setLpOrderState(String lpOrderState) {
        this.lpOrderState = lpOrderState == null ? null : lpOrderState.trim();
    }

    public String getLpPaymentType() {
        return lpPaymentType;
    }

    public void setLpPaymentType(String lpPaymentType) {
        this.lpPaymentType = lpPaymentType == null ? null : lpPaymentType.trim();
    }

    public String getLpParkingRealCost() {
        return lpParkingRealCost;
    }

    public void setLpParkingRealCost(String lpParkingRealCost) {
        this.lpParkingRealCost = lpParkingRealCost;
    }
}