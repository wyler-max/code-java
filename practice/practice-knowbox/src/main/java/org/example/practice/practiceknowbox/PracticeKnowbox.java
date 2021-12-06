package org.example.practice.practiceknowbox;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.example.practice.practiceknowbox.config.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Slf4j
public class PracticeKnowbox {
    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {

        SpringApplication.run(PracticeKnowbox.class, args);
    }

    /**
     * 设置SSL单向校验
     */
    public Registry<ConnectionSocketFactory> getConnectionSocketFactory() {
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
        /*if (connectionSocketFactory == null) {
            log.info("ConnectionSocketFactory SSL双向校验");
            connectionSocketFactory = getDefaultConnectionSocketFactory();
        }*/
        Registry<ConnectionSocketFactory> connectionSocketFactory =
            RestTemplateConfig.getDefaultConnectionSocketFactory();
        PoolingHttpClientConnectionManager poolingConnectionManager =
            new PoolingHttpClientConnectionManager(connectionSocketFactory);
        // 连接池最大连接数
        poolingConnectionManager.setMaxTotal(1000);
        // 每个主机的并发
        poolingConnectionManager.setDefaultMaxPerRoute(200);
        return poolingConnectionManager;
    }

    @Bean
    @ConditionalOnBean(RestTemplate.class)
    public void setRestTemplateEncode() {
        if (null == restTemplate || CollectionUtils.isEmpty(restTemplate.getMessageConverters())) {
            return;
        }
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        for (int i = 0; i < messageConverters.size(); i++) {
            HttpMessageConverter<?> httpMessageConverter = messageConverters.get(i);
            if (httpMessageConverter.getClass().equals(StringHttpMessageConverter.class)) {
                messageConverters.set(i, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            }
        }
    }
}
