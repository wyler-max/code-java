package org.example.springbootstart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * SpringBoot 启动入口
 *
 * SpringBootApplication 声明该类是一个SpringBoot引导类
 * MapperScan 扫描指定包下的 Mapper接口类，并为其生成实现类
 */
@SpringBootApplication
/*@MapperScans({
        @MapperScan(basePackages = "org.example.springbootstart.db.dao.auto"),
        @MapperScan(basePackages = "org.example.springbootstart.db.dao.biz"),
})*/
@MapperScan(basePackages = "org.example.springbootstart.db.dao.biz")
public class SpringBootStartApp {

    private static ConfigurableApplicationContext ac;

    //main是java程序的入口
    public static void main(String[] args) {
        //run方法 表示运行SpringBoot的引导类  run参数就是SpringBoot引导类的字节码对象
        SpringApplication.run(SpringBootStartApp.class, args);
    }
}
