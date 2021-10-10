package org.kayla.gateway.outbound.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * NettyClientInitializer
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/07 18:05
 **/
public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {

    // 有连接到达时会创建一个channel
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // pipeline管理子通道channel中的Handler
        // 向子channel流水线添加一个handler处理器
        ch.pipeline().addLast(NettyClientHandler.INSTANCE);
    }
}
