package org.example.practicescaffold.config.mysql;

public enum DataSourceType {
    MASTER, // NL
    FORCE_MASTER, // NL强制使用master，防止主从延时
    SLAVE
}
