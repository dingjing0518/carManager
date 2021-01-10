package com.jinshi.mapper;

import com.jinshi.entity.JinshiHistoricalTrack;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface JinshiHistoricalTrackMapper {
    int deleteByPrimaryKey(Integer htId);

    int insert(JinshiHistoricalTrack record);

    int insertSelective(JinshiHistoricalTrack record);

    JinshiHistoricalTrack selectByPrimaryKey(Integer htId);

    int updateByPrimaryKeySelective(JinshiHistoricalTrack record);

    int updateByPrimaryKey(JinshiHistoricalTrack record);

    List<JinshiHistoricalTrack> selectHistoricalTrackPage(Integer pageNum, Integer pageSize, String parkId);

    int getHistoricalTrackRecords(String parkId);

    List<JinshiHistoricalTrack> searchHistoricalTrack(String keyWord, Integer pageNum, Integer pageSize, String parkId);

    List<JinshiHistoricalTrack> selectHistoricalTrackByTime(Integer pageNum, Integer pageSize, Date startTime, Date endTime, String parkId, String keyWord);

    int getHistoricalTrackByTime(Date startTime, Date endTime, String parkId, String keyWord);

    int getHistoricalTrackRecordsSearch(String parkId, String keyWord);
}