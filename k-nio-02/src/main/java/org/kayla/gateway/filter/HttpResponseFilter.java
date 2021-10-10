package org.kayla.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * HttpResponseFilter
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/07 16:33
 **/
public interface HttpResponseFilter {

    void filter(FullHttpResponse response);

}
