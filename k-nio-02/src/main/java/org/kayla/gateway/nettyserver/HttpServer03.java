package org.kayla.gateway.nettyserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建了一个固定大小的线程池处理请求
 *
 * @author Kayla(J - doIt)
 * @date 2021/09/25 13:29
 **/
public class HttpServer03 {

    private static boolean isStop = false;

    public static void start() throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() + 2);
        // final ServerSocket
        final ServerSocket serverSocket = new ServerSocket(8803);
        while (!isStop) {
            try {
                // final Socket
                final Socket socket = serverSocket.accept();
                // 把处理的请求提交给线程池来异步处理
                executorService.execute(() -> service(socket));
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
            String body = "hello,nio3";
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
