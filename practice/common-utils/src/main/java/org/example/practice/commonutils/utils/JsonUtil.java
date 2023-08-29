package org.example.practice.commonutils.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class JsonUtil {
    public static final ObjectMapper MAPPER = new ObjectMapper();
    private static final TypeReference<Map<String, Object>> MAP_TYPE_REFERENCE =
        new TypeReference<Map<String, Object>>() {};

    static {
        MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        MAPPER.setSerializationInclusion(Include.NON_NULL);
    }

    /**
     * JSON转对象
     *
     * @param <T>
     * @param content
     * @param c
     * @return
     */
    public static <T> T toObject(String content, Class<T> c) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try {
            return MAPPER.readValue(getSafeJson(content), c);
        } catch (Exception e) {
            log.error("convert json:{} to class:{} error", content, c.getName(), e);
        }
        return null;
    }

    /**
     * JSON转其它类型
     *
     * @param <T>
     * @param content
     * @param typeReference
     * @return
     */
    public static <T> T toObject(String content, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try {
            return MAPPER.readValue(getSafeJson(content), typeReference);
        } catch (Exception e) {
            log.error("convert json:{} to typeReference error", content, e);
        }
        return null;
    }

    /**
     * JSON转LIST
     *
     * @param <T>
     * @param content
     * @param c
     * @return
     */
    public static <T> List<T> toList(String content, Class<T> c) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try {
            return MAPPER.readValue(getSafeJson(content),
                MAPPER.getTypeFactory().constructParametricType(List.class, c));
        } catch (Exception e) {
            log.error("convert json:{} to list class:{} error", content, c.getName(), e);
        }
        return Lists.newArrayList();
    }

    /**
     * JSON转MAP
     *
     * @param <K>
     * @param <V>
     * @param content
     * @param key
     * @param val
     * @return
     */
    public static <K, V> Map<K, V> toMap(String content, Class<K> key, Class<V> val) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try {
            return MAPPER.readValue(getSafeJson(content),
                MAPPER.getTypeFactory().constructParametricType(Map.class, key, val));
        } catch (Exception e) {
            log.error("convert json:{} to map key:{},value:{} error", content, key.getName(), val.getName(), e);
        }
        return Maps.newHashMap();
    }

    /**
     * 对象转JSON
     *
     * @param value
     * @return
     */
    public static String toJson(Object value) {
        if (null == value) {
            return null;
        }
        // 特殊处理
        if (value instanceof String) {
            return (String)value;
        }
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 取安全的Json字符串
     *
     * @param json
     * @return
     */
    public static String getSafeJson(String json, boolean filterControlChar) {
        if (json == null || json.length() == 0) {
            return json;
        }
        StringBuilder sb = new StringBuilder(json.length());
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c < 32 || c == 127) {
                if (!filterControlChar) {
                    if (c != '\r' && c != '\n' && c != '\t') {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            // UTF-8 blank
            if (c == '\u2028' || c == '\u2029') {
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static Map<String, Object> toMap(Object object) {
        return MAPPER.convertValue(object, MAP_TYPE_REFERENCE);
    }

    private static String getSafeJson(String json) {
        return getSafeJson(json, true);
    }

}
