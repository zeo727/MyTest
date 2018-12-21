package sharp_parent_test.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import sharp_parent_test.Vo.ReturnVo;


public class BooleanJson {
    public static void main(String[] args) {

        ReturnVo returnvo = new ReturnVo();
        String sessionId = "1234567";
        String json = null;

        returnvo.setErrcode("0");
        returnvo.setErrmsg("ok");
        returnvo.setErrdata(sessionId);
        String json1 = getJsonFromBean(returnvo);
        System.out.println(json1);
        boolean security = json1.matches("[a-zA-Z0-9,:\"{}]+");
        if (security) {
            json = json1;
        } else {
            json = "数据可能被篡改";
        }
        System.out.println(json);
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
