package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.*;
import com.jinshi.mapper.LincensePlateMapper;
import com.jinshi.service.*;
import com.jinshi.service.HttpClient;
import com.jinshi.util.*;
import io.swagger.annotations.Api;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/payTest")
@CrossOrigin
@Api(tags = "泊链支付")
public class PayTestController {

    private static Logger logger = Logger.getLogger(PayTestController.class.getName());

    @Autowired
    HttpClient httpClient;
    @Autowired
    private LincensePlateService lincensePlateService;
    @Autowired
    private JinshiParkSettingService jinshiParkSettingService;
    @Autowired
    private JinshiCameraService jinshiCameraService;
    @Autowired
    private MemberService memberService;

    @Autowired
    private JinshiCCBPayService jinshiCCBPayService;

    @Autowired
    private JinshiCCBPayController jinshiCCBPayController;

    @Autowired
    private JinshiParkingService jinshiParkingService;

    @Autowired
    private JinshiAreaService jinshiAreaService;

    @Autowired
    private JinshiMemberOrderService jinshiMemberOrderService;

    @Autowired
    private LincensePlateMapper lincensePlateMapper;

//    private String union_id = "200474";
//    private String requestUrl = "https://beta.bolink.club/unionapi/getqrcodecloud";
//    private String key = "EC6B6BE71DBE76D6";


    public static void main(String[] args) throws ParseException {

//        Integer integer = new PayTestController().payMemberOrder();
    }

    public Integer updateMember(String Lincese, String areaName,String type,String name,String phone) throws ParseException{
        Member member = new Member();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        member.setLincensePlateId(Lincese);
        member.setAreaName(areaName);
        Member resMember = memberService.checkMemberByLincese(member);
        if(resMember!=null){
            if(type.equals("1")){
                gregorianCalendar.setTime(resMember.getExpirationTime());
                gregorianCalendar.add(gregorianCalendar.DATE,30);
                Date time = gregorianCalendar.getTime();
                resMember.setExpirationTime(time);
                resMember.setMemberId(name);
                resMember.setPhoneNumber(phone);
            }else if(type.equals("2")){
                gregorianCalendar.setTime(resMember.getExpirationTime());
                gregorianCalendar.add(gregorianCalendar.YEAR,1);
                Date time = gregorianCalendar.getTime();
                resMember.setExpirationTime(time);
                resMember.setMemberId(name);
                resMember.setPhoneNumber(phone);
            }
            memberService.updateByPrimaryKey(resMember);
        }else{
            if(type.equals("1")){
                gregorianCalendar.setTime(new Date());
                gregorianCalendar.add(gregorianCalendar.DATE,30);
                Date time = gregorianCalendar.getTime();
                member.setExpirationTime(time);
                resMember.setMemberId(name);
                resMember.setPhoneNumber(phone);
            }else if(type.equals("2")){
                gregorianCalendar.setTime(new Date());
                gregorianCalendar.add(gregorianCalendar.YEAR,1);
                Date time = gregorianCalendar.getTime();
                member.setExpirationTime(time);
                resMember.setMemberId(name);
                resMember.setPhoneNumber(phone);
            }
            member.setJoinTime(new Date());
            memberService.insert(member);
        }
        return 0;
    }


