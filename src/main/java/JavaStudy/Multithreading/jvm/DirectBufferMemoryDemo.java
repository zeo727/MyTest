package JavaStudy.Multithreading.jvm;

import java.nio.ByteBuffer;

/**
 * Direct buffer memory:主要应NIO引起的。
 *
 * 原因：
 *
 * Java8出现了NIO，缓存，通道，选择器。在 写NIO程序的时候，经常使用ByteBuffer来读或者写入数据,这是一种基于通道( Channel)与
 * 缓冲区( Buffer)的I/0方式.它可以使用 Native函数库直接分配堆外内存,然后通过一个存做在Java里面的 DirectByteBuffer对作为这块内存的引用
 * 进行操作。可以提高性能,因为避免了在Java堆和 Native堆中来回复制数据。
 *
 * ByteBuffer. allocate(capability)第一种方式是分配java堆内存,属于GC管理范围,由于需要进行拷贝，所以比较慢。
 * ByteBuffer. allocteDirect (capability)第一种方式是分配操作系统的本地内存,不属于GC管辖范围,由于不需要内存拷贝所以速度相对较快。
 * 但如果不断分配本地内存,堆内存很少使用,那么java虚拟机就不需要进行GC, DirectByteBuffer对象们就不会被回收,这个时候堆内存充足,
 * 但本地内存可能已经使用光了,在尝试分配本地内存就会出0ut0fMemory Error,那程序就直接奔溃了。
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory:"+(sun.misc.VM.maxDirectMemory()/(double)1024/1024)+"MB");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //配置-XX:MaxDirectMemorySize=5m 实际使用6M
        ByteBuffer bb = ByteBuffer.allocateDirect(6*1024*1024);
    }
}
