package org.example.practice.provider.api;

public class UserApiTestDriver {

    public static void main(String[] args) {
        UserApi userApi = new UserApi.Default();
        System.out.println(userApi.getUser1());
        System.out.println(userApi.getUser2());
    }
}
