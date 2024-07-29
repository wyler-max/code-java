package org.example.practice.practicespring.springcontext;

import javax.annotation.Resource;

import org.example.practice.practicespring.PracticeSpring;
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
public class TestEnvironment {

    @Resource
    private Environment env;

    @Test
    public void printEnv() {
        System.out.println(env.getProperty("other.user"));
        System.out.println(env.getProperty("other.password"));
        System.out.println(env.getProperty("other.union"));
    }

}
