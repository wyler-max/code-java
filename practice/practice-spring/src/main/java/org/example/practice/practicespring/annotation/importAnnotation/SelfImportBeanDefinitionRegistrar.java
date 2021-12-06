package org.example.practice.practicespring.annotation.importAnnotation;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义 ImportBeanDefinitionRegistrar 接口的实现类，重写 registerBeanDefinitions 方法
 */
public class SelfImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * 将 TestD 注册到 BeanFactory中
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition testD = new RootBeanDefinition(ClassD.class);
        registry.registerBeanDefinition("testD", testD);
    }
}
