package com.jinshi.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数字数组排序
 */
public class SortUtil {

    public static String sort(String s) {

//        String s = "1,3,8,6,7,16,11,10,14,17,5,4,2,12,15,13";
        String[] arr2 = s.split(",");
        int[] num = new int[arr2.length];
        for (int i = 0; i < num.length; i++) {
            num[i] = Integer.parseInt(arr2[i]);
        }
        Arrays.sort(num);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            String s1 = num[i] + ",";
            list.add(s1);
        }
        String[] array = new String[list.size()];
        if (list.size() > 0) {
            // List转换成数组
            for (int i = 0; i < list.size() + 1; i++) {
                if (i < list.size()) {
                    array[i] = String.valueOf(list.get(i));
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
        }
        String string = sb.toString();
        String substring = string.substring(0, string.length() - 1);
        return substring;
    }
}
