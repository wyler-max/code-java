package org.example.practice.practiceknowbox.common.json.ser;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.time.FastDateFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

/**
 * 默认输出秒
 *
 * @author yijiu.chen
 * @date 2020/04/17
 */
public class DateJsonSerializer extends JsonSerializer<Date> implements ContextualSerializer {

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value != null ? String.valueOf(value.getTime() / 1000) : "null");
    }

    /**
     * 该方法用于Date属性的序列化，正常情况下应该调用{@link #serialize(Date, JsonGenerator, SerializerProvider)}方法
     * 返回时间戳(秒),但是当我们在Date属性上加@JsonFormate时，需要保证返回我们指定的格式。 该方法的实例是共享的所以我们需要返回一个新实例
     *
     * @param prov
     * @param property
     * @return
     * @throws JsonMappingException
     */
    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
        throws JsonMappingException {
        JsonFormat annotation = property.getAnnotation(JsonFormat.class);
        // 时间序列化
        if (annotation != null && annotation.pattern() != null) {
            return new JsonSerializer<Date>() {
                @Override
                public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
                    throws IOException {
                    gen.writeString(
                        value != null ? FastDateFormat.getInstance(annotation.pattern()).format(value) : "null");
                }
            };
        }
        return this;
    }

}
