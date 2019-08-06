package sharp_parent_test.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class WeatherInfoServiceImpl {


    private static final int HTTP_OK = 200;
    private static final String PM25_KEY = "kvstore_pm25";
    private static final String PM25_HISTORY_KEY = "kvstore_pm25_history";
    private static final String PM25_ALL_FILED = "kvstore_all_pm25";
    // 最终URL(appid取前6位)
    private static final String OPENWEATHER_FORECAST_URL = "http://open.weather.com.cn/data/?areaid={0}&type=forecast_v&date={1}&appid={2}&key={3}";

    // 用于生成加密key的URL(appid为完整)
    private static final String OPENWEATHER_FORECAST_KEY_URL = "http://open.weather.com.cn/data/?areaid={0}&type=forecast_v&date={1}&appid={2}"; // 用于生成加密key的URL(appid为完整)
    private static final String OPENWEATHER_INDEX_KEY_URL = "http://open.weather.com.cn/data/?areaid={0}&type=index_v&date={1}&appid={2}";
    // 最终URL(appid取前6位)
    private static final String OPENWEATHER_INDEX_URL = "http://open.weather.com.cn/data/?areaid={0}&type=index_v&date={1}&appid={2}&key={3}";
    // 和风API URL
    private static final String OPENWEATHER_URL = "https://api.heweather.com/x3/weather?city={0}&key={1}";
    // 密钥
    //private static final String OPENWEATHER_PRIVATE_KEY = AgentConfig.getWeatherValue("openweather_private_key");
    private static final String OPENWEATHER_PRIVATE_KEY = "ec4e4038e2bd4306862f86b8e57e29eb";
    // openweather的appId
    //private static final String OPENWEATHER_APP_ID = AgentConfig.getWeatherValue("openweather_appid");
    private static final String OPENWEATHER_APP_ID = "ee56efeb009673cc";
    // pm25.in的URL
    private static final String PM25IN_URL = "http://www.pm25.in/api/querys/pm2_5.json?city={0}&stations=no&token={1}";
    // pm25.in的全国URL
    private static final String PM25IN_ALL_URL = "http://www.pm25.in/api/querys/aqi_ranking.json?token={0}";
    // all api的URL
    private static final String PMALLAQI_URL = "http://www.pm25.in/api/querys/aqi_details.json?city={0}&stations=no&token={1}";
    // tencent map geocode service URL
    private static final String TENCENT_MAP_GEOCODE_URL = "http://apis.map.qq.com/ws/geocoder/v1/?location={0},{1}&key={2}&get_poi=0";
    //pm25.in的appKey
    private static final String PM25IN_KEY = "GiyErqxy6tq5z29H8scu";
    // tencent map geocode service URL
    private static final String TENCENT_MAP_GETCITY_BY_IP_URL = "http://apis.map.qq.com/ws/location/v1/ip?key={0}&ip={1}";
    //和风天气空气质量接口
    private static final String OPENWEATHER_PM25 = "https://api.heweather.net/s6/air/now?location={0}&key={1}";

    public static void main(String[] args) {
    }

    public String getWeatherInfo(String cityName) {

        return callOpenWeatherForecast(cityName);
    }

    public String getHeWeatherInfo(String subLocal, String cityName) {

        return callHeWeatherForecast(subLocal, cityName);
    }


    @Deprecated
    public String getWeatherIndexInfo(String cityName) {

        return callOpenWeatherIndex(cityName);
    }

    public String getPM25Info(String cityName) {

        return callPM25IN(cityName);
    }


    public Object getPM25Object(String cityName) {

        return callPM25Obj(cityName);
    }


//    public String getCityNameByLatLng(String latitude, String longitude) {
//
//        // return getCityNameFromGoogleMapGeocode(latitude, longitude);
//        return getCityNameFromTencentGeocode(latitude, longitude);
//    }
//
//
//    public String getCityNameByIp(String ip) {
//
//        return callCityNameByIp(ip);
//    }

    /**
     * 发送HTTP GET请求，可指定服务名用于log输出
     *
     * @param url         请求的URL
     * @param serviceName 调用服务名
     * @return 请求所得结果
     */
    private String sendHttpGetRequest(String url, String serviceName) {

        String result = "";

        HttpGet httpGet = new HttpGet(url);
        HttpClient client = HttpClientBuilder.create().build();
        InputStreamReader inputReader = null;
        System.out.println(MessageFormat.format("{0} service url [{1}]", serviceName, url));

        try {

            HttpResponse response = client.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == HTTP_OK) {
                inputReader = new InputStreamReader(response.getEntity().getContent());
                BufferedReader in = new BufferedReader(inputReader);
                String line = "";
                while ((line = in.readLine()) != null) {
                    result += line;
                }
                System.out.println(MessageFormat.format("result from {0} service --> {1}", serviceName, result));
            }
        } catch (Exception e) {
        } finally {
            if (inputReader != null) {
                try {
                    inputReader.close();
                } catch (IOException e) {
                }
            }
        }

        return result;

    }

    /**
     * 获取OpenWeather中定义的城市id
     *
     * @param cityName 城市名
     * @return OpenWeather城市ID
     */
    private String getOpenWeatherAreaId(String cityName) {
        String ret = "";
        if (CityConstant.openweatherAreaId.get(cityName) != null) {
            ret = CityConstant.openweatherAreaId.get(cityName);
        }
        return ret;
    }

    /**
     * 获取OpenWeather中定义的城市英文名
     *
     * @param cityName 城市名
     * @return OpenWeather城市ID
     */
    private String getOpenWeatherAreaNameEN(String cityName) {
        String ret = "";
        if (CityConstant.openweatherAreaNameEN.get(cityName) != null) {
            ret = CityConstant.openweatherAreaNameEN.get(cityName);
        }
        return ret;
    }

    /**
     * 从OpenWeather获取天气数据
     *
     * @param cityName 城市名
     * @return 天气数据
     */
    private String callOpenWeatherForecast(String cityName) {

        /*
         * String ret = "";
         *
         * if (!StringUtil.isNotBlank(getOpenWeatherAreaId(cityName))) { return
         * ret; }
         *
         * // 获取用于生成key的URL String data =
         * MessageFormat.format(OPENWEATHER_FORECAST_KEY_URL,
         * getOpenWeatherAreaId(cityName),
         * DateTime.now().toString("yyyyMMddHHmm"), OPENWEATHER_APP_ID);
         *
         * // 生成key String key = EncodeUtil.generateOpenWeatherKey(data,
         * OPENWEATHER_PRIVATE_KEY);
         *
         * // 实际调用的URL及参数 // appid只使用前6位 String url =
         * MessageFormat.format(OPENWEATHER_FORECAST_URL,
         * getOpenWeatherAreaId(cityName),
         * DateTime.now().toString("yyyyMMddHHmm"),
         * OPENWEATHER_APP_ID.substring(0, 6), key);
         *
         * String result = sendHttpGetRequest(url, "OpenWeather");
         *
         * Map<String, Object> jsonObj = JsonUtil.getJsonFromString(result);
         *
         * ret = MessageHelper.getWeatherMessage(jsonObj);
         *
         * return ret;
         */

        String ret = "";

//        if (!StringUtil.isNotBlank(getOpenWeatherAreaId(cityName))) {
//            return ret;
//        }

        System.out.println("aaa");

        // 实际调用的URL及参数
        String url = MessageFormat.format(OPENWEATHER_URL, cityName.trim(), OPENWEATHER_PRIVATE_KEY);

        String result = sendHttpGetRequest(url, "OpenWeather");

        Map<String, Object> jsonObj = JsonUtil.getJsonFromString(result);

        ret = MessageHelper.getNewWeatherMessage(jsonObj);

        System.out.println(ret);

        return ret;
    }

    /**
     * 从OpenWeather获取天气数据
     *
     * @param cityName 城市名
     * @return 天气数据
     */
    private String callHeWeatherForecast(String subLocal, String cityName) {
        String ret = "";

//        if (!StringUtil.isNotBlank(getOpenWeatherAreaNameEN(subLocal.trim()))) {
//            return ret;
//        }

        // 实际调用的URL及参数
        String url = MessageFormat.format(OPENWEATHER_URL, subLocal.trim(), OPENWEATHER_PRIVATE_KEY);

        String result = sendHttpGetRequest(url, "OpenWeather");

        Map<String, Object> jsonObj = JsonUtil.getJsonFromString(result);

        Object jsonPM25 = JsonUtil.getJsonObject(callPM25Info(cityName.trim()));

        Map<String, String> weatherMap = MessageHelper.getHeWeatherMessage(jsonObj, jsonPM25);
        weatherMap.put("location", getOpenWeatherAreaNameEN(subLocal.trim()));

        ret = JsonUtil.getJsonFromMap(weatherMap);

        System.out.println(ret);

        return ret;
    }

    /**
     * 从OpenWeather获取天气指数数据
     *
     * @param cityName 城市名
     * @return 天气数据
     */
    @Deprecated
    private String callOpenWeatherIndex(String cityName) {

        String ret = "";

        if (!StringUtil.isNotBlank(getOpenWeatherAreaId(cityName))) {
            return ret;
        }

        // 获取用于生成key的URL
        String data = MessageFormat.format(OPENWEATHER_INDEX_KEY_URL, getOpenWeatherAreaId(cityName),
                DateTime.now().toString("yyyyMMddHHmm"), OPENWEATHER_APP_ID);

        // 生成key
        String key = EncodeUtil.generateOpenWeatherKey(data, OPENWEATHER_PRIVATE_KEY);

        // 实际调用的URL及参数
        // appid只使用前6位
        String url = MessageFormat.format(OPENWEATHER_INDEX_URL, getOpenWeatherAreaId(cityName),
                DateTime.now().toString("yyyyMMddHHmm"), OPENWEATHER_APP_ID.substring(0, 6), key);

        String result = sendHttpGetRequest(url, "OpenWeatherIndex");

        Map<String, Object> jsonObj = JsonUtil.getJsonFromString(result);

        ret = MessageHelper.getWeatherIndexMessage(jsonObj);

        return ret;
    }

    /**
     * 从pm25.in获取PM2.5数据
     *
     * @param cityName 城市名
     * @return PM2.5数据
     */
    private String callPM25IN(String cityName) {

        String ret = "";

        Object jsonObj = JsonUtil.getJsonObject(callPM25Info(cityName));

        ret = MessageHelper.getPM25Message(jsonObj);

        return ret;
    }

    /**
     * 从pm25.in获取PM2.5数据
     *
     * @param cityName 城市名
     * @return PM2.5数据
     */
    private Object callPM25Obj(String cityName) {

        if (StringUtil.isNotBlank(cityName)) {
            Object jsonObj = JsonUtil.getJsonObject(callPM25Info(cityName));

            return jsonObj;
        } else {
            return null;
        }
    }

    /**
     * @param cityName
     * @return
     */
    private String callPM25Info(String cityName) {
        String result = "";
        boolean newFlg = false;

        String url = MessageFormat.format(OPENWEATHER_PM25, cityName, OPENWEATHER_PRIVATE_KEY);

        result = sendHttpGetRequest(url, "PM25.in");

        String pm25Result = DateUtil.getCurrentDate() + result;

        if (StringUtil.isNotBlank(pm25Result)) {
            String currentDate = DateUtil.getCurrentDate();
            String pm25Result_date = pm25Result.substring(0, 8);
            String pm25Result_hour = pm25Result.substring(8, 10);
            // 年月日比较
            if (currentDate.substring(0, 8).equals(pm25Result_date)) {
                if (currentDate.substring(8, 10).compareTo(pm25Result_hour) >= 1) {
                    newFlg = true;
                }

            } else {
                newFlg = true;
            }

        } else {
            newFlg = true;
        }

        if (newFlg) {
//            // 获取用于生成key的URL
//            String url = MessageFormat.format(PMALLAQI_URL, cityName, PM25IN_KEY);
//
//            result = sendHttpGetRequest(url, "PM25.in");
//
//            if (StringUtil.isNotBlank(result)) {
//                if (!(result.contains("error"))) {
//                    KVStoreUtils.putHashmapField(PM25_KEY, cityName, DateUtil.getCurrentDate() + result);
//                }
//            }

        } else {
            result = pm25Result.substring(10);
        }

        return result;
    }

