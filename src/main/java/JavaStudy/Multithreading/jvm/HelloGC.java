package JavaStudy.Multithreading.jvm;

/**
 * JVM参数
 */
public class HelloGC {
    public static void main(String[] args) throws Exception{
//        long totalMemory = Runtime.getRuntime().totalMemory();  //返回 Java虚拟机中的内存总量
//        long maxMemory = Runtime.getRuntime().maxMemory();  //返回 Java虚拟机试图使用的最大内存总量
//        System.out.println("TOTAL_MEMORY(-Xms)=" + totalMemory + "(字节)、" + (totalMemory / (double) 1024 / 1024 + "MB"));
//        System.out.println("MAX_MEMORY(-Xmx)=" + totalMemory + "(字节)、" + (totalMemory / (double) 1024 / 1cc024 + "MB"));

        System.out.println("********HelloGC");
        Thread.sleep(Integer.MAX_VALUE);
        //byte[] byteArray = new byte[50*1024*1024];
    }
}
