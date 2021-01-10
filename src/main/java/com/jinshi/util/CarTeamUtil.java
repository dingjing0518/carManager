package com.jinshi.util;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.entity.JinshiCameras;
import com.jinshi.entity.JinshiCamerasSpare;
import com.jinshi.entity.JinshiParkingSetup;
import com.jinshi.service.JinshiCameraService;
import com.jinshi.service.JinshiParkingSetupService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

@Component
@EnableScheduling
public class CarTeamUtil {

    //客户端定时任务-------车队模式
    private static Logger logger = Logger.getLogger(QianYiCameraUtil.class);

    @Autowired
    private JinshiCameraService jinshiCameraService;

    public void timeSix() throws Exception {
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
//        String jcStartTime = null;
//        String jcEndTime = null;
//        List<JinshiCameras> list = jinshiCameraService.selectByParkIdDesc(String.valueOf(GlobalVariable.parkId));
//        for (JinshiCameras jinshiCameras : list) {
//            Integer jcIsCarTeam = jinshiCameras.getJcIsCarTeam();
//            Date jcStartTime1 = jinshiCameras.getJcStartTime();
//            Date jcEndTime1 = jinshiCameras.getJcEndTime();
//            if (jcStartTime1 != null && jcEndTime1 != null) {
//                jcStartTime = formatTime.format(jcStartTime1);
//                jcEndTime = formatTime.format(jcEndTime1);
//                Date date = new Date();
//                if (jcIsCarTeam != null && jcIsCarTeam == 1) {
//                    JSONObject jsonObject = DateUtils.subtimeToDate(jcStartTime,jcEndTime);//时分秒时间
//                    String jpsStartTime = (String) jsonObject.get("jpsStartTime");
//                    String jpsEndTime = (String) jsonObject.get("jpsEndTime");
//                    String jpsTHandle = jinshiCameras.getJcThandle();
//                    Integer jpsInterval = GlobalVariable.interval;
//                    try {
////                        date = format.parse(formatTime.format(date));
//                        Date date1 = format.parse(jpsStartTime);
//                        Date date2 = format.parse(jpsEndTime);
//                        boolean after = date.after(date1);
//                        boolean before = date.before(date2);
////                        JinshiCameras jinshiCameras1 = jinshiCameraService.selectByParkIdAndJcName(GlobalVariable.parkId,jinshiCameras.getJcName());
////                        jcIsCarTeam = jinshiCameras1.getJcIsCarTeam();
//                        while (after && before && GlobalVariable.jcIsCarTeam==1) {
//                            try {
//                                logger.info("车队模式开启中。。。--------------");
//                                Thread.sleep(jpsInterval * 1000); //设置暂停的时间
//                                JinshiCamerasSpare jinshiCamerasSpare = GlobalVariable.util.selectRealCameraId(Integer.valueOf(jpsTHandle));
//                                GlobalVariable.util.openGate(jinshiCamerasSpare.getJcsSpareThandle());
////                                GlobalVariable.util.openGate(Integer.valueOf(jpsTHandle));
//                                date = format.parse(format.format(new Date()));
//                                boolean beforeEnd = date.before(date2);
//                                if (!beforeEnd) {
//                                    JinshiCamerasSpare jinshiCamerasSpare1 = GlobalVariable.util.selectRealCameraId(Integer.valueOf(jpsTHandle));
//                                    GlobalVariable.util.closeGate(jinshiCamerasSpare1.getJcsSpareThandle());
////                                    GlobalVariable.util.closeGate(Integer.valueOf(jpsTHandle));
//                                    //将车队模式修改为0---不开启
//    //                                jinshiCameras.setJcIsCarTeam(0);
//    //                                jinshiCameraService.updateOpenMode(jinshiCameras);
//                                    logger.info("车队模式结束------------------");
//                                    break;
//                                }
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        while (after && before && GlobalVariable.jcIsCarTeam==0) {
//                            JinshiCamerasSpare jinshiCamerasSpare1 = GlobalVariable.util.selectRealCameraId(Integer.valueOf(jpsTHandle));
//                            GlobalVariable.util.closeGate(jinshiCamerasSpare1.getJcsSpareThandle());
////                            GlobalVariable.util.closeGate(Integer.valueOf(jpsTHandle));
//                            logger.info("车队模式结束------------------");
//                            break;
//                        }
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
    }
}
