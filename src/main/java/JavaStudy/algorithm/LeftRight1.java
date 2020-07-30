package JavaStudy.algorithm;

import java.util.Arrays;

//https://blog.csdn.net/z275598733/article/details/101459629
//给定一个int数组，将正数移动到数组左边，负数移动到数组右边
public class LeftRight1 {
    public static void main(String[] args) {
        Integer[] arr =  {8, 6, 4, -3, 5, -2, -1, 0, 1, -9, 1, -1};
        for (int i = 0,j = 0; i < arr.length; i++) {
            if(arr[i]>=0) { //当前值大于等于0，自己与自己交换。或者当j指针指向负数时，与j指针对应的数交换
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++; //大于等于0，指针继续走，遇到负数停下，等待下一个正数然后交换。
            }
        }
        System.out.println(Arrays.asList(arr));

    }
}
