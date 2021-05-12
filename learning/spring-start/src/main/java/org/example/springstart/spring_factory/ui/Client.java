package org.example.springstart.spring_factory.ui;

import org.example.springstart.spring_factory.factory.BeanFactory;
import org.example.springstart.spring_factory.service.IAccountService;

/**
 * 测试 工厂生成 Bean 实例
 * 单例：getBean
 * 多例：getBeanMultition
 */
public class Client {

    public static void main(String[] args) {
        // IAccountService as = new AccountServiceImpl();
        for (int i = 0; i < 5; i++) {
            IAccountService as = (IAccountService) BeanFactory.getBeanMultition("accountService");
            System.out.println(as);
            as.saveAccount();
        }
    }
}
