package org.example.practice.practiceknowbox.config;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.example.practice.practiceknowbox.common.web.interceptor.RestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RestTemplateConfig {

    @Autowired(required = false)
    private Registry<ConnectionSocketFactory> connectionSocketFactory;
    @Autowired(required = false)
    private ConnectionKeepAliveStrategy connectionKeepAliveStrategy;
    @Autowired(required = false)
    private HttpClientConnectionManager httpClientConnectionManager;

    @Value("${restTemplate.client.connectionRequestTimeout:2000}")
    private int connectionRequestTimeout;
    @Value("${restTemplate.client.connectTimeout:1000}")
    private int connectTimeout;
    @Value("${restTemplate.client.socketTimeout:3000}")
    private int socketTimeout;
    @Value("${restTemplate.client.pool.maxTotal:1000}")
    private int maxTotal;
    @Value("${restTemplate.client.pool.defaultMaxPerRoute:400}")
    private int defaultMaxPerRoute;

    @Value("${restTemplate.client.pool.idleConnectionWaitSeconds:30}")
    private int idleConnectionWaitSeconds;

    @Value("${restTemplate.slowclient.connectTimeout:2000}")
    private int slowConnectTimeout;
    @Value("${restTemplate.slowclient.socketTimeout:10000}")
    private int slowSocketTimeout;

    @Bean
    @Primary
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        restTemplate.setInterceptors(Collections.singletonList(new RestTemplateInterceptor()));
        return restTemplate;
    }

    /**
     * 某些服务就是慢
     *
     * @return
     */
    @Bean("slowRestTemplate")
    public RestTemplate slowRestTemplate() {
        Registry<ConnectionSocketFactory> registry = getDefaultConnectionSocketFactory();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        connectionManager.setValidateAfterInactivity(2000);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(slowSocketTimeout)
                .setConnectTimeout(slowConnectTimeout).setConnectionRequestTimeout(connectionRequestTimeout).build();
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClientBuilder
                .create().setDefaultRequestConfig(requestConfig).setConnectionManager(connectionManager).build()));
        restTemplate.setInterceptors(Collections.singletonList(new RestTemplateInterceptor()));
        return restTemplate;
    }

    /**
     * 默认SSL校验
     */
    public static Registry<ConnectionSocketFactory> getDefaultConnectionSocketFactory() {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory()).build();
        return socketFactoryRegistry;
    }

    /**
     * 设置SSL单向校验
     */
    public static Registry<ConnectionSocketFactory> getConnectionSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            sslsf = new SSLConnectionSocketFactory(builder.build());
        } catch (Exception e) {
            log.error("poolingHttpClientConnectionManager", e);
        }
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory()).register("https", sslsf).build();
        return socketFactoryRegistry;
    }

    /**
     * 池化连接管理器
     */
    private HttpClientConnectionManager poolingConnectionManager() {
        // 可通过自定义connectionSocketFactory，注入到bean容器，实现SSL单向校验
        if (connectionSocketFactory == null) {
            log.info("RestTemplateConfig Default ConnectionSocketFactory SSL双向校验");
            connectionSocketFactory = getDefaultConnectionSocketFactory();
        } else {
            log.info("RestTemplateConfig 设置SSL单向校验");
        }
        PoolingHttpClientConnectionManager poolingConnectionManager =
                new PoolingHttpClientConnectionManager(connectionSocketFactory);
        // 连接池最大连接数
        poolingConnectionManager.setMaxTotal(maxTotal);
        // 每个主机的并发
        poolingConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        // 清除失效连接
        poolingConnectionManager.closeExpiredConnections();
        // 每隔n秒，清除一次空闲连接
        poolingConnectionManager.closeIdleConnections(idleConnectionWaitSeconds, TimeUnit.SECONDS);
        return poolingConnectionManager;
    }

    /**
     * 设置HTTPClientBuilder
     */
    private HttpClientBuilder httpClientBuilder() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 设置HTTP连接管理器
        if (httpClientConnectionManager == null) {
            log.info("RestTemplateConfig Default HttpClientConnectionManager");
            httpClientConnectionManager = poolingConnectionManager();
        } else {
            log.info("RestTemplateConfig 自定义HTTP连接管理器");
        }
        httpClientBuilder.setConnectionManager(httpClientConnectionManager);
        // 设置连接保持策略
        if (connectionKeepAliveStrategy == null) {
            log.info("RestTemplateConfig Default ConnectionKeepAliveStrategy");
            connectionKeepAliveStrategy = DefaultConnectionKeepAliveStrategy.INSTANCE;
        } else {
            log.info("RestTemplateConfig 自定义连接保持策略");
        }
        httpClientBuilder.setKeepAliveStrategy(connectionKeepAliveStrategy);
        return httpClientBuilder;
    }

    /**
     * 设置ClientHttpRequestFactory
     */
    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClientBuilder().build());
        // 连接建立超时时间（握手时间），毫秒
        clientHttpRequestFactory.setConnectTimeout(connectTimeout);
        // 连接读取超时时间，毫秒
        clientHttpRequestFactory.setReadTimeout(socketTimeout);
        // 连接池获取连接超时时间
        clientHttpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
        return clientHttpRequestFactory;
    }
}
