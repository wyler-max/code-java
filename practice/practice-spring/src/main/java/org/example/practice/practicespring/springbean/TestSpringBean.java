package org.example.practice.practicespring.springbean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wangyulin
 * @date 2021/6/16
 */
public class TestSpringBean {

    public static void main(String[] args) {
        System.out.println("start");
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        // 将AppConfig注册为bean
        ac.register(AppConfig.class);
        // 刷新、生成 BeanDefinition 等操作
        ac.refresh();

        System.out.println("修改 BeanDefinition");

        // SelfBeanFactoryPostPorcessor 中注册了X.class；将已注册的 X.class => x(beanName) 修改为 Z.class => x(beanName)
        // 最终结果
        // X.class
        // Y.class => y
        // Z.class => x

        // 已注入，正常打印
        System.out.println(ac.getBean(Y.class));
        // 名为Z，实为X
        System.out.println(ac.getBean(Z.class));
        // 因为名为X的 BeanDefinition已经改名为Z，所以查找不到
        System.out.println(ac.getBean(X.class));
    }
}
