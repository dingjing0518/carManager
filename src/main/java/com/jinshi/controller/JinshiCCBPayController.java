package com.jinshi.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.io.IOException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinshi.bankPay.*;
import com.jinshi.entity.*;
import com.jinshi.mapper.LincensePlateMapper;
import com.jinshi.mapper.LincensePlatessMapper;
import com.jinshi.service.*;
import com.jinshi.util.*;
import io.swagger.annotations.Api;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.util.List;

@RequestMapping("/JinshiCCBPay")
@CrossOrigin
@Controller
@Api(tags = "建行支付")
public class JinshiCCBPayController {

    private static Logger logger = Logger.getLogger(JinshiCCBPayController.class.getName());

    @Autowired
    private JinshiCCBPayService jinshiCCBPayService;

    @Autowired
    private JinshiParkSettingService jinshiParkSettingService;

    @Autowired
    private LincensePlateService lincensePlateService;

    @Autowired
    private JinshiCameraService jinshiCameraService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private JinshiAreaService jinshiAreaService;

    @Autowired
    private JinshiMemberOrderService jinshiMemberOrderService;

    @Autowired
    private JinshiParkingService jinshiParkingService;

    @Autowired
    private LincensePlateMapper  lincensePlateMapper;

    /**
     * 调起支付
     * "orderId":57438561615511515125,
     * "payment":0.01
     * @return
     */
    @PostMapping("/CCBPay")
    @ResponseBody
    public SuccessVo CCBPay(@RequestBody JSONObject jsonObject) {
        JSONObject decode = jinshiCCBPayService.CCBPay(jsonObject);
        return new SuccessVo(decode);
    }

    /**
     * 车辆进场通知
     *
     * @return
     */
    @PostMapping("/carInNotice")
    @ResponseBody
    public Integer carInNotice(@RequestBody JSONObject jsonParam) {
        logger.info("车辆进场通知--------建行支付--------" + jsonParam.toJSONString());
        JinshiCCBPay jinshiCCBPay = JSONObject.parseObject(jsonParam.toJSONString(), JinshiCCBPay.class);
        return jinshiCCBPayService.insert(jinshiCCBPay);
    }

    /**
     * 车辆出场通知
     *
     * @return
     */
    @PostMapping("/carOutNotice")
    @ResponseBody
    public Integer carOutNotice(@RequestBody JSONObject jsonParam) {
        logger.info("车辆出场通知--------建行支付--------" + jsonParam.toJSONString());
        JinshiCCBPay jinshiCCBPay = JSONObject.parseObject(jsonParam.toJSONString(), JinshiCCBPay.class);
        return jinshiCCBPayService.updateCarOut(jinshiCCBPay);
    }

    /**
     * 根据车牌号和车场id查询订单
     * @return
     */
    @PostMapping("/selectCCBPayByPlateAndParkId")
    @ResponseBody
    public List<JinshiCCBPay> selectCCBPayByPlateAndParkId(@RequestBody JSONObject jsonParam) {
        String carNumber = (String) jsonParam.get("ccbCarNumber");
        Integer parkId = (Integer) jsonParam.get("ccbParkId");
        return jinshiCCBPayService.selectCCBPayByPlateAndParkId(carNumber, parkId);
    }

    /**
     * 根据车牌号查询订单修改订单号
     * @return
     */
    @PostMapping("/updateOrderId")
    @ResponseBody
    public Integer updateOrderId(String car_number,String uuid) {
        List<JinshiCCBPay> jinshiCCBPayList = jinshiCCBPayService.selectPlate(car_number);
        if (jinshiCCBPayList.size() > 0) {
            JinshiCCBPay jinshiCCBPay = jinshiCCBPayList.get(0);
            jinshiCCBPay.setCcbOrderId(uuid);
            jinshiCCBPayService.updateOrderId(jinshiCCBPay);
        }
        return 1;
    }

    /**
     * 根据订单号查询订单
     *
     * @return
     */
    @PostMapping("/selectCCBPayByOrderId")
    @ResponseBody
    public Map<String, Object> selectCCBPayByOrderId(@RequestBody JSONObject jsonParam) {
        String orderId = (String) jsonParam.get("orderId");
        return jinshiCCBPayService.selectByOrderId(orderId);
    }

