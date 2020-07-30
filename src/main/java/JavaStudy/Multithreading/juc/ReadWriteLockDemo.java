package JavaStudy.Multithreading.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多个线程同时读一个资源类没有任何问题，所以为了并发量，读取共享资源应该可以同时进行。
 * 但是
 * 如果有一个线程想去写共享资源来，就不应该再有其它线程可以对该资源进行读或写
 * 小总结：
 * 读-读能共存
 * 读-写不能共存
 * 写-写不能共存
 * <p>
 * 写操作：原子独占,整个过程必须是一个完整的统一体，中间不许被分割，被打断
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 1; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt + "");
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt + "");
            }, String.valueOf(i)).start();
        }
    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        rwlock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
            //暂停一会线程
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);

            System.out.println(Thread.currentThread().getName() + "\t 写入完成： ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwlock.writeLock().unlock();
        }

    }

    public void get(String key) {
        rwlock.readLock().lock();

        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwlock.readLock().unlock();
        }
        System.out.println(Thread.currentThread().getName() + "\t 正在读取：" + key);
        //暂停一会线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.get(key);
        System.out.println(Thread.currentThread().getName() + "\t 读取完成：");
    }

    public void clearMap() {
        map.clear();
    }
}

