package com.jinshi.service;

import com.jinshi.entity.Financial;

import java.util.List;

public interface FinancialService {
    int deleteByPrimaryKey(Integer id);

    int insert(Financial record);

    int insertSelective(Financial record);

    Financial selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Financial record);

    int updateByPrimaryKey(Financial record);

    List<Financial> selectFinancialAll();

    int getFinancialRecords();

    List<Financial> selectFinancialForPage(Integer pageNum,Integer pageSize);

    List<Financial> searchFinancial(String keyWork,Integer pageNum,Integer pageSize);

    int getFinancialRecordsSearch(String keyWord);
}
