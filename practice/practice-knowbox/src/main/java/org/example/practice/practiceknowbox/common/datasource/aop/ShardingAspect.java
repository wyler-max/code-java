package org.example.practice.practiceknowbox.common.datasource.aop;

import java.lang.reflect.Method;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.practice.practiceknowbox.common.datasource.ShardingDatabaseAllHolder;
import org.example.practice.practiceknowbox.common.datasource.ShardingId;
import org.example.practice.practiceknowbox.common.datasource.annotation.DatabaseSharding;
import org.example.practice.practiceknowbox.common.datasource.annotation.DatabaseShardings;
import org.example.practice.practiceknowbox.common.datasource.annotation.TableSharding;
import org.example.practice.practiceknowbox.common.datasource.annotation.TableShardings;
import org.example.practice.practiceknowbox.common.datasource.annotation.UseMaster;
import org.example.practice.practiceknowbox.common.datasource.annotation.UseMasters;
import org.example.practice.practiceknowbox.common.datasource.enums.DataSourceType;
import org.example.practice.practiceknowbox.common.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * 分库分表相关注解拦截
 *
 * @author yijiu.chen
 * @date 2020/04/18
 */
@Aspect
@Component
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@ConditionalOnBean(ShardingDatabaseAllHolder.class)
@Slf4j
public class ShardingAspect implements PriorityOrdered {

    @Autowired
    private ShardingDatabaseAllHolder holders;

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

    @Around("@annotation(org.example.practice.practiceknowbox.common.datasource.annotation.UseMaster) "
        + "|| @annotation(org.example.practice.practiceknowbox.common.datasource.annotation.UseMasters)")
    public Object useMasterAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        List<String> dbNames = Lists.newArrayList();
        if (signature instanceof MethodSignature) {
            Class<?> targetCls = joinPoint.getTarget().getClass();
            MethodSignature ms = (MethodSignature) signature;
            Method targetMethod = targetCls.getDeclaredMethod(signature.getName(), ms.getParameterTypes());
            List<UseMaster> annotations = Lists.newArrayList();
            if (targetMethod.isAnnotationPresent(UseMaster.class)) {
                annotations.add(targetMethod.getAnnotation(UseMaster.class));
            }
            if (targetMethod.isAnnotationPresent(UseMasters.class)) {
                annotations.addAll(Lists.newArrayList(targetMethod.getAnnotation(UseMasters.class).value()));
            }
            for (UseMaster val : annotations) {
                dbNames.add(val.dbName());
                holders.setDataSourceType(val.dbName(), DataSourceType.FORCE_MASTER);
            }
        }
        try {
            return joinPoint.proceed();
        } finally {
            for (String dbName : dbNames) {
                holders.removeDataSourceType(dbName);
            }
        }
    }

    @Around("@annotation(org.example.practice.practiceknowbox.common.datasource.annotation.DatabaseSharding) "
        + "|| @annotation(org.example.practice.practiceknowbox.common.datasource.annotation.DatabaseShardings)")
    public Object databaseShardingAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        List<String> dbNames = Lists.newArrayList();
        long shardId = 0L;
        if (signature instanceof MethodSignature) {
            if (joinPoint.getArgs() == null || joinPoint.getArgs().length == 0) {
                throw new RuntimeException("DatabaseSharding注解使用错误, 方法没有参数");
            }
            for (Object arg : joinPoint.getArgs()) {
                if (arg != null && arg instanceof ShardingId) {// 第一个参数
                    shardId = ((ShardingId) arg).getId();
                    if (log.isDebugEnabled()) {
                        log.debug("sharding id:{}", JsonUtil.toJson(arg));
                    }
                    break;
                }
            }
            if (shardId <= 0L) {
                throw new RuntimeException("DatabaseSharding注解使用错误, 方法没有非空的ShardingId参数");
            }

            Class<?> targetCls = joinPoint.getTarget().getClass();
            MethodSignature ms = (MethodSignature) signature;
            Method targetMethod = targetCls.getDeclaredMethod(signature.getName(), ms.getParameterTypes());
            List<DatabaseSharding> annotations = Lists.newArrayList();
            if (targetMethod.isAnnotationPresent(DatabaseSharding.class)) {
                annotations.add(targetMethod.getAnnotation(DatabaseSharding.class));
            }
            if (targetMethod.isAnnotationPresent(DatabaseShardings.class)) {
                annotations.addAll(Lists.newArrayList(targetMethod.getAnnotation(DatabaseShardings.class).value()));
            }
            for (DatabaseSharding val : annotations) {
                dbNames.add(val.dbName());
                holders.setDataSourceShardingIndex(val.dbName(),
                    (int) ((shardId - 1) % holders.fetchDatabaseShardingSize(val.dbName())));
            }
        }
        try {
            return joinPoint.proceed();
        } finally {
            for (String dbName : dbNames) {
                holders.removeDataSourceShardingIndex(dbName);
            }
        }
    }

    @Around("@annotation(org.example.practice.practiceknowbox.common.datasource.annotation.TableSharding) "
        + "|| @annotation(org.example.practice.practiceknowbox.common.datasource.annotation.TableShardings)")
    public Object tableShardingAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        List<String> tableNames = Lists.newArrayList();
        long shardId = 0L;
        if (signature instanceof MethodSignature) {
            if (joinPoint.getArgs() == null || joinPoint.getArgs().length == 0) {
                throw new RuntimeException("TableSharding注解使用错误, 方法没有参数");
            }
            for (Object arg : joinPoint.getArgs()) {
                if (arg != null && arg instanceof ShardingId) {// 第一个参数
                    shardId = ((ShardingId) arg).getTableId();
                    if (log.isDebugEnabled()) {
                        log.debug("sharding id:{}", JsonUtil.toJson(arg));
                    }
                    break;
                }
            }
            if (shardId <= 0L) {
                throw new RuntimeException("TableSharding注解使用错误, 方法没有非空的ShardingId参数");
            }

            Class<?> targetCls = joinPoint.getTarget().getClass();
            MethodSignature ms = (MethodSignature) signature;
            Method targetMethod = targetCls.getDeclaredMethod(signature.getName(), ms.getParameterTypes());
            List<TableSharding> annotations = Lists.newArrayList();
            if (targetMethod.isAnnotationPresent(TableSharding.class)) {
                annotations.add(targetMethod.getAnnotation(TableSharding.class));
            }
            if (targetMethod.isAnnotationPresent(TableShardings.class)) {
                annotations.addAll(Lists.newArrayList(targetMethod.getAnnotation(TableShardings.class).value()));
            }
            for (TableSharding val : annotations) {
                tableNames.add(val.tableName());
                holders.setTableShardInfo(val.dbName(), val.tableName(), shardId);
            }
        }
        try {
            return joinPoint.proceed();
        } finally {
            for (String tableName : tableNames) {
                holders.removeTableShardInfo(tableName);
            }
        }
    }
}
