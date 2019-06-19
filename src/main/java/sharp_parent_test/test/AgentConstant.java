package sharp_parent_test.test;

/**
 * Agent用常量
 * 
 * @author Pactera
 *
 */
public class AgentConstant {

	public static final String LABEL_DEVICE = "设备";
	// 传感器的名称
	public static final String SENSOR_NAME_TEMPERATURE = "温       度：";
	public static final String SENSOR_NAME_HUMIDITY = "湿       度：";
	public static final String SENSOR_NAME_PM = "PM   2.5：";
	public static final String SENSOR_NAME_MODE = "【运行状态】";
	public static final String SENSOR_NAME_DUST = "室       尘：";
	public static final String SENSOR_NAME_ODOR = "异       味：";
	public static final String SENSOR_NAME_SWITCH_ON = "开启运行";
	public static final String SENSOR_NAME_SWITCH_OFF = "停止运行";
	public static final String SENSOR_NAME_SWITCH_HUMIDIFICATION = "加湿";
	public static final String SENSOR_NAME_HUMIDI_STATUS = "加湿状态";
	public static final String SENSOR_NAME_LUMINANCE = "亮度调节";
	public static final String SENSOR_NAME_PCI = "PCI净离子群";
	public static final String SENSOR_NAME_TIME_ON = "定时开机：";
	public static final String SENSOR_NAME_TIME_ON_VALUE = "定时开机值";
	public static final String SENSOR_NAME_TIME_OFF = "定时关机：";
	public static final String SENSOR_NAME_TIME_OFF_VALUE = "定时关机值";
	public static final String SENSOR_NAME_ERROR = "出错";

	// 传感器的值对应信息
	public static final String SENSOR_VALUE_UNIT_TEMPERATUE = "℃";
	public static final String SENSOR_VALUE_UNIT_HUMIDITY = "%";
	public static final String SENSOR_VALUE_UNIT_TIME = "小时";
	public static final String SENSOR_VALUE_DUST[] = { "好", "普通", "差" };
	public static final String SENSOR_VALUE_PM[] = { "好", "较好", "普通", "较差", "差" };
	public static final String SENSOR_VALUE_MODE[] = { "全自动运行净化器模式", "强力定向净化模式", "强力快速净化模式", "睡眠模式", "节能模式", "花粉模式",
			"风量强模式", "风量中模式", "风量弱模式" };
	public static final String SENSOR_VALUE_SWITCH[] = { "关", "开" };
	public static final String SENSOR_VALUE_LUMINANCE[] = { "关", "暗", "开" };
	public static final String SENSOR_VALUE_HUMIDI_STATUS[] = { "加湿运行中，水箱有水", "加湿运行中，但水箱没水", "不加湿运行中，不显示" };

	public static final String SENSOR_VALUE_ERROR_C1 = "拔下电源插头，过一分多种以后请再次插入，并请再一次进行运行操作。如果出错信息反复显示时，请与购机商店或夏普的售后服务中心联系维修，维修电话400-018-2128。";
	public static final String SENSOR_VALUE_ERROR_E2 = "加湿过滤网·拖盘·滚轮是否都被可靠安装？请再一次进行运行操作；如果出错信息反复显示时，请与购机商店或夏普的售后服务中心联系维修，维修电话400-018-2128。";
	public static final String SENSOR_VALUE_ERROR_E9 = "离子发生装置是否被准确插入，取出离子发生装置，并在此插入直至地步，请再一次进行运行操作；如果出错信息反复显示时，请与购机商店或夏普的售后服务中心联系维修，维修电话400-018-2128。";
	public static final String SENSOR_VALUE_ERROR_U3 = "净离子群离子的浓度已经下降，请对离子发生装置（电极部）进行保养；如果出错信息反复显示时，请与购机商店或夏普的售后服务中心联系维修，维修电话400-018-2128。";

