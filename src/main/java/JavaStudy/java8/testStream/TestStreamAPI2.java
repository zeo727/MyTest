package JavaStudy.java8.testStream;

import JavaStudy.java8.pojo.Employee;
import JavaStudy.java8.pojo.Status;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author https://blog.csdn.net/Hello_World_QWP/article/details/80251248
 */

public class TestStreamAPI2 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.00, Status.BUSY),
            new Employee("李四", 18, 5555.00,Status.BUSY),
            new Employee("王五", 50, 6666.00,Status.BUSY),
            new Employee("赵六", 16, 3333.00,Status.BUSY),
            new Employee("田七", 8, 7777.00,Status.BUSY),
            new Employee("田七", 8, 7777.00,Status.BUSY),
            new Employee("田七", 8, 7777.00,Status.BUSY)
    );

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();

        for (Character character : str.toCharArray()) {
            list.add(character);
        }
        return list.stream();
    }

    /**
     * 筛选与切片：
     * filter(Predicate p p) 接收 Lambda，从流中排除某些元素
     * distinct() 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     * limit(long maxSize) 截断流，使其元素不超过给定数量
     * skip(long n) 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
     */

    //内部迭代：迭代操作由Stream API完成
    @Test
    public void test1() {
        //中间操作:不会执行任何操作
        Stream<Employee> stream = employees.stream()
                .filter(e -> {
                    System.out.println("Stream API 的中间操作");
                    return e.getAge() > 35;
                });
        //终止操作：一次性执行全部内容，即"惰性求值"
        stream.forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test2() {
        Iterator<Employee> iterable = employees.iterator();

        while (iterable.hasNext()) {
            System.out.println(iterable.next());
        }
    }

    @Test
    public void test3() {
        employees.stream()
                .filter(e -> {
                    System.out.println("短路！");
                    return e.getSalary() > 5000;
                })
                .limit(2)
                .forEach(System.out::println);

    }

    @Test
    public void test4() {
        employees.stream()
                .filter(e -> e.getSalary() > 5000)
                .skip(2)
                .distinct()
                .forEach(System.out::println);

    }

    /**
     * 映射
     * map(Function f) 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     * mapToDouble(ToDoubleFunction f) 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 DoubleStream
     * flatMap(Function f) 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */

    @Test
    public void test5() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        list.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
        System.out.println("-------------------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
        System.out.println("-------------------");

//        Stream<Stream<Character>> streamStream = list.stream()
//                .map(TestStreamAPI2::filterCharacter);
//        streamStream.forEach(sm ->{
//            sm.forEach(System.out::println);
//        });
        System.out.println("-------------------");

        Stream<Character> characterStream = list.stream()
                .flatMap(TestStreamAPI2::filterCharacter);
        characterStream.forEach(System.out::println);


    }

    @Test
    public void test6() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        List list1 = new ArrayList();

        list1.add(11);
        list1.add(22);
        //list1.add(list);
        list1.addAll(list);

        System.out.println(list1);

    }

    /**
     * 排序
     * sorted() 产生一个新流，其中按自然顺序(Comparable)排序
     * sorted(Comparator comp)  产生一个新流，其中按比较器(Comparator)顺序排序
     */

    @Test
    public void test7() {
        List<String> list = Arrays.asList("ccc", "aaa", "bbb", "ddd", "eee");
        list.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("-------------------");

        employees.stream()
                .sorted((e1,e2) -> {
                    if (e1.getAge().equals(e2.getAge())){
                        int i = e1.getName().compareTo(e2.getName());
                        System.out.println(e1.getName()+e2.getName());
                        System.out.println(i);
                        return i;
                    }else {
                        return -e1.getAge().compareTo(e2.getAge());
                    }
                }).forEach(System.out::println);

    }
}

