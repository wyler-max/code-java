package org.example.practicescaffold.common.utils.yaml;

import org.example.practicescaffold.db.model.biz.Student;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "student")
public class YmlListStudentUtil {

    private List<Student> list;
}
