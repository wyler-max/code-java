package org.example.practice.practicespring.annotation.importAnnotation;

import javax.annotation.Resource;

import org.example.practice.practicespring.PracticeSpring;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试 @Import 注解
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PracticeSpring.class)
public class TestImport {

    @Resource
    private ClassA classA;
    @Resource
    private ClassB classB;
    @Resource
    private ClassC classC;
    @Resource
    private ClassD classD;

    @Test
    public void testA() {
        System.out.println("start");
        classA.printName();
    }

    /**
     * 通过 @Import 注解导入被 @Configuration标记的配置类 TestB，注册成 BeanFactory中的 bean
     */
    @Test
    public void testB() {
        System.out.println("start");
        classB.printName();
    }

    /**
     * 实现 ImportSelector 接口，将 TestC添加到导入数组中
     */
    @Test
    public void testC() {
        System.out.println("start");
        classC.printName();
    }

    /**
     * 实现 ImportBeanDefinitionRegistrar 接口，将 TestD注册到BeanFactory中
     */
    @Test
    public void testD() {
        System.out.println("start");
        classD.printName();
    }
}
