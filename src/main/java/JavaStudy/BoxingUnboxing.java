package JavaStudy;

/**
 * 拆箱装箱
 * @author
 * https://bbs.csdn.net/topics/280075473
 * https://www.cnblogs.com/dolphin0520/p/3780005.html
 *
 * Integer i1 = 100; //100不是自动转化成 new Integer(100); 而是自动转化成Integer.valueOf(100)
 * 因为从-127到128之间的Integer数，Java在Integer中有事先缓存好的原型对象，
 * 每次返回的都是同一个Integer对象，只有不在这个范围的数才会new出一个Integer,
 */
public class BoxingUnboxing {
    public static void main(String[] args) {
        Integer i = 10;  //装箱
        int n = i;   //拆箱

        TestInteger();
        TestDouble();
        TestBoolean();
        Integer.valueOf(100);
        Double.valueOf(100.0);

    }

    private static void TestBoolean() {
        Boolean i1 = false;
        Boolean i2 = false;
        Boolean i3 = true;
        Boolean i4 = true;

        System.out.println(i1==i2);
        System.out.println(i3==i4);
    }

    private static void TestDouble() {

        Double i1 = 100.0;
        Double i2 = 100.0;
        Double i3 = 200.0;
        Double i4 = 200.0;

        System.out.println(i1==i2);
        System.out.println(i3==i4);
    }

    private static void TestInteger() {
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;

        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
    }
}