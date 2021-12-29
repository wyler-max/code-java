package lazy.api.impl;

import java.util.Map;

import com.google.common.collect.Maps;

import lazy.api.DepartmentServiceApi;

/**
 *
 */
public class DepartmentServiceImpl implements DepartmentServiceApi {

    private static Map<Long, String> deptMap = Maps.newHashMap();
    static {
        deptMap.put(1L, "技术部");
        deptMap.put(2L, "产品部");
        deptMap.put(3L, "运营部");
    }

    @Override
    public String getDepartment(long uid) {
        return deptMap.getOrDefault(uid, "技术部");
    }
}
