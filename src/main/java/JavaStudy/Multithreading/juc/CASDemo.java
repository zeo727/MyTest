package JavaStudy.Multithreading.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS --->UnSafe ---> CAS底层思想 ---> ABA --->原子引用更新 ---> 如何规避ABA问题
 * https://blog.csdn.net/qq_41950229/article/details/106332389
 *  1 CAS是什么？ ===>compareAndSwap
 *  比较并交换
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        //main() do thing;
        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t current data:"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024)+"\t current data:"+atomicInteger.get());

        atomicInteger.getAndIncrement();
    }
}
