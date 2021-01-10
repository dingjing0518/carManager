package com.jinshi.serviceImpl;

import com.jinshi.entity.LincensePlatess;
import com.jinshi.mapper.LincensePlatessMapper;
import com.jinshi.service.LincensePlatessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LincensePlatessServiceImpl implements LincensePlatessService {
    @Autowired
    private LincensePlatessMapper lincensePlatessMapper;
    @Override
    public List<LincensePlatess> searchLincense(String keyWork, Integer pageNum, Integer pageSize) {
        return lincensePlatessMapper.searchLincense(keyWork,pageNum,pageSize);
    }

    @Override
    public List<LincensePlatess> selectLincenseForPage(Integer pageNum, Integer pageSize) {
        return lincensePlatessMapper.selectLincenseForPage(pageNum,pageSize);
    }

    @Override
    public int getLincenseTotalRecords() {
        return lincensePlatessMapper.getLincenseTotalRecords();
    }

    @Override
    public List<LincensePlatess> selectss(Integer pageNum, Integer pageSize,String parkId) {
        return lincensePlatessMapper.selectss(pageNum,pageSize,parkId);
    }
}
