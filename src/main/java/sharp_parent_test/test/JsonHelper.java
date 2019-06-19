package sharp_parent_test.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析json格式数据
 * 
 * @author Pactera
 *
 */

/**
 * @author Feng
 *
 */
public class JsonHelper {


	/**
	 * 以Map类型解析JSON
	 * 
	 * @param jsonString
	 *            JSON格式字符串
	 * @return Map对象
	 */
	public static Map<String, Object> getJsonFromString(String jsonString) {
		Map<String, Object> jsonObj = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonObj = mapper.readValue(jsonString, Map.class);
		} catch (IOException e) {
			System.out.println("JsonUtil(getJsonFromString) error:" + e.toString());
		}
		return jsonObj;
	}

	/**
	 * 以Object类型解析JSON
	 * 
	 * @param jsonString
	 *            JSON格式字符串
	 * @return Object对象
	 */
	public static Object getJsonObject(String jsonString) {
		Object jsonObj = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonObj = mapper.readValue(jsonString, Object.class);
		} catch (IOException e) {
			System.out.println("JsonUtil(getJsonObject) error:" + e.toString());
		}
		return jsonObj;
	}

	/**
	 * 从JSON格式数据中获取sensor的信息
	 * 
	 * @param jsonString
	 * @return
	 */
	public static List<String[]> getSensorInfoFromJSON(String jsonString) {

		List<String[]> sensorDataList = new ArrayList<String[]>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			Map<String, List<Map>> jsonObj = mapper.readValue(jsonString, Map.class);

			if (jsonObj != null) {

				List<Map> sensorList = jsonObj.get("sensorEventList");
				for (Map sensor : sensorList) {

					System.out.println(sensor.get("sensorId"));
					Object obj = sensor.get("eventList");

					if (obj instanceof ArrayList<?>) {
						List<Object> eventList = (ArrayList<Object>) obj;

						if (eventList.size() > 0) {
							int s = eventList.size();
							Map<String, Object> m = (Map<String, Object>) eventList.get(s - 1);
							String data[] = { sensor.get("sensorId").toString(), m.get("value").toString() };
							sensorDataList.add(data);
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return sensorDataList;
	}

	/**
	 * 将Map<String,String>转换成json字符串
	 * 
	 * @param jsonMap
	 * @return
	 */
	public static String getJsonFromMap(Map<String, String> jsonMap) {
		String result = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.writeValueAsString(jsonMap);
		} catch (JsonProcessingException e) {
			System.out.println(e.toString());
		}

		return result;
	}

	/**
	 * 解析GoogleMap返回的位置数据得到城市名称
	 * 
	 * @param
	 * @return
	 */
	public static String getCityNameFromJSONForGoogleMap(String jsonString) {

		String ret = "";

		String locality = "";
		String subLocality = "";

		Map<String, Object> data = getJsonFromString(jsonString);

		if (data.get("results") != null) {
			ArrayList<?> addressList = (ArrayList<?>) data.get("results");
			if (addressList.size() > 0) {
				Map<String, ?> address = (Map<String, ?>) addressList.get(0);
				if (address.get("address_components") != null) {
					ArrayList<?> componentList = (ArrayList<?>) address.get("address_components");
					for (Object component : componentList) {
						Map<String, Object> m = (Map<String, Object>) component;
						if (m.get("types") != null && m.get("long_name") != null) {
							ArrayList<String> typeList = (ArrayList) m.get("types");
							for (String type : typeList) {
								if ("sublocality_level_1".equalsIgnoreCase(type)) {
									subLocality = m.get("long_name").toString().toLowerCase();
								}
								if ("locality".equalsIgnoreCase(type)) {
									locality = m.get("long_name").toString().toLowerCase();
								}
							}
						}
					}
				}
			}
		}

		if (subLocality.contains(" shi")) {
			ret = subLocality.replace(" shi", "");
		} else {
			ret = locality.replace(" shi", "");
		}

		return ret;
	}

	/**
	 * 解析TencentMap返回的位置数据得到城市名称
	 * 
	 * @param jsonString
	 *            JSON数据
	 * @return 城市的中文名
	 */
	public static String getCityNameFromJSONForTencentMap(String jsonString) {

		String ret = "";

		String city = "";
		String district = "";

		Map<String, Object> data = getJsonFromString(jsonString);

		if (data.get("result") != null) {
			Map<String, Object> result = (Map<String, Object>) data.get("result");

			if (result.get("ad_info") != null) {
				Map<String, Object> addressInfo = (Map<String, Object>) result.get("ad_info");

				if (addressInfo.get("city") != null) {
					city = addressInfo.get("city").toString().replace(AgentConstant.MARK_CITY, "");
				}
				if (addressInfo.get("district") != null) {
					String subcity = addressInfo.get("district").toString();
					if (subcity.contains(AgentConstant.MARK_CITY)) {
						district = subcity;
					} else {
						district = city;
					}

					if (!("呼市郊区".equals(district) || "津市".equals(district) || "芒市".equals(district)
							|| "沙市".equals(district))) {
						district = district.replace(AgentConstant.MARK_CITY, "");
					}
				}
			}

			if (StringUtil.isNotBlank(city) && StringUtil.isNotBlank(district)) {
				ret = district + "|" + city;
			}
		}

		return ret;
	}

	/**
	 * 解析TencentMap返回的位置数据得到城市名称
	 * 
	 * @param jsonString
	 *            JSON数据
	 * @return 城市的中文名
	 */
	public static String getCityNameForTencentMap(String jsonString) {

		String ret = "";

		String city = "";

		Map<String, Object> data = getJsonFromString(jsonString);

		if (data.get("result") != null) {
			Map<String, Object> result = (Map<String, Object>) data.get("result");

			if (result.get("ad_info") != null) {
				Map<String, Object> addressInfo = (Map<String, Object>) result.get("ad_info");

				if (addressInfo.get("city") != null) {
					city = addressInfo.get("city").toString().replace(AgentConstant.MARK_CITY, "");
				}
			}

			if (StringUtil.isNotBlank(city)) {
				ret = city + "|" + city;
			}
		}

		return ret;
	}

	/**
	 * 得到INFLog中的16进制字符串
	 * 
	 * @param jsonData
	 *            JSON数据
	 * @return 16进制数据的字符串
	 */
	public static String getINFLogPropsFromJSON(String jsonData) {

		String code = "";
		ObjectMapper mapper = new ObjectMapper();

		try {
			Map<String, Object> jsonObj = mapper.readValue(jsonData, Map.class);

			if (jsonObj != null) {
				Object infLogList = jsonObj.get("infLogList");
				if (infLogList instanceof ArrayList<?>) {
					if (((ArrayList<?>) infLogList).size() > 0) {
						Object infLog = ((ArrayList<?>) infLogList).get(0);
						if (infLog instanceof Map) {
							Map<String, Object> m = (Map<String, Object>) infLog;
							// m.get("date");
							// m.get("deviceId");
							if (m.get("code") != null) {
								code = m.get("code").toString();
							}
						}
					}
				}
			}

		} catch (Exception e) {

		}

		return code;
	}

	/**
	 * 解析INF中的EPC与EDT数据
	 * 
	 * @param infList
	 *            INF中的infList
	 * @return 包含epc值的map
	 */
	public static Map<String, byte[]> getInfEpcValue(List<?> infList) {

		Map<String, byte[]> ret = new HashMap<String, byte[]>();

		for (Object obj : infList) {
			if (obj instanceof Map) {
				Map<String, Object> item = (Map<String, Object>) obj;
				String epc = "0x" + item.get("epc").toString().toLowerCase();
				if (item.get("edt") instanceof ArrayList<?>) {
					List<Integer> edt = (List) item.get("edt");
					byte[] edtByte = EncodeUtil.getByteFromIntList(edt);
					ret.put(epc, edtByte);
				}

			}
		}
		return ret;
	}

	/**
	 * 解析DeviceStatus中的status code数据
	 * 
	 * @param jsonData
	 *            JSON数据
	 * @return 包含epc值的map
	 */
	public static Map<String, byte[]> getDeviceStatusCode(String jsonData) {

		Map<String, byte[]> ret = new HashMap<String, byte[]>();

		Map<String, Object> jsonObject = JsonUtil.getJsonFromString(jsonData);

		if (jsonObject.get("deviceStatus") instanceof Map) {
			Map<String, Object> deviceStatus = (Map<String, Object>) jsonObject.get("deviceStatus");

			if (deviceStatus.get("status") instanceof List<?>) {
				List<?> statusList = (List<?>) deviceStatus.get("status");

				for (Object obj : statusList) {
					if (obj instanceof Map) {
						Map<String, Object> status = (Map<String, Object>) obj;

						String epc = "0x" + status.get("statusCode").toString().toLowerCase();
						if (status.get("valueType") != null) {
							String valueType = status.get("valueType").toString();
							if ("valueBinary".equalsIgnoreCase(valueType) && status.get("valueBinary") instanceof Map) {
								Map<String, Object> binaryData = (Map<String, Object>) status.get("valueBinary");

								if (binaryData.get("code") != null) {
									String code = binaryData.get("code").toString();
									byte[] data = EncodeUtil.hexToByteArray(code);
									ret.put(epc, data);
								}
							}
						}
					}

				}
			}
		}
		return ret;
	}

	/**
	 * 解析DeviceStatus中的status code数据
	 * 
	 * @param jsonData
	 *            JSON数据
	 * @return 包含epc值的map
	 */
	public static String getDeviceCode(String jsonData) {

		String ret = "";

		Map<String, Object> jsonObject = JsonUtil.getJsonFromString(jsonData);

		if (jsonObject.get("deviceStatus") instanceof Map) {
			Map<String, Object> deviceStatus = (Map<String, Object>) jsonObject.get("deviceStatus");

			if (deviceStatus.get("status") instanceof List<?>) {
				List<?> statusList = (List<?>) deviceStatus.get("status");

				for (Object obj : statusList) {
					if (obj instanceof Map) {
						Map<String, Object> status = (Map<String, Object>) obj;

						String epc = status.get("statusCode").toString().toLowerCase();
						if (status.get("valueType") != null) {
							String valueType = status.get("valueType").toString();
							if ("valueBinary".equalsIgnoreCase(valueType) && status.get("valueBinary") instanceof Map) {
								Map<String, Object> binaryData = (Map<String, Object>) status.get("valueBinary");

								if (binaryData.get("code") != null) {
									if ("F3".equalsIgnoreCase(epc)) {
										ret = binaryData.get("code").toString();
										break;
									}
								}
							}
						}
					}

				}
			}
		}
		return ret;
	}

	/**
	 * 获取F1的value
	 * 
	 * @param jsonData
	 *            JSON数据
	 * @return 包含epc值的map
	 */
	public static String getDeviceF1Code(String jsonData) {

		String ret = "";

		Map<String, Object> jsonObject = JsonUtil.getJsonFromString(jsonData);

		if (jsonObject.get("deviceStatus") instanceof Map) {
			Map<String, Object> deviceStatus = (Map<String, Object>) jsonObject.get("deviceStatus");

			if (deviceStatus.get("status") instanceof List<?>) {
				List<?> statusList = (List<?>) deviceStatus.get("status");

				for (Object obj : statusList) {
					if (obj instanceof Map) {
						Map<String, Object> status = (Map<String, Object>) obj;

						String epc = status.get("statusCode").toString().toLowerCase();
						if (status.get("valueType") != null) {
							String valueType = status.get("valueType").toString();
							if ("valueBinary".equalsIgnoreCase(valueType) && status.get("valueBinary") instanceof Map) {
								Map<String, Object> binaryData = (Map<String, Object>) status.get("valueBinary");

								if (binaryData.get("code") != null) {
									if ("F1".equalsIgnoreCase(epc)) {
										ret = binaryData.get("code").toString();
										break;
									}
								}
							}
						}
					}

				}
			}
		}
		return ret;
	}
	
	
	public static String getDeviceF2Code(String relatedEpc,String jsonData) {

		String ret = "";

		Map<String, Object> jsonObject = JsonUtil.getJsonFromString(jsonData);

		if (jsonObject.get("deviceStatus") instanceof Map) {
			Map<String, Object> deviceStatus = (Map<String, Object>) jsonObject.get("deviceStatus");

			if (deviceStatus.get("status") instanceof List<?>) {
				List<?> statusList = (List<?>) deviceStatus.get("status");

				for (Object obj : statusList) {
					if (obj instanceof Map) {
						Map<String, Object> status = (Map<String, Object>) obj;

						String epc = status.get("statusCode").toString().toLowerCase();
						if (status.get("valueType") != null) {
							String valueType = status.get("valueType").toString();
							if ("valueBinary".equalsIgnoreCase(valueType) && status.get("valueBinary") instanceof Map) {
								Map<String, Object> binaryData = (Map<String, Object>) status.get("valueBinary");

								if (binaryData.get("code") != null) {
									if (relatedEpc.equalsIgnoreCase(epc)) {
										ret = binaryData.get("code").toString();
										break;
									}
								}
							}
						}
					}

				}
			}
		}
		return ret;
	}


}
