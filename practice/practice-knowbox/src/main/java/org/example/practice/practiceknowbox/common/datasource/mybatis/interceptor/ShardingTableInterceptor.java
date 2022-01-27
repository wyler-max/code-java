package org.example.practice.practiceknowbox.common.datasource.mybatis.interceptor;

import java.sql.Connection;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.cglib.proxy.Proxy;

import org.example.practice.practiceknowbox.common.datasource.ShardTableConfig;
import org.example.practice.practiceknowbox.common.datasource.ShardingDatabaseAllHolder;
import org.example.practice.practiceknowbox.common.model.AccessInfo;
import org.example.practice.practiceknowbox.common.util.StringsUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 分表
 *
 * @author yijiu.chen
 * @date 2020/04/20
 */
@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class ShardingTableInterceptor implements Interceptor {

    private ShardingDatabaseAllHolder holder;

    public ShardingTableInterceptor(ShardingDatabaseAllHolder holder) {
        this.holder = holder;
    }

    /* (non-Javadoc)
     * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        doTable(statementHandler, metaObject);
        return invocation.proceed();
    }

    private void doTable(StatementHandler handler, MetaObject metaStatementHandler) throws ClassNotFoundException {
        BoundSql boundSql = handler.getBoundSql();
        String originalSql = boundSql.getSql();

        if (StringUtils.isNotBlank(originalSql)) {
            MappedStatement mappedStatement =
                (MappedStatement)metaStatementHandler.getValue("delegate.mappedStatement");
            String id = mappedStatement.getId();
            String className = id.substring(0, id.lastIndexOf("."));// xxxmapper.method
            className = className.substring(className.lastIndexOf(".") + 1);// xxxMapper
            String tableName = StringsUtil.camelToUnderline(className.substring(0, className.length() - 6));
            // 注解会设置当前表的数据
            ShardTableConfig config = holder.fetchTableShardingConfig(tableName);
            if (config != null) {
                // 分表
                String shardingTable = tableName + "_" + config.fetchTableShardingSize();
                originalSql = originalSql.replaceAll("\\n", " ");
                String convertedSql = originalSql.replaceFirst(" " + tableName + " ", " " + shardingTable + " ");
                AccessInfo.addParams(shardingTable, StringUtils.EMPTY);
                metaStatementHandler.setValue("delegate.boundSql.sql", convertedSql);
                log.debug("分表原始:{},转换后:{}", originalSql, convertedSql);
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
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

}
