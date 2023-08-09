package org.example.practice.practicespring;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试读取配置文件中的内容
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PracticeSpring.class)
public class TestPropertiesConfig {

    @Resource
    private Environment env;

    @Test
    public void TestA() {
        System.out.println(env.getProperty("db.mysql.user"));
        System.out.println(env.getProperty("db.mysql.password"));
        System.out.println(env.getProperty("db.mysql.union"));

        System.out.println("==========");
    }

}
