package com.jinshi.serviceImpl;

import com.jinshi.entity.JinshiParkSetting;
import com.jinshi.mapper.JinshiParkSettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JinshiParkSettingService implements com.jinshi.service.JinshiParkSettingService {

    @Autowired
    private JinshiParkSettingMapper jinshiParkSettingMapper;


    @Override
    public List<JinshiParkSetting> selectByParkKey(Integer parkId) {
        return jinshiParkSettingMapper.selectByParkKey(parkId);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return jinshiParkSettingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(JinshiParkSetting record) {
        return jinshiParkSettingMapper.insert(record);
    }

    @Override
    public int insertSelective(JinshiParkSetting record) {
        return jinshiParkSettingMapper.insertSelective(record);
    }

    @Override
    public JinshiParkSetting selectByPrimaryKey(Integer id) {
        return jinshiParkSettingMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(JinshiParkSetting record) {
        return jinshiParkSettingMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(JinshiParkSetting record) {
        return jinshiParkSettingMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByParkidAndCarType(JinshiParkSetting record) {
        return jinshiParkSettingMapper.updateByParkidAndCarType(record);
    }

    @Override
    public JinshiParkSetting selectByCarTypeAndParkId(Integer parkId, Integer carType) {
        return jinshiParkSettingMapper.selectByCarTypeAndParkId(parkId,carType);
    }
}
