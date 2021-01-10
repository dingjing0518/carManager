package com.jinshi.serviceImpl;

import com.jinshi.entity.JinshiCameras;
import com.jinshi.entity.JinshiHistoricalTrack;
import com.jinshi.mapper.JinshiCamerasMapper;
import com.jinshi.mapper.JinshiHistoricalTrackMapper;
import com.jinshi.service.JinshiHistoricalTrackService;
import com.jinshi.util.GlobalVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JinshiHistoricalTrackServiceImpl implements JinshiHistoricalTrackService {

    @Autowired
    private JinshiHistoricalTrackMapper jinshiHistoricalTrackMapper;

    @Autowired
    private JinshiCamerasMapper jinshiCamerasMapper;

    @Override
    public List<JinshiHistoricalTrack> selectHistoricalTrackPage(Integer pageNum, Integer pageSize, String parkId) {
        List<JinshiHistoricalTrack> jinshiHistoricalTrackList = jinshiHistoricalTrackMapper.selectHistoricalTrackPage(pageNum, pageSize,parkId);
        for (JinshiHistoricalTrack jinshiHistoricalTrack : jinshiHistoricalTrackList) {
            JinshiCameras jinshiCameras = jinshiCamerasMapper.selectByThandle(String.valueOf(GlobalVariable.parkId), jinshiHistoricalTrack.getHtThandle());
            if (null != jinshiCameras) {
                jinshiHistoricalTrack.setJaName(jinshiCameras.getJcName());
            }
        }
        return jinshiHistoricalTrackList;
    }

    @Override
    public int getHistoricalTrackRecords(String parkId) {
        return jinshiHistoricalTrackMapper.getHistoricalTrackRecords(parkId);
    }

    @Override
    public List<JinshiHistoricalTrack> searchHistoricalTrack(String keyWord, Integer pageNum, Integer pageSize, String parkId) {
        List<JinshiHistoricalTrack> jinshiHistoricalTrackList = jinshiHistoricalTrackMapper.searchHistoricalTrack(keyWord,pageNum,pageSize,parkId);
        for (JinshiHistoricalTrack jinshiHistoricalTrack : jinshiHistoricalTrackList) {
            JinshiCameras jinshiCameras = jinshiCamerasMapper.selectByThandle(String.valueOf(GlobalVariable.parkId), jinshiHistoricalTrack.getHtThandle());
            if (null != jinshiCameras) {
                jinshiHistoricalTrack.setJaName(jinshiCameras.getJcName());
            }
        }
        return jinshiHistoricalTrackList;
    }

    @Override
    public List<JinshiHistoricalTrack> selectHistoricalTrackByTime(Integer pageNum, Integer pageSize, Date startTime, Date endTime, String parkId, String keyWord) {
        List<JinshiHistoricalTrack> jinshiHistoricalTrackList = jinshiHistoricalTrackMapper.selectHistoricalTrackByTime(pageNum,pageSize,startTime,endTime,parkId,keyWord);
        for (JinshiHistoricalTrack jinshiHistoricalTrack : jinshiHistoricalTrackList) {
            JinshiCameras jinshiCameras = jinshiCamerasMapper.selectByThandle(String.valueOf(GlobalVariable.parkId), jinshiHistoricalTrack.getHtThandle());
            if (null != jinshiCameras) {
                jinshiHistoricalTrack.setJaName(jinshiCameras.getJcName());
            }
        }
        return jinshiHistoricalTrackList;
    }

    @Override
    public int getHistoricalTrackByTime(Date startTime, Date endTime, String parkId, String keyWord) {
        return jinshiHistoricalTrackMapper.getHistoricalTrackByTime(startTime,endTime,parkId,keyWord);
    }

    @Override
    public int insertHistoryTrack(JinshiHistoricalTrack jinshiHistoricalTrack) {
        return jinshiHistoricalTrackMapper.insertSelective(jinshiHistoricalTrack);
    }

    @Override
    public int getHistoricalTrackRecordsSearch(String parkId, String keyWord) {
        return jinshiHistoricalTrackMapper.getHistoricalTrackRecordsSearch(parkId,keyWord);
    }
}
