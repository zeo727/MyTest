package sharp_parent_test.common;

import java.util.concurrent.ThreadLocalRandom;

public class randomDataTest {
    public static void main(String[] args) {
        System.out.println(randomData(35));
    }

    private static int randomData(int max) {
        int s = ThreadLocalRandom.current().nextInt(max);
        return s;
    }
}
