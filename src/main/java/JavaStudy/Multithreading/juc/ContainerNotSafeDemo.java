package JavaStudy.Multithreading.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        Set<String> set = new HashSet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        /**
         * 1.故障现象
         *      java.util.ConcurrentModificationException
         * 2.导致原因
         * 并发争抢修改导致
         *
         * 3.解决方案
         * 3.1 new Vector<>();
         * 3.2 collections.synchronizedList(new ArrayList<>());
         * 3.3 new CopyOnWriteArrayList<>();
         *
         * 4.优化建议（同样的错误不犯第二次）
         */}
}
