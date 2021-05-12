package org.example.practicescaffold.mybatis.domain;

import java.io.Serializable;

/**
 * 使用 pojo 传递查询条件。（可以打包多个model属性，比如 user，order，address等）
 *
 * 将查询条件放到 QueryVo 的 user属性中。
 */
public class QueryVo implements Serializable {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
