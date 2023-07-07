package org.example.practicescaffold.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author yijiu.chen
 * @date 2020/04/16
 */
@Slf4j
public class IpUtil {
    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址, 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *
     * @return ip
     */
    public static String getRealIP(HttpServletRequest request) {
        // 优先取X-Real-IP
        // 自建openresty
        String ip = request.getHeader("X-Real-IP");

        // 钉钉云slb
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("proxy-client-ip");
        }
        // 阿里云slb
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("remoteip");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }

        return ip;
    }

    public static boolean isSelfCall(HttpServletRequest request) {
        return "127.0.0.1".equals(getRealIP(request));
    }

    public static boolean isInnerCall(HttpServletRequest request) {
        String ip = getRealIP(request);
        // 10.0.0.0-10.255.255.255 172.16.0.0-172.31.255.255 192.168.0.0-192.168.255.255
        return "127.0.0.1".equals(ip) || ip.startsWith("192.168.") || ip.startsWith("10.")
            || ("172.16.0.0".compareTo(ip) <= 0 && "172.31.255.255".compareTo(ip) >= 0);
    }

    public static boolean isInnerCall(String ip) {
        // 10.0.0.0-10.255.255.255 172.16.0.0-172.31.255.255 192.168.0.0-192.168.255.255
        return "127.0.0.1".equals(ip) || ip.startsWith("192.168.") || ip.startsWith("10.")
            || ("172.16.0.0".compareTo(ip) <= 0 && "172.31.255.255".compareTo(ip) >= 0);
    }

    public static String getHostIp() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface)allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = (InetAddress)addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            log.error("getHostIp", e);
        }
        return null;
    }
}
