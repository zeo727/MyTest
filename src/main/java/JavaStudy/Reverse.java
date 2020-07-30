package JavaStudy;

/**
 * Java中String对象的不可变性
 *
 * @author https://www.cnblogs.com/qingergege/p/5701011.html
 *
 * string.intern()方法
 * https://www.cnblogs.com/paddix/p/5309550.html
 */
public class Reverse {
    public static void main(String[] args) {
        String c1 = new String("abc");
        String c2 = new String("abc");

        String c3 = c1;

        System.out.println("c1==c2:" + (c1 == c2));
        System.out.println("c1.equals(c2):" + c1.equals(c2));
        System.out.println("c3==c1:" + (c3 == c1));
        System.out.println("c1.equals(c3):" + c1.equals(c3));
        c1 = "han";
        System.out.println(c1 + "   " + c3);
        System.out.println("" + (c3 == c1));

        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
    private static <T extends Number & Comparable<? super T>> T min(T[] values) {
        if (values == null || values.length == 0) return null;
        T min = values[0];
        for (int i = 1; i < values.length; i++) {
            if (min.compareTo(values[i]) > 0) min = values[i];
        }
        return min;
    }
}