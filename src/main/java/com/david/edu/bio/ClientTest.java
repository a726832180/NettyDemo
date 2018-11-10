package com.david.edu.bio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * @ Author     ：zgq.
 * @ Date       ：Created in 17:06 2018/11/10
 * @ Description：测试方法
 * @ Modified By：
 * @Version: $version$
 */
public class ClientTest {

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {

            @Override
            public void run(){
                try {
                    BIOServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //防止客户端先于服务端启动前执行代码;
        Thread.sleep(100);

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()){
            String str = scanner.next();

            Client.send(str);
        }
//        final char[] op = {'+', '-', '*', '/'};

//        final Random random = new Random(System.currentTimeMillis());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    //随机产生算术表达式
//                    String expression = random.nextInt(10) + "" + op[random.nextInt(4)] +
//                            (random.nextInt(10) + 1);
//                    Client.send(expression);
//                    try {
//                        Thread.sleep(random.nextInt(1000));
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();

    }
}
