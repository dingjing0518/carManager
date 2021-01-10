package com.jinshi.mapper;

import com.jinshi.entity.BlackList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlackListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlackList record);

    int insertSelective(BlackList record);

    BlackList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlackList record);

    int updateByPrimaryKey(BlackList record);

    List<BlackList> selectBlackListAll();

    BlackList checkBlackListByLincese(String lincensePlateNumber);

    List<BlackList> selectBlackListForPage(Integer pageNum, Integer pageSize,Integer parkId);

    int getBlackListTotalRecords(Integer parkId);

    List<BlackList> searchBlackList(String keyWord,Integer pageNum,Integer pageSize, Integer parkId);

    void updateByIsFlag(Integer id);

    void updateByIsFlagNo(Integer id);

    List<BlackList> checkBlackListByLinceseAndParkId(String lincensePlateNumber, Integer parkId);
}