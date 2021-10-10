package org.kayla.gateway.outbound.httpclient;

import org.apache.commons.io.IOUtils;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.pool.PoolStats;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * HttpClientHelper
 *
 * 代码出处: 《Java高并发核心编程》
 * @author Kayla(J - doIt)
 * @date 2021/09/26 17:13
 **/
public class HttpClientHelper {

    private static final long KEEP_ALIVE_DURATION = 600000;// 长连接的保持时长， 单位ms
    private static final int POOL_MAX_TOTAL = 500;// 最大的连接数
    private static final int SOCKET_TIMEOUT = 2000;// 建立连接后，客户端从服务器读取数据的超时时长， 单位ms
    private static final int CONNECT_TIMEOUT = 2000;// 客户端和服务器建立连接的超时时长， 单位ms
    private static final int REQUEST_TIMEOUT = 2000;// 从连接池获取连接的超时时长， 单位ms
    private static final int IDLE_CHECK_GAP = 6;// 空闲监测，配置文件默认为6秒

    // 单例：HTTP长连接管理器(连接池)
    private static PoolingHttpClientConnectionManager httpClientConnectionManager;
    // 单例：全局的池化HTTP客户端实例
    private static CloseableHttpClient pooledHttpClient;

    private static ScheduledExecutorService monitorExecutor = null;

    public static void main(String[] args) throws InterruptedException {
//        String url = "http://127.0.0.1:8888";
        String url = "http://127.0.0.1:8808/test";
        ExecutorService pool = Executors.newFixedThreadPool(10);
        int index = 100;
        while (--index > 0) {
            String target = url + index;
            pool.submit(() -> {
                String out = jdkGet(target);
                System.out.println("out = " + out);
            });
        }
        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 创建全局连接池：HTTP连接管理器
     */
    public static void createHttpClientConnectionManager() {
        // DNS 解析器
        DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;
        // 负责HTTP传输的Socket套接字工厂
        ConnectionSocketFactory plainSocketFactory = PlainConnectionSocketFactory.getSocketFactory();
        // 负责HTTPS传输的安全Socket套接字工厂
        LayeredConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactory.getSocketFactory();
        // 根据应用层协议，为其注册传输层的套接字工厂
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", plainSocketFactory)
                .build();
        // 创建连接池管理器
        httpClientConnectionManager = new PoolingHttpClientConnectionManager(
                registry,
                null,
                null,
                dnsResolver,
                KEEP_ALIVE_DURATION,
                TimeUnit.MILLISECONDS);

        // 最大连接数，高于这个值时的新连接请求，需要阻塞和排队等待
        httpClientConnectionManager.setMaxTotal(POOL_MAX_TOTAL);
    }

    /**
     * 定时处理线程：对异常连接进行关闭
     */
    private static void startExpiredConnectionsMonitor() {
        // 空闲监测,配置文件默认为6s
        int idleCheckGap = IDLE_CHECK_GAP;
        // 设置保持连接的时长,根据实际情况调整配置
        long keepAliveTimeout = KEEP_ALIVE_DURATION;
        // 开启监控线程,对异常和空闲线程进行关闭
        monitorExecutor = Executors.newScheduledThreadPool(1);
        monitorExecutor.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // 关闭异常连接
                httpClientConnectionManager.closeExpiredConnections();
                // 关闭keepAliveTimeout（保持连接时长）超时的不活跃的连接
                httpClientConnectionManager.closeIdleConnections(keepAliveTimeout, TimeUnit.MILLISECONDS);
                // 获取连接池的状态
                PoolStats status = httpClientConnectionManager.getTotalStats();
            }
        }, idleCheckGap, idleCheckGap, TimeUnit.MILLISECONDS);
        httpClientConnectionManager.closeExpiredConnections();
        httpClientConnectionManager.closeIdleConnections(keepAliveTimeout, TimeUnit.MILLISECONDS);

    }

    /**
     * 创建带连接池的 pooledHttpClient 全局客户端实例
     */
    public static CloseableHttpClient pooledHttpClient() {
        if (null != pooledHttpClient) {
            return pooledHttpClient;
        }
        createHttpClientConnectionManager();

        // 请求配置实例
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        // 等待数据超时设置
        requestConfigBuilder.setSocketTimeout(SOCKET_TIMEOUT);
        // 连接超时设置
        requestConfigBuilder.setConnectTimeout(CONNECT_TIMEOUT);
        // 从连接池获取连接的等待超时时间设置
        requestConfigBuilder.setConnectionRequestTimeout(REQUEST_TIMEOUT);
        RequestConfig config = requestConfigBuilder.build();

        // httpclient建造者实例
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 设置连接池管理器
        httpClientBuilder.setConnectionManager(httpClientConnectionManager);
        // 设置请求配置信息
        httpClientBuilder.setDefaultRequestConfig(config);
        // httpclient默认提供了一个Keep-Alive策略
        // 这里进行定制：确保客户端与服务端在长连接的保持时长一致
        httpClientBuilder.setKeepAliveStrategy(new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String parame = he.getName();
                    String value = he.getValue();
                    if (value != null && parame.equalsIgnoreCase("timeout")) {
                        return Long.parseLong(value) * 1000;
                    }
                }
                return KEEP_ALIVE_DURATION;
            }
        });
        // 实例化：全局的池化HTTP客户端实例
        pooledHttpClient = httpClientBuilder.build();
        // 启动定时处理线程：对异常和空闲连接进行关闭
        startExpiredConnectionsMonitor();
        return pooledHttpClient;
    }

    public static String get(String url) {
        CloseableHttpClient client = pooledHttpClient();
        HttpGet httpGet = new HttpGet(url);
        return pooledRequestData(url, client, httpGet);
    }

    private static String pooledRequestData(String url, CloseableHttpClient client, HttpRequest request) {
        CloseableHttpResponse response = null;
        InputStream in = null;
        String result = null;

        try {
            HttpHost httpHost = getHost(url);
            response = client.execute(httpHost, request, HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                in = entity.getContent();
                result = IOUtils.toString(in, "utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            quietlyClose(in);
            quietlyClose(response);
        }
        return result;
    }

    private static HttpHost getHost(String url) {
        String hostName = url.split("/")[2];
        int port = 80;
        if (hostName.contains(":")) {
            String[] args = hostName.split(":");
            hostName = args[0];
            port = Integer.parseInt(args[1]);
        }
        HttpHost httpHost = new HttpHost(hostName, port);
        return httpHost;
    }

    private static void quietlyClose(java.io.Closeable closeable) {
        if (null == closeable) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String jdkGet(String url) {
        //输入流
        InputStream inputStream = null;
        //HTTP连接实例
        HttpURLConnection httpConnection = null;
        StringBuilder builder = new StringBuilder();
        try {
            URL restServiceURL = new URL(url);
            //打开HttpURLConnection连接实例
            httpConnection =
                    (HttpURLConnection) restServiceURL.openConnection();
            //设置请求头
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Accept", "application/json");
            //建立连接，发送请求
            httpConnection.connect();
            //读取响应
            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + httpConnection.getResponseCode());
            }
            //读取响应内容（字节流）
            inputStream = httpConnection.getInputStream();
            byte[] b = new byte[1024];
            int length = -1;
            while ((length = inputStream.read(b)) != -1) {
                builder.append(new String(b, 0, length));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流和连接
            quietlyClose(inputStream);
            httpConnection.disconnect();
        }
        return builder.toString();
    }

}
