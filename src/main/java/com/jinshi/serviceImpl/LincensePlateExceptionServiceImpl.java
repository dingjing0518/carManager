package com.jinshi.serviceImpl;

import com.jinshi.entity.CountList;
import com.jinshi.entity.LincensePlateException;
import com.jinshi.entity.LincensePlatess;
import com.jinshi.mapper.LincensePlateExceptionMapper;
import com.jinshi.service.LincensePlateExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LincensePlateExceptionServiceImpl implements LincensePlateExceptionService {

    @Autowired
    private LincensePlateExceptionMapper lincensePlateExceptionMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return lincensePlateExceptionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(LincensePlateException record) {
        return lincensePlateExceptionMapper.insert(record);
    }

    @Override
    public int insertException(String exceptionDate) {
        return lincensePlateExceptionMapper.insertException(exceptionDate);
    }

    @Override
    public int insertSelective(LincensePlateException record) {
        return lincensePlateExceptionMapper.insertSelective(record);
    }

    @Override
    public LincensePlateException selectByPrimaryKey(Integer id) {
        return lincensePlateExceptionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(LincensePlateException record) {
        return lincensePlateExceptionMapper.updateByPrimaryKeySelective(record);
    }
    @Override
    public LincensePlateException selectByLincense(LincensePlateException lincense){
        return lincensePlateExceptionMapper.selectByLincense(lincense);
    }

    @Override
    public int updateByPrimaryKey(LincensePlateException record) {
        return lincensePlateExceptionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<LincensePlateException> selectLincensePlateAll(){
        return lincensePlateExceptionMapper.selectLincensePlateAll();
    }

    @Override
    public int updateByPlate(LincensePlateException record){return lincensePlateExceptionMapper.updateByPlate(record);}

    @Override
    public LincensePlateException selectByOrderStatAndCname(LincensePlateException record){return  lincensePlateExceptionMapper.selectByOrderStatAndCname(record);}

    @Override
    public List<LincensePlateException> searchLincense(String keyWork,Integer pageNum,Integer pageSize){
        return lincensePlateExceptionMapper.searchLincense(keyWork,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateException> selectLincenseForPage(Integer pageNum,Integer pageSize){
        return lincensePlateExceptionMapper.selectLincenseForPage(pageNum,pageSize);
    }

    @Override
    public int getLincenseTotalRecords(){
        return lincensePlateExceptionMapper.getLincenseTotalRecords();
    }

    @Override
    public List<LincensePlateException> selectLincenseInRocord(Integer pageNum, Integer pageSize) {
        return lincensePlateExceptionMapper.selectLincenseInRocord(pageNum,pageSize);
    }

    @Override
    public int getLincenseInRecords() {
        return lincensePlateExceptionMapper.getLincenseInRecords();
    }

    @Override
    public List<LincensePlateException> selectLincenseOutRocord(Integer pageNum, Integer pageSize) {
        return lincensePlateExceptionMapper.selectLincenseOutRocord(pageNum,pageSize);
    }

    @Override
    public int getLincenseOutRecords() {
        return lincensePlateExceptionMapper.getLincenseOutRecords();
    }

    @Override
    public LincensePlateException selectByLincensePlate(String lincensePlateNumber) {
        return lincensePlateExceptionMapper.selectByLincensePlate(lincensePlateNumber);
    }

    @Override
    public List<LincensePlatess> selectss() {
        return lincensePlateExceptionMapper.selectss();
    }

    @Override
    public List<LincensePlateException> selectByWbPlate(Integer pageNum, Integer pageSize) {
        return lincensePlateExceptionMapper.selectByWbPlate(pageNum,pageSize);
    }

    @Override
    public Integer insertWxUser(LincensePlateException lincensePlate) {
        return lincensePlateExceptionMapper.insertWxUser(lincensePlate);
    }

    @Override
    public String selectByPlate(String carNumber) {
        return lincensePlateExceptionMapper.selectByPlate(carNumber);
    }

    @Override
    public List<LincensePlateException> selectByOpenid(String openid) {
        return lincensePlateExceptionMapper.selectByOpenid(openid);
    }

    @Override
    public int updatePlateCleanUp(LincensePlateException record) {
        return lincensePlateExceptionMapper.updatePlateCleanUp(record);
    }

    @Override
    public List<LincensePlateException> selectLincenseByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize) {
        return lincensePlateExceptionMapper.selectLincenseByDay(dayBegin,dayEnd,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateException> selectLincenseByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize) {
        return lincensePlateExceptionMapper.selectLincenseByWeek(beginDayOfWeek,endDayOfWeek,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateException> selectLincenseByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize) {
        return lincensePlateExceptionMapper.selectLincenseByMonth(beginDayOfMonth,endDayOfMonth,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateException> selectLincenseByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize) {
        return lincensePlateExceptionMapper.selectLincenseByYear(beginDayOfYear,endDayOfYear,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateException> selectLincenseInRocordByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize) {
        return lincensePlateExceptionMapper.selectLincenseInRocordByDay(dayBegin,dayEnd,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateException> selectLincenseInRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize) {
        return lincensePlateExceptionMapper.selectLincenseInRocordByWeek(beginDayOfWeek,endDayOfWeek,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateException> selectLincenseInRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize) {
        return lincensePlateExceptionMapper.selectLincenseInRocordByMonth(beginDayOfMonth,endDayOfMonth,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateException> selectLincenseInRocordByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize) {
        return lincensePlateExceptionMapper.selectLincenseInRocordByYear(beginDayOfYear,endDayOfYear,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateException> selectLincenseOutRocordByDay(Date dayBegin, Date dayEnd, Integer pageNum, Integer pageSize) {
        return lincensePlateExceptionMapper.selectLincenseOutRocordByDay(dayBegin,dayEnd,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateException> selectLincenseOutRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek, Integer pageNum, Integer pageSize) {
        return lincensePlateExceptionMapper.selectLincenseOutRocordByWeek(beginDayOfWeek,endDayOfWeek,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateException> selectLincenseOutRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth, Integer pageNum, Integer pageSize) {
        return lincensePlateExceptionMapper.selectLincenseOutRocordByMonth(beginDayOfMonth,endDayOfMonth,pageNum,pageSize);
    }

    @Override
    public List<LincensePlateException> selectLincenseOutRocordByYear(Date beginDayOfYear, Date endDayOfYear, Integer pageNum, Integer pageSize) {
        return lincensePlateExceptionMapper.selectLincenseOutRocordByYear(beginDayOfYear,endDayOfYear,pageNum,pageSize);
    }

    @Override
    public CountList selectCountLincensePlateByDay(Date dayBegin, Date dayEnd) {
        return lincensePlateExceptionMapper.selectCountLincensePlateByDay(dayBegin,dayEnd);
    }

    @Override
    public CountList selectCountLincensePlateByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateExceptionMapper.selectCountLincensePlateByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public CountList selectCountLincensePlateByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateExceptionMapper.selectCountLincensePlateByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public CountList selectCountLincensePlateByYear(Date beginDayOfYear, Date endDayOfYear) {
        return lincensePlateExceptionMapper.selectCountLincensePlateByYear(beginDayOfYear,endDayOfYear);
    }

    @Override
    public Integer selectCountLincenseInRocordByDay(Date dayBegin, Date dayEnd) {
        return lincensePlateExceptionMapper.selectCountLincenseInRocordByDay(dayBegin,dayEnd);
    }

    @Override
    public Integer selectCountLincenseInRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateExceptionMapper.selectCountLincenseInRocordByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public Integer selectCountLincenseInRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateExceptionMapper.selectCountLincenseInRocordByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public Integer selectCountLincenseInRocordByYear(Date beginDayOfYear, Date endDayOfYear) {
        return lincensePlateExceptionMapper.selectCountLincenseInRocordByYear(beginDayOfYear,endDayOfYear);
    }

    @Override
    public Integer selectCountLincenseOutRocordByDay(Date dayBegin, Date dayEnd) {
        return lincensePlateExceptionMapper.selectCountLincenseOutRocordByDay(dayBegin,dayEnd);
    }

    @Override
    public Integer selectCountLincenseOutRocordByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateExceptionMapper.selectCountLincenseOutRocordByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public Integer selectCountLincenseOutRocordByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateExceptionMapper.selectCountLincenseOutRocordByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public Integer selectCountLincenseOutRocordByYear(Date beginDayOfYear, Date endDayOfYear) {
        return lincensePlateExceptionMapper.selectCountLincenseOutRocordByYear(beginDayOfYear,endDayOfYear);
    }

    @Override
    public int getLincenseTotalRecordsByDay(Date dayBegin, Date dayEnd) {
        return lincensePlateExceptionMapper.getLincenseTotalRecordsByDay(dayBegin,dayEnd);
    }

    @Override
    public int getLincenseTotalRecordsByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateExceptionMapper.getLincenseTotalRecordsByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public int getLincenseTotalRecordsByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateExceptionMapper.getLincenseTotalRecordsByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public int getLincenseTotalRecordsByYear(Date beginDayOfYear, Date endDayOfYear) {
        return lincensePlateExceptionMapper.getLincenseTotalRecordsByYear(beginDayOfYear,endDayOfYear);
    }

    @Override
    public Integer selectInCountByDay(Date dayBegin,Date dayEnd) {
        return lincensePlateExceptionMapper.selectInCountByDay(dayBegin,dayEnd);
    }

    @Override
    public Integer selectOutCountByDay(Date dayBegin, Date dayEnd) {
        return lincensePlateExceptionMapper.selectOutCountByDay(dayBegin,dayEnd);
    }

    @Override
    public Integer selectPayCountByDay(Date dayBegin, Date dayEnd) {
        return lincensePlateExceptionMapper.selectPayCountByDay(dayBegin,dayEnd);
    }

    @Override
    public Integer selectInCountByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateExceptionMapper.selectInCountByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public Integer selectOutCountByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateExceptionMapper.selectOutCountByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public Integer selectPayCountByWeek(Date beginDayOfWeek, Date endDayOfWeek) {
        return lincensePlateExceptionMapper.selectPayCountByWeek(beginDayOfWeek,endDayOfWeek);
    }

    @Override
    public Integer selectInCountByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateExceptionMapper.selectInCountByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public Integer selectOutCountByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateExceptionMapper.selectOutCountByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public Integer selectPayCountByMonth(Date beginDayOfMonth, Date endDayOfMonth) {
        return lincensePlateExceptionMapper.selectPayCountByMonth(beginDayOfMonth,endDayOfMonth);
    }

    @Override
    public List<Date> selectInPlateByFrontWeek() {
        return lincensePlateExceptionMapper.selectInPlateByFrontWeek();
    }
}
