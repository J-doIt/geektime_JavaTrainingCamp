package org.kayla.gateway;

import org.kayla.gateway.inbound.NettyHttpServer;
import org.kayla.gateway.nettyserver.HttpServer01;
import org.kayla.gateway.nettyserver.HttpServer02;
import org.kayla.gateway.nettyserver.HttpServer03;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.Arrays;

/**
 * 启动类
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/07 15:14
 **/
@SpringBootApplication
public class NettyServerApplication {

    public static void main(String[] args) {
        ApplicationContext context =
                SpringApplication.run(NettyServerApplication.class, args);

        String proxyServers = System.getProperty("proxyServers","http://localhost:8801,http://localhost:8802,http://localhost:8803");
        try {
            new NettyHttpServer(
                    8888,
                    Arrays.asList(proxyServers.split(","))
            ).start();
            HttpServer01.start();
            HttpServer02.start();
            HttpServer03.start();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
