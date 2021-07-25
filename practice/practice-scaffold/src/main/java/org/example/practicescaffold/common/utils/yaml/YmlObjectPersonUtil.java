package org.example.practicescaffold.common.utils.yaml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "person2")
public class YmlObjectPersonUtil {

    private String name;
    private int age;
    private String addr;
}
