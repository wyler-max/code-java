package org.example.practice.practicespring.springcontext;

import org.springframework.beans.factory.BeanClassLoaderAware;

/**
 * 用于获取bean的装在类
 */
public class SelfBeanClassLoaderAware implements BeanClassLoaderAware {

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {

    }
}
