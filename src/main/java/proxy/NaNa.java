package proxy;

public class NaNa implements Beauty {

    private Beauty beauty;

    /**
     * 通过构造方法传入目标对象
     *
     * @param beauty
     */
    public NaNa(Beauty beauty) {
        this.beauty = beauty;
    }


    @Override
    public void eat() {
        //调用目标对象上的吃饭方法
        beauty.eat();
    }
}