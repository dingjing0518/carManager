package com.jinshi.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.*;
import com.jinshi.service.*;
import com.jinshi.util.CameraParam;
import com.jinshi.util.GlobalVariable;
import com.jinshi.util.SmsController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cameraInit")
@CrossOrigin
@Api(tags = "出入管理")
public class CameraInitTestController {

    private static Logger logger = Logger.getLogger(CameraInitTestController.class.getName());
    @Autowired
    private CameraInitTestService cameraInitTestService;
    @Autowired
    private JinshiCameraService jinshiCameraService;
    @Autowired
    private LincensePlateService lincensePlateService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private LincensePlateHistoryService lincensePlateHistoryService;

    @RequestMapping(value = "/ManualCFixOpenGate", method = RequestMethod.POST)
    @ResponseBody
    public String ManualCFixOpenGate(@RequestBody JSONObject jsonParam) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject resJO = new JSONObject();
        String license = (String) jsonParam.get("license");
        String tHandleStr = (String) jsonParam.get("tHandle");
        int tHandle = Integer.parseInt(tHandleStr);
        if(tHandleStr.equals("-1")){
            resJO.put("resMessage","请先选择摄像机");
            return resJO.toString();
        }
        String datatime = (String) jsonParam.get("datatime");
        Date parse = sdf.parse(datatime);
        String orderIdByUUId = GlobalVariable.util.getOrderIdByUUIdAddPlate(license);
        JinshiParkingSetup jinshiParkingSetup = GlobalVariable.jinshiParkingSetup;
        JinshiCameras jinshiCameras = CameraParam.cameraMap.get(tHandle);
        jinshiCameras.setCameraLicenseId(license);
        jinshiCameras.setCameraInboundTime(parse);
//        Integer lgId = (Integer) jsonParam.get("lgId");//todo
        Integer lgType = (Integer) jsonParam.get("lgType");
        GlobalVariable.util.dataProcessNew(license,(byte)0,(byte)1,new Date(),orderIdByUUId,tHandle,jinshiCameras.getJcArea(),null,String.valueOf(lgType));
        if (jinshiParkingSetup.getJpsPayType() == 0) {
            GlobalVariable.util.Carin(license,orderIdByUUId,parse);
        } else if (jinshiParkingSetup.getJpsPayType() == 1) {
            GlobalVariable.util.carInNotice(license,new Date(),GlobalVariable.parkId,GlobalVariable.agentId,orderIdByUUId,tHandle,lgType);
        }
        JinshiCamerasSpare jinshiCamerasSpare = GlobalVariable.util.selectRealCameraId(tHandle);
        GlobalVariable.util.openGate(jinshiCamerasSpare.getJcsSpareThandle());
        resJO.put("resMessage","执行成功");
        return resJO.toJSONString();
    }

    /**
     * 人工补录不抬杆
     * @param jsonParam
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/ManualCFix", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "出入管理---人工补录不抬杆")
    public String ManualCFix(@RequestBody JSONObject jsonParam) throws ParseException {
        logger.info("人工补录不抬杆：" + jsonParam.toJSONString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject resJO = new JSONObject();
        String license = (String) jsonParam.get("license");
        String tHandleStr = (String) jsonParam.get("tHandle");
        int tHandle = Integer.parseInt(tHandleStr);
        if(tHandleStr.equals("-1")){
            resJO.put("resMessage","请先选择摄像机");
            return resJO.toString();
        }
        String datatime = (String) jsonParam.get("datatime");
        Date parse = sdf.parse(datatime);
        String orderIdByUUId = GlobalVariable.util.getOrderIdByUUIdAddPlate(license);
        JinshiParkingSetup jinshiParkingSetup = GlobalVariable.jinshiParkingSetup;
        JinshiCameras jinshiCameras = CameraParam.cameraMap.get(tHandle);
        jinshiCameras.setCameraLicenseId(license);
        jinshiCameras.setCameraInboundTime(parse);
        List<LincensePlate> lincensePlateList = GlobalVariable.util.selectByLincensePlate(license);
        if (lincensePlateList.size() > 0) {
            GlobalVariable.util.insertplateHistoryManual(lincensePlateList);
            GlobalVariable.util.deletePlates(lincensePlateList);
        }
        GlobalVariable.util.dataProcess(license,(byte)0,(byte)1,parse,orderIdByUUId,tHandle,jinshiCameras.getJcArea());
        if (jinshiParkingSetup.getJpsPayType() == 0) {
            GlobalVariable.util.Carin(license,orderIdByUUId,parse);
        } else if (jinshiParkingSetup.getJpsPayType() == 1) {
            GlobalVariable.util.carInNotice(license,new Date(),GlobalVariable.parkId,GlobalVariable.agentId,orderIdByUUId,tHandle,0);
        }
        resJO.put("resMessage","成功");
        return resJO.toJSONString();
    }

    /**
     * 点击手动出场以后---弹框---收费抬杆按钮
     * @param jsonParam
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/getRentOpenGate", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "出入管理---点击手动出场以后---弹框---收费抬杆按钮")
    public String getRentOpenGate(@RequestBody JSONObject jsonParam) throws ParseException {
        logger.info("点击手动出场以后---弹框---收费抬杆按钮：" + jsonParam.toJSONString());
        String license = (String) jsonParam.get("license");
        String tHandle = (String) jsonParam.get("tHandle");
        Integer i = Integer.parseInt(tHandle);
        JinshiCameras jinshiCameras = CameraParam.cameraMap.get(i);
        JinshiParkingSetup jinshiParkingSetup = GlobalVariable.jinshiParkingSetup;
        Integer rent = (Integer) jsonParam.get("rent");
        double v = Double.parseDouble(rent.toString());
        LincensePlate lincese = GlobalVariable.util.findLincese(license);
        Integer dateOften = getDateOften(new Date(), lincese.getLpInboundTime()); //计算停车时长
        if (jinshiParkingSetup.getJpsPayType() == 0) {
            GlobalVariable.util.Carout(license, GlobalVariable.util.getUnixTime(lincese.getLpInboundTime()), lincese.getLpOrderId(), v);
        } else if (jinshiParkingSetup.getJpsPayType() == 1) {
            GlobalVariable.util.carOutNotice(license,GlobalVariable.parkId, Double.valueOf(rent),dateOften,new Date(),Integer.valueOf(tHandle),"人工收费出场");
        }
        JinshiCamerasSpare jinshiCamerasSpare = GlobalVariable.util.selectRealCameraId(i);
        GlobalVariable.util.openGate(jinshiCamerasSpare.getJcsSpareThandle());
//        GlobalVariable.util.updataProcess(lincese,license,new Date(),v,i,"支付成功","手动出场",Integer.valueOf(lincese.getLpParkingOften()));
        GlobalVariable.util.updataProcess(lincese,license,new Date(),v,i,"支付成功","人工收费出场",dateOften);
        jinshiCameras.setCameraLicenseId(lincese.getLpLincensePlateIdCar());
        jinshiCameras.setCameraInboundTime(lincese.getLpInboundTime());
        jinshiCameras.setCameraDepartureTime(new Date());
        jinshiCameras.setCameraRent(v);
        jinshiCameras.setPlatePayState("临时车：手动收费出场");
        CameraParam.cameraMap.put(i,jinshiCameras);
        GlobalVariable.util.updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
        return "success";
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

    /**
     * 出入管理手动出场按钮
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/ManualAFormSubmit", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "出入管理---手动出场")
    public String ManualAFormSubmit(@RequestBody JSONObject jsonParam){
        logger.info("出入管理手动出场按钮：" + jsonParam.toJSONString());
        DateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject resJO = new JSONObject();
        String license = (String) jsonParam.get("license");
        String tHandle = (String) jsonParam.get("tHandle");
        if(tHandle.equals("-1")){
            resJO.put("resMessage","请先选择摄像机");
            return resJO.toString();
        }
        LincensePlate lp = new LincensePlate();
        Member mb = new Member();
        mb.setLincensePlateId(license);
        lp.setLpLincensePlateIdCar(license);
        List<LincensePlate> lincensePlates = lincensePlateService.selectByLincense(lp);
        LincensePlate lincensePlate = new LincensePlate();
        if(lincensePlates.size()>0){
            lincensePlate = lincensePlates.get(0);
        }
//        LincensePlate lincensePlate = lincensePlateService.selectByLincense(lp);
        if(lincensePlate!=null){
            if(lincensePlate.getLpDepartureTime()==null){
                resJO.put("resMessage","无出口记录，请重新识别车辆");
                return resJO.toString();
            }
        }
        Double rent = CameraParam.getRent(new Date(),lincensePlate.getLpInboundTime());
        Member member1 = memberService.checkMemberByLincese(mb);
        Date lpInboundTime = lincensePlate.getLpInboundTime();
        String lpInboundTimeStr = df.format(lpInboundTime);
        JinshiCameras jinshiCameras = jinshiCameraService.selectByParkIdAndtHandle(GlobalVariable.parkId, tHandle);
        resJO.put("lpInboundTime",lpInboundTimeStr);
        resJO.put("license",license);
        resJO.put("lpInboundCname",jinshiCameras.getJcName());
        if(member1!=null){
            String serviceType = member1.getServiceType();
            resJO.put("carType",serviceType);
            resJO.put("rent",0.0);
        }else{
            resJO.put("carType","临时车");
            resJO.put("rent",rent);
        }
        return resJO.toString();
    }

//    @RequestMapping(value = "getGlobalVariable", method = RequestMethod.POST)
//    @ResponseBody
//    public String getGlobalVariable(@RequestBody JSONObject jsonParam) throws ParseException {
//        String parkId = (String) jsonParam.get("parkId");
//        List<JinshiCameras> list = jinshiCameraService.selectByParkIdDesc(parkId);
//        DateFormat format2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        JSONObject resJo = new JSONObject();
//        if (list.size() > 0) {
//            for (JinshiCameras jinshiCameras : list) {
//                String carType = "";
//                String lincenseType = "";
//                if(jinshiCameras.getCameraMemberType()!=null){
//                    if(jinshiCameras.getCameraMemberType().equals("1")){
//                        carType = "会员车";
//                    }else if(jinshiCameras.getCameraMemberType().equals("0")){
//                        carType = "临停车";
//                    }else if(jinshiCameras.getCameraMemberType().equals("2")){
//                        carType = "无效卡";
//                    }
//                }else{
//                    carType = "无信息";
//                }
//                if(jinshiCameras.getCameraLincenseType()!=null){
//                    if(jinshiCameras.getCameraLincenseType().equals("1")){
//                        lincenseType = "汽油车";
//                    }else if(jinshiCameras.getCameraLincenseType().equals("11")){
//                        lincenseType = "能源车";
//                    }else if(jinshiCameras.getCameraLincenseType().equals("2")){
//                        lincenseType = "蓝牌能源车";
//                    }else if(jinshiCameras.getCameraLincenseType().equals("3")){
//                        lincenseType = "黄牌车";
//                    }
//                }else{
//                    lincenseType = "无信息";
//                }
//                resJo.put("payState",jinshiCameras.getPlatePayState());
//                resJo.put("lincenseType",lincenseType);
//                resJo.put("memberType",carType);
//                if(jinshiCameras.getCameraOften()!=null){
//                    resJo.put("carOften",jinshiCameras.getCameraOften().toString()+" 分钟");
//                }else{
//                    resJo.put("carOften","无信息");
//                }
//                resJo.put("cameraLicenseId", jinshiCameras.getCameraLicenseId());
//                if(jinshiCameras.getCameraInboundTime()!=null){
//                    resJo.put("cameraInboundTime", format2.format(jinshiCameras.getCameraInboundTime()));
//                }else{resJo.put("cameraInboundTime", "无信息");}
//                if(jinshiCameras.getCameraDepartureTime()!=null){
//                    resJo.put("cameraDepartureTime", format2.format(jinshiCameras.getCameraDepartureTime()));
//                }else{resJo.put("cameraDepartureTime", "无信息");}
//                if(jinshiCameras.getCameraRent()!=null){
//                    resJo.put("cameraRent", jinshiCameras.getCameraRent());
//                }else{resJo.put("cameraRent", "无信息");}
//                resJo.put("cameraName",jinshiCameras.getJcName());
//                resJo.put("cameraIpAddress",jinshiCameras.getJcIpAddress());
//                resJo.put("access",jinshiCameras.getJcAccess());
//                resJo.put("thandle",jinshiCameras.getJcThandle());
//                resJo.put("voiceCode",jinshiCameras.getVoiceCode());
//                resJo.put("picPath",jinshiCameras.getPicPath());
//            }
//        }
//        return resJo.toString();
//    }

    @RequestMapping(value = "/getGlobalVariable", method = RequestMethod.POST)
    @ResponseBody
    public String getGlobalVariable(@RequestBody JSONObject jsonParam) throws ParseException {
        Integer i1 = Integer.parseInt((String) jsonParam.get("listA"));
        Integer i2 = Integer.parseInt((String) jsonParam.get("listB"));
        Integer i3 = Integer.parseInt((String) jsonParam.get("listC"));
        Integer i4 = Integer.parseInt((String) jsonParam.get("listD"));
        Integer i5 = Integer.parseInt((String) jsonParam.get("listE"));
        Integer i6 = Integer.parseInt((String) jsonParam.get("listF"));
        Integer i7 = Integer.parseInt((String) jsonParam.get("listG"));
        Integer i8 = Integer.parseInt((String) jsonParam.get("listH"));
        DateFormat format2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject resJo = new JSONObject();
        reFreshGlobalVariable(i1,i2,i3,i4,i5,i6,i7,i8);
//        if(i1!=-1&&i2!=-1&&i3!=-1&&i4!=-1){
        if(i1!=-1){
            JinshiCameras jinshiCameras = CameraParam.cameraMap.get(i1);
            String carType = "";
            String lincenseType = "";
            if(jinshiCameras.getCameraMemberType()!=null){
                if(jinshiCameras.getCameraMemberType().equals("1")){
                    Member member = new Member();
                    member.setLincensePlateId(jinshiCameras.getCameraLicenseId());
                    member.setAreaNumber(jinshiCameras.getCameraAreaCode());
                    member.setParkId(GlobalVariable.parkId);
                    List<Member> members = memberService.checkMemberByLinceseForUtil(member);
                    if (members.size() > 0) {
                        Member member1 = members.get(0);
                        carType = member1.getServiceType();
                    } else {
                        carType = "会员车";
                    }
                }else if(jinshiCameras.getCameraMemberType().equals("0")){
                    carType = "临停车";
                }else if(jinshiCameras.getCameraMemberType().equals("2")){
                    carType = "无效卡";
                }
            }else{
                carType = "无信息";
            }
            if(jinshiCameras.getCameraLincenseType()!=null){
                if(jinshiCameras.getCameraLincenseType().equals("1")){
                    lincenseType = "汽油车";
                }else if(jinshiCameras.getCameraLincenseType().equals("11")){
                    lincenseType = "能源车";
                }else if(jinshiCameras.getCameraLincenseType().equals("2")){
                    lincenseType = "蓝牌能源车";
                }else if(jinshiCameras.getCameraLincenseType().equals("3")){
                    lincenseType = "黄牌车";
                }else if(jinshiCameras.getCameraLincenseType().equals("5")){
                    lincenseType = "特种车";
                }
            }else{
                lincenseType = "无信息";
            }
            resJo.put("payStateA",jinshiCameras.getPlatePayState());
            resJo.put("lincenseAType",lincenseType);
            resJo.put("memberAType",carType);
            if(jinshiCameras.getCameraOften()!=null){
                resJo.put("carAOften",jinshiCameras.getCameraOften().toString()+" 分钟");
            }else{
                resJo.put("carAOften","无信息");
            }
            resJo.put("cameraALicenseId", jinshiCameras.getCameraLicenseId());
            if(jinshiCameras.getCameraInboundTime()!=null){
                resJo.put("cameraAInboundTime", format2.format(jinshiCameras.getCameraInboundTime()));
            }else{resJo.put("cameraAInboundTime", "无信息");}
            if(jinshiCameras.getCameraDepartureTime()!=null){
                resJo.put("cameraADepartureTime", format2.format(jinshiCameras.getCameraDepartureTime()));
            }else{resJo.put("cameraADepartureTime", "无信息");}
            if(jinshiCameras.getCameraRent()!=null){
                resJo.put("cameraARent", jinshiCameras.getCameraRent());
            }else{resJo.put("cameraARent", "无信息");}
//            resJo.put("cameraARent", jinshiCameras.getCameraRent());
            resJo.put("cameraAName",jinshiCameras.getJcName());
            resJo.put("cameraAIpAddress",jinshiCameras.getJcIpAddress());
            resJo.put("accessA",jinshiCameras.getJcAccess());
            resJo.put("thandleA",jinshiCameras.getJcThandle());
            resJo.put("voiceCode",jinshiCameras.getVoiceCode());
            resJo.put("picPathA",jinshiCameras.getPicPath());
            JinshiCameras cameras = jinshiCameraService.selectByParkIdAndtHandle(Integer.valueOf(jinshiCameras.getJcParking()),jinshiCameras.getJcThandle());
            resJo.put("jcIsTypeA",cameras.getJcIsType());
        }
        if(i2!=-1){
            JinshiCameras jinshiCameras1 = CameraParam.cameraMap.get(i2);
            String carType = "";
            String lincenseType = "";
            if(jinshiCameras1.getCameraMemberType()!=null){
                if(jinshiCameras1.getCameraMemberType().equals("1")){
//                    carType = "会员车";
                    Member member = new Member();
                    member.setLincensePlateId(jinshiCameras1.getCameraLicenseId());
                    member.setAreaNumber(jinshiCameras1.getCameraAreaCode());
                    member.setParkId(GlobalVariable.parkId);
                    List<Member> members = memberService.checkMemberByLinceseForUtil(member);
                    if (members.size() > 0) {
                        Member member1 = members.get(0);
                        carType = member1.getServiceType();
                    } else {
                        carType = "会员车";
                    }
                }else if(jinshiCameras1.getCameraMemberType().equals("0")){
                    carType = "临停车";
                }else if(jinshiCameras1.getCameraMemberType().equals("2")){
                    carType = "无效卡";
                }
            }else{
                carType = "无信息";
            }
            if(jinshiCameras1.getCameraLincenseType()!=null){
                if(jinshiCameras1.getCameraLincenseType().equals("1")){
                    lincenseType = "汽油车";
                }else if(jinshiCameras1.getCameraLincenseType().equals("11")){
                    lincenseType = "能源车";
                }else if(jinshiCameras1.getCameraLincenseType().equals("2")){
                    lincenseType = "蓝牌能源车";
                }else if(jinshiCameras1.getCameraLincenseType().equals("3")){
                    lincenseType = "黄牌车";
                }else if(jinshiCameras1.getCameraLincenseType().equals("5")){
                    lincenseType = "特种车";
                }
            }else{
                lincenseType = "无信息";
            }
            resJo.put("payStateB",jinshiCameras1.getPlatePayState());
            resJo.put("lincenseBType",lincenseType);
            if(jinshiCameras1.getCameraOften()!=null){
                resJo.put("carBOften",jinshiCameras1.getCameraOften().toString()+" 分钟");
            }else{resJo.put("carBOften","无信息");}
            resJo.put("memberBType",carType);
            resJo.put("cameraBLicenseId", jinshiCameras1.getCameraLicenseId());
            if(jinshiCameras1.getCameraInboundTime()!=null){
                resJo.put("cameraBInboundTime", format2.format(jinshiCameras1.getCameraInboundTime()));
            }else{resJo.put("cameraBInboundTime","无信息");}
            if(jinshiCameras1.getCameraDepartureTime()!=null){
                resJo.put("cameraBDepartureTime", format2.format(jinshiCameras1.getCameraDepartureTime()));
            }else{resJo.put("cameraBDepartureTime","无信息");}
            if(jinshiCameras1.getCameraRent()!=null){
                resJo.put("cameraBRent", jinshiCameras1.getCameraRent());
            }else{resJo.put("cameraBRent","无信息");}
//            resJo.put("cameraBRent", jinshiCameras1.getCameraRent());
            resJo.put("cameraBName",jinshiCameras1.getJcName());
            resJo.put("cameraBIpAddress",jinshiCameras1.getJcIpAddress());
            resJo.put("accessB",jinshiCameras1.getJcAccess());
            resJo.put("thandleB",jinshiCameras1.getJcThandle());
            resJo.put("voiceCode",jinshiCameras1.getVoiceCode());
            resJo.put("picPathB",jinshiCameras1.getPicPath());
            JinshiCameras cameras = jinshiCameraService.selectByParkIdAndtHandle(Integer.valueOf(jinshiCameras1.getJcParking()),jinshiCameras1.getJcThandle());
            resJo.put("jcIsTypeB",cameras.getJcIsType());
        }
        if(i3!=-1){
            JinshiCameras jinshiCameras2 = CameraParam.cameraMap.get(i3);
            String carType = "";
            String lincenseType = "";
            if(jinshiCameras2.getCameraMemberType()!=null){
                if(jinshiCameras2.getCameraMemberType().equals("1")){
//                    carType = "会员车";
                    Member member = new Member();
                    member.setLincensePlateId(jinshiCameras2.getCameraLicenseId());
                    member.setAreaNumber(jinshiCameras2.getCameraAreaCode());
                    member.setParkId(GlobalVariable.parkId);
                    List<Member> members = memberService.checkMemberByLinceseForUtil(member);
                    if (members.size() > 0) {
                        Member member1 = members.get(0);
                        carType = member1.getServiceType();
                    } else {
                        carType = "会员车";
                    }
                }else if(jinshiCameras2.getCameraMemberType().equals("0")){
                    carType = "临停车";
                }else if(jinshiCameras2.getCameraMemberType().equals("2")){
                    carType = "无效卡";
                }
            }else{
                carType = "无信息";
            }
            if(jinshiCameras2.getCameraLincenseType()!=null){
                if(jinshiCameras2.getCameraLincenseType().equals("1")){
                    lincenseType = "汽油车";
                }else if(jinshiCameras2.getCameraLincenseType().equals("11")){
                    lincenseType = "能源车";
                }else if(jinshiCameras2.getCameraLincenseType().equals("2")){
                    lincenseType = "蓝牌能源车";
                }else if(jinshiCameras2.getCameraLincenseType().equals("3")){
                    lincenseType = "黄牌车";
                }else if(jinshiCameras2.getCameraLincenseType().equals("5")){
                    lincenseType = "特种车";
                }
            }else{
                lincenseType = "无信息";
            }
            resJo.put("lincenseCType",lincenseType);
            resJo.put("memberCType",carType);
            if(jinshiCameras2.getCameraInboundTime()!=null){
                resJo.put("cameraCInboundTime", format2.format(jinshiCameras2.getCameraInboundTime()));
            }else{resJo.put("cameraCInboundTime", "无信息");}
            resJo.put("cameraCLicenseId", jinshiCameras2.getCameraLicenseId());
            resJo.put("cameraCLincenseType", lincenseType);
            resJo.put("cameraCMemberType", jinshiCameras2.getCameraMemberType());
            resJo.put("cameraCName",jinshiCameras2.getJcName());
            resJo.put("cameraCIpAddress",jinshiCameras2.getJcIpAddress());
            resJo.put("accessC",jinshiCameras2.getJcAccess());
            resJo.put("thandleC",jinshiCameras2.getJcThandle());
            resJo.put("picPathC",jinshiCameras2.getPicPath());
            JinshiCameras cameras = jinshiCameraService.selectByParkIdAndtHandle(Integer.valueOf(jinshiCameras2.getJcParking()),jinshiCameras2.getJcThandle());
            resJo.put("jcIsTypeE",cameras.getJcIsType());
        }
        if(i4!=-1){
            JinshiCameras jinshiCameras3 = CameraParam.cameraMap.get(i4);
            String carType = "";
            String lincenseType = "";
            if(jinshiCameras3.getCameraMemberType()!=null){
                if(jinshiCameras3.getCameraMemberType().equals("1")){
//                    carType = "会员车";
                    Member member = new Member();
                    member.setLincensePlateId(jinshiCameras3.getCameraLicenseId());
                    member.setAreaNumber(jinshiCameras3.getCameraAreaCode());
                    member.setParkId(GlobalVariable.parkId);
                    List<Member> members = memberService.checkMemberByLinceseForUtil(member);
                    if (members.size() > 0) {
                        Member member1 = members.get(0);
                        carType = member1.getServiceType();
                    } else {
                        carType = "会员车";
                    }
                }else if(jinshiCameras3.getCameraMemberType().equals("0")){
                    carType = "临停车";
                }else if(jinshiCameras3.getCameraMemberType().equals("2")){
                    carType = "无效卡";
                }
            }else{carType = "无信息";}
            if(jinshiCameras3.getCameraLincenseType()!=null){
                if(jinshiCameras3.getCameraLincenseType().equals("1")){
                    lincenseType = "汽油车";
                }else if(jinshiCameras3.getCameraLincenseType().equals("11")){
                    lincenseType = "能源车";
                }else if(jinshiCameras3.getCameraLincenseType().equals("2")){
                    lincenseType = "蓝牌能源车";
                }else if(jinshiCameras3.getCameraLincenseType().equals("3")){
                    lincenseType = "黄牌车";
                }else if(jinshiCameras3.getCameraLincenseType().equals("5")){
                    lincenseType = "特种车";
                }
            }else{lincenseType = "无信息";}
            resJo.put("lincenseDType",lincenseType);
            resJo.put("memberDType",carType);
            if(jinshiCameras3.getCameraInboundTime()!=null){
                resJo.put("cameraDInboundTime", format2.format(jinshiCameras3.getCameraInboundTime()));
            }else{resJo.put("cameraDInboundTime", "无信息");}
            resJo.put("cameraDLincenseType", jinshiCameras3.getCameraLincenseType());
            resJo.put("cameraDMemberType", jinshiCameras3.getCameraMemberType());
            resJo.put("cameraDLicenseId", jinshiCameras3.getCameraLicenseId());
            resJo.put("cameraDName",jinshiCameras3.getJcName());
            resJo.put("cameraDIpAddress",jinshiCameras3.getJcIpAddress());
            resJo.put("accessD",jinshiCameras3.getJcAccess());
            resJo.put("thandleD",jinshiCameras3.getJcThandle());
            resJo.put("picPathD",jinshiCameras3.getPicPath());
            JinshiCameras cameras = jinshiCameraService.selectByParkIdAndtHandle(Integer.valueOf(jinshiCameras3.getJcParking()),jinshiCameras3.getJcThandle());
            resJo.put("jcIsTypeD",cameras.getJcIsType());
        }
        if(i5!=-1){
            JinshiCameras jinshiCameras4 = CameraParam.cameraMap.get(i5);
            String carType = "";
            String lincenseType = "";
            if(jinshiCameras4.getCameraMemberType()!=null){
                if(jinshiCameras4.getCameraMemberType().equals("1")){
//                    carType = "会员车";
                    Member member = new Member();
                    member.setLincensePlateId(jinshiCameras4.getCameraLicenseId());
                    member.setAreaNumber(jinshiCameras4.getCameraAreaCode());
                    member.setParkId(GlobalVariable.parkId);
                    List<Member> members = memberService.checkMemberByLinceseForUtil(member);
                    if (members.size() > 0) {
                        Member member1 = members.get(0);
                        carType = member1.getServiceType();
                    } else {
                        carType = "会员车";
                    }
                }else if(jinshiCameras4.getCameraMemberType().equals("0")){
                    carType = "临停车";
                }else if(jinshiCameras4.getCameraMemberType().equals("2")){
                    carType = "无效卡";
                }
            }else{
                carType = "无信息";
            }
            if(jinshiCameras4.getCameraLincenseType()!=null){
                if(jinshiCameras4.getCameraLincenseType().equals("1")){
                    lincenseType = "汽油车";
                }else if(jinshiCameras4.getCameraLincenseType().equals("11")){
                    lincenseType = "能源车";
                }else if(jinshiCameras4.getCameraLincenseType().equals("2")){
                    lincenseType = "蓝牌能源车";
                }else if(jinshiCameras4.getCameraLincenseType().equals("3")){
                    lincenseType = "黄牌车";
                }else if(jinshiCameras4.getCameraLincenseType().equals("5")){
                    lincenseType = "特种车";
                }
            }else{
                lincenseType = "无信息";
            }
            resJo.put("payStateE",jinshiCameras4.getPlatePayState());
            resJo.put("lincenseEType",lincenseType);
            resJo.put("memberEType",carType);
            if(jinshiCameras4.getCameraOften()!=null){
                resJo.put("carEOften",jinshiCameras4.getCameraOften().toString()+" 分钟");
            }else{
                resJo.put("carEOften","无信息");
            }
            resJo.put("cameraELicenseId", jinshiCameras4.getCameraLicenseId());
            if(jinshiCameras4.getCameraInboundTime()!=null){
                resJo.put("cameraEInboundTime", format2.format(jinshiCameras4.getCameraInboundTime()));
            }else{resJo.put("cameraEInboundTime", "无信息");}
            if(jinshiCameras4.getCameraDepartureTime()!=null){
                resJo.put("cameraEDepartureTime", format2.format(jinshiCameras4.getCameraDepartureTime()));
            }else{resJo.put("cameraEDepartureTime", "无信息");}
            if(jinshiCameras4.getCameraRent()!=null){
                resJo.put("cameraERent", jinshiCameras4.getCameraRent());
            }else{resJo.put("cameraERent", "无信息");}
//            resJo.put("cameraARent", jinshiCameras.getCameraRent());
            resJo.put("cameraEName",jinshiCameras4.getJcName());
            resJo.put("cameraEIpAddress",jinshiCameras4.getJcIpAddress());
            resJo.put("accessE",jinshiCameras4.getJcAccess());
            resJo.put("thandleE",jinshiCameras4.getJcThandle());
            resJo.put("voiceCode",jinshiCameras4.getVoiceCode());
            resJo.put("picPathE",jinshiCameras4.getPicPath());
            JinshiCameras cameras = jinshiCameraService.selectByParkIdAndtHandle(Integer.valueOf(jinshiCameras4.getJcParking()),jinshiCameras4.getJcThandle());
            resJo.put("jcIsTypeC",cameras.getJcIsType());
        }
        if(i6!=-1){
            JinshiCameras jinshiCameras5 = CameraParam.cameraMap.get(i6);
            String carType = "";
            String lincenseType = "";
            if(jinshiCameras5.getCameraMemberType()!=null){
                if(jinshiCameras5.getCameraMemberType().equals("1")){
//                    carType = "会员车";
                    Member member = new Member();
                    member.setLincensePlateId(jinshiCameras5.getCameraLicenseId());
                    member.setAreaNumber(jinshiCameras5.getCameraAreaCode());
                    member.setParkId(GlobalVariable.parkId);
                    List<Member> members = memberService.checkMemberByLinceseForUtil(member);
                    if (members.size() > 0) {
                        Member member1 = members.get(0);
                        carType = member1.getServiceType();
                    } else {
                        carType = "会员车";
                    }
                }else if(jinshiCameras5.getCameraMemberType().equals("0")){
                    carType = "临停车";
                }else if(jinshiCameras5.getCameraMemberType().equals("2")){
                    carType = "无效卡";
                }
            }else{
                carType = "无信息";
            }
            if(jinshiCameras5.getCameraLincenseType()!=null){
                if(jinshiCameras5.getCameraLincenseType().equals("1")){
                    lincenseType = "汽油车";
                }else if(jinshiCameras5.getCameraLincenseType().equals("11")){
                    lincenseType = "能源车";
                }else if(jinshiCameras5.getCameraLincenseType().equals("2")){
                    lincenseType = "蓝牌能源车";
                }else if(jinshiCameras5.getCameraLincenseType().equals("3")){
                    lincenseType = "黄牌车";
                }else if(jinshiCameras5.getCameraLincenseType().equals("5")){
                    lincenseType = "特种车";
                }
            }else{
                lincenseType = "无信息";
            }
            resJo.put("lincenseFType",lincenseType);
            resJo.put("memberFType",carType);
            if(jinshiCameras5.getCameraInboundTime()!=null){
                resJo.put("cameraFInboundTime", format2.format(jinshiCameras5.getCameraInboundTime()));
            }else{resJo.put("cameraFInboundTime", "无信息");}
            resJo.put("cameraFLicenseId", jinshiCameras5.getCameraLicenseId());
            resJo.put("cameraFLincenseType", lincenseType);
            resJo.put("cameraFMemberType", jinshiCameras5.getCameraMemberType());
            resJo.put("cameraFName",jinshiCameras5.getJcName());
            resJo.put("cameraFIpAddress",jinshiCameras5.getJcIpAddress());
            resJo.put("accessF",jinshiCameras5.getJcAccess());
            resJo.put("thandleF",jinshiCameras5.getJcThandle());
            resJo.put("picPathF",jinshiCameras5.getPicPath());
            JinshiCameras cameras = jinshiCameraService.selectByParkIdAndtHandle(Integer.valueOf(jinshiCameras5.getJcParking()),jinshiCameras5.getJcThandle());
            resJo.put("jcIsTypeF",cameras.getJcIsType());
        }
        if(i7!=-1){
            JinshiCameras jinshiCameras6 = CameraParam.cameraMap.get(i7);
            String carType = "";
            String lincenseType = "";
            if(jinshiCameras6.getCameraMemberType()!=null){
                if(jinshiCameras6.getCameraMemberType().equals("1")){
//                    carType = "会员车";
                    Member member = new Member();
                    member.setLincensePlateId(jinshiCameras6.getCameraLicenseId());
                    member.setAreaNumber(jinshiCameras6.getCameraAreaCode());
                    member.setParkId(GlobalVariable.parkId);
                    List<Member> members = memberService.checkMemberByLinceseForUtil(member);
                    if (members.size() > 0) {
                        Member member1 = members.get(0);
                        carType = member1.getServiceType();
                    } else {
                        carType = "会员车";
                    }
                }else if(jinshiCameras6.getCameraMemberType().equals("0")){
                    carType = "临停车";
                }else if(jinshiCameras6.getCameraMemberType().equals("2")){
                    carType = "无效卡";
                }
            }else{
                carType = "无信息";
            }
            if(jinshiCameras6.getCameraLincenseType()!=null){
                if(jinshiCameras6.getCameraLincenseType().equals("1")){
                    lincenseType = "汽油车";
                }else if(jinshiCameras6.getCameraLincenseType().equals("11")){
                    lincenseType = "能源车";
                }else if(jinshiCameras6.getCameraLincenseType().equals("2")){
                    lincenseType = "蓝牌能源车";
                }else if(jinshiCameras6.getCameraLincenseType().equals("3")){
                    lincenseType = "黄牌车";
                }else if(jinshiCameras6.getCameraLincenseType().equals("5")){
                    lincenseType = "特种车";
                }
            }else{
                lincenseType = "无信息";
            }
            resJo.put("payStateG",jinshiCameras6.getPlatePayState());
            resJo.put("lincenseGType",lincenseType);
            resJo.put("memberGType",carType);
            if(jinshiCameras6.getCameraOften()!=null){
                resJo.put("carGOften",jinshiCameras6.getCameraOften().toString()+" 分钟");
            }else{
                resJo.put("carGOften","无信息");
            }
            resJo.put("cameraGLicenseId", jinshiCameras6.getCameraLicenseId());
            if(jinshiCameras6.getCameraInboundTime()!=null){
                resJo.put("cameraGInboundTime", format2.format(jinshiCameras6.getCameraInboundTime()));
            }else{resJo.put("cameraGInboundTime", "无信息");}
            if(jinshiCameras6.getCameraDepartureTime()!=null){
                resJo.put("cameraGDepartureTime", format2.format(jinshiCameras6.getCameraDepartureTime()));
            }else{resJo.put("cameraGDepartureTime", "无信息");}
            if(jinshiCameras6.getCameraRent()!=null){
                resJo.put("cameraGRent", jinshiCameras6.getCameraRent());
            }else{resJo.put("cameraGRent", "无信息");}
//            resJo.put("cameraARent", jinshiCameras.getCameraRent());
            resJo.put("cameraGName",jinshiCameras6.getJcName());
            resJo.put("cameraGIpAddress",jinshiCameras6.getJcIpAddress());
            resJo.put("accessG",jinshiCameras6.getJcAccess());
            resJo.put("thandleG",jinshiCameras6.getJcThandle());
            resJo.put("voiceCode",jinshiCameras6.getVoiceCode());
            resJo.put("picPathG",jinshiCameras6.getPicPath());
            JinshiCameras cameras = jinshiCameraService.selectByParkIdAndtHandle(Integer.valueOf(jinshiCameras6.getJcParking()),jinshiCameras6.getJcThandle());
            resJo.put("jcIsTypeG",cameras.getJcIsType());
        }
        if(i8!=-1){
            JinshiCameras jinshiCameras7 = CameraParam.cameraMap.get(i8);
            String carType = "";
            String lincenseType = "";
            if(jinshiCameras7.getCameraMemberType()!=null){
                if(jinshiCameras7.getCameraMemberType().equals("1")){
//                    carType = "会员车";
                    Member member = new Member();
                    member.setLincensePlateId(jinshiCameras7.getCameraLicenseId());
                    member.setAreaNumber(jinshiCameras7.getCameraAreaCode());
                    member.setParkId(GlobalVariable.parkId);
                    List<Member> members = memberService.checkMemberByLinceseForUtil(member);
                    if (members.size() > 0) {
                        Member member1 = members.get(0);
                        carType = member1.getServiceType();
                    } else {
                        carType = "会员车";
                    }
                }else if(jinshiCameras7.getCameraMemberType().equals("0")){
                    carType = "临停车";
                }else if(jinshiCameras7.getCameraMemberType().equals("2")){
                    carType = "无效卡";
                }
            }else{
                carType = "无信息";
            }
            if(jinshiCameras7.getCameraLincenseType()!=null){
                if(jinshiCameras7.getCameraLincenseType().equals("1")){
                    lincenseType = "汽油车";
                }else if(jinshiCameras7.getCameraLincenseType().equals("11")){
                    lincenseType = "能源车";
                }else if(jinshiCameras7.getCameraLincenseType().equals("2")){
                    lincenseType = "蓝牌能源车";
                }else if(jinshiCameras7.getCameraLincenseType().equals("3")){
                    lincenseType = "黄牌车";
                }else if(jinshiCameras7.getCameraLincenseType().equals("5")){
                    lincenseType = "特种车";
                }
            }else{
                lincenseType = "无信息";
            }
            resJo.put("lincenseHType",lincenseType);
            resJo.put("memberHType",carType);
            if(jinshiCameras7.getCameraInboundTime()!=null){
                resJo.put("cameraHInboundTime", format2.format(jinshiCameras7.getCameraInboundTime()));
            }else{resJo.put("cameraHInboundTime", "无信息");}
            resJo.put("cameraHLicenseId", jinshiCameras7.getCameraLicenseId());
            resJo.put("cameraHLincenseType", lincenseType);
            resJo.put("cameraHMemberType", jinshiCameras7.getCameraMemberType());
            resJo.put("cameraHName",jinshiCameras7.getJcName());
            resJo.put("cameraHIpAddress",jinshiCameras7.getJcIpAddress());
            resJo.put("accessH",jinshiCameras7.getJcAccess());
            resJo.put("thandleH",jinshiCameras7.getJcThandle());
            resJo.put("picPathH",jinshiCameras7.getPicPath());
            JinshiCameras cameras = jinshiCameraService.selectByParkIdAndtHandle(Integer.valueOf(jinshiCameras7.getJcParking()),jinshiCameras7.getJcThandle());
            resJo.put("jcIsTypeH",cameras.getJcIsType());
        }
//        }
        return resJo.toString();
    }

    public void reFreshGlobalVariable(Integer i1,Integer i2,Integer i3,Integer i4,Integer i5,Integer i6,Integer i7,Integer i8){
//        if(i1!=-1&&i2!=-1&&i3!=-1&&i4!=-1){
        if(i1!=-1){
            JinshiCameras jinshiCameras1 = CameraParam.cameraMap.get(i1);
            String jcIpAddress = jinshiCameras1.getJcIpAddress();
            if (jcIpAddress != null) {
                GlobalVariable.cameraAIpAddress = jcIpAddress;
            }
            GlobalVariable.cameraAName = jinshiCameras1.getJcName();
            GlobalVariable.accessA = jinshiCameras1.getJcAccess();
            GlobalVariable.thandleA = jinshiCameras1.getJcThandle();
        }
        if(i2!=-1){
            JinshiCameras jinshiCameras2 = CameraParam.cameraMap.get(i2);
            String jcIpAddress = jinshiCameras2.getJcIpAddress();
            if (jcIpAddress != null) {
                GlobalVariable.cameraAIpAddress = jcIpAddress;
            }
            GlobalVariable.cameraBName = jinshiCameras2.getJcName();
            GlobalVariable.accessB = jinshiCameras2.getJcAccess();
            GlobalVariable.thandleB = jinshiCameras2.getJcThandle();
        }
        if(i3!=-1){
            JinshiCameras jinshiCameras3 = CameraParam.cameraMap.get(i3);
            String jcIpAddress = jinshiCameras3.getJcIpAddress();
            if (jcIpAddress != null) {
                GlobalVariable.cameraAIpAddress = jcIpAddress;
            }
            GlobalVariable.cameraCName = jinshiCameras3.getJcName();
            GlobalVariable.accessC = jinshiCameras3.getJcAccess();
            GlobalVariable.thandleC = jinshiCameras3.getJcThandle();
        }
        if(i4!=-1){
            JinshiCameras jinshiCameras4 = CameraParam.cameraMap.get(i4);
            String jcIpAddress = jinshiCameras4.getJcIpAddress();
            if (jcIpAddress != null) {
                GlobalVariable.cameraAIpAddress = jcIpAddress;
            }
            GlobalVariable.cameraDName = jinshiCameras4.getJcName();
            GlobalVariable.accessD = jinshiCameras4.getJcAccess();
            GlobalVariable.thandleD = jinshiCameras4.getJcThandle();
        }
        if(i5!=-1){
            JinshiCameras jinshiCameras5 = CameraParam.cameraMap.get(i5);
            String jcIpAddress = jinshiCameras5.getJcIpAddress();
            if (jcIpAddress != null) {
                GlobalVariable.cameraAIpAddress = jcIpAddress;
            }
            GlobalVariable.cameraEName = jinshiCameras5.getJcName();
            GlobalVariable.accessE = jinshiCameras5.getJcAccess();
            GlobalVariable.thandleE = jinshiCameras5.getJcThandle();
        }
        if(i6!=-1){
            JinshiCameras jinshiCameras6 = CameraParam.cameraMap.get(i6);
            String jcIpAddress = jinshiCameras6.getJcIpAddress();
            if (jcIpAddress != null) {
                GlobalVariable.cameraAIpAddress = jcIpAddress;
            }
            GlobalVariable.cameraFName = jinshiCameras6.getJcName();
            GlobalVariable.accessF = jinshiCameras6.getJcAccess();
            GlobalVariable.thandleF = jinshiCameras6.getJcThandle();
        }
        if(i7!=-1){
            JinshiCameras jinshiCameras7 = CameraParam.cameraMap.get(i7);
            String jcIpAddress = jinshiCameras7.getJcIpAddress();
            if (jcIpAddress != null) {
                GlobalVariable.cameraAIpAddress = jcIpAddress;
            }
            GlobalVariable.cameraGName = jinshiCameras7.getJcName();
            GlobalVariable.accessG = jinshiCameras7.getJcAccess();
            GlobalVariable.thandleG = jinshiCameras7.getJcThandle();
        }
        if(i8!=-1){
            JinshiCameras jinshiCameras8 = CameraParam.cameraMap.get(i8);
            String jcIpAddress = jinshiCameras8.getJcIpAddress();
            if (jcIpAddress != null) {
                GlobalVariable.cameraAIpAddress = jcIpAddress;
            }
            GlobalVariable.cameraHName = jinshiCameras8.getJcName();
            GlobalVariable.accessH = jinshiCameras8.getJcAccess();
            GlobalVariable.thandleH = jinshiCameras8.getJcThandle();
        }
//        }
//        for (int i = 0; i < CameraParam.cameraList.size(); i++) {
//            if(CameraParam.cameraList.get(i)!=null){
//                JinshiCameras jinshiCameras = CameraParam.cameraList.get(i);
//                Integer jcSort = Integer.parseInt(jinshiCameras.getJcSort());
//                if(jcSort==1){
//                    GlobalVariable.cameraAIpAddress = jinshiCameras.getJcIpAddress();
//                    GlobalVariable.cameraAName = jinshiCameras.getJcName();
//                    GlobalVariable.accessA = jinshiCameras.getJcAccess();
//                    GlobalVariable.thandleA = jinshiCameras.getJcThandle();
//                }else if (jcSort==2){
//                    GlobalVariable.cameraBIpAddress = jinshiCameras.getJcIpAddress();
//                    GlobalVariable.cameraBName = jinshiCameras.getJcName();
//                    GlobalVariable.accessB = jinshiCameras.getJcAccess();
//                    GlobalVariable.thandleB = jinshiCameras.getJcThandle();
//                }else if(jcSort==3){
//                    GlobalVariable.cameraCIpAddress = jinshiCameras.getJcIpAddress();
//                    GlobalVariable.cameraCName = jinshiCameras.getJcName();
//                    GlobalVariable.accessC = jinshiCameras.getJcAccess();
//                    GlobalVariable.thandleC = jinshiCameras.getJcThandle();
//                }else if(jcSort==4){
//                    GlobalVariable.cameraDIpAddress = jinshiCameras.getJcIpAddress();
//                    GlobalVariable.cameraDName = jinshiCameras.getJcName();
//                    GlobalVariable.accessD = jinshiCameras.getJcAccess();
//                    GlobalVariable.thandleD = jinshiCameras.getJcThandle();
//                }
//            }
//        }
    }
    @ResponseBody
    @RequestMapping(value = "/getInCamerasName", method = RequestMethod.POST)
    public String getInCamerasName(){
        List<JinshiCameras> jinshiCameras = jinshiCameraService.selectCameraByParkId(GlobalVariable.parkId);
        JSONArray resJa = new JSONArray();
        for (int i = 0; i < jinshiCameras.size(); i++) {
            if(jinshiCameras.get(i).getJcAccess().equals("进口")){
                JSONObject resJo = new JSONObject();
                resJo.put("thandle",jinshiCameras.get(i).getJcThandle());
                resJo.put("name",jinshiCameras.get(i).getJcName());
                resJa.add(resJo);
            }
        }
        return resJa.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/getOutCamerasName", method = RequestMethod.POST)
    public String getOutCamerasName(){
        List<JinshiCameras> jinshiCameras = jinshiCameraService.selectCameraByParkId(GlobalVariable.parkId);
        JSONArray resJa = new JSONArray();
        for (int i = 0; i < jinshiCameras.size(); i++) {
            if(jinshiCameras.get(i).getJcAccess().equals("出口")){
                JSONObject resJo = new JSONObject();
                resJo.put("thandle",jinshiCameras.get(i).getJcThandle());
                resJo.put("name",jinshiCameras.get(i).getJcName());
                resJa.add(resJo);
            }
        }
        return resJa.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/openGate", method = RequestMethod.POST)
    public Integer openGate(@RequestBody JSONObject jsonParam){
        Integer jcThandle = Integer.parseInt((String) jsonParam.get("jcThandle"));
        JinshiCamerasSpare jinshiCamerasSpare = GlobalVariable.util.selectRealCameraId(jcThandle);
        Integer integer = GlobalVariable.util.openGate(jinshiCamerasSpare.getJcsSpareThandle());
//        Integer integer = GlobalVariable.util.openGate(jcThandle);
        return integer;
    }

    /**
     * 出入管理收费放行
     * @param jsonParam
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/openGateNew", method = RequestMethod.POST)
    @ApiOperation(value = "出入管理---收费放行")
    public JSONObject openGateNew(@RequestBody JSONObject jsonParam) throws Exception{
        logger.info("出入管理收费放行：" + jsonParam.toJSONString());
        JSONObject jsonObject = new JSONObject();
        String jcThandle = (String) jsonParam.get("jcThandle");
        String lincense = (String) jsonParam.get("lincense");
        Integer lpParkingRealCost = (Integer) jsonParam.get("lpParkingRealCost");//实收费用
        if (null == lpParkingRealCost) {
            jsonObject.put("msg","请输入实收费用");
            return jsonObject;
        }
        JinshiCamerasSpare jinshiCamerasSpare = GlobalVariable.util.selectRealCameraId(Integer.parseInt(jcThandle));
        Integer integer = GlobalVariable.util.openGate(jinshiCamerasSpare.getJcsSpareThandle());
//        GlobalVariable.util.openGate(Integer.parseInt(jcThandle));
        logger.info("出入管理收费放行执行抬杆：" + jcThandle);
        List<LincensePlate> lincensePlates = lincensePlateService.selectByLincensePlate(lincense);
        if (lincensePlates.size() > 0) {
            LincensePlate lincensePlate = lincensePlates.get(0);
            lincensePlate.setLpOrderState("收费放行");
            lincensePlateService.updateByPlateByOrderId(lincensePlate);
            GlobalVariable.openGateNewRealMoney = Integer.valueOf(lpParkingRealCost);
            Thread.sleep(1000);
            jsonObject.put("msg","执行成功");
            logger.info("出入管理收费放行执行成功：" + jcThandle);
        }
        return jsonObject;
    }
    @ResponseBody
    @RequestMapping(value = "/closeGate", method = RequestMethod.POST)
    public Integer closeGate(@RequestBody JSONObject jsonParam){
        Integer jcThandle = Integer.parseInt((String) jsonParam.get("jcThandle"));
        JinshiCamerasSpare jinshiCamerasSpare = GlobalVariable.util.selectRealCameraId(jcThandle);
        Integer integer = GlobalVariable.util.closeGate(jinshiCamerasSpare.getJcsSpareThandle());
//        Integer integer = GlobalVariable.util.closeGate(jcThandle);
        return integer;
    }
    @ResponseBody
    @RequestMapping("/remakePhoto")
    public Integer remakePhoto(@RequestBody JSONObject jsonParam){
        Integer jcThandle = Integer.parseInt((String) jsonParam.get("jcThandle"));
        JinshiCamerasSpare jinshiCamerasSpare = GlobalVariable.util.selectRealCameraId(jcThandle);
        GlobalVariable.util.remakePhoto(jinshiCamerasSpare.getJcsSpareThandle());
//        GlobalVariable.util.remakePhoto(jcThandle);
        return 1;
    }

    @ResponseBody
    @RequestMapping("/netInit")
    public void netInit(){
        cameraInitTestService.netInit();
    }

    @ResponseBody
    @RequestMapping("/cinit")
    public void cameraInit(){
        logger.info("test logger 4j");
        cameraInitTestService.cameraInit();
    }
    @ResponseBody
//    @RequestMapping("cinitA/{ip}")   @PathVariable String ipcameraInitA
    @RequestMapping("cinitA")
    public void cameraInitA(){
        logger.info("ACameraInit");
        cameraInitTestService.cameraInitA();
    }
    @ResponseBody
    @RequestMapping("/cinitB")
    public void cameraInitB(){
        logger.info("BCameraInit");
        cameraInitTestService.cameraInitB();
    }
    @ResponseBody
    @RequestMapping("/cinitC")
    public void cameraInitC(){
        logger.info("CCameraInit");
        cameraInitTestService.cameraInitC();
    }
    @ResponseBody
    @RequestMapping("/cinitD")
    public void cameraInitD(){
        logger.info("DCameraInit");
        cameraInitTestService.cameraInitD();
    }
    @ResponseBody
    @RequestMapping("/test")
    public void testTest(){
        logger.info("enter to test");
    }

    /**
     * 人工补录抬杆
     * @param jsonParam
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping(value = "/ManualSupplementOpenGateNext", method = RequestMethod.POST)
    @ApiOperation(value = "出入管理---人工补录抬杆")
    public String ManualSupplementOpenGateNext(@RequestBody JSONObject jsonParam) throws ParseException {
        logger.info("人工补录抬杆：" + jsonParam.toJSONString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject resJO = new JSONObject();
        String license = (String) jsonParam.get("license");
        String tHandleStr = (String) jsonParam.get("tHandle");
        int tHandle = Integer.parseInt(tHandleStr);
        if(tHandleStr.equals("-1")){
            resJO.put("resMessage","请先选择摄像机");
            return resJO.toString();
        }
        String datatime = (String) jsonParam.get("datatime");
        Date parse = sdf.parse(datatime);
        String orderIdByUUId = GlobalVariable.util.getOrderIdByUUIdAddPlate(license);
        JinshiParkingSetup jinshiParkingSetup = GlobalVariable.jinshiParkingSetup;
        JinshiCameras jinshiCameras = CameraParam.cameraMap.get(tHandle);
        jinshiCameras.setCameraLicenseId(license);
        jinshiCameras.setCameraInboundTime(parse);
        List<LincensePlate> lincensePlateList = GlobalVariable.util.selectByLincensePlate(license);
        if (lincensePlateList.size() > 0) {
            GlobalVariable.util.insertplateHistoryManual(lincensePlateList);
            GlobalVariable.util.deletePlates(lincensePlateList);
        }
        GlobalVariable.util.dataProcess(license,(byte)0,(byte)1,parse,orderIdByUUId,tHandle,jinshiCameras.getJcArea());
        if (jinshiParkingSetup.getJpsPayType() == 0) {
            GlobalVariable.util.Carin(license,orderIdByUUId,parse);
        } else if (jinshiParkingSetup.getJpsPayType() == 1) {
            GlobalVariable.util.carInNotice(license,new Date(),GlobalVariable.parkId,GlobalVariable.agentId,orderIdByUUId,tHandle,0);
        }
        JinshiCamerasSpare jinshiCamerasSpare = GlobalVariable.util.selectRealCameraId(tHandle);
        GlobalVariable.util.openGate(jinshiCamerasSpare.getJcsSpareThandle());
//        GlobalVariable.util.openGate(tHandle);
        resJO.put("resMessage","执行成功");
        return resJO.toJSONString();
    }

    /**
     * 服务端接收客户端发送的请求
     */
    private Integer num = 0;
    @ResponseBody
    @PostMapping("/receivePostForNet")
    public Integer receivePostForNet(@RequestBody JSONObject jsonParam) throws Exception{
        Date date = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Integer parkId = (Integer) jsonParam.get("parkId");
        Integer agentId = (Integer) jsonParam.get("agentId");
        Long getTime = (Long) jsonParam.get("time");
        String lincense = "车场id为" + parkId + "的出现异常";
        long l = getTime - GlobalVariable.getTime;
        if (null != parkId && null != agentId) {
            num = 0;
        } else if (null == parkId && null == agentId){
            num++;
        }
        if (num > 3 && GlobalVariable.flag && l/1000 >= 10) {
            SmsController smsController = new SmsController();
            smsController.sms("18994388793",lincense,sdf.format(date),"森林公园停车场","");
            smsController.sms("18622139742",lincense,sdf.format(date),"森林公园停车场","");
            GlobalVariable.flag = false;
            logger.info(sdf.format(date) + "发送异常信息成功");
            num = 0;
        }
        return num;
    }
}
