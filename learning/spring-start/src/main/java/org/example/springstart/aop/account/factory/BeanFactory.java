package org.example.springstart.aop.account.factory;

import org.example.springstart.aop.account.service.IAccountService;
import org.example.springstart.aop.account.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建Service的代理对象的工厂
 */
public class BeanFactory {
    private IAccountService accountService;
    private TransactionManager txManager;

    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    /**
     * 基于 Proxy 动态代理实现事务的管理
     * @return
     */
    public IAccountService getAccountService() {
        return (IAccountService)Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {

                    /**
                     * 添加事务的支持
                     *
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        Object rtValue = null;
                        try {
                            System.out.println("开启事务.....");
                            txManager.beginTransaction();

                            // 反射调用被增强的方法
                            rtValue = method.invoke(accountService, args);

                            System.out.println("提交事务.....");
                            txManager.commit();
                            return rtValue;
                        } catch (Exception e) {
                            System.out.println("回滚事务.....");
                            txManager.rollback();
                            throw new RuntimeException(e);
                        } finally {
                            System.out.println("释放连接.....");
                            txManager.release();
                        }
                    }
                });
    }
}
