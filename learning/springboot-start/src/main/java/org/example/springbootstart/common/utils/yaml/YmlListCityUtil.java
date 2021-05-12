package org.example.springbootstart.common.utils.yaml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "city")
public class YmlListCityUtil {

    private List<String> list;
}
