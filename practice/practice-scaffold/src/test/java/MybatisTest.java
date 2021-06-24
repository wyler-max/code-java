import java.util.List;

import javax.annotation.Resource;

import org.example.practicescaffold.PracticeScaffoldApp;
import org.example.practicescaffold.db.dao.biz.UserMapper;
import org.example.practicescaffold.db.model.biz.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 从测试单元，加载SpringBoot应用，调用其中的方法
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PracticeScaffoldApp.class)
public class MybatisTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test() {
        List<User> users = userMapper.queryUserList();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test2() {
        System.out.println("aaa");
    }
}
