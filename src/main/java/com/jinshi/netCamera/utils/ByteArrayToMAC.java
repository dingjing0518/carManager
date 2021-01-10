package com.jinshi.netCamera.utils;

public class ByteArrayToMAC {
	
	//byte数组转Mac格式字符串
	public static String getMacAddress(byte[] address){
		if(address == null){
			return new String();
		}
		StringBuffer sbf = new StringBuffer();
		for(byte b : address){
			int bb = b;
			bb = bb & 0xff;
			sbf.append(Integer.toHexString(bb));
			sbf.append("-");
		}	
		sbf.deleteCharAt((sbf.lastIndexOf("-")));
		return sbf.toString();
	}
}
