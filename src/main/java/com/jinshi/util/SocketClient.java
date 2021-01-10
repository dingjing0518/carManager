package com.jinshi.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketClient {
    public static void connectService4Socket(){
        try {
            ExecutorService threadPool = Executors.newFixedThreadPool(300);
            while (true) {
                Runnable runnable=()->{
                        try {
                            //发送至服务器消息
                            Socket socket = new Socket(GlobalVariable.host, GlobalVariable.port);
                            socket.setSoTimeout(350);
//                            System.out.println("sent message to server "+GlobalVariable.sentServiceMessage);
                            socket.getOutputStream().write(GlobalVariable.sentServiceMessage.getBytes("UTF-8"));

                            OutputStream outputStream = socket.getOutputStream();
                            socket.shutdownOutput();

                            //获取服务器信息
                            InputStream inputStream = socket.getInputStream();
                            byte[] bytes = new byte[1024];
                            int len;
                            StringBuilder sb = new StringBuilder();
                            while ((len = inputStream.read(bytes)) != -1) {
                                //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                                sb.append(new String(bytes, 0, len,"UTF-8"));
                            }
                            if(sb.toString().equals("-1")){
                                //无消息标识
                            }else{
                                GlobalVariable.openGateLin.put(sb.toString(),sb.toString());
                                System.out.println("---------------get message from server: " + sb+"----------------------");
                            }
//                            System.out.println("get message from server: " + sb);
                            inputStream.close();
                            outputStream.close();
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                };
                threadPool.submit(runnable);
                Thread.sleep(1 * 500); //设置暂停的时间 3 秒
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) throws Exception {
        // 要连接的服务端IP地址和端口
//        String host = "127.0.0.1";
//        int port = 55533;
//        // 与服务端建立连接
//        Socket socket = new Socket(host, port);
//        // 建立连接后获得输出流
//        OutputStream outputStream = socket.getOutputStream();
//        String message = "你好  yiwangzhibujian";
//        socket.getOutputStream().write(message.getBytes("UTF-8"));
//        //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
//        socket.shutdownOutput();

//        InputStream inputStream = socket.getInputStream();
//        byte[] bytes = new byte[1024];
//        int len;
//        StringBuilder sb = new StringBuilder();
//        while ((len = inputStream.read(bytes)) != -1) {
//            //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
//            sb.append(new String(bytes, 0, len,"UTF-8"));
//        }
//        System.out.println("get message from server: " + sb);

//        inputStream.close();
//        outputStream.close();
//        socket.close();
    }
}
