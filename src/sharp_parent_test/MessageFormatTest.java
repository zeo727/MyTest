package sharp_parent_test;

import java.text.MessageFormat;

public class MessageFormatTest {
    public static void main(String[] args) {
        System.out.println(MessageFormat.format("{0}{1}\n","运行状态","净化"));
        System.out.println(MessageFormat.format("{0}{1}","运行状态","净化"));
        System.out.println("1234");
    }
}
