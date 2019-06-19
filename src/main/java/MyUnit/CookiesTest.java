package MyUnit;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class CookiesTest {
    public static void main(String[] args) throws IOException {
        String url1 = "https://weixin.sogou.com/weixin?usip=&query=%E6%8A%95%E7%A5%A8&ft=&tsn=1&et=&interation=&type=2&wxid=&ie=utf8&page=";
        URL url = new URL(url1);
        URLConnection urlConnection = url.openConnection();
        String refer = "https://weixin.sogou.com/weixin?type=2&s_from=input&query=%E6%8A%95%E7%A5%A8&ie=utf8&_sug_=n&_sug_type_=";
        String accept = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";

        //设置请求头,下面的第二参数是请求头的值
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");
        urlConnection.setRequestProperty("Accept", accept);
        urlConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.9,zh;q=0.8,zh-CN;q=0.7");
        urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
        urlConnection.setRequestProperty("Connection", "keep-alive");
        urlConnection.setRequestProperty("Referer", refer);
        urlConnection.connect();
        // 获取所有响应头字段
        Map<String, List<String>> map = urlConnection.getHeaderFields();
        // 遍历所有的响应头字段
        for (String key : map.keySet()) {
            System.out.println(key + "--->" + map.get(key));
        }
    }
}