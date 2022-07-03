package org.example.practicejava.utils.elasticsearch;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * es 对象资源所在
 */
@Data
public class ESSource {

    private Integer cost;
    private String uri;
    private String source;
    // ... 忽略一些无用字段

    @JsonProperty("@timestamp")
    private String timestamp;
    private String request_id;

    private String header;
    private String param;
    private String resp;

    @Data
    public static class Header {
        @JsonProperty("RemoteIp")
        private String RemoteIp;
        @JsonProperty("X-Forwarded-For")
        private String XForwardedFor;
        @JsonProperty("X-Real-IP")
        private String XRealIP;
    }
}
