package com.jinshi.service;

import com.jinshi.entity.JinshiParkSetting;

import java.util.List;

public interface JinshiParkSettingService {
    List<JinshiParkSetting> selectByParkKey(Integer parkId);

    int deleteByPrimaryKey(Integer id);

    int insert(JinshiParkSetting record);

    int insertSelective(JinshiParkSetting record);


    JinshiParkSetting selectByPrimaryKey(Integer jpsId);

    int updateByPrimaryKeySelective(JinshiParkSetting record);

    int updateByPrimaryKey(JinshiParkSetting record);

    int updateByParkidAndCarType(JinshiParkSetting record);

    JinshiParkSetting selectByCarTypeAndParkId(Integer parkId, Integer carType);
}
