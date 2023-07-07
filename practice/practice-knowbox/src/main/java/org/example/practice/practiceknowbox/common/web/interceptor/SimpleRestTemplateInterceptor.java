package org.example.practice.practiceknowbox.common.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.practice.practiceknowbox.common.util.JsonUtil;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

/**
 * RestTemplate 拦截器
 */
@Slf4j
public class SimpleRestTemplateInterceptor implements ClientHttpRequestInterceptor {

    private static final String HEADER_TRACE_ID = "trace_id";

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        traceHeader(request);
        long start = System.currentTimeMillis();
        ClientHttpResponse response = execution.execute(request, body);
        log.info("{}, {}", traceRequest(request), traceResponse(response, System.currentTimeMillis() - start));
        return response;
    }

    /**
     * 处理header 添加traceId
     */
    private void traceHeader(HttpRequest request) {
        if (!request.getHeaders().containsKey(HEADER_TRACE_ID)) {
            request.getHeaders().add(HEADER_TRACE_ID, MDC.get("X-B3-TraceId"));
        }
    }

    /**
     * 打印请求参数
     */
    private String traceRequest(HttpRequest request) {
        StringBuilder str = new StringBuilder();
        str.append("RestTemplate request method:");
        str.append(request.getMethodValue());
        str.append(", headers:");
        // remove header useless field
        HttpHeaders headers = request.getHeaders();
        headers.setAcceptCharset(Collections.emptyList());
        str.append(JsonUtil.toJson(headers));
        str.append(", uri:");
        str.append(request.getURI());
        return str.toString();
    }

    private String traceResponse(ClientHttpResponse response, long cost) {
        StringBuilder respBuffer = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                respBuffer.append(line);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
        StringBuilder str = new StringBuilder();
        str.append("resp:").append(respBuffer);
        str.append(", cost:").append(cost).append("ms");
        return str.toString();
    }
}
