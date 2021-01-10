package com.jinshi.controller;

import com.jinshi.util.DateUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ttt {
    public static void main(String[] args)  {

        String a = "2020092710211600004E9H2C1";
        String substring = a.substring(14, 19);
        String c = "";
        if (substring.equals("00004")){
             c = "3";
        }
        String substring1 = a.substring(a.length() - 6);
        System.out.println(c);


    }
}
