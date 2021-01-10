package com.jinshi.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
    public static void getClientMessage4Socket() throws IOException {
        ServerSocket server = new ServerSocket(GlobalVariable.port);
        System.out.println("start socketServer");
        ExecutorService threadPool = Executors.newFixedThreadPool(300);
        while (true) {
            Socket socket = server.accept();
            socket.setSoTimeout(350);
            Runnable runnable=()->{
                try {
                    // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取  接收消息
                    InputStream inputStream = socket.getInputStream();
                    byte[] bytes = new byte[1024];
                    int len;
                    StringBuilder sb = new StringBuilder();
                    while ((len = inputStream.read(bytes)) != -1) {
                        // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                        sb.append(new String(bytes, 0, len, "UTF-8"));
                    }
                    //发送客户端消息
                    OutputStream outputStream = socket.getOutputStream();
                    if(GlobalVariable.sentClientMessage.equals("-1")){
                        //无消息标识
//                        System.out.println("sent message to client    " +GlobalVariable.sentClientMessage);
                        outputStream.write(GlobalVariable.sentClientMessage.getBytes("UTF-8"));
                    }else{
                        System.out.println(GlobalVariable.sentClientMessage+"  service sent lin-----------------------");
                        outputStream.write(GlobalVariable.sentClientMessage.getBytes("UTF-8"));
                        GlobalVariable.sentClientMessage = "-1";
                    }
//                    System.out.println("get message from client: " + sb);
                    inputStream.close();
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            threadPool.submit(runnable);
        }
    }

    public static void main(String args[]) throws Exception {
//        // 监听指定的端口
//        int port = 55533;
//        ServerSocket server = new ServerSocket(port);
//        // server将一直等待连接的到来
//        System.out.println("server将一直等待连接的到来");
//        //如果使用多线程，那就需要线程池，防止并发过高时创建过多线程耗尽资源
//        ExecutorService threadPool = Executors.newFixedThreadPool(100);
//
//        while (true) {
//            Socket socket = server.accept();
//            Runnable runnable=()->{
//                try {
//                    // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
//                    InputStream inputStream = socket.getInputStream();
//                    byte[] bytes = new byte[1024];
//                    int len;
//                    StringBuilder sb = new StringBuilder();
//                    while ((len = inputStream.read(bytes)) != -1) {
//                        // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
//                        sb.append(new String(bytes, 0, len, "UTF-8"));
//                    }
//                    OutputStream outputStream = socket.getOutputStream();
//                    outputStream.write("Hello Client,I get the message.".getBytes("UTF-8"));
//                    System.out.println("get message from client: " + sb);
//                    inputStream.close();
//                    socket.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            };
//            threadPool.submit(runnable);
//        }
    }
}