    /**
     * 查询订单实时金额通知(车场，出入口)(临时车扫码支付)
     * @param jsonParam
     * @return
     */
    @PostMapping("/checkOrderRent")
    @ResponseBody
    public JSONObject checkOrderRent(@RequestBody JSONObject jsonParam) {
        logger.info("checkOrderRent ======================建行支付=======================");
        logger.info("扫码后携带参数：" + jsonParam);
        Integer parkId = (Integer) jsonParam.get("ccbParkId");
        String jcName = (String) jsonParam.get("jcName");
        List<LincensePlate> list = new ArrayList<>();
        LincensePlate lp = new LincensePlate();
        LincensePlate plate = new LincensePlate();
        JinshiCameras jinshiCameras = jinshiCameraService.selectByParkIdAndJcName(parkId,jcName);
        logger.info("扫码后获取的摄像机信息：" + JSONObject.toJSON(jinshiCameras));
        String handleByName = jinshiCameras.getJcThandle();
        plate.setLpDepartureCname(handleByName);
        plate.setLpParkingName(String.valueOf(parkId));
        list = lincensePlateMapper.selectByParkIdAndstatus(plate);
        JSONObject json = new JSONObject();
        if (list.size() > 0) {
            lp = list.get(0);
        } else {
            json.put("msg", "此出口未查询到车辆");
            logger.info("此出口:  " + jinshiCameras.getJcName() + " 未查询到车辆");
            return json;
        }
        logger.info("lp开始查询到的车辆：" + JSONObject.toJSON(lp));
        if ("支付成功".equals(lp.getLpPaymentType())) { //预付款订单
            LincensePlate lincensePlate = new LincensePlate();
            lincensePlate.setLpLincensePlateIdCar(lp.getLpLincensePlateIdCar());
            List<LincensePlate> lincensePlates = lincensePlateService.selectByLincense(lincensePlate);
            if (lincensePlates.size() > 0) {
                lp = lincensePlates.get(0);
                logger.info("lp预付款查询到的车辆：" + JSONObject.toJSON(lp));
            }
        } else {
            //临时车待支付
            LincensePlate lincensePlate = new LincensePlate();
            lincensePlate.setLpOrderState("待支付");
            lincensePlate.setLpDepartureCname(handleByName);
            lincensePlate.setLpParkingName(String.valueOf(parkId));
            List<LincensePlate> listLP = lincensePlateService.selectByOrderStatAndCname(lincensePlate);
            if (listLP.size() > 0) {
                lp = listLP.get(0);
                logger.info("lp临时车待支付查询到的车辆：" + JSONObject.toJSON(lp));
            }
        }
        if (lp == null) {
            json.put("msg", "此出口未查询到车辆");
            logger.info("此出口:  " + jinshiCameras.getJcName() + " 未查询到车辆");
            return json;
        }
        String rent = lp.getLpParkingCost();
        if ("0.0".equals(rent)) {
            json.put("msg", "未查询到记录，请重新拍照识别");
            logger.info("车牌为：" + lp.getLpLincensePlateIdCar() + "未查询到记录，请重新拍照识别---" + "rent: " + rent);
            logger.info("车牌为：" + lp.getLpLincensePlateIdCar() + " 未查询到记录，请重新拍照识别 " + "lincense: " + JSONObject.toJSON(lp));
            return json;
        }
        String orderIdByUUId = OrderIdForCCB.getOrderIdByUUId(lp.getLpLincensePlateIdCar(),lp.getLpParkingName());
        plate.setLpOrderId(orderIdByUUId);
        plate.setLpId(lp.getLpId());
        lincensePlateMapper.updateByPrimaryKeySelective(plate);
        logger.info("lp最后准备支付的车辆信息：" + JSONObject.toJSON(lp));
        String s = DateUtils.afterSixSecond(new Date());//获取当前时间6分半之后的时间，作为支付超时时间
        json.put("orderId", orderIdByUUId);
        json.put("payment", rent);
        json.put("TIMEOUT",s);
        logger.info("车牌号:  " + lp.getLpLincensePlateIdCar() + "准备调起支付");
        logger.info("orderId:  " + orderIdByUUId);
        logger.info("payment:  " + rent);
        logger.info("TIMEOUT:  " + s);
        return jinshiCCBPayService.CCBPay(json);
    }

