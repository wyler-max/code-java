package org.example.practice.consumer.config;

import java.io.IOException;
import java.lang.reflect.Type;

import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.StringDecoder;

public class MyDecoder extends StringDecoder {

    /**
     * 这里统一处理，根据状态码判断返回正常还是异常的， 200返回正常的，其他状态码直接抛出异常
     */
    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        /*if (response.status() == 200) {
            return super.decode(response, type);
        }*/
        // throw new MyFeignException("解码异常");
        if (response.status() == 404 || response.status() == 204) {
            return Util.emptyValueOf(type);
        }
        if (response.body() == null) {
            return null;
        }
        if (byte[].class.equals(type)) {
            return Util.toByteArray(response.body().asInputStream());
        }
        return super.decode(response, type);
    }
}
