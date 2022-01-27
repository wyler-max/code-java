package org.example.practice.practiceknowbox.common.json.deser;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import org.example.practice.practiceknowbox.common.util.JsonUtil;

/**
 * 支持对象或JSON string反序列化
 * <pre>
 * 比如xx字段为对象
 * "xx":"{\"id\":1}"
 * "xx":{id:1}
 * </pre>
 *
 * @author chenyijiu
 * @date 2019/11/05
 */
public class JsonStringOrDefaultDeserializer extends JsonDeserializer<Object> implements ContextualDeserializer {

    private JavaType javaType;

    public JsonStringOrDefaultDeserializer() {

    }

    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.deser.ContextualDeserializer#createContextual(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.BeanProperty)
     */
    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property)
        throws JsonMappingException {
        // 获取类型
        this.javaType = property.getType();
        return this;
    }

    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
     */
    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (p.hasTokenId(JsonTokenId.ID_STRING)) {
            return JsonUtil.MAPPER.readValue(p.getText(), javaType);
        }
        // 原来的
        return JsonUtil.MAPPER.readValue(p, javaType);
    }

}
