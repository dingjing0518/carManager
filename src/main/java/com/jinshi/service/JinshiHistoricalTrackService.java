package com.jinshi.service;

import com.jinshi.entity.JinshiHistoricalTrack;

import java.util.Date;
import java.util.List;

public interface JinshiHistoricalTrackService {

    List<JinshiHistoricalTrack> selectHistoricalTrackPage(Integer pageNum, Integer pageSize, String parkId);

    int getHistoricalTrackRecords(String parkId);

    List<JinshiHistoricalTrack> searchHistoricalTrack(String keyWord, Integer pageNum, Integer pageSize, String parkId);

    List<JinshiHistoricalTrack> selectHistoricalTrackByTime(Integer pageNum, Integer pageSize, Date startTime, Date endTime, String parkId, String keyWord);

    int getHistoricalTrackByTime(Date startTime, Date endTime, String parkId, String keyWord);

    int insertHistoryTrack(JinshiHistoricalTrack jinshiHistoricalTrack);

    int getHistoricalTrackRecordsSearch(String parkId, String keyWord);
}
