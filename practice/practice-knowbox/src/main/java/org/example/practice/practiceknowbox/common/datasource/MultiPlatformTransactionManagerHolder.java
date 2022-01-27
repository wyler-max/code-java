package org.example.practice.practiceknowbox.common.datasource;

import java.util.Map;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.Assert;

/**
 * @author yijiu.chen
 * @date 2020/04/21
 */
public class MultiPlatformTransactionManagerHolder {

    private Map<String, PlatformTransactionManager> map;

    public MultiPlatformTransactionManagerHolder(Map<String, PlatformTransactionManager> map) {
        Assert.isTrue(map != null && map.size() > 0, "事务管理器错误");
        this.map = map;
    }

    public PlatformTransactionManager fetchByName(String name) {
        PlatformTransactionManager tm = map.get(name);
        if (tm == null) {
            throw new RuntimeException("错误的事务管理器名称");
        }
        return tm;
    }
}
