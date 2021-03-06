package JavaStudy.java8.interfaceMethod;

/**
 * @author
 * https://blog.csdn.net/Hello_World_QWP/article/details/80278448
 *
 * Java 8中允许接口中包含具有具体实现的方法，该方法称为“默认方法”，默认方法使用 default 关键字修饰。
 *
 * 接口默认方法的 ” 类优先 ” 原则：
 * 若一个接口中定义了一个默认方法，而另外一个父类或接口中又定义了一个同名的方法时
 * 选择父类中的方法：如果一个父类提供了具体的实现，那么接口中具有相同名称和参数的默认方法会被忽略。
 * 接口冲突：如果一个父接口提供一个默认方法，而另一个接口也提供了一个具有相同名称和参数列表的方法（不管方法是否是默认方法），那么必须覆盖该方法来解决冲突
 */
public class TestDefaultInterface {
    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        System.out.println(subClass.getName());

        MyInterface.show();
    }
}
