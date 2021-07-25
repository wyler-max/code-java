package org.example.practice.consumer.entity;

import java.util.Optional;

import lombok.Data;

@Data
public class DefaultReq extends AbstractRequest {

    private String func;

    @Override
    public Optional<String> validParams() {
        return Optional.empty();
    }
}
