package org.example.practicescaffold.utils;

import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象工具类
 */
public class ObjectReflectUtils {
    /**
     * 单个对象的所有键值对
     */
    public static Map<String, Object> getObjectKeysAndValues(Object object) {
        Map<String, Object> map = new HashMap<>();
        Class userClazz = object.getClass();
        Field[] fs = userClazz.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true);
            try {
                Object val = f.get(object);
                map.put(f.getName(), val);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 单个对象的某个键值
     */
    public static Object getValueBKey(Object object, String key) {
        Class userClazz = object.getClass();
        Field[] fs = userClazz.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true);
            try {
                if (f.getName().equals(key)) {
                    return f.get(object);
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 多个对象的所有键值对
     */
    public static List<Map<String, Object>> getObjectKeysAndValues(List<Object> objects) {
        List<Map<String, Object>> list = new ArrayList<>();
        for(Object obj: objects) {
            Map<String, Object> map = getObjectKeysAndValues(obj);
            list.add(map);
        }
        return list;
    }

    /**
     * 多个对象的所有键值
     */
    public static List<Object> getValuesByKey(List<Object> objects, String key) {
        List<Object> list = new ArrayList<>();
        for(Object obj: objects) {
            Object val = getValueBKey(obj, key);
            if (ObjectUtils.isNotEmpty(val)) {
                list.add(val);
            }
        }
        return list;
    }


}
