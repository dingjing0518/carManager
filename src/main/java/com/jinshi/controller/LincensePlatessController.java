package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.jinshi.entity.CountList;
import com.jinshi.entity.LincensePlateHistory;
import com.jinshi.entity.bo.CounlistBo;
import com.jinshi.service.LincensePlateHistoryService;
import com.jinshi.service.LincensePlatessService;
import com.jinshi.util.DateUtils;
import com.jinshi.util.QianYiCameraUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/lincensePlatess")
@Api(tags = "财务管理")
public class LincensePlatessController {

    private static Logger logger = Logger.getLogger(QianYiCameraUtil.class.getName());
    @Autowired
    private LincensePlatessService lincensePlatessService;

    @Autowired
    private LincensePlateHistoryService lincensePlateHistoryService;

    /**
     * 财务管理页面查询所有
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectss",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "财务管理---查询所有")
    public String selectss(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkId = (String) jsonParam.get("parkId");
        Date dayBegin = DateUtils.getDayBegin();
        Date dayEnd = DateUtils.getDayEnd();
        pageNum = (pageNum-1)*pageSize;
        List<LincensePlateHistory> lincensePlateHistories = lincensePlateHistoryService.selectLincenseForPageFin(pageNum, pageSize, parkId,dayBegin,dayEnd);
        int lincenseTotalRecords = lincensePlateHistoryService.getLincenseTotalRecordsFin(parkId,dayBegin,dayEnd);
        List<CountList> countList = new ArrayList<>();
        countList.add(lincensePlateHistoryService.selectCountLincensePlateByDepartureTime(parkId,dayBegin,dayEnd));
        JSONObject resJo = new JSONObject();
        resJo.put("lincensessData",lincensePlateHistories);
        resJo.put("lincenseCountList",countList);
        resJo.put("lincensessTotalRecords",lincenseTotalRecords);
        return resJo.toJSONString();
    }


    /**
     * 财务管理---每天记录展示
     * @param
     * @return
     */
    @RequestMapping(value = "/findrecord",method = RequestMethod.POST)
    @ApiOperation(value = "财务管理---每天记录展示")
    @ResponseBody
    public PageInfo<CounlistBo> findrecord(@RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
                                           @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize,
                                           @RequestParam("parkId")String parkId) throws ParseException {
       // List<LincensePlateHistory> byrecord = lincensePlateHistoryService.findByrecord(parkId);
        List<CounlistBo> countList = new ArrayList<>();
       /* for (LincensePlateHistory lincensePlateHistory : byrecord) {
            CounlistBo counlistBo = new CounlistBo();
            Date lpDepartureTime = lincensePlateHistory.getLpDepartureTime();
            Date dayBegin = DateUtils.getDayStartTime(lpDepartureTime);
            Date dayEnd = DateUtils.getDayEndTime(lpDepartureTime);
            CountList countList1 = lincensePlateHistoryService.selectCountLincensePlateByDepartureTime(parkId, dayBegin, dayEnd);
            counlistBo.setDateTime(lpDepartureTime);
            counlistBo.setPlate(countList1.getPlate());
            counlistBo.setPrice(countList1.getPrice());
            counlistBo.setRealPrice(countList1.getRealPrice());
            countList.add(counlistBo);
        }*/
        Date date = new Date();

        List<String> timeInterval = DateUtils.getTimeInterval(date);
        for (String s : timeInterval) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = sdf.parse(s);
            CounlistBo counlistBo = new CounlistBo();
            Date dayBegin = DateUtils.getDayStartTime(parse);
            Date dayEnd = DateUtils.getDayEndTime(parse);
            CountList countList1 = lincensePlateHistoryService.selectCountLincensePlateByDepartureTime(parkId, dayBegin, dayEnd);
            counlistBo.setDateTime(parse);
            counlistBo.setPlate(countList1.getPlate());
            counlistBo.setPrice(countList1.getPrice());
            counlistBo.setRealPrice(countList1.getRealPrice());
            countList.add(counlistBo);
        }


        if (countList == null) {
            return null;
        }
        if (countList.size() == 0) {
            return null;
        }

        Integer count = countList.size(); // 记录总数
        Integer pageCount = 0; // 页数
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        int fromIndex = 0; // 开始索引
        int toIndex = 0; // 结束索引

