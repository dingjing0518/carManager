package com.jinshi.mapper;

import com.jinshi.entity.JinshiPresenceTrack;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface JinshiPresenceTrackMapper {
    int deleteByPrimaryKey(Integer ptId);

    int insert(JinshiPresenceTrack record);

    int insertSelective(JinshiPresenceTrack record);

    JinshiPresenceTrack selectByPrimaryKey(Integer ptId);

    int updateByPrimaryKeySelective(JinshiPresenceTrack record);

    int updateByPrimaryKey(JinshiPresenceTrack record);

    List<JinshiPresenceTrack> selectPresenceTrackPage(Integer pageNum, Integer pageSize, String parkId);

    int getPresenceTrackRecords(String parkId);

    List<JinshiPresenceTrack> searchPresenceTrack(String keyWord, Integer pageNum, Integer pageSize, String parkId);

    List<JinshiPresenceTrack> selectPresenceTrackByTime(Integer pageNum, Integer pageSize, Date startTime, Date endTime, String parkId, String keyWord);

    int getPresenceTrackByTime(Date startTime, Date endTime, String parkId, String keyWord);

    List<JinshiPresenceTrack> selectAllInfo(String ptParkid, String lincense);

    List<JinshiPresenceTrack> selectByPtLpId(Integer ptLpId);

    Integer deletePresenceTrack(Integer ptLpId);

    int getPresenceTrackRecordsSearch(String parkId, String keyWord);
}