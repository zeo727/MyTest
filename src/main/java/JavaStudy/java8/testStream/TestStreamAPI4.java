package JavaStudy.java8.testStream;

import JavaStudy.java8.pojo.Employee;
import JavaStudy.java8.pojo.Status;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//练习
public class TestStreamAPI4 {
    /**
     * 1. 给定一个数字列表，返回一个由每个数的平方构成的列表
     */
    @Test
    public void test1() {
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5};

        Arrays.stream(nums)
                .map(x -> x*x)
                .forEach(System.out::println);
    }

    /**
     * 2.使用 map和reduce 求出员工总人数
     */

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.00, Status.FREE),
            new Employee("李四", 38, 5555.00, Status.BUSY),
            new Employee("王五", 50, 6666.00, Status.VOCATION),
            new Employee("赵六", 16, 3333.00, Status.FREE),
            new Employee("田七", 8, 7777.00, Status.BUSY),
            new Employee("田七", 8, 7777.00, Status.BUSY)
    );
    @Test
    public void test2() {
        Optional<Integer> count = employees.stream()
                .map(e -> 1)
                .reduce(Integer::sum);

        System.out.println(count.get());
    }
}
