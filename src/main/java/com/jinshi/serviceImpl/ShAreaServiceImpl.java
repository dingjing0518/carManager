package com.jinshi.serviceImpl;

import com.jinshi.entity.ShArea;
import com.jinshi.mapper.ShAreaMapper;
import com.jinshi.service.ShAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShAreaServiceImpl implements ShAreaService {
    @Autowired
    private ShAreaMapper shAreaMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return shAreaMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ShArea record) {
        return shAreaMapper.insert(record);
    }

    @Override
    public int insertSelective(ShArea record) {
        return shAreaMapper.insertSelective(record);
    }

    @Override
    public ShArea selectByPrimaryKey(Integer id) {
        return shAreaMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ShArea record) {
        return shAreaMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ShArea record) {
        return shAreaMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ShArea> selects() {
        return shAreaMapper.selects();
    }

    @Override
    public List<ShArea> selectcity(String name) {
        return shAreaMapper.selectcity(name);
    }
}
