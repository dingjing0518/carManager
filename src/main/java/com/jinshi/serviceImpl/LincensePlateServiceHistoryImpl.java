package com.jinshi.serviceImpl;

import com.jinshi.entity.*;
import com.jinshi.mapper.*;
import com.jinshi.service.LincensePlateHistoryService;
import com.jinshi.util.DateUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LincensePlateServiceHistoryImpl implements LincensePlateHistoryService {

    private static Logger logger = Logger.getLogger(LincensePlateServiceHistoryImpl.class.getName());

    @Autowired
    private LincensePlateHistoryMapper lincensePlateHistoryMapper;

    @Autowired
    private JinshiCamerasMapper jinshiCamerasMapper;

    @Autowired
    private JinshiLincenseGroupMapper jinshiLincenseGroupMapper;

    @Autowired
    private JinshiAreaMapper jinshiAreaMapper;

    @Autowired
    private JinshiParkingMapper jinshiParkingMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return lincensePlateHistoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(LincensePlateHistory record) {
        return lincensePlateHistoryMapper.insert(record);
    }

    @Override
    public int insertSelective(LincensePlateHistory record) {
        return lincensePlateHistoryMapper.insertSelective(record);
    }

    @Override
    public LincensePlateHistory selectByPrimaryKey(Integer id) {
        return lincensePlateHistoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(LincensePlateHistory record) {
        return lincensePlateHistoryMapper.updateByPrimaryKeySelective(record);
    }
    @Override
    public LincensePlateHistory selectByLincense(LincensePlateHistory lincense){
        return lincensePlateHistoryMapper.selectByLincense(lincense);
    }

    @Override
    public int updateByPrimaryKey(LincensePlateHistory record) {
        return lincensePlateHistoryMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<LincensePlateHistory> selectLincensePlateAll(){
        return lincensePlateHistoryMapper.selectLincensePlateAll();
    }

    @Override
    public int updateByPlate(LincensePlateHistory record){
        return lincensePlateHistoryMapper.updateByPlate(record);
    }

    @Override
    public LincensePlateHistory selectByOrderStatAndCname(LincensePlateHistory record){return  lincensePlateHistoryMapper.selectByOrderStatAndCname(record);}

    @Override
    public List<LincensePlateHistory> searchLincense(String keyWork,Integer pageNum,Integer pageSize,String parkId){
        List<LincensePlateHistory> lincensePlateHistoryList =
                lincensePlateHistoryMapper.searchLincense(keyWork,pageNum,pageSize,parkId);
        for (LincensePlateHistory lincensePlateHistory : lincensePlateHistoryList) {
            String lpLincenseType = lincensePlateHistory.getLpLincenseType();
            if ("1".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("汽油车");
            } else if ("11".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("能源车");
            } else if ("2".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("蓝牌能源车");
            } else if ("3".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("黄牌车");
            } else if ("5".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("特种车");
            }
            JinshiCameras jinshiCameras = jinshiCamerasMapper.selectByThandle(parkId,lincensePlateHistory.getLpInboundCname());
            if (null != jinshiCameras) {
                lincensePlateHistory.setLpInboundCname(jinshiCameras.getJcName());
            }
            JinshiCameras jinshiCameras1 = jinshiCamerasMapper.selectByThandle(parkId,lincensePlateHistory.getLpDepartureCname());
            if (null != jinshiCameras1) {
                lincensePlateHistory.setLpDepartureCname(jinshiCameras1.getJcName());

            }
        }
        return lincensePlateHistoryList;
    }

    @Override
    public List<LincensePlateHistory> findByrecord( String parkId) {

        return lincensePlateHistoryMapper.findByrecord( parkId);
    }

    @Override
    public List<LincensePlateHistory> searchrecord(String parkId, Date startTime, Date endTime) {
        return lincensePlateHistoryMapper.searchrecord(parkId,startTime,endTime);
    }

    @Override
    public List<LincensePlateHistory> selectLincenseForPage(Integer pageNum, Integer pageSize, String parkId){
        List<LincensePlateHistory> lincensePlateHistoryList =
                lincensePlateHistoryMapper.selectLincenseForPage(pageNum, pageSize, parkId);
        for (LincensePlateHistory lincensePlateHistory : lincensePlateHistoryList) {
            String lpLincenseType = lincensePlateHistory.getLpLincenseType();
            if ("1".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("汽油车");
            } else if ("11".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("能源车");
            } else if ("2".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("蓝牌能源车");
            } else if ("3".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("黄牌车");
            } else if ("5".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("特种车");
            }
            JinshiCameras jinshiCameras = jinshiCamerasMapper.selectByThandle(parkId,lincensePlateHistory.getLpInboundCname());
            if (null != jinshiCameras) {
                lincensePlateHistory.setLpInboundCname(jinshiCameras.getJcName());
            }
            JinshiCameras jinshiCameras1 = jinshiCamerasMapper.selectByThandle(parkId,lincensePlateHistory.getLpDepartureCname());
            if (null != jinshiCameras1) {
                lincensePlateHistory.setLpDepartureCname(jinshiCameras1.getJcName());
            }
        }
        return lincensePlateHistoryList;
    }

    @Override
    public List<LincensePlateHistory> selectLincenseForPageFin(Integer pageNum, Integer pageSize, String parkId, Date dayBegin, Date dayEnd){
        return lincensePlateHistoryMapper.selectLincenseForPageFin(pageNum,pageSize,parkId,dayBegin,dayEnd);
    }

    @Override
    public int getLincenseTotalRecords(String parkId){
        return lincensePlateHistoryMapper.getLincenseTotalRecords(parkId);
    }

    @Override
    public int getLincenseTotalRecordsFin(String parkId, Date dayBegin, Date dayEnd){
        return lincensePlateHistoryMapper.getLincenseTotalRecordsFin(parkId,dayBegin,dayEnd);
    }

    @Override
    public List<LincensePlateHistory> selectLincenseInRocord(Integer pageNum, Integer pageSize) {
        return lincensePlateHistoryMapper.selectLincenseInRocord(pageNum,pageSize);
    }

    @Override
    public int getLincenseInRecords() {
        return lincensePlateHistoryMapper.getLincenseInRecords();
    }

    @Override
    public List<LincensePlateHistory> selectLincenseOutRocord(Integer pageNum, Integer pageSize) {
        return lincensePlateHistoryMapper.selectLincenseOutRocord(pageNum,pageSize);
    }

    @Override
    public int getLincenseOutRecords() {
        return lincensePlateHistoryMapper.getLincenseOutRecords();
    }

    @Override
    public List<LincensePlateHistory> selectByLincensePlate(String lincensePlateNumber) {
        return lincensePlateHistoryMapper.selectByLincensePlate(lincensePlateNumber);
    }

    @Override
    public List<LincensePlatess> selectss() {
        return lincensePlateHistoryMapper.selectss();
    }

    @Override
    public List<LincensePlateHistory> selectByWbPlate(Integer pageNum, Integer pageSize) {
        return lincensePlateHistoryMapper.selectByWbPlate(pageNum,pageSize);
    }

    @Override
    public Integer insertWxUser(LincensePlateHistory lincensePlate) {
        return lincensePlateHistoryMapper.insertWxUser(lincensePlate);
    }

    @Override
    public String selectByPlate(String carNumber) {
        return lincensePlateHistoryMapper.selectByPlate(carNumber);
    }

    @Override
    public List<LincensePlateHistory> selectByOpenid(String openid) {
        return lincensePlateHistoryMapper.selectByOpenid(openid);
    }

    @Override
    public int updatePlateCleanUp(LincensePlateHistory record) {
        return lincensePlateHistoryMapper.updatePlateCleanUp(record);
    }

    @Override
    public List<LincensePlateHistory> selectLincenseByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize) {
        return lincensePlateHistoryMapper.selectLincenseByDay(dayBegin,dayEnd,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateHistory> selectLincenseByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize) {
        return lincensePlateHistoryMapper.selectLincenseByWeek(beginDayOfWeek,endDayOfWeek,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateHistory> selectLincenseByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize) {
        return lincensePlateHistoryMapper.selectLincenseByMonth(beginDayOfMonth,endDayOfMonth,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateHistory> selectLincenseByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize) {
        return lincensePlateHistoryMapper.selectLincenseByYear(beginDayOfYear,endDayOfYear,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateHistory> selectLincenseInRocordByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize) {
        return lincensePlateHistoryMapper.selectLincenseInRocordByDay(dayBegin,dayEnd,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateHistory> selectLincenseInRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize) {
        return lincensePlateHistoryMapper.selectLincenseInRocordByWeek(beginDayOfWeek,endDayOfWeek,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateHistory> selectLincenseInRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize) {
        return lincensePlateHistoryMapper.selectLincenseInRocordByMonth(beginDayOfMonth,endDayOfMonth,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateHistory> selectLincenseInRocordByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize) {
        return lincensePlateHistoryMapper.selectLincenseInRocordByYear(beginDayOfYear,endDayOfYear,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateHistory> selectLincenseOutRocordByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize,String parkId) {
        return lincensePlateHistoryMapper.selectLincenseOutRocordByDay(dayBegin,dayEnd,pageNum,pageSize,parkId);
    }

    @Override
    public List<LincensePlateHistory> selectLincenseOutRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize,String parkId) {
        return lincensePlateHistoryMapper.selectLincenseOutRocordByWeek(beginDayOfWeek,endDayOfWeek,pageNum,pageSize,parkId);
    }

    @Override
    public List<LincensePlateHistory> selectLincenseOutRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize,String parkId) {
        return lincensePlateHistoryMapper.selectLincenseOutRocordByMonth(beginDayOfMonth,endDayOfMonth,pageNum,pageSize,parkId);
    }

    @Override
    public List<LincensePlateHistory> selectLincenseOutRocordByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize,String parkId) {
        return lincensePlateHistoryMapper.selectLincenseOutRocordByYear(beginDayOfYear,endDayOfYear,pageNum,pageSize,parkId);
    }

    @Override
    public CountList selectCountLincensePlateByDay(Date dayBegin, Date dayEnd) {
        return lincensePlateHistoryMapper.selectCountLincensePlateByDay(dayBegin,dayEnd);
    }

    @Override
    public CountList selectCountLincensePlateByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateHistoryMapper.selectCountLincensePlateByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public CountList selectCountLincensePlateByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateHistoryMapper.selectCountLincensePlateByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public CountList selectCountLincensePlateByYear(Date beginDayOfYear, Date endDayOfYear) {
        return lincensePlateHistoryMapper.selectCountLincensePlateByYear(beginDayOfYear,endDayOfYear);
    }

    @Override
    public Integer selectCountLincenseInRocordByDay(Date dayBegin, Date dayEnd) {
        return lincensePlateHistoryMapper.selectCountLincenseInRocordByDay(dayBegin,dayEnd);
    }

    @Override
    public Integer selectCountLincenseInRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateHistoryMapper.selectCountLincenseInRocordByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public Integer selectCountLincenseInRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateHistoryMapper.selectCountLincenseInRocordByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public Integer selectCountLincenseInRocordByYear(Date beginDayOfYear, Date endDayOfYear) {
        return lincensePlateHistoryMapper.selectCountLincenseInRocordByYear(beginDayOfYear,endDayOfYear);
    }

    @Override
    public Integer selectCountLincenseOutRocordByDay(Date dayBegin, Date dayEnd,String parkId) {
        return lincensePlateHistoryMapper.selectCountLincenseOutRocordByDay(dayBegin,dayEnd,parkId);
    }

    @Override
    public Integer selectCountLincenseOutRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek,String parkId) {
        return lincensePlateHistoryMapper.selectCountLincenseOutRocordByWeek(beginDayOfWeek,endDayOfWeek,parkId);
    }

    @Override
    public Integer selectCountLincenseOutRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth,String parkId) {
        return lincensePlateHistoryMapper.selectCountLincenseOutRocordByMonth(beginDayOfMonth,endDayOfMonth,parkId);
    }

    @Override
    public Integer selectCountLincenseOutRocordByYear(Date beginDayOfYear, Date endDayOfYear,String parkId) {
        return lincensePlateHistoryMapper.selectCountLincenseOutRocordByYear(beginDayOfYear,endDayOfYear,parkId);
    }

    @Override
    public int getLincenseTotalRecordsByDay(Date dayBegin, Date dayEnd) {
        return lincensePlateHistoryMapper.getLincenseTotalRecordsByDay(dayBegin,dayEnd);
    }

    @Override
    public int getLincenseTotalRecordsByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateHistoryMapper.getLincenseTotalRecordsByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public int getLincenseTotalRecordsByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateHistoryMapper.getLincenseTotalRecordsByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public int getLincenseTotalRecordsByYear(Date beginDayOfYear, Date endDayOfYear) {
        return lincensePlateHistoryMapper.getLincenseTotalRecordsByYear(beginDayOfYear,endDayOfYear);
    }

    @Override
    public Integer selectInCountByDay(Date dayBegin,Date dayEnd) {
        return lincensePlateHistoryMapper.selectInCountByDay(dayBegin,dayEnd);
    }

    @Override
    public Integer selectOutCountByDay(Date dayBegin, Date dayEnd) {
        return lincensePlateHistoryMapper.selectOutCountByDay(dayBegin,dayEnd);
    }

    @Override
    public Integer selectPayCountByDay(Date dayBegin, Date dayEnd) {
        return lincensePlateHistoryMapper.selectPayCountByDay(dayBegin,dayEnd);
    }

    @Override
    public Integer selectInCountByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateHistoryMapper.selectInCountByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public Integer selectOutCountByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateHistoryMapper.selectOutCountByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public Integer selectPayCountByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateHistoryMapper.selectPayCountByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public Integer selectInCountByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateHistoryMapper.selectInCountByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public Integer selectOutCountByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateHistoryMapper.selectOutCountByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public Integer selectPayCountByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateHistoryMapper.selectPayCountByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public List<Date> selectInPlateByFrontWeek() {
        return lincensePlateHistoryMapper.selectInPlateByFrontWeek();
    }

    @Override
    public CountList selectCountLincensePlateByDepartureTime(String parkId, Date dayBegin, Date dayEnd) {
        return lincensePlateHistoryMapper.selectCountLincensePlateByDepartureTime(parkId,dayBegin,dayEnd);
    }

    @Override
    public int findByrecordlist(String parkId) {
        return lincensePlateHistoryMapper.findByrecordlist(parkId);
    }

    @Override
    public List<LincensePlateHistory> selectLincenseForPageFinByTime(Integer pageNum, Integer pageSize, String parkId, Date startTime, Date endTime, String keyWord) {
        List<LincensePlateHistory> lincensePlateHistoryList =
                lincensePlateHistoryMapper.selectLincenseForPageFinByTime(pageNum,pageSize,parkId,startTime,endTime,keyWord);
        for (LincensePlateHistory lincensePlateHistory : lincensePlateHistoryList) {
            String lpLincenseType = lincensePlateHistory.getLpLincenseType();
            if ("1".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("汽油车");
            } else if ("11".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("能源车");
            } else if ("2".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("蓝牌能源车");
            } else if ("3".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("黄牌车");
            } else if ("5".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("特种车");
            }
            JinshiCameras jinshiCameras = jinshiCamerasMapper.selectByThandle(parkId,lincensePlateHistory.getLpInboundCname());
            if (null != jinshiCameras) {
                lincensePlateHistory.setLpInboundCname(jinshiCameras.getJcName());
            }
            JinshiCameras jinshiCameras1 = jinshiCamerasMapper.selectByThandle(parkId,lincensePlateHistory.getLpDepartureCname());
            if (null != jinshiCameras1) {
                lincensePlateHistory.setLpDepartureCname(jinshiCameras1.getJcName());
            }
            Integer lpLgId = lincensePlateHistory.getLpLgId();
            Integer lpLgType = lincensePlateHistory.getLpLgType();
            JinshiArea jinshiArea = jinshiAreaMapper.selectByAreaName(lincensePlateHistory.getLpCarType());
            if (null != lpLgId) {
                JinshiLincenseGroup jinshiLincenseGroup1 = new JinshiLincenseGroup();
                jinshiLincenseGroup1.setJsNumber(String.valueOf(lpLgId));
                jinshiLincenseGroup1.setJsParkId(Integer.valueOf(parkId));
                jinshiLincenseGroup1.setJsAreaId(jinshiArea.getId());
                JinshiLincenseGroup jinshiLincenseGroup = jinshiLincenseGroupMapper.selectAllByJsNumber(jinshiLincenseGroup1);
                lincensePlateHistory.setLpLgName(jinshiLincenseGroup.getJsGroupName());
            }
            if (null != lpLgType && 0 == lpLgType) {
                lincensePlateHistory.setLpLgTypeName("临时车进场");
            } else if (null != lpLgType && 1 == lpLgType) {
                lincensePlateHistory.setLpLgTypeName("月租车进场");
            }
        }
        return lincensePlateHistoryList;
    }

    @Override
    public int getLincenseTotalRecordsFinByTime(String parkId, Date startTime, Date endTime, String keyWord) {
        return lincensePlateHistoryMapper.getLincenseTotalRecordsFinByTime(parkId,startTime,endTime,keyWord);
    }

    @Override
    public CountList selectCountLincensePlateByDepartureTimeByTime(String parkId, Date startTime, Date endTime, String keyWord) {
        return lincensePlateHistoryMapper.selectCountLincensePlateByDepartureTimeByTime(parkId,startTime,endTime,keyWord);
    }

    @Override
    public Integer selectPayFeeCountByDay(Date dayBegin, Date dayEnd) {
        return lincensePlateHistoryMapper.selectPayFeeCountByDay(dayBegin,dayEnd);
    }

    @Override
    public Integer selectInCountByDayId(Date dayBegin, Date dayEnd, String parkid) {
        return lincensePlateHistoryMapper.selectInCountByDayId(dayBegin,dayEnd,parkid);
    }

    @Override
    public Integer selectOutCountByDayId(Date dayBegin, Date dayEnd, String parkid) {
        return lincensePlateHistoryMapper.selectOutCountByDayId(dayBegin,dayEnd,parkid);
    }

    @Override
    public Integer selectPayCountByDayId(Date dayBegin, Date dayEnd, String parkid) {
        return lincensePlateHistoryMapper.selectPayCountByDayId(dayBegin,dayEnd,parkid);
    }

    @Override
    public Integer selectPayFeeCountByDayId(Date dayBegin, Date dayEnd, String parkid) {
        return lincensePlateHistoryMapper.selectPayFeeCountByDayId(dayBegin,dayEnd,parkid);
    }

    @Override
    public Integer selectPayFeeCountByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateHistoryMapper.selectPayFeeCountByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public Integer selectInCountByWeekId(Date beginDayOfWeek, Date endDayOfWeek, String valueOf) {
        return lincensePlateHistoryMapper.selectInCountByWeekId(beginDayOfWeek,endDayOfWeek,valueOf);
    }

    @Override
    public Integer selectOutCountByWeekId(Date beginDayOfWeek, Date endDayOfWeek, String valueOf) {
        return lincensePlateHistoryMapper.selectOutCountByWeekId(beginDayOfWeek,endDayOfWeek,valueOf);
    }

    @Override
    public Integer selectPayCountByWeekId(Date beginDayOfWeek, Date endDayOfWeek, String valueOf) {
        return lincensePlateHistoryMapper.selectPayCountByWeekId(beginDayOfWeek,endDayOfWeek,valueOf);
    }

    @Override
    public Integer selectPayFeeCountByWeekId(Date beginDayOfWeek, Date endDayOfWeek, String valueOf) {
        return lincensePlateHistoryMapper.selectPayFeeCountByWeekId(beginDayOfWeek,endDayOfWeek,valueOf);
    }

    @Override
    public Integer selectPayFeeCountByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateHistoryMapper.selectPayFeeCountByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public Integer selectInCountByMonthId(Date beginDayOfMonth, Date endDayOfMonth, String valueOf) {
        return lincensePlateHistoryMapper.selectInCountByMonthId(beginDayOfMonth,endDayOfMonth,valueOf);
    }

    @Override
    public Integer selectOutCountByMonthId(Date beginDayOfMonth, Date endDayOfMonth, String valueOf) {
        return lincensePlateHistoryMapper.selectOutCountByMonthId(beginDayOfMonth,endDayOfMonth,valueOf);
    }

    @Override
    public Integer selectPayCountByMonthId(Date beginDayOfMonth, Date endDayOfMonth, String valueOf) {
        return lincensePlateHistoryMapper.selectPayCountByMonthId(beginDayOfMonth,endDayOfMonth,valueOf);
    }

    @Override
    public Integer selectPayFeeCountByMonthId(Date beginDayOfMonth, Date endDayOfMonth, String valueOf) {
        return lincensePlateHistoryMapper.selectPayFeeCountByMonthId(beginDayOfMonth,endDayOfMonth,valueOf);
    }

    @Override
    public List<LincensePlateHistory> selectLincenseForPageAll(Integer pageNum, Integer pageSize, String parkId, Date dayBegin, Date dayEnd) {
        List<LincensePlateHistory> lincensePlateHistoryList =
                lincensePlateHistoryMapper.selectLincenseForPageAll(pageNum,pageSize,parkId,dayBegin,dayEnd);
        for (LincensePlateHistory lincensePlateHistory : lincensePlateHistoryList) {
            String lpLincenseType = lincensePlateHistory.getLpLincenseType();
            if ("1".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("汽油车");
            } else if ("11".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("能源车");
            } else if ("2".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("蓝牌能源车");
            } else if ("3".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("黄牌车");
            } else if ("5".equals(lpLincenseType)) {
                lincensePlateHistory.setLpLincenseType("特种车");
            }
            JinshiCameras jinshiCameras = jinshiCamerasMapper.selectByThandle(parkId,lincensePlateHistory.getLpInboundCname());
            if (null != jinshiCameras) {
                lincensePlateHistory.setLpInboundCname(jinshiCameras.getJcName());
            }
            JinshiCameras jinshiCameras1 = jinshiCamerasMapper.selectByThandle(parkId,lincensePlateHistory.getLpDepartureCname());
            if (null != jinshiCameras1) {
                lincensePlateHistory.setLpDepartureCname(jinshiCameras1.getJcName());
            }
            Integer lpLgId = lincensePlateHistory.getLpLgId();
            Integer lpLgType = lincensePlateHistory.getLpLgType();
            JinshiArea jinshiArea = jinshiAreaMapper.selectByAreaName(lincensePlateHistory.getLpCarType());
            if (null != lpLgId && lpLgId!=0) {
                JinshiLincenseGroup jinshiLincenseGroup1 = new JinshiLincenseGroup();
                jinshiLincenseGroup1.setJsNumber(String.valueOf(lpLgId));
                jinshiLincenseGroup1.setJsParkId(Integer.valueOf(parkId));
                jinshiLincenseGroup1.setJsAreaId(jinshiArea.getId());
                JinshiLincenseGroup jinshiLincenseGroup = jinshiLincenseGroupMapper.selectAllByJsNumber(jinshiLincenseGroup1);
                lincensePlateHistory.setLpLgName(jinshiLincenseGroup.getJsGroupName());
            }
            if (null != lpLgType && 0 == lpLgType) {
                lincensePlateHistory.setLpLgTypeName("临时车进场");
            } else if (null != lpLgType && 1 == lpLgType) {
                lincensePlateHistory.setLpLgTypeName("月租车进场");
            }
        }
        return lincensePlateHistoryList;
    }

    @Override
    public int getLincenseTotalRecordsAll(String parkId, Date dayBegin, Date dayEnd) {
        return lincensePlateHistoryMapper.getLincenseTotalRecordsAll(parkId,dayBegin,dayEnd);
    }

    @Override
    public int getLincenseTotalRecordsSearch(String parkId, String keyWord) {
        return lincensePlateHistoryMapper.getLincenseTotalRecordsSearch(parkId,keyWord);
    }

    @Override
    public CountList selectCountLincensePlateByDepartureTimeSearch(String parkId, String keyWord) {
        return lincensePlateHistoryMapper.selectCountLincensePlateByDepartureTimeSearch(parkId,keyWord);
    }

    @Override
    public List<LincensePlate> selectPlateInRocordByType(Integer pageNum, Integer pageSize, String parkIdStr, String lpPaymentType) {
        return lincensePlateHistoryMapper.selectPlateInRocordByType(pageNum,pageSize,parkIdStr,lpPaymentType);
    }

    @Override
    public int getLincenseInRecordsByType(String parkIdStr, String lpPaymentType) {
        return lincensePlateHistoryMapper.getLincenseInRecordsByType(parkIdStr,lpPaymentType);
    }

    @Override
    public List<LincensePlateHistory> selectHistoryByTimeAndType(Integer pageNum, Integer pageSize, String parkId, Date startTime, Date endTime, String lpPaymentType, String keyWord, String companyName, String areaName) {
        List<LincensePlateHistory> list = new ArrayList<>();
        if (lpPaymentType != null) {
            list = lincensePlateHistoryMapper.selectHistoryByTimeAndType(pageNum, pageSize, parkId, startTime, endTime, lpPaymentType,keyWord,companyName,areaName);
        } else {
            list = lincensePlateHistoryMapper.selectHistoryByTimeAndTypeNull(pageNum, pageSize, parkId, startTime, endTime,keyWord,companyName,areaName);
        }
        return list;
    }

    @Override
    public int getHistoryByTimeAndType(String parkId, Date startTime, Date endTime, String lpPaymentType, String keyWord, String companyName, String areaName) {
        Integer i = null;
        if (lpPaymentType != null) {
            i = lincensePlateHistoryMapper.getHistoryByTimeAndType(parkId, startTime, endTime, lpPaymentType,keyWord,companyName,areaName);
        } else {
            i = lincensePlateHistoryMapper.getHistoryByTimeAndTypeNull(parkId, startTime, endTime,keyWord,companyName,areaName);
        }
        return i;
    }

    @Override
    public LincensePlateHistory selectByOrderId(String orderId) {
        return lincensePlateHistoryMapper.selectByOrderId(orderId);
    }

    @Override
    public Integer selectInCountByDayAgentId(Date dayBegin, Date dayEnd, String agentId) {
        return lincensePlateHistoryMapper.selectInCountByDayAgentId(dayBegin,dayEnd,agentId);
    }

    @Override
    public Integer selectOutCountByDayAgentId(Date dayBegin, Date dayEnd, String agentId) {
        return lincensePlateHistoryMapper.selectOutCountByDayAgentId(dayBegin,dayEnd,agentId);
    }

    @Override
    public Integer selectPayCountByDayAgentId(Date dayBegin, Date dayEnd, String agentId) {
        return lincensePlateHistoryMapper.selectPayCountByDayAgentId(dayBegin,dayEnd,agentId);
    }

    @Override
    public Integer selectPayFeeCountByDayAgentId(Date dayBegin, Date dayEnd, String agentId) {
        return lincensePlateHistoryMapper.selectPayFeeCountByDayAgentId(dayBegin,dayEnd,agentId);
    }

    @Override
    public Integer selectInCountByWeekAgentId(Date beginDayOfWeek, Date endDayOfWeek, String agentId) {
        return lincensePlateHistoryMapper.selectInCountByWeekAgentId(beginDayOfWeek,endDayOfWeek,agentId);
    }

    @Override
    public Integer selectOutCountByWeekAgentId(Date beginDayOfWeek, Date endDayOfWeek, String agentId) {
        return lincensePlateHistoryMapper.selectOutCountByWeekAgentId(beginDayOfWeek,endDayOfWeek,agentId);
    }

    @Override
    public Integer selectPayCountByWeekAgentId(Date beginDayOfWeek, Date endDayOfWeek, String agentId) {
        return lincensePlateHistoryMapper.selectPayCountByWeekAgentId(beginDayOfWeek,endDayOfWeek,agentId);
    }

    @Override
    public Integer selectPayFeeCountByWeekAgentId(Date beginDayOfWeek, Date endDayOfWeek, String agentId) {
        return lincensePlateHistoryMapper.selectPayFeeCountByWeekAgentId(beginDayOfWeek,endDayOfWeek,agentId);
    }

    @Override
    public Integer selectInCountByMonthAgentId(Date beginDayOfMonth, Date endDayOfMonth, String agentId) {
        return lincensePlateHistoryMapper.selectInCountByMonthAgentId(beginDayOfMonth,endDayOfMonth,agentId);
    }

    @Override
    public Integer selectOutCountByMonthAgentId(Date beginDayOfMonth, Date endDayOfMonth, String agentId) {
        return lincensePlateHistoryMapper.selectOutCountByMonthAgentId(beginDayOfMonth,endDayOfMonth,agentId);
    }

    @Override
    public Integer selectPayCountByMonthAgentId(Date beginDayOfMonth, Date endDayOfMonth, String agentId) {
        return lincensePlateHistoryMapper.selectPayCountByMonthAgentId(beginDayOfMonth,endDayOfMonth,agentId);
    }

    @Override
    public Integer selectPayFeeCountByMonthAgentId(Date beginDayOfMonth, Date endDayOfMonth, String agentId) {
        return lincensePlateHistoryMapper.selectPayFeeCountByMonthAgentId(beginDayOfMonth,endDayOfMonth,agentId);
    }

    @Override
    public int batchExport(String parkId, String keyWord, String companyName, String areaName, String startTime, String endTime, String lpPaymentType, HttpServletResponse response) throws Exception{
        int result = 1;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HSSFWorkbook wb = new HSSFWorkbook();
        logger.info("历史记录导出开始");
        HSSFSheet sheet = wb.createSheet("历史记录");
        HSSFCellStyle style = wb.createCellStyle();

        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setLocked(true);
        style.setWrapText(true);

        //冻结单元格，参数对应（冻结的列数，冻结的行数，被冻结列第一列的列号，被冻结的最后一行的行号）//（从左往右，从上至下）
        sheet.createFreezePane(1,1,1,1);

        // 左边框的颜色
        style.setLeftBorderColor((short) 8);
        // 右边框的颜色
        style.setRightBorderColor((short) 8);
        // 设置单元格的边框颜色
        style.setBottomBorderColor((short) 8);
        // 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
        style.setFillForegroundColor((short) 8);

        style.setBorderBottom(BorderStyle.THIN); //下边框
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setBorderTop(BorderStyle.THIN);//上边框
        style.setBorderRight(BorderStyle.THIN);//右边框

        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short)10);
        font.setFontName("宋体");
        style.setFont(font);

        /**设置单元格格式为文本格式*/
        HSSFDataFormat format = wb.createDataFormat();
        style.setDataFormat(format.getFormat("@"));

        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("车牌号");
        row.createCell(2).setCellValue("入场时间");
        row.createCell(3).setCellValue("出场时间");
        row.createCell(4).setCellValue("区域");
        row.createCell(5).setCellValue("车牌类型");
        row.createCell(6).setCellValue("停车时长");
        row.createCell(7).setCellValue("实收费用");
        row.createCell(8).setCellValue("应收费用");
        row.createCell(9).setCellValue("订单编号");
        row.createCell(10).setCellValue("订单状态");
        row.createCell(11).setCellValue("出场类型");
        row.createCell(12).setCellValue("入场口名");
        row.createCell(13).setCellValue("出场口名");
        row.createCell(14).setCellValue("付费类型");
        row.createCell(15).setCellValue("车牌组");
        row.createCell(16).setCellValue("进场类型");

        //设置单元格列宽
        sheet.setColumnWidth(0,3000);
        sheet.setColumnWidth(1,4000);
        sheet.setColumnWidth(2,6000);
        sheet.setColumnWidth(3,6000);
        sheet.setColumnWidth(4,4000);
        sheet.setColumnWidth(5,4000);
        sheet.setColumnWidth(6,4000);
        sheet.setColumnWidth(7,3000);
        sheet.setColumnWidth(8,6000);
        sheet.setColumnWidth(9,9000);
        sheet.setColumnWidth(10,5000);
        sheet.setColumnWidth(11,5000);
        sheet.setColumnWidth(12,4000);
        sheet.setColumnWidth(13,4000);
        sheet.setColumnWidth(14,5000);
        sheet.setColumnWidth(15,3000);
        sheet.setColumnWidth(16,5000);

        Date startTimeJS = null;
        Date endTimeJS = null;
        if (startTime != null && endTime != null) {
            startTimeJS = DateUtils.getDayStartTime(df.parse(startTime));
            endTimeJS = DateUtils.getDayEndTime(df.parse(endTime));
        }
        List<LincensePlateHistory> lincensePlateHistoryList = new ArrayList<>();
        if (lpPaymentType != null && lpPaymentType != "") {
            lincensePlateHistoryList = lincensePlateHistoryMapper.selectHistoryForExport(parkId, startTimeJS, endTimeJS, lpPaymentType,keyWord,companyName,areaName);
        } else {
            lincensePlateHistoryList = lincensePlateHistoryMapper.selectHistoryForExportTwo(parkId, startTimeJS, endTimeJS,keyWord,companyName,areaName);
        }
        for (int i = 0; i < lincensePlateHistoryList.size(); i++) {
            HSSFRow rowX = sheet.createRow(i + 1);
            LincensePlateHistory dataX = lincensePlateHistoryList.get(i);
            /**
             * BatchData的字段设置为String类型则导出数据为文本类型,免得设置cell格式
             */
            rowX.createCell(0).setCellValue(dataX.getLpId());
            rowX.createCell(1).setCellValue(dataX.getLpLincensePlateIdCar());
            rowX.createCell(2).setCellValue(dft.format(dataX.getLpInboundTime()));
            rowX.createCell(3).setCellValue(dft.format(dataX.getLpDepartureTime()));
            rowX.createCell(4).setCellValue(dataX.getLpCarType());
            if(dataX.getLpLincenseType() != null ){
                if(dataX.getLpLincenseType().equals("1")){
                    rowX.createCell(5).setCellValue("汽油车");
                }else if(dataX.getLpLincenseType().equals("11")){
                    rowX.createCell(5).setCellValue("能源车");
                }else if(dataX.getLpLincenseType().equals("2")){
                    rowX.createCell(5).setCellValue("蓝牌能源车");
                }else if(dataX.getLpLincenseType().equals("3")){
                    rowX.createCell(5).setCellValue("黄牌车");
                }else if(dataX.getLpLincenseType().equals("5")){
                    rowX.createCell(5).setCellValue("特种车");
                }
            }else{
                rowX.createCell(5).setCellValue("无信息");
            }
            String lpParkingOften = dataX.getLpParkingOften();
            if (lpParkingOften != null && lpParkingOften != "") {
                rowX.createCell(6).setCellValue(dataX.getLpParkingOften() + "分钟");
            } else {
                rowX.createCell(6).setCellValue("");
            }
            rowX.createCell(7).setCellValue(dataX.getLpParkingRealCost());
            rowX.createCell(8).setCellValue(dataX.getLpParkingCost());
            rowX.createCell(9).setCellValue(dataX.getLpOrderId());
            rowX.createCell(10).setCellValue(dataX.getLpOrderState());
            rowX.createCell(11).setCellValue(dataX.getLpPaymentType());
//            String lpInboundCname = dataX.getLpInboundCname();
            rowX.createCell(12).setCellValue(dataX.getLpInboundCname());
//            if (lpInboundCname != null && lpInboundCname != "") {
//                JinshiCameras jinshiCameras1 = jinshiCamerasMapper.selectByThandle(parkId, lpInboundCname);
//                rowX.createCell(12).setCellValue(jinshiCameras1.getJcName());
//            } else {
//                rowX.createCell(12).setCellValue("");
//            }
//            String lpDepartureCname = dataX.getLpDepartureCname();
            rowX.createCell(13).setCellValue(dataX.getLpDepartureCname());
//            if (lpDepartureCname != null && lpDepartureCname != "") {
//                JinshiCameras jinshiCameras2 = jinshiCamerasMapper.selectByThandle(parkId, lpDepartureCname);
//                rowX.createCell(13).setCellValue(jinshiCameras2.getJcName());
//            } else {
//                rowX.createCell(13).setCellValue("");
//            }
            rowX.createCell(14).setCellValue(dataX.getLpPaymentType());
            JinshiArea jinshiArea = jinshiAreaMapper.selectByAreaName(dataX.getLpCarType());
            Integer lpLgId = dataX.getLpLgId();
            if (lpLgId == null) {
                rowX.createCell(15).setCellValue("");
            } else {
                JinshiLincenseGroup jinshiLincenseGroup1 = new JinshiLincenseGroup();
                jinshiLincenseGroup1.setJsNumber(String.valueOf(lpLgId));
                jinshiLincenseGroup1.setJsParkId(Integer.valueOf(parkId));
                jinshiLincenseGroup1.setJsAreaId(jinshiArea.getId());
                JinshiLincenseGroup jinshiLincenseGroup = jinshiLincenseGroupMapper.selectAllByJsNumber(jinshiLincenseGroup1);
                if (jinshiLincenseGroup != null) {
                    rowX.createCell(15).setCellValue(jinshiLincenseGroup.getJsGroupName());
                }
            }
            Integer lpLgType = dataX.getLpLgType();
            if (null != lpLgType && 0 == lpLgType) {
                rowX.createCell(16).setCellValue("临时车进场");
            } else if (null != lpLgType && 1 == lpLgType) {
                rowX.createCell(16).setCellValue("月租车进场");
            }
        }
        for (int i = 0; i <= lincensePlateHistoryList.size(); i++) {
            HSSFRow row00 = sheet.getRow(i);
            for (Cell cell : row00) {
                cell.setCellStyle(style);
            }
        }
        FileOutputStream out = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            JinshiParking jinshiParking = jinshiParkingMapper.selectByJpId(Integer.valueOf(parkId));
            String fileName = jinshiParking.getJpName() + "历史记录" + df.format(new Date());
            out = new FileOutputStream(fileName +".xls");
            wb.write(out);
            File file = new File(fileName+".xls");
            if(file.exists()){
                logger.info("下载开始.....");
                // 设置强制下载不打开
                response.setContentType("application/force-download");
                // 设置文件名
                response.setHeader("Content-disposition",
                        "attachment; filename=" + new String(fileName.getBytes("GB2312"), "8859_1") + ".xls");

                byte[] buffer = new byte[1024];
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                logger.info("开始读取.."+i);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                logger.info("下载成功....." + fileName);

            }else{
                logger.info("下载失败！");
            }
        } catch (Exception e) {
            logger.error("接口内部错误",e);
            e.printStackTrace();
        }finally {
            logger.info("进入finally代码块");
            if(out != null){
                try {
                    out.close();
                    logger.info("关闭out输出流");
                } catch (IOException e) {
                    logger.error("接口内部错误out",e);
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                    logger.info("关闭bis输出流");
                } catch (IOException e) {
                    logger.error("接口内部错误bis",e);
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                    logger.info("关闭fis输出流");
                } catch (IOException e) {
                    logger.error("接口内部错误fis",e);
                    e.printStackTrace();
                }
            }
        }
        return result;
    }




}
