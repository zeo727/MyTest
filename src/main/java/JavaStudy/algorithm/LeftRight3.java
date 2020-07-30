package JavaStudy.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://blog.csdn.net/PitBXu/article/details/97672145
//int []数组与List互相转换
public class LeftRight3 {
    public static void main(String[] args) {
        int[] arrs = {6, 4, -3, 5, -2, -1, 0, 1, -9};
        //List<Integer> list = Arrays.stream(arrs).boxed().collect(Collectors.toList());

        List<Integer> left = new ArrayList();
        List<Integer> right = new ArrayList();

        for (int i : arrs
        ) {
            if (i > 0) {
                left.add(i);
            } else if (i == 0) {
                right.add(0,i);
            }else {
                right.add(i);
            }
        }
        left.addAll(right);
        int[] newArrs = left.stream().mapToInt(Integer::valueOf).toArray();
        System.out.print(Arrays.toString(newArrs));

    }
}
