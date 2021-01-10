package com.jinshi.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.jinshi.entity.*;
import com.jinshi.netCamera.utils.ByteArrayToMAC;
import com.jinshi.netCamera.utils.HandleManage;
import com.jinshi.netCamera.utils.IPAndInt;
import com.jinshi.netCamera.utils.StatusCode;
import com.jinshi.wxPay.WeChatTemplate;
import net.sdk.bean.basicconfig.devsetup.Data_T_DevSetup;
import net.sdk.bean.basicconfig.netsetup.Data_T_MACSetup;
import net.sdk.bean.basicconfig.netsetup.Data_T_NetSetup;
import net.sdk.bean.basicconfig.portsetup.Data_T_RS485Setup;
import net.sdk.bean.serviceconfig.imagesnap.Data_T_DCImageSnap;
import net.sdk.bean.serviceconfig.platedevice.Data_T_ControlGate;
import net.sdk.bean.serviceconfig.platedevice.Data_T_LedSetup;
import net.sdk.bean.serviceconfig.platedevice.Data_T_RS485Data;
import net.sdk.bean.serviceconfig.platedevice.Data_T_SubLedSetup;
import net.sdk.function.main.NET;
import net.sdk.function.systemcommon.imagesnap.callback.Callback_FGetImageCB.FGetImageCB;
//import org.apache.logging.log4j.LogManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import uk.co.caprica.vlcj.player.MediaPlayer;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QianYiCameraUtil {

    private static Logger logger = Logger.getLogger(QianYiCameraUtil.class.getName());

    private static NET net;
    private String ptIp;
    private Integer outIn;
    private int handle;
    private MediaPlayer player;
    private String cameraName;
//    private String requestUrl = GlobalVariable.urlNew + "lincensePlate/insert";
//    private String requestUrlSelectByLincense = GlobalVariable.urlNew + "lincensePlate/selectByLincese";
//    private String requestUrl = "http://localhost:8080/carmanager_war/lincensePlate/insert";
//    private String requestUrlSelectByLincense = "http://localhost:8080/carmanager_war/lincensePlate/selectByLincese";

    private Map<Integer,Boolean> paySwitch = new HashMap<Integer, Boolean>();

    public QianYiCameraUtil(NET net){
        this.net = net;
    }
    public QianYiCameraUtil(String ptIp){
        this.ptIp = ptIp;
    }
    public QianYiCameraUtil(String ptIp,NET net) {
        this.ptIp = ptIp;
        this.net = net;
    }
    public int gettHandle(){
        return HandleManage.gettHandle(net);
    }

    public JSONObject getDeviceNetMessage(Integer tHandle){
        Data_T_NetSetup.T_NetSetup.ByReference ptNetSetup = new Data_T_NetSetup.T_NetSetup.ByReference();
        int qns = net.Net_QueryNETSetup(tHandle, ptNetSetup);
        logger.info(StatusCode.getStatusCode(qns, "Net_QueryNETSetup"));
//        String ipaddress = IPAndInt.intToIp(ptNetSetup.uiIPAddress);//ip地址
        String maskAddress = IPAndInt.intToIp(ptNetSetup.uiMaskAddress);//子网掩码
        String gateWayAddress = IPAndInt.intToIp(ptNetSetup.uiGatewayAddress);//网关
        String dns1 = IPAndInt.intToIp(ptNetSetup.uiDNS1);//dns1
//        String dns2 = IPAndInt.intToIp(ptNetSetup.uiDNS2);//dns2
//        String hostName = new String(ptNetSetup.szHostName).trim();//dns域名名称
        JSONObject resJO = new JSONObject();
        resJO.put("maskAddress",maskAddress);
        resJO.put("gateWayAddress",gateWayAddress);
        resJO.put("dns1",dns1);
        return resJO;
    }

    public String getDeviceMac(Integer thandle){
        Data_T_MACSetup.T_MACSetup.ByReference ptMacSetup = new Data_T_MACSetup.T_MACSetup.ByReference();
        int qms = net.Net_QueryMACSetup(thandle, ptMacSetup);
        logger.info(StatusCode.getStatusCode(qms, "Net_QueryMACSetup"));
        if(qms == 0){
            return ByteArrayToMAC.getMacAddress(ptMacSetup.aucMACAddresss);
        }else {
            return "error";
        }
    }

    public String getDeviceLicense(Integer hHandle){
        Data_T_DevSetup.T_DevSetup.ByReference ptDevSetup = new Data_T_DevSetup.T_DevSetup.ByReference();
        int qds = net.Net_QueryDevSetup(hHandle, ptDevSetup);
        if(qds == 0){
            String szLicenseNum = new String(ptDevSetup.szLicenseNum).trim();
//            String szDistrictNumber= new String(ptDevSetup.szDistrictNumber).trim();
//            String szProjectName  = new String(ptDevSetup.szProjectName).trim();
//            String zDevNumber = new String(ptDevSetup.szDevNumber).trim();
//            String szRoadName = new String(ptDevSetup.szRoadName).trim();
//            String szRecordNum = new String(ptDevSetup.szRecordNum).trim();
//            String szRoadNumber = new String(ptDevSetup.szRoadNumber).trim();
//            byte ucDirection = ptDevSetup.ucDirection;
//            containerControl.message("设备License："+szLicenseNum+"\n地区编号："+szDistrictNumber
//                    +"\n出入口名称:"+szRoadName+"\n项目名称:"+szProjectName+"\n设备号："+zDevNumber
//                    +"\n备案号："+szRecordNum+"\n出入口编号："+szRoadNumber+"\n方向："+ucDirection);
            return szLicenseNum;
        }else{
            return"error";
        }
    }

    public JSONObject selectOrderByPlate(String jcoType,String plate){
        String url = GlobalVariable.urlNewShop + "JinshiCouponOrder/selectOrderByPlate";
        RestTemplate restTemplate=new RestTemplate();
        JSONObject jo = new JSONObject();
        jo.put("jcoType",jcoType);
        jo.put("jcoPlate",plate);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jo,String.class);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);
        if(jsonObject.size()>0){
            JSONArray couponOrderList = JSONObject.parseArray(jsonObject.get("couponOrderList").toString());
            if(couponOrderList.size()>0){
                JSONObject o = (JSONObject) couponOrderList.get(0);
                jsonObject = o;
            }else{
                return null;
            }
        }
        return jsonObject;
    }
    public JSONObject selectGenerateById(Integer jcgId){
        String url = GlobalVariable.urlNewShop + "JinshiCouponGenerate/selectGenerateById";
        RestTemplate restTemplate=new RestTemplate();
        JSONObject jo = new JSONObject();
        jo.put("jcgId",jcgId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jo,String.class);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);
        return jsonObject;
    }

    public String updateOrderByPlate(String jcoType,String plate,Integer jcoId,Date inBoundTime,Date outBoundTime,Integer often){
        String url = GlobalVariable.urlNewShop + "JinshiCouponOrder/updateByPrimaryKeySelective";
        RestTemplate restTemplate=new RestTemplate();
        JinshiCouponOrderBo jinshiCouponOrderBo = new JinshiCouponOrderBo();
        jinshiCouponOrderBo.setJcoId(jcoId);
        jinshiCouponOrderBo.setJcoPlate(plate);
        jinshiCouponOrderBo.setJcoType(jcoType);
        jinshiCouponOrderBo.setJcoInboundTime(inBoundTime);
        jinshiCouponOrderBo.setJcoDepartureTime(outBoundTime);
        jinshiCouponOrderBo.setJcoOften(often.toString());
        JSONObject jo = (JSONObject) JSONObject.parse(JSONObject.toJSON(jinshiCouponOrderBo).toString());
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jo,String.class);
        String body = responseEntity.getBody();
        return body;
    }

    public String sendPostToNet(){
        String urlKS = GlobalVariable.netPathKS + "cameraInit/receivePostForNet";
        String urlWJ = GlobalVariable.netPathWJ + "cameraInit/receivePostForNet";
        RestTemplate restTemplate=new RestTemplate();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("parkId",GlobalVariable.parkId);
        jsonObject.put("agentId",GlobalVariable.agentId);
        jsonObject.put("time",System.currentTimeMillis());
        String body = null;
        if (GlobalVariable.parkId == 1) {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(urlKS,jsonObject,String.class);
            body = responseEntity.getBody();
        } else if (GlobalVariable.parkId == 3) {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(urlWJ,jsonObject,String.class);
            body = responseEntity.getBody();
        }
        return body;
    }
    //修改区域编号 checkMemberByLinceseForUtil 13
    public List<Member> checkServiceTypeCarByLincese(String lincese,String areaNumber){
//        String url = "http://localhost:8080/carmanager_war/member/checkMemberByLincese";
        String url = GlobalVariable.urlNew + "member/checkMemberByLinceseForUtil";
//        String url = "http://47.103.142.85:8081/carmanager-TEST/member/checkMemberByLincese";
        RestTemplate restTemplate=new RestTemplate();
        Member member = new Member();
        member.setLincensePlateId(lincese);
        member.setAreaNumber(areaNumber);
        member.setParkId(GlobalVariable.parkId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,member,String.class);
        String body = responseEntity.getBody();
        JSONArray objects = JSONArray.parseArray(body);
        List<Member> resMember = objects.toJavaList(Member.class);
        if(resMember.size() > 0){
            return resMember;
        }else {
            return null;
        }
    }

    public List<Member> checkISMember(String lincese,String areaName){
//        String url = "http://localhost:8080/carmanager_war/member/checkMemberByLincese";
//        String url = "http://47.103.142.85:8081/carmanager-TEST/member/checkMemberByLincese";
        String url = GlobalVariable.urlNew + "member/checkISMember"; //是否会员
        String urlAllArea = GlobalVariable.urlNew + "member/checkISAllAreaMember";//是否全区域会员
        RestTemplate restTemplate=new RestTemplate();
        Member member = new Member();
        member.setLincensePlateId(lincese);
        member.setAreaName(areaName);
        member.setParkId(GlobalVariable.parkId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,member,String.class);
        ResponseEntity<String> responseEntityAllArea = restTemplate.postForEntity(urlAllArea,member,String.class);
        String bodyArea = responseEntityAllArea.getBody();
        JSONArray objectsArea = JSONArray.parseArray(bodyArea);
        List<Member> resMemberArea = objectsArea.toJavaList(Member.class);
        if(resMemberArea.size() > 0){
            return resMemberArea;
        }else {
            String body = responseEntity.getBody();
            JSONArray objects = JSONArray.parseArray(body);
            List<Member> resMember = objects.toJavaList(Member.class);
            if(resMember.size() > 0){
                return resMember;
            }else {
                return null;
            }
        }
    }

    public void updataProcess(String lincense,Date createDate,Double rent,int tHandle,String payState,String orderId){
        String url = GlobalVariable.urlNew + "lincensePlate/updateByPlate";
//        String urlCCB = GlobalVariable.urlNew + "lincensePlate/updateByPlate";
        RestTemplate restTemplate=new RestTemplate();
        LincensePlate lp = new LincensePlate();
        lp.setLpLincensePlateIdCar(lincense);
        lp.setLpDepartureTime(createDate);
        lp.setLpDepartureCname(String.valueOf(tHandle));
        lp.setLpParkingCost(rent.toString());
        lp.setLpOrderState(payState);
        lp.setLpOrderId(orderId);
        ResponseEntity<String> insertresponseEntity = restTemplate.postForEntity(url,lp,String.class);
    }
    public void deletePlates(List<LincensePlate> plates){
        String deleteurl = GlobalVariable.urlNew + "lincensePlate/deleteByPrimaryKey";
//        String deleteurl = "http://localhost:8080/carManager_V1_0_war/lincensePlate/deleteByPrimaryKey";
        RestTemplate restTemplate=new RestTemplate();
        for (int i = 0; i < plates.size(); i++) {
            ResponseEntity<String> deleteresponseEntity = restTemplate.postForEntity(deleteurl,plates.get(i),String.class);
        }
    }

    public void inserHistory(String lincese,String paymentType,Date departureTime,int thandle){
        String insertHistoryurl = GlobalVariable.urlNew + "lincensePlateHistory/insert";
        LincensePlateHistory lp = new LincensePlateHistory();
        RestTemplate restTemplate=new RestTemplate();
        lp.setLpPaymentType(paymentType);
        lp.setLpLincensePlateIdCar(lincese);
        lp.setLpDepartureCname(String.valueOf(thandle));
        lp.setLpDepartureTime(departureTime);
        lp.setLpCreateTime(new Date());
        ResponseEntity<String> insertresponseEntity = restTemplate.postForEntity(insertHistoryurl,lp,String.class);
    }
    public void insertExceptionHistory(LincensePlate plates){
        String insertHistoryurl = GlobalVariable.urlNew + "lincensePlateHistory/insert";
//        String insertHistoryurl = "http://localhost:8080/carManager_V1_0_war/lincensePlateHistory/insert";
        RestTemplate restTemplate=new RestTemplate();
        LincensePlateHistory lp = new LincensePlateHistory();
        BeanUtils.copyProperties(plates,lp);
        ResponseEntity<String> insertresponseEntity = restTemplate.postForEntity(insertHistoryurl,lp,String.class);
    }

    public void insertplateHistory(List<LincensePlate> plates){
        String insertHistoryurl = GlobalVariable.urlNew + "lincensePlateHistory/insert";
//        String insertHistoryurl = "http://localhost:8080/carManager_V1_0_war/lincensePlateHistory/insert";
        RestTemplate restTemplate=new RestTemplate();
        for (int i = 0; i < plates.size(); i++) {
            LincensePlateHistory lp = new LincensePlateHistory();
            LincensePlate lincensePlate = plates.get(i);
            BeanUtils.copyProperties(lincensePlate,lp);
            lp.setLpPaymentType("异常数据");
            lp.setLpId(null);
            ResponseEntity<String> insertresponseEntity = restTemplate.postForEntity(insertHistoryurl,lp,String.class);
        }
    }

    /**
     * 手动抬杆-手动删除
     * 人工补录抬杆与不抬杆
     * @param plates
     */
    public void insertplateHistoryManual(List<LincensePlate> plates){
        String insertHistoryurl = GlobalVariable.urlNew + "lincensePlateHistory/insert";
//        String insertHistoryurl = "http://localhost:8080/carManager_V1_0_war/lincensePlateHistory/insert";
        RestTemplate restTemplate=new RestTemplate();
        for (int i = 0; i < plates.size(); i++) {
            LincensePlateHistory lp = new LincensePlateHistory();
            LincensePlate lincensePlate = plates.get(i);
            BeanUtils.copyProperties(lincensePlate,lp);
            lp.setLpPaymentType("手动删除");
            lp.setLpId(null);
            ResponseEntity<String> insertresponseEntity = restTemplate.postForEntity(insertHistoryurl,lp,String.class);
        }
    }

    /**
     * 收费放行
     * @param plates
     */
    public void insertplateHistoryManualPay(List<LincensePlate> plates){
        String insertHistoryurl = GlobalVariable.urlNew + "lincensePlateHistory/insert";
//        String insertHistoryurl = "http://localhost:8080/carManager_V1_0_war/lincensePlateHistory/insert";
        RestTemplate restTemplate=new RestTemplate();
        for (int i = 0; i < plates.size(); i++) {
            LincensePlateHistory lp = new LincensePlateHistory();
            LincensePlate lincensePlate = plates.get(i);
            BeanUtils.copyProperties(lincensePlate,lp);
            lp.setLpPaymentType("人工收费出场");
            lp.setLpId(null);
            ResponseEntity<String> insertresponseEntity = restTemplate.postForEntity(insertHistoryurl,lp,String.class);
        }
    }

    public void updataProcess(LincensePlate lincese,String lincense,Date createDate,Double rent,int tHandle,String payState,String lpPaymentType,Integer dateOften){
//        String url = "http://58.210.33.107:8080/carmanager-TEST/lincensePlate/updateByPlate";
        String deleteurl = GlobalVariable.urlNew + "lincensePlate/deleteByPrimaryKey";
        String insertHistoryurl = GlobalVariable.urlNew + "lincensePlateHistory/insert";
//        String deleteurl = "http://localhost:8080/carManager_V1_0_war/lincensePlate/deleteByPrimaryKey";
//        String insertHistoryurl = "http://localhost:8080/carManager_V1_0_war/lincensePlateHistory/insert";
        RestTemplate restTemplate=new RestTemplate();
        LincensePlateHistory lp = new LincensePlateHistory();
        BeanUtils.copyProperties(lincese,lp);
        lp.setLpId(null);
        lp.setLpLincensePlateIdCar(lincense);
        lp.setLpDepartureTime(createDate);
        lp.setLpDepartureCname(String.valueOf(tHandle));
        lp.setLpParkingCost(rent.toString());
        lp.setLpParkingRealCost(rent.toString());
        lp.setLpOrderState(payState);
        lp.setLpPaymentType(lpPaymentType);
        lp.setLpParkingOften(String.valueOf(dateOften));
//        lp.setLpCcbOrderId(lincese.getLpCcbOrderId());
        ResponseEntity<String> insertresponseEntity = restTemplate.postForEntity(insertHistoryurl,lp,String.class);
        ResponseEntity<String> deleteresponseEntity = restTemplate.postForEntity(deleteurl,lincese,String.class);
    }
    //临时车---------
    public void updataProcessForQRCode(LincensePlate lincese,String lincense,Date createDate,Double rent,int tHandle,String payState,String lpPaymentType,Integer dateOften){
//        String url = "http://58.210.33.107:8080/carmanager-TEST/lincensePlate/updateByPlate";
        String deleteurl = GlobalVariable.urlNew + "lincensePlate/deleteByPrimaryKey";
        String insertHistoryurl = GlobalVariable.urlNew + "lincensePlateHistory/insert";
//        String deleteurl = "http://localhost:8080/carManager_V1_0_war/lincensePlate/deleteByPrimaryKey";
//        String insertHistoryurl = "http://localhost:8080/carManager_V1_0_war/lincensePlateHistory/insert";
        RestTemplate restTemplate=new RestTemplate();
        LincensePlateHistory lp = new LincensePlateHistory();
        BeanUtils.copyProperties(lincese,lp);
        lp.setLpId(null);
        lp.setLpLincensePlateIdCar(lincense);
        lp.setLpDepartureTime(createDate);
        lp.setLpDepartureCname(String.valueOf(tHandle));
        lp.setLpParkingCost(rent.toString());
        lp.setLpParkingRealCost(rent.toString());
        lp.setLpOrderState(payState);
        lp.setLpPaymentType(lpPaymentType);
        lp.setLpParkingOften(String.valueOf(dateOften));
//        lp.setLpCcbOrderId(lincese.getLpCcbOrderId());
        ResponseEntity<String> insertresponseEntity = restTemplate.postForEntity(insertHistoryurl,lp,String.class);
        ResponseEntity<String> deleteresponseEntity = restTemplate.postForEntity(deleteurl,lincese,String.class);
    }
    public void updataProcessForPay(LincensePlate lincese,String lincense,Date createDate,Double rent,int tHandle,String payState,String lpPaymentType,Integer dateOften){
//        String url = "http://58.210.33.107:8080/carmanager-TEST/lincensePlate/updateByPlate";
        String deleteurl = GlobalVariable.urlNew + "lincensePlate/deleteByPrimaryKey";
        String insertHistoryurl = GlobalVariable.urlNew + "lincensePlateHistory/insert";
//        String deleteurl = "http://localhost:8080/carManager_V1_0_war/lincensePlate/deleteByPrimaryKey";
//        String insertHistoryurl = "http://localhost:8080/carManager_V1_0_war/lincensePlateHistory/insert";
        RestTemplate restTemplate=new RestTemplate();
        LincensePlateHistory lp = new LincensePlateHistory();
        BeanUtils.copyProperties(lincese,lp);
        lp.setLpId(null);
        lp.setLpLincensePlateIdCar(lincense);
        lp.setLpDepartureTime(createDate);
        lp.setLpDepartureCname(String.valueOf(tHandle));
        lp.setLpParkingCost(rent.toString());
        lp.setLpParkingRealCost(String.valueOf(GlobalVariable.openGateNewRealMoney));
        lp.setLpOrderState(payState);
        lp.setLpPaymentType(lpPaymentType);
        lp.setLpParkingOften(String.valueOf(dateOften));
        ResponseEntity<String> insertresponseEntity = restTemplate.postForEntity(insertHistoryurl,lp,String.class);
        ResponseEntity<String> deleteresponseEntity = restTemplate.postForEntity(deleteurl,lincese,String.class);
    }

    public void updataFirstProcess(int tHandle,String payState){
//        String url = "http://localhost:8080/carmanager_war/lincensePlate/updateByPlate";
        String url = GlobalVariable.urlNew + "lincensePlate/updatePlateCleanUp";
        RestTemplate restTemplate=new RestTemplate();
        LincensePlate lp = new LincensePlate();
        lp.setLpDepartureTime(null);
        lp.setLpDepartureCname(String.valueOf(tHandle));
        lp.setLpParkingCost(null);
        lp.setLpOrderState(payState);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,lp,String.class);
    }
    //修改此出口之前所有待支付记录---为null
    public void clearToPayToHistory(int tHandle){
        //查询之前所有待支付记录
        String url = GlobalVariable.urlNew + "lincensePlate/selectByParkIdAndCname";
        String urlNull = GlobalVariable.urlNew + "lincensePlate/updatePlateCnameToNull";
        RestTemplate restTemplate=new RestTemplate();
        LincensePlate lp = new LincensePlate();
        lp.setLpDepartureCname(String.valueOf(tHandle));
        lp.setLpParkingName(String.valueOf(GlobalVariable.parkId));
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,lp,String.class);
        String body = responseEntity.getBody();
        List<LincensePlate> lincensePlates = JSONObject.parseArray(body, LincensePlate.class);
        if (lincensePlates.size() > 0) {
            for (int i = 0; i < lincensePlates.size(); i++) {
                LincensePlate lincensePlate = lincensePlates.get(i);
                ResponseEntity<String> responseEntityNull = restTemplate.postForEntity(urlNull,lincensePlate,String.class);
            }
        }
    }



    public void updataErrorProcess(String lincense,String payState){
//        String url = "http://localhost:8080/carmanager_war/lincensePlate/updateByPlate";
        String url = GlobalVariable.urlNew + "lincensePlate/updateByPlate";
        RestTemplate restTemplate=new RestTemplate();
        LincensePlate lp = new LincensePlate();
        lp.setLpLincensePlateIdCar(lincense);
        lp.setLpOrderState(payState);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,lp,String.class);
    }

    public void updataOrder(String lincense,Date createDate,Double rent,int tHandle){
//        String url = "http://localhost:8080/carmanager_war/lincensePlate/updateByPlate";
        String url = GlobalVariable.urlNew + "lincensePlate/updateByPlate";
        RestTemplate restTemplate=new RestTemplate();
        LincensePlate lp = new LincensePlate();
        lp.setLpLincensePlateIdCar(lincense);
        lp.setLpDepartureTime(createDate);
        lp.setLpDepartureCname(String.valueOf(tHandle));
        lp.setLpParkingCost(rent.toString());
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,lp,String.class);
    }

    public void dataProcess(String lincensePlate,byte ucPlateColor,byte ucLprType,Date createTime,String orderIdByUUId,int tHandle,String area) throws ParseException{
        RestTemplate restTemplate=new RestTemplate();
        LincensePlate lp = new LincensePlate();
        lp.setLpLincensePlateIdCar(lincensePlate);
        lp.setLpServiceTypeCar("");
        lp.setLpInboundTime(createTime);
        lp.setLpOrderId(orderIdByUUId);
        lp.setLpInboundCname(String.valueOf(tHandle));
        lp.setLpAgentName(GlobalVariable.agentId.toString());
        lp.setLpLincenseType(String.valueOf(ucLprType));
        lp.setLpCarColor(String.valueOf(ucPlateColor));
        lp.setLpParkingName(GlobalVariable.parkId.toString());
        lp.setLpCreateTime(createTime);
        lp.setLpOrderState("未付款");
        lp.setLpCarType(area);
        int memberDays = 0;
        List<Member> memberList = checkServiceTypeCarByLincese(lincensePlate, area);
        if (memberList != null && memberList.size() > 0) {//是否是会员信息
            for (Member member1 : memberList) {
                memberDays = DateUtils.daysBetween(new Date(), member1.getExpirationTime());//获取会员有效时间
                if (memberDays > 0) {
                    lp.setLpLgType(1);
                } else {
                    lp.setLpLgType(0);
                }
            }
        } else {
            lp.setLpLgType(0);
        }
//        LincensePlate lp = new LincensePlate();
//        lp.setLincensePlateIdCar("津MYL40011");
//        lp.setServiceTypeCar(2);
//        lp.setJoinTime(new Date());
        String requestUrl = GlobalVariable.urlNew + "lincensePlate/insert";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(requestUrl,lp,String.class);
    }
    public void dataProcessNew(String lincensePlate,byte ucPlateColor,byte ucLprType,Date createTime,String orderIdByUUId,int tHandle,String area,String jsNumber,String type){
        RestTemplate restTemplate=new RestTemplate();
        LincensePlate lp = new LincensePlate();
        lp.setLpLincensePlateIdCar(lincensePlate);
        lp.setLpServiceTypeCar("");
        lp.setLpInboundTime(createTime);
        lp.setLpOrderId(orderIdByUUId);
        lp.setLpInboundCname(String.valueOf(tHandle));
        lp.setLpAgentName(GlobalVariable.agentId.toString());
        lp.setLpLincenseType(String.valueOf(ucLprType));
        lp.setLpCarColor(String.valueOf(ucPlateColor));
        lp.setLpParkingName(GlobalVariable.parkId.toString());
        lp.setLpCreateTime(createTime);
        lp.setLpOrderState("未付款");
        lp.setLpCarType(area);
        if (jsNumber != null) {
            lp.setLpLgId(Integer.valueOf(jsNumber));
        } else {
            lp.setLpLgId(null);
        }
        lp.setLpLgType(Integer.parseInt(type));
//        LincensePlate lp = new LincensePlate();
//        lp.setLincensePlateIdCar("津MYL40011");
//        lp.setServiceTypeCar(2);
//        lp.setJoinTime(new Date());
        String requestUrl = GlobalVariable.urlNew + "lincensePlate/insert";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(requestUrl,lp,String.class);
    }
    public  LincensePlate findLincese(String lincense){
        RestTemplate restTemplate=new RestTemplate();
        LincensePlate lp = new LincensePlate();
        lp.setLpLincensePlateIdCar(lincense);
        String requestUrlSelectByLincense = GlobalVariable.urlNew + "lincensePlate/selectByLincese";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(requestUrlSelectByLincense,lp,String.class);
        String body = responseEntity.getBody();
        LincensePlate lincensePlate = JSONObject.parseObject(body, LincensePlate.class);
        return lincensePlate;
    }

    public List<LincensePlate> selectByLincensePlate(String lincense){
        String url = GlobalVariable.urlNew + "lincensePlate/selectByLincensePlate";
//        String url = "http://localhost:8080/carManager_V1_0_war/lincensePlate/selectByLincensePlate";
        RestTemplate restTemplate=new RestTemplate();
        LincensePlate lp = new LincensePlate();
        lp.setLpLincensePlateIdCar(lincense);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,lp,String.class);
        String body = responseEntity.getBody();
        List<LincensePlate> lincensePlates = JSONObject.parseArray(body, LincensePlate.class);
