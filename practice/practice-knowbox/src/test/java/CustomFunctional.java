/**
 * 自定义函数式接口
 */
@FunctionalInterface
public interface CustomFunctional<T, R> {

    R doAction(T t);
}
