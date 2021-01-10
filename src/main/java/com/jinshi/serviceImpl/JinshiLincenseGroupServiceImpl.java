package com.jinshi.serviceImpl;

import com.jinshi.entity.JinshiLincenseGroup;
import com.jinshi.entity.bo.JinshiLincenseGroupBo;
import com.jinshi.mapper.JinshiLincenseGroupMapper;
import com.jinshi.service.JinshiLincenseGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JinshiLincenseGroupServiceImpl implements JinshiLincenseGroupService {

    @Autowired
    private JinshiLincenseGroupMapper jinshiLincenseGroupMapper;

    @Override
    public List<JinshiLincenseGroupBo> selectLincenseGroupPage(Integer pageNum, Integer pageSize, Integer parkId) {
        return jinshiLincenseGroupMapper.findByparkId(pageNum, pageSize,parkId);
    }

    @Override
    public int getLincenseGroupRecords(Integer parkId) {
        return jinshiLincenseGroupMapper.getLincenseGroupRecords(parkId);
    }

    @Override
    public List<JinshiLincenseGroup> searchLincenseGroup(String keyWord, Integer pageNum, Integer pageSize, Integer parkId) {
        return jinshiLincenseGroupMapper.searchLincenseGroup(keyWord, pageNum, pageSize,parkId);
    }

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return jinshiLincenseGroupMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(JinshiLincenseGroup jinshiLincenseGroup) {
        return jinshiLincenseGroupMapper.insertSelective(jinshiLincenseGroup);
    }

    @Override
    public Integer updateByPrimaryKey(JinshiLincenseGroup jinshiLincenseGroup) {
        return jinshiLincenseGroupMapper.updateByPrimaryKeySelective(jinshiLincenseGroup);
    }

    @Override
    public List<JinshiLincenseGroup> selectALl() {
        return jinshiLincenseGroupMapper.selectALl();
    }

    @Override
    public JinshiLincenseGroup selectAllByJsNumber(JinshiLincenseGroup jinshiLincenseGroup) {
        return jinshiLincenseGroupMapper.selectAllByJsNumber(jinshiLincenseGroup);
    }

    @Override
    public int getLincenseGroupRecordsSearch(String keyWord, Integer parkId) {
        return jinshiLincenseGroupMapper.getLincenseGroupRecordsSearch(keyWord,parkId);
    }

    @Override
    public JinshiLincenseGroup selectByPrimaryKey(Integer lgId) {
        return jinshiLincenseGroupMapper.selectByPrimaryKey(lgId);
    }

    @Override
    public List<JinshiLincenseGroup> selectByParkIdAndAreaId(JinshiLincenseGroup jinshiLincenseGroup) {
        return jinshiLincenseGroupMapper.selectByParkIdAndAreaId(jinshiLincenseGroup);
    }
}
