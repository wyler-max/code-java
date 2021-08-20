package org.example.practicescaffold.config.mysql;

import java.util.List;

import com.zaxxer.hikari.HikariConfig;

import lombok.Data;

/**
 * 主从数据库配置
 */
@Data
public class MasterSlavesConfig {

    private HikariConfig master;

    private List<HikariConfig> slaves;
}
