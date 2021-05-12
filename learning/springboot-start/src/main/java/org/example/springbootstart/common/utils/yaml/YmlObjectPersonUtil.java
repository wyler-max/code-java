package org.example.springbootstart.common.utils.yaml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "person2")
public class YmlObjectPersonUtil {

    private String name;
    private int age;
    private String addr;
}
