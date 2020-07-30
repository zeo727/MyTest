package JavaStudy.Multithreading.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * java.lang.OutOfMemoryError: GC overhead limit exceeded 异常解决
 * https://blog.csdn.net/baidu_37313657/article/details/105024907
 * 产生原因
 * GC回收时间过长时会抛出OutOfMemroyError,过长的定义是，超过98%的时间用来做GC并且回收了不到2%的堆内存，
 * 连续多次GC 都只回收了不到2%的极端情况下才会抛出（最起码已经进行了5次连续的垃圾回收）。
 * <p>
 * 解决方法
 * 1、增加heap堆内存
 * 2、一般出现这种问题跟内存其实没太大关系，主要还是代码问题，代码中出现了大量占用内存的对象，一定要检查代码，
 *
 * JVM配置，配置小内存，让它内存不够用了导致OOM，看软引用的回收情况
 * -Xms5m -Xmx5m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 */
public class GCOverheadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();

        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            System.out.println("********i:"+i);
            e.printStackTrace();
        }

    }
}
