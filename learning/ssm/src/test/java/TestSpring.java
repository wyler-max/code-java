import org.example.ssm.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring 环境测试类
 */
public class TestSpring {
    @Test
    public void run() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        IAccountService as = (IAccountService)ac.getBean("accountServiceImpl");
        as.findAll();
    }
}