    /**
     * 支付回调(页面反馈 get)付款人付款完成后，点击“返回商户网站”按钮，触发页面反馈。
     * @return
     */
    @GetMapping("/payCallBackForPage")
    public void payCallBackForPage(PayCallBackEntity payCallBackEntity,
                                   HttpServletResponse response) throws Exception {
        logger.info("支付回调(页面反馈 get) start  ====================建行支付=====================");
        logger.info("payCallBackEntity:  " + payCallBackEntity);
        String success = payCallBackEntity.getSUCCESS();
        String orderId = payCallBackEntity.getORDERID();
        String payment = payCallBackEntity.getPAYMENT();
        logger.info("success:  " + success);
        logger.info("orderId:  " + orderId);
        if ("Y".equals(success)) {
            Map<String, Object> map = jinshiCCBPayService.selectByOrderId(orderId);
            logger.info("支付回调页面反馈查询到的订单信息:  " + map);
            Object returnCode = map.get("returnCode");
            Object returnMsg = map.get("returnMsg");
            logger.info("returnCode:  " + returnCode);
            logger.info("returnMsg:  " + returnMsg);
            if ("000000".equals(returnCode)) {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //说明此订单号已支付成功
                Thread.sleep(500);
                JinshiCCBPay jinshiCCBPay = jinshiCCBPayService.selectOrder(orderId);
                String ccbInTime = null;
                String ccbOutTime = null;
                if (jinshiCCBPay != null) {
                    logger.info("展示支付成功页面的车辆信息：" + JSONObject.toJSON(jinshiCCBPay));
                    String ccbCarNumber = jinshiCCBPay.getCcbCarNumber();
                    Date ccbInTime1 = jinshiCCBPay.getCcbInTime();
                    if (ccbInTime1 != null) {
                        ccbInTime = format.format(ccbInTime1);
                    }
                    Date ccbOutTime1 = jinshiCCBPay.getCcbOutTime();
                    if (ccbOutTime1 != null) {
                        ccbOutTime = format.format(ccbOutTime1);
                    }
                    String ccbOften = jinshiCCBPay.getCcbOften();
                    logger.info("车牌号：" + ccbCarNumber + " 支付回调(页面反馈 get)-订单支付成功：" + jinshiCCBPay.getCcbPayType());
                    response.sendRedirect("http://www.jinshipark.com/pages/success.html?ccbCarNumber=" + URLEncoder.encode(ccbCarNumber, "UTF-8") + "&ccbInTime=" + ccbInTime + "&ccbOutTime=" + ccbOutTime + "&ccbOften=" + ccbOften + "&orderId=" + orderId + "&payment=" + payment);
                    logger.info("执行支付成功页面跳转(页面反馈 get)");
                } else {
                    logger.info("(页面反馈 get)：未查询到订单信息-----" + orderId);
                }
            }
        } else {
            logger.info("支付回调(页面反馈 get):     订单支付失败 ===========");
            response.sendRedirect("http://www.jinshipark.com/pages/fail.html");
            logger.info("执行支付失败页面跳转(页面反馈 get)");
        }
    }

    /**
     * 支付回调(服务器反馈 post)只要支付成功，无需触发，由建行支付网关，以post 方法，发信息给反馈URL
     * @return
     */
    @PostMapping("/payCallBackForServer")
    @ResponseBody
    public void payCallBackForServer(PayCallBackEntity payCallBackEntity) throws Exception {
        logger.info("支付回调(服务器反馈 post) start  ====================建行支付=====================");
        logger.info("payCallBackEntity:  " + payCallBackEntity);
        String success = payCallBackEntity.getSUCCESS();
        String orderId = payCallBackEntity.getORDERID();
        String payment = payCallBackEntity.getPAYMENT();
        logger.info("success:  " + success);
        logger.info("orderId:  " + orderId);
        logger.info("payment:  " + payment);
        if ("Y".equals(success)) {
            Map<String, Object> map = jinshiCCBPayService.selectByOrderId(orderId);
            logger.info("支付回调服务器反馈查询到的订单信息:  " + map);
            Object returnCode = map.get("returnCode");
            Object returnMsg = map.get("returnMsg");
            logger.info("returnCode:  " + returnCode);
            logger.info("returnMsg:  " + returnMsg);
            if ("000000".equals(returnCode)) {
                //说明此订单号已支付成功
                //停车缴费订单 / 预付款订单
                String parkName = orderId.substring(14, 19);
                String carNumber = orderId.substring(orderId.length() - 6);
                String parkId = "";
                if (parkName.equals("00001")){
                    parkId="1";
                }
                if (parkName.equals("00003")){
                    parkId="3";
                }
                if (parkName.equals("00004")){
                    parkId="27";
                }
                if (parkName.equals("00005")){
                    parkId="28";
                }
                LincensePlate byorderId = lincensePlateMapper.findByparkIdAndcarNumber(carNumber,parkId);
                if (byorderId!=null){
                    byorderId.setLpOrderState("支付成功");
                    byorderId.setLpParkingRealCost(payment);
                    lincensePlateService.updateByPlateByOrderId(byorderId);
                    logger.info("车牌号：" + byorderId.getLpLincensePlateIdCar() + ":     订单支付成功 ===========");
                    logger.info("订单支付成功的信息：" + JSONObject.toJSON(byorderId));
                    logger.info("支付回调(服务器反馈 post):     订单支付成功 ===========");
                }
            /*    String substring = orderId.substring(orderId.length() - 6, orderId.length());
                String subPlate = substring.replace("I", "");
                logger.info("截取到的车牌号:  " + subPlate);
                List<LincensePlate> lincensePlate = lincensePlateService.selectBlurryByPlate(subPlate);
                if (lincensePlate.size() > 0) {
                    for (LincensePlate plate : lincensePlate) {
                        plate.setLpOrderState("支付成功");
                        plate.setLpParkingRealCost(payment);
                        plate.setLpOrderId(orderId);
                        lincensePlateService.updateByPlateByOrderId(plate);
                        logger.info("车牌号：" + plate.getLpLincensePlateIdCar() + ":     订单支付成功 ===========");
                        logger.info("订单支付成功的信息：" + JSONObject.toJSON(plate));
                    }
                    logger.info("支付回调(服务器反馈 post):     订单支付成功 ===========");
                }*/
                //公众号购买月卡订单
                JinshiMemberOrder jinshiMemberOrder = jinshiMemberOrderService.selectByOrderId(orderId);
                if (jinshiMemberOrder != null) {
                    //修改会员信息
                    jinshiMemberOrder.setJmoActualCost(BigDecimal.valueOf(Long.parseLong(payment)));
                    payMemberOrderCallBack(jinshiMemberOrder);
                    logger.info("车牌号：" + jinshiMemberOrder.getJmoLincensePlate() + ":     购买月卡订单支付成功 ===========");
                    logger.info("支付回调(服务器反馈 post):     购买月卡订单支付成功 ===========");
                }
            }
        } else {
            logger.info("支付回调(服务器反馈 post):     订单支付失败 ===========");
        }
    }

