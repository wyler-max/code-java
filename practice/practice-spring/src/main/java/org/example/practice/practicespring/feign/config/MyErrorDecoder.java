package org.example.practice.practicespring.feign.config;

import feign.Response;
import feign.codec.ErrorDecoder;

public class MyErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return new MyFeignException(methodKey, response);
    }
}
