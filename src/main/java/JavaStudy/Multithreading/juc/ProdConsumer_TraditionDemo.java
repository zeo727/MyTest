package JavaStudy.Multithreading.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();

        try {
            //1 判断
            while (number != 0) {   // 永远在while循环里而不是if语句下使用wait。这样，循环会在线程睡眠前后都检查wait的条件,并在条件实际上并未改变的情况下处理唤醒通知。
                                    // 当线程wait之后，又被唤醒的时候，是从wait后面开始执行，而不是又从头开始执行的，如果用if的话，被唤醒之后就不会在判断if中的条件，而是继续往下执行了
                // 等待，不能生产
                condition.await();
            }
            //2.干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3 通知唤醒
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void decrement() throws Exception {
        lock.lock();

        try {
            //1 判断
            while (number == 0) {
                // 等待，不能生产
                condition.await();
            }
            //2.干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3 通知唤醒
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}

/**
 * 题目：一个初始值为零的变量，两个线程对其交替操作，一个加1一个减1
 * <p>
 * 传统方式
 * 1   线程  操作       资源类
 * 2   判断  干活     通知
 * 3   防止虚假唤醒机制
 */
public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
        for (int i = 1; i <= 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
    }
}
