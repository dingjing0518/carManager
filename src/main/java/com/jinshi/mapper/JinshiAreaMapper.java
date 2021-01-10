package com.jinshi.mapper;

import com.jinshi.entity.JinshiArea;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JinshiAreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JinshiArea record);

    int insertSelective(JinshiArea record);

    JinshiArea selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JinshiArea record);

    int updateByPrimaryKey(JinshiArea record);

    List<JinshiArea> selectAreaForPage(Integer pageNum, Integer pageSize, Integer parkId);

    int getAreaTotalRecords(Integer parkId);

    List<JinshiArea> searchArea(String keyWord, Integer pageNum, Integer pageSize,Integer parkId);

    List<JinshiArea> selectAreaNameAll(Integer parkId);

    JinshiArea selectByAreaName(String bcAreaName);

    JinshiArea findByAreaName(String bcAreaName,Integer parkId);

    JinshiArea selectByJcArea(String jcArea, Integer parkId);

    List<JinshiArea> selectAllArea(Integer parkId);
}