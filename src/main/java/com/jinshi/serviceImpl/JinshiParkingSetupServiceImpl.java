package com.jinshi.serviceImpl;

import com.jinshi.entity.JinshiParkingSetup;
import com.jinshi.mapper.JinshiParkingSetupMapper;
import com.jinshi.service.JinshiParkingSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JinshiParkingSetupServiceImpl implements JinshiParkingSetupService {

    @Autowired
    private JinshiParkingSetupMapper jinshiParkingSetupMapper;

    @Override
    public Integer updateSetup(JinshiParkingSetup jinshiParkingSetup) {
        return jinshiParkingSetupMapper.updateSetupByParkId(jinshiParkingSetup);
    }

    @Override
    public JinshiParkingSetup selectParkSetup(Integer parkId) {
        return jinshiParkingSetupMapper.selectParkSetup(parkId);
    }

    @Override
    public Integer updateSetupHoliday(Integer type1) {
        return jinshiParkingSetupMapper.updateSetupHoliday(type1);
    }

    @Override
    public Integer updateOpenMode(JinshiParkingSetup jinshiParkingSetup) {
        return jinshiParkingSetupMapper.updateOpenMode(jinshiParkingSetup);
    }
}
