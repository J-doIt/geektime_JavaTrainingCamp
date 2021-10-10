package org.kayla.gateway.nettyserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 单线程的 socket 程序
 *
 * @author Kayla(J - doIt)
 * @date 2021/09/24 00:22
 **/
public class HttpServer01 {

    private static boolean isStop = false;

    public static void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8801);
        while (!isStop) {
            try {
                Socket socket = serverSocket.accept();
                service(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket) {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio1";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        isStop = true;
    }

}
