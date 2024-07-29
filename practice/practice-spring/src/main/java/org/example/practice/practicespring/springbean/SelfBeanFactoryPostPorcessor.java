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
public class SelfBeanFactoryPostPorcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // bean工厂
        DefaultListableBeanFactory defaultLB = (DefaultListableBeanFactory)beanFactory;

        // 注册 Y.class => y(beanName)
        GenericBeanDefinition gbd = new GenericBeanDefinition();
        gbd.setBeanClass(Y.class);
        defaultLB.registerBeanDefinition("y", gbd);

        // 将已注册的 X.class => x(beanName) 修改为 Z.class => x(beanName)
        BeanDefinition bd = defaultLB.getBeanDefinition("x");
        bd.setBeanClassName("org.example.practice.practicespring.springbean.Z");
    }
}
