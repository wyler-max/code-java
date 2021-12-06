package org.example.practice.practicespring.annotation.importAnnotation;

import java.util.function.Predicate;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义 ImportSelector 接口的实现类，重写 selectImports 方法
 */
public class SelfImportSelector implements ImportSelector {

    /**
     * 将 TestC 添加到 Import 数组，进行注册
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {"org.example.practice.practicespring.annotation.importAnnotation.ClassC"};
    }

    @Override
    public Predicate<String> getExclusionFilter() {
        return ImportSelector.super.getExclusionFilter();
    }
}
