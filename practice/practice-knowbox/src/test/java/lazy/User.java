package lazy;

import java.util.Set;

/**
 * 实体对象
 */
public class User {
    /**
     * 用户ID
     */
    private Long uid;
    /**
     * 用户所在部门
     */
    private String department;
    /**
     * 用户所在部门主管
     */
    private Long supervisor;
    /**
     * 用户所有权限
     */
    private Set<String> permission;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Long supervisor) {
        this.supervisor = supervisor;
    }

    public Set<String> getPermission() {
        return permission;
    }

    public void setPermission(Set<String> permission) {
        this.permission = permission;
    }
}
