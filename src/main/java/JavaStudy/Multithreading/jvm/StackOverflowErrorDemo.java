package JavaStudy.Multithreading.jvm;

//栈溢出(StackOverflowError)
public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        StackeOverflowError();
    }

    private static void StackeOverflowError() {
        StackeOverflowError();
    }
}
