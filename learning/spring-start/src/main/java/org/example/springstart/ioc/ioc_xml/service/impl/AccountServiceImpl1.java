package org.example.springstart.ioc.ioc_xml.service.impl;

import org.example.springstart.ioc.ioc_xml.service.IAccountService;

import java.util.Date;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl1 implements IAccountService {
    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImpl1(String name, Integer age, Date birthday){
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    @Override
    public void saveAccount() {
        System.out.println("service中的saveAccount方法执行了。。。"+name+","+age+","+birthday);
    }
}
