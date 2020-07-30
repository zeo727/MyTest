package JavaStudy.Multithreading.juc;

//JUC多线程及高并发：对volatile的理解
//https://blog.csdn.net/qq784515681/article/details/90512031
public class SingletonDemo {
    private static volatile SingletonDemo instance = null;

    public SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法SingletonDemo()");
    }

    /**
     * DCL(双端检索)机制不一定线程安全，原因是有指令重排序的存在，加入volatile可以禁止指令重排
     * @return
     */
    //DCL (Double Check Lock 双端检索机制)
    public static SingletonDemo getInstance() {
        //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (instance == null) {
            //类对象加锁
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

//        //单线程(main线程的操作动作...)
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//
//        System.out.println("--------------------------");

        //并发多线程后，情况发生了变化
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }


    }
}
