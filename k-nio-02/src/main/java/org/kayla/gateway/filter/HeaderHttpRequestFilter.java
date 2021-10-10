package org.kayla.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * HeaderHttpRequestFilter
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/07 16:36
 **/
public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("RequestType", "httpclient");
    }
}
