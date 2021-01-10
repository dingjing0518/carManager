package com.jinshi.serviceImpl;

import com.jinshi.entity.JinshiMemberOrder;
import com.jinshi.mapper.JinshiMemberOrderMapper;
import com.jinshi.service.JinshiMemberOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JinshiMemberOrderServiceImpl implements JinshiMemberOrderService {

    @Autowired
    private JinshiMemberOrderMapper jinshiMemberOrderMapper;

    @Override
    public Integer insert(JinshiMemberOrder jinshiMemberOrder) {
        return jinshiMemberOrderMapper.insert(jinshiMemberOrder);
    }

    @Override
    public List<JinshiMemberOrder> selectMemberOrderForPage(Integer pageNum, Integer pageSize, Integer parkId) {
        return jinshiMemberOrderMapper.selectMemberOrderForPage(pageNum,pageSize,parkId);
    }

    @Override
    public int getMemberOrderRecords(Integer parkId) {
        return jinshiMemberOrderMapper.getMemberOrderRecords(parkId);
    }

    @Override
    public int getMemberOrderRecordsSearch(String keyWord, Integer parkId) {
        return jinshiMemberOrderMapper.getMemberOrderRecordsSearch(keyWord,parkId);
    }

    @Override
    public List<JinshiMemberOrder> searchMemberOrder(String keyWord, Integer pageNum, Integer pageSize, Integer parkId) {
        return jinshiMemberOrderMapper.searchMemberOrder(keyWord,pageNum,pageSize,parkId);
    }

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return jinshiMemberOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public JinshiMemberOrder selectByOrderId(String orderId) {
        return jinshiMemberOrderMapper.selectByOrderId(orderId);
    }

    @Override
    public int updateByOrderId(JinshiMemberOrder jinshiMemberOrder) {
        return jinshiMemberOrderMapper.updateByOrderId(jinshiMemberOrder);
    }
}
