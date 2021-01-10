package com.jinshi.serviceImpl;

import com.jinshi.entity.WhiteList;
import com.jinshi.mapper.WhiteListMapper;
import com.jinshi.service.WhiteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class WhiteListServiceImpl implements WhiteListService {

    @Autowired
    private WhiteListMapper whiteListMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return whiteListMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WhiteList record) {
        return whiteListMapper.insert(record);
    }

    @Override
    public int insertSelective(WhiteList record) {
        return whiteListMapper.insertSelective(record);
    }

    @Override
    public WhiteList selectByPrimaryKey(Integer id) {
        return whiteListMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WhiteList record) {
        return whiteListMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WhiteList record) {
        return whiteListMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<WhiteList> selectWhiteListAll(){return whiteListMapper.selectWhiteListAll();}

    @Override
    public List<WhiteList> searchWhiteList(String keyWord, Integer pageNum, Integer pageSize, Integer parkId){
        return whiteListMapper.searchWhiteList(keyWord,pageNum,pageSize,parkId);
    }

    @Override
    public WhiteList checkWhiteListByLincese(String Lincese,Integer parkId){
        return whiteListMapper.checkWhiteListByLincese(Lincese,parkId);
    }

    @Override
    public List<WhiteList> selectWhiteListForPage(Integer pageNum, Integer pageSize,Integer parkId) {
        return whiteListMapper.selectWhiteListForPage(pageNum,pageSize,parkId);
    }

    @Override
    public int getWhiteListTotalRecords(Integer parkId) {
        return whiteListMapper.getWhiteListTotalRecords(parkId);
    }

    @Override
    public void updateByIsFlag(Integer id) {
        whiteListMapper.updateByIsFlag(id);
    }

    @Override
    public void updateByIsFlagNo(Integer id) {
        whiteListMapper.updateByIsFlagNo(id);
    }

}
