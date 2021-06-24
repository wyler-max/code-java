package org.example.practice.practicespring.annotation.importDir;

import java.util.function.Predicate;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author wangyulin
 * @date 2021/6/16
 */
public class SelfImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {"org.example.practice.practicespring.annotation.importDir.Testc"};
    }

    @Override
    public Predicate<String> getExclusionFilter() {
        return ImportSelector.super.getExclusionFilter();
    }
}
