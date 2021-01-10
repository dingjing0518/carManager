package com.jinshi.entity;

import java.util.List;

public class JinshiWebSonAll {
    private Integer id;

    private String icon;

    private String index;

    private String title;

    private String subs;

    private List<Subs> subss;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubs() {
        return subs;
    }

    public void setSubs(String subs) {
        this.subs = subs;
    }

    public List<Subs> getSubss() {
        return subss;
    }

    public void setSubss(List<Subs> subss) {
        this.subss = subss;
    }
}
