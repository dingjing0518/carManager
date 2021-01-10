package com.jinshi.serviceImpl;

import com.jinshi.entity.BlackList;
import com.jinshi.mapper.BlackListMapper;
import com.jinshi.service.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlackListServiceImpl implements BlackListService {

    @Autowired
    private BlackListMapper blackListMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return blackListMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BlackList record) {
        return blackListMapper.insert(record);
    }

    @Override
    public int insertSelective(BlackList record) {
        return blackListMapper.insertSelective(record);
    }

    @Override
    public BlackList selectByPrimaryKey(Integer id) {
        return blackListMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BlackList record) {
        return blackListMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BlackList record) {
        return blackListMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<BlackList> selectBlackListAll(){return blackListMapper.selectBlackListAll();}

    @Override
    public BlackList checkBlackListByLincese(String lincensePlateNumber) {
        return blackListMapper.checkBlackListByLincese(lincensePlateNumber);
    }

    @Override
    public List<BlackList> selectBlackListForPage(Integer pageNum, Integer pageSize,Integer parkId) {
        return blackListMapper.selectBlackListForPage(pageNum,pageSize,parkId);
    }

    @Override
    public int getBlackListTotalRecords(Integer parkId) {
        return blackListMapper.getBlackListTotalRecords(parkId);
    }

    @Override
    public List<BlackList> searchBlackList(String keyWord, Integer pageNum, Integer pageSize, Integer parkId) {
        return blackListMapper.searchBlackList(keyWord,pageNum,pageSize,parkId);
    }

    @Override
    public void updateByIsFlag(Integer id) {
        blackListMapper.updateByIsFlag(id);
    }

    @Override
    public void updateByIsFlagNo(Integer id) {
        blackListMapper.updateByIsFlagNo(id);
    }

    @Override
    public List<BlackList> checkBlackListByLinceseAndParkId(String lincensePlateNumber, Integer parkId) {
        return blackListMapper.checkBlackListByLinceseAndParkId(lincensePlateNumber, parkId);
    }
}
