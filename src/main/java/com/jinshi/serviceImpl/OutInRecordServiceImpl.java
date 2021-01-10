package com.jinshi.serviceImpl;

import com.jinshi.entity.OutInRecord;
import com.jinshi.mapper.OutInRecordMapper;
import com.jinshi.service.OutInRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class OutInRecordServiceImpl implements OutInRecordService {

    @Autowired
    private OutInRecordMapper outInRecordMapper;


    @Override
    public void connCamera(int tHandle){

    }
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return outInRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OutInRecord record) {
        return outInRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(OutInRecord record) {
        return outInRecordMapper.insertSelective(record);
    }

    @Override
    public OutInRecord selectByPrimaryKey(Integer id) {
        return outInRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(OutInRecord record) {
        return outInRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OutInRecord record) {
        return outInRecordMapper.updateByPrimaryKey(record);
    }

    /**
     * 停车记录，根据用户ID
     */
    @Override
    public Map<String,Object> selectParkRecord(String memberId) {

        Map<String,Object> data = outInRecordMapper.selectParkRecord(memberId);
        Date entryTimes = (Date) data.get("entry_time");
        Date exitTimes = (Date)data.get("exit_time");
        long number = exitTimes.getTime() - entryTimes.getTime();
        data.put("number",number);  // 进出场时间相差的毫秒数

        return data;
    }
}
