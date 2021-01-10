package com.jinshi.util;

import com.jinshi.CarmanagerApplication;
import com.jinshi.entity.JinshiCameras;
import com.jinshi.netCamera.utils.StatusCode;
import net.sdk.function.main.NET;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CameraParam {
    public static NET net;
    private static Logger logger = LogManager.getLogger(CarmanagerApplication.class.getName());
    public static List<JinshiCameras> cameraList;
    public static HashMap<Integer,JinshiCameras> cameraMap;
    public CameraParam(NET net) {
        this.net = net;
        int i = net.Net_Init();
        String statusCode = StatusCode.getStatusCode(i, "Net_Init()");
        logger.info(statusCode);
    }

    public static Double getRent(Date endDate, Date nowDate) {
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
}
