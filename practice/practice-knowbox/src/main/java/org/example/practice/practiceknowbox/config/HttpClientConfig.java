package org.example.practice.practiceknowbox.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpClientConfig {

    @Value("${http.client.pool.maxTotal:200}")
    private int maxTotalConnection;

    @Value("${http.client.pool.defaultMaxPerRoute:100}")
    private int defaultMaxPerRoute;

    @Value("${http.client.connectionKeepAlive:6000}")
    private long connectionKeepAlive;

    @Value("${http.client.connectionRequestTimeout:500}")
    private int connectionRequestTimeout;

    @Value("${http.client.connectTimeout:5000}")
    private int connectionTimeout;

    @Value("${http.client.socketTimeout:30000}")
    private int socketTimeout;

    @Value("${http.client.pool.closeIdleConnectionWaitSeconds:30}")
    private int idleConnectionWaitSeconds;

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        SSLContextBuilder builder = new SSLContextBuilder();
        try {
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        } catch (NoSuchAlgorithmException | KeyStoreException e) {
            log.error("poolingHttpClientConnectionManager", e);
        }

        SSLConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(builder.build());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            log.error("poolingHttpClientConnectionManager", e);
        }

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
            .register("https", SSLConnectionSocketFactory.getSocketFactory())
            .register("http", new PlainConnectionSocketFactory()).build();

        PoolingHttpClientConnectionManager poolingConnectionManager =
            new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        poolingConnectionManager.setMaxTotal(maxTotalConnection);
        poolingConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return poolingConnectionManager;
    }

    @Bean
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        ConnectionKeepAliveStrategy strategy = new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
                HeaderElementIterator it =
                    new BasicHeaderElementIterator(httpResponse.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();

                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        return Long.parseLong(value) * 1000;
                    }
                }
                return connectionKeepAlive;

            }
        };
        return strategy;
    }

    @Bean
    public CloseableHttpClient closeableHttpClient() {
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
            .setConnectTimeout(connectionTimeout).setSocketTimeout(socketTimeout).build();

        return HttpClients.custom().setDefaultRequestConfig(requestConfig)
            .setConnectionManager(poolingHttpClientConnectionManager())
            .setKeepAliveStrategy(connectionKeepAliveStrategy()).build();

    }
}
