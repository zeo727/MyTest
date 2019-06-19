package sharp_parent_test.common;

public class SubStringTest {
    public static void main(String[] args) {
        int prefixCommandLen = 4;
        String body = "f128000000000003ff00f000020000000000000774000000000000000000000000000000000000000000";
        int len = 2 * Integer.valueOf(body.substring(2, 3), 16) * 16 + 2 * Integer.valueOf(body.substring(3, 4), 16)
                + prefixCommandLen;
        String s = body.substring(3, 4);
        int i = Integer.valueOf(body.substring(2, 3), 16);
        System.out.println(i);
        System.out.println(s);
        System.out.println(len);
        System.out.println(body.length());
        int[] arr = {1, 2, 3};
        int a = arr.length;

    }
}
