package lazy.api;

/**
 * 部门服务接口
 */
public interface DepartmentServiceApi {

    /**
     * 获取用户所在部门
     */
    String getDepartment(long uid);
}
