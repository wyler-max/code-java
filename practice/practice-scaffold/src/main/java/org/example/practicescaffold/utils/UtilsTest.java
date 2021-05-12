package org.example.practicescaffold.utils;

import org.example.practicescaffold.utils.clazzs.User;

import java.util.Map;

/**
 * 测试工具类
 */
public class UtilsTest {
    public static void main(String[] args) {
        User user = new User();
        user.setName("王二");
        user.setAge(8);
        user.setFriend_1("大龙");
        user.setFriend_1("二龙");

        Map<String, Object> userMap = ObjectReflectUtils.getObjectKeysAndValues((Object) user);
        System.out.println(userMap.get("name"));
        System.out.println(userMap.get("age"));
        System.out.println(userMap.get("friend"));
    }
}
