package com.jinshi.serviceImpl;

import com.jinshi.entity.Service;
import com.jinshi.mapper.ServiceMapper;
import com.jinshi.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceMapper serviceMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return serviceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Service record) {
        return serviceMapper.insert(record);
    }

    @Override
    public int insertSelective(Service record) {
        return serviceMapper.insertSelective(record);
    }

    @Override
    public Service selectByPrimaryKey(Integer id) {
        return serviceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Service record) {
        return serviceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Service record) {
        return serviceMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Service> selectServiceAll(){return serviceMapper.selectServiceAll();}

    @Override
    public List<Service> selectServcieForPage(Integer pageNum, Integer pageSize) {
        return serviceMapper.selectServcieForPage(pageNum,pageSize);
    }

    @Override
    public int getServiceTotalRecords() {
        return serviceMapper.getServiceTotalRecords();
    }

    @Override
    public List<Service> searchService(String keyWord, Integer pageNum, Integer pageSize) {
        return serviceMapper.searchService(keyWord,pageNum,pageSize);
    }

    @Override
    public int getServiceTotalRecordsSearch(String keyWord) {
        return serviceMapper.getServiceTotalRecordsSearch(keyWord);
    }
}
