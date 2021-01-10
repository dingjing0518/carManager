package com.jinshi.entity;

import java.util.Date;

public class LincensePlatess {
    private String lpLincensePlateIdCar;

    private String lpCarType;

    private String lpParkingCost;

    @Override
    public String toString() {
        return "LincensePlatess{" +
                "lpLincensePlateIdCar='" + lpLincensePlateIdCar + '\'' +
                ", lpCarType='" + lpCarType + '\'' +
                ", lpParkingCost='" + lpParkingCost + '\'' +
                '}';
    }

    public String getLpLincensePlateIdCar() {
        return lpLincensePlateIdCar;
    }

    public void setLpLincensePlateIdCar(String lpLincensePlateIdCar) {
        this.lpLincensePlateIdCar = lpLincensePlateIdCar;
    }

    public String getLpCarType() {
        return lpCarType;
    }

    public void setLpCarType(String lpCarType) {
        this.lpCarType = lpCarType;
    }

    public String getLpParkingCost() {
        return lpParkingCost;
    }

    public void setLpParkingCost(String lpParkingCost) {
        this.lpParkingCost = lpParkingCost;
    }
}