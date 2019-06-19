package JavaStudy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class Variable {
    private static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
    private static boolean running = false;

    public BlockingQueue<Runnable> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}

class ExpDataJob implements Runnable {

    Variable var = new Variable();

    public void run() {
        while (var.isRunning()) {
            try {
                //取得一个任务
                Runnable job = var.getQueue().poll(5000L, TimeUnit.MILLISECONDS);
                System.out.println("x-x-x-x-x-x-x 进入数据《导出》阶段 ....");
                if (job != null) {
                    job.run();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.toString());
            }

        }
    }
}

class InitDataQuartzCommon {

    private static int count = 0;

    public void startClearData() {

        // 初始化任务顺序
        OrderdJobUtils orderdJobUtils = new OrderdJobUtils();
        // 启动任务
        orderdJobUtils.start();
        for (int i = 0; i < 10; i++) {
            orderdJobUtils.insertJob(new Runnable() {
                public void run() {
                    System.out.println(count++);
                }
            });
        }

        synchronized (orderdJobUtils) {
            try {
                orderdJobUtils.wait(2000L);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.toString());
            }
        }

        orderdJobUtils.stop();

    }
}

class OrderdJobUtils {
    Variable var = new Variable();

    public void start() {
        var.setRunning(true);
        Thread thread = new Thread(new ExpDataJob());
        thread.start();
    }

    public void stop() {
        var.setRunning(false);
    }

    public void insertJob(Runnable job) {

        try {
            //插入 一个任务
            if (var.getQueue().offer(job, 5000L, TimeUnit.MILLISECONDS) == false) {
                // 处理任务插入失败的情况
                System.out.println("E-E-E-E-E-E-E: 任务插入失败 ！~ ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

}

public class ApplicationMain {
    public static void main(String[] args) {
        // 初始化清理数据任务
        InitDataQuartzCommon dataQuartzCommon = new InitDataQuartzCommon();
        // 启动数据清理任务
        dataQuartzCommon.startClearData();
    }

}