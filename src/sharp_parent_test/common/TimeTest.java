package sharp_parent_test.common;

import java.util.Calendar;
import java.util.Date;

public class TimeTest {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        int nowHour =cal.get(Calendar.HOUR_OF_DAY);
        System.out.println(nowHour);
        System.out.println(cal.getTime());

        System.out.println(cal.getTime());
        System.out.println(cal.getTime().getTime());

        System.out.println(new Date());
        System.out.println(new Date().getTime());

//1551226865261
//        Calendar cal1 = Calendar.getInstance();
//        cal1.add(Calendar.DAY_OF_MONTH, 8);
//        System.out.println(cal1);
    }
}
