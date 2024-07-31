package org.example.practicescaffold.config.mysql.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.practicescaffold.config.mysql.configuration.ShardingDataSourceConfig;
import org.example.practicescaffold.config.mysql.enums.DataSourceType;
import org.example.practicescaffold.config.mysql.sharding.ShardingDataSourceHolderAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class MasterSlaveAspect {
    @Autowired
    private ShardingDataSourceConfig dataSourceConfig;

    @Autowired
    private ShardingDataSourceHolderAll holder;

    @Before("execution(* org.example.practicescaffold.db.jayone.dao..*.select*(..))"
        + " || execution(* org.example.practicescaffold.db.jayone.dao..*.query*(..))"
        + " || execution(* org.example.practicescaffold.db.jayone.dao..*.count*(..))")
    public void setJayOneSlaveDataSourceType() {
        holder.setDataSourceType(dataSourceConfig.getJayOneName(), DataSourceType.SLAVE);
    }

    @Before("execution(* org.example.practicescaffold.db.jayone.dao..*.update*(..))"
        + " || execution(* org.example.practicescaffold.db.jayone.dao..*.insert*(..))"
        + " || execution(* org.example.practicescaffold.db.jayone.dao..*.delete*(..))"
        + " || execution(* org.example.practicescaffold.db.jayone.dao..*.batchInsert*(..))"
        + " || execution(* org.example.practicescaffold.db.jayone.dao..*.logicalDelete*(..))")
    public void setJayOneMasterSourceType() {
        holder.setDataSourceType(dataSourceConfig.getJayOneName(), DataSourceType.MASTER);
    }

    @After("execution(* org.example.practicescaffold.db.jayone.dao..*.*(..))")
    public void removeJayOneDataSource() {
        if (!DataSourceType.FORCE_MASTER.equals(holder.fetchDataSourceType(dataSourceConfig.getJayOneName()))) {
            holder.removeDataSourceType(dataSourceConfig.getJayOneName());
        }
    }

    @Before("execution(* org.example.practicescaffold.db.jaytwo.dao..*.select*(..))"
        + " || execution(* org.example.practicescaffold.db.jaytwo.dao..*.query*(..))"
        + " || execution(* org.example.practicescaffold.db.jaytwo.dao..*.count*(..))")
    public void setJayTwoSlaveDataSourceType() {
        log.info("MasterSlaveAspect set slave");
        holder.setDataSourceType(dataSourceConfig.getJayTwoName(), DataSourceType.SLAVE);
    }

    @Before("execution(* org.example.practicescaffold.db.jaytwo.dao..*.update*(..))"
        + " || execution(* org.example.practicescaffold.db.jaytwo.dao..*.insert*(..))"
        + " || execution(* org.example.practicescaffold.db.jaytwo.dao..*.delete*(..))"
        + " || execution(* org.example.practicescaffold.db.jaytwo.dao..*.batchInsert*(..))"
        + " || execution(* org.example.practicescaffold.db.jaytwo.dao..*.logicalDelete*(..))")
    public void setJayTwoMasterSourceType() {
        log.info("MasterSlaveAspect set master");
        holder.setDataSourceType(dataSourceConfig.getJayTwoName(), DataSourceType.MASTER);
    }

    @After("execution(* org.example.practicescaffold.db.jaytwo.dao..*.*(..))")
    public void removeJayTwoDataSource() {
        if (!DataSourceType.FORCE_MASTER.equals(holder.fetchDataSourceType(dataSourceConfig.getJayTwoName()))) {
            holder.removeDataSourceType(dataSourceConfig.getJayTwoName());
        }
    }
}