    /*支付月卡费用回调*/
    @RequestMapping(value = "/payMemberOrderCallBack",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String payMemberOrderCallBack(@RequestBody JSONObject jsonParam) throws ParseException {
        logger.info("payMemberOrderCallBack getParam Start====================================================");
        logger.info("jsonParam.toJSONString()          "+jsonParam.toJSONString());
        JSONObject jsonObject = JSONObject.parseObject(jsonParam.toJSONString());
        logger.info(jsonObject);
        JSONObject jo = (JSONObject) jsonObject.get("data");
        Integer state = (Integer) jo.get("state");
        String trade_no = (String) jo.get("trade_no");
        JSONObject attach = (JSONObject) jo.get("attach");
        String car_number = (String) attach.get("car_number");
        String type = (String) attach.get("type");
        String area = (String) attach.get("area");
        String name = (String) attach.get("name");
        String phone = (String) attach.get("phone");
        updateMember(car_number,area,type,name,phone);
        JSONObject resJO = new JSONObject();
        resJO.put("state",1);
        resJO.put("trade_no",trade_no);
        return resJO.toJSONString();
    }
    /*支付月卡费用*/
    @RequestMapping(value = "/payMemberOrder",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public String payMemberOrder(@RequestParam("car_number")String car_number,
                                 @RequestParam("type")String type,
                                 @RequestParam("area")String area,
                                 @RequestParam("name")String name,
                                 @RequestParam("phone")String phone
                                 ) {
        String amount = "";
        DateString dateString = new DateString();
        long l = dateString.dateToMillisecond(new Date());
        JSONObject dataOb = new JSONObject();
        JSONObject signOb = new JSONObject();
        JSONObject attachJO = new JSONObject();
        attachJO.put("car_number",car_number);
        attachJO.put("type",type);
        attachJO.put("area",area);
        attachJO.put("name",name);
        attachJO.put("phone",phone);
        if(type.equals("1")){
            amount = "100";
        }else{
            amount = "1000";
        }
        dataOb.put("park_id","00001");
        dataOb.put("amount",amount);
//        dataOb.put("card_id",);
        dataOb.put("trade_no",GlobalVariable.util.getOrderIdByUUIdAddPlate(car_number));
//        dataOb.put("notify_url","http://www.51quyd.com/payTest/payMemberOrderCallBack");
//        dataOb.put("notify_url","http://192.168.3.206:8080/carmanager_war/payTest/payMemberOrderCallBack");
//        dataOb.put("back_url","https://s.bolink.club/unionapi/paysuccess?trace_no=201712125566359777");
//        dataOb.put("cancel_url","https://s.bolink.club/unionapi/paysfail?trade_no=201712125566359777");
        dataOb.put("car_number",car_number);
        dataOb.put("attach",attachJO);
        dataOb.put("pay_channel","weixin");
        dataOb.put("time_temp",l);

//        dataOb.put("car_number","津MYL400");
//        dataOb.put("in_time",l);
//        dataOb.put("order_id",orderIdByUUId);
//        dataOb.put("empty_plot",100);
//        dataOb.put("park_id","00001");
//        dataOb.put("union_id",200395);
//        dataOb.put("remark","test");
//        dataOb.put("inpark_img","Test");

        String dataobstr = dataOb.toString();
        String md5encode = dataobstr+"key=EC6B6BE71DBE76D6";
        String signString = MD5Utils.MD5EncodeToUpper(md5encode,"UTF-8");
        signOb.put("data",dataOb);
        signOb.put("sign",signString);
        signOb.put("union_id",200474);
        logger.info(signOb);
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://s.bolink.club/unionapi/paymonthcard",signOb,String.class);
        logger.info(responseEntity);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);
        //{"payurl":"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx962fe9d5c0e2a2c7&redirect_uri=https%3A%2F%2Fs.bolink.club%2Funionapi%2Fpaymonthcard%3Fkfc_id%3d128364358&response_type=code&scope=snsapi_base&state=123#wechat_redirect",
        // "trade_no":"201712125566359777",
        // "state":1},
        // [Server:"Apache-Coyote/1.1", Access-Control-Allow-Origin:"*", Access-Control-Allow-Methods:"POST,GET", Content-Type:"application/json;charset=utf-8", Transfer-Encoding:"chunked", Vary:"Accept-Encoding", Date:"Tue, 19 Nov 2019 05:56:14 GMT", Connection:"close"]>
        return jsonObject.toJSONString();
    }
//    @RequestMapping("carin")
//    public String GetUser(){
//        logger.info("ths");
//        String Md5Key = MD5Utils.MD5EncodeToUpper("F435C093F68AD0B7","UTF-8");
//        HttpMethod method = HttpMethod.POST;
//        JSONObject dataOb = new JSONObject();
//        JSONObject signOb = new JSONObject();
//        JSONObject params = new JSONObject();
//
//        dataOb.put("type",1);
//        dataOb.put("channel_id","A1");
//        dataOb.put("union_id",200395);
//        dataOb.put("park_id","00001");
//        dataOb.put("change",0);
//        String dataobstr = dataOb.toString();
//        String md5encode = dataobstr+"key=F435C093F68AD0B7";
//        String signString = MD5Utils.MD5EncodeToUpper(md5encode,"UTF-8");
//        signOb.put("data",dataOb);
//        signOb.put("sign",signString);
//        logger.info(signOb);
//        RestTemplate restTemplate=new RestTemplate();
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(requestUrl,signOb,String.class);
//        logger.info(responseEntity);
//        //{"sign":"50BE7B01F8531AE4346B1170BE8E90DA","data":{"type":1,"channel_id":"A1","union_id":20002,"park_id":"21787","change":1}}
//        //{sign=[769ACA88C96F75264FC973ECE0E0F3BF], data=[{"change":1,"union_id":200395,"park_id":"00001","type":1,"channel_id":"A1"}]}
//        return null;
//    }
//    @RequestMapping("systemin")
//    public String Carin() throws ParseException {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
//        long epoch = df.parse("2015-09-09 0:0:0").getTime();
//        String Md5Key = MD5Utils.MD5EncodeToUpper("F435C093F68AD0B7","UTF-8");
//        HttpMethod method = HttpMethod.POST;
//        JSONObject dataOb = new JSONObject();
//        JSONObject signOb = new JSONObject();
////{"data":{"car_number":"京ONLYOU","in_time":1484369702,"order_id":"109010","empty_plot":20,"park_id":"21798","union_id":200185,"remark":"入场信息备注"},"sign":"1D3BA0D562AF49B13D905B6102C3C564"}
//        dataOb.put("car_number","津MYL400");
//        dataOb.put("in_time",1484369702);
//        dataOb.put("order_id","000003");
//        dataOb.put("empty_plot",100);
//        dataOb.put("park_id","00001");
//        dataOb.put("union_id",200395);
//        dataOb.put("remark","test");
//        dataOb.put("inpark_img","Test");
//
//        String dataobstr = dataOb.toString();
//        String md5encode = dataobstr+"key=F435C093F68AD0B7";
//        String signString = MD5Utils.MD5EncodeToUpper(md5encode,"UTF-8");
//        signOb.put("data",dataOb);
//        signOb.put("sign",signString);
//        logger.info(signOb);
//        RestTemplate restTemplate=new RestTemplate();
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://beta.bolink.club/unionapi/neworder/addorder",signOb,String.class);
//        logger.info(responseEntity);
//        //{"sign":"50BE7B01F8531AE4346B1170BE8E90DA","data":{"type":1,"channel_id":"A1","union_id":20002,"park_id":"21787","change":1}}
//        //{sign=[769ACA88C96F75264FC973ECE0E0F3BF], data=[{"change":1,"union_id":200395,"park_id":"00001","type":1,"channel_id":"A1"}]}
//        return null;
//    }

//    @RequestMapping("systemout")
//    public String Carout() throws ParseException {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
//        long epoch = df.parse("2015-09-09 0:0:0").getTime();
//        String Md5Key = MD5Utils.MD5EncodeToUpper("F435C093F68AD0B7","UTF-8");
//        HttpMethod method = HttpMethod.POST;
//        JSONObject dataOb = new JSONObject();
//        JSONObject signOb = new JSONObject();
////{"data":{"park_id":"21798","union_id":200185,"car_number":"京ABC123","in_time":1484369702,"out_time":1484369702,"order_id":"109010","total":"0.01",
//// "remark":"出场信息备注","pay_type":"wallet","empty_plot":20,"auth_code": "134789575154465610"},"sign":"629BA006E0E904C0C92552D90D0AA4C7"}
//        dataOb.put("park_id","00001");
//        dataOb.put("union_id",200395);
//        dataOb.put("car_number","津MYL400");
//        dataOb.put("in_time",1484369702);
//        dataOb.put("out_time",1484369702);
//        dataOb.put("order_id","000003");
//        dataOb.put("total","0.01");
//        dataOb.put("remark","Test");
//        dataOb.put("pay_type","wallet");
//        dataOb.put("empty_plot",80);
//
//        String dataobstr = dataOb.toString();
//        String md5encode = dataobstr+"key=F435C093F68AD0B7";
//        String signString = MD5Utils.MD5EncodeToUpper(md5encode,"UTF-8");
//        signOb.put("data",dataOb);
//        signOb.put("sign",signString);
//        logger.info(signOb);
//        RestTemplate restTemplate=new RestTemplate();
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://beta.bolink.club/unionapi/neworder/updateorder",signOb,String.class);
//        logger.info(responseEntity);
//        //{"sign":"50BE7B01F8531AE4346B1170BE8E90DA","data":{"type":1,"channel_id":"A1","union_id":20002,"park_id":"21787","change":1}}
//        //{sign=[769ACA88C96F75264FC973ECE0E0F3BF], data=[{"change":1,"union_id":200395,"park_id":"00001","type":1,"channel_id":"A1"}]}
//        return null;
//    }

    //车辆进场通知
    @RequestMapping("carinNotify")
    @ResponseBody
    public String carinNotify(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString()+"aaaaaaaaaaaaaaaaaaaaaa");
        return jsonParam.toJSONString();
    }
    //车辆出场通知
    @RequestMapping("carOutNotify")
    @ResponseBody
    public String carOutNotify(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        return jsonParam.toJSONString();
    }