        if (pageNum != pageCount) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }

        List pageList = countList.subList(fromIndex, toIndex);
        //PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(pageList,count);
    }

    /**
     * 财务详情--每天记录搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/searchrecord",method = RequestMethod.POST)
    @ApiOperation(value = "财务详情---每天记录搜索")
    @ResponseBody
    public PageInfo<CounlistBo> searchrecord(@RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
                                   @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize,
                                   @RequestParam("parkId")String parkId, @RequestParam("startTime") String startTime,
                                   @RequestParam("endTime")String endTime) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = simpleDateFormat.parse(startTime);
        Date end = simpleDateFormat.parse(endTime);
        List<LincensePlateHistory> searchrecord = lincensePlateHistoryService.searchrecord(parkId, start, end);
        List<CounlistBo> countList = new ArrayList<>();
        for (LincensePlateHistory lincensePlateHistory : searchrecord) {
            CounlistBo counlistBo = new CounlistBo();
            Date lpDepartureTime = lincensePlateHistory.getLpDepartureTime();
            Date dayBegin = DateUtils.getDayStartTime(lpDepartureTime);
            Date dayEnd = DateUtils.getDayEndTime(lpDepartureTime);
            CountList countList1 = lincensePlateHistoryService.selectCountLincensePlateByDepartureTime(parkId, dayBegin, dayEnd);
            counlistBo.setDateTime(lpDepartureTime);
            counlistBo.setPlate(countList1.getPlate());
            counlistBo.setPrice(countList1.getPrice());
            counlistBo.setRealPrice(countList1.getRealPrice());
            countList.add(counlistBo);
        }

        if (countList == null) {
            return null;
        }
        if (countList.size() == 0) {
            return null;
        }

        Integer count = countList.size(); // 记录总数
        Integer pageCount = 0; // 页数
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        int fromIndex = 0; // 开始索引
        int toIndex = 0; // 结束索引

        if (pageNum != pageCount) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }

        List pageList = countList.subList(fromIndex, toIndex);
        //PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(pageList,count);
    }

    /**
     * 财务管理根据选择的时间段去查询
     * @param jsonParam
     * @return
     */
