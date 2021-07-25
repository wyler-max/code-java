package temp;

import org.example.practicescaffold.config.mysql.MasterSlavesConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 读取数据库配置 单库、一主多从、多主多从、纯分片
 */
@Component
@Data
@ConfigurationProperties("databaseconfig")
public class ShadingDataSourceConfig {
    // 单库 master
    private MasterSlavesConfig jaydefault;
    // 一主多从 master+slaves
    private MasterSlavesConfig jayone;
    // 多主多从 (master+slaves) * n
    private MasterSlavesConfig jaytwo;
    // 纯分片 maters
    private MasterSlavesConfig jaythree;
}
