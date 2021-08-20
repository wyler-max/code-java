package org.example.practicescaffold.common.utils.yaml;

import java.util.List;

import org.example.practicescaffold.db.jaydefault.model.biz.Student;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "student")
public class YmlListStudentUtil {

    private List<Student> list;
}
