package lazy.api.impl;

import java.util.Map;

import com.google.common.collect.Maps;

import lazy.api.SupervisorServiceApi;

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
