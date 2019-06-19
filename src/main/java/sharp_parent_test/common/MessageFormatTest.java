package sharp_parent_test.common;

import java.text.MessageFormat;

public class MessageFormatTest {
    public static void main(String[] args) {
        System.out.println(MessageFormat.format("{0}{2}\n", "运行状态", "净化", "2"));
        System.out.println(MessageFormat.format("{0}{1}", "运行状态", "净化"));
        System.out.println("1234");
    }
}
