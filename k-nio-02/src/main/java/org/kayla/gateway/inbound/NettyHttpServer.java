package org.kayla.gateway.inbound;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.kayla.gateway.inbound.HttpInboundHandler;

import java.util.List;

/**
 * NettyHttpServer
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/10 20:24
 **/
@Slf4j
public class NettyHttpServer {

    private int port;
    private List<String> proxyServers;

    public NettyHttpServer(int port, List<String> proxyServers) {
        this.port = port;
        this.proxyServers = proxyServers;
    }

    public void start() throws InterruptedException {

        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new HttpZeroCopyInitializer(this.proxyServers));

        Channel ch = bootstrap.bind(this.port).sync().channel();

        log.info("HTTP ECHO 服务已经启动 http://{}:{}/"
                , "127.0.0.1"
                , this.port);
        // 等待服务端监听到端口关闭
        ch.closeFuture().sync();
    }

    /**
     *
     */
    static class HttpZeroCopyInitializer extends ChannelInitializer<SocketChannel> {

        private List<String> proxyServer;

        public HttpZeroCopyInitializer(List<String> proxyServer) {
            this.proxyServer = proxyServer;
        }

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {

            ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast(new HttpRequestDecoder());
            pipeline.addLast(new HttpResponseEncoder());
            pipeline.addLast(new HttpObjectAggregator(1024 * 1024));

//            pipeline.addLast(new NettyHttpHandler());
            pipeline.addLast(new HttpInboundHandler(this.proxyServer));
        }
    }

}
