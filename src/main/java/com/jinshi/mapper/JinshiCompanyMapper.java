package com.jinshi.mapper;

import com.jinshi.entity.JinshiCompany;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JinshiCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JinshiCompany record);

    int insertSelective(JinshiCompany record);

    JinshiCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JinshiCompany record);

    int updateByPrimaryKey(JinshiCompany record);

    List<JinshiCompany> selectCompanyForPage(Integer pageNum, Integer pageSize,Integer parkId);

    int getCompanyTotalRecords(Integer parkId);

    List<JinshiCompany> searchCompany(String keyWord,Integer pageNum,Integer pageSize,Integer parkId);

    List<JinshiCompany> selectname(Integer parkId);

}