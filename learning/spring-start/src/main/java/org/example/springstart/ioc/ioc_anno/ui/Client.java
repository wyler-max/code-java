package org.example.springstart.ioc.ioc_anno.ui;

import org.example.springstart.ioc.ioc_anno.service.IAccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {

    public static void main(String[] args) {
        // 1. 获取核心容器对象
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("com/example/springstart/ioc/ioc_anno/bean.xml");

        // 2. 根据id 获取Bean 对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        /*
        IAccountService as1 = ac.getBean("accountService", IAccountService.class);
        System.out.println(as ==  as1);
        */

        /*
        System.out.println(as);
        IAccountDao adao = ac.getBean("accountDao", IAccountDao.class);
        System.out.println(adao);
        */
        as.saveAccount();

        //ac.close();

    }
}
