package org.example.practice.provider.api;

import org.example.practice.provider.pojo.User;

public interface UserApi {
    User getUser1();

    User getUser2();

    public static class Default implements UserApi {

        @Override
        public User getUser1() {
            return new User(1001, "user1001", 11);
        }

        @Override
        public User getUser2() {
            return new User(1002, "user1002", 12);
        }
    }
}
