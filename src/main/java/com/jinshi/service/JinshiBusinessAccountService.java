package com.jinshi.service;

import com.jinshi.entity.JinshiBusinessAccount;
import com.jinshi.entity.JinshiBusinessAccountBo;

import java.util.List;

public interface JinshiBusinessAccountService {
    List<JinshiBusinessAccountBo> selectBusinessAccountPage(Integer pageNum, Integer pageSize,Integer parkId);

    int getBusinessAccountRecords(Integer parkId);

    List<JinshiBusinessAccount> searchBusinessAccount(String keyWord, Integer pageNum, Integer pageSize);

    Integer deleteByPrimaryKey(Integer id);

    Integer insert(JinshiBusinessAccountBo jinshiBusinessAccountBo);

    Integer updateByPrimaryKey(JinshiBusinessAccountBo jinshiBusinessAccount);

    List<JinshiBusinessAccount> selectAllBusinessName(Integer bpId, Integer areaId);
}
