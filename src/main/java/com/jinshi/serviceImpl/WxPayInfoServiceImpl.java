package com.jinshi.serviceImpl;

import com.jinshi.entity.WxPayInfo;
import com.jinshi.mapper.WxPayInfoMapper;
import com.jinshi.service.WxPayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxPayInfoServiceImpl implements WxPayInfoService {

    @Autowired
    private WxPayInfoMapper wxPayInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return wxPayInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WxPayInfo record) {
        return wxPayInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(WxPayInfo record) {
        return wxPayInfoMapper.insertSelective(record);
    }

    @Override
    public WxPayInfo selectByPrimaryKey(Integer id) {
        return wxPayInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WxPayInfo record) {
        return wxPayInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WxPayInfo record) {
        return wxPayInfoMapper.updateByPrimaryKey(record);
    }
}
