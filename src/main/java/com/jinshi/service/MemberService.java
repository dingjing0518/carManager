package com.jinshi.service;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.Member;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface MemberService {
    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Member record);

    JSONObject updateByPrimaryKey(Member record) ;

    Member checkMemberByLincese(Member record);

    List<Member> selectMemberAll(Integer pageNum, Integer pageSize);

    List<Member> selectMemberForPage(Integer pageNum,Integer pageSize,Integer parkId);

    int deleteByLincense(String lincense);

    int getMemberTotalRecordsSearch(Integer parkId, String keyWord);

    List<Member> searchMember(String areaName,String phoneNumber,String lincensePlateId,String companyName,Integer pageNum,Integer pageSize,Integer parkId);

    List<Member> selectMemberByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize,Integer parkId);

    List<Member> selectMemberByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize,Integer parkId);

    List<Member> selectMemberByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize ,Integer parkId);

    List<Member> selectMemberByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize,Integer parkId);

    Integer selectCountMemberByDay(Date dayBegin, Date dayEnd,Integer parkId);

    Integer selectCountMemberByWeek(Date beginDayOfWeek, Date endDayOfWeek,Integer parkId);

    Integer selectCountMemberByMonth(Date beginDayOfMonth, Date endDayOfMonth,Integer parkId);

    Integer selectCountMemberByYear(Date beginDayOfYear, Date endDayOfYear,Integer parkId);

    List<Member> checkMemberByLinceseForUtil(Member member);

    Integer updateRenew(Integer id, Date joinTime, Date expirationTime, String number);

    int getMemberTotalRecords(Integer parkId);

    Integer updateCarNameForTimes(Integer id, String lincensePlateId);

    Integer updateCarNameForMoney(Integer id, String lincensePlateId, Integer money);

    Integer updateCarNameToZero(Integer id);

    List<Member> selectByID(String lpLincensePlateIdCar);

    Integer updateStoreMoneyToZero(Integer id);

    Integer updateRenewMoney(Integer id, Date joinTime, Date expirationTime, Integer storedMoney);

    List<Member> checkISMember(Member member);

    List<Member> checkISAllAreaMember(Member member);

    int getAllCount();
}
