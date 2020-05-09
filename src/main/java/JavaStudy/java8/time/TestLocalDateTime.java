package JavaStudy.java8.time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * @author https://www.cnblogs.com/fx-blog/p/11747265.html
 */
public class TestLocalDateTime {

    //1. LocalDate LocalTime LocalDateTime
    @Test
    public void test1() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.of(2015, 10, 19, 13, 22, 33);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.plusYears(2);
        System.out.println(ldt3);

        LocalDateTime ldt4 = ldt.minusMonths(2);
        System.out.println(ldt4);

        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonthValue());
        System.out.println(ldt.getDayOfMonth());
        System.out.println(ldt.getHour());
        System.out.println(ldt.getMinute());
        System.out.println(ldt.getSecond());
    }

    //2. Instant：时间戳(以Unix 元年：1970年1月1日 00:00:00到某个时间之间的毫秒值)
    @Test
    public void test2() {
        Instant instant1 = Instant.now(); //默认获取UTC时区
        System.out.println(instant1);

        OffsetDateTime offsetDateTime = instant1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        System.out.println(instant1.toEpochMilli());

        Instant instant2 = Instant.ofEpochSecond(1000);
        System.out.println(instant2);
    }
    //3.
    //Duration:用于计算两个“时间”间隔
    //Period:用于计算两个“日期”间隔

    @Test
    public void test3() {
        Instant instant1 = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        Instant instant2 = Instant.now();

        Duration duration = Duration.between(instant1, instant2);
        System.out.println(duration.toMillis());

        System.out.println("------------------------");

        LocalTime lt1 = LocalTime.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }

        LocalTime lt2 = LocalTime.now();

        System.out.println(Duration.between(lt1, lt2).toMillis());
    }

    @Test
    public void test4() {
        LocalDate localDate1 = LocalDate.of(2015, 1, 1);
        LocalDate localDate2 = LocalDate.now();

        Period period = Period.between(localDate1, localDate2);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    //TemporalAdjuster: 时间校正器
    @Test
    public void test5() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);

        //自定义：下一个工作日
        LocalDateTime ldt5 = ldt.with(l -> {
            LocalDateTime ldt4 = (LocalDateTime) l;

            DayOfWeek dayOfWeek = ldt4.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);
    }

    //DateTimeForMatter: 格式化时间/日期
    @Test
    public void test6() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE;
        LocalDateTime localDateTime = LocalDateTime.now();

        String strDate = localDateTime.format(dateTimeFormatter);
        System.out.println(strDate);

        System.out.println("------------------------");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");

        String strDate2 = dtf2.format(localDateTime);
        System.out.println(strDate2);

        System.out.println("------------------------");
        LocalDateTime newDate = LocalDateTime.parse(strDate2, dtf2);
        System.out.println(newDate);
    }

    //ZonedDate、ZonedTime、ZonedDateTime
    @Test
    public void test7() {
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }

    @Test
    public void test8() {
        LocalDateTime ldt1 = LocalDateTime.now(ZoneId.of("Europe/Monaco"));
        System.out.println(ldt1);

        LocalDateTime ldt2 = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ldt2.atZone(ZoneId.of("Europe/Monaco"));
        System.out.println(zonedDateTime);


    }
}
