package com.lp.exam;

import java.io.*;
import java.net.Socket;

public class ClientUtils {

    public static void main(String[] args) {
        client();
    }

    public static void client() {
        Socket socket = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        PrintWriter write = null;
        try {
            socket = new Socket("192.168.221.1", 8081);
            outputStream = socket.getOutputStream();
            write = new PrintWriter(outputStream);
            inputStream = socket.getInputStream();
            write.println("------------------------ 开始连接 --------------------------");
            write.println("------------------------ 访问 ----------------------------");
            write.println("------------------------ 连接结束 --------------------------");
            write.flush();



            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine;
            while((inputLine = reader.readLine()) != null) {
                System.err.println(inputLine);
            }
            write.close();
            reader.close();
//            System.err.println(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException("连接错误");
        } finally {
            try {
//                write.close();
                outputStream.close();
                inputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
