package TaskTest;

public class GetMessage {
    public static void main(String[] args) {
        Execute t1 = new Execute();
        t1.start();
        for (int i = 0; i < 100; i++) {
            t1.Insert();
        }
    }
}