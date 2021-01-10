package com.jinshi.serviceImpl;

import com.jinshi.entity.*;
import com.jinshi.mapper.*;
import com.jinshi.service.LincensePlateService;
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
public class LincensePlateServiceImpl implements LincensePlateService {

    private static Logger logger = Logger.getLogger(LincensePlateServiceImpl.class.getName());

    @Autowired
    private LincensePlateMapper lincensePlateMapper;

    @Autowired
    private JinshiCamerasMapper jinshiCamerasMapper;

    @Autowired
    private JinshiLincenseGroupMapper jinshiLincenseGroupMapper;

    @Autowired
    private LincensePlateHistoryMapper lincensePlateHistoryMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private JinshiAreaMapper jinshiAreaMapper;

    @Autowired
    private JinshiParkingMapper jinshiParkingMapper;

    @Override
    public int deleteByPrimaryKey(Integer lpId) {
        return lincensePlateMapper.deleteByPrimaryKey(lpId);
    }

    @Override
    public int deleteExceptionData(String exceptionDate) {
        return lincensePlateMapper.deleteExceptionData(exceptionDate);
    }

    @Override
    public int insert(LincensePlate record) {
        return lincensePlateMapper.insert(record);
    }

    @Override
    public int insertSelective(LincensePlate record) {
        return lincensePlateMapper.insertSelective(record);
    }

