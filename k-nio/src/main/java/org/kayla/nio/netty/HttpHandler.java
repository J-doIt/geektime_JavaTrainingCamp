package org.kayla.nio.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.ReferenceCountUtil;

/**
 * HttpHandler
 * 整个 Netty Server 启动之后, 客户端的请求进来, 我们读取客户端请求的这么一个 Handler.
 *
 * @author Kayla(J - doIt)
 * @date 2021/09/25 19:39
 **/
public class HttpHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 调用ChannelHandlerContext.fireChannelRead(Object)
     *      以转发到ChannelPipeline的下一个ChannelInboundHandler 。
     *
     * 子类可以覆盖这个方法来改变行为。
     *
     * @param ctx
     * @param msg msg 本身就代表着这次请求的所有的数据包装类的一个对象(HTTP 协议的报文, 相关的信息都在 msg 里).
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
//            logger.info("channelRead 流量接口请求开始, 时间为{}", startTime);
            // 将 msg 转为 HttpRequest 对象, 拿到它内部的结构.
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            String uri = fullHttpRequest.uri();
            // logger("接收到的请求 url 为{}", uri);
            // 根据 URL 做不同的处理, 相当于一个路由, 或者相当于 Spring MVC 的 request mapping.
            if (uri.contains("/test")) {
                handlerTest(fullHttpRequest, ctx, "hello, kayla.");
            } else {
                handlerTest(fullHttpRequest, ctx, "hello, others.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private void handlerTest(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, String body) {
        FullHttpResponse response = null;
        try {
            // 对接上次作业的 httpclient 或者 okhttp 请求另一个 url 的响应数据
            String value = body;
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(value.getBytes()));
            response.headers().set("Content-type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());

        } catch (Exception e) {
            System.out.println("处理出错: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (fullHttpRequest != null) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
