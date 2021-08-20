import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BTest {
    public static void main(String[] args) {
        funcC();
        funcD();
        System.out.println(getLineInfo());
        System.out.println(getLineInfo("shakalaka"));
        log.debug("aaaa");
    }

    public static String getLineInfo() {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        // StackTraceElement ste = Thread.currentThread().getStackTrace()[1];
        return ste.getFileName() + ": Line " + ste.getLineNumber();
    }

    public static String getLineInfo(String msg) {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        // StackTraceElement ste = Thread.currentThread().getStackTrace()[1];
        return ste.getFileName() + ": Line " + ste.getLineNumber() + ", msg " + msg;
    }

    public static void funcA() {
        int a = 10;
        System.out.println(Thread.currentThread().getStackTrace()[0].getLineNumber());
        System.out.println("a=" + a);
    }

    public static void funcB() {
        int a = 10;
        System.out.println(Thread.currentThread().getStackTrace()[0].getLineNumber());
        System.out.println("b=" + a);
    }

    public static void funcC() {
        int a = 10;
        System.out.println(getLineInfo());
        log.debug("cccc");
        System.out.println("c=" + a);
    }

    public static void funcD() {
        int a = 10;
        System.out.println(Thread.currentThread().getStackTrace()[1].getLineNumber());
        System.out.println("d=" + a);
    }
}
