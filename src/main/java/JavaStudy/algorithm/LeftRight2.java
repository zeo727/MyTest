package JavaStudy.algorithm;

import java.util.Arrays;

//给定一个int数组=[6，4，-3，5，-2，-1，0，1，-9]，将所有正数向左移动，并将所有负数向右移动。尽量使其时间复杂度为O（n），空间复杂度为O（1）。
public class LeftRight2 {

    public static void main(String[] args) {
        int[] arr = {6,4,-3,5,-2,-1,0,1,-9};
        int[] sort = move(arr);
        System.out.println(Arrays.toString(sort));
    }

    private static int[] move(int[] arr){
        int x = 0;
        int temp = 0; // 数组正数个数
        int count0 = 0; // 数组0的个数
        int[] arr2 = new int[arr.length];

        for (int value : arr) {
            if (value > 0){
                temp ++;  // temp 4
            }
            if (value == 0) {
                count0 ++; // count0  1
            }
        }
        for (int value : arr) {
            if (count0 != 0) {
                arr2[temp++] = 0;
                count0 --;
            }
            if (value > 0) {
                arr2[x++] = value;
            }
            if (value < 0) {
                arr2[temp++] = value;
            }
        }
        return arr2;
    }
}