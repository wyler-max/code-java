package org.example.practice.practicespring.config.feign;

import static java.lang.String.format;

import java.lang.reflect.Type;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;

public class MyEncoder implements Encoder {
    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        if (bodyType == String.class) {
            template.body(object.toString());
        } else if (bodyType == byte[].class) {
            template.body((byte[])object, null);
        } else if (object != null) {
            throw new EncodeException(format("%s is not a type supported by this encoder.", object.getClass()));
        }
    }
}
