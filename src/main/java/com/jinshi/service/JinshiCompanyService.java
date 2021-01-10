package com.jinshi.service;

import com.jinshi.entity.JinshiCompany;
import com.jinshi.entity.JinshiDepartment;

import java.util.List;

public interface JinshiCompanyService {

    int deleteByPrimaryKey(Integer id);

    int insert(JinshiCompany record);

    List<JinshiCompany> selectCompanyForPage(Integer pageNum, Integer pageSize,Integer parkId);

    int getCompanyTotalRecords(Integer parkId);

    List<JinshiCompany> searchCompany(String keyWord,Integer pageNum, Integer pageSize, Integer parkId);

    int updateByPrimaryKey(JinshiCompany jinshiCompany);

    List<JinshiDepartment> selects(Integer id);

    List<JinshiCompany> selectname(Integer parkId);
}
