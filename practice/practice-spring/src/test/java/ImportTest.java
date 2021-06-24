import javax.annotation.Resource;

import org.example.practice.practicespring.PracticeSpring;
import org.example.practice.practicespring.annotation.importDir.TestA;
import org.example.practice.practicespring.annotation.importDir.TestB;
import org.example.practice.practicespring.annotation.importDir.TestC;
import org.example.practice.practicespring.annotation.importDir.TestD;
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
public class ImportTest {

    @Resource
    private TestA testA;
    @Resource
    private TestB testB;
    @Resource
    private TestC testC;
    @Resource
    private TestD testD;

    @Test
    public void TestA() {
        System.out.println("start");
        testA.printName();
    }

    @Test
    public void TestB() {
        System.out.println("start");
        testB.printName();
    }

    @Test
    public void TestD() {
        System.out.println("start");
        testD.printName();
    }

    @Test
    public void TestC() {
        System.out.println("start");
        testC.printName();
    }
}
