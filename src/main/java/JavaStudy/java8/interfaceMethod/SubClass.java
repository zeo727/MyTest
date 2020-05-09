package JavaStudy.java8.interfaceMethod;

public class SubClass /*extends MyClass*/ implements MyFun,MyInterface {

    @Override
    public String getName() {
        return MyFun.super.getName();
    }
}
