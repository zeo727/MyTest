package JavaStudy.algorithm;

import java.util.ArrayList;
import java.util.Scanner;

//https://s1.ax1x.com/2020/06/01/tGN0fI.png
//已知连续数列和S，数列个数N，求该数列
public class Sequence1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int s = scanner.nextInt();
            int n = scanner.nextInt();

            ArrayList<Integer> arrayList = FindContinuousSequence(s, n);
            if (arrayList.equals(-1)) {
                System.out.println("-1");
            } else {
                System.out.println(arrayList);
            }

        }
    }
    public static ArrayList<Integer>  FindContinuousSequence(int s, int n) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int x = 1; x < s; x++) {
            if ((2 * x + n - 1) * n == 2 * s) {
                for (int i = x; i <= x + n - 1; i++) {
                    arrayList.add(i);
                    if (i ==x+n-1){
                        return arrayList;
                    }
                }
            }else {

                arrayList.add(-1);
                return arrayList;
            }
        }
        return arrayList;
    }
}