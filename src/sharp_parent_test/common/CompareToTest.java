package sharp_parent_test.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CompareToTest {
    public static void main(String[] args) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMddHH");
        String time = format.format(date);

        System.out.println("time"+": "+time);
        System.out.println("time.substring(8, 10)"+": "+time.substring(8, 10)+"\n");

        String a1="01"; String a2="10";
        String a3="19"; String a4="20";

        System.out.println(a1.compareTo(a2));
        System.out.println(a1.compareTo(a4));
        System.out.println(a2.compareTo(a3));
        System.out.println(a1.compareTo(a4));
        System.out.println(a4.compareTo(a1));
        
    }
}
