package com.jinshi.util;

import java.io.IOException;

public class SocketServerThread extends Thread {
    @Override
    public void run() {
        try {
            SocketServer.getClientMessage4Socket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
