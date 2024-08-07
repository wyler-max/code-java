package org.example.practice.practicespring.springcontext;

import javax.annotation.Resource;

import org.example.practice.practicespring.PracticeSpring;
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
        System.out.println("EmbededValueResolverAware read properties");
        System.out.println(selfEmbededValueResolverAware.getPropertiesValue("other.user"));
        System.out.println(selfEmbededValueResolverAware.getPropertiesValue("other.password"));
        System.out.println(selfEmbededValueResolverAware.getPropertiesValue("other.union"));
    }

}
