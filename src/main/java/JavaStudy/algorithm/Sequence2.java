package JavaStudy.algorithm;

import java.util.Scanner;

//https://s1.ax1x.com/2020/06/01/tGN0fI.png
//已知连续数列和S，数列个数N，求该数列
public class Sequence2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int S = sc.nextInt();
        int N = sc.nextInt();

        int a = (2 * S - N * N + N) / (2 * N);
        System.out.println(a);

        int b = (2 * S + N * N - N) / (2 * N);
        System.out.println(b);


        System.out.println("a=" + a);
        System.out.println("b=" + b);

        System.out.println("-1");
    }
}


