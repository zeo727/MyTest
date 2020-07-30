package JavaStudy.Multithreading.jvm;

//强引用
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object(); //这样定义的默认就是强引用
        Object obj2 = obj1; //obj2 引用赋值(强引用)
        obj1 = null; //置空
        System.gc();
        System.out.println(obj2); //强引用不回收
    }
}
