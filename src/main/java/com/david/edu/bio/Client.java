package com.david.edu.bio;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ Author     ：zgq.
 * @ Date       ：Created in 16:58 2018/11/10
 * @ Description：阻塞式IO创建的客户端
 * @ Modified By：
 * @Version: $version$
 */
@Slf4j
public class Client {

    //默认的端口号
    private static int DEFAULT_SERVER_PORT = 8888;

    //
    private static String DEFAULT_SERVER_IP = "127.0.0.1";

    public static void send(String msg){
        send(DEFAULT_SERVER_PORT,msg);
    }

    private static void send(int port, String message) {
//        System.out.println("算法表达式:" + expression);
        Socket socket = null;
        BufferedReader in  = null;
        BufferedReader input  = null;
        PrintWriter out = null;

        String fromMsg = null;
        String toMsg = null;

        try {
            socket  = new Socket(DEFAULT_SERVER_IP,port);
            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            input  = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter( socket.getOutputStream(),true);
            out.println(message);

            String time = null;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while (true){
                //服务从服务端传来的消息
                if((fromMsg = in.readLine() ) == null) break;
                time = sdf.format(new Date());
                System.out.println(time + " 客户端收到消息：" + fromMsg);
                System.out.print("客户端回复：");
                // 获取回复的消息
                toMsg = input.readLine();
                out.println(toMsg);
            }

            System.out.println("结果为:"+in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }

            if (out != null){
                out.close();
                out = null;
            }


            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
