package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.CountList;
import com.jinshi.entity.LincensePlateBo;
import com.jinshi.entity.LincensePlateHistory;
import com.jinshi.entity.User;
import com.jinshi.service.LincensePlateHistoryService;
import com.jinshi.service.UserService;
import com.jinshi.util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@CrossOrigin
@RequestMapping("/lincensePlateHistory")
@Api(tags = "历史记录")
public class LincensePlateHistoryController {

    private static Logger logger = Logger.getLogger(LincensePlateHistoryController.class.getName());
    @Autowired
    private LincensePlateHistoryService lincensePlateHistoryService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/batchExportPlateHistory",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "历史记录---导出")
    public void batchExport(@RequestParam("parkId") String parkId,
                            @RequestParam("keyWord") String keyWord,
                            @RequestParam("companyName") String companyName,
                            @RequestParam("areaName") String areaName,
                            @RequestParam("startTime") String startTime,
                            @RequestParam("endTime") String endTime,
                            @RequestParam("lpPaymentType") String lpPaymentType,
                            HttpServletResponse response) throws Exception{
        lincensePlateHistoryService.batchExport(parkId,keyWord,companyName,areaName,startTime,endTime,lpPaymentType,response);
    }

    @RequestMapping("/deleteByPrimaryKey")
    public Integer deleteByPrimaryKey(Integer id) {
        return lincensePlateHistoryService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public Integer insert(@RequestBody JSONObject jsonParam) {
        logger.info(jsonParam.toJSONString());
        LincensePlateHistory lincensePlate = JSONObject.parseObject(jsonParam.toJSONString(), LincensePlateHistory.class);
        return lincensePlateHistoryService.insert(lincensePlate);
    }

    /**
     * 历史记录页面刷新默认是今天的记录
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectLincenseAll",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "历史记录---页面刷新默认是今天的记录")
    public String selectLincenseAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkId = (String) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        Date dayBegin = DateUtils.getDayBegin();
        Date dayEnd = DateUtils.getDayEnd();
        List<LincensePlateHistory> res = lincensePlateHistoryService.selectLincenseForPageAll(pageNum,pageSize,parkId,dayBegin,dayEnd);
        int memberTotalRecords = lincensePlateHistoryService.getLincenseTotalRecordsAll(parkId,dayBegin,dayEnd);
        JSONObject resJo = new JSONObject();
        resJo.put("lincenseData",res);
        resJo.put("lincenseTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }

    /**
     * 历史记录页面按时间段条件查询/出场类型或者付费类型查询 + 搜索
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/selectLincenseAllByTime",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "历史记录---按时间段条件查询/出场类型或者付费类型查询 + 搜索")
    public String selectLincenseAllByTime(@RequestBody JSONObject jsonParam) throws Exception{
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkId = (String) jsonParam.get("parkId");
        String keyWord = (String) jsonParam.get("keyWord");
        String companyName = (String) jsonParam.get("jsName");//公司名字
        String areaName = (String) jsonParam.get("areaName");//区域名称
        pageNum = (pageNum-1)*pageSize;
        String lpPaymentType = (String) jsonParam.get("lpPaymentType");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String startTimeJS = (String) jsonParam.get("startTime");
        String endTimeJS = (String) jsonParam.get("endTime");
        Date startTime = null;
        Date endTime = null;
        if (startTimeJS != null && endTimeJS != null) {
            startTime = DateUtils.getDayStartTime(format.parse(startTimeJS));
            endTime = DateUtils.getDayEndTime(format.parse(endTimeJS));
        }
        List<LincensePlateHistory> res = lincensePlateHistoryService.selectHistoryByTimeAndType(pageNum,pageSize,parkId,startTime,endTime,lpPaymentType,keyWord,companyName,areaName);
        int memberTotalRecords = lincensePlateHistoryService.getHistoryByTimeAndType(parkId,startTime,endTime,lpPaymentType,keyWord,companyName,areaName);
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
        String parkId = (String) jsonParam.get("parkId");
        pageNum = (pageNum-1)*pageSize;
        List<LincensePlateHistory> res = lincensePlateHistoryService.searchLincense(keyWord,pageNum,pageSize,parkId);
        int memberTotalRecords = lincensePlateHistoryService.getLincenseTotalRecordsSearch(parkId,keyWord);
        JSONObject resJo = new JSONObject();
        resJo.put("lincenseData",res);
        resJo.put("lincenseTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }

    @RequestMapping(value = "/selectByLincese",method = RequestMethod.POST)
    @ResponseBody
    public LincensePlateHistory selectByLincese(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        String lincensePlateIdCar = (String) jsonParam.get("lpLincensePlateIdCar");
        logger.info("lincese:  "+lincensePlateIdCar);
        LincensePlateHistory lincensePlate = new LincensePlateHistory();
        lincensePlate.setLpLincensePlateIdCar(lincensePlateIdCar);
        LincensePlateHistory lincensePlate1 = new LincensePlateHistory();
        try {
             lincensePlate1 = lincensePlateHistoryService.selectByLincense(lincensePlate);
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
        logger.info(jsonParam.toJSONString());
        LincensePlateHistory lincensePlate = JSONObject.parseObject(jsonParam.toJSONString(), LincensePlateHistory.class);
        return lincensePlateHistoryService.updateByPlate(lincensePlate);
    }

    @RequestMapping(value = "/updatePlateCleanUp",method = RequestMethod.POST)
    @ResponseBody
    public Integer updatePlateCleanUp(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        LincensePlateHistory lincensePlate = JSONObject.parseObject(jsonParam.toJSONString(), LincensePlateHistory.class);
        return lincensePlateHistoryService.updatePlateCleanUp(lincensePlate);
    }

    @RequestMapping("/insertSelective")
    public Integer insertSelective(LincensePlateHistory record) {
        return lincensePlateHistoryService.insertSelective(record);
    }

    @RequestMapping("/selectByPrimaryKey")
    public LincensePlateHistory selectByPrimaryKey(Integer id) {
        return lincensePlateHistoryService.selectByPrimaryKey(id);
    }



    @RequestMapping("/updateByPrimaryKeySelective")
    public Integer updateByPrimaryKeySelective(LincensePlateHistory record) {

        return lincensePlateHistoryService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("/updateByPrimaryKey")
    public Integer updateByPrimaryKey(LincensePlateHistory record) {

        return lincensePlateHistoryService.updateByPrimaryKey(record);
    }

    @RequestMapping(value = "/selectLincenseInRocord",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String selectLincenseInRocord(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        pageNum = (pageNum-1)*pageSize;
        List<LincensePlateHistory> res = lincensePlateHistoryService.selectLincenseInRocord(pageNum,pageSize);
        int memberTotalRecords = lincensePlateHistoryService.getLincenseInRecords();
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
        List<LincensePlateHistory> res = lincensePlateHistoryService.selectLincenseOutRocord(pageNum,pageSize);
        int memberTotalRecords = lincensePlateHistoryService.getLincenseOutRecords();
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
        List<LincensePlateHistory> res = lincensePlateHistoryService.selectLincenseForPage(pageNum,pageSize,parkId);
        int memberTotalRecords = lincensePlateHistoryService.getLincenseTotalRecords(parkId);
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
    @ApiOperation(value = "财务管理---按条件查询")
    public String selectLincenseByCalendar(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        pageNum = (pageNum-1)*pageSize;
        String type = (String) jsonParam.get("type");
        JSONObject resJo = new JSONObject();
        List<LincensePlateHistory> res = new ArrayList<>();
        int lincenseTotalRecords = 0;
        List<CountList> countList = new ArrayList<>();
        if ("1".equals(type)) {
            Date dayBegin = DateUtils.getDayBegin();
            Date dayEnd = DateUtils.getDayEnd();
            res = lincensePlateHistoryService.selectLincenseByDay(dayBegin,dayEnd,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateHistoryService.getLincenseTotalRecordsByDay(dayBegin,dayEnd);
            countList.add(lincensePlateHistoryService.selectCountLincensePlateByDay(dayBegin,dayEnd));
        } else if ("2".equals(type)) {
            Date beginDayOfWeek = DateUtils.getBeginDayOfWeek();
            Date endDayOfWeek = DateUtils.getEndDayOfWeek();
            res = lincensePlateHistoryService.selectLincenseByWeek(beginDayOfWeek,endDayOfWeek,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateHistoryService.getLincenseTotalRecordsByWeek(beginDayOfWeek,endDayOfWeek);
            countList.add(lincensePlateHistoryService.selectCountLincensePlateByWeek(beginDayOfWeek,endDayOfWeek));
        } else if ("3".equals(type)) {
            Date beginDayOfMonth = DateUtils.getBeginDayOfMonth();
            Date endDayOfMonth = DateUtils.getEndDayOfMonth();
            res = lincensePlateHistoryService.selectLincenseByMonth(beginDayOfMonth,endDayOfMonth,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateHistoryService.getLincenseTotalRecordsByMonth(beginDayOfMonth,endDayOfMonth);
            countList.add(lincensePlateHistoryService.selectCountLincensePlateByMonth(beginDayOfMonth,endDayOfMonth));
        } else if ("4".equals(type)) {
            Date beginDayOfYear = DateUtils.getBeginDayOfYear();
            Date endDayOfYear = DateUtils.getEndDayOfYear();
            res = lincensePlateHistoryService.selectLincenseByYear(beginDayOfYear,endDayOfYear,pageNum,pageSize);
            lincenseTotalRecords = lincensePlateHistoryService.getLincenseTotalRecordsByYear(beginDayOfYear,endDayOfYear);
            countList.add(lincensePlateHistoryService.selectCountLincensePlateByYear(beginDayOfYear,endDayOfYear));
        }
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
    @ApiOperation(value = "总览---查询每天进出付费车辆数")
    public String selectPlateByDay(@RequestParam("username") String username){

        JSONObject resJo = new JSONObject();
        Date dayBegin = DateUtils.getDayBegin();
        Date dayEnd = DateUtils.getDayEnd();
        User user = userService.selectAllByUserName(username);
        String roleName = user.getRoleName();
        if ("admin".equals(username) || "金石管理员".equals(roleName)) {
            //admin权限查询所有的数据
            //进车辆数
            Integer inCountByDay = lincensePlateHistoryService.selectInCountByDay(dayBegin, dayEnd);
            //出车辆数
            Integer outCountByDay = lincensePlateHistoryService.selectOutCountByDay(dayBegin, dayEnd);
            //付费车辆
            Integer payCountByDay = lincensePlateHistoryService.selectPayCountByDay(dayBegin, dayEnd);
            //付费总额
            Integer payFeeByDay = lincensePlateHistoryService.selectPayFeeCountByDay(dayBegin,dayEnd);
            resJo.put("outInCount",inCountByDay + outCountByDay);
            resJo.put("payCount",payCountByDay);
            resJo.put("payFeeCount",payFeeByDay);
        } else if (roleName.contains("代理商")){
            //代理商登陆
            //进车辆数
            Integer inCountByDay = lincensePlateHistoryService.selectInCountByDayAgentId(dayBegin,dayEnd,String.valueOf(user.getAgentid()));
            //出车辆数
            Integer outCountByDay = lincensePlateHistoryService.selectOutCountByDayAgentId(dayBegin,dayEnd,String.valueOf(user.getAgentid()));
            //付费车辆
            Integer payCountByDay = lincensePlateHistoryService.selectPayCountByDayAgentId(dayBegin,dayEnd,String.valueOf(user.getAgentid()));
            //付费总额
            Integer payFeeByDay = lincensePlateHistoryService.selectPayFeeCountByDayAgentId(dayBegin,dayEnd,String.valueOf(user.getAgentid()));
            resJo.put("outInCount",inCountByDay + outCountByDay);
            resJo.put("payCount",payCountByDay);
            resJo.put("payFeeCount",payFeeByDay);
        } else {
            //其他权限根据parkid查询
            //进车辆数
            Integer inCountByDay = lincensePlateHistoryService.selectInCountByDayId(dayBegin,dayEnd,String.valueOf(user.getParkid()));
            //出车辆数
            Integer outCountByDay = lincensePlateHistoryService.selectOutCountByDayId(dayBegin,dayEnd,String.valueOf(user.getParkid()));
            //付费车辆
            Integer payCountByDay = lincensePlateHistoryService.selectPayCountByDayId(dayBegin,dayEnd,String.valueOf(user.getParkid()));
            //付费总额
            Integer payFeeByDay = lincensePlateHistoryService.selectPayFeeCountByDayId(dayBegin,dayEnd,String.valueOf(user.getParkid()));
            resJo.put("outInCount",inCountByDay + outCountByDay);
            resJo.put("payCount",payCountByDay);
            resJo.put("payFeeCount",payFeeByDay);
        }
        return resJo.toJSONString();
    }

    /**
     * 总览页面查询每周进出付费车辆数
     * @return
     */
    @RequestMapping(value = "/selectPlateByWeek",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "总览---查询每周进出付费车辆数")
    public String selectPlateByWeek(@RequestParam("username") String username){

        JSONObject resJo = new JSONObject();
        Date beginDayOfWeek = DateUtils.getBeginDayOfWeek();
        Date endDayOfWeek = DateUtils.getEndDayOfWeek();
        User user = userService.selectAllByUserName(username);
        String roleName = user.getRoleName();
        if ("admin".equals(username) || "金石管理员".equals(roleName)) {
            //进车辆数
            Integer inCountByWeek = lincensePlateHistoryService.selectInCountByWeek(beginDayOfWeek, endDayOfWeek);
            //出车辆数
            Integer outCountByWeek = lincensePlateHistoryService.selectOutCountByWeek(beginDayOfWeek, endDayOfWeek);
            //付费车辆
            Integer payCountByWeek = lincensePlateHistoryService.selectPayCountByWeek(beginDayOfWeek, endDayOfWeek);
            //付费总额
            Integer payFeeByWeek = lincensePlateHistoryService.selectPayFeeCountByWeek(beginDayOfWeek, endDayOfWeek);
            resJo.put("outInCount", inCountByWeek + outCountByWeek);
            resJo.put("payCount", payCountByWeek);
            resJo.put("payFeeCount", payFeeByWeek);
        } else if (roleName.contains("代理商")){
            //代理商登陆
            //进车辆数
            Integer inCountByDay = lincensePlateHistoryService.selectInCountByWeekAgentId(beginDayOfWeek,endDayOfWeek,String.valueOf(user.getAgentid()));
            //出车辆数
            Integer outCountByDay = lincensePlateHistoryService.selectOutCountByWeekAgentId(beginDayOfWeek,endDayOfWeek,String.valueOf(user.getAgentid()));
            //付费车辆
            Integer payCountByDay = lincensePlateHistoryService.selectPayCountByWeekAgentId(beginDayOfWeek,endDayOfWeek,String.valueOf(user.getAgentid()));
            //付费总额
            Integer payFeeByDay = lincensePlateHistoryService.selectPayFeeCountByWeekAgentId(beginDayOfWeek,endDayOfWeek,String.valueOf(user.getAgentid()));
            resJo.put("outInCount",inCountByDay + outCountByDay);
            resJo.put("payCount",payCountByDay);
            resJo.put("payFeeCount",payFeeByDay);
        } else {
            //进车辆数
            Integer inCountByWeek = lincensePlateHistoryService.selectInCountByWeekId(beginDayOfWeek, endDayOfWeek,String.valueOf(user.getParkid()));
            //出车辆数
            Integer outCountByWeek = lincensePlateHistoryService.selectOutCountByWeekId(beginDayOfWeek, endDayOfWeek,String.valueOf(user.getParkid()));
            //付费车辆
            Integer payCountByWeek = lincensePlateHistoryService.selectPayCountByWeekId(beginDayOfWeek, endDayOfWeek,String.valueOf(user.getParkid()));
            //付费总额
            Integer payFeeByWeek = lincensePlateHistoryService.selectPayFeeCountByWeekId(beginDayOfWeek, endDayOfWeek,String.valueOf(user.getParkid()));
            resJo.put("outInCount", inCountByWeek + outCountByWeek);
            resJo.put("payCount", payCountByWeek);
            resJo.put("payFeeCount", payFeeByWeek);
        }
        return resJo.toJSONString();
    }

    /**
     * 总览页面查询每月进出付费车辆数
     * @return
     */
    @RequestMapping(value = "/selectPlateByMonth",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "总览---查询每月进出付费车辆数")
    public String selectPlateByMonth(@RequestParam("username") String username){

        JSONObject resJo = new JSONObject();
        Date beginDayOfMonth = DateUtils.getBeginDayOfMonth();
        Date endDayOfMonth = DateUtils.getEndDayOfMonth();
        User user = userService.selectAllByUserName(username);
        String roleName = user.getRoleName();
        if ("admin".equals(username) || "金石管理员".equals(roleName)) {
            //进车辆数
            Integer inCountByMonth = lincensePlateHistoryService.selectInCountByMonth(beginDayOfMonth, endDayOfMonth);
            //出车辆数
            Integer outCountByMonth = lincensePlateHistoryService.selectOutCountByMonth(beginDayOfMonth, endDayOfMonth);
            //付费车辆
            Integer payCountByMonth = lincensePlateHistoryService.selectPayCountByMonth(beginDayOfMonth, endDayOfMonth);
            //付费总额
            Integer payFeeByMonth = lincensePlateHistoryService.selectPayFeeCountByMonth(beginDayOfMonth, endDayOfMonth);
            resJo.put("outInCount", inCountByMonth + outCountByMonth);
            resJo.put("payCount", payCountByMonth);
            resJo.put("payFeeCount", payFeeByMonth);
        }  else if (roleName.contains("代理商")){
            //代理商登陆
            //进车辆数
            Integer inCountByDay = lincensePlateHistoryService.selectInCountByMonthAgentId(beginDayOfMonth,endDayOfMonth,String.valueOf(user.getAgentid()));
            //出车辆数
            Integer outCountByDay = lincensePlateHistoryService.selectOutCountByMonthAgentId(beginDayOfMonth,endDayOfMonth,String.valueOf(user.getAgentid()));
            //付费车辆
            Integer payCountByDay = lincensePlateHistoryService.selectPayCountByMonthAgentId(beginDayOfMonth,endDayOfMonth,String.valueOf(user.getAgentid()));
            //付费总额
            Integer payFeeByDay = lincensePlateHistoryService.selectPayFeeCountByMonthAgentId(beginDayOfMonth,endDayOfMonth,String.valueOf(user.getAgentid()));
            resJo.put("outInCount",inCountByDay + outCountByDay);
            resJo.put("payCount",payCountByDay);
            resJo.put("payFeeCount",payFeeByDay);
        } else {
            //进车辆数
            Integer inCountByMonth = lincensePlateHistoryService.selectInCountByMonthId(beginDayOfMonth, endDayOfMonth,String.valueOf(user.getParkid()));
            //出车辆数
            Integer outCountByMonth = lincensePlateHistoryService.selectOutCountByMonthId(beginDayOfMonth, endDayOfMonth,String.valueOf(user.getParkid()));
            //付费车辆
            Integer payCountByMonth = lincensePlateHistoryService.selectPayCountByMonthId(beginDayOfMonth, endDayOfMonth,String.valueOf(user.getParkid()));
            //付费总额
            Integer payFeeByMonth = lincensePlateHistoryService.selectPayFeeCountByMonthId(beginDayOfMonth, endDayOfMonth,String.valueOf(user.getParkid()));
            resJo.put("outInCount", inCountByMonth + outCountByMonth);
            resJo.put("payCount", payCountByMonth);
            resJo.put("payFeeCount", payFeeByMonth);
        }
        return resJo.toJSONString();
    }
}
