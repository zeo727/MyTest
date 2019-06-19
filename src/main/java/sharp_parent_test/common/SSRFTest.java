package sharp_parent_test.common;

import com.google.common.net.InternetDomainName;

import java.net.URL;

public class SSRFTest {
    public static void main(String[] args) {
        String[] urlwhitelist = {"joychou.org", "joychou.me"};
        String url = "http://localhost:18080/SharpCloudAgent/agent/userAction?appSecret=OPBKAEhw1gTa0RthYTGqwk1d5nwClollldU1qpvUBrR";
        if (!securitySSRFUrlCheck(url, urlwhitelist)) {
            System.out.println("y");
            return;
        } else {
            System.out.println("N");
        }
    }

    public static Boolean securitySSRFUrlCheck(String url, String[] urlwhitelist) {
        System.out.println("securitySSRFUrlCheck start");
        try {
            System.out.println("securitySSRFUrlCheck try start");
            URL u = new URL(url);
            // 只允许http和https的协议通过
            if (!u.getProtocol().startsWith("http") && !u.getProtocol().startsWith("https")) {
                System.out.println("n");
                return false;
            }
            // 获取域名，并转为小写
            String host = u.getHost().toLowerCase();
            // 获取一级域名
            String rootDomain = InternetDomainName.from(host).topPrivateDomain().toString();
            System.out.println("rootDomain" + rootDomain);

            for (String whiteurl : urlwhitelist) {
                if (rootDomain.equals(whiteurl)) {
                    System.out.println("whiteurl");
                    return true;
                }
            }
            System.out.println("securitySSRFUrlCheck false1 end");
            return false;

        } catch (Exception e) {
            System.out.println("securitySSRFUrlCheck false2 end");
            return false;
        }
    }


}
