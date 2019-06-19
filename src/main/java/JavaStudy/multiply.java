package JavaStudy;

public class multiply {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {//i是一个乘数
            for (int j = 0; j <= i; j++) {//j是另外一个乘数
                System.out.print(j + "*" + i + "=" + (i * j < 10 ? (" " + i * j) : i * j) + " ");
            }
            System.out.println();
        }
    }
}