//        List<LincensePlate> parse = (List<LincensePlate>) JSONArray.parse(body);
//        List<LincensePlate> lincensePlate = JSONObject.parseObject(body, LincensePlate.class);
        return lincensePlates;
    }

    //根据车牌组编号查询在场车辆
    public List<LincensePlate> selectLincensePlateByLgId(String lincense,Integer lgId){
        String url = GlobalVariable.urlNew + "lincensePlate/selectByLgId";
        RestTemplate restTemplate = new RestTemplate();
        LincensePlate lincensePlate = new LincensePlate();
        lincensePlate.setLpLgId(lgId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,lincensePlate,String.class);
        String body = responseEntity.getBody();
        List<LincensePlate> lincensePlates = JSONObject.parseArray(body, LincensePlate.class);
        if (lincensePlates.size() > 0) {
            Iterator<LincensePlate> iterator = lincensePlates.iterator();
            while(iterator.hasNext()){
                LincensePlate lp = iterator.next();
                if (lincense.equals(lp.getLpLincensePlateIdCar())) {
                    iterator.remove();
                }
            }
        }
        return lincensePlates;
    }

    //根据车牌组编号查询
    public JinshiLincenseGroup selectLincenseGroupByLgId(Integer jsNumber,Integer areaId){
        String url = GlobalVariable.urlNew + "jinshiLincenseGroup/selectAllByJsNumber";
        RestTemplate restTemplate = new RestTemplate();
        JinshiLincenseGroup jinshiLincenseGroup1 = new JinshiLincenseGroup();
        jinshiLincenseGroup1.setJsNumber(String.valueOf(jsNumber));
        jinshiLincenseGroup1.setJsParkId(GlobalVariable.parkId);
        jinshiLincenseGroup1.setJsAreaId(areaId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiLincenseGroup1,String.class);
        String body = responseEntity.getBody();
        JinshiLincenseGroup jinshiLincenseGroup = JSONObject.parseObject(body,JinshiLincenseGroup.class);
        return jinshiLincenseGroup;
    }

    public JinshiArea selectByParkIdAndAreaName(Integer jcParking,String areaName){
        String url = GlobalVariable.urlNew + "jinshiArea/selectByParkIdAndAreaName";
        RestTemplate restTemplate=new RestTemplate();
        JinshiArea jinshiArea = new JinshiArea();
        jinshiArea.setAreaName(areaName);
        jinshiArea.setParkId(jcParking);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiArea,String.class);
        String body = responseEntity.getBody();
        JinshiArea parse = JSONObject.parseObject(body,JinshiArea.class);
        return parse;
    }

    public List<JinshiPresenceTrack> selectPresenceTrack(String lincense, Integer parkId){
        String url = GlobalVariable.urlNew + "jinshiPresenceTrack/selectPresenceTrack";
        RestTemplate restTemplate=new RestTemplate();
        JinshiPresenceTrack jinshiPresenceTrack = new JinshiPresenceTrack();
        jinshiPresenceTrack.setPtLincensePlateId(lincense);
        jinshiPresenceTrack.setPtParkid(String.valueOf(parkId));
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiPresenceTrack,String.class);
        String body = responseEntity.getBody();
        JSONArray objects = JSONArray.parseArray(body);
        List<JinshiPresenceTrack> jinshiPresenceTrackList = objects.toJavaList(JinshiPresenceTrack.class);
        return jinshiPresenceTrackList;
    }

    public void insertHistoryTrack(Integer lpId){
        String url = GlobalVariable.urlNew + "jinshiHistoricalTrack/insertHistoryTrack";
        RestTemplate restTemplate=new RestTemplate();
        JinshiPresenceTrack jinshiPresenceTrack = new JinshiPresenceTrack();
        jinshiPresenceTrack.setPtLpId(lpId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiPresenceTrack,String.class);
    }

    public void deletePresenceTrack(Integer lpId){
        String url = GlobalVariable.urlNew + "jinshiPresenceTrack/deletePresenceTrack";
        RestTemplate restTemplate=new RestTemplate();
        JinshiPresenceTrack jinshiPresenceTrack = new JinshiPresenceTrack();
        jinshiPresenceTrack.setPtLpId(lpId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiPresenceTrack,String.class);
    }

    //修改车场设置节假日属性
    public void updateSetupHoliday(Integer type1){
        String url = GlobalVariable.urlNew + "JinshiParkingSetup/updateSetupHoliday";
        RestTemplate restTemplate=new RestTemplate();
        JinshiParkingSetup jinshiParkingSetup = new JinshiParkingSetup();
        jinshiParkingSetup.setJpsHoliday(type1);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiParkingSetup,String.class);
    }

    public JinshiCameras selectByThandle(String parkId,String thandle){
        String url = GlobalVariable.urlNew + "jinshiCameras/selectByThandle";
        RestTemplate restTemplate=new RestTemplate();
        JinshiCameras jinshiCameras = new JinshiCameras();
        jinshiCameras.setJcParking(parkId);
        jinshiCameras.setJcThandle(thandle);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiCameras,String.class);
        String body = responseEntity.getBody();
        JinshiCameras parse = JSONObject.parseObject(body,JinshiCameras.class);
        return parse;
    }

    //根据parkId获取当前车场设置
    public JinshiParkingSetup selectByParkId(Integer parkId){
        String url = GlobalVariable.urlNew + "JinshiParkingSetup/selectParkSetup";
        RestTemplate restTemplate=new RestTemplate();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("parkId",parkId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jsonObject,String.class);
        String body = responseEntity.getBody();
        JinshiParkingSetup parse = JSONObject.parseObject(body,JinshiParkingSetup.class);
        return parse;
    }

    //如果 LpLgType 为null，则去查询是否为会员
    public String isMember(String lincense){
        String url = GlobalVariable.urlNew + "lincensePlate/isMember";
        RestTemplate restTemplate=new RestTemplate();
        LincensePlate lincensePlate = new LincensePlate();
        lincensePlate.setLpLincensePlateIdCar(lincense);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,lincensePlate,String.class);
        String body = responseEntity.getBody();
        return body;
    }

    //根据areaName查询在场车辆数
    public String selectCountByAreaId(String areaName){
        String url = GlobalVariable.urlNew + "lincensePlate/selectCountByParkId";
        RestTemplate restTemplate=new RestTemplate();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("areaName",areaName);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jsonObject,String.class);
        String body = responseEntity.getBody();
        return body;
    }

    //修改摄像机状态
    public Integer updateCamerasCode(Integer parkId,JinshiCameras jinshiCameras){
        String url = GlobalVariable.urlNew + "jinshiCameras/updateCamerasCode";
        RestTemplate restTemplate=new RestTemplate();
        JinshiCameras jinshiCameras1 = new JinshiCameras();
        jinshiCameras1.setJcParking(String.valueOf(parkId));
        jinshiCameras1.setJcThandle(jinshiCameras.getJcThandle());
        String platePayState = jinshiCameras.getJcCode();
        if (null != platePayState || "" == platePayState) {
            jinshiCameras1.setJcCode(platePayState);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiCameras1,String.class);
            return 1;
        }
        return 0;
    }

    //计次车修改次数
    public void updateCarNameForTimes(Integer id,String lincensePlateId){
        String url = GlobalVariable.urlNew + "member/updateCarNameForTimes";
        RestTemplate restTemplate=new RestTemplate();
        Member member = new Member();
        member.setId(id);
        member.setLincensePlateId(lincensePlateId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,member,String.class);
    }
    //储值车修改储值金额
    public void updateCarNameForMoney(Integer id,String lincensePlateId,Integer carName){
        String url = GlobalVariable.urlNew + "member/updateCarNameForMoney";
        RestTemplate restTemplate=new RestTemplate();
        Member member = new Member();
        member.setId(id);
        member.setLincensePlateId(lincensePlateId);
        member.setStoredMoney(carName);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,member,String.class);
    }

    public void insertPresenceTrackRecord(Integer lpId,Date time,String lincense,String tHandle,String ptStatus){
        String url = GlobalVariable.urlNew + "jinshiPresenceTrack/insertPresenceTrack";
        RestTemplate restTemplate=new RestTemplate();
        JinshiPresenceTrack jinshiPresenceTrack = new JinshiPresenceTrack();
        jinshiPresenceTrack.setPtLpId(lpId);
        jinshiPresenceTrack.setPtCreatTime(time);
        jinshiPresenceTrack.setPtLincensePlateId(lincense);
        jinshiPresenceTrack.setPtThandle(tHandle);
        jinshiPresenceTrack.setPtStatus(ptStatus);
        jinshiPresenceTrack.setPtParkid(String.valueOf(GlobalVariable.parkId));
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiPresenceTrack,String.class);
//        String body = responseEntity.getBody();
//        JinshiPresenceTrack parse = JSONObject.parseObject(body,JinshiPresenceTrack.class);
//        return parse;
    }

    // 常熟添加接口
    public String insertIotRoadgateRecord(String communityname,Date date,String doorname,String licenseplate){
        String url = GlobalVariable.urlNew + "changshuController/get";
        RestTemplate restTemplate=new RestTemplate();
        Log log = new Log();
        log.setCommunityname(communityname);
        log.setAddtime(date);
        log.setDoorname(doorname);
        log.setLicenseplate(licenseplate);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,log,String.class);
        String body = responseEntity.getBody();
        return body;
    }

    // 常熟添加接口
    public String iotCarCrossInserts(String communityname,Date date,String cameraBrand,String cameraType,
                                     String cameraNumber,String colorPlate,String licensePlate,String snapPhotoMul,String crossChannel){
        String url = GlobalVariable.urlNew + "changshuController/car";
        RestTemplate restTemplate=new RestTemplate();
        Cartt cartt = new Cartt();
        cartt.setCommunityName(communityname);
        cartt.setCameraBrand(cameraBrand);
        cartt.setCameraType(cameraType);
        cartt.setCameraNumber(cameraNumber);
        cartt.setLicensePlate(licensePlate);
        if(colorPlate.equals("1")){
            cartt.setColorPlate("汽油车");
        }else if(colorPlate.equals("11")){
            cartt.setColorPlate("能源车");
        }else if(colorPlate.equals("2")){
            cartt.setColorPlate("蓝牌能源车");
        }else if(colorPlate.equals("3")){
            cartt.setColorPlate("黄牌车");
        }
        cartt.setSnapTime(date);
        cartt.setSnapPhotoMul(snapPhotoMul);
        cartt.setCrossChannel(crossChannel);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,cartt,String.class);
        String body = responseEntity.getBody();
        return body;
    }

    // 查询是否黑名单车辆
    public JSONObject checkBlackListByLinceseAndParkId(String licenseplate,Integer parkId){
        String url = GlobalVariable.urlNew + "blackList/checkBlackListByLinceseAndParkId";
        RestTemplate restTemplate=new RestTemplate();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lincensePlateNumber",licenseplate);
        jsonObject.put("parkId",parkId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jsonObject,String.class);
        String body = responseEntity.getBody();
        JSONObject json = JSONObject.parseObject(body);
        if(json.size()>0){
            JSONArray blackList = JSONObject.parseArray(json.get("blackList").toString());
            if(blackList.size()>0){
                JSONObject o = (JSONObject) blackList.get(0);
                json = o;
            }else{
                return null;
            }
        }
        return json;
    }

    // 查询是否白名单车辆
    public String checkWhiteList(String licenseplate,Integer parkId){
        String url = GlobalVariable.urlNew + "whiteList/checkWhiteListByLincese";
        RestTemplate restTemplate=new RestTemplate();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lincensePlateNumber",licenseplate);
        jsonObject.put("parkId",parkId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jsonObject,String.class);
        String body = responseEntity.getBody();
        return body;
    }

    public String getCameraName(){
        return this.cameraName;
    }
