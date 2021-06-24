package org.example.practice.practicespring.annotation.importDir;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author wangyulin
 * @date 2021/6/16
 */
public class SelfImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition testD = new RootBeanDefinition(TestD.class);
        registry.registerBeanDefinition("testD", testD);
    }
}
