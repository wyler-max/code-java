package org.example.practicescaffold.common.request;

import org.springframework.context.annotation.Description;

import lombok.Data;

/**
 * 使用抽象类是为了禁止实例化
 */
@Data
@Description(value = "请求的公共参数")
public abstract class AbstractRequest {

    // 平台公共参数
    private String appId;

    private String version;

    private String source;

    private String clientIp;

    // 用户公共参数
    private String token;
    private long userNumber;
}
