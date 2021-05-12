import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 从测试单元，启动SpringBoot，执行方法
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAppTest {

    @Test
    public void contextLoads() {
        System.out.println("contextLoads 执行了...");
    }
}
