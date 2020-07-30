package JavaStudy.heap_and_stack;

import java.util.ArrayList;
import java.util.List;

/**
 * 永久代溢出测试
 * @author
 * https://www.cnblogs.com/paddix/p/5309550.html
 */
public class StringOomMock {
    static String  base = "string";
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i=0;i< Integer.MAX_VALUE;i++){
            String str = base + base;
            base = str;
            list.add(str.intern()); //intern 函数用来返回常量池中的某字符串，如果常量池中已经存在该字符串，则直接返回常量池中该对象的引用。否则，在常量池中加入该对象，然后返回引用。
        }
    }
}