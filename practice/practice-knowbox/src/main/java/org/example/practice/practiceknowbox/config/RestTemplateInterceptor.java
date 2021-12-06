package org.example.practice.practiceknowbox.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.practice.practiceknowbox.utils.JsonUtil;
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
        if (time > 200) {
            String message = buildString(request, body, time);
            log.info(message);
        } else if (log.isDebugEnabled()) {
            String message = buildString(request, body, time);
            log.debug(message);
        }
        return response;
    }

    /**
     * 处理header 添加logger_id
     */
    private void processHeader(HttpRequest request) {
        String s = MDC.get("X-B3-TraceId");
        log.info("aaaaa=" + s);
        if (!request.getHeaders().containsKey(HEADER_TRACE_ID)) {
            final String traceId = DigestUtils.md5Hex(UUID.randomUUID().toString());
            request.getHeaders().add(HEADER_TRACE_ID, traceId);
        }
    }

    /**
     * 处理response
     */
    private String processResponse(ClientHttpResponse response) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                inputStringBuilder.append(line);
                inputStringBuilder.append('\n');
                line = bufferedReader.readLine();
            }
        }
        return inputStringBuilder.toString();
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
