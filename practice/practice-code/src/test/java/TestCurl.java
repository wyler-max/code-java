import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.Test;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author wangyulin
 * @date 2022/7/4
 */
@Slf4j
public class TestCurl {

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

    @Test
    public void testCurl(){
        String url = "https://bslive.knowbox.cn/course/user/service/wechatWork/batchGetUserExternalUserId.do";
        List<String> mobiles = Lists.newArrayList("18618147343");

        PoolingHttpClientConnectionManager poolingConnectionManager =
                new PoolingHttpClientConnectionManager(getConnectionSocketFactory());
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(poolingConnectionManager);
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClientBuilder.build());

        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        String resp = restTemplate.postForObject(url, mobiles, String.class);
        System.out.println(resp);
    }
}
