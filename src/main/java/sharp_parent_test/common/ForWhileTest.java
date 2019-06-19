package sharp_parent_test.common;

public class ForWhileTest {
    public static void main(String[] args) {
        for (int i = 100; i >= 0; i = i - 10) {
            System.out.println(i);
            break;
        }
        int a = 0;
        int i;
        for (i = 0; i < 10; i++) {
            System.out.println("循环中：i=" + i);
        }
        System.out.println("循环结束：i=" + i);
        while (a < 10) {

            System.out.println("循环中：a=" + a);
            a++;
        }
        System.out.println("循环结束：a=" + a);

    }
}
