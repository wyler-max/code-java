package org.example.practice.practicespring.annotation.importDir;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @formatter:off
 * 1、@Import 注解导入普通类
 * 2、@Import 注解导入已被@Configuration注解标记的类
 * 3、@Import 注解导入实现 ImportSelect接口的类
 * 4、@Import 注解导入实现 ImportBeanDefinitionRegistrar接口的类
 */
@Import({TestA.class, TestB.class, SelfImportSelector.class, SelfImportBeanDefinitionRegistrar.class})
@Configuration
public class ImportConfig {

}
