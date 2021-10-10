package org.kayla.gateway.router;

import java.util.List;

/**
 * HttpEndpointRouter
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/07 17:23
 **/
public interface HttpEndpointRouter {

    String route(List<String> endpoints);

}
