package com.jinshi.service;

import com.jinshi.entity.Agent;
import com.jinshi.entity.JinshiParking;

import java.util.List;


public interface JinshiParkingService {
    int deleteByPrimaryKey(Integer id);

    int insert(JinshiParking record);

    int insertSelective(JinshiParking record);

    JinshiParking selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JinshiParking record);

    int updateByPrimaryKey(JinshiParking record);

    JinshiParking selectByJpId(Integer jpId);

    List<JinshiParking> selectParkingAll();

    JinshiParking selectByNumber(String jpNumber);

    List<JinshiParking> selectParkingForPage(Integer pageNum, Integer pageSize,Integer agentId);

    int getParkingTotalRecords(Integer agentId);

    List<JinshiParking> searchParking(String keyWord, Integer pageNum, Integer pageSize,Integer agentId);

    List<Agent> selectAllAgent();

    List<JinshiParking> selectAllParkingName(Integer jcId);

    List<JinshiParking> selectAllPark();

    List<JinshiParking> selectParkByParkId(Integer parkId);
}
