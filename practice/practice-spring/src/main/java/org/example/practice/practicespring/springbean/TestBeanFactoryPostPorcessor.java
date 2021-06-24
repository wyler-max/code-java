package org.example.practice.practicespring.springbean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author wangyulin
 * @date 2021/6/16
 */
@Component
public class TestBeanFactoryPostPorcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory defaultLB = (DefaultListableBeanFactory)beanFactory;
        // 注册Y.class
        GenericBeanDefinition y = new GenericBeanDefinition();
        y.setBeanClass(Y.class);
        defaultLB.registerBeanDefinition("y", y);

        // 修改X.class -> Z.class 修改后：beanName=x，beanClass=xxx...Z
        BeanDefinition x = defaultLB.getBeanDefinition("x");
        x.setBeanClassName("org.example.practice.practicespring.springbean.Z");
    }
}
