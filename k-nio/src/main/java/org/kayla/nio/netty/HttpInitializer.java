package org.kayla.nio.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;

/**
 * HttpInitializer 继承了 ChannelInitializer
 *
 * @author Kayla(J - doIt)
 * @date 2021/09/25 19:39
 **/
public class HttpInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 需要在这次的网络处理里面中间这一段，需要我们自己控制它的流程(流水线/处理链)
        ChannelPipeline pipeline = ch.pipeline();
        // 在里面末尾添加了一个 HttpServerCodec 编码器
        pipeline.addLast(new HttpServerCodec());
//        pipeline.addLast(new HttpServerExpectContinueHandler());
        // 再添加一个 HttpObjectAggregator 报文聚合器
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        // 再在最后面, 添加一个自定义的 HttpHandler
        pipeline.addLast(new HttpHandler());
    }
}
