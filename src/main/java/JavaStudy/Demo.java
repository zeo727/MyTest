package JavaStudy;

import java.util.ArrayList;

public class Demo {
    private int a;
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        Demo demo = new Demo();
        demo.bbb();
        Object object = new Object();
        object.hashCode();

        String s =new String();
        s.hashCode();

        ArrayList list = new ArrayList();
        list.add(1);
        try {
            System.out.print(arr[4]);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("数组超出范围");
        }
    }

    public void aaa(int i){
        System.out.println(i);

    }
    public void bbb(){
        aaa(a+1);
    }
}