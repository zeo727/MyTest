package sharp_parent_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HexToByteTest {
    public static void main(String[] args) {
//        String commandId="2762929,2762928";
//        String[] sessions = commandId.split(",");
//        System.out.println(sessions);


        byte[] b = hexToByteArray("4101DB1922024CB2F2005B00039211013F013F000F0000000700000007000000000013000000014100000000");
        System.out.println("byte "+Arrays.toString(b));
        List list1 = new ArrayList();
        for (int i = 0; i < b.length; i++) {
            int a = (int) b[i] & 0xFF;
            list1.add(a);
        }
        System.out.println("list1 "+list1);
    }

    public static byte[] hexToByteArray(String code) {

        byte[] ret = null;

        if (code.length() >= 2) {
            ret = new byte[code.length() / 2];
            char[] array = code.toLowerCase().toCharArray();

            System.out.println("array" + Arrays.toString(array));

            for (int i = 0; i < ret.length; i++) {
                char high = array[2 * i];

                System.out.println("high" + i + " " + high);

                char low = array[2 * i + 1];

                System.out.println("low" + i + " " + low);

                ret[i] = (byte) ((hexCharToValue(high) << 4) + hexCharToValue(low));

                System.out.println("ret" + i + " " + Arrays.toString(ret));
            }
        }
        return ret;
    }

    private static byte hexCharToValue(char c) {
        byte ret = 0;

        switch (c) {
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
            case 'a':
                ret = 10;
                break;
            case 'b':
                ret = 11;
                break;
            case 'c':
                ret = 12;
                break;
            case 'd':
                ret = 13;
                break;
            case 'e':
                ret = 14;
                break;
            case 'f':
                ret = 15;
                break;
            default:
                break;
        }
        return ret;
    }
}
