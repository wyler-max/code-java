package org.example.practicescaffold.common.utils.yaml;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "city")
public class YmlListCityUtil {

    private List<String> list;
}
