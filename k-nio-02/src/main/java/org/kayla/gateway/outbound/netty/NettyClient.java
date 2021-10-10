package org.kayla.gateway.outbound.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.kayla.gateway.util.Dateutil;

import java.util.Scanner;

/**
 * NettyClient
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/07 17:45
 **/
public class NettyClient {

    private int serverPort;
    private String serverIp;
    Bootstrap bootstrap = new Bootstrap();

    public NettyClient(int port, String ip) {
        this.serverPort = port;
        this.serverIp = ip;
    }

    public void runClient() {
        // 创建 reactor 线程组 (创建反应器轮询组)
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            //1 设置 reactor 线程组
            bootstrap.group(workerLoopGroup);
            //2 设置nio类型的channel
            bootstrap.channel(NioSocketChannel.class);
            //3 设置监听端口
            bootstrap.remoteAddress(serverIp, serverPort);
            //4 设置通道的参数
            bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            //5 装配子通道流水线
            bootstrap.handler(new NettyClientInitializer());

            ChannelFuture channelFuture = bootstrap.connect();
            channelFuture.addListener((ChannelFuture futureListener) -> {
                if (futureListener.isSuccess()) {
                    org.kayla.gateway.util.Logger.info("EchoClient客户端连接成功!");
                } else {
                    org.kayla.gateway.util.Logger.info("EchoClient客户端连接失败!");
                }
            });

            // 阻塞,直到连接完成
            channelFuture.sync();
            Channel channel = channelFuture.channel();

            Scanner scanner = new Scanner(System.in);
            org.kayla.gateway.util.Logger.tcfo("请输入发送内容:");

            while (scanner.hasNext()) {
                //获取输入的内容
                String next = scanner.next();
                byte[] bytes = (Dateutil.getNow() + " >>" + next).getBytes("UTF-8");
                //发送ByteBuf
                ByteBuf buffer = channel.alloc().buffer();
                buffer.writeBytes(bytes);
                channel.writeAndFlush(buffer);
                org.kayla.gateway.util.Logger.tcfo("请输入发送内容:");

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 优雅关闭EventLoopGroup，
            // 释放掉所有资源包括创建的线程
            workerLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyClient(8888, "127.0.0.1").runClient();
    }
}
