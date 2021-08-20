package org.example.practicescaffold.config.mysql.sharding;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 主从-数据源实体类
 */
@Data
@Slf4j
public class MasterSlavesDataSourceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private DataSourceInfo master;

    private List<DataSourceInfo> slaves;

    private LongAdder counter;

    private int slaveSize;

    /**
     * 初始化数据源
     */
    public MasterSlavesDataSourceEntity(List<DataSourceInfo> list) {
        Assert.isTrue(!CollectionUtils.isEmpty(list), "数据库列表不能为空");
        List<DataSourceInfo> slaveList = Lists.newArrayListWithExpectedSize(list.size());
        boolean foundMaster = false;
        for (DataSourceInfo info : list) {
            if (info.getType() == DataSourceType.MASTER) {
                if (!foundMaster) {
                    foundMaster = true;
                    this.master = info;
                } else {
                    throw new RuntimeException("目前仅支持一主多从数据库配置");
                }
            } else if (info.getType() == DataSourceType.SLAVE) {
                slaveList.add(info);
            }
        }
        if (this.master == null) {
            throw new RuntimeException("没有找到master数据库配置");
        }
        this.slaves = slaveList;
        this.slaveSize = slaveList.size();
        this.counter = new LongAdder();
    }

    /**
     * 获取数据源 主库=>主库；从库=>下一个从库
     */
    public Object findDbKey(DataSourceType type) {
        if (type == null) {
            log.error("数据库主从未指定，默认使用MASTER");
            type = DataSourceType.MASTER;
            return type.toString();
        }
        if (type == DataSourceType.MASTER || type == DataSourceType.FORCE_MASTER) {
            log.debug("使用MASTER数据库");
            return type.toString();
        }
        if (slaveSize == 0) {
            log.debug("没有配置从库，默认使用MASTER");
            type = DataSourceType.MASTER;
            return type.toString();
        }
        // 分库计算
        counter.increment();
        long index = (int)(counter.longValue() % slaveSize);
        log.debug("使用SLAVE-{}数据库", index);
        return type.toString() + index;
    }

    /**
     * 根据数据库名称，构造数据源map
     */
    public Map<Object, Object> buildMap(String dataSourceName) {
        Map<Object, Object> map = Maps.newHashMapWithExpectedSize(1 + slaveSize);
        map.put(dataSourceName + DataSourceType.MASTER.toString(), this.master.getDatasource());
        for (int i = 0; i < slaveSize; i++) {
            map.put(dataSourceName + DataSourceType.SLAVE.toString() + i, this.slaves.get(i).getDatasource());
        }
        return map;
    }
}
