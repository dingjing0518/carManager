package com.jinshi.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ThreadUtil {
    //服务端定时任务----------------
    private static Logger logger = LogManager.getLogger(ThreadUtil.class.getName());

    public void timeTwo() {
        GlobalVariable.getTime = System.currentTimeMillis();
    }
}