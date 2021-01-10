package com.jinshi.serviceImpl;

import com.jinshi.entity.JinshiCameras;
import com.jinshi.entity.JinshiPresenceTrack;
import com.jinshi.mapper.JinshiCamerasMapper;
import com.jinshi.mapper.JinshiPresenceTrackMapper;
import com.jinshi.service.JinshiPresenceTrackService;
import com.jinshi.util.GlobalVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JinshiPresenceTrackServiceImpl implements JinshiPresenceTrackService {

    @Autowired
    private JinshiPresenceTrackMapper jinshiPresenceTrackMapper;

    @Autowired
    private JinshiCamerasMapper jinshiCamerasMapper;

    @Override
    public List<JinshiPresenceTrack> selectPresenceTrackPage(Integer pageNum, Integer pageSize, String parkId) {
        List<JinshiPresenceTrack> jinshiPresenceTrackList = jinshiPresenceTrackMapper.selectPresenceTrackPage(pageNum, pageSize,parkId);
        for (JinshiPresenceTrack jinshiPresenceTrack : jinshiPresenceTrackList) {
            JinshiCameras jinshiCameras = jinshiCamerasMapper.selectByThandle(String.valueOf(GlobalVariable.parkId), jinshiPresenceTrack.getPtThandle());
            if (null != jinshiCameras) {
                jinshiPresenceTrack.setJcName(jinshiCameras.getJcName());
            }
        }
        return jinshiPresenceTrackList;
    }

    @Override
    public int getPresenceTrackRecords(String parkId) {
        return jinshiPresenceTrackMapper.getPresenceTrackRecords(parkId);
    }

    @Override
    public List<JinshiPresenceTrack> searchPresenceTrack(String keyWord, Integer pageNum, Integer pageSize, String parkId) {
        List<JinshiPresenceTrack> jinshiPresenceTrackList = jinshiPresenceTrackMapper.searchPresenceTrack(keyWord,pageNum,pageSize,parkId);
        for (JinshiPresenceTrack jinshiPresenceTrack : jinshiPresenceTrackList) {
            JinshiCameras jinshiCameras = jinshiCamerasMapper.selectByThandle(String.valueOf(GlobalVariable.parkId), jinshiPresenceTrack.getPtThandle());
            if (null != jinshiCameras) {
                jinshiPresenceTrack.setJcName(jinshiCameras.getJcName());
            }
        }
        return jinshiPresenceTrackList;
    }

    @Override
    public List<JinshiPresenceTrack> selectPresenceTrackByTime(Integer pageNum, Integer pageSize, Date startTime, Date endTime, String parkId, String keyWord) {
        List<JinshiPresenceTrack> jinshiPresenceTrackList = jinshiPresenceTrackMapper.selectPresenceTrackByTime(pageNum,pageSize,startTime,endTime,parkId,keyWord);
        for (JinshiPresenceTrack jinshiPresenceTrack : jinshiPresenceTrackList) {
            JinshiCameras jinshiCameras = jinshiCamerasMapper.selectByThandle(String.valueOf(GlobalVariable.parkId), jinshiPresenceTrack.getPtThandle());
            if (null != jinshiCameras) {
                jinshiPresenceTrack.setJcName(jinshiCameras.getJcName());
            }
        }
        return jinshiPresenceTrackList;
    }

    @Override
    public int getPresenceTrackByTime(Date startTime, Date endTime, String parkId, String keyWord) {
        return jinshiPresenceTrackMapper.getPresenceTrackByTime(startTime,endTime,parkId,keyWord);
    }

    @Override
    public Integer insertPresenceTrack(JinshiPresenceTrack jinshiPresenceTrack) {
        return jinshiPresenceTrackMapper.insertSelective(jinshiPresenceTrack);
    }

    @Override
    public List<JinshiPresenceTrack> selectAllInfo(String ptParkid, String lincense) {
        return jinshiPresenceTrackMapper.selectAllInfo(lincense,ptParkid);
    }

    @Override
    public List<JinshiPresenceTrack> selectByPtLpId(Integer ptLpId) {
        return jinshiPresenceTrackMapper.selectByPtLpId(ptLpId);
    }

    @Override
    public Integer deletePresenceTrack(Integer ptLpId) {
        return jinshiPresenceTrackMapper.deletePresenceTrack(ptLpId);
    }

    @Override
    public int getPresenceTrackRecordsSearch(String parkId, String keyWord) {
        return jinshiPresenceTrackMapper.getPresenceTrackRecordsSearch(parkId,keyWord);
    }
}
