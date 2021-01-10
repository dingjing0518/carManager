package com.jinshi.mapper;

import com.jinshi.entity.CountList;
import com.jinshi.entity.LincensePlateException;
import com.jinshi.entity.LincensePlatess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface LincensePlateExceptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LincensePlateException record);

    int insertException(String exceptionDate);

    int insertSelective(LincensePlateException record);

    LincensePlateException selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LincensePlateException record);

    int updateByPrimaryKey(LincensePlateException record);

    LincensePlateException selectByLincense(LincensePlateException lincense);

    List<LincensePlateException> selectLincensePlateAll();

    int updateByPlate(LincensePlateException record);

    LincensePlateException selectByOrderStatAndCname(LincensePlateException record);

    List<LincensePlateException> searchLincense(@Param(value = "keyWork") String keyWork, @Param(value = "pageNum") Integer pageNum, @Param(value = "pageSize") Integer pageSize);

    List<LincensePlateException> selectLincenseForPage(Integer pageNum, Integer pageSize);

    int getLincenseTotalRecords();

    List<LincensePlateException> selectLincenseInRocord(Integer pageNum, Integer pageSize);

    int getLincenseInRecords();

    List<LincensePlateException> selectLincenseOutRocord(Integer pageNum, Integer pageSize);

    int getLincenseOutRecords();

    LincensePlateException selectByLincensePlate(String lincensePlateNumber);

    List<LincensePlatess> selectss();

    List<LincensePlateException> selectByWbPlate(Integer pageNum, Integer pageSize);

    Integer insertWxUser(LincensePlateException lincensePlate);

    String selectByPlate(String carNumber);

    List<LincensePlateException> selectByOpenid(String openid);

    int updatePlateCleanUp(LincensePlateException record);

    List<LincensePlateException> selectLincenseByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize);

    List<LincensePlateException> selectLincenseByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize);

    List<LincensePlateException> selectLincenseByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize);

    List<LincensePlateException> selectLincenseByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize);

    List<LincensePlateException> selectLincenseInRocordByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize);

    List<LincensePlateException> selectLincenseInRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize);

    List<LincensePlateException> selectLincenseInRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize);

    List<LincensePlateException> selectLincenseInRocordByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize);

    List<LincensePlateException> selectLincenseOutRocordByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize);

    List<LincensePlateException> selectLincenseOutRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize);

    List<LincensePlateException> selectLincenseOutRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize);

    List<LincensePlateException> selectLincenseOutRocordByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize);

    CountList selectCountLincensePlateByDay(Date dayBegin, Date dayEnd);

    CountList selectCountLincensePlateByWeek(Date beginDayOfWeek, Date endDayOfWeek);

    CountList selectCountLincensePlateByMonth(Date beginDayOfMonth, Date endDayOfMonth);

    CountList selectCountLincensePlateByYear(Date beginDayOfYear, Date endDayOfYear);

    Integer selectCountLincenseInRocordByDay(Date dayBegin, Date dayEnd);

    Integer selectCountLincenseInRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek);

    Integer selectCountLincenseInRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth);

    Integer selectCountLincenseInRocordByYear(Date beginDayOfYear, Date endDayOfYear);

    Integer selectCountLincenseOutRocordByDay(Date dayBegin, Date dayEnd);

    Integer selectCountLincenseOutRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek);

    Integer selectCountLincenseOutRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth);

    Integer selectCountLincenseOutRocordByYear(Date beginDayOfYear, Date endDayOfYear);

    int getLincenseTotalRecordsByDay(Date dayBegin, Date dayEnd);

    int getLincenseTotalRecordsByWeek(Date beginDayOfWeek, Date endDayOfWeek);

    int getLincenseTotalRecordsByMonth(Date beginDayOfMonth, Date endDayOfMonth);

    int getLincenseTotalRecordsByYear(Date beginDayOfYear, Date endDayOfYear);

    Integer selectInCountByDay(Date dayBegin, Date dayEnd);

    Integer selectOutCountByDay(Date dayBegin, Date dayEnd);

    Integer selectPayCountByDay(Date dayBegin, Date dayEnd);

    Integer selectInCountByWeek(Date beginDayOfWeek, Date endDayOfWeek);

    Integer selectOutCountByWeek(Date beginDayOfWeek, Date endDayOfWeek);

    Integer selectPayCountByWeek(Date beginDayOfWeek, Date endDayOfWeek);

    Integer selectInCountByMonth(Date beginDayOfMonth, Date endDayOfMonth);

    Integer selectOutCountByMonth(Date beginDayOfMonth, Date endDayOfMonth);

    Integer selectPayCountByMonth(Date beginDayOfMonth, Date endDayOfMonth);

    List<Date> selectInPlateByFrontWeek();
}