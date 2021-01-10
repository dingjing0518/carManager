package com.jinshi.service;

import com.jinshi.entity.JinshiPresenceTrack;

import java.util.Date;
import java.util.List;

public interface JinshiPresenceTrackService {
    List<JinshiPresenceTrack> selectPresenceTrackPage(Integer pageNum, Integer pageSize, String parkId);

    int getPresenceTrackRecords(String parkId);

    List<JinshiPresenceTrack> searchPresenceTrack(String keyWord, Integer pageNum, Integer pageSize, String parkId);

    List<JinshiPresenceTrack> selectPresenceTrackByTime(Integer pageNum, Integer pageSize, Date startTime, Date endTime, String parkId, String keyWord);

    int getPresenceTrackByTime(Date startTime, Date endTime, String parkId, String keyWord);

    Integer insertPresenceTrack(JinshiPresenceTrack jinshiPresenceTrack);

    List<JinshiPresenceTrack> selectAllInfo(String ptParkid, String lincense);

    List<JinshiPresenceTrack> selectByPtLpId(Integer ptLpId);

    Integer deletePresenceTrack(Integer ptLpId);

    int getPresenceTrackRecordsSearch(String parkId, String keyWord);
}
