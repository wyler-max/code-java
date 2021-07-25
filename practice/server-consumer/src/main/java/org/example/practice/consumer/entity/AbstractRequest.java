package org.example.practice.consumer.entity;

import java.util.Optional;

import lombok.Data;

@Data
public abstract class AbstractRequest {
    public String token;

    protected String name;
    private Long number;

    public abstract Optional<String> validParams();
}
