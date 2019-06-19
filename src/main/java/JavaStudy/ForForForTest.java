package JavaStudy;

class ForForTest {
    public static void main(String[] args) {
        for (int x = 1; x <= 5; x++) {
            for (int y = x; y <= 5; y++) {
                System.out.print("*");
            }
            System.out.println();
        }

    }
}