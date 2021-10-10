package org.kayla.gateway.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import org.kayla.gateway.util.HttpProtocolHelper;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;

/**
 * NettyHttpHandler
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/10 20:38
 **/
public class NettyHttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg)
            throws Exception {
        if (!msg.decoderResult().isSuccess()) {
            HttpProtocolHelper.sendError(ctx, BAD_REQUEST);
            return;
        }

    }

}
