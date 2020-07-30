package JavaStudy.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//https://blog.csdn.net/shoushou_/article/details/103097609
public class TheLastDeleteNumber2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n > 1000) {
                n = 999;
            }
            List<Integer> list = new ArrayList<>();
            int i = 0;
            for (i = 0; i < n; i++) {
                list.add(i);
            }
            while (list.size() > 1) {
                i = (i + 2) % list.size();
                list.remove(i);
            }
            System.out.println(list.get(0));
        }
    }
}
