package JavaStudy.java8.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 传统时间格式化的线程安全问题
 *
 * @author https://blog.csdn.net/u013063153/article/details/76776296
 */
public class TestSimpleDateFormat {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                //报错
//                return simpleDateFormat.parse("20161218");
                // Java8之前的解决方法
//                return DateFormatThreadLocal.convert("20161218");
                //Java8的解决方法
                return LocalDate.parse("20161218", dateTimeFormatter);

            }
        };

        List<Future<LocalDate>> results = new ArrayList<>();
        ExecutorService pool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<LocalDate> future : results) {
            System.out.println(future.get());
        }
        pool.shutdown();
    }
}
