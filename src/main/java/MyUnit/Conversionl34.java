package MyUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

/**
 * Created by likexin on 2018/11/6.
 */

public class Conversionl34 {
    private static final String C_CODES_STRING = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    /***
     * 将10进制转换为34进制
     */
    public static Stack<String> conversion(int number) {
        Stack<String> s = new Stack<String>();
        if (number == 0)
            s.push(0 + "");
        while (number != 0) {
            s.push(C_CODES_STRING.charAt(Integer.parseInt(String.valueOf(number % 34))) + "");
            number /= 34;
        }
        return s;
    }

    /***
     * 将34进制转换为10进制
     */
    public static int conversion(char value) {
        int ret = 0;
        switch (value) {
            case '0':
                ret = 0;
                break;
            case '1':
                ret = 1;
                break;
            case '2':
                ret = 2;
                break;
            case '3':
                ret = 3;
                break;
            case '4':
                ret = 4;
                break;
            case '5':
                ret = 5;
                break;
            case '6':
                ret = 6;
                break;
            case '7':
                ret = 7;
                break;
            case '8':
                ret = 8;
                break;
            case '9':
                ret = 9;
                break;
            case 'A':
                ret = 10;
                break;
            case 'B':
                ret = 11;
                break;
            case 'C':
                ret = 12;
                break;
            case 'D':
                ret = 13;
                break;
            case 'E':
                ret = 14;
                break;
            case 'F':
                ret = 15;
                break;
            case 'G':
                ret = 16;
                break;
            case 'H':
                ret = 17;
                break;
            case 'J':
                ret = 18;
                break;
            case 'K':
                ret = 19;
                break;
            case 'L':
                ret = 20;
                break;
            case 'M':
                ret = 21;
                break;
            case 'N':
                ret = 22;
                break;
            case 'P':
                ret = 23;
                break;
            case 'Q':
                ret = 24;
                break;
            case 'R':
                ret = 25;
                break;
            case 'S':
                ret = 26;
                break;
            case 'T':
                ret = 27;
                break;
            case 'U':
                ret = 28;
                break;
            case 'V':
                ret = 29;
                break;
            case 'W':
                ret = 30;
                break;
            case 'X':
                ret = 31;
                break;
            case 'Y':
                ret = 32;
                break;
            case 'Z':
                ret = 33;
                break;
            default:
                break;

        }

        return ret;


    }

    /***
     * 日期
     */
    public static String datecode(int number) {
        StringBuffer code = new StringBuffer();
        Stack<String> s = conversion(number);
        while (!s.empty()) {
            code.append(s.pop());
        }
        return code.toString();
    }

    /***
     * 流水号
     */
    public static String serialNumber(int number) {
        StringBuffer code = new StringBuffer();
        Stack<String> s = conversion(number);
        while (!s.empty()) {
            code.append(s.pop());
        }
        while (code.length() < 4) {
            code.insert(0, 0);
        }
        return code.toString();
    }

    /***
     * 校验码
     */
    public static String checkcode(String str) {
        String result = null;
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int decimal = conversion(ch);
            if (decimal != 0) {
                list.add(decimal);
            }
        }

        int value = 1;
        for (ListIterator<Integer> it = list.listIterator(); it.hasNext(); )
            value *= it.next();
        int yu = value % 34;
        result = datecode(yu);

        return result;
    }


//    public static void main(String[] args) {
//        String test1 = datecode(12);
//        String Caesarcipher = serialNumber(33);
//        String test3=checkcode("CC1B940010013");
//        System.out.println(test1);
//        System.out.println(Caesarcipher);
//        System.out.println(test3);
//    }
}
