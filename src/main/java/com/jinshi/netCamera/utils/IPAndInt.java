package com.jinshi.netCamera.utils;

public class IPAndInt {
	 //int转ip(字节数组)  
    public static String intToIp(int i) {     
    	short[] result = new short[4];     
        result[0] = (short)((i >> 24) & 0xFF);  
        result[1] = (short)((i >> 16) & 0xFF);  
        result[2] = (short)((i >> 8) & 0xFF);   
        result[3] = (short)(i & 0xFF);  
        
        StringBuffer sbf = new StringBuffer();
        for(short s : result){
        	sbf.append(s);
        	sbf.append(".");
        }
        sbf.deleteCharAt(sbf.lastIndexOf("."));
        return sbf.toString();
    }  
    
    //ip(字节数组)转int  
    public static int ipToInt(String ip) {  
        int value = 0;
        String[] ipArr = ip.split("\\.");
        for (int i = 0; i < 4; i++) {  
        	
           value |= Integer.parseInt(ipArr[i]);  
           if ( i < 3 ) {  
             value = value << 8;  
           }  
        }  
        return value;  
    }  
}
