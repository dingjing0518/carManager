package com.jinshi.mapper;

import com.jinshi.entity.Parking;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParkingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Parking record);

    int insertSelective(Parking record);

    Parking selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Parking record);

    int updateByPrimaryKey(Parking record);

    List<Parking> selectParkingAll(Integer agentId);
}