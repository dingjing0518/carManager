package com.jinshi.service;

import com.jinshi.entity.LincensePlate;
import com.jinshi.entity.LincensePlatess;

import java.util.List;

public interface LincensePlatessService {

    List<LincensePlatess> searchLincense(String keyWork, Integer pageNum, Integer pageSize);

    List<LincensePlatess> selectLincenseForPage(Integer pageNum, Integer pageSize);

    int getLincenseTotalRecords();



    List<LincensePlatess> selectss(Integer pageNum, Integer pageSize,String parkId);

}