    @Override
    public LincensePlate selectByPrimaryKey(Integer id) {
        return lincensePlateMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(LincensePlate record) {
        return lincensePlateMapper.updateByPrimaryKeySelective(record);
    }
    @Override
    public List<LincensePlate> selectByLincense(LincensePlate lincense){
        return lincensePlateMapper.selectByLincense(lincense);
    }

    @Override
    public int updateByPrimaryKey(LincensePlate record) {
        return lincensePlateMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<LincensePlate> selectLincensePlateAll(){
        return lincensePlateMapper.selectLincensePlateAll();
    }

    @Override
    public int updateByPlate(LincensePlate record){
        return lincensePlateMapper.updateByPlate(record);
    }

    @Override
    public List<LincensePlate> selectByOrderStatAndCname(LincensePlate record){
        return  lincensePlateMapper.selectByOrderStatAndCname(record);
    }

    @Override
    public List<LincensePlate> searchLincense(String keyWord,Integer pageNum,Integer pageSize,String parkId){
        return lincensePlateMapper.searchLincense(keyWord,pageNum,pageSize,parkId);
    }

    @Override
    public List<LincensePlate> selectLincenseForPage(Integer pageNum,Integer pageSize){
        return lincensePlateMapper.selectLincenseForPage(pageNum,pageSize);
    }

    @Override
    public int getLincenseTotalRecords(String areaName){
        return lincensePlateMapper.getLincenseTotalRecords(areaName);
    }

    @Override
    public List<LincensePlate> selectLincenseInRocord(Integer pageNum, Integer pageSize, String lpParkingName, Date dayBegin, Date dayEnd) {
        List<LincensePlate> lincensePlateList = lincensePlateMapper.selectLincenseInRocord(pageNum, pageSize, lpParkingName,dayBegin, dayEnd);
        for (LincensePlate lincensePlate : lincensePlateList) {
            String lpInboundCname = lincensePlate.getLpInboundCname();
            JinshiCameras jinshiCameras = jinshiCamerasMapper.selectByParkIdAndtHandle(Integer.valueOf(lpParkingName), lpInboundCname);
            lincensePlate.setLpInboundCname(jinshiCameras.getJcName());
            Integer lpLgId = lincensePlate.getLpLgId();
            Integer lpLgType = lincensePlate.getLpLgType();
            JinshiArea jinshiArea = jinshiAreaMapper.selectByAreaName(lincensePlate.getLpCarType());
            if (null != lpLgId && 0 != lpLgId) {
                JinshiLincenseGroup jinshiLincenseGroup1 = new JinshiLincenseGroup();
                jinshiLincenseGroup1.setJsNumber(String.valueOf(lpLgId));
                jinshiLincenseGroup1.setJsParkId(Integer.valueOf(lpParkingName));
                jinshiLincenseGroup1.setJsAreaId(jinshiArea.getId());
                JinshiLincenseGroup jinshiLincenseGroup = jinshiLincenseGroupMapper.selectAllByJsNumber(jinshiLincenseGroup1);
                lincensePlate.setLpLgName(jinshiLincenseGroup.getJsGroupName());
            }
            if (null != lpLgType && 0 == lpLgType) {
                lincensePlate.setLpLgTypeName("临时车进场");
            } else if (null != lpLgType && 1 == lpLgType) {
                lincensePlate.setLpLgTypeName("月租车进场");
            }
            String lpLincensePlateIdCar = lincensePlate.getLpLincensePlateIdCar();
            List<Member> members = memberMapper.selectByID(lpLincensePlateIdCar);
            if (members != null && members.size() > 0) {
                Member member = members.get(0);
                lincensePlate.setCompanyName(member.getCompanyName());
                lincensePlate.setDepartmentName(member.getDepartmentName());
            }

        }
        return lincensePlateList;
    }

    @Override
    public int getLincenseInRecords(String lpParkingName, Date dayBegin, Date dayEnd) {
        return lincensePlateMapper.getLincenseInRecords(lpParkingName,dayBegin,dayEnd);
    }

    @Override
    public List<LincensePlate> selectLincenseOutRocord(Integer pageNum, Integer pageSize) {
        return lincensePlateMapper.selectLincenseOutRocord(pageNum,pageSize);
    }

    @Override
    public int getLincenseOutRecords() {
        return lincensePlateMapper.getLincenseOutRecords();
    }

    @Override
    public List<LincensePlate> selectByLincensePlate(String lincensePlateNumber) {
        return lincensePlateMapper.selectByLincensePlate(lincensePlateNumber);
    }

    @Override
    public List<LincensePlatess> selectss() {
        return lincensePlateMapper.selectss();
    }

    @Override
    public List<LincensePlate> selectByWbPlate(Integer pageNum, Integer pageSize,String parkId) {
        return lincensePlateMapper.selectByWbPlate(pageNum,pageSize,parkId);
    }

    @Override
    public Integer insertWxUser(LincensePlate lincensePlate) {
        return lincensePlateMapper.insertWxUser(lincensePlate);
    }

    @Override
    public String selectByPlate(String carNumber) {
        return lincensePlateMapper.selectByPlate(carNumber);
    }

    @Override
    public List<LincensePlate> selectByOpenid(String openid) {
        return lincensePlateMapper.selectByOpenid(openid);
    }

    @Override
    public int updatePlateCleanUp(LincensePlate record) {
        return lincensePlateMapper.updatePlateCleanUp(record);
    }

    @Override
    public List<LincensePlate> selectLincenseByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize) {
        return lincensePlateMapper.selectLincenseByDay(dayBegin,dayEnd,pageNum,pageSize);
    }

    @Override
    public List<LincensePlate> selectLincenseByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize) {
        return lincensePlateMapper.selectLincenseByWeek(beginDayOfWeek,endDayOfWeek,pageNum,pageSize);
    }

