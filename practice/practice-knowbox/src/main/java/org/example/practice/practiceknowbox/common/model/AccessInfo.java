package org.example.practice.practiceknowbox.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.example.practice.practiceknowbox.common.util.JsonUtil;

import com.google.common.collect.Maps;

import lombok.Data;

/**
 * 业务访问日志
 *
 * @author yijiu.chen
 * @date 2020/04/16
 */
@Data
public class AccessInfo implements Serializable {
    private static final ThreadLocal<AccessInfo> REQUEST_INFO = new ThreadLocal<>();

    private static String[] ENCRYPT_PARAMS = {"idCard", "mobile", "password", "token"};

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Date time;

    private long costTime;

    private long userId;

    private int role;

    private String ip;

    private String cookies;

    private String headers;

    private String uriPattern;

    private String uri;

    private String method;

    private String controllerInfo;

    private Map<String, String> paramMap;

    private int code;

    private String msg;

    private String resp;

    public void addAllParam(Map<String, String> allParam) {
        if (this.paramMap == null) {
            this.paramMap = Maps.newHashMap();
        }
        this.paramMap.putAll(allParam);
    }

    public void addAllParam(String key, String value) {
        if (this.paramMap == null) {
            this.paramMap = Maps.newHashMap();
        }
        this.paramMap.put(key, value);
    }

    public String toStr() {
        StringBuilder sb = new StringBuilder(1024);
        sb.append("[URI:").append(controllerInfo).append("|").append(uriPattern).append("|").append(method).append("|")
            .append(uri).append("]");
        sb.append("[Param:").append(processSensitiveParam()).append("]");
        sb.append("[Header:").append(headers).append("]");
        sb.append("[Cookie:").append(cookies).append("]");
        sb.append("[ClientIP:").append(ip).append("]");
        sb.append("[Cost:").append(costTime).append("ms]");
        sb.append("[Code:").append(code).append("]");
        sb.append("[Msg:").append(msg).append("]");
        sb.append("[UserId:").append(userId).append("]");
        sb.append("[Resp:").append(resp).append("]");
        return sb.toString();
    }

    private String processSensitiveParam() {
        for (String key : ENCRYPT_PARAMS) {
            String value = paramMap.get(key);
            if (value != null && value.length() >= 6) {
                paramMap.put(key, value.substring(0, value.length() - 5) + "*****");
            }
        }
        return JsonUtil.toJson(paramMap);
    }

    public static AccessInfo getRequestInfo() {
        return REQUEST_INFO.get();
    }

    public static void setRequestInfo(AccessInfo info) {
        REQUEST_INFO.set(info);
    }

    public static void removeRequestInfo() {
        REQUEST_INFO.remove();
    }

    public static void addParams(Map<String, String> allParam) {
        AccessInfo info = getRequestInfo();
        if (info != null) {
            info.addAllParam(allParam);
        }
    }

    public static void addParams(String key, String value) {
        AccessInfo info = getRequestInfo();
        if (info != null) {
            info.addAllParam(key, value);
        }
    }
}
