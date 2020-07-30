package JavaStudy.Multithreading.juc;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * java
 * 多线程中，第3种获得多线程的方式
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //FutureTask(Callable<V> callable)
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        //同一个futureTask结果可以复用
        new Thread(futureTask, "AA").start();
        new Thread(futureTask, "BB").start();

        //int result02 = futureTask.get(); // 要求获得Callable线程的计算结果，如果没有计算完成就要去强求，会导致阻塞，直到计算完成，
                                        // get 方法一般放最后

        System.out.println(Thread.currentThread().getName()+"***********");
        int result01 = 100;

        while (!futureTask.isDone()){

        }
        int result02 = futureTask.get();
        System.out.println("*******result:" + (result01 + result02));
    }
}

//class MyThread implements Runnable
//{
//
//    @Override
//    public void run() {
//
//    }
//}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"*******come in callable");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1024;
    }
}