package com.jinshi.entity;

public class Parking {
    private Integer id;

    private String name;

    private String site;

    private String members;

    private String totalTurnover;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members == null ? null : members.trim();
    }

    public String getTotalTurnover() {
        return totalTurnover;
    }

    public void setTotalTurnover(String totalTurnover) {
        this.totalTurnover = totalTurnover == null ? null : totalTurnover.trim();
    }
}