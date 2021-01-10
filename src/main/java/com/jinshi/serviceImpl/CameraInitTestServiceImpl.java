package com.jinshi.serviceImpl;

import com.jinshi.service.CameraInitTestService;
import com.jinshi.util.CameraParam;
import com.jinshi.util.QianYiCameraUtil;
import net.sdk.function.main.NET;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CameraInitTestServiceImpl implements CameraInitTestService {

    private static Logger logger = LogManager.getLogger(CameraInitTestServiceImpl.class.getName());

    @Override
    public void netInit(){
        logger.info("初始化开始-----------------------------------");
        new CameraParam(NET.INSTANCE);

    }

//    @Override
//    public void openGate(){
//
//    }

    @Override
    public void cameraInitA(){
        Thread cameraa = new Thread(new Runnable(){//cameraInitA
            @Override
            public void run() {
                new QianYiCameraUtil("192.168.1.105", CameraParam.net).camerStart("192.168.1.105");//   thandle :0  南出口2
                new QianYiCameraUtil("192.168.1.106", CameraParam.net).camerStart("192.168.1.106");//   thandle :1  南入口
                new QianYiCameraUtil("192.168.1.107", CameraParam.net).camerStart("192.168.1.107");//   thandle :2  南出口1
                new QianYiCameraUtil("192.168.1.108", CameraParam.net).camerStart("192.168.1.108");//   thandle :3  北入口
            }
        });
        cameraa.start();
    }

    @Override
    public void cameraInitB(){
//        Thread camerab = new Thread(new Runnable(){
//            @Override
//            public void run() {
                QianYiCameraUtil ipb = new QianYiCameraUtil("192.168.3.199",CameraParam.net);
        Integer integer = ipb.addCamera("192.168.3.199");
        ipb.connCamera(integer);
//            }
//        });
//        camerab.start();
    }

    @Override
    public void cameraInitC(){
        Thread camerac = new Thread(new Runnable(){
            @Override
            public void run() {
                QianYiCameraUtil ipc = new QianYiCameraUtil("192.168.1.107",CameraParam.net);
                ipc.camerStart("192.168.1.107");
            }
        });
        camerac.start();
    }

    @Override
    public void cameraInitD(){
        Thread camerad = new Thread(new Runnable(){
            @Override
            public void run() {
                QianYiCameraUtil ipd = new QianYiCameraUtil("192.168.1.108",CameraParam.net);
                ipd.camerStart("192.168.1.108");
            }
        });
        camerad.start();
    }


    @Override
    public void cameraInit(){
//        Thread cameraf = new Thread(new Runnable(){
//            @Override
//            public void run() {
//                QianYiCameraUtil ipf = new QianYiCameraUtil("192.168.3.199","2");
//                ipf.camerStart();
////                ipf.dataProcess("1","2","2",new Date(),"1");
//                try {
//                    ipf.checkAndExecute("津MYL40011");
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

//        Thread cameras = new Thread(new Runnable(){
//            @Override
//            public void run() {
//                QianYiCameraUtil ips = new QianYiCameraUtil("192.168.3.199","2");
//                ips.camerStart();
//            }
//        });

//        cameraf.start();
//        cameras.start();

        Thread cameraa = new Thread(new Runnable(){
            @Override
            public void run() {
                QianYiCameraUtil ipa = new QianYiCameraUtil("192.168.1.105",CameraParam.net);
                ipa.camerStart("192.168.1.105");
            }
        });

        Thread camerab = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                QianYiCameraUtil ipb = new QianYiCameraUtil("192.168.1.106",CameraParam.net);
                ipb.camerStart("192.168.1.106");
            }
        });

        Thread camerac = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                QianYiCameraUtil ipc = new QianYiCameraUtil("192.168.1.107",CameraParam.net);
                ipc.camerStart("192.168.1.107");
            }
        });

        Thread camerad = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                QianYiCameraUtil ipd = new QianYiCameraUtil("192.168.1.108",CameraParam.net);
                ipd.camerStart("192.168.1.108");
            }
        });

        cameraa.start();
        camerab.start();
        camerac.start();
        camerad.start();
    }
}
