package JavaStudy;

import org.junit.Test;

/**
 * 拆箱装箱
 *
 * @author https://bbs.csdn.net/topics/280075473
 * https://www.cnblogs.com/dolphin0520/p/3780005.html
 * <p>
 * Integer i1 = 100; //100不是自动转化成 new Integer(100); 而是自动转化成Integer.valueOf(100)
 * 因为从-127到128之间的Integer数，Java在Integer中有事先缓存好的原型对象，
 * 每次返回的都是同一个Integer对象，只有不在这个范围的数才会new出一个Integer,
 *
 * 基本型和基本型封装型进行“==”运算符的比较，基本型封装型将会自动拆箱变为基本型后再进行比较
 * https://blog.csdn.net/qq_34801169/article/details/81735902
 *
 *
 */
public class BoxingUnboxing {
    public static void main(String[] args) {
        Integer i = 10;  //装箱
        int n = i;   //拆箱

        Integer.valueOf(100);
        Double.valueOf(100.0);

    }

    @Test
    public void TestBoolean() {
        Boolean i1 = false;
        Boolean i2 = false;
        Boolean i3 = true;
        Boolean i4 = true;

        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
    }

    @Test
    public void TestDouble() {

        Double i1 = 100.0;
        Double i2 = 100.0;
        Double i3 = 200.0;
        Double i4 = 200.0;

        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
    }

    @Test
    public void TestInteger() {
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;

        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
    }

    @Test
    public void TestLong() {
        Long a = new Long(3);
        Long b = 3L;
        int c = 3;
        System.out.println(a == b);
        System.out.println(a == c);

    }
}