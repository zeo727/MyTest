package sharp_parent_test.common;

public class StringUnitTest {
    public static void main(String[] args) {
        String hexcode2 = hexString2binaryString("28");
        System.out.println(hexcode2);
        String s3 = hexcode2.substring(2, 6);
        System.out.println(s3);
        String s4 = "00" + s3 + "00";
        String commandId2 = binaryString2hexString(s4);
        System.out.println(commandId2);
        String commandId = commandId2.substring(commandId2.length() - 2);
        System.out.println(commandId);

        //binaryString2hexString()


    }

    public static String binaryString2hexString(String bString) {
        if (bString == null || bString.equals("") || bString.length() % 8 != 0)
            return null;
        StringBuffer tmp = new StringBuffer();
        int iTmp = 0;
        for (int i = 0; i < bString.length(); i += 4) {
            iTmp = 0;
            for (int j = 0; j < 4; j++) {
                iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
            }
            tmp.append(Integer.toHexString(iTmp));
        }
        return tmp.toString();
    }

    /**
     * 转换为二进制字符串
     */
    public static String hexString2binaryString(String hexString) {
        if (hexString == null || hexString.length() % 2 != 0)
            return null;
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++) {
            // Integer.parseInt(String,radix)将radix进制的数转换为10进制
            tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }
        return bString;
    }
}
