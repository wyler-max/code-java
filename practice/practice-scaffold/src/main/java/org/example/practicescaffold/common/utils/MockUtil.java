package org.example.practicescaffold.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.example.practicescaffold.db.jaydefault.model.biz.User;

/**
 * Mock 工具类
 */
public class MockUtil {

    public static Map<String, User> userMap = new HashMap<>();
    static {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("zhangsan");
        user1.setPassword("123");
        user1.setAddr("张三");
        userMap.put("token1", user1);

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("lisi");
        user2.setPassword("123");
        user2.setAddr("李四");
        userMap.put("token2", user2);
    }

    // 模拟根据Token查询用户
    public static User fetchUserByToken(String token) {
        if (userMap.containsKey(token)) {
            return userMap.get(token);
        }
        return null;
    }
}
