package com.jinshi.serviceImpl;

import com.jinshi.entity.JinshiWxcar;
import com.jinshi.mapper.JinshiWxcarMapper;
import com.jinshi.service.JinshiWxcarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JinshiWxcarServiceImpl implements JinshiWxcarService {

    @Autowired
    private JinshiWxcarMapper jinshiWxcarMapper;

    @Override
    public Integer insertWxUser(JinshiWxcar jinshiWxcar) {
        return jinshiWxcarMapper.insert(jinshiWxcar);
    }

    @Override
    public JinshiWxcar selectByCarNumber(String carNumber) {
        return jinshiWxcarMapper.selectByCarNumber(carNumber);
    }

    @Override
    public List<JinshiWxcar> selectWxUser(String openid) {
        return jinshiWxcarMapper.selectWxUser(openid);
    }

    @Override
    public int updateByOpenid(JinshiWxcar jinshiWxcar1) {
        return jinshiWxcarMapper.updateByOpenid(jinshiWxcar1);
    }
}
