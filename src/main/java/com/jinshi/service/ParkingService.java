package com.jinshi.service;

import com.jinshi.entity.Parking;

import java.util.List;

public interface ParkingService {

    int deleteByPrimaryKey(Integer id);

    int insert(Parking record);

    int insertSelective(Parking record);

    Parking selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Parking record);

    int updateByPrimaryKey(Parking record);

    List<Parking> selectParkingAll(Integer agentId);
}
