package com.jinshi.util;

import com.jinshi.entity.JinshiCameras;
import com.jinshi.entity.JinshiParkingSetup;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GlobalVariable {
    public static QianYiCameraUtil util;

    public static String host = "58.210.33.107";
    public static int port = 55533;
    public static String sentServiceMessage = "-1";
    public static String sentClientMessage = "-1";
    public static Map<String,String> openGateLin;
    public static Long getTime;
    public static boolean flag = true;
    public static boolean flagCamera = true;
//    public static String urlNew = "http://47.103.142.85:8081/carmanager-TEST/";
//    public static String urlNew = "http://192.168.3.215:8081/carmanager_war/";
//    public static String urlNew = "http://www.51quyd.com/";
//    public static String urlNewShop = "http://yun.51quyd.com/"; //商户平台
//    public static String urlNew = "http://www.jinshipark.com/"; //改域名时把ping的地址也改了+主流程
//    public static String urlNewShop = "http://yun.jinshipark.com/"; //商户平台
    public static String urlNew;
    public static String urlNewShop;
    public static String urlNewPC;
    public static String urlNewShopPC;
    public static String picPathIs;  //确认文件夹地址
    public static String picPathHttp; //访问地址
    public static Integer csCode;  // 常熟设置code标识
    public static Integer yellowLincenseAllow = 1; //是否允许黄牌车进入,默认为1标识可以进
    public static Integer openGateNewRealMoney = 0; //收费放行时的实收费用
    public static JinshiParkingSetup jinshiParkingSetup;
    public static Integer delayTime; //临时车出场时设置的轮询时间（单位：秒）
    public static Integer interval; //车队模式设置的抬杆间隔时间（单位：秒）
    public static Integer jcIsCarTeam; //车队模式开关标识
    public static Integer typeTime; //进出场确认模式等待的轮询时间
    public static String netPathKS; //昆山本地服务器发送请求路径
    public static String netPathWJ; //吴江本地服务器发送请求路径

    public static boolean freeMode = false;
    public static boolean openGateAllMode = false;
    public static boolean closeGateAllMode = false;
    public static boolean noEnterMode = false;
    public static boolean noOutMode = false;
    public static boolean invalidRelease = true;

    public static Integer parkId;
    public static String parkName;
    public static String parkNumber;
    public static Integer parkPlaceNumber;
    public static String parkCameraBrand;
    public static String parkPicturePath;
    public static Integer agentId;

    public static Integer blueLicenseFreeTime;
    public static Integer blueLicenseFirstTime;
    public static Double blueLincenseFirstCharge;
    public static Integer blueLincenseFollowTime;
    public static Double blueLincenseFollowCharge;
    public static Double blueLincenseAllDayLimit;
    public static Integer blueLincensePayAdvanceOutTime;

    public static Integer yellowLicenseFreeTime;
    public static Integer yellowLicenseFirstTime;
    public static Double yellowLincenseFirstCharge;
    public static Integer yellowLincenseFollowTime;
    public static Double yellowLincenseFollowCharge;
    public static Double yellowLincenseAllDayLimit;
    public static Integer yellowLincensePayAdvanceOutTime;

    public static Integer yellowLicenseFreeTimeBig;
    public static Integer yellowLicenseFirstTimeBig;
    public static Double yellowLincenseFirstChargeBig;
    public static Integer yellowLincenseFollowTimeBig;
    public static Double yellowLincenseFollowChargeBig;
    public static Double yellowLincenseAllDayLimitBig;
    public static Integer yellowLincensePayAdvanceOutTimeBig;

    public static Integer greenLicenseFreeTime;
    public static Integer greenLicenseFirstTime;
    public static Double greenLincenseFirstCharge;
    public static Integer greenLincenseFollowTime;
    public static Double greenLincenseFollowCharge;
    public static Double greenLincenseAllDayLimit;
    public static Integer greenLincensePayAdvanceOutTime;

    public static Integer specialLincenseFreeTime;
    public static Integer specialLicenseFirstTime;
    public static Double specialLincenseFirstCharge;
    public static Integer specialLincenseFollowTime;
    public static Double specialLincenseFollowCharge;
    public static Double specialLincenseAllDayLimit;
    public static Integer specialLincensePayAdvanceOutTime;

    public static String cameraName;
    public static String cameraIpAddress;
    public static String access;
    public static String thandle;

    public static String cameraALicenseId;
    public static Date cameraAInboundTime;
    public static Date cameraADepartureTime;
    public static Double cameraARent;
    public static String cameraAName;
    public static String cameraAState;
    public static String cameraAIpAddress;
    public static String accessA;
    public static String thandleA;

    public static String cameraBLicenseId;
    public static Date cameraBInboundTime;
    public static Date cameraBDepartureTime;
    public static Double cameraBRent;
    public static String cameraBName;
    public static String cameraBState;
    public static String cameraBIpAddress;
    public static String accessB;
    public static String thandleB;

    public static String cameraCLicenseId;
    public static Date cameraCInboundTime;
    public static String cameraCLincenseType;
    public static String cameraCMemberType;
    public static String cameraCName;
    public static String cameraCState;
    public static String cameraCIpAddress;
    public static String accessC;
    public static String thandleC;

    public static String cameraDLicenseId;
    public static Date cameraDInboundTime;
    public static String cameraDLincenseType;
    public static String cameraDMemberType;
    public static String cameraDName;
    public static String cameraDState;
    public static String cameraDIpAddress;
    public static String accessD;
    public static String thandleD;

    public static String cameraELicenseId;
    public static Date cameraEInboundTime;
    public static String cameraELincenseType;
    public static String cameraEMemberType;
    public static String cameraEName;
    public static String cameraEState;
    public static String cameraEIpAddress;
    public static String accessE;
    public static String thandleE;

    public static String cameraFLicenseId;
    public static Date cameraFInboundTime;
    public static String cameraFLincenseType;
    public static String cameraFMemberType;
    public static String cameraFName;
    public static String cameraFState;
    public static String cameraFIpAddress;
    public static String accessF;
    public static String thandleF;

    public static String cameraGLicenseId;
    public static Date cameraGInboundTime;
    public static String cameraGLincenseType;
    public static String cameraGMemberType;
    public static String cameraGName;
    public static String cameraGState;
    public static String cameraGIpAddress;
    public static String accessG;
    public static String thandleG;

    public static String cameraHLicenseId;
    public static Date cameraHInboundTime;
    public static String cameraHLincenseType;
    public static String cameraHMemberType;
    public static String cameraHName;
    public static String cameraHState;
    public static String cameraHIpAddress;
    public static String accessH;
    public static String thandleH;
}
