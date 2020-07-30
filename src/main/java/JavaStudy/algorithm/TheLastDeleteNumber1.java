package JavaStudy.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * https://blog.csdn.net/weixin_45481403/article/details/103623819
 * 题目描述：
 * 有一个数组a[N]顺序存放0~N-1,要求每隔两个数删掉一个数，到末尾时循环至开
 * 头继续进行，求最后一个被删掉的数的原始下标位置。以8个数(N=7)为例: {0,1, 2, 3, 4, 5, 6, 7}，0->1->2(删除)->3->4->5<(删除)->6->7->0(删除)，如此循环直到最后一个数被删除。
 *
 * 输入描述:
 * 每组数据为一行一个整数n(小于等于1000)，为数组成员数,如果大于1000，则对a[999]进行计算。
 * 输出描述:
 * 一行输出最后一个被删掉的数的原始下标位置。
 *
 * 示例1 输入输出示例仅供调试， 后台判题数据一般不包含示例
 * 输入
 * 8
 * 输出
 * 6
 *
 */
public class TheLastDeleteNumber1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i;
            }
            System.out.println(delete(arr));
        }
        scanner.close();
    }

    public static int delete (int[] arr) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        while (queue.size() != 1) {
            int count = 0;
            while (count != 2) {
                queue.add(queue.peek());  //queue.peek()) //返回第一个元素
                queue.poll();  //返回第一个元素，并在队列中删除
                count++;
            }
            queue.poll();
        }
        return queue.element();
    }
}