    /**
     * 支付月卡费用
     * @return
     */
    @GetMapping("/payMemberOrder")
    @ResponseBody
    @CrossOrigin
    public JSONObject payMemberOrder(@RequestParam("car_number") String car_number,
                                     @RequestParam("type") String type,
                                     @RequestParam("parkId") String parkId,
                                     @RequestParam("area") String area,
                                     @RequestParam("name") String name,
                                     @RequestParam("phone") String phone) {
        logger.info("payMemberOrder公众号支付月卡 Start==============建行支付===================");
        Integer amount = null;
        String num = "";
        String orderIdByUUId = OrderIdForCCB.getOrderIdByUUIdAddPlate(car_number);
        JinshiParking jinshiParking = jinshiParkingService.selectByJpId(Integer.valueOf(parkId));
        JinshiArea jinshiArea = jinshiAreaService.selectByJcArea(area, Integer.valueOf(parkId));
        //调用支付
        JSONObject dataOb = new JSONObject();
        if (type.equals("1")) { //包月
            amount = jinshiArea.getPayMonth();
            num = "0";
        } else if (type.equals("2")){ //包季
            amount = jinshiArea.getPayQuarter();
            num = "1";
        } else if (type.equals("3")){ //包年
            amount = jinshiArea.getPayYear();
            num = "2";
        }
        //支付时先添加支付订单，支付成功后完善信息
        JinshiMemberOrder jinshiMemberOrder = new JinshiMemberOrder();
        jinshiMemberOrder.setJmoLincensePlate(car_number);
        jinshiMemberOrder.setJmoMemberId(name);
        jinshiMemberOrder.setJmoServiceType("月租车");
        jinshiMemberOrder.setJmoServiceNumber(num); // 0 表示一个月  1表示 一年
        jinshiMemberOrder.setJmoJoinTime(DateUtils.getDayBegin());
        jinshiMemberOrder.setJmoCreatTime(new Date());
        jinshiMemberOrder.setJmoPayable(BigDecimal.valueOf(amount));
        jinshiMemberOrder.setJmoPhoneNumber(phone);
        jinshiMemberOrder.setJmoAreaName(area);
        jinshiMemberOrder.setJmoParkId(Integer.valueOf(parkId));
        jinshiMemberOrder.setJmoAgentId(jinshiParking.getJpAgentId());
        jinshiMemberOrder.setJmoEnterUser("待支付");
        jinshiMemberOrder.setJmoOrderId(orderIdByUUId);
        jinshiMemberOrderService.insert(jinshiMemberOrder);
        String s = DateUtils.afterSixSecond(new Date());//获取当前时间6分半之后的时间，作为支付超时时间
        dataOb.put("TIMEOUT",s);
        dataOb.put("payment", amount);
        dataOb.put("orderId", orderIdByUUId);
        return jinshiCCBPayService.CCBPay(dataOb);
    }