	public static final String INF_MESSAGE_1_1 = "过滤网维护保养指示灯正在亮灯。>>请对加湿过滤网等的周围部件和后面板的灰尘进行清扫。";
	public static final String INF_MESSAGE_1_2 = "过滤网维护保养指示灯正在亮灯。>>请对后面板或预过滤网的灰尘进行清扫。";
	public static final String INF_MESSAGE_1_3 = "集尘盒维护保养灯正在亮灯。>>请清扫灰尘。";
	public static final String INF_MESSAGE_2_1 = "净离子群发生器就要到更换时间了。";
	public static final String INF_MESSAGE_2_2 = "净离子群发生器该更换了。请根据使用说明书的要求进行更换。";
	public static final String INF_MESSAGE_2_3 = "滤网该更换了。请根据使用说明书的要求进行更换。";
	public static final String INF_MESSAGE_3_1 = "净离子群发生装置的针头部位有污染。>>请根据使用说明书的要求进行清扫。";
	public static final String INF_MESSAGE_3_2 = "加湿过滤网是否安装正确？>>请根据使用说明书的要求，对安装状况进行确认。";
	public static final String INF_MESSAGE_3_3 = "净离子群发生器是否安装正确？>>请根据使用说明书的要求，对安装状况进行确认。";
	public static final String INF_MESSAGE_3_4 = "数字显示区正在闪烁（显示2位数的错误号码●●）。>>请根据使用说明书中“这种时候”的内容进行确认。";
	public static final String INF_MESSAGE_3_5 = "自动清洁装置没有正确设置。>>请根据使用说明书的要求，进行正确安装。";

	public static final String INF_MESSAGE_1_11 = "过滤网维护保养指示灯正在亮灯。请对加湿过滤网等的周围部件和后面板的灰尘进行清扫。";
	public static final String INF_MESSAGE_1_21 = "过滤网维护保养指示灯正在亮灯。请对后面板或预过滤网的灰尘进行清扫。";
	public static final String INF_MESSAGE_1_31 = "集尘盒维护保养灯正在亮灯。请清扫灰尘。";
	public static final String INF_MESSAGE_2_11 = "净离子群发生器就要到更换时间了。";
	public static final String INF_MESSAGE_2_21 = "净离子群发生器该更换了。请根据使用说明书的要求进行更换。";
	public static final String INF_MESSAGE_2_31 = "滤网该更换了。请根据使用说明书的要求进行更换。";
	public static final String INF_MESSAGE_3_11 = "净离子群发生装置的针头部位有污染。请根据使用说明书的要求进行清扫。";
	public static final String INF_MESSAGE_3_21 = "加湿过滤网是否安装正确？请根据使用说明书的要求，对安装状况进行确认。";
	public static final String INF_MESSAGE_3_31 = "净离子群发生器是否安装正确？请根据使用说明书的要求，对安装状况进行确认。";
	public static final String INF_MESSAGE_3_41 = "数字显示区正在闪烁（显示2位数的错误号码●●）。请根据使用说明书中“这种时候”的内容进行确认。";
	public static final String INF_MESSAGE_3_51 = "自动清洁装置没有正确设置。请根据使用说明书的要求，进行正确安装。";

