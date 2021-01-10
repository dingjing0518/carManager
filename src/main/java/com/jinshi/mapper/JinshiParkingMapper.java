package com.jinshi.mapper;

import com.jinshi.entity.JinshiParking;
//import com.sun.org.apache.bcel.internal.generic.INEG;
import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JinshiParkingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JinshiParking record);

    int insertSelective(JinshiParking record);

    JinshiParking selectByPrimaryKey(Integer jpId);

    int updateByPrimaryKeySelective(JinshiParking record);

    int updateByPrimaryKey(JinshiParking record);

    JinshiParking selectByJpId(Integer jpId);

    List<JinshiParking> selectParkingAll();

    JinshiParking selectByNumber(String jpNumber);

    List<JinshiParking> selectParkingForPage(Integer pageNum, Integer pageSize, Integer jpAgentId);

    int getParkingTotalRecords(Integer jpAgentId);

    List<JinshiParking> searchParking(String keyWord,Integer pageNum,Integer pageSize,Integer agentId);


    Integer deleteByJpNumber(String jpNumber);

    List<JinshiParking> selectAllParkingName(Integer jpId);

    JinshiParking selectByJpName(String bcParkingName);

    List<JinshiParking> selectAllPark();

    List<JinshiParking> selectParkByParkId(Integer parkId);

    List<JinshiParking> selectByAgentId(Integer id);

    int updateParkNumber(JinshiParking record);
}