package sharp_parent_test.common;


import java.net.InetAddress;
import java.net.URL;

public class BooleanUrl {
    public static void main(String[] args) throws Exception {
        String url1 = "http://localhost:18080/SharpCloudAgent/agent/userAction?appSecret=OPBKAEhw1gTa0RthYTGqwk1d5nwClollldU1qpvUBrR";
        String url2 = "http://localhost:18080/SharpCloudAgent/userDevices?openId=15735171531&appSecret=OPBKAEhw1gTa0RthYTGqwk1d5nwClollldU1qpvUBrR";

        URL url = new URL(url1);
        String host = url.getHost();

        if (!url.getProtocol().startsWith("http")) {
            System.out.println("http no");
        }

        InetAddress inetAddress = InetAddress.getByName(host);

        System.out.println(host);
        System.out.println(inetAddress);


        if (inetAddress.isAnyLocalAddress()) {
            System.out.println("isAnyLocalAddress");
        }
        if (inetAddress.isLoopbackAddress()) {
            System.out.println("isLoopbackAddress");
        }
        if (inetAddress.isLinkLocalAddress()) {
            System.out.println("isLinkLocalAddress");
        }
//        HttpURLConnection conn = (HttpURLConnection)(url.openConnection());
//
//        conn.setInstanceFollowRedirects(false);
//        conn.connect();


    }

}
