package com.jinshi.serviceImpl;

import com.jinshi.entity.WxUserInfo;
import com.jinshi.mapper.WxUserInfoMapper;
import com.jinshi.service.WxUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxUserInfoServiceImpl implements WxUserInfoService  {

    @Autowired
    private WxUserInfoMapper wxUserInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return wxUserInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WxUserInfo record) {
        return wxUserInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(WxUserInfo record) {
        return wxUserInfoMapper.insertSelective(record);
    }

    @Override
    public WxUserInfo selectByPrimaryKey(Integer id) {
        return wxUserInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WxUserInfo record) {
        return wxUserInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WxUserInfo record) {
        return wxUserInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByOpenid(WxUserInfo wxUserInfo) {
        return wxUserInfoMapper.updateByOpenid(wxUserInfo);
    }

    @Override
    public String selectByOpenid(String openId) {
        return wxUserInfoMapper.selectByOpenid(openId);
    }
}
