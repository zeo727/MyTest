package JavaStudy.java8.testLambda1;

//@FunctionalInterface函数式接口检查
@FunctionalInterface
public interface MyPredicate<T> {
    public boolean test(T t);
}
