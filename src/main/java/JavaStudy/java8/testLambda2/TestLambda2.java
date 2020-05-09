package JavaStudy.java8.testLambda2;

import JavaStudy.java8.pojo.Employee;
import JavaStudy.java8.pojo.Status;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//lambda表达式练习
public class TestLambda2 {
    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.00, Status.BUSY),
            new Employee("李四", 38, 5555.00,Status.BUSY),
            new Employee("王五", 50, 6666.00,Status.BUSY),
            new Employee("赵六", 16, 3333.00,Status.BUSY),
            new Employee("田七", 8, 7777.00,Status.BUSY)
    );
    @Test
    public void test1(){
        Collections.sort(employees,(e1,e2) -> {
            if(e1.getAge().equals(e2.getAge())){
                return e1.getName().compareTo(e2.getName());
            }else {
                //倒序
                return -Integer.compare(e1.getAge(),e2.getAge());
            }
        });
        for (Employee employee:employees){
            System.out.println(employee);
        }
    }

    @Test
    public void test2() {
        String string1  = strHandler("string",s -> s.toUpperCase());
        System.out.println(string1);
        String string2 = strHandler(string1,s -> s.substring(2,5));
        System.out.println(string2);
    }
    //处理字符串
    public String strHandler(String string,MyFunction2 myFunction2){
        return myFunction2.getValue(string);
    }

    @Test
    public void test3() {
        op(100L,200L,(x,y) -> x+y);

    }

    //Long运算
    public void op(Long l1,Long l2,MyFunction3<Long,Long> myFunction3){
        System.out.println(myFunction3.getValue(l1,l2));
    }
}
