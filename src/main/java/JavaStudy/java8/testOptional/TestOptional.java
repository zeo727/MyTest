package JavaStudy.java8.testOptional;

import JavaStudy.java8.pojo.*;
import org.junit.Test;

import java.util.Optional;

/**
 * optional类
 * @author
 * https://blog.csdn.net/Hello_World_QWP/article/details/80278578
 */
public class TestOptional {
    /**
     * 常用方法：
     * Optional.of(T t) : 创建一个 Optional 实例
     * Optional.empty() : 创建一个空的 Optional 实例
     * Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
     * isPresent() : 判断是否包含值
     * orElse(T t) : 如果调用对象包含值，返回该值，否则返回t
     * orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
     * map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
     * flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
     */

    @Test
    public void test1(){
        Optional<Employee> op = Optional.of(new Employee());
        Employee employee = op.get();
        System.out.println(employee);

    }

    @Test
    public void test2(){
        Optional<Employee> op = Optional.empty();

        System.out.println(op.get());
    }

    @Test
    public void test3(){
        Optional<Employee> op = Optional.ofNullable(null);

//        if (op.isPresent()) {
//            System.out.println(op.get());
//        }

//        Employee employee = op.orElse(new Employee("张三", 18, 888.88, Status.BUSY));
//        System.out.println(employee);
        op.orElseGet(() -> new Employee());
    }

    @Test
    public void test4(){
        Optional<Employee> op = Optional.ofNullable(new Employee("张三", 18, 888.88, Status.BUSY));
//        Optional<String> s = op.map(Employee::getName);
//        System.out.println(s.get());

        Optional<String> s2 = op.flatMap(employee -> Optional.of(employee.getName()));
        System.out.println(s2.get());
    }

    @Test
    public void test5(){
//        Man man = new Man();
//        String n = getGodnessName(man);
//        System.out.println(n);

        Optional<Godness> gn = Optional.ofNullable(new Godness("xxx"));
        Optional<NewMan> op = Optional.ofNullable(new NewMan(gn));
        String s = getGodnessName2(op);
        System.out.println(s);

    }

    public String getGodnessName(Man man){
        if (man != null) {
            Godness godness = man.getGodness();
            if (godness !=null){
                return godness.getName();
            }
        }
        return "zzz";
    }

    public String getGodnessName2(Optional<NewMan> man) {
        return  man.orElse(new NewMan())
                .getGodness()
                .orElse(new Godness("zzz"))
                .getName();

    }
}
