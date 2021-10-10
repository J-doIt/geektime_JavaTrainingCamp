package org.kayla.gateway.util;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_0;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by 尼恩 @ 疯狂创客圈
 */
public class HttpProtocolHelper {

    public static final AttributeKey<HttpVersion> PROTOCOL_VERSION_KEY =
            AttributeKey.valueOf("PROTOCOL_VERSION");
    public static final AttributeKey<Boolean> KEEP_ALIVE_KEY =
            AttributeKey.valueOf("KEEP_ALIVE_KEY");

    public static boolean isHTTP_1_0(ChannelHandlerContext ctx) {
        HttpVersion protocol_version =
                ctx.channel().attr(PROTOCOL_VERSION_KEY).get();
        if (null == protocol_version) {
            return false;
        }
        if (protocol_version.equals(HTTP_1_0)) {
            return true;
        }
        return false;
    }

    private static HttpVersion getHttpVersion(ChannelHandlerContext ctx) {
        HttpVersion version;
        if (isHTTP_1_0(ctx)) {
            version = HTTP_1_0;
        } else {
            version = HTTP_1_1;
        }
        return version;
    }


    /**
     * 发送响应
     * @param ctx
     * @param response
     */
    public static void sendAndCleanupConnection(ChannelHandlerContext ctx, FullHttpResponse response) {
        final boolean keepAlive = ctx.channel().attr(KEEP_ALIVE_KEY).get();
        HttpUtil.setContentLength(response, response.content().readableBytes());
        if (!keepAlive) {
            // 如果不是长连接，设置 connection:close 头部
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
        } else if (isHTTP_1_0(ctx)) {
            // 如果是1.0版本的长连接，设置 connection:keep-alive 头部
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }
        //发送内容
        ChannelFuture flushPromise = ctx.writeAndFlush(response);

        if (!keepAlive) {
            // 如果不是长连接，发送完成之后，关闭连接
            flushPromise.addListener(ChannelFutureListener.CLOSE);
        }
    }

    public static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        HttpVersion version = getHttpVersion(ctx);
        FullHttpResponse response = new DefaultFullHttpResponse(
                version, status, Unpooled.copiedBuffer("Failure: " + status + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");

        sendAndCleanupConnection(ctx, response);
    }
}