    /**
     * 支付月卡费用回调
     * @throws ParseException
     */
//    @PostMapping("/payMemberOrderCallBack")
//    @ResponseBody
    public String payMemberOrderCallBack(JinshiMemberOrder jinshiMemberOrder) throws Exception {
        logger.info("payMemberOrderCallBack Start================建行支付=======================");
        String car_number = jinshiMemberOrder.getJmoLincensePlate();
        String jmoServiceNumber = jinshiMemberOrder.getJmoServiceNumber();
        Integer type = null;
        if ("0".equals(jmoServiceNumber)) {
            type = 1; //月卡
        } else if ("1".equals(jmoServiceNumber)) {
            type = 2; //季卡
        } else if ("2".equals(jmoServiceNumber)) {
            type = 3; //年卡
        }
        //修改会员信息
        String jmoOrderId = jinshiMemberOrder.getJmoOrderId();
        BigDecimal jmoActualCost = jinshiMemberOrder.getJmoActualCost();
        JinshiMemberOrder jinshiMemberOrder1 = jinshiMemberOrderService.selectByOrderId(jmoOrderId);
        jinshiMemberOrder1.setJmoActualCost(jmoActualCost);
        updateMember(String.valueOf(type),jinshiMemberOrder1);
        //判断当前车辆是否在场,若是临时车身份进场，购买月卡后修改状态为1，会员车出场
        List<LincensePlate> lincensePlateList = lincensePlateService.selectByLincensePlate(car_number);
        if (lincensePlateList.size() > 0) {
            LincensePlate lincensePlate = lincensePlateList.get(0);
            Integer lpLgType = lincensePlate.getLpLgType();
            if (lpLgType == 0) {
                lincensePlateService.updateType(lincensePlate.getLpLincensePlateIdCar());
            }
        }
        JSONObject resJO = new JSONObject();
        resJO.put("state", 1);
        resJO.put("jinshiMemberOrder", jinshiMemberOrder);
        return resJO.toJSONString();
    }

