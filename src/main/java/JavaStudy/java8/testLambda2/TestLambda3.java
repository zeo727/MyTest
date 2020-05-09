package JavaStudy.java8.testLambda2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * lambda表达式
 * Java8 内置的四大核心函数式接口
 * 了解更多的函数式接口，可以到Java8开发文档中进行查看
 *
 * @author https://blog.csdn.net/Hello_World_QWP/article/details/80245464
 */
public class TestLambda3 {
    //Consumer<T> 消费型接口：
    @Test
    public void test1() {
        happy(10000, m -> System.out.println("消费" + m + "元"));
    }

    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    //Supplier<T> 供给型接口：
    @Test
    public void test2() {
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));

        for (Integer num : numList) {
            System.out.println(num);
        }
    }

    //产生指定个数整数，并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = supplier.get();
            System.out.println(n);
            list.add(n);
        }
        return list;
    }

    //Function<T,R> 函数型接口：
    @Test
    public void test3() {
        String newStr = strHandler("\t\t\t 开开心心", s -> s.trim());
        System.out.println(newStr);

        String subStr = strHandler(newStr, s -> s.substring(2, 5));
        System.out.println(subStr);
    }

    //处理字符串
    public String strHandler(String string, Function<String, String> function) {
        return function.apply(string);
    }

    //Predicate<T> 断言型接口：
    @Test
    public void test4() {
        List<String> list = Arrays.asList("Hello","Lambda","www","ok");
        List<String> stringlist = filterStr(list,s ->s.length()>3);

        for (String string : stringlist) {
            System.out.println(string);
        }

    }

    //将满足条件的字符串放入集合
    public List<String> filterStr(List<String> list, Predicate<String> predicate){
        List<String> stringList = new ArrayList<>();

        for (String string : list){
            if (predicate.test(string)){
                stringList.add(string);
            }
        }
        return stringList;
    }


}
