package com.jinshi.serviceImpl;

import com.jinshi.entity.WxPayOrder;
import com.jinshi.mapper.WxPayOrderMapper;
import com.jinshi.service.WxPayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxPayOrderServiceImpl implements WxPayOrderService {

    @Autowired
    private WxPayOrderMapper wxPayOrderMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return wxPayOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WxPayOrder record) {
        return wxPayOrderMapper.insert(record);
    }

    @Override
    public int insertSelective(WxPayOrder record) {
        return wxPayOrderMapper.insertSelective(record);
    }

    @Override
    public WxPayOrder selectByPrimaryKey(Integer id) {
        return wxPayOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WxPayOrder record) {
        return wxPayOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WxPayOrder record) {
        return wxPayOrderMapper.updateByPrimaryKey(record);
    }
}
