package com.jinshi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Test {
    public static void main(String[] args) {
//        readProperty1();
    }

    //方法一
    private static void readProperty1() {
        Properties properties = new Properties();
        InputStream inputStream = Object.class.getResourceAsStream("/config/carManagerConfig.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(properties.get("parkNumber"));
        System.out.println(properties.get("parkId"));
    }

}
