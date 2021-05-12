package org.example.practicescaffold.common.model;

/**
 * 用户信息处理
 */
public class UserContext {
    private static final ThreadLocal<UserInfo> USER_THREAD_LOCAL = new ThreadLocal<>();

    public UserContext() {}

    public static UserInfo getUser() {
        return USER_THREAD_LOCAL.get();
    }

    public static void setUser(UserInfo userInfo) {
        USER_THREAD_LOCAL.set(userInfo);
    }

    public static void release() {
        USER_THREAD_LOCAL.remove();
    }
}
