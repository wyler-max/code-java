package org.example.practice.practicespring.web.interceptor;

import java.io.IOException;

import org.example.practice.commonutils.utils.JsonUtil;
import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * RestTemplate 拦截器
 */
@Slf4j
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    private static final String HEADER_TRACE_ID = "trace_id";

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
        throws IOException {
        processHeader(request);
        long start = System.currentTimeMillis();
        ClientHttpResponse response = execution.execute(request, body);
        long time = System.currentTimeMillis() - start;
        String message = buildString(request, body, time);
        log.info(message);
        return response;
    }

    /**
     * 处理header 添加traceId
     */
    private void processHeader(HttpRequest request) {
        if (!request.getHeaders().containsKey(HEADER_TRACE_ID)) {
            request.getHeaders().add(HEADER_TRACE_ID, MDC.get("X-B3-TraceId"));
        }
    }

    /**
     * 打印请求参数和耗时
     */
    private String buildString(HttpRequest request, byte[] body, long cost) {
        StringBuilder str = new StringBuilder();
        str.append("RestTemplate request method:");
        str.append(request.getMethodValue());
        str.append(", headers:").append(HEADER_TRACE_ID).append("=");
        str.append(JsonUtil.toJson(request.getHeaders().get(HEADER_TRACE_ID)));
        str.append(", uri:");
        str.append(request.getURI());
        str.append(", body:");
        str.append(new String(body));
        str.append(", cost:");
        str.append(cost);
        str.append("ms");
        return str.toString();
    }
}
