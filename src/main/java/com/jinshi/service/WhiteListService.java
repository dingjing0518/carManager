package com.jinshi.service;

import com.jinshi.entity.WhiteList;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface WhiteListService  {
    int deleteByPrimaryKey(Integer id);

    int insert(WhiteList record);

    int insertSelective(WhiteList record);

    WhiteList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WhiteList record);

    int updateByPrimaryKey(WhiteList record);

    List<WhiteList> selectWhiteListAll();

    List<WhiteList> searchWhiteList( String keyWord, Integer pageNum, Integer pageSize, Integer parkId);

    WhiteList checkWhiteListByLincese(String Lincese,Integer parkId);

    List<WhiteList> selectWhiteListForPage(Integer pageNum,Integer pageSize,Integer parkId);

    int getWhiteListTotalRecords(Integer parkId);

    void updateByIsFlag(Integer id);

    void updateByIsFlagNo(Integer id);
}
