package org.example.practice.practiceknowbox.common.web.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.example.practice.practiceknowbox.common.aes.AesException;
import org.example.practice.practiceknowbox.common.aes.WXBizMsgCrypt;
import org.example.practice.practiceknowbox.common.util.HttpUtil;
import org.example.practice.practiceknowbox.common.web.annotation.QywxCallbackCheck;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangshuai
 * @date 2021/11/4 4:39 下午
 */
@Slf4j
public class QywxInterceptor extends HandlerInterceptorAdapter implements Ordered {

    @Value("${qywx.corpId}")
    private String corpId;

    @Value("${qywx.appid}")
    private String appid;

    @Value("${qywx.token}")
    private String token;

    @Value("${qywx.encodingAesKey}")
    private String encodingAesKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            if (!(handler instanceof HandlerMethod)) {
                return true;
            }
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Method method = handlerMethod.getMethod();
            // 只支持方法级别
            QywxCallbackCheck annotation = method.getAnnotation(QywxCallbackCheck.class);
            if (annotation == null) {
                return true;
            }
            String msgSignature = request.getParameter("msg_signature");
            String receiveId = corpId;
            // 说明是微信的消息
            int platform = 0;
            // 说明是微信平台
            if (StringUtils.isEmpty(msgSignature)) {
                msgSignature = request.getParameter("signature");
                receiveId = appid;
                platform = 1;
            }
            String echostr = request.getParameter("echostr");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");

            WXBizMsgCrypt wxCpt = new WXBizMsgCrypt(token, encodingAesKey, receiveId);
            // 说明是验证接口以及加解密的Get请求
            if (!StringUtils.isEmpty(echostr) && HttpMethod.GET.name().equalsIgnoreCase(request.getMethod())) {
                String result = wxCpt.VerifyURL(msgSignature, timestamp, nonce, echostr, platform);
                log.info("verify url succeed echoStr:{}", result);
                HttpUtil.flushResponse(response, result, HttpStatus.OK.value());
                return false;
            }
            request.setAttribute("token", token);
            request.setAttribute("encodingAesKey", encodingAesKey);
            return true;
        } catch (IOException e) {
            log.error("qywx解析body流失败", e);
        } catch (AesException e) {
            log.error("qywx加解密验证失败", e);
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    public static void main(String[] args) throws AesException {
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt("yF6ZmqdzvzZ1juNbnT1sPsL3o61vWJ",
            "47MPLBpYA9X5Pq1omToh2xmf66iB8MrTuwYCjP7XWSx", "ww01e0bc5de633e6cd");
        String verifyMsgSig = "f1fa334cc339a6d1ee2c361101bc1f935e32728f";
        String timeStamp = "1638266340";
        String nonce = "1638201027";
        String echoStr = "CxtR5KirbLcq6tnjaW8YAxkkur2xd33Y2arjzGX5upj1patMXVKlySvXE/VyrTtwDyUU55LTFhiHxjNh0NC0fg==";
        String s = wxcpt.VerifyURL(verifyMsgSig, timeStamp, nonce, echoStr);
        System.out.println(s);
    }
}
