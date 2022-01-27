package org.example.practice.practiceknowbox.common.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * 分页工具类
 *
 * @author yijiu.chen
 * @date 2020/04/16
 */
public class ConvertUtil {

    /**
     * 转换
     *
     * @param <S>
     * @param <D>
     * @param list
     * @param copy
     * @param cls
     * @return
     */
    public static <S, D> List<D> convert(List<S> list, BeanCopier copy, Class<D> cls) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        List<D> items = Lists.newArrayListWithCapacity(list.size());
        list.forEach(item -> {
            try {
                D d = cls.getDeclaredConstructor().newInstance();
                copy.copy(item, d, null);
                items.add(d);
            } catch (Exception e) {
                // do nothing
            }
        });
        return items;
    }

    public static <S, D> D convert(S source, BeanCopier copy, Class<D> cls) {
        if (source == null) {
            return null;
        }
        try {
            D d = cls.getDeclaredConstructor().newInstance();
            copy.copy(source, d, null);
            return d;
        } catch (Exception e) {
            // do nothing
        }
        return null;
    }

    public static List<Integer> strToIntList(String numList) {
        return strToIntList(numList, true);
    }

    public static List<Integer> strToIntList(String numList, boolean needPositive) {
        if (StringUtils.isBlank(numList)) {
            return Lists.newArrayList();
        }
        String[] nums = numList.split(",");
        List<Integer> result = Lists.newArrayListWithExpectedSize(nums.length);
        for (String num : nums) {
            int val = NumberUtils.toInt(num);
            if (val > 0) {
                result.add(val);
            }
        }
        return result;
    }
}
