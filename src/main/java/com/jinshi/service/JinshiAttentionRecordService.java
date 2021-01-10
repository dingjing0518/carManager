package com.jinshi.service;

import com.jinshi.entity.JinshiAttentionRecord;

import java.util.Date;
import java.util.List;

public interface JinshiAttentionRecordService {
    List<JinshiAttentionRecord> selectAttentionRecord(Integer pageNum, Integer pageSize, Integer parkId);

    int getAttentionRecord(Integer parkId);

    List<JinshiAttentionRecord> selectAttentionRecordByTime(Integer pageNum, Integer pageSize, Integer parkId, Date startTime, Date endTime, String keyWord);

    int getAttentionRecordByTime(Integer parkId, Date startTime, Date endTime, String keyWord);

    Integer deleteByPrimaryKey(Integer id);

    Integer insert(JinshiAttentionRecord jinshiAttentionRecord);

    Integer updateByOrderId(JinshiAttentionRecord jinshiAttentionRecord);

}
