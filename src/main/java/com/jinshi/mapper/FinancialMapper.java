package com.jinshi.mapper;

import com.jinshi.entity.Financial;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FinancialMapper {
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