package com.jinshi.util;

import com.jinshi.entity.JinshiCameras;
import com.jinshi.entity.JinshiCamerasSpare;
import com.jinshi.service.JinshiCameraService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class GateUtil {
    //客户端定时任务----------------
    private static Logger logger = LogManager.getLogger(GateUtil.class.getName());

    @Autowired
    private JinshiCameraService jinshiCameraService;

    public void timeFive() {

//        List<JinshiCameras> list = jinshiCameraService.selectCamerasByParkId(String.valueOf(GlobalVariable.parkId));
//        if (list.size() > 0) {
//            for (JinshiCameras jinshiCameras : list) {
//                String jcSort = jinshiCameras.getJcSort();
//                String thandle = jinshiCameras.getJcThandle();
//                if (null != jcSort) {
//                    if ("0".equals(jcSort)) {
//                        JinshiCamerasSpare jinshiCamerasSpare = GlobalVariable.util.selectRealCameraId(Integer.parseInt(thandle));
//                        GlobalVariable.util.openGate(jinshiCamerasSpare.getJcsSpareThandle());
////                        GlobalVariable.util.openGate(Integer.valueOf(thandle));
//                        logger.info(jinshiCameras.getJcName() + "---手动抬杆");
//                    } else if ("1".equals(jcSort)){
//                        JinshiCamerasSpare jinshiCamerasSpare = GlobalVariable.util.selectRealCameraId(Integer.parseInt(thandle));
//                        GlobalVariable.util.closeGate(jinshiCamerasSpare.getJcsSpareThandle());
////                        GlobalVariable.util.closeGate(Integer.valueOf(thandle));
//                        logger.info(jinshiCameras.getJcName() + "---手动落杆");
//                    }
//                    JinshiCameras jinshiCameras1 = new JinshiCameras();
//                    jinshiCameras1.setJcThandle(jinshiCameras.getJcThandle());
//                    jinshiCameras1.setJcParking(jinshiCameras.getJcParking());
//                    jinshiCameras1.setJcSort(null);
//                    jinshiCameraService.updateCamerasSort(jinshiCameras1);
//                }
//            }
//        }
    }
}
