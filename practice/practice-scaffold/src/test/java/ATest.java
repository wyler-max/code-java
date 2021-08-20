import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ATest {

    public static void main(String[] args) {
        List<Long> list = Lists.newArrayList();
        list.add(1L);
        list.add(0L);
        list.add(null);
        list.add(100L);
        System.out.println(list);
        list.removeAll(Lists.newArrayList(null, 0L));
        System.out.println(list);
    }
}
