package JavaStudy.java8.testLambda2;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * lambda表达式
 *
 * @author https://blog.csdn.net/Hello_World_QWP/article/details/80245356
 *
 * Lambda 表达式的基础语法：Java8中引入了一个新的操作符 “->” 该操作符称为箭头操作符或 Lambda 操作符 箭头操作符将 Lambda
 * 表达式拆分成两部分：
 *
 * 左侧：Lambda 表达式的参数列表
 * 右侧：Lambda 表达式中所需执行的功能， 即 Lambda 体
 *
 * Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，
 * 即“类型推断” (Integer x, Integer y) -> Integer.compare(x, y);
 *
 * 关于类型推断：
 * 在 Lambda 表达式中的参数类型都是由编译器推断得出的。 Lambda 表达式中无需指定类型，程序依然可以编译，这是因为 javac 根据程序的上下文，
 * 在后台推断出了参数的类型。 Lambda 表达式的类型依赖于上下文环境，是由编译器推断出来的。这就是所谓的“类型推断”。
 *
 * Lambda 表达式需要“函数式接口”的支持 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。
 * 可以使用注解 @FunctionalInterface 修饰 可以检查是否是函数式接口
 */
public class TestLambda1 {
    @Test
    public void test1() {
        int num = 0; //jdk 1.8以前内部类应用同级别变量必须是final，1.8默认final
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World" + num);
            }
        };
        runnable1.run();

        System.out.println("-------------------");
        Runnable runnable2 = () -> System.out.println("无参数，无返回值 () ");
        runnable2.run();
    }


    @Test
    public void test2() {
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("1个参数无返回值，参数小括号可以省略。eg:x ->  ");
    }

    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("有返回值，lambda体中有多条语句，需要用{}，只有一条语句return可以省略");
            return Integer.compare(x, y);
        };

    }
    @Test
    public void test4() {
        Comparator<Integer> comparator = (x, y) ->  Integer.compare(x, y);
    }

    //类型推断
    @Test
    public void test5() {
//        String[] strings;
//        strings ={"aaa","bbb","ccc"};
        List<String> list =new ArrayList<>();
        show(new HashMap<>());
    }
    public void show(Map<String,Integer> map){

    }

    @Test
    public void test6() {

        Integer num = operation(100,x -> x*x);
        System.out.println(num);

        System.out.println(operation(200,y -> y+200));
    }
    public Integer operation(Integer num,MyFunction1 mf){
        return mf.getValue(num);
    }
}
