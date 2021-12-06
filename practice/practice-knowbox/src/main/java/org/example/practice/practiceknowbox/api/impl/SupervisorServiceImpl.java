package org.example.practice.practiceknowbox.api.impl;

import java.util.Map;

import org.example.practice.practiceknowbox.api.SupervisorServiceApi;

import com.google.common.collect.Maps;

/**
 *
 */
public class SupervisorServiceImpl implements SupervisorServiceApi {

    private static Map<String, Long> superMap = Maps.newHashMap();
    static {
        superMap.put("技术部", 10001L);
        superMap.put("产品部", 10002L);
        superMap.put("运营部", 10003L);
    }

    @Override
    public Long getSupervisor(String department) {
        return superMap.getOrDefault(department, 10000L);
    }
}
