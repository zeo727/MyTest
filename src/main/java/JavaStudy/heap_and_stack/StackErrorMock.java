package JavaStudy.heap_and_stack;

/**
 * 栈溢出测试
 * @author
 * https://www.cnblogs.com/paddix/p/5309550.html
 */
public class StackErrorMock {
    private static int index = 1;
 
    public void call(){
        index++;
        call();
    }
 
    public static void main(String[] args) {
        StackErrorMock mock = new StackErrorMock();
        try {
            mock.call();
        }catch (Throwable e){
            System.out.println("Stack deep : "+index);
            e.printStackTrace();
        }
    }
}