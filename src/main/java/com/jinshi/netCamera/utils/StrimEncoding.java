package com.jinshi.netCamera.utils;

public class StrimEncoding {
	
	public static byte[] getRealBytes(byte[] react,int realLength){
		byte[] tmp = new byte[realLength];
		int reactLength = react.length;
		for (int i = 0; i < reactLength; i++) {
				tmp[i] = react[i];
			}
		if(reactLength < realLength){
			tmp[react.length] = '\n';
		}
		tmp[realLength - 1] = '\n';
		
		return tmp;
	}
}
