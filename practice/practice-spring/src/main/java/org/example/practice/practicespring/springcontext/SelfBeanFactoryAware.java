package org.example.practice.practicespring.springcontext;

import org.example.practice.practicespring.annotation.importAnnotation.ClassB;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * 获取 beanFactory
 *
 * getBean
 *
 * beanFactory让你可以不依赖注入方式，随意的读取IOC容器里面的对象，不过beanFactory本身还是要注入的。
 */
@Component
public class SelfBeanFactoryAware implements BeanFactoryAware {

    private BeanFactory beanFactory;

    // 实现了 BeanFactoryAware 接口，重写setBeanFactory方法后，可以拿到beanFactory，获取到任何bean
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        // 默认的BeanFactory => DefaultListableBeanFactory
        System.out.println("I belong to " + beanFactory);

        // 通过beanFactory，读取任意bean
        ClassB classB = (ClassB)beanFactory.getBean("classB");
        classB.printName();
    }

    /**
     * @formatter:off
     * 使用实例：
     *      比如有10个银行的处理对象，都继承了BankService对象，使用时需要选择具体的银行对象.
     * 命名：
     *      将银行对象分别命名为 BankServiceA、BankServiceB、BankServiceC...
     * 使用：
     *      String bankName = "A";
     *      BankService bank = (BankService)beanFactory.getBean("bankService" + baneName);
     *      bank.inputMoney();
     *      bank.outputMoney();
     */
}
