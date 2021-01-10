package com.jinshi.util;

public class SocketClientThread extends Thread {
    @Override
    public void run() {
        SocketClient.connectService4Socket();
    }
}
