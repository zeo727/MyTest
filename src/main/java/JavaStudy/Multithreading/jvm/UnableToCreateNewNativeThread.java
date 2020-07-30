package JavaStudy.Multithreading.jvm;

/**
 * unable to create new native thread：无法创建新的线程。
 *
 * 原因：
 *
 *    1.应用程序创建了太多的线程，超过了系统承载。
 *
 *    2.服务器不允许你的应用创建这么多线程。linux用户除了Root以外默认创建线程数是1024.
 *
 *   解决办法：
 *
 *    1.修改用户创建线程数量。查看当前用户的线程数量。ulimit -u  修改：vim /etc/security/limits.d/90-nproc.conf
 *
 *    2.修改程序。降低应用程序的线程数量。
 */
public class UnableToCreateNewNativeThread {
    public static void main(String[] args) {
        for (int i =1;  ;i++){
            System.out.println("************* i="+i);
            new Thread(() ->{
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },""+i).start();
        }
    }
}
