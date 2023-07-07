package org.example.practice.practicespring.springcontext;

import javax.annotation.Resource;

import org.example.practice.practicespring.PracticeSpring;
import org.example.practice.practicespring.springcontext.SelfEmbededValueResolverAware;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试读取配置文件中的内容
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PracticeSpring.class)
public class TestEmbededValueResolverAware {

    @Resource
    private SelfEmbededValueResolverAware selfEmbededValueResolverAware;

    @Test
    public void TestA() {
        System.out.println("start");
        System.out.println(selfEmbededValueResolverAware.getPropertiesValue("db.mysql.user"));
        System.out.println(selfEmbededValueResolverAware.getPropertiesValue("db.mysql.password"));
        System.out.println(selfEmbededValueResolverAware.getPropertiesValue("db.mysql.union"));
    }

}
