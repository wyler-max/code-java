package org.example.practice.practicespring.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 扫描xml
 */
@Configuration
@MapperScan(basePackages = "org.example.practice.practicespring.db.mapper")
public class Config {}
