package JavaStudy.java8.testMethodRef;

import JavaStudy.java8.pojo.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author
 * https://blog.csdn.net/Hello_World_QWP/article/details/80245566
 * 方法引用,构造器引用,数组引用
 *
 * 注意：
 * ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 *
 */
public class TestMethodRef {

    //对象::实例方法名
    @Test
    public void test1(){
        PrintStream ps1 =System.out;
        Consumer<String> con =x ->ps1.println(x);

        PrintStream ps =System.out;
        Consumer<String> con1 =ps::println;

        Consumer<String> con2 = System.out::println;
        con2.accept("abcdef");
    }

    //练习
    @Test
    public void test2(){
        Employee employee = new Employee();
        Supplier<String> supplier1 = () ->employee.getName();
        String s1 = supplier1.get();
        System.out.println(s1);

        Supplier<Integer> supplier2 = () ->employee.getAge();
        Integer integer = supplier2.get();
        System.out.println(integer);
    }

    //类名::静态方法名
    @Test
    public void test3() {
        Comparator<Integer> comparator1 = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> comparator2 = Integer::compare;

    }

    //类名::实例方法名
    //②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
    @Test
    public void test4() {
        BiPredicate<String,String> bp =(x,y) -> x.equals(y);
        BiPredicate<String,String> bp2 = String::equals;

    }

    //构造器引用 类名::new
    @Test
    public void test5() {
        Supplier<Employee> supplier =() -> new Employee();

        //构造器引用方式
        Supplier<Employee> supplier2 =Employee::new;
        Employee employee = supplier2.get();
        System.out.println(employee);
    }

    //构造器的参数列表，需要与函数式接口中参数列表保持一致！
    @Test
    public void test6() {
        Function<Integer,Employee> function =(x) -> new Employee(x);

        Function<Integer,Employee> function1 = Employee::new;
        Employee employee = function1.apply(11);
        System.out.println(employee);

    }

    //数组引用 Type::new
    @Test
    public void test7() {
        Function<Integer,String[]> function =(x) -> new String[x];
        String[] s = function.apply(10);
        System.out.println(s.length);

        Function<Integer,String[]> function1 = String[]::new;
        String[] s1 = function1.apply(20);
        System.out.println(s1.length);

    }
}
