package org.example.springstart.spring_bean.service.impl;

import org.example.springstart.spring_bean.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

    public AccountServiceImpl() {
        System.out.println("AccountServiceImpl 对象创建成功");
    }
    @Override
    public void saveAccount() {
        System.out.println("service 中的 saveAccount 方法执行了");
    }

    public void init() {
        System.out.println("AccountServiceImpl 初始化");
    }
    public void destroy() {
        System.out.println("AccountServiceImpl 销毁了");
    }
}
