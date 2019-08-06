package sharp_parent_test.test;

public class test {
    public static void main(String[] args) {
        WeatherInfoServiceImpl weatherInfoService=new WeatherInfoServiceImpl();
        //weatherInfoService.getWeatherInfo("shenzhen");
        //weatherInfoService.getHeWeatherInfo("shenzhen","shenzhen");
        String s =weatherInfoService.getPM25Info1("shenzhen");
        System.out.println(s);
    }
}
