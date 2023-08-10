import org.example.practice.practicespringbootstart.app.PracticeSpringbootStartApp;
import org.example.practice.practicespringbootstart.biz.AlarmBizService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangyulin
 * @date 2023/8/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PracticeSpringbootStartApp.class)
public class TestEnv {

    @Test
    public void fetchEnv() {
        System.out.println(AlarmBizService.getActiveProfiles());
    }
}
