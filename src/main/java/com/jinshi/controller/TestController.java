package com.jinshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.ctc.wstx.util.StringUtil;
import com.jinshi.entity.JinshiCameras;
import com.jinshi.entity.JinshiParkingSetup;
import com.jinshi.entity.LincensePlate;
import com.jinshi.entity.Member;
import com.jinshi.mapper.JinshiCamerasMapper;
import com.jinshi.mapper.JinshiRoleMapper;
import com.jinshi.mapper.LincensePlateMapper;
import com.jinshi.mapper.MemberMapper;
import com.jinshi.service.*;
import com.jinshi.util.*;
//import com.sun.corba.se.spi.ior.IdentifiableFactoryFinder;
//import com.sun.jna.platform.unix.X11;
import org.apache.http.HttpResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DateUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.Line;
import java.awt.*;
import java.io.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.md5;

@Controller
@RequestMapping("/test")
@CrossOrigin
public class TestController {
    //    private static Logger logger = LogManager.getLogger(TestController.class.getName());
    private static Logger logger = Logger.getLogger(Test.class);
    @Autowired
    private QianYiCameraUtil qianYiCameraUtil;

    @Autowired
    private LincensePlateMapper lincensePlateMapper;

    @Autowired
    private JinshiRoleMapper jinshiRoleMapper;

    public static net.sdk.bean.serviceconfig.imagesnap.Data_T_PicInfo.T_PicInfo.ByReference ptPicInfo;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private JinshiParkingSetupService jinshiParkingSetupService;
    @Autowired
    private JinshiCameraService jinshiCameraService;
    @Autowired
    private JinshiCCBPayService jinshiCCBPayService;
    @Autowired
    private LincensePlateService lincensePlateService;

    @ResponseBody
    @GetMapping(value = "/openGate")
    public Integer openGate(@RequestParam("lincense") String lincense,
                            @RequestParam("tHandle") Integer tHandle) throws Exception {
        byte plateColor = 0;
        byte lprType = 1;
        return qianYiCameraUtil.checkAndExecute(lincense, tHandle, plateColor, lprType, ptPicInfo);
    }
    @ResponseBody
    @GetMapping(value = "/checkAndExecuteIS")
    public Integer checkAndExecuteIS(@RequestParam("lincense") String lincense,
                                     @RequestParam("tHandle") Integer tHandle) throws Exception {

        byte plateColor = 0;
        byte lprType = 1;
        return checkAndExecuteIS(lincense, tHandle, plateColor, lprType, ptPicInfo);
    }

    @ResponseBody
    @GetMapping(value = "/testPay")
    public void testPay(@RequestParam("subPlate") String subPlate) throws Exception {
        List<LincensePlate> lincensePlate = lincensePlateService.selectBlurryByPlate(subPlate);
        if (lincensePlate.size() > 0) {
            for (LincensePlate plate : lincensePlate) {
                plate.setLpOrderState("支付成功");
                lincensePlateService.updateByPlateByOrderId(plate);
                logger.info("车牌号：" + plate.getLpLincensePlateIdCar() + ":     订单支付成功 ===========");
                logger.info("订单支付成功的信息：" + JSONObject.toJSON(plate));
            }
            logger.info("支付回调(服务器反馈 post):     订单支付成功 ===========");
        }
    }

    @ResponseBody
    @GetMapping(value = "/testplayMedia")
    public Integer testplayMedia(@RequestParam("thandle") Integer thandle) {

        return GlobalVariable.util.playMedia(thandle);
    }

    @ResponseBody
    @GetMapping(value = "/testMember123")
    public Integer testMember123() {
        List<LincensePlate> lincensePlates = lincensePlateMapper.selectLincensePlateAll();
        for (LincensePlate lincensePlate : lincensePlates) {
            if (null == lincensePlate.getLpLgType()) {
                List<Member> member = memberMapper.selectByID(lincensePlate.getLpLincensePlateIdCar());
                if (member.size() > 0) {
                    lincensePlateMapper.updateType(lincensePlate.getLpLincensePlateIdCar());
                } else {
                    lincensePlateMapper.updateTypeZero(lincensePlate.getLpLincensePlateIdCar());
                }
            }
        }
        return 1;
    }

    public static void f() {
        final int dataSize = (int) (Runtime.getRuntime().maxMemory() * 0.6);
        System.out.println(dataSize);
        byte[] data = new byte[dataSize];
        for (int i = 0; i < 10; i++) {
            System.out.println("Please be so kind and release memory");
        }
        System.out.println(dataSize);
        byte[] data2 = new byte[dataSize];
    }
    public static void main(String[] args) {

    }

