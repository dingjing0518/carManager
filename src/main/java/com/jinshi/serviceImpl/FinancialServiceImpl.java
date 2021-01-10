package com.jinshi.serviceImpl;

import com.jinshi.entity.Financial;
import com.jinshi.mapper.FinancialMapper;
import com.jinshi.service.FinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialServiceImpl implements FinancialService {

    @Autowired
    private FinancialMapper financialMapper;

    @Override
    public List<Financial> selectFinancialAll(){return financialMapper.selectFinancialAll();}

    @Override
    public int getFinancialRecords() {
        return financialMapper.getFinancialRecords();
    }

    @Override
    public List<Financial> selectFinancialForPage(Integer pageNum, Integer pageSize) {
        return financialMapper.selectFinancialForPage(pageNum,pageSize);
    }

    @Override
    public List<Financial> searchFinancial(String keyWork, Integer pageNum, Integer pageSize) {
        return financialMapper.searchFinancial(keyWork,pageNum,pageSize);
    }

    @Override
    public int getFinancialRecordsSearch(String keyWord) {
        return financialMapper.getFinancialRecordsSearch(keyWord);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return financialMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Financial record) {
        return financialMapper.insert(record);
    }

    @Override
    public int insertSelective(Financial record) {
        return financialMapper.insertSelective(record);
    }

    @Override
    public Financial selectByPrimaryKey(Integer id) {
        return financialMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Financial record) {
        return financialMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Financial record) {
        return financialMapper.updateByPrimaryKey(record);
    }
}
