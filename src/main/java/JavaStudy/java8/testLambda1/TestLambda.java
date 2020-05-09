package JavaStudy.java8.testLambda1;

import JavaStudy.java8.pojo.Employee;
import JavaStudy.java8.pojo.Status;
import org.junit.Test;

import java.util.*;

/**
 * lambda表达式
 * 匿名内部类与lambda表达式对比
 *
 * Java8与以前的版本相比有以下几大特点：
 * 速度更快
 * 代码更少（增加了新的语法 Lambda 表达式）
 * 强大的 Stream API
 * 便于并行
 * 最大化减少空指针异常 Optional
 *
 * @author
 * https://www.bilibili.com/video/BV14W411u7Ly?p=2
 */
public class TestLambda {
    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.00,Status.BUSY),
            new Employee("李四", 38, 5555.00,Status.BUSY),
            new Employee("王五", 50, 6666.00,Status.BUSY),
            new Employee("赵六", 16, 3333.00,Status.BUSY),
            new Employee("田七", 8, 7777.00,Status.BUSY)
    );

    //原来的匿名内部类
    @Test
    public void test1() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> ts = new TreeSet<>(comparator);

    }

    //lambda表达式
    @Test
    public void test2() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(comparator);

    }

    //过滤35岁员工
    @Test
    public void test3() {
        List<Employee> list = filterEmployeesByAge(employees);
        for (Employee employee : list) {
            System.out.println(employee);
        }

    }

    public List<Employee> filterEmployeesByAge(List<Employee> employees) {
        List<Employee> employees1 = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAge() >= 35) {
                employees1.add(employee);
            }
        }
        return employees1;
    }

    public List<Employee> filterEmployeesBySalary(List<Employee> employees) {
        List<Employee> employees1 = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getSalary() >= 5000) {
                employees1.add(employee);
            }
        }
        return employees1;
    }

    public List<Employee> filterEmployees(List<Employee> employees, MyPredicate<Employee> myPredicate) {
        List<Employee> employees1 = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getSalary() >= 5000) {
                employees1.add(employee);
            }
        }
        return employees1;
    }

    //优化方式1：策略设计模式
    @Test
    public void test4() {
        List<Employee> list = filterEmployees(employees, new FilterEmployeesBySalary<Employee>());
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    //优化方式2：匿名内部类
    @Test
    public void test5() {
        List<Employee> list = filterEmployees(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() > 35;
            }
        });
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    //优化方式3：lambda表达式
    @Test
    public void test6() {
        List<Employee> list = filterEmployees(employees, (e) -> e.getSalary() >= 35);
        list.forEach(System.out::println);
    }

    //优化方式4： Stream API
    @Test
    public void test7() {
        employees.stream()
                .filter((e) -> e.getSalary() >= 5000)
                .limit(2)
                .forEach(System.out::println);

        System.out.println("----------------------------------");
        employees.stream().map(Employee::getName)
                .forEach(System.out::println);
    }
}