//        /**
//         * 使用google地图服务从经纬度得到位置城市
//         *
//         * @param latitude
//         *            纬度
//         * @param longitude
//         *            经度
//         * @return 位置数据
//         */
//        private String getCityNameFromGoogleMapGeocode(double latitude, double longitude) {
//
//            String ret = "";
//
//            // 获取用于生成key的URL
//            String url = MessageFormat.format(GOOGLE_MAP_GEOCODE_URL, latitude, longitude);
//
//            String result = sendHttpGetRequest(url, "GoogleMap");
//
//            ret = JsonHelper.getCityNameFromJSONForGoogleMap(result);
//
//            return ret;
//        }
//
//        /**
//         * 使用腾讯地图服务从经纬度得到位置城市
//         *
//         * @param latitude
//         *            纬度
//         * @param longitude
//         *            经度
//         * @return 位置数据
//         */
//        private String getCityNameFromTencentGeocode(String latitude, String longitude) {
//
//            String ret = "";
//
//            // 获取用于生成key的URL
//            String url = MessageFormat.format(TENCENT_MAP_GEOCODE_URL, latitude, longitude, TENCENT_API_KEY);
//
//            String result = sendHttpGetRequest(url, "TencentMap");
//
//            ret = JsonHelper.getCityNameFromJSONForTencentMap(result);
//
//            return ret;
//        }
//
//        private String callCityNameByIp(String ip) {
//            String ret = "";
//
//            // 获取用于生成key的URL
//            String url = MessageFormat.format(TENCENT_MAP_GETCITY_BY_IP_URL, TENCENT_API_KEY, ip);
//
//            String result = sendHttpGetRequest(url, "TencentMap");
//
//            ret = JsonHelper.getCityNameForTencentMap(result);
//
//            return ret;
//        }

    public int getPM25InfoByCity(String cityName) {
        int pm25 = 0;

        if (StringUtil.isNotBlank(cityName)) {

            Object jsonObj = JsonUtil.getJsonObject(callPM25Info(cityName));

            String ret = MessageHelper.getPM25Value(jsonObj);

            if (StringUtil.isNotBlank(ret)) {
                try {
                    pm25 = Integer.valueOf(ret);
                } catch (Exception e) {
                    pm25 = 0;
                }
            }
        }
        if (pm25 == 0) {
            boolean newFlg = false;

            //String pm25Result = KVStoreUtils.getHashmapField(PM25_KEY, PM25_ALL_FILED);
//            if (StringUtil.isNotBlank(pm25Result)) {
//                String currentDate = DateUtil.getCurrentDate();
//                String pm25Result_date = pm25Result.substring(0, 8);
//                String pm25Result_hour = pm25Result.substring(8, 10);
//                // 年月日比较
//                if (currentDate.substring(0, 8).equals(pm25Result_date)) {
//                    newFlg = false;
//
//                } else {
//                    if (currentDate.substring(8, 10).compareTo(pm25Result_hour) >= 24) {
//                        newFlg = true;
//                    }
//                }
//
//            } else {
//                newFlg = true;
//            }

            if (newFlg) {
                // 获取用于生成key的URL
                String url = MessageFormat.format(PM25IN_ALL_URL, PM25IN_KEY);
                String result = sendHttpGetRequest(url, "PM25.in");
                Object jsonObj = JsonUtil.getJsonObject(result);
                pm25 = MessageHelper.getPM25AllValue(jsonObj);
                if (pm25 != 0) {
                    //KVStoreUtils.putHashmapField(PM25_KEY, PM25_ALL_FILED, DateUtil.getCurrentDate() + pm25);
                }
            } else {
                //pm25 = Integer.parseInt(pm25Result.substring(10));
            }
        }

        return pm25;
    }

    public Map<String, String> getAllPM25Info(String workDate) {
        Map<String, String> returnMap = new HashMap<String, String>();
        // 获取用于生成key的URL
        String url = MessageFormat.format(PM25IN_ALL_URL, PM25IN_KEY);
        String result = sendHttpGetRequest(url, "PM25.in");
        //logger.info("从第三方获取到的室外空气质量==》》"+result);
        Object jsonObj = JsonUtil.getJsonObject(result);
        returnMap = MessageHelper.getAllPM25Info(jsonObj, workDate);
        return returnMap;
    }

    public int getPM25InfoByCity1(String cityName) {
        int pm25 = 0;

        if (StringUtil.isNotBlank(cityName)) {

            Object jsonObj = JsonUtil.getJsonObject(callPM25Info1(cityName));

            String ret = MessageHelper.getPM25Value(jsonObj);

            if (StringUtil.isNotBlank(ret)) {
                try {
                    pm25 = Integer.valueOf(ret);
                } catch (Exception e) {
                    //logger.error(e);
                    pm25 = 0;
                }
            }
        }
        if (pm25 == 0) {
            boolean newFlg = false;

            //String pm25Result = KVStoreUtils.getHashmapField(PM25_KEY, PM25_ALL_FILED);
//            if (StringUtil.isNotBlank(pm25Result)) {
//                String currentDate = DateUtil.getCurrentDate();
//                String pm25Result_date = pm25Result.substring(0, 8);
//                String pm25Result_hour = pm25Result.substring(8, 10);
//                // 年月日比较
//                if (currentDate.substring(0, 8).equals(pm25Result_date)) {
//                    newFlg = false;
//
//                } else {
//                    if (currentDate.substring(8, 10).compareTo(pm25Result_hour) >= 24) {
//                        newFlg = true;
//                    }
//                }
//
//            } else {
//                newFlg = true;
//            }

            if (newFlg) {
                // 获取用于生成key的URL
                String url = MessageFormat.format(PM25IN_ALL_URL, PM25IN_KEY);
                String result = sendHttpGetRequest(url, "PM25.in");
                Object jsonObj = JsonUtil.getJsonObject(result);
                pm25 = MessageHelper.getPM25AllValue(jsonObj);
                if (pm25 != 0) {
                    //KVStoreUtils.putHashmapField(PM25_KEY, PM25_ALL_FILED, DateUtil.getCurrentDate() + pm25);
                }
            } else {
                //pm25 = Integer.parseInt(pm25Result.substring(10));
            }
        }

        return pm25;
    }

    /**
     * @param cityName
     * @return
     */
    private String callPM25Info1(String cityName) {
        String result = "";
        boolean newFlg = false;

//        String pm25Result = KVStoreUtils.getHashmapField(PM25_KEY, cityName);
//        if (StringUtil.isNotBlank(pm25Result)) {
//            String currentDate = DateUtil.getCurrentDate();
//            String pm25Result_date = pm25Result.substring(0, 8);
//            String pm25Result_hour = pm25Result.substring(8, 10);
//            // 年月日比较
//            if (currentDate.substring(0, 8).equals(pm25Result_date)) {
//                if (currentDate.substring(8, 10).compareTo(pm25Result_hour) >= 1) {
//                    newFlg = true;
//                }
//
//            } else {
//                newFlg = true;
//            }
//
//        } else {
//            newFlg = true;
//        }

        if (newFlg) {
            // 获取用于生成key的URL
            String url = MessageFormat.format(OPENWEATHER_PM25, cityName, OPENWEATHER_PRIVATE_KEY);

            result = sendHttpGetRequest(url, "PM25.in");

            if (StringUtil.isNotBlank(result)) {
                if (!(result.contains("error"))) {
                    //KVStoreUtils.putHashmapField(PM25_KEY, cityName, DateUtil.getCurrentDate() + result);
                }
            }

        } else {
            //result = pm25Result.substring(10);
        }

        return result;
    }

    public String getPM25Info1(String city) {
        String url = MessageFormat.format(OPENWEATHER_PM25, city, OPENWEATHER_PRIVATE_KEY);

        String result = sendHttpGetRequest(url, "PM25.in");
        String pm25 = "";

        if (StringUtil.isNotBlank(result)) {
            if (!(result.contains("error"))) {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray list = (JSONArray) jsonObject.get("HeWeather6");
                JSONObject jsonObject1 = (JSONObject) list.get(0);
                JSONObject air_now_city = jsonObject1.getJSONObject("air_now_city");

                pm25 = (String) air_now_city.get("pm25");
            }

        }
        return pm25;
    }

    public Object getPM25Object1(String city) {
        return "";
    }

}
