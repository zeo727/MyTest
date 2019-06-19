package TaskTest;

import java.util.ArrayList;
import java.util.Scanner;

class Execute extends Thread {
    Task1 a1 = new Task1();
    Task2 a2 = new Task2();
    Task3 a3 = new Task3();
    ArrayList list = new ArrayList();

    void Insert() {
        int i;
        Scanner sc = new Scanner(System.in);
        i = sc.nextInt();
        if (i == 1) {
            list.add(a1);
            System.out.println("输入成功，请等待");
        } else if (i == 2) {
            list.add(a2);
            System.out.println("输入成功，请等待");
        } else if (i == 3) {
            list.add(a3);
            System.out.println("输入成功，请等待");
        } else {
            System.out.println("输入错误，请重新输入");
        }
    }

    public void run() {
        while (true) {
            if (list.size() != 0) {
                Task task = (Task) list.get(0);
                task.Task();
                list.remove(0);
                System.out.println("等待执行的任务有：" + list);
            } else {
                System.out.println("请输入");
            }
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
