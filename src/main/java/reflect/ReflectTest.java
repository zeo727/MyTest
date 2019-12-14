package reflect;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectTest {
    public static void main(String[] args) throws Exception {
        //可以创建任意类的对象，可以执行任意方法

        /*
         * 前提，不能改变该类的任何代码，可以创建任意类的对象，可以执行任意方法。
         * */
//        Person p = new Person();
//        p.eat();
//        Student student = new Student();
//        student.sleep();


        //1.加载配置文件
        //1.1.创建properties对象
        Properties properties = new Properties();
        //1.2加载配置文件，转换为一个集合
        //1.2.1获取class目录下的配置文件
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("pro.properties");
        properties.load(inputStream);

        //2.获取配置文件中定义的数据
        String className =properties.getProperty("className");
        String methodName = properties.getProperty("methodName");

        //3.加载该类进内存
        Class cls = Class.forName(className);
        //4.创建对象
        Object object = cls.newInstance();
        //5.获取方法对象
        Method method =cls.getMethod(methodName);
        //6.执行方法
        method.invoke(object);

    }
}
