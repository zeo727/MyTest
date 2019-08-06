package sharp_parent_test.test;


import org.json.JSONObject;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于从数据生成各种消息
 * 
 * @author Pactera
 *
 */
public class MessageHelper {

	private static final String VALUE_PAIR_FORMAT = "{0}{1}\n";


	/**
	 * 生成传感器数据表示用消息
	 * 
	 * @param sensorDataList
	 *            sensor数据列表
	 * @return 传感器消息
	 */
	public static String getSensorMessage(List<String[]> sensorDataList) {

		StringBuilder strb = new StringBuilder();
		strb.append(AgentConstant.LABEL_DEVICE + "\n");

		for (String[] sensor : sensorDataList) {
			String data = getSingleSensorInfo(sensor[0], sensor[1]);
			if (StringUtil.isNotBlank(data)) {
				strb.append(data + "\n");
			}
		}

		return strb.toString();
	}

	/**
	 * 将传感器ID和值转成对应的名字与显示信息
	 * 
	 * @param sensorId
	 *            传感器ID
	 * @param value
	 *            传感器值
	 * @return 传感器信息
	 */
	private static String getSingleSensorInfo(String sensorId, String value) {
		String ret = "";

		if (sensorId.contains("temperate") || sensorId.contains("temperature")) {
			ret = AgentConstant.SENSOR_NAME_TEMPERATURE;
			ret += "：" + value + AgentConstant.SENSOR_VALUE_UNIT_TEMPERATUE;
		} else if (sensorId.contains("humidity")) {
			ret = AgentConstant.SENSOR_NAME_HUMIDITY;
			ret += "：" + value + AgentConstant.SENSOR_VALUE_UNIT_HUMIDITY;
		} else if (sensorId.contains("pm")) {
			ret = AgentConstant.SENSOR_NAME_PM;
			ret += "：" + AgentConstant.SENSOR_VALUE_PM[Integer.valueOf(value)];
		} else if (sensorId.contains("mode")) {
			ret = AgentConstant.SENSOR_NAME_MODE;
			ret += "：" + AgentConstant.SENSOR_VALUE_MODE[Integer.valueOf(value)];
		} else if (sensorId.contains("dust")) {
			ret = AgentConstant.SENSOR_NAME_DUST;
			ret += "：" + AgentConstant.SENSOR_VALUE_DUST[Integer.valueOf(value)];
		} else if (sensorId.contains("odor")) {
			ret = AgentConstant.SENSOR_NAME_ODOR;
			ret += "：" + AgentConstant.SENSOR_VALUE_DUST[Integer.valueOf(value)];
		} else if (sensorId.contains("switch_on")) {
			ret = AgentConstant.SENSOR_NAME_SWITCH_ON;
			ret += "：" + AgentConstant.SENSOR_VALUE_MODE[Integer.valueOf(value)];
		} else if (sensorId.contains("switch_off")) {
			ret = AgentConstant.SENSOR_NAME_SWITCH_OFF;
			ret += "：" + AgentConstant.SENSOR_VALUE_SWITCH[Integer.valueOf(value)];
		} else if (sensorId.contains("humidification")) {
			ret = AgentConstant.SENSOR_NAME_SWITCH_HUMIDIFICATION;
			ret += "：" + AgentConstant.SENSOR_VALUE_SWITCH[Integer.valueOf(value)];
		} else if (sensorId.contains("humidi_status")) {
			ret = AgentConstant.SENSOR_NAME_HUMIDI_STATUS;
			ret += "：" + AgentConstant.SENSOR_VALUE_HUMIDI_STATUS[Integer.valueOf(value)];
		} else if (sensorId.contains("luminance")) {
			ret = AgentConstant.SENSOR_NAME_LUMINANCE;
			ret += "：" + AgentConstant.SENSOR_VALUE_LUMINANCE[Integer.valueOf(value)];
		} else if (sensorId.contains("pci")) {
			ret = AgentConstant.SENSOR_NAME_PCI;
			ret += "：" + AgentConstant.SENSOR_VALUE_SWITCH[Integer.valueOf(value)];
		} else if (sensorId.contains("time_on")) {
			ret = AgentConstant.SENSOR_NAME_TIME_ON;
			ret += "：" + AgentConstant.SENSOR_VALUE_SWITCH[Integer.valueOf(value)];
		} else if (sensorId.contains("time_on_value")) {
			ret = AgentConstant.SENSOR_NAME_TIME_ON_VALUE;
			ret += "：" + value + AgentConstant.SENSOR_VALUE_UNIT_TIME;
		} else if (sensorId.contains("time_off")) {
			ret = AgentConstant.SENSOR_NAME_TIME_OFF;
			ret += "：" + AgentConstant.SENSOR_VALUE_SWITCH[Integer.valueOf(value)];
		} else if (sensorId.contains("time_off_value")) {
			ret = AgentConstant.SENSOR_NAME_TIME_OFF_VALUE;
			ret += "：" + value + AgentConstant.SENSOR_VALUE_UNIT_TIME;
		} else if (sensorId.contains("error")) {
			if (Integer.valueOf(value) != 0) {
				ret = AgentConstant.SENSOR_NAME_ERROR;
				ret += ":" + getSensorErrorMessage(Integer.valueOf(value));
			}
		}

		return ret;
	}

