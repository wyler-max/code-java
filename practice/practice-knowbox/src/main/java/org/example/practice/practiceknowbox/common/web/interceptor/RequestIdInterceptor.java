package org.example.practice.practiceknowbox.common.web.interceptor;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author yijiu.chen
 * @date 2020/04/22
 */
public class RequestIdInterceptor implements ClientHttpRequestInterceptor {

    /* (non-Javadoc)
     * @see org.springframework.http.client.ClientHttpRequestInterceptor#intercept(org.springframework.http.HttpRequest, byte[], org.springframework.http.client.ClientHttpRequestExecution)
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution execution)
        throws IOException {
        if (!httpRequest.getHeaders().containsKey(LogInterceptor.REQUEST_ID)) {
            String requestId = MDC.get(LogInterceptor.REQUEST_ID);
            if (StringUtils.isNotBlank(requestId)) {
                httpRequest.getHeaders().add(LogInterceptor.REQUEST_ID, requestId);
            } else {
                requestId = DigestUtils.md5Hex(UUID.randomUUID().toString());
                MDC.put(LogInterceptor.REQUEST_ID, requestId);
                httpRequest.getHeaders().add(LogInterceptor.REQUEST_ID, requestId);
            }
        }
        return execution.execute(httpRequest, body);
    }

}
