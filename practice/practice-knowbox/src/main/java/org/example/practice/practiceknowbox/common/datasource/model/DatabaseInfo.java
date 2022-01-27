package org.example.practice.practiceknowbox.common.datasource.model;

import java.io.Serializable;

import javax.sql.DataSource;

import org.example.practice.practiceknowbox.common.datasource.enums.DataSourceType;
import org.springframework.util.Assert;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.Data;

/**
 * 单个数据库信息
 *
 * @author yijiu.chen
 * @date 2020/04/20
 */
@Data
public class DatabaseInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private DataSource datasource;

    private DataSourceType type;

    public DatabaseInfo() {}

    public DatabaseInfo(DataSourceType type, HikariConfig config) {
        Assert.isTrue(type != null && config != null, "数据库参数错误");
        this.type = type;
        this.datasource = new HikariDataSource(config);
    }

}
