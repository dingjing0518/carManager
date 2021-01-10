package com.jinshi.util;

public class JNITest {
    static {
        System.load("c://DUCP_DLL.dll");
        //System.loadLibrary("Connector");//载入需要调用的dll  Connector.dll
    }
    public native static int DUCP_HOST_SYS_Reset();

    public static void main(String[] args) {
        int i = DUCP_HOST_SYS_Reset();
        System.out.println(i);
    }
}