    /**
     * 预付款出场
     * @param jsonParam
     * @return
     */
    @PostMapping("/payAdvanceCode")
    @ResponseBody
    @CrossOrigin
    public JSONObject payAdvanceCode(@RequestBody JSONObject jsonParam) throws Exception{
        logger.info("payAdvanceCode getParam Start=====================建行支付==================");
        Integer pac_parkId;
        String car_number = (String) jsonParam.get("car_number");
        List<LincensePlate> lincensePlateList = lincensePlateService.selectByLincensePlate(car_number);
        LincensePlate lincense = new LincensePlate();
        JSONObject json = new JSONObject();
        if (lincensePlateList.size() > 0) {
            //查询会员表，确定会员属性，返回不同内容
            LincensePlate lp = lincensePlateList.get(0);
            logger.info("lp准备预付款出场的车辆信息：" + JSONObject.toJSON(lp));
            pac_parkId = Integer.valueOf(lp.getLpParkingName());
            Integer lpLgId = lp.getLpLgId();
            if (lpLgId==1){
                json.put("msg","尊敬的用户，车牌号：" + car_number + "为本场会员，无需扫码请直接出场");
                return json;
            }
           /* Member member = new Member();
            member.setLincensePlateId(car_number);
            member.setAreaName(lp.getLpCarType());
            member.setParkId(Integer.valueOf(lp.getLpParkingName()));

            List<Member> members = memberService.checkISMember(member);
            List<Member> members1 = memberService.checkISAllAreaMember(member);
            List<Member> memberList = new ArrayList<>();
            if(members1.size() > 0){
                CollectionUtils.addAll(memberList, new Object[members1.size()]);
                Collections.copy(memberList, members1);
            }else if(members.size() > 0){
                CollectionUtils.addAll(memberList, new Object[members.size()]);
                Collections.copy(memberList, members);
            }
            if (memberList != null && memberList.size() > 0) {//是否是会员信息
                Member member1 = memberList.get(0);
                String serviceType = member1.getServiceType();
                Date today = new Date();
                Date expirationTime = member1.getExpirationTime();
                int i = today.compareTo(expirationTime); //判断当前日期是否在有效期内
                Integer memberDays = 0;
                memberDays = DateUtils.daysBetween(new Date(),member1.getExpirationTime());//获取会员有效时间
                if ("计次车".equals(serviceType)) {
                    if (Integer.valueOf(member1.getCarName()) >= 1 && (i == -1 || i == 0)) {
                        json.put("msg","尊敬的用户，车牌号：" + car_number + "为本场计次车会员，无需扫码请直接出场");
                        return json;
                    }
                }else if (memberDays > 0) {
                    json.put("msg","尊敬的用户，车牌号：" + car_number + "为本场会员，无需扫码请直接出场");
                    return json;
                }
            }*/
            BeanUtils.copyProperties(lp,lincense);
            Integer dateOften = getDateOften(new Date(), lincense.getLpInboundTime());
            String lpParkingName = lincense.getLpParkingName();
            String lpLincenseType = lincense.getLpLincenseType();
            JinshiParkSetting jinshiParkSetting = new JinshiParkSetting();
            if (lpLincenseType == null || "1".equals(lpLincenseType)) {
                jinshiParkSetting = jinshiParkSettingService.selectByCarTypeAndParkId(Integer.valueOf(lpParkingName), 0);
            } else if ("11".equals(lpLincenseType) || "2".equals(lpLincenseType)) {
                jinshiParkSetting = jinshiParkSettingService.selectByCarTypeAndParkId(Integer.valueOf(lpParkingName), 2);
            } else if ("3".equals(lpLincenseType)) {
                jinshiParkSetting = jinshiParkSettingService.selectByCarTypeAndParkId(Integer.valueOf(lpParkingName), 1);
            } else if ("5".equals(lpLincenseType)) {
                jinshiParkSetting = jinshiParkSettingService.selectByCarTypeAndParkId(Integer.valueOf(lpParkingName), 3);
            }
            if (dateOften <= jinshiParkSetting.getJpsFreeTime()) {
                json.put("msg", "车牌为：" + car_number + " 的用户您好，您还在免费时长之内哦~");
                logger.info("车牌为：" + car_number + " 还在免费时长之内哦~");
                return json;
            }
            logger.info("预付款的车辆信息：" + JSONObject.toJSON(lincense));
        } else {
            json.put("msg", "未查询到 " + car_number + " 在场车辆");
            logger.info("预付码未查询到 " + car_number + " 在场车辆");
            return json;
        }
        if ("5".equals(lincense.getLpLincenseType())) {
            json.put("msg", "车牌号：" + car_number + " 为特种车辆，无需付款可直接出场");
            logger.info("车牌号：" + car_number + " 为特种车辆，无需付款可直接出场");
            return json;
        }
        Date tempDate = new Date();
        Double rent = getRent(tempDate, lincense.getLpInboundTime(),lincense.getLpLincenseType(), Integer.valueOf(lincense.getLpParkingName()));
        if (rent == 0.0) {
            logger.info("测试信息！！！！-------- new Date： "+ tempDate);
            logger.info("测试信息！！！！-------- 入场时间： "+ lincense.getLpInboundTime());
            json.put("msg", "系统错误，请到出口扫码支付");
            logger.info("车牌为：" + car_number + "系统错误，请到出口扫码支付---" + "rent: " + rent);
            logger.info("车牌为：" + car_number + " 出现系统错误 " + "lincense: " + JSONObject.toJSON(lincense));
            return json;
        }
        //每次重新生成支付需要的订单号，更新数据库
        String orderIdByUUId = OrderIdForCCB.getOrderIdByUUIdAddPlate(lincense.getLpLincensePlateIdCar(),pac_parkId);
        logger.info("车牌号：" + lincense.getLpLincensePlateIdCar() + " 的预付款支付订单号为：" + orderIdByUUId);
        lincense.setLpOrderId(orderIdByUUId);
        lincense.setLpPaymentType("预付款请求出场");
        lincense.setLpParkingCost(rent.toString());
        lincensePlateService.updateByPlateForAdvance(lincense);//修改在场表订单号
        updateOrderId(car_number,orderIdByUUId);//修改CCB表订单号
        String s = DateUtils.afterSixSecond(new Date()); //获取当前时间6分半之后的时间，作为支付超时时间
        json.put("TIMEOUT",s);
        json.put("orderId", orderIdByUUId);
        json.put("payment", rent);
        logger.info(car_number + " 预付款调起支付");
        logger.info("预付款订单号：" + orderIdByUUId);
        logger.info("预付款费用：" + rent);
        logger.info("预付款超时时间：" + s);
        return jinshiCCBPayService.CCBPay(json);
    }

