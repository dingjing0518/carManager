package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiLincenseGroup;
import com.jinshi.entity.JinshiMemberOrder;
import com.jinshi.entity.LincensePlate;
import com.jinshi.entity.Member;
import com.jinshi.service.*;
import com.jinshi.util.DateUtils;
import com.jinshi.util.GlobalVariable;
import com.jinshi.util.OrderIdForCCB;
import com.jinshi.util.QianYiCameraUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/member")
@Api(tags = "车主档案")
public class MemberController {
    private static Logger logger = Logger.getLogger(MemberController.class.getName());
    @Autowired
    private MemberService memberService;

    @Autowired
    private BatchService batchService;

    @Autowired
    private JinshiMemberOrderService jinshiMemberOrderService;

    @Autowired
    private LincensePlateService lincensePlateService;

    @Autowired
    private JinshiLincenseGroupService jinshiLincenseGroupService;

    @RequestMapping(value = "/file")
    @ResponseBody
    public String file(){
        return "file.html";
    }

    @RequestMapping(value = "/batchImport",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "车主档案---导入")
    public String batchImport(MultipartFile file,Integer parkId,Integer agentId) {
        batchService.batchImport(file,parkId,agentId);
        return "forward:/file";
    }


    @RequestMapping(value = "/batchExport",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "车主档案---导出")
    public void batchExport(@RequestParam("parkId") String parkId,
                            @RequestParam("roleName") String roleName,
                            HttpServletResponse response) {
        batchService.batchExport(parkId,roleName,response);
    }


