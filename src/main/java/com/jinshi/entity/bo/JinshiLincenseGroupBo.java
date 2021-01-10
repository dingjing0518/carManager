package com.jinshi.entity.bo;

import com.jinshi.entity.JinshiLincenseGroup;

public class JinshiLincenseGroupBo extends JinshiLincenseGroup {

    private String areaName;

    @Override
    public String toString() {
        return "JinshiLincenseGroupBo{" +
                "areaName='" + areaName + '\'' +
                '}';
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
