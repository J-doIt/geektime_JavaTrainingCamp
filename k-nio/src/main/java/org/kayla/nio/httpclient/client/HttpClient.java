package org.kayla.nio.httpclient.client;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * HttpClient
 *
 * 代码出处: https://blog.csdn.net/dongzi_yu/article/details/107994120
 * @author Kayla(J - doIt)
 * @date 2021/09/26 21:09
 **/
public class HttpClient {

    public static void main(String[] args) throws IOException {
        String request = getRequest("http://127.0.0.1:8808/");
        System.out.println(request);
    }

    public static String getRequest(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        String context = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = null;
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                entity = response.getEntity();
                context = EntityUtils.toString(entity, "utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }
        return context;
    }
}
