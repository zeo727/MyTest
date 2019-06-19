package sharp_parent_test.test;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 城市配置
 * 
 * @author Pactera
 *
 */
public class CityConstant {
	/**
	 * OpenWeather用的城市与ID列表
	 */
	public static final Map<String, String> openweatherAreaId = new HashMap<String, String>();

	/**
	 * OpenWeather用的城市中文名与英文名列表
	 */
	public static final Map<String, String> openweatherAreaNameEN = new HashMap<String, String>();

	/**
	 * 城市与区号列表
	 */
	public static final Map<String, String> areaNumber = new HashMap<String, String>() {
		{
			put("wuxi", "0510");
			put("jiangyin", "0510");
			put("yixing", "0510");
			put("xishan", "0510");
		}
	};

	static {
		FileInputStream fileStream = null;
		InputStreamReader inputReader = null;
		BufferedReader br = null;
		try {
			URL path = CityConstant.class.getClassLoader().getResource("/areaid_v.csv");
			File csv = new File(path.toURI()); // CSV文件
			fileStream = new FileInputStream(csv);
			inputReader = new InputStreamReader(fileStream, "GBK");
			br = new BufferedReader(inputReader);
			// 读取直到最后一行
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				// openweatherAreaId.put(data[1], data[0]);
				openweatherAreaId.put(data[2], data[0]);
				openweatherAreaNameEN.put(data[2], data[1]);
			}

		} catch (Exception e) {

		} finally {
			if (fileStream != null) {
				try {
					fileStream.close();
				} catch (IOException e) {

				}
			}
			if (inputReader != null) {
				try {
					inputReader.close();
				} catch (IOException e) {

				}
			}

			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {

				}
			}
		}
	}
}
