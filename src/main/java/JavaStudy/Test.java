package JavaStudy;

/**
 *
 */
public class Test {
    public static void main(String[] args) {
        String  str ="abc.rar";

        String [] strs = str.split("[.]");
        System.out.println("符号bai后面的字符串="+strs[1]);
    }
}
