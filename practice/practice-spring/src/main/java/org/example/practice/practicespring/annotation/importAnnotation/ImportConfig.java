package org.example.practice.practicespring.annotation.importAnnotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 测试 @Import 注解
 *
 * 1、@Import 注解导入普通类 ClassA 直接导入即可
 * 2、@Import 注解导入已被 @Configuration 注解标记的类 ClassB，不需要额外处理
 * 3、@Import 注解导入实现 ImportSelect接口的类 ClassC
 * 4、@Import 注解导入实现 ImportBeanDefinitionRegistrar 接口的类 ClassD
 *
 */
@Import({ClassA.class, SelfImportSelector.class, SelfImportBeanDefinitionRegistrar.class})
@Configuration
public class ImportConfig {

}
