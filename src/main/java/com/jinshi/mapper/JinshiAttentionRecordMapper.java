package com.jinshi.mapper;

import com.jinshi.entity.JinshiAttentionRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface JinshiAttentionRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JinshiAttentionRecord record);

    int insertSelective(JinshiAttentionRecord record);

    JinshiAttentionRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JinshiAttentionRecord record);

    int updateByPrimaryKey(JinshiAttentionRecord record);

    List<JinshiAttentionRecord> selectAttentionRecord(Integer pageNum, Integer pageSize, Integer parkId);

    int getAttentionRecord(Integer parkId);

    List<JinshiAttentionRecord> selectAttentionRecordByTime(Integer pageNum, Integer pageSize, Integer parkId, Date startTime, Date endTime, String keyWord);

    int getAttentionRecordByTime(Integer parkId, Date startTime, Date endTime, String keyWord);

    Integer updateByOrderId(JinshiAttentionRecord jinshiAttentionRecord);
}