package org.example.springstart.ioc.ioc_xml.ui;

import org.example.springstart.ioc.ioc_xml.dao.IAccountDao;
import org.example.springstart.ioc.ioc_xml.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Client {

    public static void main(String[] args) {
        // 1. 获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("com/example/springstart/ioc/ioc_xml/bean.xml");

        // 2. 根据id 获取Bean 对象
        IAccountService as = (IAccountService)ac.getBean("accountService");
        IAccountDao adao = ac.getBean("accountDao", IAccountDao.class);
        System.out.println(as);
        System.out.println(adao);
        //as.saveAccount(); // 报错


        //2. 测试spring ioc 各种配置
        IAccountService as1  = (IAccountService)ac.getBean("accountService1");
        as1.saveAccount();

        IAccountService as2 = ac.getBean("accountService2", IAccountService.class);
        as2.saveAccount();

        IAccountService as3 = ac.getBean("accountService3", IAccountService.class);
        as3.saveAccount();


    }
}
