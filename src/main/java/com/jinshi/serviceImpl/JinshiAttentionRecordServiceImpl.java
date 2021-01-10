package com.jinshi.serviceImpl;

import com.jinshi.entity.JinshiAttentionRecord;
import com.jinshi.mapper.JinshiAttentionRecordMapper;
import com.jinshi.service.JinshiAttentionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JinshiAttentionRecordServiceImpl implements JinshiAttentionRecordService {

    @Autowired
    private JinshiAttentionRecordMapper jinshiAttentionRecordMapper;

    @Override
    public List<JinshiAttentionRecord> selectAttentionRecord(Integer pageNum, Integer pageSize, Integer parkId) {
        return jinshiAttentionRecordMapper.selectAttentionRecord(pageNum,pageSize,parkId);
    }

    @Override
    public int getAttentionRecord(Integer parkId) {
        return jinshiAttentionRecordMapper.getAttentionRecord(parkId);
    }

    @Override
    public List<JinshiAttentionRecord> selectAttentionRecordByTime(Integer pageNum, Integer pageSize, Integer parkId, Date startTime, Date endTime, String keyWord) {
        return jinshiAttentionRecordMapper.selectAttentionRecordByTime(pageNum,pageSize,parkId,startTime,endTime,keyWord);
    }

    @Override
    public int getAttentionRecordByTime(Integer parkId, Date startTime, Date endTime, String keyWord) {
        return jinshiAttentionRecordMapper.getAttentionRecordByTime(parkId,startTime,endTime,keyWord);
    }

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return jinshiAttentionRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(JinshiAttentionRecord jinshiAttentionRecord) {
        return jinshiAttentionRecordMapper.insertSelective(jinshiAttentionRecord);
    }

    @Override
    public Integer updateByOrderId(JinshiAttentionRecord jinshiAttentionRecord) {
        return jinshiAttentionRecordMapper.updateByOrderId(jinshiAttentionRecord);
    }
}
