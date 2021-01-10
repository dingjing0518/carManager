package com.jinshi.util;

import java.io.UnsupportedEncodingException;

public class StringToHex{
    /**
     * 字符串转换为16进制字符串
     *
     * @param s
     * @return
     */
    public static String stringToHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }
    /**
     * 16进制字符串转换为字符串
     *
     * @param s
     * @return
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(
                        s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "gbk");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    /**
     * 16进制表示的字符串转换为字节数组
     *
     * @param s 16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] b = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return b;
    }

    /**
     * byte数组转16进制字符串
     * @param bArray
     * @return
     */
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    //将指定byte数组以16进制的形式打印到控制台
    public static void printHexString( byte[] b) {
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            System.out.print("0x"+hex.toUpperCase()+" " );
        }

    }

    public static String getDLString(Integer length){
        String hex = Integer.toHexString(length);
        if (hex.length() == 1) {
            hex = '0' + hex;
        }
        return "0x"+hex.toUpperCase();
    }
    public static String  convertStringToHex(String str){

        char[] chars = str.toCharArray();

        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++){
            hex.append(Integer.toHexString((int)chars[i]));
        }

        return hex.toString();
    }

    public static String convertHexToString(String hex){

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for( int i=0; i<hex.length()-1; i+=2 ){

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char)decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }
    //504F533838383834  POS88884
    public static void main(String[] args) throws UnsupportedEncodingException {
        String testStr = "0064FFFF300C01D4C142313233343536BEAF035F";
        String hexa = "AA 55 AA AB 01 AB AB ";
        String as = "ªUª«  ««";
        String hex = convertStringToHex(as);
//        String s = convertHexToString(hexa);

//        String s = convertHexToString(testStr);
//        System.out.println(s);
//        byte[] asciis = s.getBytes("Ascii");
//        System.out.println(asciis);
//        Byte[] bs = {0x0F, 0x1F, 0x2F, 0x3F, 0x4F, 0x5F, 0x6F};
//        System.out.println(Arrays.toString(bs));
//        String testStra = "粤B123456警";
//        String testStra = "12";
//        byte[] asciis = testStra.getBytes("GBK");
//        printHexString(asciis);
//        String s = bytesToHexString(asciis);
//        byte[] bytes = hexStringToByteArray(s);
//        System.out.println(stringToHexString("12")+"---------------------------------------");
//        System.out.println(s);
        byte[] bytes = hexStringToByteArray("12");
    }
}