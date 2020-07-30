package JavaStudy.Multithreading.jvm;

import java.util.Random;

//堆溢出(OutOfMemoryError:java heap space)
//将堆内存调小
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        String str = "atguigu";

        while (true){
            str += str + new Random().nextInt(111111111)+new Random().nextInt(222222222);
            str.intern();
        }
    }
}
