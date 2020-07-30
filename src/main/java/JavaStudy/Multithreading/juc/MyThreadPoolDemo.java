package JavaStudy.Multithreading.juc;

import java.util.concurrent.*;

/**
 * 第四种获得/使用java多线程的方式，线程池
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void threadPoolInit() {
        // Array Arrays           接口 辅助工具类
        // Collection Collections
        // Executor Executors

        //ExecutorService threadPool = Executors.newFixedThreadPool(5);  //一池5个处理线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();  //一池1个处理线程
        ExecutorService threadPool = Executors.newCachedThreadPool();  //一池n个处理线程

        // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程

        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
//                try {
//                    TimeUnit.MILLISECONDS.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