	public static final String WARNING_PM25_DUST_ODOR = "检测出室内PM2.5、灰尘及异味污染。使用净化模式可以快速洁净空气哦！/愉快";
	public static final String WARNING_PM25_DUST = "检测出室内PM2.5和灰尘污染。推荐您使用自动模式或净化模式哦/愉快";
	public static final String WARNING_PM25_ODOR = "检测出室内PM2.5和异味污染。推荐您使用自动模式或净化模式哦/愉快";
	public static final String WARNING_PM25 = "发现室内PM2.5污染。推荐您使用自动模式或净化模式哦/愉快";
	public static final String WARNING_DUST_ODOR = "检测出室内灰尘和异味污染。推荐您使用自动模式或净化模式哦/转圈";
	public static final String WARNING_ODOR = "检测出室内异味污染。推荐您使用自动模式或净化模式哦/转圈";
	public static final String WARNING_DUST = "发现室内灰尘污染。推荐您使用自动模式或净化模式哦/转圈";
	public static final String WARNING_CO_OZONE_CO2 = "检测出室内一氧化碳、二氧化碳及臭氧，建议您开窗通风或使用排气扇进行换气。/太阳";
	public static final String WARNING_CO_OZONE = "检测出室内一氧化碳和臭氧，建议您开窗通风或使用排气扇进行换气。/太阳";
	public static final String WARNING_CO_CO2 = "检测出室内一氧化碳和二氧化碳，建议您开窗通风或使用排气扇进行换气。/太阳";
	public static final String WARNING_CO = "检测出室内一氧化碳，建议您开窗通风或使用排气扇进行换气。/太阳";
	public static final String WARNING_OZONE_CO2 = "检测出室内臭氧和二氧化碳，建议您开窗通风或使用排气扇进行换气。/呲牙";
	public static final String WARNING_OZONE = "检测出室内臭氧。建议您开窗通风或使用排气扇进行换气。/呲牙";
	public static final String WARNING_CO2 = "检测出室内二氧化碳，建议您开窗通风或使用排气扇进行换气。/呲牙";
	public static final String WARNING_SUNSTRIKE = "室内温度变高了。请注意及时补水，或进行室内降温吧。/呲牙";
	public static final String WARNING_NONE = "室内空气变好啦！让我们努力保持空气健康吧！/拥抱";
	public static final String MESSAGE_FILTER_EXPIRED = "滤网使用寿命已到，请尽快更换！";
	
	public static final String MESSAGE_OFFLINE_AUTO="设备暂时处于离线状态，并且室外PM2.5高于阈值，建议开机净化！";

	public static final String TITLE_OUTSIDE = "【%s的空气状况】";
	public static final String TITLE_INSIDE = "【室内空气状况】";
	public static final String TITLE_CITY = "{0}的信息";
	public static final String TITLE_TIME = "【时间】yyyy/MM/dd HH:mm";
	public static final String TITLE_DEVICE = "【设备名称】\n{0}";
	public static final String MESSAGE_ANCHOR_DEVICESET = "* 如需查看其他地区空气状况，请在<a href=\"{0}\">【设定-设备管理-位置】</a>中选择。";
	public static final String MESSAGE_ANCHOR_DEVICESET1 = "* 如需查看其他设备状况，请在<a href=\"{0}\">【设定-设备选择】</a>中选择。";
	public static final String MESSAGE_NO_LOCATION_INFO = "\n目前未设定位置信息。可在【设备管理】页面为设备选择所在位置，以查看当地天气和PM2.5等情报。";
	public static final String MESSAGE_SERVICE_NOT_VALID = "\n目前服务不可用，请稍后再试。";

	// INF值的名称
	public static final String INF_NAME_TEMPERATURE = "空气综合：";
	public static final String INF_NAME_OZONE = "臭       氧：";
	public static final String INF_NAME_CO = "一氧化碳：";
	public static final String INF_NAME_CO2 = "二氧化碳：";
	public static final String INF_NAME_SUNSTROKE = "中暑警示：";

	// INF值对应信息
	public static final String INF_VALUE_QUALITY[] = { "干净", "轻度", "中度", "脏污" };
	public static final String ODOR_VALUE_QUALITY[] = { "正常", "轻度", "中度", "重度" };
	public static final String INF_VALUE_QUALITY1[] = { "干净", "较好", "脏污" };
	public static final String INF_VALUE_SAFETY[] = { "正常", "轻度", "中度", "重度" };
	public static final String INF_VALUE_MODE[] = { "浄化", "強", "中", "弱", "花粉", "睡眠", "自动", "停止", "清扫", "中止",
			"浄化", "自动" , "智能" };

