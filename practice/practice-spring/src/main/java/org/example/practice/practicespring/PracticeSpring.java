package org.example.practice.practicespring;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import javax.annotation.PostConstruct;

import org.example.practice.practicespring.config.TaskConfig;
import org.example.practice.practicespring.spi.Log;
import org.example.practice.practicespring.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties
@EnableFeignClients
@Import({TaskConfig.class})
public class PracticeSpring {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() throws IOException {

        // 1、加载SPI接口 Log，自动查找它的实现类 Log4j、Logback
        ServiceLoader<Log> logServiceLoader = ServiceLoader.load(Log.class);
        Iterator<Log> iterator = logServiceLoader.iterator();
        while (iterator.hasNext()) {
            Log next = iterator.next();
            next.log("JDK SPI");
        }

        // 2、加载表结构和预制数据
        boolean resetDatebase = false;
        if (resetDatebase) {
            List<String> list = FileUtil.readTxtToList(ResourceUtils.getFile("classpath:data/init_db.sql"));
            System.out.println(list);
            jdbcTemplate.batchUpdate(list.toArray(new String[list.size()]));
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(PracticeSpring.class, args);
    }
}
