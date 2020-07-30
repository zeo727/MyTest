package JavaStudy.java8.testStream;

import JavaStudy.java8.pojo.Employee;
import JavaStudy.java8.pojo.Status;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author https://blog.csdn.net/Hello_World_QWP/article/details/80251499
 * https://blog.csdn.net/Hello_World_QWP/article/details/80256722
 */
public class TestStreamAPI3 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.00, Status.FREE),
            new Employee("李四", 38, 5555.00, Status.BUSY),
            new Employee("王五", 50, 6666.00, Status.VOCATION),
            new Employee("赵六", 16, 3333.00, Status.FREE),
            new Employee("田七", 8, 7777.00, Status.BUSY),
            new Employee("田七", 8, 7777.00, Status.BUSY)
    );

    /**
     * allMatch(Predicate p) 检查是否匹配所有元素
     * anyMatch( (Predicate p) ) 检查是否至少匹配一个元素
     * noneMatch(Predicate p) 检查是否没有匹配所有元素
     * findFirst() 返回第一个元素
     * findAny() 返回当前流中的任意元素
     * count() 返回流中元素总数
     * max(Comparator c c) ) 返回流中最大值
     * min(Comparator c c) ) 返回流中最小值
     * forEach(Consumer c c) ) 内部迭代 ( (用 使用  Collection  接口需要用户去做迭代，称为 外部迭代 。相反， Stream API  使用内部迭代 —— 它帮你把迭代做了) )
     * concat(Stream s1,Stream s2)/Stream.of(stream1, stream2, stream3)  合并流
     */
    @Test
    public void test1() {
        boolean b1 = employees.stream()
                .allMatch(e -> e.getStatus().equals(Status.BUSY));
        System.out.println(b1);
        boolean b2 = employees.stream()
                .anyMatch(e -> e.getStatus().equals(Status.BUSY));
        System.out.println(b2);
        boolean b3 = employees.stream()
                .noneMatch(e -> e.getStatus().equals(Status.BUSY));
        System.out.println(b3);

        Optional<Employee> optional = employees.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();

        System.out.println(optional.get());
        Optional<Employee> op2 = employees.parallelStream()
                .filter(e -> e.getStatus().equals(Status.FREE))
                .findAny();
        System.out.println(op2.get());
    }

    @Test
    public void test2() {
        long count = employees.stream().count();
        System.out.println(count);
        Optional<Employee> max = employees.stream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max);

        Optional<Double> min = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compareTo);
        System.out.println(min.get());
    }

    /**
     * 归约
     * reduce(T iden, BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。 返回 T
     * reduce(BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。 返回 Optional<T>
     */
    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        System.out.println("----------------------");

        Optional<Double> op = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(op.get());
    }

    /**
     * 搜集
     * collect(Collector c) 将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法。
     */

    @Test
    public void test4() {
        List<String> list = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        list.forEach(System.out::println);
        System.out.println("----------------------");

        Set<String> set = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());

        set.forEach(System.out::println);
        System.out.println("----------------------");

        HashSet<String> hashSet = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));

        hashSet.forEach(System.out::println);

    }

    @Test
    public void test5() {
        //总数
        Long count = employees.stream()
                .collect(Collectors.counting());
        System.out.println(count);
        System.out.println("----------------------");

        //平均值
        Double avg = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);
        System.out.println("----------------------");

        //总和
        Double sum = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);
        System.out.println("----------------------");

        //最大值
        Optional<Employee> max = employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(max.get());
        System.out.println("----------------------");

        //最大值
        Optional<Double> min = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(min.get());
    }

    //分组
    @Test
    public void test6() {
        Map<Status, List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        for (Map.Entry<Status, List<Employee>> entry : map.entrySet()) {
            for (Employee employee : entry.getValue()) {
                System.out.println(entry.getKey() + ":" + employee);
            }
        }
    }

    //多级分组
    @Test
    public void test7() {
        Map<Status, Map<String, List<Employee>>> mapMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                    if (((Employee) e).getAge() <= 35) {
                        return "青年";
                    } else if (((Employee) e).getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(mapMap);

    }

    //分区
    @Test
    public void test8() {
        Map<Boolean, List<Employee>> map = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 8000));
        System.out.println(map);
    }

    @Test
    public void test9() {
        DoubleSummaryStatistics dss = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getMax());
        System.out.println(dss.getAverage());
    }

    @Test
    public void test10() {
        String s = employees.stream()
                .map(employee -> employee.getName())
                .collect(Collectors.joining(",","---","---"));
        System.out.println(s);
    }
}


