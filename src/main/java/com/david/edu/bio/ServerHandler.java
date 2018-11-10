package com.david.edu.bio;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ Author     ：zgq.
 * @ Date       ：Created in 16:45 2018/11/10
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
@Slf4j
public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        BufferedReader consoleInput = null;
        PrintWriter out = null;

        try {

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            consoleInput = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(socket.getOutputStream(),true);

//            String expression;
//            int result;

            //读取从给客户端传来的消息并回复
            String fromMsg = null;
            String toMsg = null;
            String time = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while(true){

                //读取从客户端传来的消息
                if ((fromMsg = in.readLine()) == null)break;

                time = sdf.format(new Date());
                System.out.println((time + " 服务器端收到信息：" + fromMsg));
                System.out.print("服务端回复：");
                // 获取回复的消息
                toMsg = consoleInput.readLine();
                // 返回客户端
                out.println(toMsg);


                //通过bufferedReader读取一行
                //如果已经读到输入流尾部,返回null,退出循环
                //如果非控制,就输出到控制台并返回
//                if ((expression = in.readLine()) == null) break;
//                System.out.println("数据：" + expression);
//                out.println("服务端收到的数据：" + expression);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println((e.getLocalizedMessage()));
        }finally {

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                in = null;

            }

            if (out != null) {

                out.close();
                out = null;

            }

            if (socket != null) {

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
