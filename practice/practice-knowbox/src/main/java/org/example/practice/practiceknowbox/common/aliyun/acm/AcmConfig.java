package org.example.practice.practiceknowbox.common.aliyun.acm;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.example.practice.practiceknowbox.common.util.DateUtil;
import org.example.practice.practiceknowbox.common.util.JsonUtil;

import com.alibaba.edas.acm.ConfigService;
import com.alibaba.edas.acm.exception.ConfigException;
import com.alibaba.edas.acm.listener.ConfigChangeListener;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 阿里云ACM封装，先仅是properties类型文件处理，注意自动装载类型配置删除不会清理内存数据！！
 *
 * @author yijiu.chen
 * @date 2020/06/05
 */
@Data
@Slf4j
public class AcmConfig {

    public static String DEFAULT_SPLIT = ",";
    public static final Splitter STRING_SPLIT = Splitter.on(DEFAULT_SPLIT).trimResults().omitEmptyStrings();

    private String endpoint;
    private String namespace;
    private String accessKey;
    private String secretKey;
    private String group;
    private long timeout = 6000;

    /**
     * 默认分组
     *
     * @param dataId
     * @param listener
     * @return
     */
    public boolean addConfigListener(String dataId, MapPropertiesListener listener) {
        return addConfigListener(dataId, group, listener);
    }

    /**
     *
     * @param dataId
     * @param group
     * @param listener
     * @return
     */
    public boolean addConfigListener(String dataId, String group, MapPropertiesListener listener) {
        Properties properties = new Properties();
        properties.put("endpoint", endpoint);
        properties.put("namespace", namespace);
        properties.put("accessKey", accessKey);
        properties.put("secretKey", secretKey);
        ConfigService.init(properties);
        ConfigService.addListener(dataId, group, listener);
        try {
            // 同步加载测试
            return ConfigService.getConfig(dataId, group, timeout) != null;
        } catch (ConfigException e) {
            log.error("addConfigListener", e);
        }
        return false;
    }

    /**
     * 自定义listener
     *
     * @param dataId
     * @param listener
     * @return
     */
    public boolean addConfigListener(String dataId, ConfigChangeListener listener) {
        Properties properties = new Properties();
        properties.put("endpoint", endpoint);
        properties.put("namespace", namespace);
        properties.put("accessKey", accessKey);
        properties.put("secretKey", secretKey);
        ConfigService.init(properties);
        ConfigService.addListener(dataId, group, listener);
        try {
            // 同步加载测试
            return ConfigService.getConfig(dataId, group, timeout) != null;
        } catch (ConfigException e) {
            log.error("addConfigListener", e);
        }
        return false;
    }

    /**
     * 自动载入静态变量，变量名以VAR_开头
     *
     * @param cls
     * @param dataId
     * @param conf
     */
    public static void autoLoadVariable(Class<?> cls, String dataId, Map<String, String> conf) {
        Field[] fields = cls.getFields();
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)//
                && field.getName().startsWith("VAR_")) {
                try {
                    String var = conf.get(field.getName());
                    if (StringUtils.isNotBlank(var)) {
                        if (field.getType().isAssignableFrom(String.class)) {
                            field.set(null, var);
                        } else if (field.getType().isAssignableFrom(boolean.class)) {
                            field.set(null, !("0".equals(var) || "false".equals(var)));
                        } else if (field.getType().isAssignableFrom(int.class)) {
                            field.set(null, NumberUtils.toInt(var));
                        } else if (field.getType().isAssignableFrom(long.class)) {
                            field.set(null, NumberUtils.toLong(var));
                        } else if (field.getType().isArray()) {
                            String[] values = var.split(",");
                            if (field.getType().equals(String[].class)) {
                                field.set(null, values);
                            } else if (field.getType().equals(int[].class)) {
                                field.set(null, convertStrToInt(values));
                            } else if (field.getType().equals(long[].class)) {
                                field.set(null, convertStrToLong(values));
                            }
                        } else if (field.getType().isAssignableFrom(Map.class)) {
                            Object map = JsonUtil.toObject(var, Map.class);
                            if (map != null) {
                                field.set(null, map);
                            }
                        } else if (field.getType().isAssignableFrom(Set.class)) {
                            Type gt = field.getGenericType();
                            if (gt instanceof ParameterizedType) {
                                ParameterizedType pt = (ParameterizedType)gt;
                                if (pt.getActualTypeArguments() != null && pt.getActualTypeArguments().length == 1) {
                                    Class<?> gcls = (Class<?>)pt.getActualTypeArguments()[0];
                                    field.set(null,
                                        Sets.newHashSet(Objects.requireNonNull(JsonUtil.toList(var, gcls))));
                                }

                            }
                        } else if (field.getType().isAssignableFrom(List.class)) {
                            Type gt = field.getGenericType();
                            if (gt instanceof ParameterizedType) {
                                ParameterizedType pt = (ParameterizedType)gt;
                                if (pt.getActualTypeArguments() != null && pt.getActualTypeArguments().length == 1) {
                                    Class<?> gcls = (Class<?>)pt.getActualTypeArguments()[0];
                                    field.set(null, JsonUtil.toList(var, gcls));
                                }
                            }
                        } else if (field.getType().isAssignableFrom(Date.class)) {
                            Date date = null;
                            if (var.length() == 19) {
                                date = DateUtil.safeParse(DateUtil.DATETIME, var);
                            } else if (var.length() == 10) {
                                date = DateUtil.safeParse(DateUtil.DATE, var);
                            } else if (var.length() == 16) {
                                date = DateUtil.safeParse(DateUtil.DATETIME_WITHOUT_SECOND, var);
                            }
                            if (date != null) {
                                field.set(null, date);
                            }
                        } else if (field.getType().isAssignableFrom(BigDecimal.class)) {
                            field.set(null, new BigDecimal(var));
                        } else {
                            log.error("autoLoadVariable {} fail, var:{} value:{}", dataId, field.getName(), var);
                        }
                    }
                    log.info("autoLoadVariable {} load var:{} value:{}", dataId, field.getName(), var);
                } catch (IllegalArgumentException e) {
                    log.error("autoLoadVariable", e);
                } catch (IllegalAccessException e) {
                    log.error("autoLoadVariable", e);
                }
            }
        }
    }

    public static boolean greaterThan(int val, int[] array) {
        for (int v : array) {
            if (v > val) {
                return true;
            }
        }
        return false;
    }

    private static int[] convertStrToInt(String[] values) {
        int[] ret = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            ret[i] = NumberUtils.toInt(values[i]);
        }
        return ret;
    }

    private static long[] convertStrToLong(String[] values) {
        long[] ret = new long[values.length];
        for (int i = 0; i < values.length; i++) {
            ret[i] = NumberUtils.toLong(values[i]);
        }
        return ret;
    }
}
