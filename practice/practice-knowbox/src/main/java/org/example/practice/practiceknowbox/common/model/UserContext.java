package org.example.practice.practiceknowbox.common.model;

/**
 * @author yijiu.chen
 * @date 2020/04/16
 */
public class UserContext {
    private static final ThreadLocal<User> USER_INFO_THREAD_LOCAL = new ThreadLocal<>();

    public UserContext() {}

    public static User getUserInfo() {
        return USER_INFO_THREAD_LOCAL.get();
    }

    public static void setUserInfo(User userInfo) {
        USER_INFO_THREAD_LOCAL.set(userInfo);
    }

    public static void release() {
        USER_INFO_THREAD_LOCAL.remove();
    }
}