	// 天气类标签
	public static final String LABEL_WEATHER = "天       气：";
	public static final String LABEL_TEMPERATURE = "温       度：";
	public static final String LABEL_HUMIDITY = "湿度";
	public static final String LABEL_PM25 = "PM   2.5：";
	public static final String LABEL_SO2 = "二氧化硫：";
	public static final String LABEL_NO2 = "二氧化氮：";
	public static final String LABEL_AQI = "空气质量指数：";
	public static final String WEATHER_WIND_POWER1 = "风       力：";
	public static final String WEATHER_WDIR1 = "风       向：";

	// 风力
	public static final String WEATHER_WIND_POWER[] = { "微风", "3-4级", "4-5级", "5-6级", "6-7级", "7-8级", "8-9级", "9-10级",
			"10-11级", "11-12级" };

	// 风向
	public static final String WEATHER_WDIR[] = { "无持续风向", "东北风", "东风", "东南风", "南风", "西南风", "西风", "西北风", "北风", "旋转风" };

	// 天气现象
	public static final String WEATHER_PHENOMENON_0 = "晴";
	public static final String WEATHER_PHENOMENON_1 = "多云";
	public static final String WEATHER_PHENOMENON_2 = "阴";
	public static final String WEATHER_PHENOMENON_3 = "阵雨";
	public static final String WEATHER_PHENOMENON_4 = "雷阵雨";
	public static final String WEATHER_PHENOMENON_5 = "雷阵雨伴有冰雹";
	public static final String WEATHER_PHENOMENON_6 = "雨夹雪";
	public static final String WEATHER_PHENOMENON_7 = "小雨";
	public static final String WEATHER_PHENOMENON_8 = "中雨";
	public static final String WEATHER_PHENOMENON_9 = "大雨";
	public static final String WEATHER_PHENOMENON_10 = "暴雨";
	public static final String WEATHER_PHENOMENON_11 = "大暴雨";
	public static final String WEATHER_PHENOMENON_12 = "特大暴雨";
	public static final String WEATHER_PHENOMENON_13 = "阵雪";
	public static final String WEATHER_PHENOMENON_14 = "小雪";
	public static final String WEATHER_PHENOMENON_15 = "中雪";
	public static final String WEATHER_PHENOMENON_16 = "大雪";
	public static final String WEATHER_PHENOMENON_17 = "暴雪";
	public static final String WEATHER_PHENOMENON_18 = "雾";
	public static final String WEATHER_PHENOMENON_19 = "冻雨";
	public static final String WEATHER_PHENOMENON_20 = "沙尘暴";
	public static final String WEATHER_PHENOMENON_21 = "小到中雨";
	public static final String WEATHER_PHENOMENON_22 = "中到大雨";
	public static final String WEATHER_PHENOMENON_23 = "大到暴雨";
	public static final String WEATHER_PHENOMENON_24 = "暴雨到大暴雨";
	public static final String WEATHER_PHENOMENON_25 = "大暴雨到特大暴雨";
	public static final String WEATHER_PHENOMENON_26 = "小到中雪";
	public static final String WEATHER_PHENOMENON_27 = "中到大雪";
	public static final String WEATHER_PHENOMENON_28 = "大到暴雪";
	public static final String WEATHER_PHENOMENON_29 = "浮尘";
	public static final String WEATHER_PHENOMENON_30 = "扬沙";
	public static final String WEATHER_PHENOMENON_31 = "强沙尘暴";
	public static final String WEATHER_PHENOMENON_53 = "霾";
	public static final String WEATHER_PHENOMENON_99 = "无";

	public static final String MARK_CITY = "市";
	public static final String APP_SECRET = "OPBKAEhw1gTa0RthYTGqwk1d5nwClollldU1qpvUBrR";
	public static final String KV_EXCUTE_RECORD = "kvstore_excute_record";
	public static final String KV_EXCUTE_SMART_RECORD = "kvstore_excute_smart_record";
	
	public static final String NULL_VALUE_STRING="--";
}
