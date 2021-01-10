package com.jinshi.service;

import com.jinshi.entity.OutInRecord;

import java.util.Map;

public interface OutInRecordService {
    int deleteByPrimaryKey(Integer id);

    int insert(OutInRecord record);

    int insertSelective(OutInRecord record);

    OutInRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OutInRecord record);

    int updateByPrimaryKey(OutInRecord record);

    Map<String,Object> selectParkRecord(String memberId);
    void connCamera(int tHandle);
}
