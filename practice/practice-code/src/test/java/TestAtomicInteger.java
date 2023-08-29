import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicInteger {

    private static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) {
        test2();
    }

    private static void test1() {
        System.out.println(num);
        num.getAndUpdate(operand -> {
            return 3 + 2;
        });
        System.out.println(num);
    }

    private static void test2() {
        System.out.println(num);
        num.accumulateAndGet(2, (x, y) -> {
            return x * y;
        });
        num.accumulateAndGet(2, (x, y) -> {
            return x * y;
        });
        System.out.println(num);
    }

    private static String getType(Object o) {
        return o.getClass().getSimpleName().toString();
    }
}