    public Double getRent(Date endDate, Date nowDate,Integer parkId) {
        this.refreshCharging(parkId);
        if(endDate==null){
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
        long allMin = diff /nm;
//        if(v==0.0){
//            return GlobalVariable.blueLincenseFollowCharge+GlobalVariable.blueLincenseFirstCharge;
//        }
//        return day + "天" + hour + "小时" + min + "分钟";
        double allDayMin = 1440;
        double dayCharge = 0;
        double res = 0;
        double spareMin = allMin%allDayMin;
        if(allMin>allDayMin){
            dayCharge = Math.floor(allMin/allDayMin)*GlobalVariable.blueLincenseAllDayLimit;
        }
        if(spareMin<GlobalVariable.blueLicenseFreeTime){  return dayCharge;}
        if(spareMin<=GlobalVariable.blueLicenseFirstTime){
            return dayCharge+Double.valueOf(GlobalVariable.blueLincenseFirstCharge);
        }else{
            double sumTemp = Math.floor((spareMin - GlobalVariable.blueLicenseFirstTime) / GlobalVariable.blueLincenseFollowTime)+1; //sumTemp是否能够取整数
            double nowCharge = sumTemp*GlobalVariable.blueLincenseFollowCharge + GlobalVariable.blueLincenseFirstCharge;
            if (nowCharge>GlobalVariable.blueLincenseAllDayLimit) {nowCharge=GlobalVariable.blueLincenseAllDayLimit;}   //当天费用大于20时取20
            return dayCharge+nowCharge;//之前天数的费用加上当天费用
        }
    }

    public long getUnixTime(Date date) throws ParseException {
        return DateString.dateToMillisecond(date);
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
    /*查询订单实时金额通知*/
    @RequestMapping(value = "/checkOrderRent",method = RequestMethod.POST)
    @ResponseBody
    public String checkOrderRent(@RequestBody JSONObject jsonParam) throws ParseException {
        logger.info("checkOrderRent getParam Start=====================泊链支付======================");
        logger.info("jsonParam.toJSONString()          "+jsonParam.toJSONString());
        JSONObject jsonObject = JSONObject.parseObject(jsonParam.toJSONString());
        logger.info(jsonObject);
        JSONObject jo = (JSONObject) jsonObject.get("data");
        String car_number = (String) jo.get("car_number");//车牌 String 京HP00G6	否, pay_scene为0，2时必传
        String service_name = (String) jo.get("service_name");//接口名称	String	query_price	是
        String order_id = (String) jo.get("order_id");//订单记录号(车辆在停车场停车唯一订单编号，对应入场订单编号)	String	10000	否，pay_scene为0，2时必传；pay_scene为0时，无在场订单会下发order_id为空串
        String park_id = (String) jo.get("park_id");//车场编号	String	20003	是
        Integer park_idInt = Integer.parseInt(park_id);
        Integer pay_scene = (Integer) jo.get("pay_scene");//支付场景，0：场内预付，1：结算待支付，2：无牌车请求结算，默认：0	Number	0	是
        String out_channel_id = (String) jo.get("out_channel_id");//出口通道：字母、数字和下划线组成	String	A1	否，pay_scene为1,2时必传
        String query_order_no = (String) jo.get("query_order_no");//查询价格编号：本次收费系统查询价格的编号，返回时，原值返回，在预付通知中返回给收费系统	String	300022	是
        logger.info(jo);
        logger.info("checkOrderRent getParam End===================================================="+order_id);
        LincensePlate lp = new LincensePlate();
        if(lp!=null){

        }else{

        }
        //pay_scene  支付场景，0：场内预付，1：结算待支付，2：无牌车请求结算，默认：0
        if(pay_scene==1){
            LincensePlate plate = new LincensePlate();
            plate.setLpOrderState("待支付");
            JinshiCameras jinshiCameras = jinshiCameraService.selectByParkIdAndJcName(park_idInt, out_channel_id);
            String handleByName = jinshiCameras.getJcThandle();
            plate.setLpDepartureCname(handleByName);
//            plate.setLpOrderId(order_id);
            plate.setLpParkingName(park_idInt.toString());
//            plate.setLpParkingName(String.valueOf(GlobalVariable.parkId));
            logger.info(String.valueOf(GlobalVariable.parkId)+"---------------------------parkId");
            List<LincensePlate> listLP = lincensePlateService.selectByOrderStatAndCname(plate);
            logger.info(listLP.size()+"-------------------------------------------listLP.size");
            logger.info(listLP.get(0)+"----------------------------------------------listLp.get0");
            if (listLP.size() > 0) {
                LincensePlate lincensePlate = listLP.get(0);
                BeanUtils.copyProperties(lincensePlate,lp);
            }
//            lp = lincensePlateService.selectByOrderStatAndCname(plate);
            logger.info(handleByName);
        }else if(pay_scene==0){
            LincensePlate lincensePlate = new LincensePlate();
            lincensePlate.setLpLincensePlateIdCar(car_number);
            List<LincensePlate> lincensePlates = lincensePlateService.selectByLincense(lincensePlate);
            if(lincensePlates.size()>0){
                lp = lincensePlates.get(0);
            }
//            lp = lincensePlateService.selectByLincense(lincensePlate);
        }else if(pay_scene==2){
            logger.info(pay_scene+"------------------------------");
            logger.info("noplate enter");
        }
        //Double rent = getRent(new Date(),lp.getLpInboundTime(),park_idInt);
        logger.info("checkOrderRent sentParam Start==================================================");
        DateFormat df = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        JSONObject dataOb = new JSONObject();
        Date tempDate = new Date();
        Double rent = getRent(tempDate, lp.getLpInboundTime(), Integer.valueOf(lp.getLpParkingName()));
//{"data":{"park_id":"21798","union_id":200185,"car_number":"京ABC123","in_time":1484369702,"out_time":1484369702,"order_id":"109010","total":"0.01",
// "remark":"出场信息备注","pay_type":"wallet","empty_plot":20,"auth_code": "134789575154465610"},"sign":"629BA006E0E904C0C92552D90D0AA4C7"}
        dataOb.put("state",1);//状态	Number	0失败1成功	是
        dataOb.put("free_out_time",15);//免费离场时间（分钟）	Number	10	否(默认显示10分钟)
//        dataOb.put("query_time", 1516000293);//查询价格时间	Number(unix时间戳格式，精确到秒)	1490875218	否
        dataOb.put("duration",getDateOften(new Date(),lp.getLpInboundTime()));//停车时长(分钟)	Number	50	是
        dataOb.put("derate_money",0);//减免金额(元)	Number	5	否
        dataOb.put("derate_duration",0);//减免时长(分钟)	Number	5	否
        dataOb.put("park_id",park_id);//车场编号	String	20003	是
        dataOb.put("order_id",lp.getLpOrderId());//订单记录号(车辆在停车场停车唯一订单编号，对应入场订单编号)	String	10000	是
//        dataOb.put("query_order_no",query_order_no);//查询价格编号：本次收费系统查询价格的编号，返回时，原值返回，在预付通知中返回给收费系统	String	300022	否，查询调用pay_scene为1，2时必传
//        dataOb.put("price",lp.getLpParkingCost());//当前应收金额（元））	String	6.0	是
        dataOb.put("price",rent.toString());//当前应收金额（元））	String	6.0	是
        logger.info("应付金额是============="+rent);
//        dataOb.put("errmsg","查询实时订单");//方法返回描述	String	State=0时不能为空	否（有值时必传）
//        dataOb.put("total",6);//订单总金额（元）	String	6.0	否
//        dataOb.put("position","A001");//停车位置	String	A001	否
        dataOb.put("car_number",lp.getLpLincensePlateIdCar());//车牌	String	京HP00G6	否, pay_scene为0，2时必传；与in_time同时返回，可以支付未上传的订单
        dataOb.put("in_time",getUnixTime(lp.getLpInboundTime()));//进场时间	Number(unix时间戳格式，精确到秒)	1490875218	否, pay_scene为0，2时必传；与car_number同时返回，可以支付未上传的订单
//        dataOb.put("inpark_img","");//入场图片的url，没有图片时返回空字符串，不要给默认图片	String	https://s.bolink.club/image/ab.png	否
//        dataOb.put("multiple_prepay",0);//车场返回价格时加上这个参数,0不支持多次预付，1支持多次预付；默认0。	Number	0不支持，1支持	否

        logger.info(dataOb);
        //{"sign":"50BE7B01F8531AE4346B1170BE8E90DA","data":{"type":1,"channel_id":"A1","union_id":20002,"park_id":"21787","change":1}}
        //{sign=[769ACA88C96F75264FC973ECE0E0F3BF], data=[{"change":1,"union_id":200395,"park_id":"00001","type":1,"channel_id":"A1"}]}
        logger.info("checkOrderRent sentParam End======================================================");
//        this.writeJson(resp, signOb);
        return dataOb.toJSONString();
    }
    //车主会员状态变更通知
    @RequestMapping("memberStateChange")
    @ResponseBody
    public String memberStateChange(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        return jsonParam.toJSONString();
    }
    //获取车场二维码
    @RequestMapping("getYardQR")
    @ResponseBody
    public String getYardQR(@RequestBody JSONObject jsonParam){
        logger.info(jsonParam.toJSONString());
        return jsonParam.toJSONString();
    }

    /*预付订单通知*/
    @RequestMapping("payAdvanceOrder")
    @ResponseBody
    public String payAdvanceOrder(@RequestBody JSONObject jsonParam) throws ParseException {
        logger.info("payAdvanceOrder getParam Start====================================================");
        logger.info("jsonParam.toJSONString()          "+jsonParam.toJSONString());
        JSONObject jsonObject = JSONObject.parseObject(jsonParam.toJSONString());
        logger.info(jsonObject);
        JSONObject jo = (JSONObject) jsonObject.get("data");
        String car_number = (String) jo.get("car_number");
        String service_name = (String) jo.get("service_name");
        BigDecimal prepay = (BigDecimal) jo.get("prepay");
        String order_id = (String) jo.get("order_id");
        Integer query_order_no = (Integer) jo.get("query_order_no");
        String park_id = (String) jo.get("park_id");
        String trade_no = (String) jo.get("trade_no");
//        Long query_time = (Long) jo.get("query_time");
        Integer prepay_type = (Integer) jo.get("prepay_type");
        Integer pay_channel = (Integer) jo.get("pay_channel");
        Integer pay_time = (Integer) jo.get("pay_time");
        BigDecimal arrive_money = (BigDecimal) jo.get("arrive_money");
        BigDecimal fee = (BigDecimal) jo.get("fee");
        Integer account_type = (Integer) jo.get("account_type");
        String remark = (String) jo.get("remark");
        logger.info(jo);
        logger.info("payAdvanceOrder getParam End====================================================");
        LincensePlate plate = new LincensePlate();
        plate.setLpLincensePlateIdCar(car_number);
        plate.setLpOrderState("支付成功");
        lincensePlateService.updateByPlate(plate);
//        GlobalVariable.sentClientMessage = car_number;
        logger.info("payAdvanceOrder sentParam Start==================================================");
        JSONObject dataOb = new JSONObject();
//{"data":{"park_id":"21798","union_id":200185,"car_number":"京ABC123","in_time":1484369702,"out_time":1484369702,"order_id":"109010","total":"0.01",
// "remark":"出场信息备注","pay_type":"wallet","empty_plot":20,"auth_code": "134789575154465610"},"sign":"629BA006E0E904C0C92552D90D0AA4C7"}
        dataOb.put("state",1);
        dataOb.put("park_id",park_id);
//        dataOb.put("query_time", 1516000293);
        dataOb.put("order_id",order_id);
        dataOb.put("prepay",prepay.toString());
        dataOb.put("trade_no",trade_no);
        dataOb.put("errmsg","");
        logger.info(dataOb);
//        RestTemplate restTemplate=new RestTemplate();
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://beta.bolink.club/unionapi/neworder/updateorder",signOb,String.class);
//        logger.info(responseEntity);
        //{"sign":"50BE7B01F8531AE4346B1170BE8E90DA","data":{"type":1,"channel_id":"A1","union_id":20002,"park_id":"21787","change":1}}
        //{sign=[769ACA88C96F75264FC973ECE0E0F3BF], data=[{"change":1,"union_id":200395,"park_id":"00001","type":1,"channel_id":"A1"}]}
        logger.info("payAdvanceOrder sentParam End======================================================");
        return dataOb.toJSONString();
    }
    /*预付订单回调通知*/
    @RequestMapping("payAdvanceOrderCallback")
    @ResponseBody
    public String payAdvanceOrderCallback(@RequestBody JSONObject jsonParam){
        logger.info("payAdvanceOrderCallback getParam Start====================================================");
        logger.info("jsonParam.toJSONString()          "+jsonParam.toJSONString());
        JSONObject jsonObject = JSONObject.parseObject(jsonParam.toJSONString());
        logger.info(jsonObject);
        JSONObject jo = (JSONObject) jsonObject.get("data");
        String service_name = (String) jo.get("service_name");
        Integer state = (Integer) jo.get("state");
        String park_id = (String) jo.get("park_id");
        String order_id = (String) jo.get("order_id");
        String trade_no = (String) jo.get("trade_no");
        String pay_type = (String) jo.get("pay_type");
        String out_channel_id = (String) jo.get("out_channel_id");
        float total = (float) jo.get("total");
        String errmsg = (String) jo.get("errmsg");
        Integer pay_channel = (Integer) jo.get("pay_channel");
        Integer pay_time = (Integer) jo.get("pay_time");
        String car_number = (String) jo.get("car_number");
        Double arrive_money = (Double) jo.get("arrive_money");
        Double fee = (Double) jo.get("fee");
        Integer account_type = (Integer) jo.get("account_type");
        String remark = (String) jo.get("remark");
        logger.info(jo);
        logger.info("payAdvanceOrderCallback getParam End====================================================");

        if(state==1){
            LincensePlate plate = new LincensePlate();
            plate.setLpLincensePlateIdCar(car_number);
            plate.setLpOrderState("支付成功");
            lincensePlateService.updateByPlate(plate);

        }
        logger.info("payAdvanceOrderCallback sentParam Start==================================================");
        JSONObject dataOb = new JSONObject();
//{"data":{"park_id":"21798","union_id":200185,"car_number":"京ABC123","in_time":1484369702,"out_time":1484369702,"order_id":"109010","total":"0.01",
// "remark":"出场信息备注","pay_type":"wallet","empty_plot":20,"auth_code": "134789575154465610"},"sign":"629BA006E0E904C0C92552D90D0AA4C7"}
        dataOb.put("service_name","outpark");
        dataOb.put("state",1);
//        dataOb.put("query_time", 1516000293);
        dataOb.put("errmsg","");
        dataOb.put("trade_no",trade_no);
        dataOb.put("order_id",order_id);
        logger.info(dataOb);
//        RestTemplate restTemplate=new RestTemplate();
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://beta.bolink.club/unionapi/neworder/updateorder",signOb,String.class);
//        logger.info(responseEntity);
        //{"sign":"50BE7B01F8531AE4346B1170BE8E90DA","data":{"type":1,"channel_id":"A1","union_id":20002,"park_id":"21787","change":1}}
        //{sign=[769ACA88C96F75264FC973ECE0E0F3BF], data=[{"change":1,"union_id":200395,"park_id":"00001","type":1,"channel_id":"A1"}]}
        logger.info("payAdvanceOrderCallback sentParam End======================================================");
        return dataOb.toJSONString();
    }

    /*电子收费异步返回结果*/
    @RequestMapping("elChargeCallBackRes")
    @ResponseBody
    public String elChargeCallBackRes(@RequestBody JSONObject jsonParam){
        logger.info("elChargeCallBackRes getParam Start====================================================");
        logger.info("jsonParam.toJSONString()          "+jsonParam.toJSONString());
        JSONObject jsonObject = JSONObject.parseObject(jsonParam.toJSONString());
        logger.info(jsonObject);
        JSONObject jo = (JSONObject) jsonObject.get("data");
        String service_name = (String) jo.get("service_name");
        Integer state = (Integer) jo.get("state");
        String park_id = (String) jo.get("park_id");
        String order_id = (String) jo.get("order_id");
        String trade_no = (String) jo.get("trade_no");
        String pay_type = (String) jo.get("pay_type");
        String out_channel_id = (String) jo.get("out_channel_id");
        BigDecimal  total = (BigDecimal ) jo.get("total");
        String errmsg = (String) jo.get("errmsg");
        Integer pay_channel = (Integer) jo.get("pay_channel");
        Integer pay_time = (Integer) jo.get("pay_time");
        String car_number = (String) jo.get("car_number");
        BigDecimal  arrive_money = (BigDecimal ) jo.get("arrive_money");
        BigDecimal  fee = (BigDecimal ) jo.get("fee");
        Integer account_type = (Integer) jo.get("account_type");
        String remark = (String) jo.get("remark");
        logger.info(jo);
        logger.info("payAdvanceOrderCallback getParam End====================================================");

        if(state==1){
            LincensePlate plate = new LincensePlate();
            plate.setLpLincensePlateIdCar(car_number);
            plate.setLpOrderState("支付成功");
            lincensePlateService.updateByPlate(plate);
//            GlobalVariable.sentClientMessage = car_number;
        }
        logger.info("payAdvanceOrderCallback sentParam Start==================================================");
        JSONObject dataOb = new JSONObject();
//{"data":{"park_id":"21798","union_id":200185,"car_number":"京ABC123","in_time":1484369702,"out_time":1484369702,"order_id":"109010","total":"0.01",
// "remark":"出场信息备注","pay_type":"wallet","empty_plot":20,"auth_code": "134789575154465610"},"sign":"629BA006E0E904C0C92552D90D0AA4C7"}
        dataOb.put("service_name","outpark");
        dataOb.put("state",1);
//        dataOb.put("query_time", 1516000293);
        dataOb.put("errmsg","");
        dataOb.put("trade_no",trade_no);
        dataOb.put("order_id",order_id);
        logger.info(dataOb);
//        RestTemplate restTemplate=new RestTemplate();
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://beta.bolink.club/unionapi/neworder/updateorder",signOb,String.class);
//        logger.info(responseEntity);
        //{"sign":"50BE7B01F8531AE4346B1170BE8E90DA","data":{"type":1,"channel_id":"A1","union_id":20002,"park_id":"21787","change":1}}
        //{sign=[769ACA88C96F75264FC973ECE0E0F3BF], data=[{"change":1,"union_id":200395,"park_id":"00001","type":1,"channel_id":"A1"}]}
        logger.info("payAdvanceOrderCallback sentParam End======================================================");
        return dataOb.toJSONString();
    }


    public void writeJson(HttpServletResponse resp , JSONObject json ){
        PrintWriter out = null;
        try {
            //设定类容为json的格式
            resp.setContentType("application/json;charset=UTF-8");
            out = resp.getWriter();
            //写到客户端
            out.write(json.toJSONString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(out != null){
                out.close();
            }
        }
    }

//    public String getIpBytHandle(String tHandle){
//        String res = "";
//        if(tHandle.equals("0")){
//            res = "192.168.1.105";
//        }else if(tHandle.equals("1")){
//            res = "192.168.1.106";
//        }else if(tHandle.equals("2")){
//            res = "192.168.1.107";
//        }else if(tHandle.equals("3")){
//            res = "192.168.1.108";
//        }
//        return res;
//    }


    public void refreshCharging(Integer parkId){
        List<JinshiParkSetting> jinshiParkSetting = jinshiParkSettingService.selectByParkKey(parkId);
        if(jinshiParkSetting!=null){
            logger.info("正在获取费用信息-------------------------");
            for (int i = 0; i < jinshiParkSetting.size(); i++) {
                if(jinshiParkSetting.get(i).getJpsCarType()==0){
                    GlobalVariable.blueLicenseFreeTime = jinshiParkSetting.get(i).getJpsFreeTime();
                    GlobalVariable.blueLicenseFirstTime = jinshiParkSetting.get(i).getJpsFirstTime();
                    GlobalVariable.blueLincenseFirstCharge = jinshiParkSetting.get(i).getJpsFirstCharge();
                    GlobalVariable.blueLincenseFollowTime = jinshiParkSetting.get(i).getJpsFollowTime();
                    GlobalVariable.blueLincenseFollowCharge = jinshiParkSetting.get(i).getJpsFollowCharge();
                    GlobalVariable.blueLincenseAllDayLimit = jinshiParkSetting.get(i).getJpsAlldayLimit();
                    GlobalVariable.blueLincensePayAdvanceOutTime = jinshiParkSetting.get(i).getJpsAdvanceChargeOuttime();
                }else if(jinshiParkSetting.get(i).getJpsCarType()==2){
                    GlobalVariable.greenLicenseFreeTime =jinshiParkSetting.get(i).getJpsFreeTime();
                    GlobalVariable.greenLicenseFirstTime = jinshiParkSetting.get(i).getJpsFirstTime();
                    GlobalVariable.greenLincenseFirstCharge = jinshiParkSetting.get(i).getJpsFirstCharge();
                    GlobalVariable.greenLincenseFollowTime = jinshiParkSetting.get(i).getJpsFollowTime();
                    GlobalVariable.greenLincenseFollowCharge = jinshiParkSetting.get(i).getJpsFollowCharge();
                    GlobalVariable.greenLincenseAllDayLimit = jinshiParkSetting.get(i).getJpsAlldayLimit();
                    GlobalVariable.greenLincensePayAdvanceOutTime = jinshiParkSetting.get(i).getJpsAdvanceChargeOuttime();
                }else if(jinshiParkSetting.get(i).getJpsCarType()==1){
                    GlobalVariable.yellowLicenseFreeTime = jinshiParkSetting.get(i).getJpsFreeTime();
                    GlobalVariable.yellowLicenseFirstTime = jinshiParkSetting.get(i).getJpsFirstTime();
                    GlobalVariable.yellowLincenseFirstCharge = jinshiParkSetting.get(i).getJpsFirstCharge();
                    GlobalVariable.yellowLincenseFollowTime = jinshiParkSetting.get(i).getJpsFollowTime();
                    GlobalVariable.yellowLincenseFollowCharge = jinshiParkSetting.get(i).getJpsFollowCharge();
                    GlobalVariable.yellowLincenseAllDayLimit = jinshiParkSetting.get(i).getJpsAlldayLimit();
                    GlobalVariable.yellowLincensePayAdvanceOutTime = jinshiParkSetting.get(i).getJpsAdvanceChargeOuttime();
                }else if(jinshiParkSetting.get(i).getJpsCarType()==3){
                    GlobalVariable.specialLincenseFreeTime =jinshiParkSetting.get(i).getJpsFreeTime();
                    GlobalVariable.specialLicenseFirstTime = jinshiParkSetting.get(i).getJpsFirstTime();
                    GlobalVariable.specialLincenseFirstCharge = jinshiParkSetting.get(i).getJpsFirstCharge();
                    GlobalVariable.specialLincenseFollowTime = jinshiParkSetting.get(i).getJpsFollowTime();
                    GlobalVariable.specialLincenseFollowCharge = jinshiParkSetting.get(i).getJpsFollowCharge();
                    GlobalVariable.specialLincenseAllDayLimit = jinshiParkSetting.get(i).getJpsAlldayLimit();
                    GlobalVariable.specialLincensePayAdvanceOutTime = jinshiParkSetting.get(i).getJpsAdvanceChargeOuttime();
                }else if(jinshiParkSetting.get(i).getJpsCarType()==4){
                    GlobalVariable.yellowLicenseFreeTimeBig = jinshiParkSetting.get(i).getJpsFreeTime();
                    GlobalVariable.yellowLicenseFirstTimeBig = jinshiParkSetting.get(i).getJpsFirstTime();
                    GlobalVariable.yellowLincenseFirstChargeBig = jinshiParkSetting.get(i).getJpsFirstCharge();
                    GlobalVariable.yellowLincenseFollowTimeBig = jinshiParkSetting.get(i).getJpsFollowTime();
                    GlobalVariable.yellowLincenseFollowChargeBig = jinshiParkSetting.get(i).getJpsFollowCharge();
                    GlobalVariable.yellowLincenseAllDayLimitBig = jinshiParkSetting.get(i).getJpsAlldayLimit();
                    GlobalVariable.yellowLincensePayAdvanceOutTimeBig = jinshiParkSetting.get(i).getJpsAdvanceChargeOuttime();
                }
            }
        }else{
            logger.info("停车场无费用设置信息---------------------------");
        }
    }







    /*
     *
     * 临时停缴费
     * */
    @RequestMapping(value = "/paypark",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JSONObject paypark( HttpServletRequest request,  HttpServletResponse resp,@RequestBody JSONObject jesonparm)throws Exception{
        System.out.println("jesonparm传过来的是+++++++++++++"+jesonparm);
        String amount=(String) jesonparm.get("amount");//实付金额
        System.out.println("实际支付元金额是++++++++++"+amount);
        String lpInboundTime = (String) jesonparm.get("lpInboundTime");//入场时间
        String lpLincensePlateIdCar = (String) jesonparm.get("lpLincensePlateIdCar");//车牌号
        String lpParkingName = (String) jesonparm.get("lpParkingName");//车场id
        if (lpParkingName.equals("1")){
            lpParkingName = "00001";
        }
        if (lpParkingName.equals("27")){
            lpParkingName = "00004";
        }
        if (lpParkingName.equals("3")){
            lpParkingName="00003";
        }
        DateString dateString = new DateString();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = simpleDateFormat.parse(lpInboundTime);
        long l = dateString.dateToMillisecond(parse);
        long t = System.currentTimeMillis();
        JSONObject dataob = new JSONObject();
        JSONObject signob = new JSONObject();
        String orderId = UUID.randomUUID().toString().replace("-","");
        StringBuilder str=new StringBuilder();//定义变长字符串bai
        Random random=new Random();
        //随机生成数字，du并添加到字符串
        for(int i=0;i<20;i++){
            str.append(random.nextInt(10));
        }

        dataob.put("title","支付停车费");//标题，汉字20位以内，英文40以内    例如:支付停车费
        dataob.put("park_id",lpParkingName);//车场编号
        dataob.put("amount",amount);//金额，单位是元，保留两位小数
        dataob.put("trade_no", str.toString());//交易流水号，由数字组成的字符串，16-32位，不可低于16位，不可重复。
        dataob.put("order_id",orderId);//车辆进场订单号，32位以内，字母数字和下划线组成
        dataob.put("car_number",lpLincensePlateIdCar);//车牌号
        dataob.put("in_time",l);//入场时间，时间戳unix时间，到秒
        dataob.put("pay_type",0);//支付类型 0扫码支付，2扫码枪支付，默认0
        dataob.put("time_temp",String.valueOf(t));//时间戳unix时间，到毫秒
        dataob.put("channel","weixin");
        dataob.put("description",lpLincensePlateIdCar);

        String data = dataob.toJSONString();
        String md5sign = data+"key=EC6B6BE71DBE76D6";
        String sign = MD5Utils.MD5EncodeToUpper(md5sign,"UTF-8");
        signob.put("union_id",200474);
        signob.put("sign",sign);
        signob.put("data",dataob);
        System.out.println("签名------"+signob);
        logger.info(signob);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("https://s.bolink.club/unionapi/bolinkparkpay", signob, String.class);
        logger.info(stringResponseEntity);
        System.out.println("哈哈哈哈--------"+stringResponseEntity);
        String body = stringResponseEntity.getBody();
        System.out.println("body--------------"+body);
        JSONObject jsonObject = JSONObject.parseObject(body);
        System.out.println("66666666666"+jsonObject.toJSONString());
        String payurl = (String) jsonObject.get("payurl");
        System.out.println("地址是----------"+payurl);
        //resp.sendRedirect(payurl);
        return  jsonObject;
    }

    /*临时停缴支付回调*/
    @RequestMapping(value = "/payparkBack")
    @ResponseBody
    public String payparkBack(@RequestBody JSONObject jsonObject){
        //logger.info("jsonObject.toJSONString()       "+jsonObject.toJSONString());
        System.out.println("jsonObject.toJSONString()----------     "+jsonObject.toJSONString());
        JSONObject json = JSONObject.parseObject(jsonObject.toJSONString());
        System.out.println("json是----------"+json);
        //logger.info(json);
        JSONObject data = (JSONObject) json.get("data");
        String sign = (String) json.get("sign");//数据data的签名值，32位
        String park_id = (String) data.get("park_id");//车场编号
        Integer state = (Integer) data.get("state");//状态 0支付失败，1支付成功
        String amount = (String) data.get("amount");//	用户支付金额，单位是元，保留两位小数
        String receipt_amount = (String) data.get("receipt_amount");//到账金额，保留5位小数
        String pay_platform_fee = (String) data.get("pay_platform_fee");//	支付平台手续费,保留5位小数
        String trade_no = (String) data.get("trade_no");//下单时的交易流水号
        String car_number = (String) data.get("description");
        System.out.println("支付回调的车牌号是-------"+car_number);
        if (state==1){
            LincensePlate plate = new LincensePlate();
            plate.setLpLincensePlateIdCar(car_number);
            plate.setLpOrderState("支付成功");
            lincensePlateService.updateByPlate(plate);
        }

        JSONObject res = new JSONObject();
        res.put("state",state);
        res.put("trade_no",trade_no);
        res.put("data",data);
        res.put("sign",sign);
        System.out.println("res.toJSONString()-------"+res.toJSONString());
        return res.toJSONString();
    }


    /**
     * 查询订单实时金额通知(车场，出入口)(临时车扫码支付)
     * @param jsonParam
     * @return
     */
    @PostMapping("/checkRent")
    @ResponseBody
    public JSONObject checkRent(HttpServletRequest request, HttpServletResponse resp,@RequestBody JSONObject jsonParam) throws Exception {
        logger.info("checkOrderRent ======================泊链支付=======================");
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
        logger.info("lp最后准备支付的车辆信息：" + JSONObject.toJSON(lp));
        String s = DateUtils.afterSixSecond(new Date());//获取当前时间6分半之后的时间，作为支付超时时间
        /*json.put("orderId", lp.getLpOrderId());
        json.put("payment", rent);
        json.put("TIMEOUT",s);*/
        logger.info("车牌号:  " + lp.getLpLincensePlateIdCar() + "准备调起支付");
        logger.info("orderId:  " + lp.getLpOrderId());
        logger.info("payment:  " + rent);
        logger.info("TIMEOUT:  " + s);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(lp.getLpInboundTime());
        json.put("amount",lp.getLpParkingCost());
        json.put("lpInboundTime",format);
        json.put("lpParkingName",lp.getLpParkingName());
        json.put("lpLincensePlateIdCar",lp.getLpLincensePlateIdCar());

        //return  paypark(request,/,json);
        return json;
    }

    /**
     * 预付款出场
     * @param jsonParam
     * @return
     */
    @PostMapping("/payAdvance")
    @ResponseBody
    @CrossOrigin
    public JSONObject payAdvance(HttpServletRequest request, HttpServletResponse resp,@RequestBody JSONObject jsonParam) throws Exception{
        logger.info("payAdvanceCode getParam Start=====================泊链支付==================");
        Integer pac_parkId;
        String car_number = (String) jsonParam.get("car_number");
        List<LincensePlate> lincensePlateList = lincensePlateService.selectByLincensePlate(car_number);
        LincensePlate lincense = new LincensePlate();
        JSONObject json = new JSONObject();
        if (lincensePlateList.size() > 0) {
            //查询会员表，确定会员属性，返回不同内容
            LincensePlate lp = lincensePlateList.get(0);
            logger.info("lp准备预付款出场的车辆信息：" + JSONObject.toJSON(lp));
            Member member = new Member();
            member.setLincensePlateId(car_number);
            member.setAreaName(lp.getLpCarType());
            member.setParkId(Integer.valueOf(lp.getLpParkingName()));
            pac_parkId = Integer.valueOf(lp.getLpParkingName());
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
            }
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
        Double rent = getRent(tempDate, lincense.getLpInboundTime(), Integer.valueOf(lincense.getLpParkingName()));
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
        lincensePlateService.updateByPlateForAdvance(lincense);//修改在场表订单号
        jinshiCCBPayController.updateOrderId(car_number,orderIdByUUId);//修改CCB表订单号
        String s = DateUtils.afterSixSecond(new Date()); //获取当前时间6分半之后的时间，作为支付超时时间
        /*json.put("TIMEOUT",s);
        json.put("orderId", orderIdByUUId);
        json.put("payment", rent);*/
        logger.info(car_number + " 预付款调起支付");
        logger.info("预付款订单号：" + orderIdByUUId);
        logger.info("预付款费用：" + rent);
        logger.info("预付款超时时间：" + s);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(lincense.getLpInboundTime());
        DecimalFormat df = new DecimalFormat("#.00");
        String s2 = df.format(rent);
        String s1 = String.valueOf(rent);
        json.put("amount",s2);
        json.put("lpInboundTime",format);
        json.put("lpParkingName",lincense.getLpParkingName());
        json.put("lpLincensePlateIdCar",lincense.getLpLincensePlateIdCar());
        System.out.println("json是+++++"+json);

        return paypark(request,resp,json);
    }

}

