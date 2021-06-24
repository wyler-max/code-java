import javax.annotation.Resource;

import org.example.practice.practicespring.PracticeSpring;
import org.example.practice.practicespring.springcontext.SelfEmbededValueResolverAware;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wangyulin
 * @date 2021/6/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PracticeSpring.class)
public class EmbededValueResolverAwareTest {

    @Resource
    private SelfEmbededValueResolverAware selfEmbededValueResolverAware;

    @Test
    public void TestA() {
        System.out.println("start");
        System.out.println(selfEmbededValueResolverAware.getPropertiesValue("db.mysql.user"));
        System.out.println(selfEmbededValueResolverAware.getPropertiesValue("db.mysql.password"));
        System.out.println(selfEmbededValueResolverAware.getPropertiesValue("db.mysql.union"));
    }

}
