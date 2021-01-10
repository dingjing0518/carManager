package com.jinshi.util;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiCameras;
import com.jinshi.service.JinshiCameraService;
import com.jinshi.wxPay.WeChatTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class PingUtil {
    //客户端定时任务----------------
    private static Logger logger = LogManager.getLogger(PingUtil.class.getName());

    @Autowired
    private JinshiCameraService jinshiCameraService;

    @Autowired
    private WeChatTemplate weChatTemplate;

    @Autowired
    private SmsController smsController;

    public void timeThree() throws Exception{
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
//        List<JinshiCameras> list = jinshiCameraService.selectCamerasByParkId(String.valueOf(GlobalVariable.parkId));
//        if (list.size() > 0) {
//            for (JinshiCameras jinshiCameras : list) {
//                String parking = jinshiCameras.getJcParking();
//                String parkName = null;
//                if ("1".equals(parking)) {
//                    parkName = "森林公园P1P3停车场 ";
//                }else if ("3".equals(parking)){
//                    parkName = "吴江停车场 ";
//                }
//                if (!"error".equals(jinshiCameras.getJcIpAddress())) {
//                    int timeOut = 3000;
//                    boolean status = InetAddress.getByName(jinshiCameras.getJcIpAddress()).isReachable(timeOut);
//                    if (!status) {
//                        logger.info(jinshiCameras.getJcName() + "---摄像机未连接");
//                        if (GlobalVariable.flagCamera) {
//                            smsController.smsThandle("18994388793", parkName, jinshiCameras.getJcName(),sdf.format(new Date()));
//                            smsController.smsThandle("18622139742", parkName, jinshiCameras.getJcName(),sdf.format(new Date()));
//                            weChatTemplate.sendTemp("o5u721cYsm8PsMnnYrt6_xXsfvgc",jinshiCameras.getJcParking(),jinshiCameras.getJcName());
//                            weChatTemplate.sendTemp("o5u721SwUS5TvbluLCJmNjQPoA3A",jinshiCameras.getJcParking(),jinshiCameras.getJcName());
//                            weChatTemplate.sendTemp("o5u721Zaw7fLW17peoyLC5n_I7eQ",jinshiCameras.getJcParking(),jinshiCameras.getJcName());
//                            logger.info(parkName + jinshiCameras.getJcName() + "---摄像机异常车场发送微信信息成功");
//                        }
//                            GlobalVariable.flagCamera = false;
//                        }
//                } else {
//                    logger.info(jinshiCameras.getJcName() + "---摄像机异常");
//                    if (GlobalVariable.flagCamera) {
//                        smsController.smsThandle("18994388793", parkName, jinshiCameras.getJcName(),sdf.format(new Date()));
//                        smsController.smsThandle("18622139742", parkName, jinshiCameras.getJcName(),sdf.format(new Date()));
//                        weChatTemplate.sendTemp("o5u721cYsm8PsMnnYrt6_xXsfvgc",jinshiCameras.getJcParking(),jinshiCameras.getJcName());
//                        weChatTemplate.sendTemp("o5u721SwUS5TvbluLCJmNjQPoA3A",jinshiCameras.getJcParking(),jinshiCameras.getJcName());
//                        weChatTemplate.sendTemp("o5u721Zaw7fLW17peoyLC5n_I7eQ",jinshiCameras.getJcParking(),jinshiCameras.getJcName());
//                        logger.info(parkName + jinshiCameras.getJcName() + "---摄像机异常车场发送微信信息成功");
//                        GlobalVariable.flagCamera = false;
//                    }
//                }
//            }
//        }
    }
}
