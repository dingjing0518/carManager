package com.jinshi.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class QuartzUtil {
    //客户端定时任务----------------
    private static Logger logger = LogManager.getLogger(QuartzUtil.class.getName());

    public void time() throws Exception{
//        GlobalVariable.util.sendPostToNet();
    }
}