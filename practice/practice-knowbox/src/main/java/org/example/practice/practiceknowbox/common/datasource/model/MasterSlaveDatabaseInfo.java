package org.example.practice.practiceknowbox.common.datasource.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.example.practice.practiceknowbox.common.datasource.enums.DataSourceType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 主从数据库信息
 *
 * @author yijiu.chen
 * @date 2020/04/20
 */
@Data
@Slf4j
public class MasterSlaveDatabaseInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private DatabaseInfo master;

    private List<DatabaseInfo> slaves;

    private LongAdder counter;

    private int slaveSize;

    public MasterSlaveDatabaseInfo() {}

    public MasterSlaveDatabaseInfo(List<DatabaseInfo> list) {
        Assert.isTrue(!CollectionUtils.isEmpty(list), "数据库列表不能为空");
        List<DatabaseInfo> slaveList = Lists.newArrayListWithExpectedSize(list.size());
        boolean foundMaster = false;
        for (DatabaseInfo info : list) {
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

    public Object findDbKey(DataSourceType type) {
        if (type == null) {
            log.error("数据库主从未指定，默认使用MASTER");
            type = DataSourceType.MASTER;
            return type.toString();
        }

        if (type == DataSourceType.SLAVE && slaveSize == 0) {
            log.debug("没有配置从库，默认使用MASTER");
            type = DataSourceType.MASTER;
            return type.toString();
        }

        if (type == DataSourceType.MASTER || type == DataSourceType.FORCE_MASTER) {
            log.debug("使用MASTER数据库");
            return type.toString();
        } else {
            counter.increment();
            long index = (int)(counter.longValue() % slaveSize);
            log.debug("使用SLAVE-{}数据库", index);
            return type.toString() + index;
        }
    }

    public Map<Object, Object> buildMap(String dbName) {
        Map<Object, Object> map = Maps.newHashMapWithExpectedSize(1 + slaveSize);
        map.put(dbName + DataSourceType.MASTER.toString(), this.master.getDatasource());
        for (int i = 0; i < slaveSize; i++) {
            map.put(dbName + DataSourceType.SLAVE.toString() + i, this.slaves.get(i).getDatasource());
        }
        return map;
    }
}
