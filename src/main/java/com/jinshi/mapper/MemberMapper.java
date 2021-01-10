package com.jinshi.mapper;

import com.jinshi.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface MemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    Member checkMemberByLincese(Member record);

    List<Member> selectMemberAll(Integer pageNum, Integer pageSize);

    List<Member> selectMemberForPage(Integer pageNum,Integer pageSize,Integer parkId);

    int getMemberTotalRecordsSearch(Integer parkId, String keyWord);

    int deleteByLincense();

    List<Member> searchMember(@Param(value="areaName") String areaName,@Param(value="phoneNumber")String phoneNumber,@Param(value="lincensePlateId")String lincensePlateId,@Param(value="companyName")String companyName, @Param(value="pageNum") Integer pageNum,@Param(value="pageSize") Integer pageSize,@Param(value="parkId") Integer parkId);

    List<Member> selectMemberByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize,Integer parkId);

    List<Member> selectMemberByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize,Integer parkId);

    List<Member> selectMemberByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize,Integer parkId);

    List<Member> selectMemberByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize,Integer parkId);

    Integer selectCountMemberByDay(Date dayBegin, Date dayEnd,Integer parkId);

    Integer selectCountMemberByWeek(Date beginDayOfWeek, Date endDayOfWeek,Integer parkId);

    Integer selectCountMemberByMonth(Date beginDayOfMonth, Date endDayOfMonth,Integer parkId);

    Integer selectCountMemberByYear(Date beginDayOfYear, Date endDayOfYear,Integer parkId);

    List<Member> selectMemberByPlateAndArea(String lincensePlateId,String areaName,Integer parkId);

    List<Member> checkMemberByLinceseForUtil(Member member);

    Integer updateRenew(Integer id, Date joinTime, Date expirationTime, String number);

    List<Member> selectByID(String lpLincensePlateIdCar);

    int getMemberTotalRecords(Integer parkId);

    Integer updateCarNameForTimes(Integer id, String lincensePlateId);

    Integer updateCarNameForMoney(Integer id, String lincensePlateId, Integer money);

    Integer updateCarNameToZero(Integer id);

    List<Member> selectMemberAllByParkId(Integer parkId);

    Integer updateStoreMoneyToZero(Integer id);

    Integer updateRenewMoney(Integer id, Date joinTime, Date expirationTime, Integer storedMoney);

    List<Member> checkISMember(Member member);

    List<Member> checkISAllAreaMember(Member member);

    int getAllCount();

    List<Member> selectMember();
}