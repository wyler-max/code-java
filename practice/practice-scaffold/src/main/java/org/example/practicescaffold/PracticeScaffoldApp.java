package org.example.practicescaffold;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.example.practicescaffold.common.utils.FileUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;

import com.google.common.collect.Lists;

/**
 * SpringBoot 启动入口
 *
 * SpringBootApplication 声明该类是一个SpringBoot引导类 @MapperScan 扫描指定包下的 Mapper接口类，并为其生成实现类
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},
    scanBasePackages = {"org.example.practicescaffold"})
@EnableEurekaClient
@MapperScans({
    @MapperScan(basePackages = {"org.example.practicescaffold.db.jaydefault.dao.biz"},
        sqlSessionFactoryRef = "jayDefaultSqlSessionFactory"),
    @MapperScan(basePackages = {"org.example.practicescaffold.db.jayone.dao.biz"},
        sqlSessionFactoryRef = "jayOneSqlSessionFactory"),
    @MapperScan(basePackages = {"org.example.practicescaffold.db.jaytwo.dao.biz"},
        sqlSessionFactoryRef = "jayTwoSqlSessionFactory"),
    @MapperScan(basePackages = {"org.example.practicescaffold.db.jaythree.dao.biz"},
        sqlSessionFactoryRef = "jayThreeSqlSessionFactory")})
public class PracticeScaffoldApp {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 初始化数据库
     */
    @PostConstruct
    public void init() throws IOException {
        List<String> list = Lists.newArrayList();
        // 重置数据库
        List<String> databases = FileUtil.readTxtToList(ResourceUtils.getFile("classpath:db/databases.sql"));

        // 重置表
        // jay_default
        List<String> jay_default_master =
            FileUtil.readTxtToList(ResourceUtils.getFile("classpath:db/jay_default_master.sql"));
        // jay_one
        List<String> jay_one_master = FileUtil.readTxtToList(ResourceUtils.getFile("classpath:db/jay_one_master.sql"));
        List<String> jay_one_slave1 = FileUtil.readTxtToList(ResourceUtils.getFile("classpath:db/jay_one_slave1.sql"));
        List<String> jay_one_slave2 = FileUtil.readTxtToList(ResourceUtils.getFile("classpath:db/jay_one_slave2.sql"));
        // jay_two
        List<String> jay_two_master1 =
            FileUtil.readTxtToList(ResourceUtils.getFile("classpath:db/jay_two_master1.sql"));
        List<String> jay_two_master2 =
            FileUtil.readTxtToList(ResourceUtils.getFile("classpath:db/jay_two_master2.sql"));
        List<String> jay_two_slave11 =
            FileUtil.readTxtToList(ResourceUtils.getFile("classpath:db/jay_two_slave11.sql"));
        List<String> jay_two_slave12 =
            FileUtil.readTxtToList(ResourceUtils.getFile("classpath:db/jay_two_slave12.sql"));
        List<String> jay_two_slave21 =
            FileUtil.readTxtToList(ResourceUtils.getFile("classpath:db/jay_two_slave21.sql"));
        List<String> jay_two_slave22 =
            FileUtil.readTxtToList(ResourceUtils.getFile("classpath:db/jay_two_slave22.sql"));
        // jay_three
        List<String> jay_three_master1 =
            FileUtil.readTxtToList(ResourceUtils.getFile("classpath:db/jay_three_master1.sql"));
        List<String> jay_three_master2 =
            FileUtil.readTxtToList(ResourceUtils.getFile("classpath:db/jay_three_master2.sql"));

        // 合并SQL
        list.addAll(databases);
        list.addAll(jay_default_master);
        list.addAll(jay_one_master);
        list.addAll(jay_one_slave1);
        list.addAll(jay_one_slave2);
        list.addAll(jay_two_master1);
        list.addAll(jay_two_master2);
        list.addAll(jay_two_slave11);
        list.addAll(jay_two_slave12);
        list.addAll(jay_two_slave21);
        list.addAll(jay_two_slave22);
        list.addAll(jay_three_master1);
        list.addAll(jay_three_master2);
        list = list.stream().map(x -> x.replace(";", "")).collect(Collectors.toList());
        // jdbcTemplate.batchUpdate(list.toArray(new String[0]));
    }

    public static void main(String[] args) {
        SpringApplication.run(PracticeScaffoldApp.class, args);
    }
}
