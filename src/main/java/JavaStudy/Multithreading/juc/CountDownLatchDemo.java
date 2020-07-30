package JavaStudy.Multithreading.juc;

import lombok.Getter;

import java.util.concurrent.CountDownLatch;

enum CountryEnum {
    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "赵"), FIVE(5, "魏"), SIX(6, "韩");

    @Getter
    private Integer retCode;
    @Getter
    private String retMessage;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum forEach_CountryEnum(int index) {
        CountryEnum[] myArray = CountryEnum.values();
        for (CountryEnum element : myArray
        ) {
            if (index == element.getRetCode()){
                return element;
            }

        }
        return null;
    }
}

//CountDownLatch其实可以把它看作一个计数器，只不过这个计数器的操作是原子操作，是同时只能有一个线程去减这个计数器里面的值。
//  可以向CountDownLatch对象设置一个初始的数字作为计数值，任何调用这个对象上的await()方法都会阻塞，直到这个计数器的计数值被其他的线程减为0为止。
public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLathch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 国被灭");
                countDownLathch.countDown();
            },  CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }

        countDownLathch.await();
        System.out.println(Thread.currentThread().getName() + "\t *************秦帝国，一统华夏");
    }


    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLathch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 上完自习，离开教室");
                countDownLathch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLathch.await();
        System.out.println(Thread.currentThread().getName() + "\t *************班长最后关门走人");
    }
}
