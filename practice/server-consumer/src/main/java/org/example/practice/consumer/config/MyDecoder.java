package org.example.practice.consumer.config;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.stereotype.Component;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;

@Component
public class MyDecoder implements Decoder {
    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        return response;
    }
}
