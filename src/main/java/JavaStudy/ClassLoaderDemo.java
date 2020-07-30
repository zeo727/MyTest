package JavaStudy;

//双亲委派原则
//https://github.com/Snailclimb/JavaGuide/blob/master/docs/java/jvm/%E7%B1%BB%E5%8A%A0%E8%BD%BD%E5%99%A8.md
public class ClassLoaderDemo {
    public static void main(String[] args) {
        System.out.println("ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader());
        System.out.println("The Parent of ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader().getParent());
        System.out.println("The GrandParent of ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader().getParent().getParent());

    }
}
