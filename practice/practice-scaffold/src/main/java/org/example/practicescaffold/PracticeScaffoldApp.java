package org.example.practicescaffold;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 启动入口
 *
 * SpringBootApplication 声明该类是一个SpringBoot引导类 MapperScan 扫描指定包下的 Mapper接口类，并为其生成实现类
 *
 * @author wangyl
 */
@SpringBootApplication
@MapperScan(basePackages = "org.example.practicescaffold.db.dao")
public class PracticeScaffoldApp {
    public static void main(String[] args) {
        SpringApplication.run(PracticeScaffoldApp.class, args);
    }
}
