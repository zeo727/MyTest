package JavaStudy;

/**
 * final关键字
 *
 * @author https://www.cnblogs.com/xuelisheng/p/11158110.html
 */
public class Final {
    public static void main(String[] args) {
        test1();
        test2();

    }

    /**
     * 当final变量是基本数据类型以及String类型时，如果在编译期间能知道它的确切值，则编译器会把它当做编译期常量使用。也就是说在用到该
     * final变量的地方，相当于直接访问的这个常量，不需要在运行时确定。
     */
    public static void test1() {
        String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String c = b + 2;
        String e = d + 2;
        System.out.println((a == c));
        System.out.println((a == e));
    }

    /**
     * 使用getHello()方法对其进行初始化，他要在运行期才能知道其值。
     */
    public static void test2() {

        String a = "hello2";
        final String b = getHello();
        String c = b + 2;
        System.out.println((a == c));

    }

    /**
     * 这说明引用变量被final修饰之后，虽然不能再指向其他对象，但是它指向的对象的内容是可变的。
     */
    public static void test3(){
        final MyClass myClass = new MyClass();
        System.out.println(++myClass.i);
    }

    public static String getHello() {
        return "hello";
    }
}

class MyClass {
    public int i = 0;
}



