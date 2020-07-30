package JavaStudy.Multithreading.jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

//引用队列机制，软引用，弱引用，虚引用（必须）回收前先在引用队列保存一下
public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1,referenceQueue);

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("=============");
        o1 = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }
}
