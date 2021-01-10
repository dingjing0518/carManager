package com.jinshi.mapper;

import com.jinshi.entity.OutInRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface OutInRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OutInRecord record);

    int insertSelective(OutInRecord record);

    OutInRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OutInRecord record);

    int updateByPrimaryKey(OutInRecord record);

    Map<String,Object> selectParkRecord(String memberId);
}