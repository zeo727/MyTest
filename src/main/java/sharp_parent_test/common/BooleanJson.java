package sharp_parent_test.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import sharp_parent_test.Vo.ReturnVo;


public class BooleanJson {
    public static void main(String[] args) {

        ReturnVo returnvo = new ReturnVo();
        String sessionId = "1234567[。";
        String json1 = null;
        String json2 = null;

        returnvo.setErrcode("0");
        returnvo.setErrmsg("ok");
        returnvo.setErrdata(sessionId);
        System.out.println(String.valueOf(returnvo));

        String json = getJsonFromBean(returnvo);
        System.out.println(json);
        boolean security = json.matches("[a-zA-Z0-9,:\"{}]+");
        if (security) {
            json1 = json;
        } else {
            json1 = "数据可能被篡改";
        }
        json2 = json.replaceAll("[^a-zA-Z0-9,:\"{}]+", "");
        System.out.println(json1);
        System.out.println(json2);
    }

    public static String getJsonFromBean(Object javaBean) {
        String result = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(javaBean);
        } catch (JsonProcessingException e) {

        }
        return result;
    }

}
