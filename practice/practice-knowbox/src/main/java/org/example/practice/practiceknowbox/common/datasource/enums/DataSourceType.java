package org.example.practice.practiceknowbox.common.datasource.enums;

/**
 * @author yijiu.chen
 * @date 2020/04/17
 */
public enum DataSourceType {
    MASTER, // NL
    FORCE_MASTER, // NL强制使用master，防止主从延时
    SLAVE
}