    @RequestMapping(value = "/checkMemberByLincese",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public Member checkMemberByLincese(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        Member member = JSONObject.parseObject(jsonParam.toJSONString(), Member.class);
        return memberService.checkMemberByLincese(member);
    }

    @RequestMapping(value = "/checkMemberByLinceseForUtil",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public List<Member> checkMemberByLinceseForUtil(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        Member member = JSONObject.parseObject(jsonParam.toJSONString(), Member.class);
        return memberService.checkMemberByLinceseForUtil(member);
    }

    /**
     * 是否会员
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/checkISMember",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "车主档案---是否会员")
    public List<Member> checkISMember(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        Member member = JSONObject.parseObject(jsonParam.toJSONString(), Member.class);
        return memberService.checkISMember(member);
    }

    /**
     * 是否全区域会员
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/checkISAllAreaMember",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "车主档案---是否全区域会员")
    public List<Member> checkISAllAreaMember(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        Member member = JSONObject.parseObject(jsonParam.toJSONString(), Member.class);
        return memberService.checkISAllAreaMember(member);
    }
    @CrossOrigin
    @RequestMapping(value = "/selectMemberAll",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "车主档案---查询所有")
    public String selectMemberAll(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkIdstr = (String) jsonParam.get("parkId");
        String roleName = (String) jsonParam.get("roleName");
        Integer parkId = Integer.parseInt(parkIdstr);
        pageNum = (pageNum-1)*pageSize;
        List<Member> res = new ArrayList<>();
        int memberTotalRecords;
        if ("超级管理员".equals(roleName) || "金石管理员".equals(roleName)) {
            res = memberService.selectMemberAll(pageNum,pageSize);
            memberTotalRecords = memberService.getAllCount();
        } else {
            res = memberService.selectMemberForPage(pageNum,pageSize,parkId);
            memberTotalRecords = memberService.getMemberTotalRecords(parkId);
        }
        JSONObject resJo = new JSONObject();
        resJo.put("memberData",res);
        resJo.put("memberTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }

    @CrossOrigin
    @RequestMapping(value = "/searchMember",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "车主档案---搜索")
    public String searchMember(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
        Integer pageSize = (Integer) jsonParam.get("pageSize");
        String areaName = (String) jsonParam.get("areaName");
        String phoneNumber = (String) jsonParam.get("phoneNumber");
        String lincensePlateId = (String) jsonParam.get("lincensePlateId");
        String companyName = (String) jsonParam.get("companyName");
        String parkIdStr = (String) jsonParam.get("parkId");
        Integer parkId = Integer.parseInt(parkIdStr);
        pageNum = (pageNum-1)*pageSize;
        List<Member> res = memberService.searchMember(areaName,phoneNumber,lincensePlateId,companyName,pageNum,pageSize,parkId);
     //   int memberTotalRecords = memberService.getMemberTotalRecordsSearch(parkId,keyWord);
        JSONObject resJo = new JSONObject();
        resJo.put("memberData",res);
      //  resJo.put("memberTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST)
    @ApiOperation(value = "车主档案---删除")
    public Integer deleteByPrimaryKey(@RequestBody JSONObject jsonParam) {
        Integer memberid = (Integer) jsonParam.get("memberid");
        int i = memberService.deleteByPrimaryKey(memberid);
        return i;
    }
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/updateByPrimaryKey", method = RequestMethod.POST)
    @ApiOperation(value = "车主档案---编辑")
    public JSONObject updateByPrimaryKey(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam);
        Member member = JSONObject.parseObject(jsonParam.toJSONString(), Member.class);
        return memberService.updateByPrimaryKey(member);
    }

    /**
     * 车主档案页面续费
     * @param jsonParam
     * @return
     */
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/updateRenew", method = RequestMethod.POST)
    @ApiOperation(value = "车主档案---续费")
    public Integer updateRenew(@RequestBody JSONObject jsonParam) throws Exception{
        Integer id = (Integer) jsonParam.get("id");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date joinTime = format.parse(jsonParam.get("joinTime").toString());
        Date expirationTime = formatDate.parse(DateFormatUtils.format(format.parse((String) jsonParam.get("expirationTime")), "yyyy-MM-dd 23:59:59"));
//        member.setExpirationTime(formatDate.parse(endTime));
//        Date expirationTime = format.parse(jsonParam.get("expirationTime").toString());
        String carName = (String) jsonParam.get("carname");
        Integer storedMoney = (Integer) jsonParam.get("storedMoney");

        Member member = memberService.selectByPrimaryKey(id);
        member.setId(id);
        member.setJoinTime(joinTime);
        member.setExpirationTime(expirationTime);
        member.setState(formatDate.format(new Date()));
        if (carName != null) {
            String carName1 = member.getCarName();
            if (null == carName1 || "".equals(carName1)) {
                member.setCarName(carName); //次数
                memberService.updateByPrimaryKeySelective(member);
            } else {
                memberService.updateRenew(id, joinTime, expirationTime, carName);
            }
        } else if (storedMoney != null) {
            Integer storedMoney1 = member.getStoredMoney();
            if (null == storedMoney1) {
                member.setStoredMoney(storedMoney); //储值金额
                memberService.updateByPrimaryKeySelective(member);
            } else {
                memberService.updateRenewMoney(id, joinTime, expirationTime, storedMoney);
            }
        } else {
            memberService.updateByPrimaryKeySelective(member);
        }

        Member member1 = memberService.selectByPrimaryKey(id);
        JinshiMemberOrder jinshiMemberOrder = new JinshiMemberOrder();
        jinshiMemberOrder.setJmoMemberTableId(id);
        jinshiMemberOrder.setJmoMemberId(member1.getMemberId());
        jinshiMemberOrder.setJmoLincensePlate(member1.getLincensePlateId());
        jinshiMemberOrder.setJmoServiceType(member1.getServiceType());
//        if (null != carName) {
//            jinshiMemberOrder.setJmoServiceNumber(carName);
//        } else {
//            jinshiMemberOrder.setJmoServiceNumber(String.valueOf(number));
//        }
        jinshiMemberOrder.setJmoJoinTime(DateUtils.getDayStartTime(format.parse(jsonParam.get("joinTime").toString())));
        jinshiMemberOrder.setJmoExpirationTime(expirationTime);
        jinshiMemberOrder.setJmoCreatTime(new Date());
        jinshiMemberOrder.setJmoPayable(BigDecimal.valueOf((Integer) jsonParam.get("jmoPayable")));
        jinshiMemberOrder.setJmoActualCost(BigDecimal.valueOf((Integer) jsonParam.get("jmoActualCost")));
        jinshiMemberOrder.setJmoPhoneNumber(member1.getPhoneNumber());
        jinshiMemberOrder.setJmoAreaName(member1.getAreaName());
        jinshiMemberOrder.setJmoParkId(member1.getParkId());
        jinshiMemberOrder.setJmoAgentId(member1.getAgentId());
        jinshiMemberOrder.setJmoEnterUser(member1.getEnterUser());
        String orderIdByUUId = OrderIdForCCB.getOrderIdByUUId();
        jinshiMemberOrder.setJmoOrderId(orderIdByUUId);

        //判断当前车辆是否在场,若是临时车身份进场，购买月卡后修改状态为1，会员车出场
        List<LincensePlate> lincensePlateList = lincensePlateService.selectByLincensePlate(member1.getLincensePlateId());
        if (lincensePlateList.size() > 0) {
            LincensePlate lincensePlate = lincensePlateList.get(0);
            Integer lpLgType = lincensePlate.getLpLgType();
            if (lpLgType == 0) {
                lincensePlateService.updateType(lincensePlate.getLpLincensePlateIdCar());
            }
        }
        return jinshiMemberOrderService.insert(jinshiMemberOrder);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "车主档案---添加")
    public JSONObject insert(@RequestBody JSONObject jsonParam) throws ParseException {
        logger.info(jsonParam);
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat format2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String vipTime = "2099-12-31 23:59:59";

        String serviceType = (String) jsonParam.get("serviceType");
        Member member = new Member();
        member.setLincensePlateId((String) jsonParam.get("lincensePlateId"));
        member.setMemberId((String) jsonParam.get("memberId"));
        member.setServiceType(serviceType);
        member.setJoinTime(DateUtils.getDayStartTime(format1.parse((String) jsonParam.get("joinTime"))));
        member.setState(format2.format(new Date()));
        member.setCarType((String) jsonParam.get("cartype"));
        if ("贵宾车".equals(serviceType) || "内部车".equals(serviceType)) {
            member.setExpirationTime(DateUtils.getDayEndTime(format2.parse(vipTime)));
        } else {
            String endTime = DateFormatUtils.format(format1.parse((String) jsonParam.get("expirationTime")), "yyyy-MM-dd 23:59:59");
            member.setExpirationTime(format2.parse(endTime));
//            member.setExpirationTime(DateUtils.getDayEndTime(format1.parse((String) jsonParam.get("expirationTime"))));
        }
        member.setPhoneNumber(String.valueOf(jsonParam.get("phonenumber")));
        member.setCompanyName((String) jsonParam.get("companyname"));
        member.setDepartmentName((String) jsonParam.get("departmentname"));
        member.setMemberAddress((String) jsonParam.get("memberaddress")); //到期提醒
        member.setCompanyAddress((String) jsonParam.get("companyaddress"));
        if ("计次车".equals(serviceType)) {
            member.setCarName((String) jsonParam.get("carname")); //次数
        } else {
            member.setCarName(null); //次数
        }
        if ("储值车".equals(serviceType)) {
            member.setStoredMoney((Integer) jsonParam.get("storedMoney")); //储值金额
        } else {
            member.setStoredMoney(null); //储值金额
        }
        member.setCarColor((String) jsonParam.get("carcolor"));
        member.setAreaName((String) jsonParam.get("areaName"));
        member.setEnterUser((String) jsonParam.get("enterUser"));
        Integer lgId = (Integer) jsonParam.get("lgId");
        if (lgId != null) {
            JinshiLincenseGroup jinshiLincenseGroup = jinshiLincenseGroupService.selectByPrimaryKey(lgId);
            member.setLgId(Integer.valueOf(jinshiLincenseGroup.getJsNumber()));
        }
        String parkId = (String) jsonParam.get("parkId");
        Integer i = Integer.parseInt(parkId);
        member.setParkId(i);
        member.setAgentId(Integer.parseInt(String.valueOf(jsonParam.get("agentId"))));

        //添加会员订单信息
        JinshiMemberOrder jinshiMemberOrder = new JinshiMemberOrder();
        jinshiMemberOrder.setJmoMemberId((String) jsonParam.get("memberId"));
        jinshiMemberOrder.setJmoLincensePlate((String) jsonParam.get("lincensePlateId"));
        jinshiMemberOrder.setJmoServiceType((String) jsonParam.get("serviceType"));
//        jinshiMemberOrder.setJmoServiceNumber((String) jsonParam.get("carname"));
        jinshiMemberOrder.setJmoJoinTime(DateUtils.getDayStartTime(format1.parse((String) jsonParam.get("joinTime"))));
        if ("贵宾车".equals(serviceType) || "内部车".equals(serviceType)) {
            jinshiMemberOrder.setJmoExpirationTime(DateUtils.getDayEndTime(format2.parse(vipTime)));
        } else {
//            jinshiMemberOrder.setJmoExpirationTime(DateUtils.getDayEndTime(format1.parse((String) jsonParam.get("expirationTime"))));
            String endTime = DateFormatUtils.format(format1.parse((String) jsonParam.get("expirationTime")), "yyyy-MM-dd 23:59:59");
            jinshiMemberOrder.setJmoExpirationTime(format2.parse(endTime));
        }
        jinshiMemberOrder.setJmoCreatTime(new Date());
        jinshiMemberOrder.setJmoPayable(BigDecimal.valueOf((Integer) jsonParam.get("jmoPayable")));
        jinshiMemberOrder.setJmoActualCost(BigDecimal.valueOf((Integer) jsonParam.get("jmoActualCost")));
        jinshiMemberOrder.setJmoPhoneNumber((String)(jsonParam.get("phonenumber")));
        jinshiMemberOrder.setJmoAreaName((String) jsonParam.get("areaName"));
        jinshiMemberOrder.setJmoParkId(i);
        jinshiMemberOrder.setJmoAgentId(Integer.parseInt(String.valueOf(jsonParam.get("agentId"))));
        jinshiMemberOrder.setJmoEnterUser((String) jsonParam.get("enterUser"));
        String orderIdByUUId = OrderIdForCCB.getOrderIdByUUId();
        jinshiMemberOrder.setJmoOrderId(orderIdByUUId);

        JSONObject jsonObject = new JSONObject();
        Member checkMemberByLincese = memberService.checkMemberByLincese(member);
        if (checkMemberByLincese != null) {
            //说明已经有同车牌进此区域停车，提示为已重复
            jsonObject.put("code", 0);
            return jsonObject;
        } else {
            memberService.insert(member);
            jinshiMemberOrder.setJmoMemberTableId(member.getId());
            jinshiMemberOrderService.insert(jinshiMemberOrder);
            //判断当前车辆是否在场,若是临时车身份进场，购买月卡后修改状态为1，会员车出场
            List<LincensePlate> lincensePlateList = lincensePlateService.selectByLincensePlate((String) jsonParam.get("lincensePlateId"));
            if (lincensePlateList.size() > 0) {
                LincensePlate lincensePlate = lincensePlateList.get(0);
                Integer lpLgType = lincensePlate.getLpLgType();
                if (lpLgType == 0) {
                    lincensePlateService.updateType(lincensePlate.getLpLincensePlateIdCar());
                }
            }
            jsonObject.put("code", 1);
            return jsonObject;
        }
    }

    @RequestMapping("/insertSelective")
    public Integer insertSelective(Member record) {

        return memberService.insertSelective(record);
    }

    @RequestMapping("/selectByPrimaryKey")
    public Member selectByPrimaryKey(Integer id) {

        return memberService.selectByPrimaryKey(id);
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    public Integer updateByPrimaryKeySelective(Member record) {
        return memberService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping(value = "/selectMemberByCalendar",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String selectMemberByCalendar(@RequestBody JSONObject jsonParam){
        Integer pageNum = (Integer) jsonParam.get("pageNum");
         Integer pageSize = (Integer) jsonParam.get("pageSize");
        String parkIdStr = (String) jsonParam.get("parkId");
        Integer parkId = Integer.parseInt(parkIdStr);
        pageNum = (pageNum-1)*pageSize;
        String type = (String) jsonParam.get("type");
        List<Member> res = new ArrayList<>();
        int memberTotalRecords = 0;
        List<Integer> countList = new ArrayList<>();
        if ("1".equals(type)) {
            Date dayBegin = DateUtils.getDayBegin();
            Date dayEnd = DateUtils.getDayEnd();
            res = memberService.selectMemberByDay(dayBegin,dayEnd,pageNum,pageSize,parkId);
            memberTotalRecords = memberService.getMemberTotalRecords(parkId);
            countList.add(memberService.selectCountMemberByDay(dayBegin,dayEnd,parkId));
        } else if ("2".equals(type)) {
            Date beginDayOfWeek = DateUtils.getBeginDayOfWeek();
            Date endDayOfWeek = DateUtils.getEndDayOfWeek();
            res = memberService.selectMemberByWeek(beginDayOfWeek,endDayOfWeek,pageNum,pageSize,parkId);
            memberTotalRecords = memberService.getMemberTotalRecords(parkId);
            countList.add(memberService.selectCountMemberByWeek(beginDayOfWeek,endDayOfWeek,parkId));
        } else if ("3".equals(type)) {
            Date beginDayOfMonth = DateUtils.getBeginDayOfMonth();
            Date endDayOfMonth = DateUtils.getEndDayOfMonth();
            res = memberService.selectMemberByMonth(beginDayOfMonth,endDayOfMonth,pageNum,pageSize,parkId);
            memberTotalRecords = memberService.getMemberTotalRecords(parkId);
            countList.add(memberService.selectCountMemberByMonth(beginDayOfMonth,endDayOfMonth,parkId));
        } else if ("4".equals(type)) {
            Date beginDayOfYear = DateUtils.getBeginDayOfYear();
            Date endDayOfYear = DateUtils.getEndDayOfYear();
            res = memberService.selectMemberByYear(beginDayOfYear,endDayOfYear,pageNum,pageSize,parkId);
            memberTotalRecords = memberService.getMemberTotalRecords(parkId);
            countList.add(memberService.selectCountMemberByYear(beginDayOfYear,endDayOfYear,parkId));
        }
        JSONObject resJo = new JSONObject();
        resJo.put("memberData",res);
        resJo.put("memberCountList",countList);
        resJo.put("memberTotalRecords",memberTotalRecords);
        return resJo.toJSONString();
    }

    /**
     * 计次车修改次数
     * @param jsonParam
     * @return
     * @throws Exception
     */
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/updateCarNameForTimes", method = RequestMethod.POST)
    public Integer updateCarNameForTimes(@RequestBody JSONObject jsonParam){
        Integer id = (Integer) jsonParam.get("id");
        String lincensePlateId = (String) jsonParam.get("lincensePlateId");
        return memberService.updateCarNameForTimes(id,lincensePlateId);
    }

    /**
     * 储值车修改储值金额
     * @param jsonParam
     * @return
     * @throws Exception
     */
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/updateCarNameForMoney", method = RequestMethod.POST)
    public Integer updateCarNameForMoney(@RequestBody JSONObject jsonParam){
        Integer id = (Integer) jsonParam.get("id");
        String lincensePlateId = (String) jsonParam.get("lincensePlateId");
        Integer money = (Integer) jsonParam.get("storedMoney");
        return memberService.updateCarNameForMoney(id,lincensePlateId,money);
    }
}
