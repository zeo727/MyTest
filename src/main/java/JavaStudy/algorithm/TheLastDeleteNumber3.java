package JavaStudy.algorithm;

import java.util.Scanner;

public class TheLastDeleteNumber3 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        if(len > 999){
            len = 999;
        }
        int lastIndex = getLastIndex(len);
        System.out.println(lastIndex);

    }

    public static  int getLastIndex(int len){

        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = i;
        }

        int isDel= len + 1;  // 删除标志
        int currentSize = len;  // 当前数组长度
        int i = 0;  // 数组循环遍历下标
        int j = 0;
        int lastIndex = 0 ; // 最后被删除的元素的下标

        while (currentSize > 0){
            if (arr[i] != isDel) {
                if(j ++ == 2){
                    arr[i] = isDel;
                    lastIndex = i ;
                    currentSize -- ;
                    j = 0;
                }
            }
            i = (i + 1) % len;
        }
        return  lastIndex;
    }
}