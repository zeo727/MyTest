package JavaStudy.java8.annotation;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 重复注解与类型注解
 */
public class TestAnnotation {

    @MyAnnotation("Hello")
    @MyAnnotation("World")
    public void show(@MyAnnotation("abc") String str){}

    @Test
    public void test1() throws Exception{
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method m1 = clazz.getMethod("show");

        MyAnnotation[] mas = m1.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation myAnnotation : mas){
            System.out.println(myAnnotation.value());
        }
    }
}
