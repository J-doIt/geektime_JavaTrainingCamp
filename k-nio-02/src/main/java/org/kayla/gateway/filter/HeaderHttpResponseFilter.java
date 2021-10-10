package org.kayla.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * HeaderHttpResponseFilter
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/07 16:36
 **/
public class HeaderHttpResponseFilter implements HttpResponseFilter {

    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("ResponseType", "netty");
    }
}
