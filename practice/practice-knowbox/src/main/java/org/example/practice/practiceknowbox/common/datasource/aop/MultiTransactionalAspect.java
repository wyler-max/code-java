package org.example.practice.practiceknowbox.common.datasource.aop;

import java.lang.reflect.Method;
import java.util.Stack;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.practice.practiceknowbox.common.datasource.MultiPlatformTransactionManagerHolder;
import org.example.practice.practiceknowbox.common.datasource.annotation.MultiTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @author yijiu.chen
 * @date 2020/04/21
 */
@Aspect
@Component
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@ConditionalOnBean(MultiPlatformTransactionManagerHolder.class)
public class MultiTransactionalAspect implements PriorityOrdered {

    @Autowired
    private MultiPlatformTransactionManagerHolder holder;

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 1;
    }

    @Around("@annotation(org.example.practice.practiceknowbox.common.datasource.annotation.MultiTransactional)")
    public Object multiTransactionalAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        Stack<PlatformTransactionManager> tmStack = new Stack<>();
        Stack<TransactionStatus> tsStack = new Stack<>();
        Class<?> rollbackFor = RuntimeException.class;
        if (signature instanceof MethodSignature) {
            Class<?> targetCls = joinPoint.getTarget().getClass();
            MethodSignature ms = (MethodSignature) signature;
            Method targetMethod = targetCls.getDeclaredMethod(signature.getName(), ms.getParameterTypes());
            MultiTransactional annotation = targetMethod.getAnnotation(MultiTransactional.class);
            rollbackFor = annotation.getClass();
            openTransaction(tmStack, tsStack, annotation);
        }
        try {
            Object ret = joinPoint.proceed();
            commit(tmStack, tsStack);
            return ret;
        } catch (Throwable t) {
            if (t.getClass().isAssignableFrom(rollbackFor)) {
                rollback(tmStack, tsStack);
            }
            throw t;
        }
    }

    private void openTransaction(Stack<PlatformTransactionManager> tmStack, Stack<TransactionStatus> tsStack,
                                 MultiTransactional annotation) {

        String[] transactionMangerNames = annotation.values();
        if (ArrayUtils.isEmpty(annotation.values())) {
            throw new RuntimeException("MultiTransactional未指定事务");
        }
        for (String tmName : transactionMangerNames) {
            PlatformTransactionManager dataSourceTransactionManager = holder.fetchByName(tmName);
            TransactionStatus transactionStatus =
                dataSourceTransactionManager.getTransaction(new DefaultTransactionDefinition());
            tsStack.push(transactionStatus);
            tmStack.push(dataSourceTransactionManager);
        }
    }

    private void commit(Stack<PlatformTransactionManager> tmStack, Stack<TransactionStatus> tsStack) {
        while (!tmStack.isEmpty()) {
            tmStack.pop().commit(tsStack.pop());
        }
    }

    private void rollback(Stack<PlatformTransactionManager> tmStack, Stack<TransactionStatus> tsStack) {
        while (!tmStack.isEmpty()) {
            tmStack.pop().rollback(tsStack.pop());
        }
    }
}