//    public Integer cameraInit() {
//        this.net = NET.INSTANCE;
//        return net.Net_Init();
//    }
    public Integer initCallBack(){
        int rire = net.Net_RegImageRecv(fcb);
        System.out.println(StatusCode.getStatusCode(rire,"Net_RegImageRecvEx"));
        if (rire == 0) {
            System.out.println("强制抓拍Net_RegImageRecv回调函数注册完毕");
        }
        return 0;
    }

    public Integer addCamera(String ipAddress){
        int handleNet = HandleManage.gettHandle(net);
//        if (handleNet == -1) {
        handle = net.Net_AddCamera(ipAddress);
        logger.info("ptIp: "+ipAddress);
        logger.info("tHandle: "+handle);
        logger.info("cameraName: "+cameraName);
        if (handle != -1) {
            System.out.println("执行接口Net_AddCamera()完毕，返回相机句柄："
                    + handle + ",有效");
            HandleManage.settHandle(handle);
        } else {
            System.out.println("执行接口Net_AddCamera()完毕,相机句柄：-1，无效");
        }
//        } else {
//            System.out.println("直接返回相机句柄：handle: " + handle);
//        }
        return handle;
    }

    public Integer stopConn(int tHandle){
        int dcc = net.Net_DisConnCamera(tHandle);
        logger.info("全局句柄："
                + tHandle
                + " (为-1请先连接相机)\n"
                + StatusCode.getStatusCode(dcc,
                "Net_DisConnCamera"));
        return dcc;
    }
    public void connCamera(Integer tHandle){
////        int tHandle = HandleManage.gettHandle(net);
//        short usPort = 30000;
//        short usTimeout = 60;
//        int cc = net.Net_ConnCamera(handle, usPort,
//                usTimeout);
//        if (cc == 0) {
////            startRtspVideo(ptIp);
//            HandleManage.settHandle(handle);
//            System.out.println("全局设备句柄" + handle
//                    + "已生效");
//        }
//        System.out.println(StatusCode
//                .getStatusCode(cc, "Net_ConnCamera"));
//        return 0;
        System.out.println("enter to this");
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//           int thandle = HandleManage.gettHandle(net);
        short usPort = 30000;
        short usTimeout = 60;
        int cc = net.Net_ConnCamera(tHandle, usPort,
                usTimeout);
        if (cc == 0) {
//                    startRtspVideo(ptIp);
            HandleManage.settHandle(tHandle);
            System.out.println("全局设备句柄" + tHandle
                    + "已生效");
        }
        System.out.println(StatusCode
                .getStatusCode(cc, "Net_ConnCamera"));
        int rire = net.Net_RegImageRecv(fcb);
        logger.info(StatusCode.getStatusCode(rire,
                "Net_RegImageRecvEx"));
        if (rire == 0) {
            logger.info("强制抓拍Net_RegImageRecv回调函数注册完毕");
        }
//            }
//        }).start();
    }

    public Integer setSnapMode(){
        int ssm = net.Net_SetSnapMode(HandleManage.gettHandle(net), 0);
        System.out.println(StatusCode.getStatusCode(ssm, "Net_SetSnapMode"));
        return 0;
    }

    //播放视频test
    private boolean iSPlay;
    public Integer playMedia(Integer thandle){
        String url = GlobalVariable.urlNew + "jinshiCameras/selectByThandle";
        RestTemplate restTemplate=new RestTemplate();
        JinshiCameras jinshiCameras = new JinshiCameras();
        jinshiCameras.setJcThandle(String.valueOf(thandle));
        jinshiCameras.setJcParking(String.valueOf(GlobalVariable.parkId));
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiCameras,String.class);
        String body = responseEntity.getBody();
        JinshiCameras jinshiCameras1 = JSONObject.parseObject(body,JinshiCameras.class);
        String ip = jinshiCameras1.getJcIpAddress();
        System.out.println("ip: ----" + ip);
        if (iSPlay) {
            player.stop();
        }
        String[] options = {"video-filter=motionblur",  "network-caching=200", "no-plugins-cache",":rtsp-tcp"};
        iSPlay = true;
        String ipcon = "192.168.0.10";
        player.playMedia("rtsp://"+ip+":50000/video",options);
        logger.info("player.playMedia End------------------------"+thandle);
        return 1;
    }

    public Integer openGate(Integer thandle){
        Data_T_ControlGate.T_ControlGate.ByReference ptControlGate = new Data_T_ControlGate.T_ControlGate.ByReference();
        ptControlGate.ucState = (byte) 1;
        int gs = net.Net_GateSetup(thandle, ptControlGate);
        logger.info("openGate End------------------------"+thandle);
        System.out.println(StatusCode.getStatusCode(gs, "Net_GateSetup"));
        return gs;
    }

    public Integer closeGate(Integer thandle){
        Data_T_ControlGate.T_ControlGate.ByReference ptControlGate = new Data_T_ControlGate.T_ControlGate.ByReference();
        ptControlGate.ucState = (byte) 2;
//        HandleManage.gettHandle(net)
        int gs = net.Net_GateSetup(thandle, ptControlGate);
        logger.info("closeGate End------------------------"+thandle);
        System.out.println(StatusCode.getStatusCode(gs, "Net_GateSetup"));
        return gs;
    }

    public Integer shutDownGate(){
        Data_T_ControlGate.T_ControlGate.ByReference ptControlGate = new Data_T_ControlGate.T_ControlGate.ByReference();
        ptControlGate.ucState = (byte) 3;
        int gs = net.Net_GateSetup(HandleManage.gettHandle(net), ptControlGate);
        System.out.println(StatusCode.getStatusCode(gs, "Net_GateSetup"));
        return 0;
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

    private static void isChartPathExist(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
            logger.info("创建新的图片文件夹成功");
        }
    }

    //判断是否黑名单车辆
    public boolean checkBlackList(int camTHandle, String lincense,int tHandle) throws Exception{
        JSONObject jsonObject = checkBlackListByLinceseAndParkId(lincense, GlobalVariable.parkId);
        if (jsonObject != null) {
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, "黑名单车辆"), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "禁止进入"), (byte) 0);
            logger.info("黑名单车辆：" + lincense + "  被禁止驶入");
            return false;
        }
        return true;
    }
    //判断当前车场在场车辆数 和 车场车位数
    public boolean checkCountISTrue(int camTHandle, String lincense,int tHandle,String picPathHttp, byte lprType,JinshiParkingSetup jinshiParkingSetup,JinshiCameras jinshiCameras,JinshiArea jinshiArea) throws Exception{
        String count = selectCountByAreaId(jinshiArea.getAreaName()); //查询当前车场在场车辆数
        Integer allowNegativeCar = jinshiArea.getAllowNegativeCar();//允许负车位数
        Integer parkingCount = jinshiArea.getParkingCount();//车位数
        Integer temporaryParkingCount = jinshiArea.getTemporaryParkingCount();//临时车位数
        Integer total = allowNegativeCar + parkingCount;
//        Integer parkPlaceNumber = GlobalVariable.parkPlaceNumber;
        //判断当前车场在场车辆数 和 车场车位数
        if (Integer.parseInt(count) >= total) {
            //在场车辆数大于车场车位数 并且 设置允许负车位数=0，则说明此时不允许车辆进入
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, lincense), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "车位已满"), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, "车位已满"), (byte) 0);
            jinshiCameras.setCameraLicenseId(lincense);//车牌号
            jinshiCameras.setCameraInboundTime(new Date());//入场时间
            jinshiCameras.setCameraLincenseType(String.valueOf(lprType));//车类型
            jinshiCameras.setPicPath(picPathHttp); //图片地址
            CameraParam.cameraMap.put(tHandle, jinshiCameras);//将车辆信息推送到前端
            logger.info(jinshiArea.getAreaName() + ":  车位已满");
            return false;
        }
        return true;
    }

    //设置黄牌车禁止入内 说明不允许黄牌车进入
    public boolean checkISYellow(int camTHandle, String lincense,int tHandle,String picPathHttp, byte lprType,JinshiParkingSetup jinshiParkingSetup,JinshiCameras jinshiCameras) throws Exception{
        if (3 == lprType && 0 == jinshiParkingSetup.getJpsYellowLincenseAllow()) {
            //设置黄牌车禁止入内 说明不允许黄牌车进入
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, lincense), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "无权入场"), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, "黄牌车禁止入内"), (byte) 0);
            jinshiCameras.setCameraLicenseId(lincense);//车牌号
            jinshiCameras.setCameraInboundTime(new Date());//入场时间
            jinshiCameras.setCameraLincenseType(String.valueOf(lprType));//车类型
            jinshiCameras.setPicPath(picPathHttp); //图片地址
            CameraParam.cameraMap.put(tHandle, jinshiCameras);//推送前端
            logger.info(lincense + ":  黄牌车禁止入场");
            return false;
        }
        return true;
    }
    // 为1则说明开启了常熟接口
    public void checkISChangshu(String lincense,byte plateColor,JinshiParkingSetup jinshiParkingSetup,JinshiCameras jinshiCameras) {
        if (1 == jinshiParkingSetup.getJpsOpenLocalInterface()) {
            insertIotRoadgateRecord(GlobalVariable.parkName,new Date(),jinshiCameras.getJcName(),lincense);
            iotCarCrossInserts(GlobalVariable.parkName,new Date(),"芊熠",jinshiCameras.getJcRemarks(),jinshiCameras.getCameraLicenseId(),String.valueOf(plateColor),lincense,GlobalVariable.picPathHttp,jinshiCameras.getJcName());
            logger.info("开启了常熟接口： " + jinshiParkingSetup.getJpsOpenLocalInterface());
        }
    }
    //是否关注
    public Boolean checkISAttention(JinshiAttention attention,String lincense,int tHandle) throws Exception{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        if (attention != null) {
            SmsController smsController = new SmsController();
            Map<String, String> sms = smsController.sms(attention.getJcPhone(), lincense, sdf.format(new Date()), "森林公园停车场", CameraParam.cameraMap.get(tHandle).getJcName());
            return true;
        }
        return false;
    }
    //添加关注记录
    public String insertAttention(String lincense,Date date,String thandle,String orderId,Integer parkId){
        String url = GlobalVariable.urlNew + "JinshiAttentionRecord/insert";
        RestTemplate restTemplate=new RestTemplate();
        JinshiAttentionRecord jinshiAttentionRecord = new JinshiAttentionRecord();
        jinshiAttentionRecord.setJaPlate(lincense);
        jinshiAttentionRecord.setJaInboundTime(date);
        jinshiAttentionRecord.setJaInboundCname(thandle);
        jinshiAttentionRecord.setJaOrderId(orderId);
        jinshiAttentionRecord.setJaParkId(parkId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiAttentionRecord,String.class);
        String body = responseEntity.getBody();
        return body;
    }
    //修改关注记录
    public String updateAttention(Date nowDate,String thandle,String orderId,Double rent){
        String url = GlobalVariable.urlNew + "JinshiAttentionRecord/updateByOrderId";
        RestTemplate restTemplate=new RestTemplate();
        JinshiAttentionRecord jinshiAttentionRecord = new JinshiAttentionRecord();
        jinshiAttentionRecord.setJaDepartureTime(nowDate);
        jinshiAttentionRecord.setJaDepartureCname(thandle);
        jinshiAttentionRecord.setJaRealCost(String.valueOf(rent));
        jinshiAttentionRecord.setJaOrderId(orderId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiAttentionRecord,String.class);
        String body = responseEntity.getBody();
        return body;

    }
    //是否车牌组
    public void checkISLgid(int camTHandle,String lincense,Integer jsNumber,int tHandle,Member member1,int memberDays,JinshiCameras jinshiCameras,JinshiLincenseGroup jinshiLincenseGroup1,JinshiArea jinshiArea) throws Exception{
        JinshiLincenseGroup jinshiLincenseGroup = selectLincenseGroupByLgId(jsNumber,jinshiArea.getId());
        List<LincensePlate> lincensePlates = selectLincensePlateByLgId(lincense,jsNumber);
        String jsCarCount = jinshiLincenseGroup.getJsCarCount();
        if (lincensePlates.size() >= Integer.parseInt(jsCarCount)) {
            //此车牌组在场车辆数已经大于车牌组应有车辆数，作临时车处理
            jinshiCameras.setCameraMemberType("0");
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "临时车辆"), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "临时车辆"), (byte) 0);
            jinshiLincenseGroup1.setJsNumber(jinshiLincenseGroup.getJsNumber());
        } else {
            jinshiCameras.setCameraMemberType("1"); //会员属性
            String memberAddress = member1.getMemberAddress();//到期提醒
            Date expirationTime = member1.getExpirationTime();
            int diffDays = DateUtils.getDiffDays(new Date(),expirationTime); //计算到期时间和当前日期差
            if (memberAddress != null) {
                if (memberDays < 16) {
                    //月租到期前N天显示屏显示提示，语音提示，默认15天；
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "月租快到期"), (byte) 0);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "此卡剩余天数为"+memberDays+"天"), (byte) 0);
                    jinshiLincenseGroup1.setJsNumber(jinshiLincenseGroup.getJsNumber());
                }else if (Integer.parseInt(memberAddress) == diffDays){
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "月租快到期"), (byte) 0);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "此卡剩余天数为"+diffDays+"天"), (byte) 0);
                    jinshiLincenseGroup1.setJsNumber(jinshiLincenseGroup.getJsNumber());
                }
            }
            //月租车
            String serviceType = member1.getServiceType();
            if (serviceType == null || "".equals(serviceType) || serviceType.equals("月租车") || serviceType.equals("内部车") || serviceType.equals("贵宾车")) {
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "月卡车"), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "月卡车"), (byte) 0);
            } else if (serviceType.equals("储值车")) {
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "储值车"), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "临时车辆"), (byte) 0);
            } else if (serviceType.equals("计次车")) {
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "计次车"), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "临时车辆"), (byte) 0);
            }
            jinshiLincenseGroup1.setJsNumber(jinshiLincenseGroup.getJsNumber());
        }
    }

    //如果没有车牌组则会员车放行
    public void checkMemberOpenGate(int camTHandle,int tHandle,Member member1,int memberDays,JinshiCameras jinshiCameras) throws Exception {
        String memberAddress = member1.getMemberAddress();
        Date expirationTime = member1.getExpirationTime();
        int diffDays = DateUtils.getDiffDays(new Date(),expirationTime); //计算到期时间和当前日期差
        if (memberAddress != null) {
            if (memberDays < 16) {
                //月租到期前N天显示屏显示提示，语音提示，默认15天；
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "月租快到期"), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "此卡剩余天数为"+memberDays+"天"), (byte) 0);
            }else if (Integer.parseInt(memberAddress) == diffDays){
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "月租快到期"), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "此卡剩余天数为"+diffDays+"天"), (byte) 0);
            }
        }
        jinshiCameras.setCameraMemberType("1");
        String serviceType = member1.getServiceType();
        if (serviceType == null || "".equals(serviceType) || serviceType.equals("月租车") || serviceType.equals("内部车") || serviceType.equals("贵宾车")) {
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "月卡车"), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "月卡车"), (byte) 0);
        } else if (serviceType.equals("储值车")) {
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "储值车"), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "临时车辆"), (byte) 0);
        } else if (serviceType.equals("计次车")) {
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "计次车"), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "临时车辆"), (byte) 0);
        }
    }

    //获取进场时添加的是否会员属性
    public void checkISLgType(int camTHandle,Integer lpLgType,int tHandle,JinshiCameras jinshiCameras) throws Exception{
        if (null == lpLgType) {
            jinshiCameras.setCameraMemberType("0");
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, "无外场入场纪录"), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "无外场入场纪录"), (byte) 0);
        } else if (0 == lpLgType) {
            jinshiCameras.setCameraMemberType("0");
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, "临时车辆"), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "临时车辆"), (byte) 0);
        } else if (1 == lpLgType) {
            jinshiCameras.setCameraMemberType("1");
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, "月卡车"), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "月卡车"), (byte) 0);
        }
    }
    //获取进场时添加的是否会员属性
    public void checkISLgTypeTwo(LincensePlate lincense,JinshiCameras jinshiCameras) {
//        if (lincensePlates.size() > 0) {
//            for (LincensePlate lincensePlate : lincensePlates) {
                Integer lpLgType = lincense.getLpLgType();
                if (null == lpLgType) {
                    String type = isMember(lincense.getLpLincensePlateIdCar());
                    jinshiCameras.setCameraMemberType(type);
                } else if (1 == lpLgType) {
                    jinshiCameras.setCameraMemberType("1");
                } else if (0 == lpLgType) {
                    jinshiCameras.setCameraMemberType("0");
                }
//            }
//        }
    }

    //内场出口判断
    public boolean checkCloseGate(int camTHandle,String lincense,int tHandle,List<LincensePlate> lincensePlateList,LincensePlate lincese,JinshiCameras jinshiCameras) throws Exception{
        for (LincensePlate lincensePlate : lincensePlateList) {
            Integer lpLgType = lincensePlate.getLpLgType();
            if (1 == lpLgType) {
                //如果是会员
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, lincense), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "月卡车"), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, "月卡车"), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, ""), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, "欢迎下次光临"), (byte) 0);
                jinshiCameras.setCameraLicenseId(lincese.getLpLincensePlateIdCar());
                jinshiCameras.setCameraInboundTime(lincese.getLpInboundTime());
                jinshiCameras.setCameraDepartureTime(new Date());
                jinshiCameras.setCameraRent(0.0);
                jinshiCameras.setPlatePayState("会员：无需付费");
                CameraParam.cameraMap.put(tHandle, jinshiCameras);//推送前端信息
                jinshiCameras.setJcCode("会员：无需付费");
                updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
                return false;
            } else if (0 == lpLgType) {
                //临时车
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, lincense), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "临时车辆"), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, "临时车"), (byte) 0);
                jinshiCameras.setPlatePayState("临时车：出内场");
                CameraParam.cameraMap.put(tHandle, jinshiCameras);//推送前端
                jinshiCameras.setJcCode("临时车：出内场");
                updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
            } else if (null == lpLgType) {
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, lincense), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "临时车辆"), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, "无内场入场纪录"), (byte) 0);
                jinshiCameras.setPlatePayState("临时车：出内场");
                CameraParam.cameraMap.put(tHandle, jinshiCameras);//推送前端
                jinshiCameras.setJcCode("临时车：出内场");
                updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
            }
        }
        return true;
    }
    //内场出场无信息处理
    public void checkISInfield(int camTHandle,String lincense,int tHandle,JinshiParkingSetup jinshiParkingSetup,List<LincensePlate> lincensePlates,JinshiCameras jinshiCameras) throws Exception{
        jinshiCameras.setCameraMemberType("2");
        if (lincensePlates.size() > 0) {
            for (LincensePlate lincensePlate : lincensePlates) {
                Integer lpLgType = lincensePlate.getLpLgType();
                if (null == lpLgType) {
                    String type = isMember(lincense);
                    jinshiCameras.setCameraMemberType(type);
                } else if (1 == lpLgType) {
                    jinshiCameras.setCameraMemberType("1");
                } else if (0 == lpLgType) {
                    jinshiCameras.setCameraMemberType("0");
                }
            }
        }
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, lincense), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "此卡无效"), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "无效卡"), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, ""), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, ""), (byte) 0);
        jinshiCameras.setCameraInboundTime(null);
        jinshiCameras.setCameraOften(null);
        jinshiCameras.setCameraRent(null);
        jinshiCameras.setPlatePayState("无入场信息");
        CameraParam.cameraMap.put(tHandle, jinshiCameras);
        jinshiCameras.setJcCode("无入场信息");
        updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
        if (1 == jinshiParkingSetup.getJpsInvalidRelease()) {
            this.openGate(camTHandle);
        }
    }

    //外场出口无信息处理
    public void checkISOutOpen(int camTHandle,String lincense,int tHandle,JinshiCameras jinshiCameras,JinshiParkingSetup jinshiParkingSetup) throws Exception{
        jinshiCameras.setCameraMemberType("2");
        List<LincensePlate> lincensePlates = selectByLincensePlate(lincense);
        if (lincensePlates.size() > 0) {
            for (LincensePlate lincensePlate : lincensePlates) {
                Integer lpLgType = lincensePlate.getLpLgType();
                if (null == lpLgType) {
                    String type = isMember(lincense);
                    jinshiCameras.setCameraMemberType(type);
                } else if (1 == lpLgType) {
                    jinshiCameras.setCameraMemberType("1");
                } else if (0 == lpLgType) {
                    jinshiCameras.setCameraMemberType("0");
                }
            }
        }
        jinshiCameras.setCameraLicenseId(lincense); //车牌号
        jinshiCameras.setPlatePayState("无入场信息");
        jinshiCameras.setCameraInboundTime(null);
        jinshiCameras.setCameraOften(null);
        jinshiCameras.setCameraRent(null);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, lincense), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "此卡无效"), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "无效卡"), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, ""), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, ""), (byte) 0);
        CameraParam.cameraMap.put(tHandle, jinshiCameras);
        jinshiCameras.setJcCode("无入场信息");
        updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
        if (1 == jinshiParkingSetup.getJpsInvalidRelease()) {
            this.openGate(camTHandle);
        }
        inserHistory(lincense, "无效卡出场", new Date(), tHandle);
    }

    //查询是否优惠券用户
    public boolean checkISCoupon(int camTHandle,String lincense,int tHandle,Double rent,Integer dateOften,LincensePlate lincese,JinshiCameras jinshiCameras,JinshiParkingSetup jinshiParkingSetup) throws Exception{
        JSONObject jsonObject = selectOrderByPlate("0", lincense);
        if (jsonObject != null) {
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, lincense), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "免费卡"), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "优惠券车辆"), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, "无需交费"), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, "停车时间：" + dateOften + "分钟"), (byte) 0);
            jinshiCameras.setPlatePayState("免费车辆:优惠券车辆离场");
            jinshiCameras.setCameraLicenseId(lincese.getLpLincensePlateIdCar());
            jinshiCameras.setCameraInboundTime(lincese.getLpInboundTime());
            jinshiCameras.setCameraDepartureTime(new Date());
            jinshiCameras.setCameraRent(rent);
            CameraParam.cameraMap.put(tHandle, jinshiCameras);//更新前端
            jinshiCameras.setJcCode("免费车辆:优惠券车辆离场");
            updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
            logger.info("车牌号：" + lincense + "优惠券车辆离场");
            updataProcess(lincese, lincense, new Date(), rent, tHandle, "支付成功", "优惠券出场", dateOften);//更新数据库
            if (jinshiParkingSetup.getJpsPayType() == 0) {
                Carout(lincense, getUnixTime(lincese.getLpInboundTime()), lincese.getLpOrderId(), 0.0);//通知铂链
            } else if (jinshiParkingSetup.getJpsPayType() == 1) {
                carOutNotice(lincense,GlobalVariable.parkId,rent,dateOften,new Date(),tHandle,"优惠券出场");
            }
            this.openGate(camTHandle);//抬杆
            Integer jcoId = (Integer) jsonObject.get("jcoId");
            updateOrderByPlate("1", lincense, jcoId, lincese.getLpInboundTime(), new Date(), dateOften);
            return false;
        }
        return true;
    }
    //查询是否优惠券用户-------------------
    public JSONObject checkISCouponTEST(int camTHandle,String lincense,int tHandle,Double rent,Integer dateOften,LincensePlate lincese,JinshiCameras jinshiCameras,JinshiParkingSetup jinshiParkingSetup) throws Exception{
        JSONObject json = new JSONObject();
        JSONObject jsonObject = selectOrderByPlate("0", lincense);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (jsonObject != null) {
            Integer jcgId = (Integer) jsonObject.get("jcoCouponGenerateId");
            JSONObject jsonObject1 = selectGenerateById(jcgId);
            if (jsonObject1 != null) {
                Integer jcgType = (Integer) jsonObject1.get("jcgType");
                if (jcgType != null && jcgType == 1) {//减免时长优惠券
                    Integer jcgReliefTime = (Integer) jsonObject1.get("jcgReliefTime");//减免分钟
                    Integer times = dateOften - jcgReliefTime;
                    if (times > 0) {
                        long time = lincese.getLpInboundTime().getTime();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(time + 60*1000*jcgReliefTime);
                        Date format1 = format.parse(format.format(calendar.getTime()));
                        Double rent1 = getRent(new Date(), format1, lincese.getLpLincenseType());
                        json.put("rent",rent1);
                        return json;//支付
                    }
                } else if (jcgType != null && jcgType == 2) {//减免金额优惠券
                    String jcgReliefMoney = (String) jsonObject1.get("jcgReliefMoney");
                    int i = Integer.parseInt(jcgReliefMoney);
                    Double moneys = rent - i;
                    if (moneys > 0) {
                        json.put("rent",moneys);
                        return json;//支付
                    }
                }
            }
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, lincense), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "免费卡"), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "优惠券车辆"), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, "无需交费"), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, "停车时间：" + dateOften + "分钟"), (byte) 0);
            jinshiCameras.setPlatePayState("免费车辆:优惠券车辆离场");
            jinshiCameras.setCameraLicenseId(lincese.getLpLincensePlateIdCar());
            jinshiCameras.setCameraInboundTime(lincese.getLpInboundTime());
            jinshiCameras.setCameraDepartureTime(new Date());
            jinshiCameras.setCameraRent(rent);
            CameraParam.cameraMap.put(tHandle, jinshiCameras);//更新前端
            jinshiCameras.setJcCode("免费车辆:优惠券车辆离场");
            updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
            logger.info("车牌号：" + lincense + "优惠券车辆离场");
            updataProcess(lincese, lincense, new Date(), rent, tHandle, "支付成功", "优惠券出场", dateOften);//更新数据库
            if (jinshiParkingSetup.getJpsPayType() == 0) {
                Carout(lincense, getUnixTime(lincese.getLpInboundTime()), lincese.getLpOrderId(), 0.0);//通知铂链
            } else if (jinshiParkingSetup.getJpsPayType() == 1) {
                carOutNotice(lincense,GlobalVariable.parkId,rent,dateOften,new Date(),tHandle,"优惠券出场");
            }
            this.openGate(camTHandle);//抬杆
            Integer jcoId = (Integer) jsonObject.get("jcoId");
            updateOrderByPlate("1", lincense, jcoId, lincese.getLpInboundTime(), new Date(), dateOften);
            json.put("flag",true);
            return json;
        }
        json.put("flag",false);
        return json;
    }
    //外场出口预付款及会员出场处理
    public boolean checkOutOpenGate(int camTHandle,LincensePlate lincese,int tHandle,String lincense,Double rent,JinshiParkingSetup jinshiParkingSetup,Integer dateOften,JinshiCameras jinshiCameras) throws Exception{
        List<LincensePlate> lincensePlateList = selectByLincensePlate(lincense);
        if (lincensePlateList.size() > 0) {
            for (LincensePlate lincensePlate : lincensePlateList) {
                Integer lpLgType = lincensePlate.getLpLgType();
                if (lincese.getLpOrderState().equals("支付成功")) {  //如果是预付款订单状态会改变为支付成功
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, lincense), (byte) 0);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "临时车辆"), (byte) 0);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "已缴费"), (byte) 0);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "临时车辆"), (byte) 0);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, "已交费"), (byte) 0);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, "停车时间：" + dateOften + "分钟"), (byte) 0);
                    logger.info("tHandle:" + tHandle + " ready start openGate");
                    this.openGate(camTHandle);//抬杆
                    jinshiCameras.setPlatePayState("支付成功:预付款离场");
                    jinshiCameras.setCameraLicenseId(lincese.getLpLincensePlateIdCar());
                    jinshiCameras.setCameraInboundTime(lincese.getLpInboundTime());
                    jinshiCameras.setCameraDepartureTime(new Date());
                    jinshiCameras.setCameraRent(rent);
                    CameraParam.cameraMap.put(tHandle, jinshiCameras);//更新前端
                    jinshiCameras.setJcCode("支付成功:预付款离场");
                    updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
                    logger.info("车牌号：" + lincense + " 预付款离场");
                    updataProcess(lincese, lincense, new Date(), rent, tHandle, "支付成功", "预付款出场", dateOften);//更讯数据库
                    if (jinshiParkingSetup.getJpsPayType() == 0) {
                        Carout(lincense, getUnixTime(lincese.getLpInboundTime()), lincese.getLpOrderId(), rent);//通知铂链
                    } else if (jinshiParkingSetup.getJpsPayType() == 1) {
                        carOutNotice(lincense,GlobalVariable.parkId,rent,dateOften,new Date(),tHandle,"预付款出场");
                    }
                    return false;
                } else if (rent == 0.0) { //免费时长
                    this.openGate(camTHandle);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, lincense), (byte) 0);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "免费停车"), (byte) 0);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "免费时间内出场"), (byte) 0);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, "0元"), (byte) 0);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, "停车时间：" + dateOften + "分钟"), (byte) 0);
                    jinshiCameras.setCameraInboundTime(lincese.getLpInboundTime());
                    jinshiCameras.setCameraLicenseId(lincese.getLpLincensePlateIdCar());
                    jinshiCameras.setCameraDepartureTime(new Date());
                    jinshiCameras.setCameraRent(rent);
                    jinshiCameras.setPlatePayState("免费时长内离场");
                    logger.info("车牌号：" + lincense + "免费时长内离场");
                    CameraParam.cameraMap.put(tHandle, jinshiCameras);//推送前端
                    jinshiCameras.setJcCode("免费时长内离场");
                    updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
                    updataProcess(lincese, lincense, new Date(), 0.0, tHandle, "支付成功", "免费时长内出场", dateOften);//更新数据库
                    if (jinshiParkingSetup.getJpsPayType() == 0) {
                        Carout(lincense, getUnixTime(lincese.getLpInboundTime()), lincese.getLpOrderId(), rent);//通知铂链
                    } else if (jinshiParkingSetup.getJpsPayType() == 1) {
                        carOutNotice(lincense,GlobalVariable.parkId,rent,dateOften,new Date(),tHandle,"免费时长内出场");
                    }
                    return false;
                }
            }
        }
        return true;
    }
    //外场出口会员出场处理
    public boolean checkMemberOut(int camTHandle,LincensePlate lincese,int tHandle,String lincense,Double rent,JinshiParkingSetup jinshiParkingSetup,Integer dateOften,JinshiCameras jinshiCameras) throws Exception {
        Integer lpLgType = lincese.getLpLgType();
        if (1 == lpLgType) { //如果是会员
            //查询会员信息
            List<Member> memberList = checkISMember(lincense, jinshiCameras.getJcArea());
            Integer jpsHoliday = jinshiParkingSetup.getJpsHoliday();
            if (memberList != null && memberList.size() > 0) {
                Member member = memberList.get(0);
                String carName = member.getCarName();
                Integer storedMoney = member.getStoredMoney();
                String serviceType = member.getServiceType();
                Date today = new Date();
                Date expirationTime = member.getExpirationTime();
                int i = today.compareTo(expirationTime); //判断当前日期是否在有效期内
                //判断收费类型
                if (null != carName) {
                    if ("计次车".equals(serviceType)) {
                        if (Integer.valueOf(member.getCarName()) >= 1 && (i == -1 || i == 0)) {
                            updateCarNameForTimes(member.getId(), member.getLincensePlateId());
                            logger.info("计次车离场");
                        }else {
                            //免费次数不足,提示缴费，按临时车收费出场
                            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
                            if (i == 1) {
                                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, "已过期"), (byte) 0);
                            } else {
                                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, "余次不足，请缴费"), (byte) 0);
                                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "剩余次数：" + carName + "次"), (byte) 0);
                            }
                            return true;
                        }
                    } else if ("节假日车".equals(serviceType)) {
                        if (0 == jpsHoliday) {
                            //节假日车工作日收费
                            if (Double.parseDouble(member.getCarName()) >= rent) {
                                updateCarNameForMoney(member.getId(), member.getLincensePlateId(), rent.intValue());
                                logger.info("节假日车离场");
                            } else {
                                //余额不足,提示缴费，按临时车收费出场
                                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
                                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, "余额不足，请缴费"), (byte) 0);
                                return true;
                            }
                        }
                    } else if ("工作日车".equals(serviceType)) {
                        if (1 == jpsHoliday || 2 == jpsHoliday) {
                            //工作日车节假日收费
                            if (Double.parseDouble(member.getCarName()) >= rent) {
                                updateCarNameForMoney(member.getId(), member.getLincensePlateId(), rent.intValue());
                                logger.info("工作日车离场");
                            } else {
                                //余额不足,提示缴费，按临时车收费出场
                                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
                                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, "余额不足，请缴费"), (byte) 0);
                                return true;
                            }
                        }
                    }
                }
                if (storedMoney != null) {
                    if ("储值车".equals(serviceType)) {
                        if (storedMoney >= rent) {
                            updateCarNameForMoney(member.getId(), member.getLincensePlateId(), rent.intValue());
                            logger.info("储值车离场");
                        } else {
                            //余额不足,提示缴费，按临时车收费出场
                            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
                            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, "余额不足，请缴费"), (byte) 0);
                            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "剩余金额：" + storedMoney + "元"), (byte) 0);
                            return true;
                        }
                    }
                }
                logger.info("tHandle:" + tHandle + " ready start openGate");
                this.openGate(camTHandle);//抬杆
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, lincense), (byte) 0);
                if (serviceType == null || "".equals(serviceType) || serviceType.equals("月租车") || serviceType.equals("内部车") || serviceType.equals("贵宾车")) {
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "月卡车"), (byte) 0);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "月卡车"), (byte) 0);
                } else if (serviceType.equals("储值车")) {
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "储值车"), (byte) 0);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "临时车辆"), (byte) 0);
                } else if (serviceType.equals("计次车")) {
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "计次车"), (byte) 0);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "临时车辆"), (byte) 0);
                }
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, "0元"), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, "停车时间：" + dateOften + "分钟"), (byte) 0);
                jinshiCameras.setCameraLicenseId(lincese.getLpLincensePlateIdCar());
                jinshiCameras.setCameraInboundTime(lincese.getLpInboundTime());
                jinshiCameras.setCameraDepartureTime(new Date());
                jinshiCameras.setCameraRent(0.0);
                jinshiCameras.setPlatePayState("会员：无需付费");
                logger.info("车牌号：" + lincense + "会员离场");
                CameraParam.cameraMap.put(tHandle, jinshiCameras);//推送前端信息
                jinshiCameras.setJcCode("会员：无需付费");
                updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
                if ("储值车".equals(serviceType)) {
                    updataProcess(lincese, lincense, new Date(), rent, tHandle, "支付成功", "储值车出场", dateOften);//更新数据库
                    if (jinshiParkingSetup.getJpsPayType() == 0) {
                        Carout(lincense, getUnixTime(lincese.getLpInboundTime()), lincese.getLpOrderId(), 0.0);//通知铂链
                    } else if (jinshiParkingSetup.getJpsPayType() == 1) {
                        carOutNotice(lincense,GlobalVariable.parkId,0.0,dateOften,new Date(),tHandle,"储值车出场");
                    }
                } else {
                    updataProcess(lincese, lincense, new Date(), 0.0, tHandle, "支付成功", "月租车出场", dateOften);//更新数据库
                    if (jinshiParkingSetup.getJpsPayType() == 0) {
                        Carout(lincense, getUnixTime(lincese.getLpInboundTime()), lincese.getLpOrderId(), 0.0);//通知铂链
                    } else if (jinshiParkingSetup.getJpsPayType() == 1) {
                        carOutNotice(lincense,GlobalVariable.parkId,0.0,dateOften,new Date(),tHandle,"月租车出场");
                    }
                }
                return false;
            }
        }
        return true;
    }
    //外场出口临时车预处理
    public void checkTemporary(int camTHandle,int tHandle,String lincense,Double rent,Integer dateOften,JinshiCameras jinshiCameras,String orderId) throws Exception{

        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, lincense), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "临时车辆"), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "请交费" + rent + "元"), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "临时车辆,请扫码支付"), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, "请交费：" + rent + "元"), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, "停车时间：" + dateOften + "分钟"), (byte) 0);
        jinshiCameras.setPlatePayState("临时车：等待用户扫码付款");
        jinshiCameras.setVoiceCode(1);  //推送前端，提示音响
        CameraParam.cameraMap.put(tHandle, jinshiCameras);//推送前端
        jinshiCameras.setJcCode("临时车：等待用户扫码付款");
        updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