	/**
	 * 取得传感器错误信息
	 * 
	 * @param errCode
	 *            错误代码
	 * @return 错误内容
	 */
	private static String getSensorErrorMessage(int errCode) {
		String ret = "";

		switch (errCode) {
		case 1:
			ret = AgentConstant.SENSOR_VALUE_ERROR_C1;
			break;
		case 2:
			ret = AgentConstant.SENSOR_VALUE_ERROR_C1;
			break;
		case 3:
			ret = AgentConstant.SENSOR_VALUE_ERROR_C1;
			break;
		case 4:
			ret = AgentConstant.SENSOR_VALUE_ERROR_C1;
			break;
		case 5:
			ret = AgentConstant.SENSOR_VALUE_ERROR_E2;
			break;
		case 6:
			ret = AgentConstant.SENSOR_VALUE_ERROR_E9;
			break;
		case 7:
			ret = AgentConstant.SENSOR_VALUE_ERROR_U3;
			break;
		default:
			ret = "";
		}
		return ret;
	}

	/**
	 * 生成天气表示用信息
	 * 
	 * @param data
	 *            解析从OpenWeather返回的JSON数据所得对象
	 * @return 天气信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getWeatherMessage(Map<String, Object> data) {

		StringBuilder strb = new StringBuilder();

		if (data.get("f") != null && data.get("c") != null) {

			// 获取城市名字
			// Map<String, Object> cityData = (Map<String, Object>)
			// data.get("c");
			/*
			 * if (cityData.get("c3") != null) { String cityName =
			 * cityData.get("c3").toString();
			 * strb.append(MessageFormat.format(AgentConstant.TITLE_CITY,
			 * cityName)); strb.append("\n"); }
			 */

			// 获取天气信息
			Map<String, Object> weatherData = (Map<String, Object>) data.get("f");

			// 获取发布时间
			int hour = 0;
			if (weatherData.get("f0") != null) {
				String time = weatherData.get("f0").toString();
				hour = Integer.valueOf(time.substring(time.length() - 4, time.length() - 2));
			}

