package com.jinshi.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiLincenseGroup;
import com.jinshi.entity.JinshiMemberOrder;
import com.jinshi.entity.Member;
import com.jinshi.mapper.JinshiLincenseGroupMapper;
import com.jinshi.mapper.JinshiMemberOrderMapper;
import com.jinshi.mapper.MemberMapper;
import com.jinshi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private JinshiLincenseGroupMapper jinshiLincenseGroupMapper;

    @Autowired
    private JinshiMemberOrderMapper jinshiMemberOrderMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return memberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Member record) {
        return memberMapper.insert(record);
    }

    @Override
    public int insertSelective(Member record) {
        return memberMapper.insertSelective(record);
    }

    @Override
    public Member selectByPrimaryKey(Integer id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Member record) {
        return memberMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public JSONObject updateByPrimaryKey(Member record) {

        JSONObject jsonObject = new JSONObject();
        Member member = memberMapper.selectByPrimaryKey(record.getId());
        Integer lgId = record.getLgId();
        if (lgId != null) {
            JinshiLincenseGroup jinshiLincenseGroup = jinshiLincenseGroupMapper.selectByPrimaryKey(lgId);
            record.setLgId(Integer.valueOf(jinshiLincenseGroup.getJsNumber()));
        }
        if (record.getLincensePlateId().equals(member.getLincensePlateId())) {
            memberMapper.updateByPrimaryKeySelective(record);
            jsonObject.put("msg","编辑成功");
        } else {
            List<Member> members = memberMapper.selectByID(record.getLincensePlateId());
            if (members.size() > 0) {
                jsonObject.put("msg", "已存在此车牌");
            } else {
                memberMapper.updateByPrimaryKeySelective(record);
                jsonObject.put("msg","编辑成功");
            }
        }
        return jsonObject;
    }

    @Override
    public Member checkMemberByLincese(Member record){
        return memberMapper.checkMemberByLincese(record);
    }

    @Override
    public List<Member> selectMemberAll(Integer pageNum, Integer pageSize){
        return memberMapper.selectMemberAll(pageNum,pageSize);}

    @Override
    public List<Member> selectMemberForPage(Integer pageNum,Integer pageSize,Integer parkId){
        List<Member> members = memberMapper.selectMemberForPage(pageNum, pageSize, parkId);
        for (Member member : members) {
            Integer lgId = member.getLgId();
            if (null != lgId) {
                JinshiLincenseGroup jinshiLincenseGroup = jinshiLincenseGroupMapper.selectByPrimaryKey(lgId);
                if (null != jinshiLincenseGroup) {
                    member.setLgName(jinshiLincenseGroup.getJsNumber());
                }
            }
            List<JinshiMemberOrder> jinshiMemberOrder = jinshiMemberOrderMapper.selectByMemberId(member.getId());
            if (jinshiMemberOrder.size() > 0) {
                JinshiMemberOrder jinshiMemberOrder1 = jinshiMemberOrder.get(0);
                member.setJmoPayable(jinshiMemberOrder1.getJmoPayable());
                member.setJmoActualCost(jinshiMemberOrder1.getJmoActualCost());
            }
        }
        return members;
    }

    @Override
    public int getMemberTotalRecordsSearch(Integer parkId, String keyWord){
        return memberMapper.getMemberTotalRecordsSearch(parkId,keyWord);
    }

    @Override
    public int deleteByLincense(String lincense) {
        return memberMapper.deleteByLincense();
    }

    @Override
    public List<Member> searchMember(String areaName,String phoneNumber,String lincensePlateId,String companyName,Integer pageNum,Integer pageSize,Integer parkId){
        List<Member> members = memberMapper.searchMember(areaName,phoneNumber,lincensePlateId,companyName, pageNum, pageSize, parkId);
        for (Member member : members) {
            Integer lgId = member.getLgId();
            if (null != lgId) {
                JinshiLincenseGroup jinshiLincenseGroup = jinshiLincenseGroupMapper.selectByPrimaryKey(lgId);
                if (null != jinshiLincenseGroup) {
                    member.setLgName(jinshiLincenseGroup.getJsNumber());
                }
            }
        }
        return members;
    }

    @Override
    public List<Member> selectMemberByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize,Integer parkId) {
        return memberMapper.selectMemberByDay(dayBegin,dayEnd,pageNum,pageSize,parkId);
    }

    @Override
    public List<Member> selectMemberByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize,Integer parkId) {
        return memberMapper.selectMemberByWeek(beginDayOfWeek,endDayOfWeek,pageNum,pageSize,parkId);
    }

    @Override
    public List<Member> selectMemberByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize,Integer parkId) {
        return memberMapper.selectMemberByMonth(beginDayOfMonth,endDayOfMonth,pageNum,pageSize,parkId);
    }

    @Override
    public List<Member> selectMemberByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize,Integer parkId) {
        return memberMapper.selectMemberByYear(beginDayOfYear,endDayOfYear,pageNum,pageSize,parkId);
    }

    @Override
    public Integer selectCountMemberByDay(Date dayBegin, Date dayEnd,Integer parkId) {
        return memberMapper.selectCountMemberByDay(dayBegin,dayEnd,parkId);
    }

    @Override
    public Integer selectCountMemberByWeek(Date beginDayOfWeek, Date endDayOfWeek,Integer parkId) {
        return memberMapper.selectCountMemberByWeek(beginDayOfWeek,endDayOfWeek,parkId);
    }

    @Override
    public Integer selectCountMemberByMonth(Date beginDayOfMonth, Date endDayOfMonth,Integer parkId) {
        return memberMapper.selectCountMemberByMonth(beginDayOfMonth,endDayOfMonth,parkId);
    }

    @Override
    public Integer selectCountMemberByYear(Date beginDayOfYear, Date endDayOfYear,Integer parkId) {
        return memberMapper.selectCountMemberByYear(beginDayOfYear,endDayOfYear,parkId);
    }

    @Override
    public List<Member> checkMemberByLinceseForUtil(Member member) {
        return memberMapper.checkMemberByLinceseForUtil(member);
    }

    @Override
    public Integer updateRenew(Integer id, Date joinTime, Date expirationTime, String number) {
        return memberMapper.updateRenew(id,joinTime,expirationTime,number);
    }

    @Override
    public int getMemberTotalRecords(Integer parkId) {
        return memberMapper.getMemberTotalRecords(parkId);
    }

    @Override
    public Integer updateCarNameForTimes(Integer id, String lincensePlateId) {
        return memberMapper.updateCarNameForTimes(id,lincensePlateId);
    }

    @Override
    public Integer updateCarNameForMoney(Integer id, String lincensePlateId, Integer money) {
        return memberMapper.updateCarNameForMoney(id,lincensePlateId,money);
    }

    @Override
    public Integer updateCarNameToZero(Integer id) {
        return memberMapper.updateCarNameToZero(id);
    }

    @Override
    public List<Member> selectByID(String lpLincensePlateIdCar) {
        return memberMapper.selectByID(lpLincensePlateIdCar);
    }

    @Override
    public Integer updateStoreMoneyToZero(Integer id) {
        return memberMapper.updateStoreMoneyToZero(id);
    }

    @Override
    public Integer updateRenewMoney(Integer id, Date joinTime, Date expirationTime, Integer storedMoney) {
        return memberMapper.updateRenewMoney(id,joinTime,expirationTime,storedMoney);
    }

    @Override
    public List<Member> checkISMember(Member member) {
        return memberMapper.checkISMember(member);
    }

    @Override
    public List<Member> checkISAllAreaMember(Member member) {
        return memberMapper.checkISAllAreaMember(member);
    }

    @Override
    public int getAllCount() {
        return memberMapper.getAllCount();
    }
}
