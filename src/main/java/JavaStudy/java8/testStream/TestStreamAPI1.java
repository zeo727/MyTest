package JavaStudy.java8.testStream;

import JavaStudy.java8.pojo.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author
 * https://blog.csdn.net/Hello_World_QWP/article/details/80251248
 * 创建 Stream
 */
public class TestStreamAPI1 {


    @Test
    public void test1() {
        //1. 可以通过Collection 系列集合stream() 或parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.通过Arrays 的静态方法(array);
        Employee[] employees = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(employees);

        //3.通过Stream 类中的静态方法of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        //4.创建无限流
        //迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.limit(10)
                .forEach(System.out::println);
        //生成
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);

    }
}
