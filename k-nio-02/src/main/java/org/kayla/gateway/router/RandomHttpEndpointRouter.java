package org.kayla.gateway.router;

import java.util.List;
import java.util.Random;

/**
 * RandomHttpEndpointRouter
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/07 17:24
 **/
public class RandomHttpEndpointRouter implements HttpEndpointRouter {

    @Override
    public String route(List<String> endpoints) {
        int size = endpoints.size();
        Random random = new Random(System.currentTimeMillis());
        return endpoints.get(random.nextInt(size));
    }
}