			if (weatherData.get("f1") != null) {
				List dayList = (List) weatherData.get("f1");
				if (dayList.size() > 0) {
					Map<String, String> day = (Map) dayList.get(0);
					if (hour >= 16) {
						// 晚上气象
						if (day.get("fb") != null) {
							String phenomenon = getPhenomenon(day.get("fb"));
							strb.append(
									MessageFormat.format(VALUE_PAIR_FORMAT, AgentConstant.LABEL_WEATHER, phenomenon));
						}
						// 晚上温度
						if (day.get("fd") != null) {
							String temperature = day.get("fd") + AgentConstant.SENSOR_VALUE_UNIT_TEMPERATUE;
							strb.append(MessageFormat.format(VALUE_PAIR_FORMAT, AgentConstant.LABEL_TEMPERATURE,
									temperature));
						}
						// 晚上风向及风力
						if (day.get("ff") != null && day.get("fh") != null) {
							strb.append(AgentConstant.WEATHER_WDIR1);
							strb.append(AgentConstant.WEATHER_WDIR[Integer.valueOf(day.get("ff"))]);
							strb.append("\n");
							strb.append(AgentConstant.WEATHER_WIND_POWER1);
							strb.append(AgentConstant.WEATHER_WIND_POWER[Integer.valueOf(day.get("fh"))]);
							strb.append("\n");
						}
					} else {
						// 白天气象
						if (day.get("fa") != null) {
							String phenomenon = getPhenomenon(day.get("fa"));
							strb.append(
									MessageFormat.format(VALUE_PAIR_FORMAT, AgentConstant.LABEL_WEATHER, phenomenon));
						}
						// 白天温度
						if (day.get("fc") != null) {
							String temperature = day.get("fc") + AgentConstant.SENSOR_VALUE_UNIT_TEMPERATUE;
							strb.append(MessageFormat.format(VALUE_PAIR_FORMAT, AgentConstant.LABEL_TEMPERATURE,
									temperature));
						}
						// 白天风向及风力
						if (day.get("fe") != null && day.get("fg") != null) {
							strb.append(AgentConstant.WEATHER_WDIR1);
							strb.append(AgentConstant.WEATHER_WDIR[Integer.valueOf(day.get("fe"))]);
							strb.append("\n");
							strb.append(AgentConstant.WEATHER_WIND_POWER1);
							strb.append(AgentConstant.WEATHER_WIND_POWER[Integer.valueOf(day.get("fg"))]);
							strb.append("\n");
						}
					}
				}
			}
		}

		return strb.toString();
	}

	/**
	 * 生成天气和指数表示用信息
	 * 
	 * @param data
	 *            解析从OpenWeather返回的JSON数据所得对象
	 * @return 天气和指数信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getCityWeatherMessage(Map<String, Object> data) {

		StringBuilder strb = new StringBuilder();

		if (data.get("HeWeather data service 3.0") != null) {
			List dataList = (List) data.get("HeWeather data service 3.0");
			if (dataList != null) {
				Map<String, Object> dataMap = (Map) dataList.get(0);
				if (dataMap != null) {
					Map<String, Object> mapNow = (Map) dataMap.get("now");// now实况天气数据
					if (mapNow != null) {
						// 天气
						if (mapNow.get("cond") != null) {
							Map<String, Object> mapCond = (Map) mapNow.get("cond");// 天气信息
							if (mapCond.get("txt") != null) {
								String txt = (String) mapCond.get("txt");// 天气描述
								strb.append(MessageFormat.format(VALUE_PAIR_FORMAT, AgentConstant.LABEL_WEATHER, txt));
							}
						}

						// 温度
						if (mapNow.get("tmp") != null) {
							String temperature = mapNow.get("tmp") + AgentConstant.SENSOR_VALUE_UNIT_TEMPERATUE;
							strb.append(MessageFormat.format(VALUE_PAIR_FORMAT, AgentConstant.LABEL_TEMPERATURE,
									temperature));
						}

						// 风力状况
						if (mapNow.get("wind") != null) {
							Map<String, Object> mapWind = (Map) mapNow.get("wind");// 风力信息
							if (mapWind != null) {
								// 风向(方向)
								if (mapWind.get("dir") != null) {
									String dir = (String) mapWind.get("dir");
									strb.append(
											MessageFormat.format(VALUE_PAIR_FORMAT, AgentConstant.WEATHER_WDIR1, dir));
								}
								// 风力等级
								if (mapWind.get("sc") != null) {
									String sc = (String) mapWind.get("sc") + "级";
									strb.append(MessageFormat.format(VALUE_PAIR_FORMAT,
											AgentConstant.WEATHER_WIND_POWER1, sc));
								}
							}
						}
					}

					Map<String, Object> dataApi = (Map) dataMap.get("aqi");// aqi空气质量指数数据
					if (dataApi != null) {
						Map<String, Object> mapCity = (Map) dataApi.get("city");// 城市数据
						if (mapCity != null) {
							if (mapCity.get("aqi") != null) {
								String aqi = (String) mapCity.get("aqi");// 空气质量指数
								strb.append(MessageFormat.format(VALUE_PAIR_FORMAT, AgentConstant.LABEL_AQI, aqi));
							}
						}
					}

					Map<String, Object> dataSuggestion = (Map) dataMap.get("suggestion");// 生活指数
					if (dataSuggestion != null) {
						// 遍历生活指数数据
						for (String key : dataSuggestion.keySet()) {
							Map<String, Object> mapSug = (Map) dataSuggestion.get(key);
							if (mapSug != null) {
								String msg = "";
								switch (key) {
								case "cw":
									msg = "洗车指数：";
									break;
								case "drsg":
									msg = "穿衣指数：";
									break;
								case "flu":
									msg = "感冒指数：";
									break;
								case "sport":
									msg = "运动指数：";
									break;
								case "trav":
									msg = "旅游指数：";
									break;
								case "uv":
									msg = "紫外线指数：";
									break;
								}
								if (!"".equals(msg)) {
									strb.append(MessageFormat.format(VALUE_PAIR_FORMAT, msg, mapSug.get("brf")));
								}
							}
						}
					}
				}
			}
		}
		return strb.toString();
	}

	/**
	 * 生成天气和指数表示用信息
	 * 
	 * @param data
	 *            解析从OpenWeather返回的JSON数据所得对象
	 * @return 天气和指数信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getNewWeatherMessage(Map<String, Object> data) {

		StringBuilder strb = new StringBuilder();

		if (data.get("HeWeather data service 3.0") != null) {
			List dataList = (List) data.get("HeWeather data service 3.0");
			if (dataList != null) {
				Map<String, Object> dataMap = (Map) dataList.get(0);
				if (dataMap != null) {
					Map<String, Object> mapNow = (Map) dataMap.get("now");// now实况天气数据
					if (mapNow != null) {
						// 天气
						if (mapNow.get("cond") != null) {
							Map<String, Object> mapCond = (Map) mapNow.get("cond");// 天气信息
							if (mapCond.get("txt") != null) {
								String txt = (String) mapCond.get("txt");// 天气描述
								strb.append(MessageFormat.format(VALUE_PAIR_FORMAT, AgentConstant.LABEL_WEATHER, txt));
							}
						}

						// 温度
						if (mapNow.get("tmp") != null) {
							String temperature = mapNow.get("tmp") + AgentConstant.SENSOR_VALUE_UNIT_TEMPERATUE;
							strb.append(MessageFormat.format(VALUE_PAIR_FORMAT, AgentConstant.LABEL_TEMPERATURE,
									temperature));
						}

						// 风力状况
						if (mapNow.get("wind") != null) {
							Map<String, Object> mapWind = (Map) mapNow.get("wind");// 风力信息
							if (mapWind != null) {
								// 风向(方向)
								if (mapWind.get("dir") != null) {
									String dir = (String) mapWind.get("dir");
									strb.append(
											MessageFormat.format(VALUE_PAIR_FORMAT, AgentConstant.WEATHER_WDIR1, dir));
								}
								// 风力等级
								if (mapWind.get("sc") != null) {
									String sc = (String) mapWind.get("sc") + "级";
									strb.append(MessageFormat.format(VALUE_PAIR_FORMAT,
											AgentConstant.WEATHER_WIND_POWER1, sc));
								}
							}
						}
					}

					Map<String, Object> dataApi = (Map) dataMap.get("aqi");// aqi空气质量指数数据
					if (dataApi != null) {
						Map<String, Object> mapCity = (Map) dataApi.get("city");// 城市数据
						if (mapCity != null) {
							if (mapCity.get("aqi") != null) {
								String aqi = (String) mapCity.get("aqi");// 空气质量指数
								strb.append(MessageFormat.format(VALUE_PAIR_FORMAT, AgentConstant.LABEL_AQI, aqi));
							}
						}
					}

					Map<String, Object> dataSuggestion = (Map) dataMap.get("suggestion");// 生活指数
					if (dataSuggestion != null) {
						// 遍历生活指数数据
						for (String key : dataSuggestion.keySet()) {
							Map<String, Object> mapSug = (Map) dataSuggestion.get(key);
							if (mapSug != null) {
								String msg = "";
								switch (key) {
								case "cw":
									msg = "洗车指数：";
									break;
								case "drsg":
									msg = "穿衣指数：";
									break;
								case "flu":
									msg = "感冒指数：";
									break;
								case "sport":
									msg = "运动指数：";
									break;
								case "trav":
									msg = "旅游指数：";
									break;
								case "uv":
									msg = "紫外线指数：";
									break;
								}
								if (!"".equals(msg)) {
									strb.append(MessageFormat.format(VALUE_PAIR_FORMAT, msg, mapSug.get("brf")));
								}
							}
						}
					}
				}
			}
		}
		return strb.toString();
	}

	/**
	 * 生成天气和指数表示用信息
	 * 
	 * @param data
	 *            解析从OpenWeather返回的JSON数据所得对象
	 * @return 天气和指数信息Map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, String> getHeWeatherMessage(Map<String, Object> data, Object jsonPM25) {

		Map<String, String> weatherMap = new HashMap<String, String>();

		if (data.get("HeWeather data service 3.0") != null) {
			List dataList = (List) data.get("HeWeather data service 3.0");
			if (dataList != null) {
				Map<String, Object> dataMap = (Map) dataList.get(0);
				if (dataMap != null) {
					Map<String, Object> mapbasic = (Map) dataMap.get("basic");// basic
																				// 城市基本信息
					if (mapbasic != null) {
						// 城市信息
						if (mapbasic.get("city") != null) {
							String cityName = (String) mapbasic.get("city");// 城市名称
							if (cityName != null) {
								weatherMap.put("city", cityName);
							}
						}
					}

					Map<String, Object> mapNow = (Map) dataMap.get("now");// now实况天气数据
					if (mapNow != null) {
						// 天气
						if (mapNow.get("cond") != null) {
							Map<String, Object> mapCond = (Map) mapNow.get("cond");// 天气信息
							if (mapCond.get("txt") != null) {
								String txt = (String) mapCond.get("txt");// 天气描述
								if (txt != null) {
									weatherMap.put("weather", txt);
								}
							}
						}

						// 温度
						if (mapNow.get("tmp") != null) {
							String temperature = (String) mapNow.get("tmp");
							if (temperature != null) {
								weatherMap.put("temperature", temperature);
							}
						}

						// 风力状况
						if (mapNow.get("wind") != null) {
							Map<String, Object> mapWind = (Map) mapNow.get("wind");// 风力信息
							if (mapWind != null) {
								// 风向(方向)&等级
								if (mapWind.get("dir") != null && mapWind.get("sc") != null) {
									String dir = (String) mapWind.get("dir");
									String sc = (String) mapWind.get("sc") + "级";
									if (dir != null && sc != null) {
										weatherMap.put("windDirection", dir + sc);
									}
								}
							}
						}

						// Map<String, Object> dataApi = (Map)
						// dataMap.get("aqi");// aqi空气质量指数数据
						// if (dataApi != null) {
						// Map<String, Object> mapCity = (Map)
						// dataApi.get("city");// 城市数据
						// if (mapCity != null) {
						// if (mapCity.get("aqi") != null) {
						// String aqi = (String) mapCity.get("aqi");// 空气质量指数
						// if (aqi != null) {
						// weatherMap.put("aqi", aqi);
						// } else {
						// weatherMap.put("aqi",
						// AgentConstant.NULL_VALUE_STRING);
						// }
						// } else {
						// weatherMap.put("aqi",
						// AgentConstant.NULL_VALUE_STRING);
						// }
						//
						// if (mapCity.get("pm25") != null) {
						// String pm25 = (String) mapCity.get("pm25");// pm25
						// if (pm25 != null) {
						// weatherMap.put("pm", pm25);
						// } else {
						// weatherMap.put("pm",
						// AgentConstant.NULL_VALUE_STRING);
						// }
						// } else {
						// weatherMap.put("pm",
						// AgentConstant.NULL_VALUE_STRING);
						// }
						// }
						// } else {
						// weatherMap.put("aqi",
						// AgentConstant.NULL_VALUE_STRING);
						// weatherMap.put("pm",
						// AgentConstant.NULL_VALUE_STRING);
						// }

						Map<String, Object> dataSuggestion = (Map) dataMap.get("suggestion");// 生活指数
						if (dataSuggestion != null) {
							// 穿衣指数数据
							Map<String, Object> mapDrsg = (Map) dataSuggestion.get("drsg");
							if (mapDrsg != null) {
								// 穿衣指数详情
								if (mapDrsg.get("txt") != null) {
									String txt = (String) mapDrsg.get("txt");// 穿衣指数详情
									if (txt != null) {
										String[] arrayTxt = txt.split("。");
										if (arrayTxt.length == 1) {
											weatherMap.put("dressingIndex", arrayTxt[0] + "。");
										} else {
											String[] arrayTxt2 = arrayTxt[0].split("，");
											if (arrayTxt2.length > 2) {
												weatherMap.put("dressingIndex",
														arrayTxt2[0] + "，" + arrayTxt2[1] + "。");
											} else {
												weatherMap.put("dressingIndex", arrayTxt[0] + "。");
											}
										}
									}

									String brf = (String) mapDrsg.get("brf");// 穿衣指数简介
									if (brf != null) {
										weatherMap.put("comfortIndex", brf);
									}
								}
							}

							// 运动指数数据
							Map<String, Object> mapSport = (Map) dataSuggestion.get("sport");
							if (mapSport != null) {
								// 运动指数详情
								if (mapSport.get("brf") != null) {
									String brf = (String) mapSport.get("brf");// 运动指数详情
									if (brf != null) {
										weatherMap.put("morningExercises", brf);
									}
								}
							}
						}
					}
				}
			}
		}

		if (jsonPM25 != null) {
			if (jsonPM25 instanceof ArrayList<?>) {
				ArrayList<?> cityList = (ArrayList<?>) jsonPM25;
				Map<String, ?> dataObj = (Map) cityList.get(0);
				if (dataObj.get("aqi") != null) {
					weatherMap.put("aqi", dataObj.get("aqi").toString());
				} else {
					weatherMap.put("aqi", AgentConstant.NULL_VALUE_STRING);
				}

				if (dataObj.get("pm2_5") != null) {
					weatherMap.put("pm", dataObj.get("pm2_5").toString());
				} else {
					weatherMap.put("pm", AgentConstant.NULL_VALUE_STRING);
				}
			} else {
				weatherMap.put("aqi", AgentConstant.NULL_VALUE_STRING);
				weatherMap.put("pm", AgentConstant.NULL_VALUE_STRING);
			}
		} else {
			weatherMap.put("aqi", AgentConstant.NULL_VALUE_STRING);
			weatherMap.put("pm", AgentConstant.NULL_VALUE_STRING);
		}

		return weatherMap;
	}

	/**
	 * 生成天气指数信息
	 * 
	 * @param data
	 *            解析从OpenWeather返回的JSON数据所得对象
	 * @return 指数信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getWeatherIndexMessage(Map<String, Object> data) {

		StringBuilder strb = new StringBuilder();

		if (data.get("i") != null) {

			List<?> indexList = (ArrayList<?>) data.get("i");

			for (Object index : indexList) {
				Map<String, String> info = (Map) index;

				if (info.get("i2") != null && info.get("i4") != null) {
					// 指数名称
					String name = info.get("i2") + "：";
					// 指数数值
					String value = info.get("i4");
					// 指数说明
					// String comment = info.get("i5");
					strb.append(MessageFormat.format(VALUE_PAIR_FORMAT, name, value));
				}
			}
		}
		return strb.toString();
	}

	/**
	 * 获取气象名称
	 * 
	 * @param value
	 *            气象代码
	 * @return 名称
	 */
	private static final String getPhenomenon(String value) {

		String ret = "";

		switch (Integer.valueOf(value)) {
		case 0:
			ret = AgentConstant.WEATHER_PHENOMENON_0;
			break;
		case 1:
			ret = AgentConstant.WEATHER_PHENOMENON_1;
			break;
		case 2:
			ret = AgentConstant.WEATHER_PHENOMENON_2;
			break;
		case 3:
			ret = AgentConstant.WEATHER_PHENOMENON_3;
			break;
		case 4:
			ret = AgentConstant.WEATHER_PHENOMENON_4;
			break;
		case 5:
			ret = AgentConstant.WEATHER_PHENOMENON_5;
			break;
		case 6:
			ret = AgentConstant.WEATHER_PHENOMENON_6;
			break;
		case 7:
			ret = AgentConstant.WEATHER_PHENOMENON_7;
			break;
		case 8:
			ret = AgentConstant.WEATHER_PHENOMENON_8;
			break;
		case 9:
			ret = AgentConstant.WEATHER_PHENOMENON_9;
			break;
		case 10:
			ret = AgentConstant.WEATHER_PHENOMENON_10;
			break;
		case 11:
			ret = AgentConstant.WEATHER_PHENOMENON_11;
			break;
		case 12:
			ret = AgentConstant.WEATHER_PHENOMENON_12;
			break;
		case 13:
			ret = AgentConstant.WEATHER_PHENOMENON_13;
			break;
		case 14:
			ret = AgentConstant.WEATHER_PHENOMENON_14;
			break;
		case 15:
			ret = AgentConstant.WEATHER_PHENOMENON_15;
			break;
		case 16:
			ret = AgentConstant.WEATHER_PHENOMENON_16;
			break;
		case 17:
			ret = AgentConstant.WEATHER_PHENOMENON_17;
			break;
		case 18:
			ret = AgentConstant.WEATHER_PHENOMENON_18;
			break;
		case 19:
			ret = AgentConstant.WEATHER_PHENOMENON_19;
			break;
		case 20:
			ret = AgentConstant.WEATHER_PHENOMENON_20;
			break;
		case 21:
			ret = AgentConstant.WEATHER_PHENOMENON_21;
			break;
		case 22:
			ret = AgentConstant.WEATHER_PHENOMENON_22;
			break;
		case 23:
			ret = AgentConstant.WEATHER_PHENOMENON_23;
			break;
		case 24:
			ret = AgentConstant.WEATHER_PHENOMENON_24;
			break;
		case 25:
			ret = AgentConstant.WEATHER_PHENOMENON_25;
			break;
		case 26:
			ret = AgentConstant.WEATHER_PHENOMENON_26;
			break;
		case 27:
			ret = AgentConstant.WEATHER_PHENOMENON_27;
			break;
		case 28:
			ret = AgentConstant.WEATHER_PHENOMENON_28;
			break;
		case 29:
			ret = AgentConstant.WEATHER_PHENOMENON_29;
			break;
		case 30:
			ret = AgentConstant.WEATHER_PHENOMENON_30;
			break;
		case 31:
			ret = AgentConstant.WEATHER_PHENOMENON_31;
			break;
		case 53:
			ret = AgentConstant.WEATHER_PHENOMENON_53;
			break;
		case 99:
		default:
			break;
		}
		return ret;
	}

	/**
	 * 生成PM2.5表示消息
	 * 
	 * @param obj
	 *            解析从PM25.in返回的JSON数据所得对象
	 * @return PM2.5信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getPM25Message(Object obj) {

		StringBuilder strb = new StringBuilder();

		if (obj instanceof ArrayList<?>) {
			ArrayList<?> cityList = (ArrayList<?>) obj;
			Map<String, ?> dataObj = (Map) cityList.get(0);
			if (dataObj.get("aqi") != null) {
				strb.append(MessageFormat.format(VALUE_PAIR_FORMAT, AgentConstant.LABEL_AQI,
						dataObj.get("aqi").toString()));
			}

			if (dataObj.get("pm2_5") != null) {
				strb.append(MessageFormat.format(VALUE_PAIR_FORMAT, AgentConstant.LABEL_PM25,
						dataObj.get("pm2_5").toString()));
			}

			if (dataObj.get("so2") != null) {
				strb.append(MessageFormat.format(VALUE_PAIR_FORMAT, AgentConstant.LABEL_SO2,
						dataObj.get("so2").toString()));
			}

			if (dataObj.get("no2") != null) {
				strb.append(MessageFormat.format(VALUE_PAIR_FORMAT, AgentConstant.LABEL_NO2,
						dataObj.get("no2").toString()));
			}
		}

		return strb.toString();
	}

	/**
	 * 解析EPC中对应0xf3的值
	 * 
	 * @param mapEpc
	 * @return
	 */
	public static byte[] getSensorMessageForINF(Map<String, byte[]> mapEpc) {

		if (mapEpc.containsKey("0xf3")) {
			byte[] propOxF3 = mapEpc.get("0xf3");
			if (propOxF3.length < 4) {
				return new byte[] {};
			}

			// 0xf3,1byte,运转模式
			return propOxF3;

		}

		return new byte[] {};

	}


	public static byte[] getSensorMessageForINFByF2(Map<String, byte[]> mapEpc){
		if (mapEpc.containsKey("0xf2")){
			byte[] prop0xF2 = mapEpc.get("0xf2");
			if (prop0xF2.length<40){
				return  new byte[]{};
			}

			return  prop0xF2;
		}

		return  new byte[]{};
	}



	/**
	 * 获取模式字符串
	 * 
	 * @param value
	 *            模式值
	 * @return 模式名称
	 */
	private static String getModeValue(int value) {
		String ret = "";

		switch (value) {
		case 0x00:
			ret = AgentConstant.INF_VALUE_MODE[7];
			break;
		case 0x10:
			ret = AgentConstant.INF_VALUE_MODE[6];
			break;
		case 0x11:
			ret = AgentConstant.INF_VALUE_MODE[5];
			break;
		case 0x13:
			ret = AgentConstant.INF_VALUE_MODE[4];
			break;
		case 0x14:
			ret = AgentConstant.INF_VALUE_MODE[3];
			break;
		case 0x15:
			ret = AgentConstant.INF_VALUE_MODE[2];
			break;
		case 0x16:
			ret = AgentConstant.INF_VALUE_MODE[1];
			break;
		case 0x17:
			ret = AgentConstant.INF_VALUE_MODE[10];
			break;
		case 0x40:
			ret = AgentConstant.INF_VALUE_MODE[0];
			break;
		case 0x0e:
			ret = AgentConstant.INF_VALUE_MODE[8];
			break;
		case 0x1e:
			ret = AgentConstant.INF_VALUE_MODE[8];
			break;
		case 0x0f:
			ret = AgentConstant.INF_VALUE_MODE[9];
			break;
		case 0x20:
			ret = AgentConstant.INF_VALUE_MODE[11];
			break;
		case 0x18:
			ret = AgentConstant.INF_VALUE_MODE[12];
			break;
		default:
			break;
		}

		return ret;
	}


	/**
	 * @param value
	 * @param sensorAttrLimit
	 * @param sensorAttrDesc
	 * @param sensorAttrContent
	 * @return
	 */
	private static String getSensorLevel(int value, String sensorAttrLimit, String sensorAttrDesc,
			String sensorAttrContent) {
		String sensorLevel = "";
		boolean levelFlag = true;
		if (StringUtil.isNotBlank(sensorAttrLimit)) {
			String[] limitArray = sensorAttrLimit.split(",");
			String[] descArray = sensorAttrDesc.split(",");
			for (int i = 0; i < limitArray.length; i++) {
				if (value == Integer.parseInt(limitArray[i])) {
					sensorLevel = descArray[i];
					levelFlag = false;
					break;
				}
			}

			if (levelFlag) {
				if (value < Integer.parseInt(limitArray[0])) {
					sensorLevel = descArray[0];
				} else if (value > Integer.parseInt(limitArray[limitArray.length - 1])) {
					sensorLevel = descArray[limitArray.length - 1];
				} else {
					sensorLevel = String.valueOf(value);
				}
			}

			// 为结果加上对应的单位
			if (AgentConstant.SENSOR_NAME_TEMPERATURE.equals(sensorAttrContent)) {
				sensorLevel = sensorLevel + "度";
			} else if (AgentConstant.SENSOR_NAME_HUMIDITY.equals(sensorAttrContent)) {
				sensorLevel = sensorLevel + "%";
			}
		} else {
			System.out.println("new device PM2.5---->" + value);
			sensorLevel = String.valueOf(value);
		}

		return sensorLevel;
	}

	/**
	 * 获取定时时间值
	 * 
	 * @param value
	 *            时间值，以分为单位
	 * @return 通常表示的时间
	 */
	private static String getTimeOnOffValue(int value) {
		String ret = "";

		ret = value / 6 + "时" + (value % 6) * 10 + "分";

		return ret;
	}

	/**
	 * @param obj
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getPM25Value(Object obj) {

		String ret = "";

		JSONObject jsonObject = (JSONObject)obj;
		jsonObject.get("mmm");


		return ret;
	}

	/**
	 * @param obj
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int getPM25AllValue(Object obj) {

		int ret = 0;
		int count = 0;
		int total = 0;
		if (obj instanceof ArrayList<?>) {
			ArrayList<?> cityList = (ArrayList<?>) obj;
			Map<String, ?> dataObj = (Map) cityList.get(0);

			if (dataObj.get("pm2_5_24h") != null) {
				total = total + Integer.parseInt(dataObj.get("pm2_5_24h").toString());
				count = count + 1;
			}
		}
		if (!(count == 0 || total == 0)) {
			ret = total / count;
		}

		return ret;
	}

	/**
	 * @param obj
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, String> getAllPM25Info(Object obj, String workDate) {

		Map<String, String> ret = new HashMap<String, String>();
		if (obj instanceof ArrayList<?>) {
			ArrayList<?> cityList = (ArrayList<?>) obj;
			// Map<String, ?> dataObj = (Map) cityList.get(0);
			//
			// if (dataObj.get("pm2_5") != null) {
			// String pm25 = dataObj.get("pm2_5").toString();
			// String area = dataObj.get("area").toString();
			// ret.put(workDate + "," + area, pm25);
			// }

			for (Object object : cityList) {
				Map<String, ?> dataObj = (Map) object;
				if (dataObj.get("pm2_5") != null) {
					String pm25 = dataObj.get("pm2_5").toString();
					String area = dataObj.get("area").toString();
					ret.put(workDate + "," + area, pm25);
				}
			}
		}

		return ret;
	}

	/**
	 * 
	 * @param hexString
	 * @return 转换为二进制字符串
	 */
	private static String hexString2binaryString(String hexString) {
		if (hexString == null || hexString.length() % 2 != 0)
			return null;
		String bString = "", tmp;
		for (int i = 0; i < hexString.length(); i++) {
			tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
			bString += tmp.substring(tmp.length() - 4);
		}
		return bString;
	}
}
