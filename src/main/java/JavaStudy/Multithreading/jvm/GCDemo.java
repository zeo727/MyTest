package JavaStudy.Multithreading.jvm;

import java.util.Random;

public class GCDemo {
    public static void main(String[] args) {
        System.out.println("********GCDemo hello");
        try {
            String str = "atguigu";
            while (true){
                str += str + new Random().nextInt(7777777)+new Random().nextInt(8888888);
                str.intern();
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