    public Integer updateMember(String type,JinshiMemberOrder memberOrder) throws ParseException {
        Member member = new Member();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        BigDecimal pay = memberOrder.getJmoActualCost();
        Integer parkId = memberOrder.getJmoParkId();
        Integer agentId = memberOrder.getJmoAgentId();
        String areaName = memberOrder.getJmoAreaName();
        String Lincese = memberOrder.getJmoLincensePlate();
        String orderId = memberOrder.getJmoOrderId();
        String name = memberOrder.getJmoMemberId();
        String phone = memberOrder.getJmoPhoneNumber();
        member.setLincensePlateId(Lincese);
        member.setAreaName(areaName);
        Member resMember = memberService.checkMemberByLincese(member);
        JinshiArea jinshiArea = jinshiAreaService.selectByJcArea(areaName, parkId);
        if (resMember != null) {
            JinshiMemberOrder jinshiMemberOrder = new JinshiMemberOrder();
            if (type.equals("1")) { //月卡
                gregorianCalendar.setTime(resMember.getExpirationTime());
                gregorianCalendar.add(gregorianCalendar.DATE, 30);
                Date time = gregorianCalendar.getTime();
                Date expirationTime = format.parse(DateFormatUtils.format(time, "yyyy-MM-dd 23:59:59"));
                resMember.setExpirationTime(expirationTime);
                //添加订单信息
                jinshiMemberOrder.setJmoExpirationTime(expirationTime);
                jinshiMemberOrder.setJmoPayable(BigDecimal.valueOf(jinshiArea.getPayMonth()));
            } else if (type.equals("2")) { //季卡
                gregorianCalendar.setTime(resMember.getExpirationTime());
                gregorianCalendar.add(gregorianCalendar.MONTH, 3);
                Date time = gregorianCalendar.getTime();
                Date expirationTime = format.parse(DateFormatUtils.format(time, "yyyy-MM-dd 23:59:59"));
                resMember.setExpirationTime(expirationTime);
                //添加订单信息
                jinshiMemberOrder.setJmoExpirationTime(expirationTime);
                jinshiMemberOrder.setJmoPayable(BigDecimal.valueOf(jinshiArea.getPayQuarter()));
            } else if (type.equals("3")) { //年卡
                gregorianCalendar.setTime(resMember.getExpirationTime());
                gregorianCalendar.add(gregorianCalendar.YEAR, 1);
                Date time = gregorianCalendar.getTime();
                Date expirationTime = format.parse(DateFormatUtils.format(time, "yyyy-MM-dd 23:59:59"));
                resMember.setExpirationTime(expirationTime);
                //添加订单信息
                jinshiMemberOrder.setJmoExpirationTime(expirationTime);
                jinshiMemberOrder.setJmoPayable(BigDecimal.valueOf(jinshiArea.getPayYear()));
            }
            resMember.setMemberId(name);
            resMember.setPhoneNumber(phone);
            jinshiMemberOrder.setJmoMemberTableId(resMember.getId());
            jinshiMemberOrder.setJmoOrderId(orderId);
            jinshiMemberOrder.setJmoActualCost(pay);
            jinshiMemberOrder.setJmoEnterUser("公众号支付成功");
            memberService.updateByPrimaryKey(resMember);
            jinshiMemberOrderService.updateByOrderId(jinshiMemberOrder);
        } else {
            JinshiMemberOrder jinshiMemberOrder = new JinshiMemberOrder();
            if (type.equals("1")) {
                gregorianCalendar.setTime(new Date());
                gregorianCalendar.add(gregorianCalendar.DATE, 30);
                Date time = gregorianCalendar.getTime();
                Date expirationTime = format.parse(DateFormatUtils.format(time, "yyyy-MM-dd 23:59:59"));
                member.setExpirationTime(expirationTime);
                //添加订单信息
                jinshiMemberOrder.setJmoExpirationTime(expirationTime);
                jinshiMemberOrder.setJmoPayable(BigDecimal.valueOf(jinshiArea.getPayMonth()));
            } else if (type.equals("2")) {
                gregorianCalendar.setTime(new Date());
                gregorianCalendar.add(gregorianCalendar.MONTH, 3);
                Date time = gregorianCalendar.getTime();
                Date expirationTime = format.parse(DateFormatUtils.format(time, "yyyy-MM-dd 23:59:59"));
                member.setExpirationTime(expirationTime);
                //添加订单信息
                jinshiMemberOrder.setJmoExpirationTime(expirationTime);
                jinshiMemberOrder.setJmoPayable(BigDecimal.valueOf(jinshiArea.getPayQuarter()));
            } else if (type.equals("3")) {
                gregorianCalendar.setTime(new Date());
                gregorianCalendar.add(gregorianCalendar.YEAR, 1);
                Date time = gregorianCalendar.getTime();
                Date expirationTime = format.parse(DateFormatUtils.format(time, "yyyy-MM-dd 23:59:59"));
                member.setExpirationTime(expirationTime);
                //添加订单信息
                jinshiMemberOrder.setJmoExpirationTime(expirationTime);
                jinshiMemberOrder.setJmoPayable(BigDecimal.valueOf(jinshiArea.getPayYear()));
            }
            member.setJoinTime(DateUtils.getDayBegin());
            member.setServiceType("月租车");
            member.setState(format.format(new Date()));
            member.setMemberId(name);
            member.setPhoneNumber(phone);
            member.setAreaName(areaName);
            member.setMemberAddress("15");
            member.setParkId(parkId);
            member.setAgentId(agentId);
            member.setEnterUser("公众号添加");
            memberService.insert(member);
            jinshiMemberOrder.setJmoOrderId(orderId);
            jinshiMemberOrder.setJmoActualCost(pay);
            jinshiMemberOrder.setJmoEnterUser("公众号支付成功");
            jinshiMemberOrder.setJmoMemberTableId(member.getId());
            jinshiMemberOrderService.updateByOrderId(jinshiMemberOrder);
        }
        return 0;
    }

