package com.jinshi.service;

import com.jinshi.entity.CountList;
import com.jinshi.entity.LincensePlate;
import com.jinshi.entity.LincensePlatess;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public interface LincensePlateService {


    int deleteByPrimaryKey(Integer lpId);

    int deleteExceptionData(String exceptionDate);

    int insert(LincensePlate record);

    int insertSelective(LincensePlate record);

    LincensePlate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LincensePlate record);

    int updateByPrimaryKey(LincensePlate record);

    List<LincensePlate> selectByLincense(LincensePlate lincense);

    List<LincensePlate> selectLincensePlateAll();

    int updateByPlate(LincensePlate record);

    List<LincensePlate> selectByOrderStatAndCname(LincensePlate record);

    List<LincensePlate> searchLincense(String keyWord,Integer pageNum,Integer pageSize,String parkId);

    List<LincensePlate> selectLincenseForPage(Integer pageNum,Integer pageSize);

    int getLincenseTotalRecords(String areaName);

    List<LincensePlate> selectLincenseInRocord(Integer pageNum, Integer pageSize, String lpParkingName, Date dayBegin, Date dayEnd);

    int getLincenseInRecords(String lpParkingName, Date dayBegin, Date dayEnd);

    List<LincensePlate> selectLincenseOutRocord(Integer pageNum, Integer pageSize);

    int getLincenseOutRecords();

    List<LincensePlate> selectByLincensePlate(String lincensePlateNumber);

    List<LincensePlatess> selectss();

    List<LincensePlate> selectByWbPlate(Integer pageNum, Integer pageSize,String parkId);

    Integer insertWxUser(LincensePlate lincensePlate);

    String selectByPlate(String carNumber);

    List<LincensePlate> selectByOpenid(String openid);

    int updatePlateCleanUp(LincensePlate record);

    List<LincensePlate> selectLincenseByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize);

    List<LincensePlate> selectLincenseByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize);

    List<LincensePlate> selectLincenseByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize);

    List<LincensePlate> selectLincenseByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize);

    List<LincensePlate> selectLincenseInRocordByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize,String parkId);

    List<LincensePlate> selectLincenseInRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize,String parkId);

    List<LincensePlate> selectLincenseInRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize,String parkId);

    List<LincensePlate> selectLincenseInRocordByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize,String parkId);

    List<LincensePlate> selectLincenseOutRocordByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize);

    List<LincensePlate> selectLincenseOutRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize);

    List<LincensePlate> selectLincenseOutRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize);

    List<LincensePlate> selectLincenseOutRocordByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize);

    CountList selectCountLincensePlateByDay(Date dayBegin, Date dayEnd);

    CountList selectCountLincensePlateByWeek(Date beginDayOfWeek, Date endDayOfWeek);

    CountList selectCountLincensePlateByMonth(Date beginDayOfMonth, Date endDayOfMonth);

    CountList selectCountLincensePlateByYear(Date beginDayOfYear, Date endDayOfYear);

    Integer selectCountLincenseInRocordByDay(Date dayBegin, Date dayEnd, String parkId);

    Integer selectCountLincenseInRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek,String parkId);

    Integer selectCountLincenseInRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth,String parkId);

    Integer selectCountLincenseInRocordByYear(Date beginDayOfYear, Date endDayOfYear,String parkId);

    Integer selectCountLincenseOutRocordByDay(Date dayBegin, Date dayEnd);

    Integer selectCountLincenseOutRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek);

    Integer selectCountLincenseOutRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth);

    Integer selectCountLincenseOutRocordByYear(Date beginDayOfYear, Date endDayOfYear);

    int getLincenseTotalRecordsByDay(Date dayBegin, Date dayEnd);

    int getLincenseTotalRecordsByWeek(Date beginDayOfWeek, Date endDayOfWeek);

    int getLincenseTotalRecordsByMonth(Date beginDayOfMonth, Date endDayOfMonth);

    int getLincenseTotalRecordsByYear(Date beginDayOfYear, Date endDayOfYear);

    Integer selectInCountByDay(Date dayBegin,Date dayEnd);

    Integer selectOutCountByDay(Date dayBegin, Date dayEnd);

    Integer selectPayCountByDay(Date dayBegin, Date dayEnd);

    Integer selectInCountByWeek(Date beginDayOfWeek, Date endDayOfWeek);

    Integer selectOutCountByWeek(Date beginDayOfWeek, Date endDayOfWeek);

    Integer selectPayCountByWeek(Date beginDayOfWeek, Date endDayOfWeek);

    Integer selectInCountByMonth(Date beginDayOfMonth, Date endDayOfMonth);

    Integer selectOutCountByMonth(Date beginDayOfMonth, Date endDayOfMonth);

    Integer selectPayCountByMonth(Date beginDayOfMonth, Date endDayOfMonth);

    List<Date> selectInPlateByFrontWeek();

    Integer selectCountPlateNumbers(String parkid);

    List<LincensePlate> selectLincenseInRocordByTime(Integer pageNum, Integer pageSize, String parkIdStr, Date startTime, Date endTime, String keyWord, String companyName, String areaName);

    int getLincenseInRecordsByTime(String parkIdStr, Date startTime, Date endTime, String keyWord, String companyName, String areaName);

    List<LincensePlate> selectByLgId(Integer lgId);

    int getLincenseTotalRecordsSearch(String parkId, String keyWord);

    Integer selectCountByTimeAndParkId(Date dayStartTime, Date dayEndTime, String parkId);

    List<LincensePlate> selectByDateToHistory(String parkId, Date startTime, Date endTime);

    Integer updateType(String lpLincensePlateIdCar);

    Integer updateTypeZero(String lpLincensePlateIdCar);

    LincensePlate selectByIdAndParkId(Integer lpId, String lpParkingName);

    List<LincensePlate> selectByParkIdAndCname(LincensePlate plate);

    List<LincensePlate> selectByOrderId(String orderId);

    Integer selectCountLincenseInRocordByDayByKey(Date startTime, Date endTime, String parkIdStr, String keyWord, String companyName, String areaName);

    Integer updateByPlateTemporary(LincensePlate lincensePlate);

    Integer updateByPlateByOrderId(LincensePlate lincensePlate);

    Integer updateByPlateForAdvance(LincensePlate lincense);

    Integer updatePlateCnameToNull(LincensePlate lincensePlate);

    List<LincensePlate> selectBlurryByPlate(String subPlate);

    LincensePlate findByorderId(String orderId);

    int batchExport(String parkId, String keyWord, String companyName, String areaName, String startTime, String endTime, HttpServletResponse response) throws Exception;
}
