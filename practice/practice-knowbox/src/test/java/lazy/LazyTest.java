package lazy;

import org.junit.Test;

import lazy.api.DepartmentServiceApi;
import lazy.api.SupervisorServiceApi;
import lazy.api.impl.DepartmentServiceImpl;
import lazy.api.impl.SupervisorServiceImpl;

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