//        updataFirstProcess(tHandle, "待支付");//更新数据库此出口所有待支付状态更新为未支付
        clearToPayToHistory(tHandle);//修改此出口之前所有待支付记录---为null
        updataProcess(lincense, new Date(), rent, tHandle, "待支付",orderId);//更新数据库此车牌号为待支付
    }

    //外场出口无信息处理
    public void checkOutNoInfo(int camTHandle,String lincense,int tHandle,int memberDays,JinshiCameras jinshiCameras,JinshiParkingSetup jinshiParkingSetup) throws Exception{
        jinshiCameras.setCameraMemberType("2");
//        List<Member> memberList = checkServiceTypeCarByLincese(lincense, jinshiCameras.getCameraAreaCode());//查询会员信息
        List<Member> memberList = checkISMember(lincense, jinshiCameras.getJcArea());//查询会员信息
        if (memberList != null && memberList.size() > 0) {
            for (Member member1 : memberList) {
                memberDays = DateUtils.daysBetween(member1.getJoinTime(), member1.getExpirationTime());
            }
        }
        if (memberDays > 0) {
            jinshiCameras.setCameraMemberType("1");
        } else {
            jinshiCameras.setCameraMemberType("0");
        }
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, lincense), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "此卡无效"), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "无效卡"), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, ""), (byte) 0);
        GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, ""), (byte) 0);
        jinshiCameras.setCameraInboundTime(null);
        jinshiCameras.setCameraOften(null);
        jinshiCameras.setCameraRent(null);
        jinshiCameras.setPlatePayState("无入场信息");
        CameraParam.cameraMap.put(tHandle, jinshiCameras);
        jinshiCameras.setJcCode("无入场信息");
        updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
        if (1 == jinshiParkingSetup.getJpsInvalidRelease()) {
            this.openGate(camTHandle);
        }
        inserHistory(lincense, "无效卡出场 ", new Date(), tHandle);
    }
    //判断是否特种车辆进场
    public Boolean checkISSpecialVehicleOpen(int camTHandle,String lincense,int tHandle,byte lprType,byte plateColor,Date nowDate,JinshiParkingSetup jinshiParkingSetup,String picPathHttp,JinshiCameras jinshiCameras) throws Exception{
        Boolean flag = true;
        if (lprType == 1 || lprType == 11 || lprType == 2 || lprType == 3) {
            flag =  true;
        } else {
            this.openGate(camTHandle);//抬杆
            //除了正常车辆类型外，其他都归属为特种车辆免费通行
            logger.info("车牌号：" + lincense + " 在 " + jinshiCameras.getJcName() +  " 特种车辆进场");
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, lincense), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "欢迎光临"), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, "免费通行"), (byte) 0);
            String orderIdByUUId = getOrderIdByUUIdAddPlate(lincense);  //新建订单号
            jinshiCameras.setCameraLicenseId(lincense);//车牌号
            jinshiCameras.setCameraInboundTime(nowDate);//入场时间
            jinshiCameras.setCameraLincenseType(String.valueOf(lprType));//车类型
            jinshiCameras.setPicPath(picPathHttp); //图片地址
            CameraParam.cameraMap.put(tHandle, jinshiCameras);//将车辆信息推送到前端
            jinshiCameras.setCameraMemberType("0");
            //查询在场车辆表----删除在场车辆信息----插入历史表
            List<LincensePlate> lincensePlates = selectByLincensePlate(lincense);
            if (lincensePlates.size() > 0) {
                insertplateHistory(lincensePlates);
                deletePlates(lincensePlates);
            }
            updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
            dataProcessNew(lincense, plateColor, lprType, nowDate, orderIdByUUId, tHandle, jinshiCameras.getJcArea(),null,jinshiCameras.getCameraMemberType());//数据库新增一条信息
            if (jinshiParkingSetup.getJpsPayType() == 0) {//模式一
                Carin(lincense, orderIdByUUId, new Date());//通知铂链
            } else if (jinshiParkingSetup.getJpsPayType() == 1) { //模式二
                //车辆进场通知
                carInNotice(lincense,new Date(),GlobalVariable.parkId,GlobalVariable.agentId,orderIdByUUId,tHandle,Integer.parseInt(jinshiCameras.getCameraMemberType()));
            }
            flag = false;
        }
        return flag;
    }
    //判断是否特种车辆出场
    public Boolean checkISSpecialVehicleClose(int camTHandle,String lincense,int tHandle,byte lprType,JinshiParkingSetup jinshiParkingSetup,String picPathHttp,JinshiCameras jinshiCameras) throws Exception{
        Boolean flag = true;
        if (lprType == 1 || lprType == 11 || lprType == 2 || lprType == 3) {
            flag =  true;
        } else {
            this.openGate(camTHandle);//抬杆
            //除了正常车辆类型外，其他都归属为特种车辆免费通行
            logger.info("车牌号：" + lincense + " 在 " + jinshiCameras.getJcName() +  " 特种车辆出场");
            LincensePlate lp = findLincese(lincense);//根据车牌号查询最后一条入场信息
            Integer dateOften = 0;
            if (lp != null) {
                dateOften = getDateOften(new Date(), lp.getLpInboundTime());//获取停车时长
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, lincense), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "欢迎光临"), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, "免费通行"), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(02, "停车时间：" + dateOften + "分钟"), (byte) 0);
                logger.info("tHandle: " + tHandle + "出口");
                //先将车辆信息推送至前端
                jinshiCameras.setCameraOften(dateOften); //停车时长
                jinshiCameras.setCameraLicenseId(lincense); //车牌号
                jinshiCameras.setCameraInboundTime(lp.getLpInboundTime()); //进场时间
                jinshiCameras.setCameraDepartureTime(new Date()); //出场时间
                jinshiCameras.setCameraRent(0.0); //费用
                jinshiCameras.setCameraLincenseType(lp.getLpLincenseType()); //车牌类型
                jinshiCameras.setCameraMemberType("0"); //车辆类型
                jinshiCameras.setPlatePayState("特种车辆：无需付费");
                jinshiCameras.setJcCode("特种车辆：无需付费");
                jinshiCameras.setPicPath(picPathHttp);
                CameraParam.cameraMap.put(tHandle, jinshiCameras); //更新前端数据
                updateCamerasCode(GlobalVariable.parkId, jinshiCameras);  //修改摄像机状态
                logger.info("车牌号：" + lincense + " 特种车离场");
                updataProcess(lp, lincense, new Date(), 0.0, tHandle, "支付成功", "特种车出场", dateOften);//更新数据库
                if (jinshiParkingSetup.getJpsPayType() == 0) {
                    Carout(lincense, getUnixTime(lp.getLpInboundTime()), lp.getLpOrderId(), 0.0);//通知铂链
                } else if (jinshiParkingSetup.getJpsPayType() == 1) {
                    carOutNotice(lincense, GlobalVariable.parkId, 0.0, dateOften, new Date(), tHandle, "特种车出场");
                }
            } else {
                //无入场信息
                checkISOutOpen(camTHandle,lincense,tHandle,jinshiCameras, jinshiParkingSetup);
            }
            flag = false;
        }
        return flag;
    }

    // 摄像机编号确定(根据摄像机真实的返回内部设定的)
    public JinshiCameras selectInternalCameraId(int camTHandle){
        logger.info("real TO Internal 调用: -----------------camTHandle: " + camTHandle);
        String url = GlobalVariable.urlNew + "JinshiCamerasSpare/selectInternalCameraId";
        String urlCam = GlobalVariable.urlNew + "jinshiCameras/selectCameraId";
        RestTemplate restTemplate=new RestTemplate();
        JinshiCamerasSpare jinshiCamerasSpare = new JinshiCamerasSpare();
        jinshiCamerasSpare.setJcsParkId(GlobalVariable.parkId);
        jinshiCamerasSpare.setJcsSpareThandle(camTHandle);
        logger.info("selectInternalCameraId------" + "GlobalVariable.parkId: " + GlobalVariable.parkId + "camTHandle: " + camTHandle);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiCamerasSpare,String.class);
        String body = responseEntity.getBody();
        JinshiCamerasSpare spare = JSONObject.parseObject(body,JinshiCamerasSpare.class);
        logger.info("spare: ---" + JSONObject.toJSON(spare));
        String jcsCameraId = spare.getJcsCameraId();
        JinshiCameras jinshiCameras = new JinshiCameras();
        jinshiCameras.setJcParking(String.valueOf(GlobalVariable.parkId));
        jinshiCameras.setJcCamerasId(jcsCameraId);
        logger.info("selectCameraId------" + "GlobalVariable.parkId: " + GlobalVariable.parkId + "jcsCameraId: " + jcsCameraId);
        ResponseEntity<String> responseEntityCam = restTemplate.postForEntity(urlCam,jinshiCameras,String.class);
        String bodyCam = responseEntityCam.getBody();
        JinshiCameras cameras = JSONObject.parseObject(bodyCam,JinshiCameras.class);
        logger.info("cameras: ---" + JSONObject.toJSON(cameras));
        return cameras;
    }
    // 摄像机编号确定(根据内部设定的返回摄像机真实的)
    public JinshiCamerasSpare selectRealCameraId(int tHandle){
        logger.info("Internal TO real 调用: -------------------tHandle: " + tHandle);
        String url = GlobalVariable.urlNew + "JinshiCamerasSpare/selectRealCameraId";
        RestTemplate restTemplate=new RestTemplate();
        JinshiCameras jinshiCameras = selectByThandle(String.valueOf(GlobalVariable.parkId), String.valueOf(tHandle));
        JinshiCamerasSpare jinshiCamerasSpare = new JinshiCamerasSpare();
        jinshiCamerasSpare.setJcsCameraId(jinshiCameras.getJcCamerasId());
        jinshiCamerasSpare.setJcsParkId(GlobalVariable.parkId);
        logger.info("selectRealCameraId------" + "GlobalVariable.parkId: " + GlobalVariable.parkId + "jinshiCameras.getJcCamerasId(): " + jinshiCameras.getJcCamerasId());
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiCamerasSpare,String.class);
        String body = responseEntity.getBody();
        JinshiCamerasSpare spare = JSONObject.parseObject(body,JinshiCamerasSpare.class);
        logger.info("cameras: ---" + JSONObject.toJSON(spare));
        return spare;
    }

    //TODO  checkAndExecute
    public Integer checkAndExecute(String lincense,int camTHandle,byte plateColor,byte lprType,net.sdk.bean.serviceconfig.imagesnap.Data_T_PicInfo.T_PicInfo.ByReference ptPicInfo) throws Exception {
        logger.info("车牌号："+lincense+"  method:checkAndExecute start"+"  camTHandle: " + camTHandle);
        boolean b = this.connectService4SocketOpenGate(camTHandle);//请求超时处理
        if(!b){ return 5; }
        JinshiCameras jinshiCameras1 = selectInternalCameraId(camTHandle);
        int tHandle = Integer.parseInt(jinshiCameras1.getJcThandle());
        //摄像机识别车牌号后进入此方法
        JinshiCameras jinshiCameras = CameraParam.cameraMap.get(tHandle); //根据摄像机标识获取缓存内摄像机
        //根据parkId获取当前车场设置
//        JinshiParkingSetup jinshiParkingSetup = selectByParkId(GlobalVariable.parkId);
        JinshiParkingSetup jinshiParkingSetup = GlobalVariable.jinshiParkingSetup;
        Date nowDate = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat formatDay = new SimpleDateFormat("yyyy-MM-dd");
        JinshiAttention attention = findAttention(lincense);  //查询是否关注
        int memberDays = 0;
        //根据车场id和区域名称查询区域编号
        JinshiArea jinshiArea = selectByParkIdAndAreaName(GlobalVariable.parkId,jinshiCameras.getJcArea());
        String areaNumber = jinshiArea.getAreaNumber();
        JinshiLincenseGroup jinshiLincenseGroup1 = new JinshiLincenseGroup();
        if(jinshiCameras.getJcAccess().equals("进口")){// 如果是入口
            if (areaNumber.length() == 2) { //如果是外场
                //判断是否存在此路径，若不存在则创建
                isChartPathExist(GlobalVariable.picPathIs + jinshiCameras.getJcName() + "/" + formatDay.format(new Date()));
                String picPathIs = GlobalVariable.picPathIs + jinshiCameras.getJcName() + "/" + formatDay.format(new Date()) +"/" + System.currentTimeMillis() + ".jpg";
                String picPathHttp = GlobalVariable.picPathHttp + jinshiCameras.getJcName() + "/" + formatDay.format(new Date()) +"/" + System.currentTimeMillis() + ".jpg";
                //获取图片
                getFileFromBytes(ptPicInfo.ptPanoramaPicBuff.getByteArray(0, ptPicInfo.uiPanoramaPicLen), picPathIs);

                //是否是特种车辆
                if (!checkISSpecialVehicleOpen(camTHandle,lincense,tHandle,lprType,plateColor,nowDate,jinshiParkingSetup,picPathHttp,jinshiCameras)) {
                    return 6;
                }

                //判断是否黑名单车辆
                if (!checkBlackList(camTHandle,lincense, tHandle)) {
                    return 5;
                }

                //判断当前车场在场车辆数 和 车场车位数
                if (!checkCountISTrue(camTHandle,lincense, tHandle, picPathHttp, lprType, jinshiParkingSetup, jinshiCameras,jinshiArea)) {
                    return 5;
                }

                //设置黄牌车禁止入内 说明不允许黄牌车进入
                if (!checkISYellow(camTHandle,lincense, tHandle, picPathHttp, lprType, jinshiParkingSetup, jinshiCameras)) {
                    return 5;
                }

                // 是否开启常熟接口
                checkISChangshu(lincense,plateColor,jinshiParkingSetup,jinshiCameras);
                //是否关注
                Boolean isAttention = checkISAttention(attention, lincense, tHandle);

                this.openGate(camTHandle);//抬杆
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, lincense), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "欢迎光临"), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
                logger.info("tHandle: " + tHandle + "enter 进口 ");
                String orderIdByUUId = getOrderIdByUUIdAddPlate(lincense);  //新建订单号
                jinshiCameras.setCameraLicenseId(lincense);//车牌号
                jinshiCameras.setCameraInboundTime(nowDate);//入场时间
                jinshiCameras.setCameraLincenseType(String.valueOf(lprType));//车类型
                jinshiCameras.setPicPath(picPathHttp); //图片地址
                List<Member> memberList = checkISMember(lincense, jinshiCameras.getJcArea());//查询会员信息----是否全区域
                if (memberList != null && memberList.size() > 0) {//是否是会员信息
                    Member member1 = memberList.get(0);
                        memberDays = DateUtils.daysBetween(nowDate,member1.getExpirationTime());//获取会员有效时间
                        if (memberDays > 0) {
                            Integer jsNumber = member1.getLgId();
                            if (null != jsNumber) {
                                //存在车牌组
                                checkISLgid(camTHandle,lincense,jsNumber,tHandle,member1,memberDays,jinshiCameras,jinshiLincenseGroup1,jinshiArea);
                            } else {
                                //如果没有车牌组则会员车放行
                                checkMemberOpenGate(camTHandle,tHandle,member1,memberDays,jinshiCameras);
                            }
                        } else {
                            //月租过期
                            jinshiCameras.setCameraMemberType("0");
                            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "月租过期"), (byte) 0);
                            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "月租车到期"), (byte) 0);
                            GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "临时车辆"), (byte) 0);
                        }
                } else {
                    //不是会员作临时车处理
                    jinshiCameras.setCameraMemberType("0");
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(03, "临时车辆"), (byte) 0);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "临时车辆"), (byte) 0);
                }
                //查询在场车辆表----删除在场车辆信息----插入历史表
                List<LincensePlate> lincensePlates = selectByLincensePlate(lincense);
                if (lincensePlates.size() > 0) {
                    insertplateHistory(lincensePlates);
                    deletePlates(lincensePlates);
                }
                logger.info("ready start openGate");
                CameraParam.cameraMap.put(tHandle, jinshiCameras);//将车辆信息推送到前端
                updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
                dataProcessNew(lincense, plateColor, lprType, nowDate, orderIdByUUId, tHandle, jinshiCameras.getJcArea(),jinshiLincenseGroup1.getJsNumber(),jinshiCameras.getCameraMemberType());//数据库新增一条信息
                if (isAttention) {
                    //如果是关注车辆，添加信息到数据库
                    insertAttention(lincense,nowDate,jinshiCameras.getJcName(),orderIdByUUId,GlobalVariable.parkId);
                }
                if (jinshiParkingSetup.getJpsPayType() == 0) {//模式一
                    Carin(lincense, orderIdByUUId, new Date());//通知铂链
                } else if (jinshiParkingSetup.getJpsPayType() == 1) { //模式二
                    //车辆进场通知
                    carInNotice(lincense,new Date(),GlobalVariable.parkId,GlobalVariable.agentId,orderIdByUUId,tHandle,Integer.parseInt(jinshiCameras.getCameraMemberType()));
                }
            } else { //如果是内场(进口)
                //判断是否存在此路径，若不存在则创建
                isChartPathExist(GlobalVariable.picPathIs + jinshiCameras.getJcName() + "/" + formatDay.format(new Date()));
                String picPathIs = GlobalVariable.picPathIs + jinshiCameras.getJcName() + "/" + formatDay.format(new Date()) +"/" + System.currentTimeMillis() + ".jpg";
                String picPathHttp = GlobalVariable.picPathHttp + jinshiCameras.getJcName() + "/" + formatDay.format(new Date()) +"/" + System.currentTimeMillis() + ".jpg";
                //获取图片
                getFileFromBytes(ptPicInfo.ptPanoramaPicBuff.getByteArray(0, ptPicInfo.uiPanoramaPicLen), picPathIs);
                //是否关注
                checkISAttention(attention,lincense,tHandle);

                this.openGate(camTHandle);  //抬杆
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, lincense), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "欢迎光临"), (byte) 0);
                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(00, lincense), (byte) 0);
                logger.info("tHandle: " + tHandle + "enter 进口 ");
                String orderIdByUUId = getOrderIdByUUIdAddPlate(lincense);  //新建订单号
                jinshiCameras.setCameraLicenseId(lincense);//车牌号
                jinshiCameras.setCameraInboundTime(nowDate);//入场时间
                jinshiCameras.setCameraLincenseType(String.valueOf(lprType));//车类型
                jinshiCameras.setPicPath(picPathHttp); //图片地址
                //查询在场车辆表
                List<LincensePlate> lincensePlates = selectByLincensePlate(lincense);
                if (lincensePlates.size() > 0) {
                    for (LincensePlate lincensePlate : lincensePlates) {
                        //获取进场时添加的是否会员属性
                        Integer lpLgType = lincensePlate.getLpLgType();
                        checkISLgType(camTHandle,lpLgType,tHandle,jinshiCameras);
                    }
                } else {
                    //无外场入场纪录
                    jinshiCameras.setCameraMemberType("0");
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, "无外场入场纪录"), (byte) 0);
                    GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "无外场入场纪录"), (byte) 0);
                }
                    //查询在场车辆表
                if (lincensePlates.size() > 0) {
                    //内场进场，添加一条在场轨迹记录
                    LincensePlate lincensePlate = lincensePlates.get(0);
                    insertPresenceTrackRecord(lincensePlate.getLpId(), format.parse(format.format(new Date())), lincense, String.valueOf(tHandle), "内场进场");
                } else {
                    insertPresenceTrackRecord(null, format.parse(format.format(new Date())), lincense, String.valueOf(tHandle), "内场进场无外场入场记录");
                }
                CameraParam.cameraMap.put(tHandle, jinshiCameras);//将车辆信息推送到前端
                updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
            }
        } else { //如果是出口
            if (areaNumber.length() == 4) { //如果是内场
                //判断是否存在此路径，若不存在则创建
                isChartPathExist(GlobalVariable.picPathIs + jinshiCameras.getJcName() + "/" + formatDay.format(new Date()));
                String picPathIs = GlobalVariable.picPathIs + jinshiCameras.getJcName() + "/" + formatDay.format(new Date()) + "/" + System.currentTimeMillis() + ".jpg";
                String picPathHttp = GlobalVariable.picPathHttp + jinshiCameras.getJcName() + "/" + formatDay.format(new Date()) + "/" + System.currentTimeMillis() + ".jpg";
                //获取图片
                getFileFromBytes(ptPicInfo.ptPanoramaPicBuff.getByteArray(0, ptPicInfo.uiPanoramaPicLen), picPathIs);
                this.openGate(camTHandle);//抬杆
                LincensePlate lincese = new LincensePlate();
                List<LincensePlate> lincensePlates = selectByLincensePlate(lincense);
                if (lincensePlates.size() > 0) {
                    //内场出场，添加一条在场轨迹记录
                    lincese = lincensePlates.get(0);
                    insertPresenceTrackRecord(lincese.getLpId(), format.parse(format.format(new Date())), lincense, String.valueOf(tHandle), "内场出场");
                } else {
                    insertPresenceTrackRecord(null, format.parse(format.format(new Date())), lincense, String.valueOf(tHandle), "内场出场无入场记录");
                }
                //是否关注
                checkISAttention(attention,lincense,tHandle);

                logger.info("tHandle: " + tHandle + "出口");
                if (jinshiCameras.getJcAccess().equals("出口")) {
                    //先将车辆信息推送至前端
                    jinshiCameras.setCameraLicenseId(lincense); //车牌号
                    jinshiCameras.setCameraDepartureTime(new Date()); //出场时间
                    jinshiCameras.setCameraLincenseType(String.valueOf(lprType)); //车类型
                    jinshiCameras.setPicPath(picPathHttp); //图片地址
                    CameraParam.cameraMap.put(tHandle, jinshiCameras); //将车辆信息推送到前端
                    updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
                }
                if (lincese != null) {
                    //获取进场时添加的是否会员属性
                    checkISLgTypeTwo(lincese,jinshiCameras);

                    jinshiCameras.setCameraOften(0); //时长
                    jinshiCameras.setCameraLicenseId(lincese.getLpLincensePlateIdCar()); //车牌号
                    jinshiCameras.setCameraInboundTime(lincese.getLpInboundTime()); //进场时间
                    jinshiCameras.setCameraDepartureTime(new Date());//出场时间
                    jinshiCameras.setCameraRent(0.0); //停车费用
                    CameraParam.cameraMap.put(tHandle, jinshiCameras);//更新前端数据
                    updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
                    //edit 4
                    List<LincensePlate> lincensePlateList = selectByLincensePlate(lincense);
                    if (lincensePlateList.size() > 0) {
                        //内场出口判断
                        if (!checkCloseGate(camTHandle,lincense, tHandle, lincensePlateList, lincese, jinshiCameras)) {
                            return 1;
                        }
                    }
                } else {
                    logger.info(lincense + "  无入场信息");
                    //内场出场无信息处理
                    checkISInfield(camTHandle,lincense,tHandle,jinshiParkingSetup,lincensePlates,jinshiCameras);
                }
            } else {  //如果是外场(出口)
                //判断是否存在此路径，若不存在则创建
                isChartPathExist(GlobalVariable.picPathIs + jinshiCameras.getJcName() + "/" + formatDay.format(new Date()));
                String picPathIs = GlobalVariable.picPathIs + jinshiCameras.getJcName() + "/" + formatDay.format(new Date()) + "/" + System.currentTimeMillis() + ".jpg";
                String picPathHttp = GlobalVariable.picPathHttp + jinshiCameras.getJcName() + "/" + formatDay.format(new Date()) + "/" + System.currentTimeMillis() + ".jpg";
                //获取图片
                getFileFromBytes(ptPicInfo.ptPanoramaPicBuff.getByteArray(0, ptPicInfo.uiPanoramaPicLen), picPathIs);

                //判断是否是特种车辆
                if (!checkISSpecialVehicleClose(camTHandle,lincense, tHandle, lprType, jinshiParkingSetup, picPathHttp, jinshiCameras)) {
                    return 6;
                }
                //是否开启常熟接口
                checkISChangshu(lincense,plateColor,jinshiParkingSetup,jinshiCameras);
                //是否关注
                Boolean isAttention = checkISAttention(attention, lincense, tHandle);
                //查询在场轨迹信息
                List<JinshiPresenceTrack> jinshiPresenceTrackList = selectPresenceTrack(lincense, GlobalVariable.parkId);
                if (jinshiPresenceTrackList.size() > 0) {
                    JinshiPresenceTrack jinshiPresenceTrack = jinshiPresenceTrackList.get(0);
                    insertHistoryTrack(jinshiPresenceTrack.getPtLpId()); //添加到历史轨迹表中
                    deletePresenceTrack(jinshiPresenceTrack.getPtLpId());  //删除在场轨迹信息
                }

                logger.info("tHandle: " + tHandle + "出口");
                if (jinshiCameras.getJcAccess().equals("出口")) {
                    //先将车辆信息推送至前端
                    jinshiCameras.setCameraLicenseId(lincense);//车牌号
                    jinshiCameras.setCameraDepartureTime(new Date());//出场时间
                    jinshiCameras.setCameraLincenseType(String.valueOf(lprType)); //车类型
                    jinshiCameras.setPicPath(picPathHttp); //图片地址
                    CameraParam.cameraMap.put(tHandle, jinshiCameras);//更新前端数据
                    updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
                }
                LincensePlate lincese = new LincensePlate();
                try {
                    lincese = findLincese(lincense);//根据车牌号查询最后一条入场信息//未完成订单
                } catch (Exception e) {//查询BeanUtils异常
                    logger.info(lincense + "  无入场信息");
                    //外场出口无信息处理
                    checkISOutOpen(camTHandle,lincense,tHandle,jinshiCameras, jinshiParkingSetup);
                }
                if (lincese != null) {
                    Double rent = getRent(new Date(), lincese.getLpInboundTime(),lincese.getLpLincenseType());//计算费用
                    Integer dateOften = getDateOften(new Date(), lincese.getLpInboundTime());//获取停车时长
                    String orderId = getOrderIdByUUIdAddPlate(lincense);
                    //获取进场时添加的是否会员属性
                    checkISLgTypeTwo(lincese,jinshiCameras);
                    //更新前端数据
                    jinshiCameras.setCameraOften(dateOften); //停车时长
                    jinshiCameras.setCameraLicenseId(lincese.getLpLincensePlateIdCar()); //车牌号
                    jinshiCameras.setCameraInboundTime(lincese.getLpInboundTime()); //进场时间
                    jinshiCameras.setCameraDepartureTime(new Date()); //出场时间
                    jinshiCameras.setCameraRent(rent); //费用
                    CameraParam.cameraMap.put(tHandle, jinshiCameras); //更新前端数据
                    updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
                    if (isAttention) {
                        //如果是关注车辆，添加信息到数据库
                        updateAttention(nowDate,jinshiCameras.getJcName(),lincese.getLpOrderId(),rent);
                    }
                    //外场出口会员出场处理 -----会员提前处理
                    if (!checkMemberOut(camTHandle,lincese, tHandle, lincense, rent,jinshiParkingSetup, dateOften, jinshiCameras)) {
                        return 1;
                    }
                    //外场出口预付款及免费时长
                    if (!checkOutOpenGate(camTHandle,lincese, tHandle, lincense, rent,jinshiParkingSetup, dateOften, jinshiCameras)) {
                        return 1;
                    }
                    //查询是否优惠券用户
//                    if (!checkISCoupon(camTHandle,lincense, tHandle, rent, dateOften, lincese, jinshiCameras,jinshiParkingSetup)) {
//                        return 1;
//                    }
                    //修改优惠券后-----------
                    JSONObject jsonObject = checkISCouponTEST(camTHandle, lincense, tHandle, rent, dateOften, lincese, jinshiCameras, jinshiParkingSetup);
                    Double rent1 = (Double) jsonObject.get("rent");
                    boolean flag = (boolean) jsonObject.get("flag");
                    if (flag) {
                        return 1;
                    }
                    if (rent1 != null) {
                        rent = rent1;
                    }
                    //临时车
                    //外场出口临时车预处理
                    checkTemporary(camTHandle,tHandle,lincense,rent,dateOften,jinshiCameras,orderId);
                    Thread.sleep(1 * 500);
                    paySwitch.put(tHandle, false);
                    int count = 0;
                    logger.info("车牌号：" + lincense + " 在 " +jinshiCameras.getJcName() +  " 扫码支付中。。。费用: " + rent + "元");
                    while (!paySwitch.get(tHandle)) {//循环查询用户是否已经付费
                        try {
                            count++;
                            LincensePlate plate = findLincese(lincense);//根据车牌号查询最后一条入场信息
                            if (plate == null) {//手动出场收费抬杆成功以后，会执行下述操作，无需再轮询
                                logger.info("车牌号：" + lincense + " 收费抬杆，手动出场成功");
                                paySwitch.put(tHandle, true);
                                logger.info("支付成功，退出循环。");
                                break;
                            }
                            if (plate.getLpOrderState().equals("支付成功")) {//如果用户支付完成铂链会调用我方云端接口更改订单状态为支付成功
                                GlobalVariable.openGateLin.remove(lincense);
                                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, "支付成功"), (byte) 0);
                                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "操作成功"), (byte) 0);
                                this.openGate(camTHandle);//抬杆
                                jinshiCameras.setPlatePayState("临时车：扫码付款成功");
                                jinshiCameras.setCameraLicenseId(lincese.getLpLincensePlateIdCar());
                                jinshiCameras.setCameraInboundTime(lincese.getLpInboundTime());
                                jinshiCameras.setCameraDepartureTime(new Date());
                                jinshiCameras.setCameraRent(rent);
                                logger.info("车牌号：" + lincense + "出口扫码支付离场");
                                CameraParam.cameraMap.put(tHandle, jinshiCameras);//推送前端信息
                                jinshiCameras.setJcCode("临时车：扫码付款成功");
                                updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
                                if (jinshiParkingSetup.getJpsPayType() == 0) {
                                    Carout(lincense, getUnixTime(lincese.getLpInboundTime()), lincese.getLpOrderId(), rent);//通知铂链
                                    updataProcess(lincese, lincense, new Date(), rent, tHandle, "支付成功", "扫码支付出场", dateOften);//更新数据库
                                } else if (jinshiParkingSetup.getJpsPayType() == 1) {
                                    carOutNoticeForQRCode(lincense,GlobalVariable.parkId,rent,dateOften,new Date(),tHandle,"扫码支付出场",orderId);
                                    lincese.setLpOrderId(orderId);
                                    updataProcessForQRCode(lincese, lincense, new Date(), rent, tHandle, "支付成功", "扫码支付出场", dateOften);//更新数据库
                                }
                                paySwitch.put(tHandle, true);
                                logger.info("支付成功，退出循环。");
                                break;
                            } else if (plate.getLpOrderState().equals("收费放行")) { //手动点击收费放行后出场
                                GlobalVariable.openGateLin.remove(lincense);
                                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, "支付成功"), (byte) 0);
                                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "操作成功"), (byte) 0);
                                this.openGate(camTHandle);//抬杆
                                jinshiCameras.setPlatePayState("临时车：人工收费出场");
                                jinshiCameras.setCameraLicenseId(lincese.getLpLincensePlateIdCar());
                                jinshiCameras.setCameraInboundTime(lincese.getLpInboundTime());
                                jinshiCameras.setCameraDepartureTime(new Date());
                                jinshiCameras.setCameraRent(rent);
                                logger.info("车牌号：" + lincense + "人工收费出场");
                                CameraParam.cameraMap.put(tHandle, jinshiCameras);//推送前端信息
                                jinshiCameras.setJcCode("临时车：人工收费出场");
                                updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
                                updataProcessForPay(lincese, lincense, new Date(), rent, tHandle, "支付成功", "人工收费出场", dateOften);//收费放行更新数据库
                                if (jinshiParkingSetup.getJpsPayType() == 0) {
                                    Carout(lincense, getUnixTime(lincese.getLpInboundTime()), lincese.getLpOrderId(), rent);//通知铂链
                                } else if (jinshiParkingSetup.getJpsPayType() == 1) {
                                    carOutNotice(lincense,GlobalVariable.parkId,rent,dateOften,new Date(),tHandle,"人工收费出场");
                                }
                                paySwitch.put(tHandle, true);
                                logger.info("支付成功，退出循环。");
                                break;
                            } else if (1 == jinshiParkingSetup.getJpsFreeMode()) {
                                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.setTextCommand(01, "免费模式"), (byte) 0);
                                GlobalVariable.util.sendRS485Data(camTHandle, LedControl.getHexByStrAndCommand(LedControl.playVoice, "一路顺风"), (byte) 0);
                                this.openGate(camTHandle);//抬杆
                                jinshiCameras.setPlatePayState("临时车：免费模式");
                                jinshiCameras.setCameraLicenseId(lincese.getLpLincensePlateIdCar());
                                jinshiCameras.setCameraInboundTime(lincese.getLpInboundTime());
                                jinshiCameras.setCameraDepartureTime(new Date());
                                jinshiCameras.setCameraRent(rent);
                                logger.info("车牌号：" + lincense + "免费模式离场");
                                CameraParam.cameraMap.put(tHandle, jinshiCameras);//推送前端信息
                                jinshiCameras.setJcCode("临时车：免费模式");
                                updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
                                updataProcess(lincese, lincense, new Date(), rent, tHandle, "支付成功", "免费模式出场", dateOften);//更新数据库
                                if (jinshiParkingSetup.getJpsPayType() == 0) {
                                    Carout(lincense, getUnixTime(lincese.getLpInboundTime()), lincese.getLpOrderId(), 0.0);//通知铂链
                                } else if (jinshiParkingSetup.getJpsPayType() == 1) {
                                    carOutNotice(lincense,GlobalVariable.parkId,rent,dateOften,new Date(),tHandle,"免费模式出场");
                                }
                                paySwitch.put(tHandle, true);
                                logger.info("免费模式离场，退出循环。");
                                break;
                            }
                            if (count >= GlobalVariable.delayTime) {//指定时间内未付款
                                jinshiCameras.setPlatePayState("临时车：指定时间内未付款");
                                CameraParam.cameraMap.put(tHandle, jinshiCameras);//推送前端信息
                                jinshiCameras.setJcCode("临时车：指定时间内未付款");
                                updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
//                                updataFirstProcess(tHandle, "待支付");//更新数据库此出口所有待支付状态更新为未支付
                                clearToPayToHistory(tHandle);//修改此出口之前所有待支付记录---为null
                                carOutNotice(lincense,GlobalVariable.parkId,rent,dateOften,new Date(),tHandle,"指定时间内未付款");
                                paySwitch.put(tHandle, true);
                                logger.info("车牌号：" + lincense + " 在 " + jinshiCameras.getJcName() + " 指定时间内未付款，退出循环");
                                break;
                            }
                            Thread.sleep(1000); //设置暂停的时间 1 秒
                        } catch (InterruptedException e) {//异常
                            jinshiCameras.setPlatePayState("异常出场");
                            CameraParam.cameraMap.put(tHandle, jinshiCameras);//推送前端信息
                            jinshiCameras.setJcCode("异常出场");
                            updateCamerasCode(GlobalVariable.parkId,jinshiCameras);  //修改摄像机状态
                            inserHistory(lincense, "异常出场", new Date(), tHandle);
                            carOutNotice(lincense,GlobalVariable.parkId,rent,dateOften,new Date(),tHandle,"异常出场");
                            this.openGate(camTHandle);
                            logger.info("车牌号：" + lincense + "异常出场");
                            e.printStackTrace();
                        }
                    }
                } else {
                    logger.info(lincense + "  无入场信息");
                    //外场出口无信息处理
                    checkOutNoInfo(camTHandle,lincense,tHandle,memberDays,jinshiCameras,jinshiParkingSetup);
                }
            }
        }
        return 0;
    }


    public long getUnixTime(Date date) throws ParseException {
        return DateString.dateToMillisecond(date);
    }

    public void remakePhoto(Integer thandle){
        new Thread(){
            @Override
            public void run() {
                System.out.println("enter to this");
//                Data_T_DCImageSnap.T_DCImageSnap.ByReference ptImageSnap = ;
                int is = net.Net_ImageSnap(thandle, new Data_T_DCImageSnap.T_DCImageSnap.ByReference());
                System.out.println(StatusCode.getStatusCode(is, "Net_ImageSnap"));
                if(is == 0){
                    System.out.println("wait");
                    logger.info("等待回调输出：");
                }
                try {
                    Thread.sleep(3 * 1000);
                    System.out.println("over");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

//        Data_T_DCImageSnap.T_DCImageSnap.ByReference ptImageSnap = new Data_T_DCImageSnap.T_DCImageSnap.ByReference();
//        int is = net.Net_ImageSnap(HandleManage.gettHandle(net), ptImageSnap);
//        if(is == 0){
//            logger.info("start RemakePhoto finish wait res"+HandleManage.gettHandle(net));
//        }
    }

    public static Double getRent(Date endDate, Date nowDate,String plateType) {
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
        if (1 == Integer.valueOf(plateType)) {//汽油车--小型车
            if(allMin>allDayMin){
                dayCharge = Math.floor(allMin/allDayMin)*GlobalVariable.blueLincenseAllDayLimit;
            }
            if(spareMin<GlobalVariable.blueLicenseFreeTime){
                return dayCharge;
            }
            if(spareMin<=GlobalVariable.blueLicenseFirstTime){
                return dayCharge+Double.valueOf(GlobalVariable.blueLincenseFirstCharge);
            }else{
                double sumTemp = Math.floor((spareMin - GlobalVariable.blueLicenseFirstTime) / GlobalVariable.blueLincenseFollowTime)+1; //sumTemp是否能够取整数
                double nowCharge = sumTemp*GlobalVariable.blueLincenseFollowCharge + GlobalVariable.blueLincenseFirstCharge;
                if (nowCharge>GlobalVariable.blueLincenseAllDayLimit) {nowCharge=GlobalVariable.blueLincenseAllDayLimit;}   //当天费用大于20时取20
                return dayCharge+nowCharge;//之前天数的费用加上当天费用
            }
        } else if (11 == Integer.valueOf(plateType) || 2 == Integer.valueOf(plateType)) {
            //能源车(能源车 + 蓝牌能源车)
            if(allMin>allDayMin){
                dayCharge = Math.floor(allMin/allDayMin)*GlobalVariable.greenLincenseAllDayLimit;
            }
            if(spareMin<GlobalVariable.greenLicenseFreeTime){  return dayCharge;}
            if(spareMin<=GlobalVariable.greenLicenseFirstTime){
                return dayCharge+Double.valueOf(GlobalVariable.greenLincenseFirstCharge);
            }else{
                double sumTemp = Math.floor((spareMin - GlobalVariable.greenLicenseFirstTime) / GlobalVariable.greenLincenseFollowTime)+1; //sumTemp是否能够取整数
                double nowCharge = sumTemp*GlobalVariable.greenLincenseFollowCharge + GlobalVariable.greenLincenseFirstCharge;
                if (nowCharge>GlobalVariable.greenLincenseAllDayLimit) {nowCharge=GlobalVariable.greenLincenseAllDayLimit;}   //当天费用大于20时取20
                return dayCharge+nowCharge;//之前天数的费用加上当天费用
            }
        } else if (3 == Integer.valueOf(plateType)) {
            //黄牌车(中型车(黄牌) + 大型车(黄牌))
            if(allMin>allDayMin){
                dayCharge = Math.floor(allMin/allDayMin)*GlobalVariable.yellowLincenseAllDayLimit;
            }
            if(spareMin<GlobalVariable.yellowLicenseFreeTime){  return dayCharge;}
            if(spareMin<=GlobalVariable.yellowLicenseFirstTime){
                return dayCharge+Double.valueOf(GlobalVariable.yellowLincenseFirstCharge);
            }else{
                double sumTemp = Math.floor((spareMin - GlobalVariable.yellowLicenseFirstTime) / GlobalVariable.yellowLincenseFollowTime)+1; //sumTemp是否能够取整数
                double nowCharge = sumTemp*GlobalVariable.yellowLincenseFollowCharge + GlobalVariable.yellowLincenseFirstCharge;
                if (nowCharge>GlobalVariable.yellowLincenseAllDayLimit) {nowCharge=GlobalVariable.yellowLincenseAllDayLimit;}   //当天费用大于20时取20
                return dayCharge+nowCharge;//之前天数的费用加上当天费用
            }
        } else if (5 == Integer.valueOf(plateType)) {
            //特种车
            if(allMin>allDayMin){
                dayCharge = Math.floor(allMin/allDayMin)*GlobalVariable.specialLincenseAllDayLimit;
            }
            if(spareMin<GlobalVariable.specialLincenseFreeTime){  return dayCharge;}
            if(spareMin<=GlobalVariable.specialLicenseFirstTime){
                return dayCharge+Double.valueOf(GlobalVariable.specialLincenseFirstCharge);
            }else{
                double sumTemp = Math.floor((spareMin - GlobalVariable.specialLicenseFirstTime) / GlobalVariable.specialLincenseFollowTime)+1; //sumTemp是否能够取整数
                double nowCharge = sumTemp*GlobalVariable.specialLincenseFollowCharge + GlobalVariable.specialLincenseFirstCharge;
                if (nowCharge>GlobalVariable.specialLincenseAllDayLimit) {nowCharge=GlobalVariable.specialLincenseAllDayLimit;}   //当天费用大于20时取20
                return dayCharge+nowCharge;//之前天数的费用加上当天费用
            }
        }
        return 0.0;
//        if(allMin>allDayMin){
//            dayCharge = Math.floor(allMin/allDayMin)*GlobalVariable.blueLincenseAllDayLimit;
//            double spareMin = allMin%allDayMin;
//            if(spareMin<GlobalVariable.blueLicenseFreeTime){
//                return dayCharge;
//            }
//            if(spareMin<GlobalVariable.blueLicenseFirstTime){
//                return dayCharge+GlobalVariable.blueLincenseFirstCharge;
//            }
//            double v = (spareMin - GlobalVariable.blueLicenseFirstTime) / GlobalVariable.blueLincenseFollowTime+1;
//            res = v*GlobalVariable.blueLincenseFollowCharge + GlobalVariable.blueLincenseFirstCharge +dayCharge;
//            return (double) Math.round(res * 100) / 100;
//        }
//        if(allMin<GlobalVariable.blueLicenseFreeTime){
//            return 0.0;
//        }
//        if(allMin<GlobalVariable.blueLicenseFirstTime){
//            return Double.valueOf(GlobalVariable.blueLincenseFirstCharge);
//        }
//        double v = (allMin - GlobalVariable.blueLicenseFirstTime) / GlobalVariable.blueLincenseFollowTime+1;
//        res = v*GlobalVariable.blueLincenseFollowCharge + GlobalVariable.blueLincenseFirstCharge;
//        return (double) Math.round(res * 100) / 100;
    }

    // 回调
    FGetImageCB fcb = new FGetImageCB() {

        @Override
        public int invoke(
                int tHandle,
                int uiImageId,
                net.sdk.bean.serviceconfig.imagesnap.Data_T_ImageUserInfo.T_ImageUserInfo.ByReference ptImageInfo,
                net.sdk.bean.serviceconfig.imagesnap.Data_T_PicInfo.T_PicInfo.ByReference ptPicInfo) {
//车型(1大2中3小)，ITS_Tb_Vt,目前根据车牌颜色来区分大小车，蓝牌为小车，黄牌为大车
            try {
                String result = new String(ptImageInfo.szLprResult,"GBK");
                System.out.println("被动回调输出：" + "\n - 车牌颜色："
                        + ptImageInfo.ucPlateColor + "\n - 车牌类型："
                        + ptImageInfo.ucLprType + "\n - 全景图片大小："
                        //+ ptPicInfo.uiPanoramaPicLen + "\n - 车牌号：");
                        + ptPicInfo.uiPanoramaPicLen + "\n - 车牌号："
                        + result.trim()+" Handle：" + tHandle);
//                checkAndExecute(result.trim(),tHandle,ptImageInfo.ucPlateColor,ptImageInfo.ucLprType);
                paySwitch.put(tHandle,true);
                new CheckAndExecuteThread(result.trim(),tHandle,ptImageInfo.ucPlateColor,ptImageInfo.ucLprType,ptPicInfo).start();
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                openGate(tHandle);
                e.printStackTrace();
            }

            //测试保存抓拍图片到本地
           getFileFromBytes(ptPicInfo.ptPanoramaPicBuff.getByteArray(0, ptPicInfo.uiPanoramaPicLen), "C:/aaa.jpeg");
           //initView.getjLabelMaxImg().setIcon(new ImageIcon("C:/aaa.jpeg"));

//            ImageIcon imgMaxIco = new ImageIcon("C:/aaa.jpeg");
//            Image imgMax = imgMaxIco.getImage();
//            imgMax = imgMax.getScaledInstance(285, 150, Image.SCALE_DEFAULT);
//            //imageMax.setImage(imageMax.getImage().getScaledInstance(ptImageInfo.usWidth, ptImageInfo.usHeight,Image.SCALE_DEFAULT ));
//
            getFileFromBytes(ptPicInfo.ptVehiclePicBuff.getByteArray(0, ptPicInfo.uiVehiclePicLen), "C:/aaa_Vehicle.jpeg");
//            //initView.getjLabelMiniImg().setIcon(new ImageIcon("C:/aaa_Vehicle.jpeg"));
//
//            ImageIcon imgMiniIco = new ImageIcon("C:/aaa_Vehicle.jpeg");
//            Image imgMini = imgMiniIco.getImage();
//            imgMini = imgMini.getScaledInstance(210, 45, Image.SCALE_DEFAULT);

            return 0;
        }
    };

    /**
     * 将byte数据存为文件
     */
    public static File getFileFromBytes(byte[] b, String path) {
        BufferedOutputStream stream = null;
        File file = null;
        try {
            file = new File(path);
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }

    public String camerStart(String ipAddress){
//        Integer qyc = cameraInit();
//        System.out.println(cameraName+"cameraName");
//        System.out.println(StatusCode.getStatusCode(qyc, "Net_Init()"));
        addCamera(ipAddress);
        initCallBack();
//        connCamera();
        setSnapMode();
        return "SUCCESS";
    }
    public JSONObject Carout(String car_number,long in_time,String order_id,Double total) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        long epoch = getUnixTime(new Date());
        String Md5Key = MD5Utils.MD5EncodeToUpper("EC6B6BE71DBE76D6","UTF-8");
        HttpMethod method = HttpMethod.POST;
        JSONObject dataOb = new JSONObject();
        JSONObject signOb = new JSONObject();
//{"data":{"park_id":"21798","union_id":200185,"car_number":"京ABC123","in_time":1484369702,"out_time":1484369702,"order_id":"109010","total":"0.01",
// "remark":"出场信息备注","pay_type":"wallet","empty_plot":20,"auth_code": "134789575154465610"},"sign":"629BA006E0E904C0C92552D90D0AA4C7"}
        dataOb.put("park_id",GlobalVariable.parkNumber);
        dataOb.put("union_id",200474);
        dataOb.put("car_number",car_number);
        dataOb.put("in_time",in_time);
        dataOb.put("out_time",epoch);
        dataOb.put("order_id",order_id);
        dataOb.put("total",total); //金额
        dataOb.put("remark","Test");
        dataOb.put("pay_type","wallet");
        dataOb.put("empty_plot",80); //空闲车位数

//        dataOb.put("park_id","00001");
//        dataOb.put("union_id",200395);
//        dataOb.put("car_number","津MYL400");
//        dataOb.put("in_time",in_time);
//        dataOb.put("out_time",epoch);
//        dataOb.put("order_id",order_id);
//        dataOb.put("total",total);
//        dataOb.put("remark","Test");
//        dataOb.put("pay_type","wallet");
//        dataOb.put("empty_plot",80);



        String dataobstr = dataOb.toString();
        String md5encode = dataobstr+"key=EC6B6BE71DBE76D6";
        String signString = MD5Utils.MD5EncodeToUpper(md5encode,"UTF-8");
        signOb.put("data",dataOb);
        signOb.put("sign",signString);
        logger.info(signOb);
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://s.bolink.club/unionapi/neworder/updateorder",signOb,String.class);
        logger.info(responseEntity);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);
        //{"state":1,"order_id":"liu2019021901","car_number":"冀T11111T","trade_no":"","errmsg":"结算成功","total":0.01,"pay_type":"cash","pay_time":1550716675,"pay_channel":-1,"arrive_money":0,"fee":0,"account_type":1,"remark":"非电子支付"}
        return jsonObject;
    }
    public Integer Carin(String car_number,String orderIdByUUId,Date inTime) throws ParseException {
        DateString dateString = new DateString();
        long l = dateString.dateToMillisecond(inTime);
        JSONObject dataOb = new JSONObject();
        JSONObject signOb = new JSONObject();
//{"data":{"car_number":"京ONLYOU","in_time":1484369702,"order_id":"109010","empty_plot":20,"park_id":"21798","union_id":200185,"remark":"入场信息备注"},"sign":"1D3BA0D562AF49B13D905B6102C3C564"}
        dataOb.put("car_number",car_number);
        dataOb.put("in_time",l);
        dataOb.put("order_id",orderIdByUUId);
        dataOb.put("empty_plot",100);
        dataOb.put("park_id",GlobalVariable.parkNumber);
        dataOb.put("union_id",200474);
        dataOb.put("remark","test");
        dataOb.put("inpark_img","Test");

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
        logger.info(signOb);
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://s.bolink.club/unionapi/neworder/addorder",signOb,String.class);
        logger.info(responseEntity);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);
        Integer res = (Integer) jsonObject.get("state");
        //{"sign":"50BE7B01F8531AE4346B1170BE8E90DA","data":{"type":1,"channel_id":"A1","union_id":20002,"park_id":"21787","change":1}}
        //{sign=[769ACA88C96F75264FC973ECE0E0F3BF], data=[{"change":1,"union_id":200395,"park_id":"00001","type":1,"channel_id":"A1"}]}
        return res;
    }

    //车辆进场通知
    public String carInNotice(String lincense, Date inTime, Integer parkId,Integer agentId, String orderId,int thandle,Integer lgType) throws ParseException {
        String url = GlobalVariable.urlNew + "JinshiCCBPay/carInNotice";
        RestTemplate restTemplate=new RestTemplate();
        JinshiCCBPay jinshiCCBPay = new JinshiCCBPay();
        jinshiCCBPay.setCcbCarNumber(lincense);
        jinshiCCBPay.setCcbCreatTime(new Date());
        jinshiCCBPay.setCcbInTime(inTime);
        jinshiCCBPay.setCcbParkId(parkId);
        jinshiCCBPay.setCcbAgentId(agentId);
        jinshiCCBPay.setCcbOrderId(orderId);
        jinshiCCBPay.setCcbInThandle(String.valueOf(thandle));
        jinshiCCBPay.setCcbLgType(String.valueOf(lgType));
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiCCBPay,String.class);
        logger.info(lincense + "   车辆进场通知");
        String body = responseEntity.getBody();
        return body;
    }

    //车辆出场通知
    public String carOutNotice(String lincense,Integer parkId,Double rent,Integer dateOften,Date outTime,int tHandle,String payType) throws ParseException {
        RestTemplate restTemplate=new RestTemplate();
        String url = GlobalVariable.urlNew + "JinshiCCBPay/carOutNotice";
        JinshiCCBPay jinshiCCBPay = new JinshiCCBPay();
        jinshiCCBPay.setCcbCarNumber(lincense);
        jinshiCCBPay.setCcbMoney(String.valueOf(rent));
        jinshiCCBPay.setCcbParkId(parkId);
        jinshiCCBPay.setCcbOften(String.valueOf(dateOften));
        jinshiCCBPay.setCcbOutTime(outTime);
        jinshiCCBPay.setCcbOutThandle(String.valueOf(tHandle));
        jinshiCCBPay.setCcbPayType(payType);
//        jinshiCCBPay.setCcbOrderId(ccbOrderId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiCCBPay,String.class);
        logger.info(lincense + "   车辆出场通知");
        String body = responseEntity.getBody();
        return body;
    }
    //车辆出场通知---扫码支付
    public String carOutNoticeForQRCode(String lincense,Integer parkId,Double rent,Integer dateOften,Date outTime,int tHandle,String payType,String orderId) throws ParseException {
        RestTemplate restTemplate=new RestTemplate();
        String url = GlobalVariable.urlNew + "JinshiCCBPay/carOutNotice";
        JinshiCCBPay jinshiCCBPay = new JinshiCCBPay();
        jinshiCCBPay.setCcbCarNumber(lincense);
        jinshiCCBPay.setCcbMoney(String.valueOf(rent));
        jinshiCCBPay.setCcbParkId(parkId);
        jinshiCCBPay.setCcbOften(String.valueOf(dateOften));
        jinshiCCBPay.setCcbOutTime(outTime);
        jinshiCCBPay.setCcbOutThandle(String.valueOf(tHandle));
        jinshiCCBPay.setCcbPayType(payType);
        jinshiCCBPay.setCcbOrderId(orderId);
//        jinshiCCBPay.setCcbOrderId(ccbOrderId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiCCBPay,String.class);
        logger.info("临时车:  " + lincense + "   车辆出场通知");
        String body = responseEntity.getBody();
        return body;
    }
    //根据订单号查询在场表
