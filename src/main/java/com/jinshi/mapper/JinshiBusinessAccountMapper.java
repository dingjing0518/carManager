package com.jinshi.mapper;

import com.jinshi.entity.JinshiBusinessAccount;
import com.jinshi.entity.JinshiBusinessAccountBo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JinshiBusinessAccountMapper {
    int deleteByPrimaryKey(Integer bcId);

    int insert(JinshiBusinessAccount record);

    int insertSelective(JinshiBusinessAccount record);

    JinshiBusinessAccount selectByPrimaryKey(Integer bcId);

    int updateByPrimaryKeySelective(JinshiBusinessAccount record);

    int updateByPrimaryKey(JinshiBusinessAccount record);

    List<JinshiBusinessAccount> selectBusinessAccountPage(Integer pageNum, Integer pageSize,Integer bcParkingId);

    int getBusinessAccountRecords(Integer bcParkingId);

    List<JinshiBusinessAccount> searchBusinessAccount(String keyWord, Integer pageNum, Integer pageSize);

    JinshiBusinessAccount selectByBcName(String couponBcName,Integer parkId);

    List<JinshiBusinessAccount> selectAllBusinessName(Integer bpId, Integer areaId);

    JinshiBusinessAccount findbc(JinshiBusinessAccountBo jinshiBusinessAccountBo);
}