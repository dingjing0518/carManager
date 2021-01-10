package com.jinshi.util;

import com.alibaba.fastjson.JSONObject;
import com.jinshi.service.JinshiParkingSetupService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableScheduling
public class HolidayUtil {
    //服务端定时任务----------------
    private static Logger logger = LogManager.getLogger(HolidayUtil.class.getName());
    @Autowired
    private JinshiParkingSetupService jinshiParkingSetupService;
    public void timeFour() throws Exception{

//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
//        String date = format.format(new Date());
//        String s = DateAPI.get(date);
//        logger.info("---------------" + s + "------------");
//        SmsController smsController = new SmsController();
//        if (null != s) {
//            JSONObject parse = JSONObject.parseObject(s);
//            JSONObject type = (JSONObject) parse.get("type");
//            Integer type1 = (Integer) type.get("type");
//            jinshiParkingSetupService.updateSetupHoliday(type1); //修改今天的是否节假日属性
//            if (0 == type1) {
//                logger.info("今天是工作日");
//            } else if (1 == type1) {
//                logger.info("今天是周六日");
//            } else if (2 == type1) {
//                logger.info("今天是节假日");
//            }
//        } else {
//            logger.info("------修改节假日属性报错---------" + s);
//        }
    }
}
