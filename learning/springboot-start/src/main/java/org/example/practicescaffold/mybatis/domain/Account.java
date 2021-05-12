package org.example.practicescaffold.mybatis.domain;

import java.io.Serializable;

/**
 * 账户实体类
 * 一对多 demo
 * 一个用户可能存在多个账户
 */
public class Account implements Serializable {

    private Integer id;
    private Integer uid;
    private Double money;

    // 从表实体应该包含一个主表实体的对象引用
    // 在 Account 类中加入 User 类的对象作为 Account 类的一个属性
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                //", user=" + user +
                '}';
    }
}
