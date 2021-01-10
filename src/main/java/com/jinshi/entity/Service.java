package com.jinshi.entity;

public class Service {
    private Integer id;

    private String serviceName;

    private String useTerm;

    private String useTimes;

    private String describtion;

    private String frontEndDisplay;

    private String includeWeekend;

    private String includeHoliday;

    private String money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public String getUseTerm() {
        return useTerm;
    }

    public void setUseTerm(String useTerm) {
        this.useTerm = useTerm == null ? null : useTerm.trim();
    }

    public String getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(String useTimes) {
        this.useTimes = useTimes;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion == null ? null : describtion.trim();
    }

    public String getFrontEndDisplay() {
        return frontEndDisplay;
    }

    public void setFrontEndDisplay(String frontEndDisplay) {
        this.frontEndDisplay = frontEndDisplay;
    }

    public String getIncludeWeekend() {
        return includeWeekend;
    }

    public void setIncludeWeekend(String includeWeekend) {
        this.includeWeekend = includeWeekend;
    }

    public String getIncludeHoliday() {
        return includeHoliday;
    }

    public void setIncludeHoliday(String includeHoliday) {
        this.includeHoliday = includeHoliday;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }
}