    @Override
    public List<LincensePlate> selectLincenseByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize) {
        return lincensePlateMapper.selectLincenseByMonth(beginDayOfMonth,endDayOfMonth,pageNum,pageSize);
    }

    @Override
    public List<LincensePlate> selectLincenseByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize) {
        return lincensePlateMapper.selectLincenseByYear(beginDayOfYear,endDayOfYear,pageNum,pageSize);
    }

    @Override
    public List<LincensePlate> selectLincenseInRocordByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize,String parkId) {
        return lincensePlateMapper.selectLincenseInRocordByDay(dayBegin,dayEnd,pageNum,pageSize,parkId);
    }

    @Override
    public List<LincensePlate> selectLincenseInRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize,String parkId) {
        return lincensePlateMapper.selectLincenseInRocordByWeek(beginDayOfWeek,endDayOfWeek,pageNum,pageSize,parkId);
    }

    @Override
    public List<LincensePlate> selectLincenseInRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize,String parkId) {
        return lincensePlateMapper.selectLincenseInRocordByMonth(beginDayOfMonth,endDayOfMonth,pageNum,pageSize,parkId);
    }

    @Override
    public List<LincensePlate> selectLincenseInRocordByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize,String parkId) {
        return lincensePlateMapper.selectLincenseInRocordByYear(beginDayOfYear,endDayOfYear,pageNum,pageSize,parkId);
    }

    @Override
    public List<LincensePlate> selectLincenseOutRocordByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize) {
        return lincensePlateMapper.selectLincenseOutRocordByDay(dayBegin,dayEnd,pageNum,pageSize);
    }

    @Override
    public List<LincensePlate> selectLincenseOutRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize) {
        return lincensePlateMapper.selectLincenseOutRocordByWeek(beginDayOfWeek,endDayOfWeek,pageNum,pageSize);
    }

    @Override
    public List<LincensePlate> selectLincenseOutRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize) {
        return lincensePlateMapper.selectLincenseOutRocordByMonth(beginDayOfMonth,endDayOfMonth,pageNum,pageSize);
    }

    @Override
    public List<LincensePlate> selectLincenseOutRocordByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize) {
        return lincensePlateMapper.selectLincenseOutRocordByYear(beginDayOfYear,endDayOfYear,pageNum,pageSize);
    }

    @Override
    public CountList selectCountLincensePlateByDay(Date dayBegin, Date dayEnd) {
        return lincensePlateMapper.selectCountLincensePlateByDay(dayBegin,dayEnd);
    }

    @Override
    public CountList selectCountLincensePlateByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateMapper.selectCountLincensePlateByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public CountList selectCountLincensePlateByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateMapper.selectCountLincensePlateByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public CountList selectCountLincensePlateByYear(Date beginDayOfYear, Date endDayOfYear) {
        return lincensePlateMapper.selectCountLincensePlateByYear(beginDayOfYear,endDayOfYear);
    }

    @Override
    public Integer selectCountLincenseInRocordByDay(Date dayBegin, Date dayEnd, String parkId) {
        return lincensePlateMapper.selectCountLincenseInRocordByDay(dayBegin,dayEnd,parkId);
    }

    @Override
    public Integer selectCountLincenseInRocordByDayByKey(Date startTime, Date endTime, String parkIdStr, String keyWord, String companyName, String areaName) {
        return lincensePlateMapper.selectCountLincenseInRocordByDayByKey(startTime,endTime,parkIdStr,keyWord,companyName,areaName);
    }

    @Override
    public Integer updateByPlateTemporary(LincensePlate lincensePlate) {
        return lincensePlateMapper.updateByPlateTemporary(lincensePlate);
    }

    @Override
    public Integer updateByPlateByOrderId(LincensePlate lincensePlate) {
        return lincensePlateMapper.updateByPlateByOrderId(lincensePlate);
    }

    @Override
    public Integer updateByPlateForAdvance(LincensePlate lincense) {
        return lincensePlateMapper.updateByPlateForAdvance(lincense);
    }

    @Override
    public Integer updatePlateCnameToNull(LincensePlate lincensePlate) {
        return lincensePlateMapper.updatePlateCnameToNull(lincensePlate);
    }
    @Override
    public List<LincensePlate> selectBlurryByPlate(String subPlate) {
        return lincensePlateMapper.selectBlurryByPlate(subPlate);
    }

    @Override
    public LincensePlate findByorderId(String orderId) {
        return lincensePlateMapper.findByorderId(orderId);
    }

    @Override
    public Integer selectCountLincenseInRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek,String parkId) {
        return lincensePlateMapper.selectCountLincenseInRocordByWeek(beginDayOfWeek,endDayOfWeek,parkId);
    }

    @Override
    public Integer selectCountLincenseInRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth,String parkId) {
        return lincensePlateMapper.selectCountLincenseInRocordByMonth(beginDayOfMonth,endDayOfMonth,parkId);
    }

    @Override
    public Integer selectCountLincenseInRocordByYear(Date beginDayOfYear, Date endDayOfYear,String parkId) {
        return lincensePlateMapper.selectCountLincenseInRocordByYear(beginDayOfYear,endDayOfYear,parkId);
    }

    @Override
    public Integer selectCountLincenseOutRocordByDay(Date dayBegin, Date dayEnd) {
        return lincensePlateMapper.selectCountLincenseOutRocordByDay(dayBegin,dayEnd);
    }

    @Override
    public Integer selectCountLincenseOutRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateMapper.selectCountLincenseOutRocordByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public Integer selectCountLincenseOutRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateMapper.selectCountLincenseOutRocordByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public Integer selectCountLincenseOutRocordByYear(Date beginDayOfYear, Date endDayOfYear) {
        return lincensePlateMapper.selectCountLincenseOutRocordByYear(beginDayOfYear,endDayOfYear);
    }

    @Override
    public int getLincenseTotalRecordsByDay(Date dayBegin, Date dayEnd) {
        return lincensePlateMapper.getLincenseTotalRecordsByDay(dayBegin,dayEnd);
    }

    @Override
    public int getLincenseTotalRecordsByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateMapper.getLincenseTotalRecordsByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public int getLincenseTotalRecordsByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateMapper.getLincenseTotalRecordsByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public int getLincenseTotalRecordsByYear(Date beginDayOfYear, Date endDayOfYear) {
        return lincensePlateMapper.getLincenseTotalRecordsByYear(beginDayOfYear,endDayOfYear);
    }

    @Override
    public Integer selectInCountByDay(Date dayBegin,Date dayEnd) {
        return lincensePlateMapper.selectInCountByDay(dayBegin,dayEnd);
    }

    @Override
    public Integer selectOutCountByDay(Date dayBegin, Date dayEnd) {
        return lincensePlateMapper.selectOutCountByDay(dayBegin,dayEnd);
    }

    @Override
    public Integer selectPayCountByDay(Date dayBegin, Date dayEnd) {
        return lincensePlateMapper.selectPayCountByDay(dayBegin,dayEnd);
    }

    @Override
    public Integer selectInCountByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateMapper.selectInCountByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public Integer selectOutCountByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateMapper.selectOutCountByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public Integer selectPayCountByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateMapper.selectPayCountByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public Integer selectInCountByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateMapper.selectInCountByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public Integer selectOutCountByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateMapper.selectOutCountByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public Integer selectPayCountByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateMapper.selectPayCountByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public List<Date> selectInPlateByFrontWeek() {
        return lincensePlateMapper.selectInPlateByFrontWeek();
    }

    @Override
    public Integer selectCountPlateNumbers(String parkid) {
        return lincensePlateMapper.selectCountPlateNumbers(parkid);
    }

    @Override
    public List<LincensePlate> selectLincenseInRocordByTime(Integer pageNum, Integer pageSize, String parkIdStr, Date startTime, Date endTime, String keyWord, String companyName, String areaName) {
        List<LincensePlate> lincensePlateList = new ArrayList<>();
        if (companyName != null && companyName != "") {
            lincensePlateList = lincensePlateMapper.selectLincenseInRocordByTimeAndCompanyName(pageNum, pageSize, parkIdStr, startTime, endTime, keyWord,companyName,areaName);
        } else {
            lincensePlateList = lincensePlateMapper.selectLincenseInRocordByTime(pageNum,pageSize,parkIdStr,startTime,endTime,keyWord,areaName);
        }
        for (LincensePlate lincensePlate : lincensePlateList) {
            String lpInboundCname = lincensePlate.getLpInboundCname();
            JinshiCameras jinshiCameras = jinshiCamerasMapper.selectByParkIdAndtHandle(Integer.valueOf(parkIdStr), lpInboundCname);
            lincensePlate.setLpInboundCname(jinshiCameras.getJcName());
            Integer lpLgId = lincensePlate.getLpLgId();
            Integer lpLgType = lincensePlate.getLpLgType();
            JinshiArea jinshiArea = jinshiAreaMapper.selectByAreaName(lincensePlate.getLpCarType());
            if (null != lpLgId && lpLgId!=0) {
                JinshiLincenseGroup jinshiLincenseGroup1 = new JinshiLincenseGroup();
                jinshiLincenseGroup1.setJsNumber(String.valueOf(lpLgId));
                jinshiLincenseGroup1.setJsParkId(Integer.valueOf(parkIdStr));
                jinshiLincenseGroup1.setJsAreaId(jinshiArea.getId());
                JinshiLincenseGroup jinshiLincenseGroup = jinshiLincenseGroupMapper.selectAllByJsNumber(jinshiLincenseGroup1);
                lincensePlate.setLpLgName(jinshiLincenseGroup.getJsGroupName());
            }
            if (null != lpLgType && 0 == lpLgType) {
                lincensePlate.setLpLgTypeName("临时车进场");
            } else if (null != lpLgType && 1 == lpLgType) {
                lincensePlate.setLpLgTypeName("月租车进场");
            }
            String lpLincensePlateIdCar = lincensePlate.getLpLincensePlateIdCar();
            List<Member> members = memberMapper.selectByID(lpLincensePlateIdCar);
            if (members != null && members.size() > 0) {
                Member member = members.get(0);
                lincensePlate.setCompanyName(member.getCompanyName());
                lincensePlate.setDepartmentName(member.getDepartmentName());
            }
        }
        return lincensePlateList;
    }

    @Override
    public int getLincenseInRecordsByTime(String parkIdStr, Date startTime, Date endTime, String keyWord, String companyName, String areaName) {
        return lincensePlateMapper.getLincenseInRecordsByTime(parkIdStr,startTime,endTime,keyWord,companyName,areaName);
    }

    @Override
    public List<LincensePlate> selectByLgId(Integer lgId) {
        return lincensePlateMapper.selectByLgId(lgId);
    }

    @Override
    public int getLincenseTotalRecordsSearch(String parkId, String keyWord) {
        return lincensePlateMapper.getLincenseTotalRecordsSearch(parkId,keyWord);
    }

    @Override
    public Integer selectCountByTimeAndParkId(Date dayStartTime, Date dayEndTime, String parkId) {
        return lincensePlateHistoryMapper.selectCountByTimeAndParkId(dayStartTime,dayEndTime,parkId);
    }

    @Override
    public List<LincensePlate> selectByDateToHistory(String parkId, Date startTime, Date endTime) {
        return lincensePlateMapper.selectByDateToHistory(parkId,startTime,endTime);
    }

    @Override
    public Integer updateType(String lpLincensePlateIdCar) {
        return lincensePlateMapper.updateType(lpLincensePlateIdCar);
    }

    @Override
    public Integer updateTypeZero(String lpLincensePlateIdCar) {
        return lincensePlateMapper.updateTypeZero(lpLincensePlateIdCar);
    }

    @Override
    public LincensePlate selectByIdAndParkId(Integer lpId, String lpParkingName) {
        return lincensePlateMapper.selectByIdAndParkId(lpId,lpParkingName);
    }

    @Override
    public List<LincensePlate> selectByParkIdAndCname(LincensePlate plate) {
        return lincensePlateMapper.selectByParkIdAndCname(plate);
    }

    @Override
    public List<LincensePlate> selectByOrderId(String orderId) {
        return lincensePlateMapper.selectByOrderId(orderId);
    }

    @Override
    public int batchExport(String parkId, String keyWord, String companyName, String areaName, String startTime, String endTime, HttpServletResponse response) throws Exception{
        int result = 1;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HSSFWorkbook wb = new HSSFWorkbook();
        logger.info("在场记录导出开始");
        HSSFSheet sheet = wb.createSheet("在场记录");
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
        row.createCell(2).setCellValue("收费类型");
        row.createCell(3).setCellValue("区域");
        row.createCell(4).setCellValue("入场时间");
        row.createCell(5).setCellValue("车辆类型");
        row.createCell(6).setCellValue("进场口名称");
        row.createCell(7).setCellValue("车牌组");
        row.createCell(8).setCellValue("公司名");
        row.createCell(9).setCellValue("部门名");
        row.createCell(10).setCellValue("进场类型");
        row.createCell(11).setCellValue("订单编号");

        //设置单元格列宽
        sheet.setColumnWidth(0,3000);
        sheet.setColumnWidth(1,4000);
        sheet.setColumnWidth(2,4000);
        sheet.setColumnWidth(3,4000);
        sheet.setColumnWidth(4,6000);
        sheet.setColumnWidth(5,4000);
        sheet.setColumnWidth(6,5000);
        sheet.setColumnWidth(7,3000);
        sheet.setColumnWidth(8,5000);
        sheet.setColumnWidth(9,5000);
        sheet.setColumnWidth(10,5000);
        sheet.setColumnWidth(11,8000);

        Date startTimeJS = null;
        Date endTimeJS = null;
        if (startTime != null && endTime != null) {
            startTimeJS = DateUtils.getDayStartTime(df.parse(startTime));
            endTimeJS = DateUtils.getDayEndTime(df.parse(endTime));
        }
        List<LincensePlate> lincensePlateList = new ArrayList<>();
        if (companyName != null && companyName != "") {
            lincensePlateList = lincensePlateMapper.selectPlateForExport(parkId, startTimeJS, endTimeJS, keyWord,companyName,areaName);
        } else {
            lincensePlateList = lincensePlateMapper.selectPlateForExportTwo(parkId, startTimeJS, endTimeJS, keyWord,areaName);
        }
        for (int i = 0; i < lincensePlateList.size(); i++) {
            HSSFRow rowX = sheet.createRow(i + 1);
            LincensePlate dataX = lincensePlateList.get(i);
            /**
             * BatchData的字段设置为String类型则导出数据为文本类型,免得设置cell格式
             */
            rowX.createCell(0).setCellValue(dataX.getLpId());
            rowX.createCell(1).setCellValue(dataX.getLpLincensePlateIdCar());
            rowX.createCell(2).setCellValue(dataX.getLpOrderState());
            rowX.createCell(3).setCellValue(dataX.getLpCarType());
            rowX.createCell(4).setCellValue(dft.format(dataX.getLpInboundTime()));
            if(dataX.getLpLincenseType() != null  && dataX.getLpLincenseType() != ""){
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
                rowX.createCell(6).setCellValue(dataX.getLpInboundCname());
//            String lpInboundCname = dataX.getLpInboundCname();
//            if (lpInboundCname != null && lpInboundCname != "") {
//                JinshiCameras jinshiCameras = jinshiCamerasMapper.selectByThandle(parkId, lpInboundCname);
//                rowX.createCell(6).setCellValue(jinshiCameras.getJcName());
//            }
//                rowX.createCell(7).setCellValue(dataX.getLpLgId());
            Integer lpLgId = dataX.getLpLgId();
            JinshiArea jinshiArea = jinshiAreaMapper.selectByAreaName(dataX.getLpCarType());
            if (lpLgId == null) {
                rowX.createCell(7).setCellValue("");
            } else {
                JinshiLincenseGroup jinshiLincenseGroup1 = new JinshiLincenseGroup();
                jinshiLincenseGroup1.setJsNumber(String.valueOf(lpLgId));
                jinshiLincenseGroup1.setJsParkId(Integer.valueOf(parkId));
                jinshiLincenseGroup1.setJsAreaId(jinshiArea.getId());
                JinshiLincenseGroup jinshiLincenseGroup = jinshiLincenseGroupMapper.selectAllByJsNumber(jinshiLincenseGroup1);
                if (jinshiLincenseGroup != null) {
                    rowX.createCell(7).setCellValue(jinshiLincenseGroup.getJsGroupName());
                }
            }
            String lpLincensePlateIdCar = dataX.getLpLincensePlateIdCar();
            List<Member> members = memberMapper.selectByID(lpLincensePlateIdCar);
            if (members != null && members.size() > 0) {
                Member member = members.get(0);
                rowX.createCell(8).setCellValue(member.getCompanyName());
                rowX.createCell(9).setCellValue(member.getDepartmentName());
            } else {
                rowX.createCell(8).setCellValue("");
                rowX.createCell(9).setCellValue("");
            }
            Integer lpLgType = dataX.getLpLgType();
            if (null != lpLgType && 0 == lpLgType) {
                rowX.createCell(10).setCellValue("临时车进场");
            } else if (null != lpLgType && 1 == lpLgType) {
                rowX.createCell(10).setCellValue("月租车进场");
            }
            rowX.createCell(11).setCellValue(dataX.getLpOrderId());
        }
        for (int i = 0; i <= lincensePlateList.size(); i++) {
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
            String fileName = jinshiParking.getJpName() + "在场记录" + df.format(new Date());
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
