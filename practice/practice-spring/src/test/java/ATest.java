import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.assertj.core.util.Lists;

public class ATest {
    public static void main(String[] args) {
        List<A> aList = Lists.newArrayList();
        A a1 = new A("aaa", 10);
        A a2 = new A("bbb", 20);
        aList.add(a1);
        aList.add(a2);
        System.out.println(aList);
        aList = Lists.newArrayList();
        Map<Integer, A> aMap = aList.stream().collect(Collectors.toMap(A::getAge, x -> x));
        for (int i = 0; i < aList.size(); i++) {
            A a = aList.get(i);
            a.setAge(30);
        }
        System.out.println(aList);
    }
}
