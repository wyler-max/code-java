package org.example.practicescaffold.common.model;

import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.example.practicescaffold.common.utils.JsonUtil;
import org.example.practicescaffold.common.utils.AESUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 业务访问日志
 * 
 * @author yijiu.chen
 * @date 2020/10/16
 */
@Data
public class AccessLogInfo implements Serializable {
    private static final ThreadLocal<AccessLogInfo> REQUEST_INFO = new ThreadLocal<>();

    private static String[] ENCRYPT_PARAMS = {"password", "sessionId"};

    private static String[] ENCRYPT_HEADERS = {"sessionId", "Cookie", "token"};

    private static final long serialVersionUID = 1L;

    private Date time;

    private String appName;

    private long userNumber;// request userNumber

    private long costTime;

    private long userId;

    private String ip;

    private String cookies;

    private Map<String, String> headerMap;

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
            .append(uri).append("]");// 请求controller和路径
        sb.append("[Param:").append(processsSensitiveParam()).append("]");// 请求参数，包括query parameter和request body
        sb.append("[Header:").append(processHeader()).append("]");// 请求头
        sb.append("[Cookie:").append(cookies).append("]");// 请求cookie
        sb.append("[ClientIP:").append(ip).append("]");// 客户端IP
        sb.append("[Cost:").append(costTime).append("ms]");// 服务器处理时间
        sb.append("[Code:").append(code).append("]");// 错误码
        sb.append("[Msg:").append(msg).append("]");// 错误消息
        sb.append("[App:").append(appName).append("]");// 所属服务
        sb.append("[UserNumber:").append(userNumber).append("]");// 参数的用户信息
        sb.append("[UserId:").append(userId).append("]");// 登录的用户信息
        sb.append("[Self:").append(userId == userNumber).append("]");// 是否用户自己操作
        sb.append("[Resp:").append(resp).append("]");// 响应内容
        return sb.toString();
    }

    private String processsSensitiveParam() {
        for (String key : ENCRYPT_PARAMS) {
            String value = paramMap.get(key);
            if (value != null && value.length() >= 6) {
                paramMap.put(key, value.substring(0, value.length() - 5) + "*****");
            }
        }
        return JsonUtil.toJson(paramMap);
    }

    private String processHeader() {
        for (String key : ENCRYPT_HEADERS) {
            String value = headerMap.get(key);
            if (StringUtils.isNotBlank(value)) {
                headerMap.put(key, AESUtils.encrypt(value));
            }
        }
        return JsonUtil.toJson(headerMap);
    }

    public static AccessLogInfo getRequestInfo() {
        return REQUEST_INFO.get();
    }

    public static void setRequestInfo(AccessLogInfo info) {
        REQUEST_INFO.set(info);
    }

    public static void removeRequestInfo() {
        REQUEST_INFO.remove();
    }

    public static void addParams(Map<String, String> allParam) {
        AccessLogInfo info = getRequestInfo();
        if (info != null) {
            info.addAllParam(allParam);
        }
    }

    public static void addParams(String key, String value) {
        AccessLogInfo info = getRequestInfo();
        if (info != null) {
            info.addAllParam(key, value);
        }
    }
}
