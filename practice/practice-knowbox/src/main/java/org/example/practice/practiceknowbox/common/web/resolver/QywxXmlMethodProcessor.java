package org.example.practice.practiceknowbox.common.web.resolver;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
import org.example.practice.practiceknowbox.common.model.AccessInfo;
import org.example.practice.practiceknowbox.common.model.QywxParam;
import org.example.practice.practiceknowbox.common.util.JsonUtil;
import org.example.practice.practiceknowbox.common.web.annotation.QywxRequestBody;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangshuai
 * @date 2021/10/27 4:09 下午
 */
@Slf4j
public class QywxXmlMethodProcessor extends AbstractMessageConverterMethodArgumentResolver {

    private ApplicationContext applicationContext;

    /**
     * 使用该构造方法可以保证在解析参数过程中，执行自定义的RequestBodyAdvice和ResponseBodyAdvice
     *
     * <pre class="code">
     *
     * </pre>
     *
     * @param converters
     * @param requestResponseBodyAdvice
     */
    public QywxXmlMethodProcessor(List<HttpMessageConverter<?>> converters, List<Object> requestResponseBodyAdvice,
        ApplicationContext context) {
        super(converters, requestResponseBodyAdvice);
        this.applicationContext = context;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(QywxRequestBody.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        // Assert.state(servletRequest != null, "No HttpServletRequest");
        // // HttpServletRequest的便捷类，可以方便提取一些信息
        // ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(servletRequest);
        // // HandlerMethodArgumentResolver.readWithMessageConverters(..)中使用HttpMessageConverter接口实现参数的解析及转换
        // // AbstractJackson2HttpMessageConverter是一个与Jackson序列化相关的抽象类
        // Object arg = readWithMessageConverters(inputMessage, parameter, parameter.getNestedGenericParameterType());
        // if (arg == null && checkRequired(parameter)) {
        // throw new HttpMessageNotReadableException("Required request body is missing: " +
        // parameter.getExecutable().toGenericString(), inputMessage);
        // }
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        try {
            String msgSignature = request.getParameter("msg_signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String token = (String)request.getAttribute("token");
            String encodingAesKey = (String)request.getAttribute("encodingAesKey");
            InputStreamReader input = new InputStreamReader(request.getInputStream());
            String body = new BufferedReader(input).lines().collect(Collectors.joining(System.lineSeparator()));
            SAXReader reader = new SAXReader();
            Document beforeEncodeDoc = reader.read(new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8)));
            Map<String, String> beforeEncodeMap = beforeEncodeDoc.getRootElement().content().stream()
                .collect(Collectors.toMap(Node::getName, Node::getText, (v1, v2) -> v1));
            // corpId或者suiteId
            String toUserName = beforeEncodeMap.get("ToUserName");
            log.debug("当前corpId is:{},before encrypt is:{}", toUserName, body);
            WXBizMsgCrypt wxCpt = new WXBizMsgCrypt(token, encodingAesKey, toUserName);
            String s = wxCpt.DecryptMsg(msgSignature, timestamp, nonce, body);
            Document document = reader.read(new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8)));
            // TODO 这是xml的root节点,先只解析一层xml格式，后续优化
            Map<String, String> contentMap =
                document.getRootElement().content().stream().collect(Collectors.toMap(Node::getName, Node::getText));
            QywxParam param = JsonUtil.toObject(JsonUtil.toJson(contentMap), QywxParam.class);
            if (param == null) {
                log.info("xml:{},解析后:{},", s, contentMap);
                return null;
            }
            param.setOthers(contentMap);
            AccessInfo.addParams(contentMap);
            return param;
        } catch (DocumentException | AesException e) {
            log.error("企业微信回调异常", e);
        }
        return null;
    }

    protected boolean checkRequired(MethodParameter parameter) {
        QywxRequestBody requestBody = parameter.getParameterAnnotation(QywxRequestBody.class);
        return (requestBody != null && requestBody.required() && !parameter.isOptional());
    }

    public static void main(String[] args) throws AesException, DocumentException {
        String body = "<xml><ToUserName><![CDATA[wwf5c7f8863170a083]]></ToUserName><Encrypt><![CDATA[HFVX9ns7"
            + "/fjLuF6Ujrn/dD/ArcBJqqcf7r4UX4RG05+Rz6U9WhLAzbaydhNEdkGpNdWeyI+RyK+K3b5rWXZB2l1AaJnc164N1"
            + "+grcX2B0pn4TZB0w02CdpGXF5FMiD3bnsMUZkMmd4zh2ze9bXovo4xsR/uAwvY3rlnT0e3tI/j59wo9CLsoCiWtmd80kj+VZ"
            + "/3vMH8aUHRWe2V0bG2Vv8zE60QMeobVb0wEKml8mc2XAhy3hA04WhEJicTbhmldkONHvSt5gjweyDg7kixushHl3p3L2OA8rpje6gvxJnCtxYcpIrNtWORFVMRZcvKka2Etjdr8sa3yu4wezjOHo9wNEbRyNdhYZ30c5AI/vaYWWR+nincvd5K9OA7SxJN8]]></Encrypt><AgentID><![CDATA[]]></AgentID></xml>";

        WXBizMsgCrypt wxCpt = new WXBizMsgCrypt("yF6ZmqdzvzZ1juNbnT1sPsL3o61vWJ",
            "47MPLBpYA9X5Pq1omToh2xmf66iB8MrTuwYCjP7XWSx", "wwf5c7f8863170a083");
        String s = wxCpt.DecryptMsg("0e57c4237922eeecfd95c82b1343d4388a456477", "1636080882", "1635780598", body);
        System.out.println(s);
        String body1 = "<xml><ToUserName><![CDATA[ww212ba83bdf58350a]]></ToUserName><Encrypt><![CDATA"
            + "[O6Ow8pjQulcnf1WhSzLVN8LMPEJOdicVvh3YWoQFC/aX4RbKSsf/dEvdl3TNb0UyXw"
            + "+KHueMFYDJ3cmGYzYAUxpczHvekwIjWtz5ODvjcK0JZRrbIqYSeOz0zZTrK8ASh8MoMMXDqFDd9baHsLnd92irek1Ufl7L2u3fQjYPi086g8rcUNSSXDY/4ZauPGtwVj2+vHlAgYcG5Is3HlD7DfSc2TRm/TD+E3poL1+pjvbkMqkP+a80LDCJklmGWcAWBth5wKkxw4G/K3B+43w5QJJqfUf5VQtN2oxqwMT7UFl1yABzk+/0NPAxRy8NLKkMF7yRJvesE1HiwLuJnCnmuiTL/BItHt/+rb/s4mu6qQqwfPduS6NDmtyB9GsRoahF]]></Encrypt><AgentID><![CDATA[]]></AgentID></xml>";
        WXBizMsgCrypt wxCpt1 = new WXBizMsgCrypt("yF6ZmqdzvzZ1juNbnT1sPsL3o61vWJ",
            "47MPLBpYA9X5Pq1omToh2xmf66iB8MrTuwYCjP7XWSx", "ww212ba83bdf58350a");
        String s1 = wxCpt1.DecryptMsg("c9665b1edd7b7ba0e04948fd4b02d6f2e4c2dbf1", "1636080882", "1635780474", body1);
        System.out.println(s1);

        SAXReader reader = new SAXReader();
        Document beforeEncodeDoc = reader.read(new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8)));
        Map<String, String> beforeEncodeMap =
            beforeEncodeDoc.getRootElement().content().stream().collect(Collectors.toMap(Node::getName, Node::getText));
        System.out.println(JsonUtil.toJson(beforeEncodeMap));
        System.out.println(beforeEncodeMap.get("ToUserName"));
    }
}
