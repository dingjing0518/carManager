package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.CountList;
import com.jinshi.entity.LincensePlate;
import com.jinshi.entity.LincensePlateBo;
import com.jinshi.entity.LincensePlateException;
import com.jinshi.service.LincensePlateExceptionService;
import com.jinshi.service.LincensePlateService;
import com.jinshi.util.DateUtils;
import com.jinshi.util.QianYiCameraUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@CrossOrigin
@RequestMapping("/lincensePlateException")
public class LincensePlateExceptionController {

    private static Logger logger = LogManager.getLogger(LincensePlateExceptionController.class.getName());
    @Autowired
    private LincensePlateExceptionService lincensePlateExceptionService;

    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
    @ResponseBody
    public Integer deleteByPrimaryKey(@RequestBody JSONObject jsonParam) {
        System.out.println(jsonParam.toString());
        LincensePlate lincensePlate = JSONObject.parseObject(jsonParam.toJSONString(), LincensePlate.class);
        return lincensePlateExceptionService.deleteByPrimaryKey(lincensePlate.getLpId());
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public Integer insert(@RequestBody JSONObject jsonParam) {
        System.out.println(jsonParam.toJSONString());
        LincensePlateException lincensePlate = JSONObject.parseObject(jsonParam.toJSONString(), LincensePlateException.class);
        return lincensePlateExceptionService.insert(lincensePlate);
    }

    @RequestMapping(value = "/insertException",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public Integer insertException(@RequestBody JSONObject jsonParam) {
        String exceptionDate = (String) jsonParam.get("exceptionDate");
        return lincensePlateExceptionService.insertException(exceptionDate);
    }

    @RequestMapping(value = "/selectLincenseAll",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String selectLincenseAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        pageNum = (pageNum-1)*pageSize;
        List<LincensePlateException> res = lincensePlateExceptionService.selectLincenseForPage(pageNum,pageSize);
        int memberTotalRecords = lincensePlateExceptionService.getLincenseTotalRecords();
        JSONObject resJo = new JSONObject();
        resJo.put("lincenseData",res);
        resJo.put("lincenseTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }

    @CrossOrigin
    @RequestMapping(value = "/searchLincense",method = RequestMethod.POST)
    @ResponseBody
    public String searchLincense(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String keyWord = (String) jsonParam.get("keyWord");
        pageNum = (pageNum-1)*pageSize;
        List<LincensePlateException> res = lincensePlateExceptionService.searchLincense(keyWord,pageNum,pageSize);
        int memberTotalRecords = lincensePlateExceptionService.getLincenseTotalRecords();
        JSONObject resJo = new JSONObject();
        resJo.put("lincenseData",res);
        resJo.put("lincenseTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }

    @RequestMapping(value = "/selectByLincese",method = RequestMethod.POST)
    @ResponseBody
    public LincensePlateException selectByLincese(@RequestBody JSONObject jsonParam){
        System.out.println(jsonParam.toJSONString());
        String lincensePlateIdCar = (String) jsonParam.get("lpLincensePlateIdCar");
        logger.info("lincese:  "+lincensePlateIdCar);
        LincensePlateException lincensePlate = new LincensePlateException();
        lincensePlate.setLpLincensePlateIdCar(lincensePlateIdCar);
        LincensePlateException lincensePlate1 = new LincensePlateException();
        try {
             lincensePlate1 = lincensePlateExceptionService.selectByLincense(lincensePlate);
        }catch (Exception e){
            e.printStackTrace();
            return lincensePlate1;
        }
        logger.info(lincensePlate1.toString());
        return lincensePlate1;
    }

    @RequestMapping(value = "/updateByPlate",method = RequestMethod.POST)
    @ResponseBody
    public Integer updateByPlate(@RequestBody JSONObject jsonParam){
        System.out.println(jsonParam.toJSONString());
        LincensePlateException lincensePlate = JSONObject.parseObject(jsonParam.toJSONString(), LincensePlateException.class);
        return lincensePlateExceptionService.updateByPlate(lincensePlate);
    }

    @RequestMapping(value = "/updatePlateCleanUp",method = RequestMethod.POST)
    @ResponseBody
    public Integer updatePlateCleanUp(@RequestBody JSONObject jsonParam){
        System.out.println(jsonParam.toJSONString());
        LincensePlateException lincensePlate = JSONObject.parseObject(jsonParam.toJSONString(), LincensePlateException.class);
        return lincensePlateExceptionService.updatePlateCleanUp(lincensePlate);
    }

    @RequestMapping("/insertSelective")
    public Integer insertSelective(LincensePlateException record) {
        return lincensePlateExceptionService.insertSelective(record);
    }

    @RequestMapping("/selectByPrimaryKey")
    public LincensePlateException selectByPrimaryKey(Integer id) {
        return lincensePlateExceptionService.selectByPrimaryKey(id);
    }



    @RequestMapping("/updateByPrimaryKeySelective")
    public Integer updateByPrimaryKeySelective(LincensePlateException record) {

        return lincensePlateExceptionService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("/updateByPrimaryKey")
    public Integer updateByPrimaryKey(LincensePlateException record) {

        return lincensePlateExceptionService.updateByPrimaryKey(record);
    }

    @RequestMapping(value = "/selectLincenseInRocord",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String selectLincenseInRocord(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        pageNum = (pageNum-1)*pageSize;
        List<LincensePlateException> res = lincensePlateExceptionService.selectLincenseInRocord(pageNum,pageSize);
        int memberTotalRecords = lincensePlateExceptionService.getLincenseInRecords();
        JSONObject resJo = new JSONObject();
        resJo.put("lincenseData",res);
        resJo.put("lincenseTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }

    @RequestMapping(value = "/selectLincenseOutRocord",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String selectLincenseOutRocord(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        pageNum = (pageNum-1)*pageSize;
        List<LincensePlateException> res = lincensePlateExceptionService.selectLincenseOutRocord(pageNum,pageSize);
        int memberTotalRecords = lincensePlateExceptionService.getLincenseOutRecords();
        JSONObject resJo = new JSONObject();
        resJo.put("lincenseData",res);
        resJo.put("lincenseTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }

    @RequestMapping(value = "/selectss",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String selectss(@RequestBody JSONObject jsonParam){

        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        pageNum = (pageNum-1)*pageSize;
        List<LincensePlateException> res = lincensePlateExceptionService.selectLincenseForPage(pageNum,pageSize);
        int memberTotalRecords = lincensePlateExceptionService.getLincenseTotalRecords();
        JSONObject resJo = new JSONObject();
        resJo.put("lincensessData",res);
        resJo.put("lincensessTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }

    /**
     * 财务管理页面按条件查询
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectLincenseByCalendar",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String selectLincenseByCalendar(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        pageNum = (pageNum-1)*pageSize;
        String type = (String) jsonParam.get("type");
        JSONObject resJo = new JSONObject();
        List<LincensePlateException> res = new ArrayList<>();
        int lincenseTotalRecords = 0;
        List<CountList> countList = new ArrayList<>();
        if ("1".equals(type)) {
            Date dayBegin = DateUtils.getDayBegin();
            Date dayEnd = DateUtils.getDayEnd();
            res = lincensePlateExceptionService.selectLincenseByDay(dayBegin,dayEnd,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateExceptionService.getLincenseTotalRecordsByDay(dayBegin,dayEnd);
            countList.add(lincensePlateExceptionService.selectCountLincensePlateByDay(dayBegin,dayEnd));
        } else if ("2".equals(type)) {
            Date beginDayOfWeek = DateUtils.getBeginDayOfWeek();
            Date endDayOfWeek = DateUtils.getEndDayOfWeek();
            res = lincensePlateExceptionService.selectLincenseByWeek(beginDayOfWeek,endDayOfWeek,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateExceptionService.getLincenseTotalRecordsByWeek(beginDayOfWeek,endDayOfWeek);
            countList.add(lincensePlateExceptionService.selectCountLincensePlateByWeek(beginDayOfWeek,endDayOfWeek));
        } else if ("3".equals(type)) {
            Date beginDayOfMonth = DateUtils.getBeginDayOfMonth();
            Date endDayOfMonth = DateUtils.getEndDayOfMonth();
            res = lincensePlateExceptionService.selectLincenseByMonth(beginDayOfMonth,endDayOfMonth,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateExceptionService.getLincenseTotalRecordsByMonth(beginDayOfMonth,endDayOfMonth);
            countList.add(lincensePlateExceptionService.selectCountLincensePlateByMonth(beginDayOfMonth,endDayOfMonth));
        } else if ("4".equals(type)) {
            Date beginDayOfYear = DateUtils.getBeginDayOfYear();
            Date endDayOfYear = DateUtils.getEndDayOfYear();
            res = lincensePlateExceptionService.selectLincenseByYear(beginDayOfYear,endDayOfYear,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateExceptionService.getLincenseTotalRecordsByYear(beginDayOfYear,endDayOfYear);
            countList.add(lincensePlateExceptionService.selectCountLincensePlateByYear(beginDayOfYear,endDayOfYear));
        }
        resJo.put("lincenseData",res);
        resJo.put("lincenseCountList",countList);
        resJo.put("lincenseTotalRecords",lincenseTotalRecords);
        return resJo.toJSONString();
    }

    /**
     * 进场记录页面按条件查询
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectLincenseInRocordByCalendar",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String selectLincenseInRocordByCalendar(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        pageNum = (pageNum-1)*pageSize;
        String type = (String) jsonParam.get("type");
        List<LincensePlateException> res = new ArrayList<>();
        int lincenseTotalRecords = 0;
        List<Integer> countList = new ArrayList<>();
        if ("1".equals(type)) {
            Date dayBegin = DateUtils.getDayBegin();
            Date dayEnd = DateUtils.getDayEnd();
            res = lincensePlateExceptionService.selectLincenseInRocordByDay(dayBegin,dayEnd,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateExceptionService.getLincenseTotalRecords();
            countList.add(lincensePlateExceptionService.selectCountLincenseInRocordByDay(dayBegin,dayEnd));
        } else if ("2".equals(type)) {
            Date beginDayOfWeek = DateUtils.getBeginDayOfWeek();
            Date endDayOfWeek = DateUtils.getEndDayOfWeek();
            res = lincensePlateExceptionService.selectLincenseInRocordByWeek(beginDayOfWeek,endDayOfWeek,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateExceptionService.getLincenseTotalRecords();
            countList.add(lincensePlateExceptionService.selectCountLincenseInRocordByWeek(beginDayOfWeek,endDayOfWeek));
        } else if ("3".equals(type)) {
            Date beginDayOfMonth = DateUtils.getBeginDayOfMonth();
            Date endDayOfMonth = DateUtils.getEndDayOfMonth();
            res = lincensePlateExceptionService.selectLincenseInRocordByMonth(beginDayOfMonth,endDayOfMonth,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateExceptionService.getLincenseTotalRecords();
            countList.add(lincensePlateExceptionService.selectCountLincenseInRocordByMonth(beginDayOfMonth,endDayOfMonth));
        } else if ("4".equals(type)) {
            Date beginDayOfYear = DateUtils.getBeginDayOfYear();
            Date endDayOfYear = DateUtils.getEndDayOfYear();
            res = lincensePlateExceptionService.selectLincenseInRocordByYear(beginDayOfYear,endDayOfYear,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateExceptionService.getLincenseTotalRecords();
            countList.add(lincensePlateExceptionService.selectCountLincenseInRocordByYear(beginDayOfYear,endDayOfYear));
        }
        JSONObject resJo = new JSONObject();
        resJo.put("lincenseData",res);
        resJo.put("lincenseCountList",countList);
        resJo.put("lincenseTotalRecords",lincenseTotalRecords);
        return resJo.toJSONString();
    }

    /**
     * 出场记录按条件查询
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectLincenseOutRocordByCalendar",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String selectLincenseOutRocordByCalendar(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        pageNum = (pageNum-1)*pageSize;
        String type = (String) jsonParam.get("type");
        List<LincensePlateException> res = new ArrayList<>();
        int lincenseTotalRecords = 0;
        List<Integer> countList = new ArrayList<>();
        if ("1".equals(type)) {
            Date dayBegin = DateUtils.getDayBegin();
            Date dayEnd = DateUtils.getDayEnd();
            res = lincensePlateExceptionService.selectLincenseOutRocordByDay(dayBegin,dayEnd,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateExceptionService.getLincenseTotalRecords();
            countList.add(lincensePlateExceptionService.selectCountLincenseOutRocordByDay(dayBegin,dayEnd));
        } else if ("2".equals(type)) {
            Date beginDayOfWeek = DateUtils.getBeginDayOfWeek();
            Date endDayOfWeek = DateUtils.getEndDayOfWeek();
            res = lincensePlateExceptionService.selectLincenseOutRocordByWeek(beginDayOfWeek,endDayOfWeek,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateExceptionService.getLincenseTotalRecords();
            countList.add(lincensePlateExceptionService.selectCountLincenseOutRocordByWeek(beginDayOfWeek,endDayOfWeek));
        } else if ("3".equals(type)) {
            Date beginDayOfMonth = DateUtils.getBeginDayOfMonth();
            Date endDayOfMonth = DateUtils.getEndDayOfMonth();
            res = lincensePlateExceptionService.selectLincenseOutRocordByMonth(beginDayOfMonth,endDayOfMonth,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateExceptionService.getLincenseTotalRecords();
            countList.add(lincensePlateExceptionService.selectCountLincenseOutRocordByMonth(beginDayOfMonth,endDayOfMonth));
        } else if ("4".equals(type)) {
            Date beginDayOfYear = DateUtils.getBeginDayOfYear();
            Date endDayOfYear = DateUtils.getEndDayOfYear();
            res = lincensePlateExceptionService.selectLincenseOutRocordByYear(beginDayOfYear,endDayOfYear,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateExceptionService.getLincenseTotalRecords();
            countList.add(lincensePlateExceptionService.selectCountLincenseOutRocordByYear(beginDayOfYear,endDayOfYear));
        }
        JSONObject resJo = new JSONObject();
        resJo.put("lincenseData",res);
        resJo.put("lincenseCountList",countList);
        resJo.put("lincenseTotalRecords",lincenseTotalRecords);
        return resJo.toJSONString();
    }

    /**
     * 总览页面查询每天进出付费车辆数
     * @return
     */
    @RequestMapping(value = "/selectPlateByDay",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public String selectPlateByDay(){

        Date dayBegin = DateUtils.getDayBegin();
        Date dayEnd = DateUtils.getDayEnd();
        //进车辆数
        Integer inCountByDay = lincensePlateExceptionService.selectInCountByDay(dayBegin,dayEnd);
        //出车辆数
        Integer outCountByDay = lincensePlateExceptionService.selectOutCountByDay(dayBegin,dayEnd);
        //付费车辆
        Integer payCountByDay = lincensePlateExceptionService.selectPayCountByDay(dayBegin,dayEnd);

        JSONObject resJo = new JSONObject();
        resJo.put("outInCount",inCountByDay + outCountByDay);
        resJo.put("outCount",outCountByDay);
        resJo.put("payCount",payCountByDay);
        return resJo.toJSONString();
    }

    /**
     * 总览页面查询每周进出付费车辆数
     * @return
     */
    @RequestMapping(value = "/selectPlateByWeek",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public String selectPlateByWeek(){

        Date beginDayOfWeek = DateUtils.getBeginDayOfWeek();
        Date endDayOfWeek = DateUtils.getEndDayOfWeek();
        //进车辆数
        Integer inCountByWeek = lincensePlateExceptionService.selectInCountByWeek(beginDayOfWeek,endDayOfWeek);
        //出车辆数
        Integer outCountByWeek = lincensePlateExceptionService.selectOutCountByWeek(beginDayOfWeek,endDayOfWeek);
        //付费车辆
        Integer payCountByWeek = lincensePlateExceptionService.selectPayCountByWeek(beginDayOfWeek,endDayOfWeek);

        JSONObject resJo = new JSONObject();
        resJo.put("outInCount",inCountByWeek + outCountByWeek);
        resJo.put("outCount",outCountByWeek);
        resJo.put("payCount",payCountByWeek);
        return resJo.toJSONString();
    }

    /**
     * 总览页面查询每月进出付费车辆数
     * @return
     */
    @RequestMapping(value = "/selectPlateByMonth",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public String selectPlateByMonth(){

        Date beginDayOfMonth = DateUtils.getBeginDayOfMonth();
        Date endDayOfMonth = DateUtils.getEndDayOfMonth();
        //进车辆数
        Integer inCountByMonth = lincensePlateExceptionService.selectInCountByMonth(beginDayOfMonth,endDayOfMonth);
        //出车辆数
        Integer outCountByMonth = lincensePlateExceptionService.selectOutCountByMonth(beginDayOfMonth,endDayOfMonth);
        //付费车辆
        Integer payCountByMonth = lincensePlateExceptionService.selectPayCountByMonth(beginDayOfMonth,endDayOfMonth);

        JSONObject resJo = new JSONObject();
        resJo.put("outInCount",inCountByMonth + outCountByMonth);
        resJo.put("outCount",outCountByMonth);
        resJo.put("payCount",payCountByMonth);
        return resJo.toJSONString();
    }


    /**
     * 总览页面进场车辆走势图
     * @return
     */
    @RequestMapping(value = "/selectPlateVehicleChart",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JSONObject selectPlateVehicleChart(){

        List<String> stringList = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        //最近7天的进场车辆数
        List<Date> lincensePlateList = lincensePlateExceptionService.selectInPlateByFrontWeek();
        JSONObject resJo = new JSONObject();
        for (Date date : lincensePlateList) {
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            String formatDate = format1.format(date);
            stringList.add(formatDate);
        }
        Map<LincensePlateBo,Map<String, Integer>> lincensePlateBoStringMap = new HashMap<>();
        for (String s : stringList) {
            int frequency = Collections.frequency(stringList, s);
            map.put(s,frequency);
            LincensePlateBo lincensePlateBo = new LincensePlateBo();
            lincensePlateBo.setFormatDate(s);
            lincensePlateBo.setFrequency(frequency);
            lincensePlateBoStringMap.put(lincensePlateBo,map);
        }
        resJo.put("LincensePlateBo",lincensePlateBoStringMap);
        return resJo;
    }

    /**
     * 总览页面进场车辆走势图
     * @return
     */
    /*@RequestMapping(value = "/selectPlateVehicleChart",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public String selectPlateVehicleChart(){

        List<String> stringList = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        //最近7天的进场车辆数
        List<Date> lincensePlateList = lincensePlateService.selectInPlateByFrontWeek();
        JSONObject resJo = new JSONObject();
        for (Date date : lincensePlateList) {
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            String formatDate = format1.format(date);
            stringList.add(formatDate);
        }
        for (String s : stringList) {
            int frequency = Collections.frequency(stringList, s);
            map.put(s,frequency);
            resJo.put("LincensePlateList",map);
        }
        return resJo.toString();
    }*/
}
