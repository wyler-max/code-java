package org.example.practicescaffold.config.mysql.model;

import java.io.Serializable;

import javax.sql.DataSource;

import org.example.practicescaffold.config.mysql.enums.DataSourceType;
import org.springframework.util.Assert;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.Data;

/**
 * 单个数据库
 */
@Data
public class DatabaseInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private DataSourceType type;
    private DataSource datasource;

    public DatabaseInfo(DataSourceType type, HikariConfig hikariConfig) {
        Assert.isTrue(type != null && hikariConfig != null, "数据库参数错误");
        this.type = type;
        this.datasource = new HikariDataSource(hikariConfig);
    }
}