    public Double getRent(Date endDate, Date nowDate,String plateType,Integer parkId) {
        if (endDate == null) {
            return 0.0;
        }

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        //总分钟数
        long allMin = diff / nm;
//        if(v==0.0){
//            return GlobalVariable.blueLincenseFollowCharge+GlobalVariable.blueLincenseFirstCharge;
//        }
//        return day + "天" + hour + "小时" + min + "分钟";
        //test
//        GlobalVariable.blueLicenseFreeTime = 30;
//        GlobalVariable.blueLicenseFirstTime = 60;
//        GlobalVariable.blueLincenseFirstCharge = 6.0;
//        GlobalVariable.blueLincenseFollowTime = 30;
//        GlobalVariable.blueLincenseFollowCharge = 3.0;
//        GlobalVariable.blueLincenseAllDayLimit = 30.0;
        //test end
        double allDayMin = 1440;
        double dayCharge = 0;
        double res = 0;
        double spareMin = allMin % allDayMin;

        List<JinshiParkSetting> jinshiParkSetting = jinshiParkSettingService.selectByParkKey(parkId);
        if(jinshiParkSetting!=null){
            logger.info("建行支付预付款获取费用信息-------------------------");
            for (JinshiParkSetting parkSetting : jinshiParkSetting) {
                logger.info(JSONObject.toJSON(parkSetting));
                if ((1 == Integer.valueOf(plateType) && parkSetting.getJpsCarType() == 0)   //汽油车--小型车
                    || ((11 == Integer.valueOf(plateType) || 2 == Integer.valueOf(plateType)) && parkSetting.getJpsCarType() == 2) //能源车(能源车 + 蓝牌能源车)
                    || (3 == Integer.valueOf(plateType) && parkSetting.getJpsCarType() == 1)    //黄牌车(中型车(黄牌) + 大型车(黄牌))
                    || (5 == Integer.valueOf(plateType) && parkSetting.getJpsCarType() == 3)) {     //特种车
                    if(allMin>allDayMin){
                        dayCharge = Math.floor(allMin/allDayMin)*parkSetting.getJpsAlldayLimit();
                    }
                    if(spareMin<parkSetting.getJpsFreeTime()){
                        return dayCharge;
                    }
                    if(spareMin<=parkSetting.getJpsFirstTime()){
                        return dayCharge+Double.valueOf(parkSetting.getJpsFirstCharge());
                    }else{
                        double sumTemp = Math.floor((spareMin - parkSetting.getJpsFirstTime()) / parkSetting.getJpsFollowTime())+1; //sumTemp是否能够取整数
                        double nowCharge = sumTemp*parkSetting.getJpsFollowCharge() + parkSetting.getJpsFirstCharge();
                        if (nowCharge>parkSetting.getJpsAlldayLimit()) {nowCharge=parkSetting.getJpsAlldayLimit();}   //当天费用大于20时取20
                        return dayCharge+nowCharge;//之前天数的费用加上当天费用
                    }
                }
            }
        }
        return 0.0;
    }
    public static Integer getDateOften(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;

        long resMin = diff / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return Math.toIntExact(resMin);
    }

    public static void main(String[] args) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date parse1 = sdf.parse("2020-4-27 16:00:00");
//        Date parse2 = sdf.parse("2020-4-27 17:01:00");
//        JinshiCCBPayController jinshiCCBPayController = new JinshiCCBPayController();
//        Double rent = jinshiCCBPayController.getRent(parse2, parse1,"1");
//        System.out.println(rent);
    }
}
