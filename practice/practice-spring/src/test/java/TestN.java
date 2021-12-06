import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * @author wangyulin
 * @date 2021/11/15
 */
public class TestN {

    @Test
    public void testN() {
        List<Long> list = Lists.newArrayList();
        list.add(1000L);
        list.add(2000L);
        System.out.println(list.indexOf(10));
        System.out.println(list.indexOf(1000L));
        System.out.println(list.indexOf(2000L));
    }
}
