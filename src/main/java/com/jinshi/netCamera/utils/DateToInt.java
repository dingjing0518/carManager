package com.jinshi.netCamera.utils;


public class DateToInt {
	
	
	public static int toDate(int year, int month, int day) {
		int tmp = 0;
		return tmp | (year << 16) | (month << 8) | day;
	}
	
	public static int toTime(int hour, int minutes, int second){
		int tmp = 0;
		return tmp | (hour << 24) | (minutes << 16) | (second << 8);
	}
}
