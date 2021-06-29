package org.example.practice.consumer.config;

import feign.Response;

public class MyFeignException extends RuntimeException {
    private String methodKey;
    private Response response;

    MyFeignException(String msg) {
        super(msg);
    }

    MyFeignException(String methodKey, Response response) {
        this.methodKey = methodKey;
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public String getMethodKey() {
        return methodKey;
    }
}
