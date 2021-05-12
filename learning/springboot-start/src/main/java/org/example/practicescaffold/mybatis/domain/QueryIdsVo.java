package org.example.practicescaffold.mybatis.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 使用 pojo 传递 ids
 */
public class QueryIdsVo implements Serializable {

    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
