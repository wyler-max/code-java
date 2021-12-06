package org.example.practice.practiceknowbox.config;

import java.util.Collections;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RestTemplateConfig {

    @Autowired
    private StringHttpMessageConverter stringHttpMessageConverter;
    @Autowired(required = false)
    private Registry<ConnectionSocketFactory> connectionSocketFactory;
    @Autowired(required = false)
    private ConnectionKeepAliveStrategy connectionKeepAliveStrategy;
    @Autowired(required = false)
    private HttpClientConnectionManager httpClientConnectionManager;

    @Value("${restTemplate.client.connectionRequestTimeout:2000}")
    private int connectionRequestTimeout;

    @Value("${restTemplate.client.connectTimeout:200}")
    private int connectTimeout;
    @Value("${restTemplate.client.socketTimeout:3000}")
    private int socketTimeout;

    @Value("${restTemplate.client.pool.maxTotal:1000}")
    private int maxTotal;
    @Value("${restTemplate.client.pool.defaultMaxPerRoute:400}")
    private int defaultMaxPerRoute;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
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
     * 池化连接管理器
     */
    private HttpClientConnectionManager poolingConnectionManager() {
        // 可通过自定义connectionSocketFactory，注入到bean容器，实现SSL单向校验
        if (connectionSocketFactory == null) {
            log.info("Default ConnectionSocketFactory SSL双向校验");
            connectionSocketFactory = getDefaultConnectionSocketFactory();
        }
        PoolingHttpClientConnectionManager poolingConnectionManager =
            new PoolingHttpClientConnectionManager(connectionSocketFactory);
        // 连接池最大连接数
        poolingConnectionManager.setMaxTotal(maxTotal);
        // 每个主机的并发
        poolingConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return poolingConnectionManager;
    }

    /**
     * 设置HTTPClientBuilder
     */
    private HttpClientBuilder httpClientBuilder() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 设置HTTP连接管理器
        if (httpClientConnectionManager == null) {
            log.info("Default HttpClientConnectionManager");
            httpClientConnectionManager = poolingConnectionManager();
        }
        httpClientBuilder.setConnectionManager(httpClientConnectionManager);
        // 设置连接保持策略
        if (connectionKeepAliveStrategy == null) {
            log.info("Default ConnectionKeepAliveStrategy");
            connectionKeepAliveStrategy = DefaultConnectionKeepAliveStrategy.INSTANCE;
        }
        httpClientBuilder.setKeepAliveStrategy(connectionKeepAliveStrategy);
        return httpClientBuilder;
    }

    /**
     * 设置ClientHttpRequestFactory
     */
    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClientBuilder().build());
        // 建立连接超时，毫秒
        clientHttpRequestFactory.setConnectTimeout(connectTimeout);
        // route响应读写超时，毫秒
        clientHttpRequestFactory.setReadTimeout(socketTimeout);
        // 获取连接的超时时间
        clientHttpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
        return clientHttpRequestFactory;
    }
}
