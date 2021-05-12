package org.example.practicescaffold.common.utils.yaml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "maps")
public class YmlMapStudentUtil {

    private Map<String, String> item1;

    private Map<String, String> item2;

}
