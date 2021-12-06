package org.example.practice.practiceknowbox.entities;

import java.util.Collections;
import java.util.Set;

/**
 * 实体对象
 */
public class UserLazy {
    /**
     * 用户ID
     */
    private Long uid;
    /**
     * 用户所在部门
     */
    private Lazy<String> department;
    /**
     * 用户所在部门主管
     */
    private Lazy<Long> supervisor;
    /**
     * 用户所有权限
     */
    private Lazy<Set<String>> permission;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getDepartment() {
        return department.get();
    }

    public void setDepartment(Lazy<String> department) {
        this.department = department;
    }

    public Long getSupervisor() {
        if (Lazy.isNull(supervisor)) {
            return null;
        }
        return supervisor.get();
    }

    public void setSupervisor(Lazy<Long> supervisor) {
        this.supervisor = supervisor;
    }

    public Set<String> getPermission() {
        if (Lazy.isNull(permission)) {
            return Collections.emptySet();
        }
        return permission.get();
    }

    public void setPermission(Lazy<Set<String>> permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserLazy{");
        sb.append("uid=").append(getUid());
        sb.append(", department=").append(getDepartment());
        sb.append(", supervisor=").append(getSupervisor());
        sb.append(", permission=").append(getPermission());
        return sb.toString();
    }
}
