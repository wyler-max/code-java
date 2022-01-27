package org.example.practice.practiceknowbox.common.datasource.mybatis.interceptor;

import java.sql.Statement;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.cglib.proxy.Proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yijiu.chen
 * @date 2020/05/18
 */
@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
    @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
    @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})})
public class SlowSqlInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        Object returnValue = invocation.proceed();
        long time = System.currentTimeMillis() - start;

        StatementHandler statementHandler = realTarget(invocation.getTarget());
        // 控制台打印日志
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        StringBuilder str = new StringBuilder(256);
        str.append("[sql:");
        str.append(beautifySql(sql));
        str.append("]");
        str.append("[Cost:");
        str.append(time);
        str.append("ms]");
        String sqlInfo = str.toString();
        if (time > 50) {
            log.info(sqlInfo);
        } else {
            log.debug(sqlInfo);
        }

        return returnValue;
    }

    @Override
    public Object plugin(Object arg) {
        return Plugin.wrap(arg, this);
    }

    /**
     * 获得真正的处理对象,可能多层代理
     *
     * @param target
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    private <T> T realTarget(Object target) {
        if (Proxy.isProxyClass(target.getClass())) {
            MetaObject metaObject = SystemMetaObject.forObject(target);
            return realTarget(metaObject.getValue("h.target"));
        }
        return (T)target;
    }

    /**
     * 美化Sql
     */
    private String beautifySql(String sql) {
        return sql.replaceAll("[\\s\n ]+", " ");
    }
}
