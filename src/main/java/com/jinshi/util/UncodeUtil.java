package com.jinshi.util;

import java.io.*;

public class UncodeUtil {
    static Integer outIng;
    static String unicodeStr;
    static boolean isSend = true;

    public static void main(String[] args) {
        try{
            InputStreamReader reader = new InputStreamReader(System.in);
            BufferedReader input = new BufferedReader(reader);
            System.out.println("Enter your number:(input end,the app exit) ");
            String name = input.readLine();
            if(name !="end") {
                //方法一:将asceII字符串转成unicode字符串
                //String unicodeTmp=toUnicode(name);
                //System.out.println("unicodeTmp="+unicodeTmp);
                //方法二:将asceII字符串转成unicode字符串
                //String unicodeTmp = new String(name.getBytes("iso-8859-1"),"gb2312");
                //System.out.println("unicodeTmp="+unicodeTmp);
                //将字符串转化成十六进制unicode码
                String unicodeTmp=toFormatUnicode(name);
                System.out.println("unicodeTmp="+unicodeTmp);
            }
        }catch(Exception ex){
            System.out.println("Exception :"+ex);
        }
    }
    public static String toStr(String unicode) {
        String strRet = "";
        return strRet;
    }

    public static String toUnicode(String strText) throws UnsupportedEncodingException {
        char c;
        String strRet = "";
        int intAsc;
        String strHex;
        for (int i = 0; i < strText.length(); i++) {
            c = strText.charAt(i);
            intAsc = (int) c;
            if (intAsc > 128) {
                strHex = Integer.toHexString(intAsc);
                strRet += "//u" + strHex;
            } else {
                strRet = strRet + c;
            }
        }
        return strRet;
    }
    public static String toFormatUnicode(String strText) throws UnsupportedEncodingException {
        char c;
        String strRet = "";
        int intAsc;
        String strHex;
        for (int i = 0; i < strText.length(); i++) {
            c = strText.charAt(i);
            intAsc = (int) c;
            strHex = Integer.toHexString(intAsc);
            if (intAsc > 128) {
                strRet += "//u" + strHex;
            }else{
                strRet += "//u00" + strHex;
            }
        }
        return strRet;
    }

    public String readUnicodeFile(String filename) {
        StringBuffer buffer = null;
        InputStream is = null;
        InputStreamReader isr = null;
        try {
            Class c = this.getClass();
            is = c.getResourceAsStream(filename);
            if (is == null)  throw new Exception("File Does Not Exist");

            isr = new InputStreamReader(is,"UTF8");

            buffer = new StringBuffer();
            int ch;
            while ((ch = isr.read()) > -1) {
                buffer.append((char)ch);
            }
            if (isr != null)
                isr.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return buffer.toString();
    }
    /*

   ////////22222
   String str="test测试";
   new String(str.getBytes("iso-8859-1"));

   ///////33333
   new String(str.getBytes("iso-8859-1"),"gb2312");
   */
    private String convertStrToUnicode(String value) {
        short valueAsShort = Short.parseShort(value.trim(),16);
        return String.valueOf((char)valueAsShort);
    }
    public String convertUnicodeToStr(String unicode) {
        try{
            byte[]  temp_t=unicode.getBytes("ISO8859-1");
            String  temp=new  String(temp_t);
            System.out.println(temp);
            return temp;
        }catch(Exception  e){}
        return null;
    }
    //*************如何将确定的Unicode代码转化为相应的字符（串）？***************//
//使用java.util.Properties类的源代码里面的这两个方法
// private   String   loadConvert(String   theString)
// private   String   loadConvert(String   theString)
// 他们就符合你的要求，这两个方法的代码比较常，
//原因是其中的10进制数和16进制的转换
//没有使用Integer类里面的现成的方法，而是自己直接使用算法转换的。。
    //private   String   loadConvert(String   theString,   boolean   escapeSpace)
    //########java所有支持的文件编码 http://java.sun.com/j2se/1.4.2/docs/guide/intl/encoding.doc.html
    public static byte[] convert(byte[] data, String srcEncoding, String targetEncoding) {
        // First, decode the data using the source encoding.
        // The String constructor does this (Javadoc).
        try{ String str = new String(data, srcEncoding);
            // Next, encode the data using the target encoding.
            // The String.getBytes() method does this.
            byte[] result = str.getBytes(targetEncoding);
            return result;
        }catch(UnsupportedEncodingException ex1){System.out.println("ex1"+ex1);}
        return null;
    }

//try { convert(args[0], args[1], "GB2312", "UTF8"); } // or "BIG5"
//catch (Exception e) {}

    public static void convert(String infile, String outfile, String fromEncoding, String toEncoding) {
        try{
            // set up byte streams
            InputStream in;
            if (infile != null) in = new FileInputStream(infile);
            else in = System.in;
            OutputStream out;
            if (outfile != null) out = new FileOutputStream(outfile);
            else out = System.out;

            // Use default encoding if no encoding is specified.
            if (fromEncoding == null)
                fromEncoding = System.getProperty("file.encoding");
            if (toEncoding == null)
                toEncoding = System.getProperty("file.encoding");

            // Set up character stream
            Reader r = new BufferedReader(new InputStreamReader(in, fromEncoding));
            Writer w = new BufferedWriter(new OutputStreamWriter(out, toEncoding));

            // Copy characters from input to output.  The InputStreamReader
            // converts from the input encoding to Unicode,, and the OutputStreamWriter
            // converts from Unicode to the output encoding.  Characters that cannot be
            // represented in the output encoding are output as '?'
            char[] buffer = new char[4096];
            int len;
            while((len = r.read(buffer)) != -1)
                w.write(buffer, 0, len);
            r.close();
            w.flush();
            w.close();
        }catch(UnsupportedEncodingException ex1){
            System.out.println("ex1"+ex1);
        }catch(IOException ioex1){
            System.out.println("ioex1"+ioex1);
        }
    }

    /*
     * 16进制数字字符集
     */
    private static String hexString="0123456789ABCDEF";
    /*
     * 将字符串编码成16进制数字,适用于所有字符（包括中文）
     */
    public static String encode(String str) {
        //根据默认编码获取字节数组
        byte[] bytes=str.getBytes();
        StringBuilder sb=new StringBuilder(bytes.length*2);
        //将字节数组中每个字节拆解成2位16进制整数
        for(int i=0;i<bytes.length;i++) {
            sb.append(hexString.charAt((bytes[i]&0xf0)>>4));
            sb.append(hexString.charAt((bytes[i]&0x0f)>>0));
        }
        return sb.toString();
    }
    /*
     * 将16进制数字解码成字符串,适用于所有字符（包括中文）
     */
    public static String decode(String bytes) {
        ByteArrayOutputStream baos=new ByteArrayOutputStream(bytes.length()/2);
        //将每2位16进制整数组装成一个字节
        for(int i=0;i<bytes.length();i+=2)
            baos.write((hexString.indexOf(bytes.charAt(i))<<4 |hexString.indexOf(bytes.charAt(i+1))));
        return new String(baos.toByteArray());
    }
}
