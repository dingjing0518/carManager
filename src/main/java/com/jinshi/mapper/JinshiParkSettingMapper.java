package com.jinshi.mapper;

import com.jinshi.entity.JinshiParkSetting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface JinshiParkSettingMapper {
    int deleteByPrimaryKey(Integer jpsId);

    int insert(JinshiParkSetting record);

    int insertSelective(JinshiParkSetting record);

    JinshiParkSetting selectByPrimaryKey(Integer jpsId);

    int updateByPrimaryKeySelective(JinshiParkSetting record);

    int updateByPrimaryKey(JinshiParkSetting record);

    List<JinshiParkSetting> selectByParkKey(Integer parkId);

    int updateByParkidAndCarType(JinshiParkSetting record);

    JinshiParkSetting selectByCarTypeAndParkId(Integer parkId, Integer carType);
}