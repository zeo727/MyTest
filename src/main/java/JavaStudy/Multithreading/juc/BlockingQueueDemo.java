package JavaStudy.Multithreading.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * https://blog.csdn.net/weixin_39879073/article/details/93379162
 * https://www.cnblogs.com/xdcat/p/12981188.html
 *
 * ArrayBlockingQueue是一个由数组结构组成的有界队列。此队列按照FIFO(先进先出)的顺序进行排序。支持公平锁和非公平锁，默认非公平锁。
 * LinkedBlockingQueue是基于单向链表的、有界的、遵循FIFO原则的阻塞队列,吞吐量通常要高于ArrayBlockingQueen
 *
 * 1 队列
 *
 * 2.阻塞队列
 *  2.1 阻塞队列有没有好的一面
 *
 *  2.2 不得不阻塞，你如何管理
 */

/**
 * BlockingQueue中API介绍
 * offer(E e): 将给定的元素设置到队列中，如果设置成功返回true, 否则返回false. e的值不能为空，否则抛出空指针异常。
 * offer(E e, long timeout, TimeUnit unit): 将给定元素在给定的时间内设置到队列中，如果设置成功返回true, 否则返回false.
 * add(E e): 将给定元素设置到队列中，如果设置成功返回true, 否则抛出异常。如果是往限定了长度的队列中设置值，推荐使用offer()方法。
 * put(E e): 将元素设置到队列中，如果队列中没有多余的空间，该方法会一直阻塞，直到队列中有多余的空间。
 * take(): 从队列中获取值，如果队列中没有值，线程会一直阻塞，直到队列中有值，并且该方法取得了该值。
 * poll(long timeout, TimeUnit unit): 在给定的时间里，从队列中获取值，如果没有取到会抛出异常。
 * remainingCapacity()：获取队列中剩余的空间。
 * remove(Object o): 从队列中移除指定的值。
 * contains(Object o): 判断队列中是否拥有该值。
 * drainTo(Collection c): 将队列中值，全部移除，并发设置到给定的集合中。
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());

    }
}
