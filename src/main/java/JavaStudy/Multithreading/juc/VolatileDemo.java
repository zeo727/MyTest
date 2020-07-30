package JavaStudy.Multithreading.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {

    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

    public void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addMyAtomic() {
        atomicInteger.getAndIncrement();
    }

}

/**
 * 1.验证volatile的可见性
 *  1.1 假如 int number = 0; number变量之前根本没有添加volatile关键字修饰
 *  1.2 添加了volatile，可以解决可见性问题。
 *
 * 2.验证volatile不保证原子性
 *  2.1 原子性是什么意思
 *      不可分割，完整性，也即某个线程正在做某个具体业务，中间不可以被加塞或者被分割。需要整体完整，要么同时成功，要么同时失败。
 *  2.2 案例演示
 *  2.3 why
 *  2.4 如何解决原子性？
 *      * 加synchronized
 *      *
 *
 *
 */
public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for(int j = 1;j<=1000;j++){
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }

            }, String.valueOf(i)).start();

            // Thread.join();  方法的作用是调用线程等待该线程完成后，才能继续用下运行。
        }
        //需要等待上面20个线程全部计算完成后，再用main线程取得最终的结果值是多少

        while (Thread.activeCount()>2){
            Thread.yield();//线程让步,使当前线程由执行状态,变成为就绪状态;
        }
        System.out.println(Thread.currentThread().getName() + "\t finally number value:" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type,finally number  value:" +myData.atomicInteger);

    }

    //volatile的可见性,及时通知其他线程，主线程内存的值已经被修改
    private static void seeOkByVolatile() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t updated number value:" + myData.number);

        }, "AAA").start();

        // main线程
        while (myData.number == 0) {
            // main 线程就一直在这里等待循环，直到number值不再等于零
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }
}