//    public LincensePlate selectByOrderId(String orderId) throws ParseException {
//        RestTemplate restTemplate=new RestTemplate();
//        String url = GlobalVariable.urlNew + "lincensePlate/selectByOrderId";
//        LincensePlate lincensePlate = new LincensePlate();
//        lincensePlate.setLpOrderId(orderId);
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,lincensePlate,String.class);
//        String body = responseEntity.getBody();
//        LincensePlate lincensePlate1 = JSONObject.parseObject(body,LincensePlate.class);
//        return lincensePlate1;
//    }

    //修改摄像机进出场确认模式
    public String updateIsType(Integer jcId,Integer isType) {
        RestTemplate restTemplate=new RestTemplate();
        String url = GlobalVariable.urlNew + "jinshiCameras/updateIsType";
        JinshiCameras jinshiCameras2 = new JinshiCameras();
        jinshiCameras2.setJcId(jcId);
        jinshiCameras2.setJcIsType(isType);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiCameras2,String.class);
        String body = responseEntity.getBody();
        return body;
    }
//    public static String getOrderIdByUUId() {
//        int machineId = 1;//最大支持1-9个集群机器部署
//        int hashCodeV = UUID.randomUUID().toString().hashCode();
//        if(hashCodeV < 0) {//有可能是负数
//            hashCodeV = - hashCodeV;
//        }
////         0 代表前面补充0
////         4 代表长度为4
////         d 代表参数为正数型
//        return  machineId+ String.format("%015d", hashCodeV);
//    }
    public static String getOrderIdByUUId() {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        Random ne=new Random();//实例化一个random的对象ne
        int x = ne.nextInt(99999-10000+1)+10000;
        String random_order = String.valueOf(x);
        String order_id = dateName+random_order;
        return order_id;
}
    public static String getOrderIdByUUIdAddPlate(String carNumber) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        String substring = carNumber.substring(carNumber.length() - 6);
        String s = ChineseUtil.filterChinese(substring);//去除车牌号中的中文
        if (s.length() < 6) {
            StringBuilder sb = new StringBuilder();
            while (s.length() < 6) {
                sb.append("I");
                sb.append(s);
                s = sb.toString();
            }
        }
        Random ne=new Random();//实例化一个random的对象ne
        int x = ne.nextInt(99999-10000+1)+10000;
        String random_order = String.valueOf(x);
        String order_id = dateName +random_order + s;
        return order_id;
    }
