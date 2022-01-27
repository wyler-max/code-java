package org.example.practice.practiceknowbox.common.web.resolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.example.practice.practiceknowbox.common.aes.AesException;
import org.example.practice.practiceknowbox.common.aes.WXBizMsgCrypt;
import org.example.practice.practiceknowbox.common.model.QywxParam;
import org.example.practice.practiceknowbox.common.util.JsonUtil;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangshuai
 * @date 2021/10/27 2:48 下午
 */
@Slf4j
public class QywxXmlHttpMessageConvert implements HttpMessageConverter<QywxParam> {

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return true;
    }

    /**
     * 若需要返回xml格式数据给企业微信则实现该方法
     *
     * @param clazz
     * @param mediaType
     * @return
     */
    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Collections.singletonList(MediaType.TEXT_XML);
    }

    @Override
    public QywxParam read(Class<? extends QywxParam> clazz, HttpInputMessage inputMessage)
        throws IOException, HttpMessageNotReadableException {
        ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest)inputMessage;
        HttpServletRequest request = serverHttpRequest.getServletRequest();
        try {
            String msgSignature = request.getParameter("msg_signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String token = (String)request.getAttribute("token");
            String encodingAesKey = (String)request.getAttribute("encodingAesKey");
            String suiteId = (String)request.getAttribute("suiteId");
            WXBizMsgCrypt wxCpt = new WXBizMsgCrypt(token, encodingAesKey, suiteId);
            String s = wxCpt.DecryptMsg(msgSignature, timestamp, nonce,
                new BufferedReader(new InputStreamReader(inputMessage.getBody())).lines()
                    .collect(Collectors.joining(System.lineSeparator())));
            log.debug("企业微信回调接口解密后数据:{}", s);
            SAXReader reader = new SAXReader();
            Document document = reader.read(s);
            // 这是xml的节点
            Map<String, String> contentMap = document.getRootElement().content().stream()
                .filter(p -> !p.getText().contains("\n")).collect(Collectors.toMap(Node::getName, Node::getText));
            QywxParam param = JsonUtil.toObject(JsonUtil.toJson(contentMap), clazz);
            param.setOthers(contentMap);
            return param;
        } catch (DocumentException | AesException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void write(QywxParam param, MediaType contentType, HttpOutputMessage outputMessage)
        throws IOException, HttpMessageNotWritableException {
        // canWrite方法返回false，暂不支持该方法。
        throw new UnsupportedOperationException("不支持写入xml");
    }
}