    public static String toChineseHex(String s) {
        String ss = s;
        byte[] bt = new byte[0];
        try {
            bt = ss.getBytes("GBK");
        }catch (Exception e){
            e.printStackTrace();
        }
        String s1 = "";
        for (int i = 0; i < bt.length; i++) {
            String tempStr = Integer.toHexString(bt[i]);
            if (tempStr.length() > 2)
                tempStr = tempStr.substring(tempStr.length() - 2);
            s1 = s1 + tempStr + "";
        }
        return s1.toUpperCase();
    }


    public Integer checkAndExecuteIS(String lincense,int tHandle,byte plateColor,byte lprType,net.sdk.bean.serviceconfig.imagesnap.Data_T_PicInfo.T_PicInfo.ByReference ptPicInfo) throws Exception {
        JinshiParkingSetup jinshiParkingSetup = GlobalVariable.jinshiParkingSetup;
        JinshiCameras jinshiCameras = GlobalVariable.util.selectByThandle(String.valueOf(GlobalVariable.parkId), String.valueOf(tHandle));
        String access = jinshiCameras.getJcAccess();
        if ("进口".equals(access) && jinshiParkingSetup.getJpsImportConfirm() == 1) {
            logger.info("进场确认模式开启中----------------------------------------");
            String s = GlobalVariable.util.updateIsType(jinshiCameras.getJcId(),1);//修改摄像机进出场确认模式为1
            int count = 0;
            while ("true".equals(s)) {
                JinshiCameras jinshiCameras1 = GlobalVariable.util.selectByThandle(String.valueOf(GlobalVariable.parkId), String.valueOf(tHandle));
                //jinshiCameras1.getJcIsType()  为0表示正常状态，为1表示需要确认模式，为2表示已确认
                try {
                    count++;
                    logger.info("count:  " + count);
                    logger.info(jinshiCameras1.getJcName() + "---待确认---" + jinshiCameras1.getJcIsType());
                    if (jinshiCameras1.getJcIsType() == 2) {
                        GlobalVariable.util.checkAndExecute(lincense,tHandle,plateColor,lprType,ptPicInfo);
                        GlobalVariable.util.updateIsType(jinshiCameras.getJcId(),0);//修改摄像机进出场确认模式为0
                        logger.info(jinshiCameras.getJcName() + " 修改摄像机进出场确认模式为0");
                        break;
                    }
                    if (count >= GlobalVariable.typeTime) {
                        GlobalVariable.util.updateIsType(jinshiCameras.getJcId(),0);//修改摄像机进出场确认模式为0
                        logger.info(jinshiCameras.getJcName() + "规定时间内未确认，退出循环");
                        break;
                    }
                    Thread.sleep(1000); //设置暂停的时间 1 秒
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if ("出口".equals(access) && jinshiParkingSetup.getJpsExportConfirm() == 1) {
            logger.info("出场确认模式开启中----------------------------------------");
            String s = GlobalVariable.util.updateIsType(jinshiCameras.getJcId(),1);//修改摄像机进出场确认模式为1
            int count = 0;
            while ("true".equals(s)) {
                JinshiCameras jinshiCameras1 = GlobalVariable.util.selectByThandle(String.valueOf(GlobalVariable.parkId), String.valueOf(tHandle));
                try {
                    count++;
                    logger.info("count:  " + count);
                    logger.info(jinshiCameras1.getJcName() + "---待确认---" + jinshiCameras1.getJcIsType());
                    if (jinshiCameras1.getJcIsType() == 2) {
                        GlobalVariable.util.checkAndExecute(lincense, tHandle, plateColor, lprType, ptPicInfo);
                        GlobalVariable.util.updateIsType(jinshiCameras.getJcId(),0);//修改摄像机进出场确认模式为0
                        logger.info(jinshiCameras.getJcName() + " 修改摄像机进出场确认模式为0");
                        break;
                    }
                    if (count >= GlobalVariable.typeTime) {
                        GlobalVariable.util.updateIsType(jinshiCameras.getJcId(),0);//修改摄像机进出场确认模式为0
                        logger.info(jinshiCameras.getJcName() + " 规定时间内未确认，退出循环");
                        break;
                    }
                    Thread.sleep(1000); //设置暂停的时间 1 秒
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            GlobalVariable.util.checkAndExecute(lincense,tHandle,plateColor,lprType,ptPicInfo);
        }
        return 1;
    }
}
