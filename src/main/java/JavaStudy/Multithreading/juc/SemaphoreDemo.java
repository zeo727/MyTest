package JavaStudy.Multithreading.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore是一个计数信号量，它的本质是一个“共享锁”。用于多个共享资源的互斥使用和并发线程数的控制。
 * 信号量维护了一个信号量许可集。线程可以通过调用acquire()来获取信号量的许可；当信号量中有可用的许可时，线程能获取该许可；否则线程必须等待，
 * 直到有可用的许可为止。线程可以通过release()来释放它所持有的信号量许可。
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3); //模拟3个停车位
        for (int i = 1; i <= 6; i++) {  //模拟6部🚘
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢到车位");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t停车3秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }

}
