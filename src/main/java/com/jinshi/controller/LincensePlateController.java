package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.*;
import com.jinshi.service.*;
import com.jinshi.util.DateUtils;
import com.jinshi.util.QianYiCameraUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@CrossOrigin
@RequestMapping("/lincensePlate")
@Api(tags = "在场记录管理")
public class LincensePlateController {

    private static Logger logger = Logger.getLogger(LincensePlateController.class.getName());
    @Autowired
    private LincensePlateService lincensePlateService;

    @Autowired
    private UserService userService;

    @Autowired
    private LincensePlateHistoryService lincensePlateHistoryService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private JinshiAttentionService jinshiAttentionService;

    @RequestMapping(value = "/batchExportPlate",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "在场记录---导出")
    public void batchExport(@RequestParam("parkId") String parkId,
                            @RequestParam("keyWord") String keyWord,
                            @RequestParam("companyName") String companyName,
                            @RequestParam("areaName") String areaName,
                            @RequestParam("startTime") String startTime,
                            @RequestParam("endTime") String endTime,
                            HttpServletResponse response) throws Exception{
        lincensePlateService.batchExport(parkId,keyWord,companyName,areaName,startTime,endTime,response);
    }

    @RequestMapping(value = "/getPayOpenGate",method = RequestMethod.POST)
    @ResponseBody
    public Integer getPayOpenGate(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toString());
        LincensePlate lincensePlate = JSONObject.parseObject(jsonParam.toJSONString(), LincensePlate.class);
        return lincensePlateService.deleteByPrimaryKey(lincensePlate.getLpId());
    }

    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
    @ResponseBody
    public Integer deleteByPrimaryKey(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toString());
        LincensePlate lincensePlate = JSONObject.parseObject(jsonParam.toJSONString(), LincensePlate.class);
        return lincensePlateService.deleteByPrimaryKey(lincensePlate.getLpId());
    }

    @RequestMapping(value = "/deleteAndInsertByPrimaryKey",method = RequestMethod.POST)
    @ResponseBody
    public Integer deleteAndInsertByPrimaryKey(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toString());
        LincensePlateHistory lincensePlateHistory = new LincensePlateHistory();
        LincensePlate lincensePlate = JSONObject.parseObject(jsonParam.toJSONString(), LincensePlate.class);
        LincensePlate lp = lincensePlateService.selectByPrimaryKey(lincensePlate.getLpId());
        BeanUtils.copyProperties(lp,lincensePlateHistory);
        lincensePlateHistory.setLpPaymentType("手动删除");
        lincensePlateHistory.setLpId(null);
        lincensePlateHistoryService.insert(lincensePlateHistory);
        return lincensePlateService.deleteByPrimaryKey(lincensePlate.getLpId());
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public Integer insert(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        LincensePlate lincensePlate = JSONObject.parseObject(jsonParam.toJSONString(), LincensePlate.class);
        return lincensePlateService.insert(lincensePlate);
    }

    @RequestMapping(value = "/deleteExceptionData",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public Integer deleteExceptionData(@RequestBody JSONObject jsonParam) {
        String deleteExceptionData = (String) jsonParam.get("deleteExceptionData");
        return lincensePlateService.deleteExceptionData(deleteExceptionData);
    }

    @RequestMapping(value = "/selectLincenseAll",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String selectLincenseAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkId = (String) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        List<LincensePlate> res = lincensePlateService.selectLincenseForPage(pageNum,pageSize);
        int memberTotalRecords = lincensePlateService.getLincenseTotalRecords(parkId);
        JSONObject resJo = new JSONObject();
        resJo.put("lincenseData",res);
        resJo.put("lincenseTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }

    @RequestMapping(value = "/selectByLincese",method = RequestMethod.POST)
    @ResponseBody
    public LincensePlate selectByLincese(@RequestBody JSONObject jsonParam){
//        logger.info(jsonParam.toJSONString());
        String lincensePlateIdCar = (String) jsonParam.get("lpLincensePlateIdCar");
//        logger.info("lincese:  "+lincensePlateIdCar);
        LincensePlate lincensePlate = new LincensePlate();
        lincensePlate.setLpLincensePlateIdCar(lincensePlateIdCar);
        List<LincensePlate> lincensePlates = lincensePlateService.selectByLincense(lincensePlate);
        if(lincensePlates.size()>0){
//            logger.info(lincensePlates.get(0));
            return lincensePlates.get(0);
        }else{
            return null;
        }
    }

    @RequestMapping(value = "/selectByLincensePlate",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public List<LincensePlate> selectByLincensePlate(@RequestBody JSONObject jsonParam){
        String lincensePlateIdCar = (String) jsonParam.get("lpLincensePlateIdCar");
        List<LincensePlate> lincensePlates = lincensePlateService.selectByLincensePlate(lincensePlateIdCar);
        return lincensePlates;
    }

    @RequestMapping(value = "/updateByPlate",method = RequestMethod.POST)
    @ResponseBody
    public Integer updateByPlate(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        LincensePlate lincensePlate = JSONObject.parseObject(jsonParam.toJSONString(), LincensePlate.class);
        return lincensePlateService.updateByPlate(lincensePlate);
    }

    @RequestMapping(value = "/selectByOrderId",method = RequestMethod.POST)
    @ResponseBody
    public List<LincensePlate> selectByOrderId(@RequestBody JSONObject jsonParam){
        String lpOrderId = (String) jsonParam.get("lpOrderId");
        return lincensePlateService.selectByOrderId(lpOrderId);
    }

    @RequestMapping(value = "/updateByPlateTemporary",method = RequestMethod.POST)
    @ResponseBody
    public Integer updateByPlateTemporary(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        LincensePlate lincensePlate = JSONObject.parseObject(jsonParam.toJSONString(), LincensePlate.class);
        return lincensePlateService.updateByPlateTemporary(lincensePlate);
    }

    @RequestMapping(value = "/updatePlateCleanUp",method = RequestMethod.POST)
    @ResponseBody
    public Integer updatePlateCleanUp(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        LincensePlate lincensePlate = JSONObject.parseObject(jsonParam.toJSONString(), LincensePlate.class);
        return lincensePlateService.updatePlateCleanUp(lincensePlate);
    }

    @RequestMapping("/insertSelective")
    public Integer insertSelective(LincensePlate record) {
        return lincensePlateService.insertSelective(record);
    }

    @RequestMapping("/selectByPrimaryKey")
    public LincensePlate selectByPrimaryKey(Integer id) {
        return lincensePlateService.selectByPrimaryKey(id);
    }



    @RequestMapping("/updateByPrimaryKeySelective")
    public Integer updateByPrimaryKeySelective(LincensePlate record) {

        return lincensePlateService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("/updateByPrimaryKey")
    public Integer updateByPrimaryKey(LincensePlate record) {

        return lincensePlateService.updateByPrimaryKey(record);
    }

    @PostMapping("/selectByParkIdAndCname")
    @ResponseBody
    public List<LincensePlate> selectByParkIdAndCname(@RequestBody JSONObject jsonParam) {
        LincensePlate lincensePlate = new LincensePlate();
        lincensePlate.setLpDepartureCname((String) jsonParam.get("lpDepartureCname"));
        lincensePlate.setLpParkingName((String)jsonParam.get("lpParkingName"));
        return lincensePlateService.selectByParkIdAndCname(lincensePlate);
    }
    @PostMapping("/updatePlateCnameToNull")
    @ResponseBody
    public Integer updatePlateCnameToNull(@RequestBody JSONObject jsonParam) {
        LincensePlate lincensePlate = JSONObject.parseObject(jsonParam.toJSONString(), LincensePlate.class);
        return lincensePlateService.updatePlateCnameToNull(lincensePlate);
    }

    /**
     * 在场记录页面刷新默认今天的数据
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectPlateInRocord",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "在场记录---页面刷新默认今天的数据")
    public String selectPlateInRocord(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkIdStr = (String) jsonParam.get("parkId");
        Date dayBegin = DateUtils.getDayBegin();
        Date dayEnd = DateUtils.getDayEnd();
        pageNum = (pageNum-1)*pageSize;
        List<LincensePlate> res = lincensePlateService.selectLincenseInRocord(pageNum,pageSize,parkIdStr,dayBegin, dayEnd);
        int memberTotalRecords = lincensePlateService.getLincenseInRecords(parkIdStr,dayBegin, dayEnd);
        Integer integer = lincensePlateService.selectCountLincenseInRocordByDay(dayBegin, dayEnd, parkIdStr);
        JSONObject resJo = new JSONObject();
        resJo.put("lincenseData",res);
        resJo.put("countInRocord",integer);
        resJo.put("lincenseTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }

    /**
     * 在场记录条件查询 + 搜索
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectPlateInRocordByTime",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "在场记录---条件查询 + 搜索")
    public String selectPlateInRocordByTime(@RequestBody JSONObject jsonParam) throws Exception{
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkIdStr = (String) jsonParam.get("parkId");
        String keyWord = (String) jsonParam.get("keyWord");
        String companyName = (String) jsonParam.get("jsName");//公司名字
        String areaName = (String) jsonParam.get("areaName");//区域名称
        pageNum = (pageNum-1)*pageSize;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String startTimeJS = (String) jsonParam.get("startTime");
        String endTimeJS = (String) jsonParam.get("endTime");
        Date startTime = null;
        Date endTime = null;
        if (startTimeJS != null && endTimeJS != null) {
            startTime = DateUtils.getDayStartTime(format.parse(startTimeJS));
            endTime = DateUtils.getDayEndTime(format.parse(endTimeJS));
        }
        List<LincensePlate> res = lincensePlateService.selectLincenseInRocordByTime(pageNum,pageSize,parkIdStr,startTime,endTime,keyWord,companyName,areaName);
        int memberTotalRecords = lincensePlateService.getLincenseInRecordsByTime(parkIdStr,startTime,endTime,keyWord,companyName,areaName);
        Integer integer = lincensePlateService.selectCountLincenseInRocordByDayByKey(startTime, endTime, parkIdStr,keyWord,companyName,areaName);
        JSONObject resJo = new JSONObject();
        resJo.put("lincenseData",res);
        resJo.put("countInRocord",integer);
        resJo.put("lincenseTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }

    /**
     * 在场记录--搜索 ----弃用
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
        List<LincensePlate> res = lincensePlateService.searchLincense(keyWord,pageNum,pageSize,parkId);
        int memberTotalRecords = lincensePlateService.getLincenseTotalRecordsSearch(parkId,keyWord);
        JSONObject resJo = new JSONObject();
        resJo.put("lincenseData",res);
        resJo.put("lincenseTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }


    /**
     * 历史记录按出场类型或者付费类型查询
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectPlateInRocordByType",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "历史记录---按出场类型或者付费类型查询")
    public String selectPlateInRocordByType(@RequestBody JSONObject jsonParam) {
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        Integer parkIdStr = (Integer) jsonParam.get("parkId");
        String lpPaymentType = (String) jsonParam.get("lpPaymentType");
        pageNum = (pageNum-1)*pageSize;
        List<LincensePlate> res = lincensePlateHistoryService.selectPlateInRocordByType(pageNum,pageSize,parkIdStr.toString(),lpPaymentType);
        int memberTotalRecords = lincensePlateHistoryService.getLincenseInRecordsByType(parkIdStr.toString(),lpPaymentType);
        JSONObject resJo = new JSONObject();
        resJo.put("lincenseData",res);
        resJo.put("lincenseTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }

    /**
     * 在场记录条件清除数据到历史表
     * @return
     */
    @RequestMapping(value = "/updatePlateInRocordToHistory",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "在场记录---条件清除数据到历史表")
    public Integer updatePlateInRocordToHistory(@RequestBody JSONObject jsonParam) throws Exception {
        String parkIdStr = (String) jsonParam.get("parkId");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = DateUtils.getDayStartTime(format.parse(jsonParam.get("startTime").toString())); //开始时间
        Date endTime =  DateUtils.getDayEndTime(format.parse(jsonParam.get("endTime").toString())); //结束时间
        List<LincensePlate> lincensePlateList = lincensePlateService.selectByDateToHistory(parkIdStr,startTime,endTime);
        if (lincensePlateList.size() > 0) {
            for (int i = 0; i < lincensePlateList.size(); i++) {
                LincensePlateHistory lp = new LincensePlateHistory();
                LincensePlate lincensePlate = lincensePlateList.get(i);
                BeanUtils.copyProperties(lincensePlate,lp);
                lp.setLpPaymentType("手动清除记录");
                lp.setLpDepartureTime(new Date());
                lp.setLpId(null);
                lincensePlateHistoryService.insert(lp);
                lincensePlateService.deleteByPrimaryKey(lincensePlate.getLpId());
            }
        }
        return 1;
    }

    /**
     * 如果 LpLgType 为null，则去查询是否为会员
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/isMember")
    public String isMember(@RequestBody JSONObject jsonParam) {

        String lpLincensePlateIdCar = (String) jsonParam.get("lpLincensePlateIdCar");
        List<Member> member = memberService.selectByID(lpLincensePlateIdCar);
        String type = null;
        if (member.size() > 0) {
            lincensePlateService.updateType(lpLincensePlateIdCar);
            type = "1";
        } else {
            lincensePlateService.updateTypeZero(lpLincensePlateIdCar);
            type = "0";
        }
        return type;
    }


    /**
     * 关注记录查询所有
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/selectAttentionRecord",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectAttentionRecord(@RequestBody JSONObject jsonParam){
        String parkId = (String) jsonParam.get("parkId");
        List<JinshiAttention> res = jinshiAttentionService.selectByParkId(parkId);
        JSONObject jsonObject = new JSONObject();
        if(res.size()>0){
            for (JinshiAttention jinshiAttention : res) {
                List<LincensePlate> lincensePlateList = lincensePlateService.selectByLincensePlate(jinshiAttention.getJcLincensePlateId());
                List<LincensePlateHistory> lincensePlateHistoryList = lincensePlateHistoryService.selectByLincensePlate(jinshiAttention.getJcLincensePlateId());
                if (lincensePlateList.size() > 0) {
                    jsonObject.put("data", lincensePlateList);
                    if (lincensePlateHistoryList.size() > 0) {
                        jsonObject.put("dataTwo", lincensePlateHistoryList);
                    }
                } else if (lincensePlateHistoryList.size() > 0){
                    jsonObject.put("dataTwo", lincensePlateHistoryList);
                }
            }
        }
        return jsonObject;
    }


    @RequestMapping(value = "/selectLincenseOutRocord",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String selectLincenseOutRocord(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        pageNum = (pageNum-1)*pageSize;
        List<LincensePlate> res = lincensePlateService.selectLincenseOutRocord(pageNum,pageSize);
        int memberTotalRecords = lincensePlateService.getLincenseOutRecords();
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
        String parkId = (String) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        List<LincensePlate> res = lincensePlateService.selectLincenseForPage(pageNum,pageSize);
        int memberTotalRecords = lincensePlateService.getLincenseTotalRecords(parkId);
        JSONObject resJo = new JSONObject();
        resJo.put("lincensessData",res);
        resJo.put("lincensessTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }

    /**
     * 根据车牌组id查询在场车辆表
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectByLgId",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public List<LincensePlate> selectByLgId(@RequestBody JSONObject jsonParam){
        Integer lgId = (Integer) jsonParam.get("lpLgId");
        List<LincensePlate> res = lincensePlateService.selectByLgId(lgId);
        return res;
    }

    /**
     * 根据areaName查询在场车辆数
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectCountByParkId",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public Integer selectCountByParkId(@RequestBody JSONObject jsonParam){
        String areaName = (String) jsonParam.get("areaName");
        return lincensePlateService.getLincenseTotalRecords(areaName);
    }

    /**
     * 财务管理页面按条件查询
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectLincenseByCalendar",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "财务管理---按条件查询")
    public String selectLincenseByCalendar(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        pageNum = (pageNum-1)*pageSize;
        String type = (String) jsonParam.get("type");
        JSONObject resJo = new JSONObject();
        List<LincensePlate> res = new ArrayList<>();
        int lincenseTotalRecords = 0;
        List<CountList> countList = new ArrayList<>();
        if ("1".equals(type)) {
            Date dayBegin = DateUtils.getDayBegin();
            Date dayEnd = DateUtils.getDayEnd();
            res = lincensePlateService.selectLincenseByDay(dayBegin,dayEnd,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateService.getLincenseTotalRecordsByDay(dayBegin,dayEnd);
            countList.add(lincensePlateService.selectCountLincensePlateByDay(dayBegin,dayEnd));
        } else if ("2".equals(type)) {
            Date beginDayOfWeek = DateUtils.getBeginDayOfWeek();
            Date endDayOfWeek = DateUtils.getEndDayOfWeek();
            res = lincensePlateService.selectLincenseByWeek(beginDayOfWeek,endDayOfWeek,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateService.getLincenseTotalRecordsByWeek(beginDayOfWeek,endDayOfWeek);
            countList.add(lincensePlateService.selectCountLincensePlateByWeek(beginDayOfWeek,endDayOfWeek));
        } else if ("3".equals(type)) {
            Date beginDayOfMonth = DateUtils.getBeginDayOfMonth();
            Date endDayOfMonth = DateUtils.getEndDayOfMonth();
            res = lincensePlateService.selectLincenseByMonth(beginDayOfMonth,endDayOfMonth,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateService.getLincenseTotalRecordsByMonth(beginDayOfMonth,endDayOfMonth);
            countList.add(lincensePlateService.selectCountLincensePlateByMonth(beginDayOfMonth,endDayOfMonth));
        } else if ("4".equals(type)) {
            Date beginDayOfYear = DateUtils.getBeginDayOfYear();
            Date endDayOfYear = DateUtils.getEndDayOfYear();
            res = lincensePlateService.selectLincenseByYear(beginDayOfYear,endDayOfYear,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateService.getLincenseTotalRecordsByYear(beginDayOfYear,endDayOfYear);
            countList.add(lincensePlateService.selectCountLincensePlateByYear(beginDayOfYear,endDayOfYear));
        }
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
        String parkId = (String) jsonParam.get("parkId");
        List<LincensePlate> res = new ArrayList<>();
        int lincenseTotalRecords = 0;
        List<Integer> countList = new ArrayList<>();
        if ("1".equals(type)) {
            Date dayBegin = DateUtils.getDayBegin();
            Date dayEnd = DateUtils.getDayEnd();
            res = lincensePlateService.selectLincenseOutRocordByDay(dayBegin,dayEnd,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateService.getLincenseTotalRecords(parkId);
            countList.add(lincensePlateService.selectCountLincenseOutRocordByDay(dayBegin,dayEnd));
        } else if ("2".equals(type)) {
            Date beginDayOfWeek = DateUtils.getBeginDayOfWeek();
            Date endDayOfWeek = DateUtils.getEndDayOfWeek();
            res = lincensePlateService.selectLincenseOutRocordByWeek(beginDayOfWeek,endDayOfWeek,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateService.getLincenseTotalRecords(parkId);
            countList.add(lincensePlateService.selectCountLincenseOutRocordByWeek(beginDayOfWeek,endDayOfWeek));
        } else if ("3".equals(type)) {
            Date beginDayOfMonth = DateUtils.getBeginDayOfMonth();
            Date endDayOfMonth = DateUtils.getEndDayOfMonth();
            res = lincensePlateService.selectLincenseOutRocordByMonth(beginDayOfMonth,endDayOfMonth,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateService.getLincenseTotalRecords(parkId);
            countList.add(lincensePlateService.selectCountLincenseOutRocordByMonth(beginDayOfMonth,endDayOfMonth));
        } else if ("4".equals(type)) {
            Date beginDayOfYear = DateUtils.getBeginDayOfYear();
            Date endDayOfYear = DateUtils.getEndDayOfYear();
            res = lincensePlateService.selectLincenseOutRocordByYear(beginDayOfYear,endDayOfYear,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateService.getLincenseTotalRecords(parkId);
            countList.add(lincensePlateService.selectCountLincenseOutRocordByYear(beginDayOfYear,endDayOfYear));
        }
        JSONObject resJo = new JSONObject();
        resJo.put("lincenseData",res);
        resJo.put("lincenseCountList",countList);
        resJo.put("lincenseTotalRecords",lincenseTotalRecords);
        return resJo.toJSONString();
    }

    /**
     * 总览页面进场车辆统计走势图
     * @return
     */
    @RequestMapping(value = "/selectPlateVehicleChart",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "总览---进场车辆统计走势图")
    public List<InfoForIndex> selectPlateVehicleChart(@RequestBody JSONObject jsonParam) {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<InfoForIndex> list = new ArrayList<>();
        Integer [] arrays = new Integer[7];
        String parkId = (String) jsonParam.get("parkId");
        for (int j = 0; j < arrays.length; j++) {
            InfoForIndex infoForIndex = new InfoForIndex();
            if (0 == j) {
                Date dayStartTime = DateUtils.getDayStartTime(new Date());
                Date dayEndTime = DateUtils.getDayEndTime(new Date());
                Integer count = lincensePlateService.selectCountByTimeAndParkId(dayStartTime,dayEndTime,parkId);
                infoForIndex.setName(format.format(new Date()));
                infoForIndex.setValue(count);
                list.add(infoForIndex);
            } else {
                Date dayStartTime = DateUtils.getDayStartTime(DateUtils.getFrontDay(new Date(), j));
                Date dayEndTime = DateUtils.getDayEndTime(DateUtils.getFrontDay(new Date(), j));
                Integer count = lincensePlateService.selectCountByTimeAndParkId(dayStartTime,dayEndTime,parkId);
                infoForIndex.setName(format.format(dayStartTime));
                infoForIndex.setValue(count);
                list.add(infoForIndex);
            }
        }
        return  list;
    }

    /**
     * 总览页面在场车辆总数
     * @return
     */
    @GetMapping(value = "/selectCountPlateNumbers")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "总览---在场车辆总数")
    public Integer selectCountPlateNumbers(@RequestParam("username") String username){

        User user = userService.selectAllByUserName(username);
        Integer parkid = user.getParkid();
        Integer count = lincensePlateService.selectCountPlateNumbers(String.valueOf(parkid));
        return count;
    }
}
