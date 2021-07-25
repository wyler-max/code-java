package org.example.practicescaffold.common.utils.yaml;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "maps")
public class YmlMapStudentUtil {

    private Map<String, String> item1;

    private Map<String, String> item2;

}
