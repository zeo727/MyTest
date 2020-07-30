package JavaStudy.Multithreading.juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 *  https://www.cnblogs.com/duanxz/p/3252267.html
 * SynchronousQueue没有容量，是无缓冲等待队列，是一个不存储元素的阻塞队列，会直接将任务交给消费者，必须等队列中的添加元素被消费后才能继续
 * 添加新的元素。一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

         new Thread(() -> {
             try {
                 System.out.println(Thread.currentThread().getName()+"\t put 1");
                 blockingQueue.put("1");
                 System.out.println(Thread.currentThread().getName()+"\t put 2");
                 blockingQueue.put("2");
                 System.out.println(Thread.currentThread().getName()+"\t put 3");
                 blockingQueue.put("3");
             } catch (Exception e) {
                 e.printStackTrace();
             }

         }, "AAA").start();

        new Thread(() -> {
            try {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "BBB").start();

    }
}
