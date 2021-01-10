package com.jinshi.mapper;

import com.jinshi.entity.JinshiParkingSetup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JinshiParkingSetupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JinshiParkingSetup record);

    int insertSelective(JinshiParkingSetup record);

    JinshiParkingSetup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JinshiParkingSetup record);

    int updateByPrimaryKey(JinshiParkingSetup record);

    Integer updateSetupByParkId(JinshiParkingSetup jinshiParkingSetup);

    JinshiParkingSetup selectParkSetup(Integer parkId);

    Integer updateSetupHoliday(Integer type1);

    Integer updateOpenMode(JinshiParkingSetup jinshiParkingSetup);

    List<JinshiParkingSetup> selectByAgentId(Integer id);
}