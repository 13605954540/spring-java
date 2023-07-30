package com.lp.exam;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerUtils {

    public static void main(String[] args) {
        server();
    }

    public static void server() {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            serverSocket = new ServerSocket(8081);
            clientSocket = serverSocket.accept();
            inputStream = clientSocket.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String inputLine = bufferedReader.readLine();
//            while((inputLine = bufferedReader.readLine()) != null) {
//                System.err.println(inputLine);
//            }
            System.err.println("跳到下面来了");
            outputStream = clientSocket.getOutputStream();
            PrintWriter write = new PrintWriter(outputStream);
            write.println("------------------------ 开始接收 --------------------------");
            write.println("------------------------ 去你的，别乱加参数" + inputLine + "----------------------------");
            write.println("------------------------ 接收结束 --------------------------");
            write.flush();
//            write.close();
        } catch (IOException e) {
            throw new RuntimeException("连接错误");
        } finally {
            if(serverSocket != null) {
                try {
                    inputStream.close();
                    outputStream.close();
                    clientSocket.close();
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
