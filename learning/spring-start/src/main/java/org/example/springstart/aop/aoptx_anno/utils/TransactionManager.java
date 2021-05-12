package org.example.springstart.aop.aoptx_anno.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 和事务管理相关的工具类，它包含了，开启事务，提交事务，回滚事务和释放连接
 *
 * 当使用注解管理各种通知时：
 * @Before              前置通知
 * @AfterReturning      后置通知    实际处理：最终通知
 * @AfterThrowing       异常通知
 * @After               最终通知    实际处理：后置通知
 *
 * 当使用xml管理各种通知时：
 * aop:before           前置通知
 * aop:after-returning  后置通知
 * aop:after-throwing   异常通知
 * aop:after            最终通知
 *
 * 使用注解和xml管理 后置通知和最终通知 是有区别的，即注解的后置通知和最终通知应该反过来用。
 *
 * 因此建议使用环绕通知替代
 *
 */
@Component("txManager")
@Aspect
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* com.example.aop.aoptx_anno.service.impl.*.*(..))")
    private void pt1(){}


    /**
     * 开启事务
     */
    @Before("pt1()")
    public  void beginTransaction(){
        System.out.println("-----前置通知：开启事务，关闭事务的自动提交");
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    @After("pt1()")
    public  void commit(){
        System.out.println("-----后置通知：提交事务");
        try {
            connectionUtils.getThreadConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    @AfterThrowing("pt1()")
    public  void rollback(){
        System.out.println("-----异常通知：回滚事务");
        try {
            connectionUtils.getThreadConnection().rollback();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 释放连接
     */
    @AfterReturning("pt1()")
    public  void release(){
        System.out.println("-----最终通知：释放连接");
        try {
            connectionUtils.getThreadConnection().close();//还回连接池中
            connectionUtils.removeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 环绕通知
     * 问题：
     *      当我们配置了环绕通知之后，切入点方法没有执行，而通知方法执行了。
     * 分析：
     *      通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有明确的切入点方法调用，而我们的代码中没有。
     * 解决：
     *      Spring框架为我们提供了一个接口：ProceedingJoinPoint。该接口有一个方法proceed()，此方法就相当于明确调用切入点方法。
     *      该接口可以作为环绕通知的方法参数，在程序执行时，spring框架会为我们提供该接口的实现类供我们使用。
     *
     * spring中的环绕通知：
     *      它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式。
     */
//    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try {
            //1.获取参数
            Object[] args = pjp.getArgs();
            //2.开启事务
            this.beginTransaction();
            //3.执行方法
            rtValue = pjp.proceed(args);
            //4.提交事务
            this.commit();

            //返回结果
            return  rtValue;

        }catch (Throwable e){
            //5.回滚事务
            this.rollback();
            throw new RuntimeException(e);
        }finally {
            //6.释放资源
            this.release();
        }
    }
}
