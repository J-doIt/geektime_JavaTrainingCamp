package org.kayla.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * HttpRequestFilter
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/07 16:33
 **/
public interface HttpRequestFilter {

    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);

}
