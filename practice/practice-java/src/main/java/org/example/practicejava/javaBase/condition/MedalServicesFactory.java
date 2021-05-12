package org.example.practicejava.javaBase.condition;

import java.util.HashMap;
import java.util.Map;

/**
 * 勋章服务工产类
 */
public class MedalServicesFactory {
    private static final Map<String, IMedalService> map = new HashMap<>();
    static {
        map.put("guard", new GuardMedalServiceImpl());
        map.put("vip", new VipMedalServiceImpl());
        map.put("guest", new GuestMedalServiceImpl());
    }
    public static IMedalService getMedalService(String medalType) {
        return map.get(medalType);
    }
}
