package proxy;

public class Test {

    public static void main(String[] args) {
        XiaoMan xiaoMan = new XiaoMan();
        NaNa naNa = new NaNa(xiaoMan);
        You you = new You(naNa);

        you.makeFriend();
    }
}
