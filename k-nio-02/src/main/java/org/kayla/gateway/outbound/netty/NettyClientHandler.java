package org.kayla.gateway.outbound.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * NettyClientHandler
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/07 17:20
 **/
@ChannelHandler.Sharable
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    public static final NettyClientHandler INSTANCE = new NettyClientHandler();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        int len = in.readableBytes();
        byte[] arr = new byte[len];
        in.getBytes(0, arr);
        org.kayla.gateway.util.Logger.info("client received: " + new String(arr, "UTF-8"));
        in.release();
    }
}
