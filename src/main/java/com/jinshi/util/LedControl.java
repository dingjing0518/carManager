package com.jinshi.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class LedControl {

    public static final String playVoice = "0064FFFF30";
    public static final String setText = "0064FFFF62";

    public static List<String> printHexString(byte[] param) {
        List<String> res = new ArrayList<String>();
        for (int i = 0; i < param.length; i++) {
            String hex = Integer.toHexString(param[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            res.add("0x"+hex.toUpperCase());
        }
        return res;
    }

    public static byte[] strTOHex(String text) throws UnsupportedEncodingException {
//        byte[] bytes = {0x00, 0x64, (byte) 0xFF, (byte) 0xFF,0x30,0x0C,0x01, (byte) 0xD4, (byte) 0xC1,0x42,0x31,0x32,0x33,0x34,0x35,0x36, (byte) 0xBE, (byte) 0xAF,0x03,0x5F};
        byte[] gbks = text.getBytes("GBK");
        List<String> strings = printHexString(gbks);
        return null;
    }
    public static byte[] setTextCommand(Integer twid,String text) throws UnsupportedEncodingException {
        String tc = "";
        if(twid==0){
            tc = "FF000000";
        }else if(twid==1){
            tc = "00FF0000";
        }else if(twid==2){
            tc = "FF000000";
        }else if(twid==3){
            tc = "00FF0000";
        }
        byte[] gbks = text.getBytes("GBK");
        Integer gbksLengthp1 = gbks.length + 19;
        String dlString2 = getDLString(gbks.length);
        String dlString = getDLString(gbksLengthp1);
        String setText = LedControl.setText;
        setText = setText+dlString;
        String dlString1 = getDLString(twid);
        setText = setText + dlString1 +"150100020000030A"+tc+"00000000"+dlString2+"00";
        for (int i = 0; i < gbks.length; i++) {
            String hex = Integer.toHexString(gbks[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            setText = setText + hex.toUpperCase();
        }
        byte[] bytes = CRC16MM.HexString2Buf(setText);
        byte[] sendBuf = CRC16MM.getSendBuf(bytes);
        String bytes1 = CRC16MM.getBufHexStr(sendBuf);
        System.out.println(bytes1);
        return sendBuf;
    }

    public static byte[] getHexByStrAndCommand(String command,String text) throws UnsupportedEncodingException {
        byte[] gbks = text.getBytes("GBK");
        Integer gbksLengthp1 = gbks.length + 1;
        String dlString = getDLString(gbksLengthp1);
        command = command+dlString+"01";
        for (int i = 0; i < gbks.length; i++) {
            String hex = Integer.toHexString(gbks[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            command = command + hex.toUpperCase();
        }
        byte[] bytes = CRC16MM.HexString2Buf(command);
        byte[] sendBuf = CRC16MM.getSendBuf(bytes);
//        String bytes1 = CRC16MM.getBufHexStr(sendBuf);
//        System.out.println(bytes1);
        return sendBuf;
    }

    public static String getDLString(Integer length){
        String hex = Integer.toHexString(length);
        if (hex.length() == 1) {
            hex = '0' + hex;
        }
        return hex.toUpperCase();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
//        getHexByStrAndCommand(LedControl.playVoice,"粤B123456警");
        byte[] tests = setTextCommand(01, "test");
    }

}
