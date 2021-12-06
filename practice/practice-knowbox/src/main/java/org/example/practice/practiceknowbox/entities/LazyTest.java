package org.example.practice.practiceknowbox.entities;

import org.example.practice.practiceknowbox.api.DepartmentServiceApi;
import org.example.practice.practiceknowbox.api.SupervisorServiceApi;
import org.example.practice.practiceknowbox.api.impl.DepartmentServiceImpl;
import org.example.practice.practiceknowbox.api.impl.SupervisorServiceImpl;
import org.junit.Test;

/**
 * @author wangyulin
 * @date 2021/11/5
 */
public class LazyTest {

    private DepartmentServiceApi departmentService = new DepartmentServiceImpl();
    private SupervisorServiceApi supervisorService = new SupervisorServiceImpl();

    @Test
    public void testLazy() {
        Lazy<Integer> lazyFunc1 = Lazy.of(() -> 1 + 2);
        Integer value = lazyFunc1.get();
        System.out.println(value);
    }

    @Test
    public void testUserLazy() {
        long uid = 2L;
        UserLazy userLazy = new UserLazy();
        userLazy.setUid(uid);
        userLazy.setDepartment(Lazy.of(() -> departmentService.getDepartment(uid)));
        // 这是个惰性计算数值，不能在调用 userLazy.getSupervisor()时，触发函数式的执行
        String department = userLazy.getDepartment();
        // userLazy.setSupervisor(Lazy.of(() -> supervisorService.getSupervisor(department)));
        System.out.println(userLazy);
    }
}
