package com.jinshi.serviceImpl;

import com.jinshi.entity.Parking;
import com.jinshi.mapper.ParkingMapper;
import com.jinshi.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingMapper parkingMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return parkingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Parking record) {
        return parkingMapper.insert(record);
    }

    @Override
    public int insertSelective(Parking record) {
        return parkingMapper.insertSelective(record);
    }

    @Override
    public Parking selectByPrimaryKey(Integer id) {
        return parkingMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Parking record) {
        return parkingMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Parking record) {
        return parkingMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Parking> selectParkingAll(Integer agentId){return parkingMapper.selectParkingAll(agentId);}
}
