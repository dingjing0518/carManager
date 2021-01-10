package com.jinshi.init;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.*;
import com.jinshi.service.*;
import com.jinshi.util.*;
import net.sdk.function.main.NET;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@PropertySource("classpath:config/carManagerConfig.properties")
public class InitProject implements ApplicationRunner {

    private static Logger logger = Logger.getLogger(QianYiCameraUtil.class);

    @Autowired
    private ParkConfig parkConfig;

    @Autowired
    private JinshiParkingService jinshiParkingService;

    @Autowired
    private JinshiParkSettingService jinshiParkSettingService;
    @Autowired
    private JinshiCameraService jinshiCameraService;
    @Autowired
    private JinshiAreaService jinshiAreaService;
    @Autowired
    private JinshiParkingSetupService jinshiParkingSetupService;
    @Autowired
    private JinshiCamerasSpareService jinshiCamerasSpareService;

    @Override
    public void run(ApplicationArguments args)  throws Exception {
        logger.info("jinshiCarmanagerProject init Start--------");
        logger.info("start camert init-----------------------------");
//        new CameraParam(NET.INSTANCE);   //客户端
        logger.info("end initNET---------------------------------");
        CameraParam.cameraList = new ArrayList<JinshiCameras>();
        CameraParam.cameraMap = new HashMap<Integer,JinshiCameras>();
//        GlobalVariable.util = new QianYiCameraUtil(CameraParam.net);   //客户端
        logger.info("Parking init start");
        GlobalVariable.parkNumber = parkConfig.getParkNumber();
        GlobalVariable.parkId = parkConfig.getParkId();
        GlobalVariable.picPathIs = parkConfig.getPicPathIs();
        GlobalVariable.picPathHttp = parkConfig.getPicPathHttp();
        GlobalVariable.csCode = parkConfig.getCsCode();
        GlobalVariable.yellowLincenseAllow = parkConfig.getYellowLincenseAllow();
        GlobalVariable.urlNew = parkConfig.getUrlNew();
        GlobalVariable.urlNewShop = parkConfig.getUrlNewShop();
        GlobalVariable.openGateNewRealMoney = parkConfig.getOpenGateNewRealMoney(); //收费放行时的实收费用
        GlobalVariable.delayTime = parkConfig.getDelayTime();
        GlobalVariable.interval = parkConfig.getInterval();
        GlobalVariable.jcIsCarTeam = parkConfig.getJcIsCarTeam();
        GlobalVariable.typeTime = parkConfig.getTypeTime();
        GlobalVariable.netPathKS = parkConfig.getNetPathKS();
        GlobalVariable.netPathWJ = parkConfig.getNetPathWJ();

        GlobalVariable.freeMode = parkConfig.isFreeMode();
        GlobalVariable.openGateAllMode = parkConfig.isOpenGateAllMode();
        GlobalVariable.closeGateAllMode = parkConfig.isCloseGateAllMode();
        GlobalVariable.noEnterMode = parkConfig.isNoEnterMode();
        GlobalVariable.noOutMode = parkConfig.isNoOutMode();
        GlobalVariable.invalidRelease = parkConfig.isInvalidRelease();
        GlobalVariable.openGateLin = new HashMap<String,String>();

        GlobalVariable.getTime = System.currentTimeMillis();
        //开启客户端socket
//        new SocketClientThread().start();
        //开启服务端socket
//        new SocketServerThread().start();
        //test---------start--------------------------------------------------
        /*List<JinshiCameras> jc = jinshiCameraService.selectCameraByParkId(GlobalVariable.parkId);
        if (jc.size()>0){
            for (int i = 0; i < jc.size(); i++) {
                logger.info("------------------------------分割线-------------------------------");
                JinshiCameras jinshiCameras = jc.get(i);
                String jcIpAddress = jinshiCameras.getJcIpAddress();
//                Integer thandle = GlobalVariable.util.addCamera(jcIpAddress);
                String ii = jinshiCameras.getJcThandle();
//                System.out.println("---------" + ii + "---------");
                Integer thandle = Integer.parseInt(jinshiCameras.getJcThandle());
                logger.info("connCamera Start ----------------------");
//                GlobalVariable.util.connCamera(thandle);
                logger.info("connCamera End ----------------------");
                logger.info("setLed start ----------------------");
//                GlobalVariable.util.rsSetup(thandle);//初始化led
                logger.info("setLed end ----------------------");
//                GlobalVariable.util.initCallBack();
//                GlobalVariable.util.setSnapMode();
                JinshiCameras updateJc = jinshiCameraService.selectByIpAddress(jcIpAddress);
//                String deviceLicense = GlobalVariable.util.getDeviceLicense(thandle);
//                String deviceMac = GlobalVariable.util.getDeviceMac(thandle);
//                JSONObject deviceNetMessage = GlobalVariable.util.getDeviceNetMessage(thandle);
//                logger.info("deviceLicense-----------" + deviceLicense);
//                logger.info("deviceMac-----------" + deviceMac);
//                logger.info("deviceNetMessage-----------" + deviceNetMessage.toJSONString());
//                if ("error".equals(deviceLicense) || "error".equals(deviceMac)) {
//                    logger.info("continue-----------" + jinshiCameras.getJcName());
//                    continue;
//                }
//                String deviceMac = GlobalVariable.util.getDeviceMac(thandle);
//                JSONObject deviceNetMessage = GlobalVariable.util.getDeviceNetMessage(thandle);
//                updateJc.setJcThandle(String.valueOf(thandle));
//                updateJc.setJcSubnet((String) deviceNetMessage.get("maskAddress"));
//                updateJc.setJcGateway((String) deviceNetMessage.get("gateWayAddress"));
//                updateJc.setJcDns((String) deviceNetMessage.get("dns1"));
//                updateJc.setJcLicense(deviceLicense);
//                updateJc.setJcMac(deviceMac);
                JinshiArea jinshiArea = jinshiAreaService.selectByJcArea(jinshiCameras.getJcArea(),GlobalVariable.parkId);
                updateJc.setCameraAreaCode(jinshiArea.getAreaNumber());
                updateJc.setJcArea(jinshiArea.getAreaName());
                jinshiCameraService.updateByPrimaryKey(updateJc);
                CameraParam.cameraMap.put(Integer.valueOf(jinshiCameras.getJcThandle()),updateJc);
                JinshiCamerasSpare jinshiCamerasSpare = new JinshiCamerasSpare();
                jinshiCamerasSpare.setJcsSpareThandle(thandle);
                jinshiCamerasSpare.setJcsParkId(GlobalVariable.parkId);
                jinshiCamerasSpare.setJcsCameraId(jinshiCameras.getJcCamerasId());
                jinshiCamerasSpareService.updateCamerasSpare(jinshiCamerasSpare);
                logger.info("jinshiCameras.getJcThandle() End ------------" + jinshiCameras.getJcThandle());
                logger.info("updateCamerasSpare End ----------------------");
            }
        }*/
        //test---------end--------------------------------------------------

        //客户端-------------start----------------------------------
//        List<JinshiCameras> jc = jinshiCameraService.selectCameraByParkId(GlobalVariable.parkId);
//        if (jc.size()>0){
//            for (int i = 0; i < jc.size(); i++) {
//                logger.info("------------------------------分割线-------------------------------");
//                JinshiCameras jinshiCameras = jc.get(i);
//                String jcIpAddress = jinshiCameras.getJcIpAddress();
//                Integer thandle = GlobalVariable.util.addCamera(jcIpAddress);
//                logger.info("connCamera Start ----------------------");
//                GlobalVariable.util.connCamera(thandle);
//                logger.info("connCamera End ----------------------");
//                logger.info("setLed start ----------------------");
////                GlobalVariable.util.rsSetup(thandle);//初始化led
//                logger.info("setLed end ----------------------");
////                GlobalVariable.util.initCallBack();
////                GlobalVariable.util.setSnapMode();
//                JinshiCameras updateJc = jinshiCameraService.selectByIpAddress(jcIpAddress);
//                String deviceLicense = GlobalVariable.util.getDeviceLicense(thandle);
//                String deviceMac = GlobalVariable.util.getDeviceMac(thandle);
//                JSONObject deviceNetMessage = GlobalVariable.util.getDeviceNetMessage(thandle);
//                logger.info("deviceLicense-----------" + deviceLicense);
//                logger.info("deviceMac-----------" + deviceMac);
//                logger.info("deviceNetMessage-----------" + deviceNetMessage.toJSONString());
//                if ("error".equals(deviceLicense) || "error".equals(deviceMac)) {
//                    logger.info("continue-----------" + jinshiCameras.getJcName());
//                    continue;
//                }
//                updateJc.setJcThandle(jinshiCameras.getJcThandle());
//                updateJc.setJcSubnet((String) deviceNetMessage.get("maskAddress"));
//                updateJc.setJcGateway((String) deviceNetMessage.get("gateWayAddress"));
//                updateJc.setJcDns((String) deviceNetMessage.get("dns1"));
//                updateJc.setJcLicense(deviceLicense);
//                updateJc.setJcMac(deviceMac);
//                JinshiArea jinshiArea = jinshiAreaService.selectByJcArea(jinshiCameras.getJcArea(),GlobalVariable.parkId);
//                updateJc.setCameraAreaCode(jinshiArea.getAreaNumber());
//                updateJc.setJcArea(jinshiArea.getAreaName());
//                jinshiCameraService.updateByPrimaryKey(updateJc);
//                CameraParam.cameraMap.put(Integer.valueOf(jinshiCameras.getJcThandle()),updateJc);
//                JinshiCameras jinshiCameras1 = CameraParam.cameraMap.get(Integer.valueOf(jinshiCameras.getJcThandle()));
//                logger.info("jinshiCameras End ------------" + JSONObject.toJSON(jinshiCameras1));
//                JinshiCamerasSpare jinshiCamerasSpare = new JinshiCamerasSpare();
//                jinshiCamerasSpare.setJcsSpareThandle(thandle);
//                jinshiCamerasSpare.setJcsParkId(GlobalVariable.parkId);
//                jinshiCamerasSpare.setJcsCameraId(jinshiCameras.getJcCamerasId());
//                jinshiCamerasSpareService.updateCamerasSpare(jinshiCamerasSpare);
//                logger.info("jinshiCameras.getJcThandle() End ------------" + jinshiCameras.getJcThandle());
//                logger.info("updateCamerasSpare End ------------" + thandle);
//            }
//        }
        //客户端-------------end----------------------------------

        JinshiParking jinshiParking = jinshiParkingService.selectByJpId(GlobalVariable.parkId);
        if(jinshiParking!=null){
            logger.info("获取停车场信息---------------------------");
            GlobalVariable.parkId = jinshiParking.getJpId();
            GlobalVariable.parkName = jinshiParking.getJpName();
            GlobalVariable.parkPlaceNumber = Integer.parseInt(jinshiParking.getJpPlaceNumber());
            GlobalVariable.parkCameraBrand = jinshiParking.getJpCameraBrand();
            GlobalVariable.parkPicturePath = jinshiParking.getJpPicturePath();
            GlobalVariable.agentId = jinshiParking.getJpAgentId();
        }else{
            logger.info("无停车场信息---------------------------");
        }
        logger.info("Parking init finish " +GlobalVariable.parkName+GlobalVariable.parkPlaceNumber+GlobalVariable.parkCameraBrand+GlobalVariable.parkPicturePath);
        logger.info("charge setting init start ----");
        GlobalVariable.jinshiParkingSetup = jinshiParkingSetupService.selectParkSetup(GlobalVariable.parkId);
        List<JinshiParkSetting> jinshiParkSetting = jinshiParkSettingService.selectByParkKey(GlobalVariable.parkId);
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
        logger.info("charge setting inti end ------");
        logger.info("------------------------------------------------------------------------");
        logger.info("------------------------------------------------------------------------");
        logger.info("------------------------------------------------------------------------");
        logger.info("------------------------------系统启动完毕-----------------------------");
        logger.info("------------------------------------------------------------------------");
        logger.info("------------------------------------------------------------------------");
        logger.info("------------------------------------------------------------------------");
    }
}