//    @RequestMapping(value = "/selectByTime",method = RequestMethod.POST)
//    @ResponseBody
//    @CrossOrigin
//    public String selectByTime(@RequestBody JSONObject jsonParam) throws Exception{
//        Integer pageNum = (Integer) jsonParam.get("pageNum");
//        Integer pageSize = (Integer) jsonParam.get("pageSize");
//        String parkId = (String) jsonParam.get("parkId");
//        pageNum = (pageNum-1)*pageSize;
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date startTime = DateUtils.getDayStartTime(format.parse(jsonParam.get("startTime").toString())); //开始时间
//        Date endTime =  DateUtils.getDayEndTime(format.parse(jsonParam.get("endTime").toString())); //结束时间
//        List<LincensePlateHistory> lincensePlateHistories =
//                lincensePlateHistoryService.selectLincenseForPageFinByTime(pageNum, pageSize, parkId,startTime,endTime);
//        int lincenseTotalRecords = lincensePlateHistoryService.getLincenseTotalRecordsFinByTime(parkId,startTime,endTime);
//        List<CountList> countList = new ArrayList<>();
//        countList.add(lincensePlateHistoryService.selectCountLincensePlateByDepartureTimeByTime(parkId,startTime,endTime));
//        JSONObject resJo = new JSONObject();
//        resJo.put("lincensessData",lincensePlateHistories);
//        resJo.put("lincenseCountList",countList);
//        resJo.put("lincensessTotalRecords",lincenseTotalRecords);
//        return resJo.toJSONString();
//    }

    /**
     * 搜索 ---- XXXXXX
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/searchLincense",method = RequestMethod.POST)
    @ResponseBody
    public String searchLincense(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        String parkId = (String) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        List<LincensePlateHistory> res = lincensePlateHistoryService.searchLincense(keyWord, pageNum, pageSize, parkId);
        int memberTotalRecords = lincensePlateHistoryService.getLincenseTotalRecordsSearch(parkId,keyWord);
        List<CountList> countList = new ArrayList<>();
        countList.add(lincensePlateHistoryService.selectCountLincensePlateByDepartureTimeSearch(parkId,keyWord));
        JSONObject resJo = new JSONObject();
        resJo.put("lincensessData",res);
        resJo.put("lincenseCountList",countList);
        resJo.put("lincensessTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }
    /**
     * 财务管理根据选择的时间段去查询 + 搜索
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectByTime",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "财务管理---根据选择的时间段去查询 + 搜索")
    public String selectByTime(@RequestBody JSONObject jsonParam) throws Exception{
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkId = (String) jsonParam.get("parkId");
        String keyWord = (String) jsonParam.get("keyWord");
        pageNum = (pageNum-1)*pageSize;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = DateUtils.getDayStartTime(format.parse(jsonParam.get("startTime").toString())); //开始时间
        Date endTime =  DateUtils.getDayEndTime(format.parse(jsonParam.get("endTime").toString())); //结束时间
        List<LincensePlateHistory> lincensePlateHistories =
                lincensePlateHistoryService.selectLincenseForPageFinByTime(pageNum, pageSize, parkId,startTime,endTime,keyWord);
        int lincenseTotalRecords = lincensePlateHistoryService.getLincenseTotalRecordsFinByTime(parkId,startTime,endTime,keyWord);
        List<CountList> countList = new ArrayList<>();
        countList.add(lincensePlateHistoryService.selectCountLincensePlateByDepartureTimeByTime(parkId,startTime,endTime,keyWord));
        JSONObject resJo = new JSONObject();
        resJo.put("lincensessData",lincensePlateHistories);
        resJo.put("lincenseCountList",countList);
        resJo.put("lincensessTotalRecords",lincenseTotalRecords);
        return resJo.toJSONString();
    }

    /**
     * 财务管理页面按时间段查询（前一天-当天-当月）
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectByPeriod",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "财务管理---按时间段查询（前一天-当天-当月）")
    public String selectByPeriod(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        pageNum = (pageNum-1)*pageSize;
        String parkId = (String) jsonParam.get("parkId");
        Integer period = (Integer) jsonParam.get("period");
        List<LincensePlateHistory> lincensePlateHistorieList = new ArrayList<>();
        Integer lincenseTotalRecords = null;
        List<CountList> countList = new ArrayList<>();
        if (period == 1) {
            //前一天记录
            Date beginDayOfYesterday = DateUtils.getBeginDayOfYesterday();
            Date endDayOfYesterDay = DateUtils.getEndDayOfYesterDay();
            lincensePlateHistorieList = lincensePlateHistoryService.selectLincenseForPageFin(pageNum, pageSize, parkId,beginDayOfYesterday,endDayOfYesterDay);
            lincenseTotalRecords = lincensePlateHistoryService.getLincenseTotalRecordsFin(parkId,beginDayOfYesterday,endDayOfYesterDay);
            countList.add(lincensePlateHistoryService.selectCountLincensePlateByDepartureTime(parkId,beginDayOfYesterday,endDayOfYesterDay));
        } else if (period == 2){
            //当天记录
            Date dayBegin = DateUtils.getDayBegin();
            Date dayEnd = DateUtils.getDayEnd();
            lincensePlateHistorieList = lincensePlateHistoryService.selectLincenseForPageFin(pageNum, pageSize, parkId,dayBegin,dayEnd);
            lincenseTotalRecords = lincensePlateHistoryService.getLincenseTotalRecordsFin(parkId,dayBegin,dayEnd);
            countList.add(lincensePlateHistoryService.selectCountLincensePlateByDepartureTime(parkId,dayBegin,dayEnd));
        } else if (period == 3) {
            //当月记录
            Date beginDayOfMonth = DateUtils.getBeginDayOfMonth();
            Date endDayOfMonth = DateUtils.getEndDayOfMonth();
            lincensePlateHistorieList = lincensePlateHistoryService.selectLincenseForPageFin(pageNum, pageSize, parkId,beginDayOfMonth,endDayOfMonth);
            lincenseTotalRecords = lincensePlateHistoryService.getLincenseTotalRecordsFin(parkId,beginDayOfMonth,endDayOfMonth);
            countList.add(lincensePlateHistoryService.selectCountLincensePlateByDepartureTime(parkId,beginDayOfMonth,endDayOfMonth));
        }
        JSONObject resJo = new JSONObject();
        resJo.put("lincensessData",lincensePlateHistorieList);
        resJo.put("lincenseCountList",countList);
        resJo.put("lincensessTotalRecords",lincenseTotalRecords);
        return resJo.toJSONString();
    }
}
