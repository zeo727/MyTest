package JavaStudy;

import java.util.ArrayList;
import java.util.Arrays;

public class Demo {
    private int a;

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int[] a = {0, 0, 0, 0};
        Demo demo = new Demo();
        demo.aaa();
        Object object = new Object();
        object.hashCode();

        String s = new String();
        s.hashCode();

        ArrayList list = new ArrayList();
        list.add(1);
        try {
            System.out.println(arr[4]);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("数组超出范围");
        }
        System.arraycopy(arr,1,a,2,2);
        for (int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
        System.out.println("---------------");

        for (int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
        System.out.println("---------------");

        int[] b = Arrays.copyOf(arr, 10);
        for (int i=0;i<b.length;i++){
            System.out.println(b[i]);
        }
    }

    public void aaa() {
        System.out.println(a+1);
    }
}