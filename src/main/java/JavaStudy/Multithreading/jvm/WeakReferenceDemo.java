package JavaStudy.Multithreading.jvm;

import sun.jvm.hotspot.utilities.BitMap;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

//弱引用
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        System.gc();
        System.out.println("==========");

        System.out.println(o1);
        System.out.println(weakReference.get());

        Map<String, SoftReference<BitMap>> im = new HashMap<String, SoftReference<BitMap>>();
    }
}
