package com.jinshi.serviceImpl;

import com.jinshi.entity.JinshiAttention;
import com.jinshi.mapper.JinshiAttentionMapper;
import com.jinshi.service.JinshiAttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JinshiAttentionServiceImpl implements JinshiAttentionService {

    @Autowired
    private JinshiAttentionMapper jinshiAttentionMapper;

    @Override
    public List<JinshiAttention> selectAttentionForPage(Integer pageNum, Integer pageSize,String parkId) {
        return jinshiAttentionMapper.selectAttentionForPage(pageNum,pageSize,parkId);
    }

    @Override
    public int getAttentionTotalRecords(String parkId) {
        return jinshiAttentionMapper.getAttentionTotalRecords(parkId);
    }

    @Override
    public List<JinshiAttention> searchAttention(String keyWord, Integer pageNum, Integer pageSize,String parkId) {
        return jinshiAttentionMapper.searchAttention(keyWord,pageNum,pageSize,parkId);
    }

    @Override
    public Integer insert(JinshiAttention jinshiAttention) {
        return jinshiAttentionMapper.insert(jinshiAttention);
    }

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return jinshiAttentionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKey(JinshiAttention jinshiAttention) {
        return jinshiAttentionMapper.updateByPrimaryKey(jinshiAttention);
    }

    @Override
    public List<JinshiAttention> searchAttentionUtil(String jcLincensePlateId) {
        return jinshiAttentionMapper.searchAttentionUtil(jcLincensePlateId);
    }

    @Override
    public List<JinshiAttention> selectByParkId(String parkId) {
        return jinshiAttentionMapper.selectByParkId(parkId);
    }
}