//    public static synchronized String genUniqueKey(){
//        Random random = new Random();
//        Integer number = random.nextInt(900000) + 100000;
//        return System.currentTimeMillis() + String.valueOf(number);
//    }

    public static String disConnCamera(Integer tHandle){
//        stopRtspVideo();
        int dcc = net.Net_DisConnCamera(tHandle);
        logger.info("全局句柄："
                + tHandle
                + " (为-1请先连接相机)\n"
                + StatusCode.getStatusCode(dcc,
                "Net_DisConnCamera"));
        return StatusCode.getStatusCode(dcc,"Net_DisConnCamera");
    }

    public static String delCamera(Integer tHandle){
//        stopRtspVideo();
        int dc = net.Net_DelCamera(tHandle);
//        if (dc == 0) {
//            HandleManage.settHandle(-1);
//            containerControl.message("全局设备句柄已置为-1");
//        }
        logger.info(StatusCode.getStatusCode(dc,"Net_DelCamera"));
        return StatusCode.getStatusCode(dc,"Net_DelCamera");
    }

    public static void rsSetup(Integer tHandle){
        Data_T_RS485Setup.T_RS485Setup.ByReference t_rs485Setup = new Data_T_RS485Setup.T_RS485Setup.ByReference();
//        byte baudRate = (byte) 9600;  3
        t_rs485Setup.ucBaudRate = 3;     //波特率   0-7
        t_rs485Setup.ucCheckOut = 0;            //校验方式 0
        t_rs485Setup.ucFunction = 0;   //
        t_rs485Setup.ucIndex = 0;
        t_rs485Setup.ucDataBits = 8;
        t_rs485Setup.ucStopBits=1;
        int i = net.Net_RS485Setup(tHandle, t_rs485Setup);
        System.out.println(StatusCode.getStatusCode(i, "Net_RS485Setup"));
        System.out.println("波特率："+t_rs485Setup.ucBaudRate+"\n校验方式："+t_rs485Setup.ucCheckOut
                +"\nucFunction:"+t_rs485Setup.ucFunction);
    }
    public static void rsSetupA(Integer tHandle){
        Data_T_RS485Setup.T_RS485Setup.ByReference t_rs485Setup = new Data_T_RS485Setup.T_RS485Setup.ByReference();
//        byte baudRate = (byte) 9600;
        t_rs485Setup.ucBaudRate = 3;     //波特率   0-7
        t_rs485Setup.ucCheckOut = 0;            //校验方式 0
        t_rs485Setup.ucFunction = 0;        //功能
        t_rs485Setup.ucIndex = 1;
        t_rs485Setup.ucDataBits = 8;
        t_rs485Setup.ucStopBits=1;
        int i = net.Net_RS485Setup(tHandle, t_rs485Setup);
        System.out.println(StatusCode.getStatusCode(i, "Net_RS485Setup"));
        System.out.println("波特率："+t_rs485Setup.ucBaudRate+"\n校验方式："+t_rs485Setup.ucCheckOut
                +"\nucFunction:"+t_rs485Setup.ucFunction);
    }

    public static void queryLedSetup(Integer tHandle){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Data_T_LedSetup.T_LedSetup.ByReference ptLedSetup = new Data_T_LedSetup.T_LedSetup.ByReference();
                int qls = net.Net_QueryLedSetup(
                        tHandle,
                        ptLedSetup);
                System.out.println(StatusCode.getStatusCode(qls, "Net_QueryLedSetup"));
                if (qls == 0) {
                    try {
                        String msrk = new String(
                                ptLedSetup.atSubLedInBusy[0].aucContent,
                                "GB2312");
                        String xsrk = new String(
                                ptLedSetup.atSubLedInIdle[0].aucContent,
                                "GB2312");
                        String msck = new String(
                                ptLedSetup.atSubLedOutBusy[0].aucContent,
                                "GB2312");
                        String csck = new String(
                                ptLedSetup.atSubLedOutIdle[0].aucContent,
                                "GB2312");
                        System.out.println("语音使能标志："
                                + ptLedSetup.ucAudioEnable
                                + "\n忙时入口显示屏显示内容：" + msrk
                                + "\n闲时入口显示屏显示内容：" + xsrk
                                + "\n忙时出口显示屏显示内容：" + msck
                                + "\n闲时出口显示屏显示内容：" + csck);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

    public static void QueryRS485Setup(Integer tHandle){
        Data_T_RS485Setup.T_RS485Setup.ByReference ptRs485Setup = new Data_T_RS485Setup.T_RS485Setup.ByReference();
        int qrss = net.Net_QueryRS485Setup(tHandle, ptRs485Setup);
        System.out.println(StatusCode.getStatusCode(qrss, "Net_QueryRS485Setup"));
        if(qrss == 0){
            System.out.println(ptRs485Setup.ucDataBits+"数据位");
            System.out.println(ptRs485Setup.ucIndex+"ucIndex");
            System.out.println(ptRs485Setup.ucStopBits+"停止位");
            System.out.println("波特率："+ptRs485Setup.ucBaudRate+"\n校验方式："+ptRs485Setup.ucCheckOut
                    +"\nucFunction:"+ptRs485Setup.ucFunction);
        }
    }

    public static void sendRS485Data(Integer tHandle,byte[] data,byte port) throws UnsupportedEncodingException {
        Data_T_RS485Data.T_RS485Data.ByReference rs485 = new Data_T_RS485Data.T_RS485Data.ByReference();
        rs485.rs485Id = 0;  //端口号aaa
        rs485.dataLen = (short) data.length;
        rs485.data = data;
//        rs485.data = text.getBytes("Ascii");
        //serviceConfView.getBtn_Send_Rs485AndHex().setText(serviceConfView .getText_rs485().getText());
        int rs = net.Net_SendRS485Data(tHandle, rs485);
        System.out.println(StatusCode.getStatusCode(rs, data.toString()));
    }

    public static void ptLedSetup(int isAudio,Integer tHandle){
        int audio = isAudio;  //1:开启  0关闭
        Data_T_LedSetup.T_LedSetup.ByReference ptLedSetup = new Data_T_LedSetup.T_LedSetup.ByReference();
        ptLedSetup.ucAudioEnable = (byte) audio;
        int ls = net.Net_LedSetup(tHandle, ptLedSetup);
        System.out.println(StatusCode.getStatusCode(ls, "Net_LedSetup"));
    }
    private Data_T_SubLedSetup.T_SubLedSetup getT_SubLedSetup(String content) {
        Data_T_SubLedSetup.T_SubLedSetup tmp = new Data_T_SubLedSetup.T_SubLedSetup();
        tmp.ucEnable = (byte) 1;//显示文本  1显示 0不显示
        tmp.ucInterval = (byte) 4;//时间间隔
        tmp.ucLedLine = (byte) 1;//上行屏幕  下行屏幕  0 1
        tmp.ucMode = (byte) 7;//模式 0-7
        if(tmp.ucMode == 7){
            try {
//                tmp.aucContent = plateDeviceView.getText_content().getText().getBytes("GB2312");
                tmp.aucContent = content.getBytes("GB2312");
            } catch (UnsupportedEncodingException e){
                //
            }
        }

        return tmp;
    }

    public void saveLed(String content){
        int xsms = 0;  //0闲事  1忙时
        int rkck = 1;  //0入口  1出口
        Data_T_LedSetup.T_LedSetup.ByReference ptLedSetup = new Data_T_LedSetup.T_LedSetup.ByReference();
        if(xsms == 0 && rkck == 0){
            //闲时入口
            ptLedSetup.atSubLedInIdle[0] = this.getT_SubLedSetup(content);

        }else if(xsms == 0 && rkck == 1){
            //闲时出口
            ptLedSetup.atSubLedOutIdle[0] = getT_SubLedSetup(content);
        }else if(xsms == 1 && rkck == 0){
            //忙时入口
            ptLedSetup.atSubLedInBusy[0] = getT_SubLedSetup(content);
        }else{
            //忙时出口
            ptLedSetup.atSubLedOutBusy[0] = getT_SubLedSetup(content);
        }
        System.out.println("已存");
    }

    public void testRs(){
        //连接摄像机
        String ptIp = "192.168.1.105";
        GlobalVariable.util = new QianYiCameraUtil(CameraParam.net);
        Integer tHandle = GlobalVariable.util.addCamera(ptIp);
        GlobalVariable.util.connCamera(tHandle);
        //连接摄像机结束
        //设置rs485
        Data_T_RS485Setup.T_RS485Setup.ByReference t_rs485Setup = new Data_T_RS485Setup.T_RS485Setup.ByReference();
//        byte baudRate = (byte) 9600;
        t_rs485Setup.ucBaudRate = 4;     //波特率   0-7
        t_rs485Setup.ucCheckOut = 0;            //校验方式 0
        t_rs485Setup.ucFunction = 1;        //功能
        t_rs485Setup.ucIndex = 0;              //序号
        t_rs485Setup.ucDataBits = 8;        //数据位
        t_rs485Setup.ucStopBits=1;          //停止位
        int i = net.Net_RS485Setup(tHandle, t_rs485Setup);
        System.out.println(StatusCode.getStatusCode(i, "Net_RS485Setup"));
        System.out.println("波特率："+t_rs485Setup.ucBaudRate+"\n校验方式："+t_rs485Setup.ucCheckOut
                +"\nucFunction:"+t_rs485Setup.ucFunction);
        //485查询参数
        Data_T_RS485Setup.T_RS485Setup.ByReference ptRs485Setup = new Data_T_RS485Setup.T_RS485Setup.ByReference();
        int qrss = net.Net_QueryRS485Setup(tHandle, ptRs485Setup);
        System.out.println(StatusCode.getStatusCode(qrss, "Net_QueryRS485Setup"));
        if(qrss == 0){
            System.out.println(ptRs485Setup.ucDataBits+"数据位");
            System.out.println(ptRs485Setup.ucIndex+"ucIndex");
            System.out.println(ptRs485Setup.ucStopBits+"停止位");
            System.out.println("波特率："+ptRs485Setup.ucBaudRate+"\n校验方式："+ptRs485Setup.ucCheckOut
                    +"\nucFunction:"+ptRs485Setup.ucFunction);
        }
        //设置rs485结束

        //发送485信息
        String sentMessage = "aabbcc";
        Data_T_RS485Data.T_RS485Data.ByReference rs485 = new Data_T_RS485Data.T_RS485Data.ByReference();
        rs485.rs485Id = 0;  //端口号
        rs485.dataLen = (short)sentMessage.length();
        rs485.data = sentMessage.getBytes();
        //serviceConfView.getBtn_Send_Rs485AndHex().setText(serviceConfView .getText_rs485().getText());
        int rs = net.Net_SendRS485Data(tHandle, rs485);
        System.out.println(StatusCode.getStatusCode(rs, sentMessage));
    }
    public static String convertStringToHex(String str){

        char[] chars = str.toCharArray();

        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++){
            hex.append(Integer.toHexString((int)chars[i]));
        }

        return hex.toString();
    }

    public static String convertHexToString(String hex){

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for( int i=0; i<hex.length()-1; i+=2 ){

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char)decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }

    class CheckAndExecuteThread extends Thread{
        private String lincense;
        private int tHandle;
        private byte plateColor;
        private byte lprType;
        private net.sdk.bean.serviceconfig.imagesnap.Data_T_PicInfo.T_PicInfo.ByReference ptPicInfo;

        CheckAndExecuteThread(String lincense,int tHandle,byte plateColor,byte lprType,net.sdk.bean.serviceconfig.imagesnap.Data_T_PicInfo.T_PicInfo.ByReference ptPicInfo){
            this.lincense = lincense;
            this.tHandle = tHandle;
            this.plateColor = plateColor;
            this.lprType = lprType;
            this.ptPicInfo = ptPicInfo;
        }
        @Override
        public void run() {
            try {
                //test123
//                this.connectService4SocketOpenGate(tHandle);  //请求超时处理
//                this.openGate(tHandle);//抬杆
                checkAndExecute(lincense,tHandle,plateColor,lprType,ptPicInfo);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public JinshiAttention findAttention(String lincense){
        String url = GlobalVariable.urlNew + "jinshiAttention/searchAttentionUtil";
//        String url = "http://localhost:8080/carManager_V1_0_war/jinshiAttention/findAttention";
        RestTemplate restTemplate=new RestTemplate();
        JinshiAttention jinshiAttention = new JinshiAttention();
        jinshiAttention.setJcLincensePlateId(lincense);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,jinshiAttention,String.class);
        String body = responseEntity.getBody();
        JinshiAttention resAttention = JSONObject.parseObject(body, JinshiAttention.class);
        return resAttention;
    }

    public boolean connectService4SocketOpenGate(Integer camTHandle){
        String httpUrl = "http://www.jinshipark.com";
        URL url = null;
        HttpURLConnection httpConn = null;
        try {
            String address = httpUrl;
            url = new URL(address);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setDoOutput(false);
            httpConn.setDoInput(true);
            //连接建立超时时间还有读取数据超时时间
            httpConn.setConnectTimeout(3000);
            httpConn.setReadTimeout(3000);
            httpConn.connect();
            //获取状态码
//            int code = httpConn.getResponseCode();
            //读http请求响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            //关闭IO和连接
            reader.close();
            httpConn.disconnect();
            return true;
        } catch(Exception e){
            //处理超时后的操作
            this.openGate(camTHandle);
            logger.info("服务器连接超时---->抬杆:  " + camTHandle);
            return false;
        } finally{
            if(httpConn!=null){
                httpConn.disconnect();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        QianYiCameraUtil qianYiCameraUtil = new QianYiCameraUtil("test");
//        qianYiCameraUtil.CCBPAY("津A98765", QianYiCameraUtil.getOrderIdByUUId(), new Date());
//        Member member = qianYiCameraUtil.checkServiceTypeCarByLincese("苏E12345", "P3");
//        System.out.println(member);
//        String res = qianYiCameraUtil.updateOrderByPlate("1", "辽7777UJ", 3);
//        qianYiCameraUtil.selectOrderByPlate("0","辽711777UJ");
//        List<LincensePlate> lincensePlates = qianYiCameraUtil.selectByLincensePlate("津MYL400");
//        qianYiCameraUtil.insertplateHistory(lincensePlates);
//        qianYiCameraUtil.deletePlates(lincensePlates);
//        System.out.println(lincensePlates.size());
//        Member member = qianYiCameraUtil.checkServiceTypeCarByLincese("苏ED09757","P1");//查询会员信息
//        int i = DateUtils.daysBetween(member.getJoinTime(), member.getExpirationTime());
//        System.out.println(i);
//        DateFormat df = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
//        String smdata = "2019-10-25 00:00:00";
//        String bddata = "2019-11-20 00:00:00";
//        Date sm = df.parse(smdata);
//        Date bd = df.parse(bddata);
//        int i = DateUtils.daysBetween(bd, sm);
//        System.out.println(i);
//        LincensePlate lp = new LincensePlate();
//        lp.setLpLincensePlateIdCar("津MYL400");
//        lp.setLpId(1117);
//        lp.setLpCarType("testcartype");
//        lp.setLpServiceTypeCar("testservicetypecar");
//        lp.setLpInboundTime(new Date());
//        qianYiCameraUtil.updataProcess(lp,"津MYL400",new Date(),2.0,1,"zhifuchenggogn");
//        QianYiCameraUtil qianYiCameraUtil = new QianYiCameraUtil("test");
//        JinshiAttention jinshiAttention = qianYiCameraUtil.findAttention("津MYL400");
//        System.out.println(jinshiAttention);
//        CameraParam cameraParam = new CameraParam(NET.INSTANCE);
//        GlobalVariable.util = new QianYiCameraUtil(CameraParam.net);
//        GlobalVariable.util.updataFirstProcess(1,"待支付");
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        String format = df.format(date);
//        System.out.println(format);
//        long allMin = 114;
////        return day + "天" + hour + "小时" + min + "分钟";
//        if(allMin<30){
//            System.out.println(0);
//        }
//        if(allMin<(30+30)){
//            System.out.println(5);
//        }
//        double v = (allMin - 60-30) / 30;   //-30
//        if(v==0.0){
//            System.out.println(1+5);
//        }
//        double res = v*1 + 5;
//        double l = Math.round(res * 100) / 100;
//        System.out.println(l);
//        CameraParam cameraParam = new CameraParam(NET.INSTANCE);
//        GlobalVariable.util = new QianYiCameraUtil(CameraParam.net);
//        GlobalVariable.util.testRs();
        //测试显示屏
//        String ptIp2 = "192.168.3.199";
//        String ptIp2 = "192.168.1.160";
//        String testStr = "0064FFFF300C01D4C142313233343536BEAF035F";
//        String s = convertHexToString(testStr);
//        CameraParam cameraParam = new CameraParam(NET.INSTANCE);
//        GlobalVariable.util = new QianYiCameraUtil(CameraParam.net);
//        Integer tHandle = GlobalVariable.util.addCamera(ptIp2);
//        GlobalVariable.util.connCamera(tHandle);
//        GlobalVariable.util.rsSetup(tHandle);
////        GlobalVariable.util.rsSetupA(tHandle);
////        GlobalVariable.util.QueryRS485Setup(tHandle);
//        byte[] bytes = {0x00, 0x64, (byte) 0xFF, (byte) 0xFF,0x30,0x0C,0x01, (byte) 0xD4, (byte) 0xC1,0x42,0x31,0x32,0x33,0x34,0x35,0x36, (byte) 0xBE, (byte) 0xAF,0x03,0x5F};
////        GlobalVariable.util.sendRS485Data(tHandle,LedControl.getHexByStrAndCommand(LedControl.playVoice,"津MYL400"),(byte) 0);
////        GlobalVariable.util.ptLedSetup(1,tHandle);
//        GlobalVariable.util.sendRS485Data(tHandle,LedControl.setTextCommand(03,"支付成功支付成功支付成功"),(byte) 0);
//        GlobalVariable.util.saveLed("test123");
//        GlobalVariable.util.ptLedSetup(1,tHandle);
//        GlobalVariable.util.queryLedSetup(tHandle);
        //测试显示屏结束
        //测试计费
//        DateFormat df = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
//        GlobalVariable.blueLicenseFreeTime = 30;
//        GlobalVariable.blueLicenseFirstTime = 60;
//        GlobalVariable.blueLincenseFirstCharge = 5.0;
//        GlobalVariable.blueLincenseFollowTime = 30;
//        GlobalVariable.blueLincenseFollowCharge = 1.0;
//        GlobalVariable.blueLincenseAllDayLimit = 20.0;
//        String startTime = "2019-08-28 15:00:00";
//        String endTime =   "2019-08-31 19:01:00";
//        Date start = df.parse(startTime);
//        Date end = df.parse(endTime);
//        System.out.println(start);
//        System.out.println(end);
//        Double rent = getRent(end,start);
//        System.out.println(rent);
        //测试计费结束

        //摄像机添加测试
//        String ptIp = "192.168.1.108";
//        String ptIp2 = "192.168.1.105";
//        CameraParam cameraParam = new CameraParam(NET.INSTANCE);
//        GlobalVariable.util = new QianYiCameraUtil(CameraParam.net);
//        Integer integer = GlobalVariable.util.addCamera(ptIp);
//        GlobalVariable.util.connCamera(integer);
//        Integer integer1 = GlobalVariable.util.addCamera(ptIp2);
//        GlobalVariable.util.connCamera(integer1);
        //摄像机添加测试结束
//        Member mem = util.checkServiceTypeCarByLincese("津MYL400");
//        System.out.println(mem.getLincensePlateId());
//        util.checkAndExecute("津MYL400",2,aaa,bbb);
        //测试铂链开始
//        byte aaa = 1;
//        byte bbb = 2;
//        String orderIdByUUId = getOrderIdByUUId();
//        new CameraParam(NET.INSTANCE);
//        QianYiCameraUtil qianYiCameraUtil = new QianYiCameraUtil(CameraParam.net);
//        Integer carin = qianYiCameraUtil.Carin("津MYL400", orderIdByUUId,new Date());
//        qianYiCameraUtil.dataProcess("津MYL400",aaa,bbb,new Date(),orderIdByUUId,0);
//        qianYiCameraUtil.updataProcess("津MYL400",new Date(),2.00,1,"待支付");
//        LincensePlate lincese = GlobalVariable.util.findLincese("津MYL400");
//        Double rent = getRent(new Date(),lincese.getLpInboundTime());
//        long unixTime = GlobalVariable.util.getUnixTime(lincese.getLpInboundTime());
//        util.updataOrder(lincese.getLpLincensePlateIdCar(),new Date(),rent,1);
//        JSONObject carout = GlobalVariable.util.Carout("津MYL400", unixTime, lincese.getLpOrderId(), rent);
//        new CameraParam(NET.INSTANCE);
//        new QianYiCameraUtil("192.168.3.199","1", CameraParam.net).camerStart("192.168.3.199");
//        new QianYiCameraUtil("192.168.3.199","2", CameraParam.net).camerStart("192.168.3.199");
//        new CameraParam(NET.INSTANCE);
//        QianYiCameraUtil qianYiCameraUtil = new QianYiCameraUtil("192.168.3.199","1",CameraParam.net);
//        qianYiCameraUtil.addCamera("192.168.3.199");
//        new QianYiCameraUtil("192.168.3.199","1",CameraParam.net).addCamera();
//        String lincense = "津MYL400";
//        List<LincensePlate> lincese = qianYiCameraUtil.findLincese(lincense);
//        System.out.println(lincese);
//        qianYiCameraUtil.dataProcess("aaa","aaa","aaa");

//        new Thread(){
//            public void run(){
//                try {
//                    QianYiCameraUtil qianYiCameraUtil = new QianYiCameraUtil("192.168.3.199","123",CameraParam.net);
////                    Integer qyc = qianYiCameraUtil.cameraInit();
////                    System.out.println(StatusCode.getStatusCode(qyc, "Net_Init()"));
//                    qianYiCameraUtil.addCamera();
//                    qianYiCameraUtil.initCallBack();
//                    Integer integer = qianYiCameraUtil.connCamera();
//                    System.out.println(integer+"   enter this");
//                    qianYiCameraUtil.setSnapMode();
//                    System.out.println("enter to sleep");
//                    Thread.sleep(500000);
//                    System.out.println("end to sleep");
//                } catch (InterruptedException e) { }
//            }
//        }.start();     //这种内部匿名类的写法，快速生成一个线程对象，也有利于快速垃圾回收
    }
}
