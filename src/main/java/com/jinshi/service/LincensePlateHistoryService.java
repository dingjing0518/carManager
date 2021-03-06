package com.jinshi.service;

import com.jinshi.entity.CountList;
import com.jinshi.entity.LincensePlate;
import com.jinshi.entity.LincensePlateHistory;
import com.jinshi.entity.LincensePlatess;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public interface LincensePlateHistoryService {


    int deleteByPrimaryKey(Integer id);

    int insert(LincensePlateHistory record);

    int insertSelective(LincensePlateHistory record);

    LincensePlateHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LincensePlateHistory record);

    int updateByPrimaryKey(LincensePlateHistory record);

    LincensePlateHistory selectByLincense(LincensePlateHistory lincense);

    List<LincensePlateHistory> selectLincensePlateAll();

    int updateByPlate(LincensePlateHistory record);

    LincensePlateHistory selectByOrderStatAndCname(LincensePlateHistory record);

    List<LincensePlateHistory> searchLincense(String keyWork, Integer pageNum, Integer pageSize,String parkId);

    List<LincensePlateHistory> selectLincenseForPage(Integer pageNum, Integer pageSize, String parkId);

    List<LincensePlateHistory> selectLincenseForPageFin(Integer pageNum, Integer pageSize, String parkId, Date dayBegin, Date dayEnd);

    int getLincenseTotalRecords(String parkId);

    int getLincenseTotalRecordsFin(String parkId, Date dayBegin, Date dayEnd);

    List<LincensePlateHistory> selectLincenseInRocord(Integer pageNum, Integer pageSize);

    int getLincenseInRecords();

    List<LincensePlateHistory> selectLincenseOutRocord(Integer pageNum, Integer pageSize);

    int getLincenseOutRecords();

    List<LincensePlateHistory> selectByLincensePlate(String lincensePlateNumber);

    List<LincensePlatess> selectss();

    List<LincensePlateHistory> selectByWbPlate(Integer pageNum, Integer pageSize);

    Integer insertWxUser(LincensePlateHistory LincensePlateHistory);

    String selectByPlate(String carNumber);

    List<LincensePlateHistory> selectByOpenid(String openid);

    int updatePlateCleanUp(LincensePlateHistory record);

    List<LincensePlateHistory> selectLincenseByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize);

    List<LincensePlateHistory> selectLincenseByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize);

    List<LincensePlateHistory> selectLincenseByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize);

    List<LincensePlateHistory> selectLincenseByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize);

    List<LincensePlateHistory> selectLincenseInRocordByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize);

    List<LincensePlateHistory> selectLincenseInRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize);

    List<LincensePlateHistory> selectLincenseInRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize);

    List<LincensePlateHistory> selectLincenseInRocordByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize);

    List<LincensePlateHistory> selectLincenseOutRocordByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize,String parkId);

    List<LincensePlateHistory> selectLincenseOutRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize,String parkId);

    List<LincensePlateHistory> selectLincenseOutRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize,String parkId);

    List<LincensePlateHistory> selectLincenseOutRocordByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize,String parkId);

    CountList selectCountLincensePlateByDay(Date dayBegin, Date dayEnd);

    CountList selectCountLincensePlateByWeek(Date beginDayOfWeek, Date endDayOfWeek);

    CountList selectCountLincensePlateByMonth(Date beginDayOfMonth, Date endDayOfMonth);

    CountList selectCountLincensePlateByYear(Date beginDayOfYear, Date endDayOfYear);

    Integer selectCountLincenseInRocordByDay(Date dayBegin, Date dayEnd);

    Integer selectCountLincenseInRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek);

    Integer selectCountLincenseInRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth);

    Integer selectCountLincenseInRocordByYear(Date beginDayOfYear, Date endDayOfYear);

    Integer selectCountLincenseOutRocordByDay(Date dayBegin, Date dayEnd,String parkId);

    Integer selectCountLincenseOutRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek,String parkId);

    Integer selectCountLincenseOutRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth,String parkId);

    Integer selectCountLincenseOutRocordByYear(Date beginDayOfYear, Date endDayOfYear,String parkId);

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

    CountList selectCountLincensePlateByDepartureTime(String parkId, Date dayBegin, Date dayEnd);

    int findByrecordlist(String parkId);

    List<LincensePlateHistory> selectLincenseForPageFinByTime(Integer pageNum, Integer pageSize, String parkId, Date startTime, Date endTime, String keyWord);

    int getLincenseTotalRecordsFinByTime(String parkId, Date startTime, Date endTime, String keyWord);

    CountList selectCountLincensePlateByDepartureTimeByTime(String parkId, Date startTime, Date endTime, String keyWord);

    Integer selectPayFeeCountByDay(Date dayBegin, Date dayEnd);

    Integer selectInCountByDayId(Date dayBegin, Date dayEnd, String parkid);

    Integer selectOutCountByDayId(Date dayBegin, Date dayEnd, String parkid);

    Integer selectPayCountByDayId(Date dayBegin, Date dayEnd, String parkid);

    Integer selectPayFeeCountByDayId(Date dayBegin, Date dayEnd, String parkid);

    Integer selectPayFeeCountByWeek(Date beginDayOfWeek, Date endDayOfWeek);

    Integer selectInCountByWeekId(Date beginDayOfWeek, Date endDayOfWeek, String valueOf);

    Integer selectOutCountByWeekId(Date beginDayOfWeek, Date endDayOfWeek, String valueOf);

    Integer selectPayCountByWeekId(Date beginDayOfWeek, Date endDayOfWeek, String valueOf);

    Integer selectPayFeeCountByWeekId(Date beginDayOfWeek, Date endDayOfWeek, String valueOf);

    Integer selectPayFeeCountByMonth(Date beginDayOfMonth, Date endDayOfMonth);

    Integer selectInCountByMonthId(Date beginDayOfMonth, Date endDayOfMonth, String valueOf);

    Integer selectOutCountByMonthId(Date beginDayOfMonth, Date endDayOfMonth, String valueOf);

    Integer selectPayCountByMonthId(Date beginDayOfMonth, Date endDayOfMonth, String valueOf);

    Integer selectPayFeeCountByMonthId(Date beginDayOfMonth, Date endDayOfMonth, String valueOf);

    List<LincensePlateHistory> selectLincenseForPageAll(Integer pageNum, Integer pageSize, String parkId, Date dayBegin, Date dayEnd);

    int getLincenseTotalRecordsAll(String parkId, Date dayBegin, Date dayEnd);

    int getLincenseTotalRecordsSearch(String parkId, String keyWord);

    CountList selectCountLincensePlateByDepartureTimeSearch(String parkId, String keyWord);

    List<LincensePlate> selectPlateInRocordByType(Integer pageNum, Integer pageSize, String parkIdStr, String lpPaymentType);

    int getLincenseInRecordsByType(String parkIdStr, String lpPaymentType);

    List<LincensePlateHistory> selectHistoryByTimeAndType(Integer pageNum, Integer pageSize, String parkId, Date startTime, Date endTime, String lpPaymentType, String keyWord, String companyName, String areaName);

    int getHistoryByTimeAndType(String parkId, Date startTime, Date endTime, String lpPaymentType, String keyWord, String companyName, String areaName);

    LincensePlateHistory selectByOrderId(String orderId);

    Integer selectInCountByDayAgentId(Date dayBegin, Date dayEnd, String agentId);

    Integer selectOutCountByDayAgentId(Date dayBegin, Date dayEnd, String agentId);

    Integer selectPayCountByDayAgentId(Date dayBegin, Date dayEnd, String agentId);

    Integer selectPayFeeCountByDayAgentId(Date dayBegin, Date dayEnd, String agentId);

    Integer selectInCountByWeekAgentId(Date beginDayOfWeek, Date endDayOfWeek, String agentId);

    Integer selectOutCountByWeekAgentId(Date beginDayOfWeek, Date endDayOfWeek, String agentId);

    Integer selectPayCountByWeekAgentId(Date beginDayOfWeek, Date endDayOfWeek, String agentId);

    Integer selectPayFeeCountByWeekAgentId(Date beginDayOfWeek, Date endDayOfWeek, String agentId);

    Integer selectInCountByMonthAgentId(Date beginDayOfMonth, Date endDayOfMonth, String agentId);

    Integer selectOutCountByMonthAgentId(Date beginDayOfMonth, Date endDayOfMonth, String agentId);

    Integer selectPayCountByMonthAgentId(Date beginDayOfMonth, Date endDayOfMonth, String agentId);

    Integer selectPayFeeCountByMonthAgentId(Date beginDayOfMonth, Date endDayOfMonth, String agentId);

    int batchExport(String parkId, String keyWord, String companyName, String areaName, String startTime, String endTime, String lpPaymentType, HttpServletResponse response) throws Exception;

    List<LincensePlateHistory> findByrecord(String parkId);

    List<LincensePlateHistory> searchrecord(String parkId,Date startTime,Date endTime);
}
