package com.jinshi;

import com.jinshi.init.InitProject;
import com.jinshi.util.IdWorker;
import com.jinshi.util.QianYiCameraUtil;
import com.jinshi.util.QuartzUtil;
import com.jinshi.util.ThreadUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@MapperScan("com.jinshi.mapper") //扫描的mapper
@SpringBootApplication
public class CarmanagerApplication extends SpringBootServletInitializer {

    private static Logger logger = LogManager.getLogger(CarmanagerApplication.class.getName());
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CarmanagerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(CarmanagerApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
