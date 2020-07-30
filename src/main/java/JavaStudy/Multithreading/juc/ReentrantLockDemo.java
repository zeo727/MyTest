package JavaStudy.Multithreading.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁（递归锁）
 * 同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，
 * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 * <p>
 * 也就是说，线程可以进入任何一个它已经拥有的锁所同步着的代码块。
 * synchronize是一个典型的可重入锁
 */
public class ReentrantLockDemo {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();
        //暂停主线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----------------------------------------");
        Thread t3 = new Thread(phone, "t3");
        Thread t4 = new Thread(phone, "t4");
        t3.start();
        t4.start();
    }
}

class Phone implements Runnable {
    Lock lock = new ReentrantLock();

    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getId() + "\t invoked sendSMS");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getId() + "#######\t invoked sendEmail");

    }

    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked get()");
            set();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "#######\t invoked set()");
        } finally {
            lock.unlock();
        }
    }
}

